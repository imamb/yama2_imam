package org.meruvian.yama.webapi.service.pos;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.meruvian.yama.core.LogInformation;
import org.meruvian.yama.core.pos.DetailJual;
import org.meruvian.yama.core.pos.DetailJualRepository;
import org.meruvian.yama.core.pos.Penjualan;
import org.meruvian.yama.core.pos.PenjualanRepository;
import org.meruvian.yama.core.pos.Produk;
import org.meruvian.yama.core.pos.ProdukRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class RestPenjualanService implements PenjualanService {
	@Inject
	private PenjualanRepository penjualanRepository;
	
	@Inject
	private DetailJualRepository detailjualRepository;
	
	@Inject
	private ProdukRepository produkRepository;
	
	@Override
	public Penjualan getPenjualanById(String id){
		return penjualanRepository.findById(id);
	}
	
	@Override
	public Page<Penjualan> findPenjualanByNomor(String nomor,Pageable pageable){
		return penjualanRepository.findByNama(nomor, nomor, LogInformation.ACTIVE, pageable);
	}
	
	@Override
	@Transactional
	public void removePenjualan(String id) {
		getPenjualanById(id).getLogInformation().setActiveFlag(LogInformation.INACTIVE);
	}
	
	@Override
	@Transactional
	public Penjualan savePenjualan(Penjualan penjualan){
		return penjualanRepository.save(penjualan);
	}
	
	@Override
	@Transactional
	public Penjualan updatePenjualan(Penjualan penjualan){
		Penjualan p=getPenjualanById(penjualan.getId());
		p.setKasir(penjualan.getKasir());
		p.setNomor(penjualan.getNomor());
		p.setPembeli(penjualan.getPembeli());
		return p;
	}
	/*
	@Override
	@Transactional
	public Produk updateProduk(Produk produk){
		Produk p=produkRepository.findById(produk.getId());
		p.setStok(p.getStok()-1);
		return p;
	}
	*/
	
	@Override
	@Transactional
	public boolean addProdukToPenjualan(String id, String produkId) {
		Penjualan p=getPenjualanById(id);
		Produk pp=produkRepository.findById(produkId);
		
		if(pp.getStok()==0){return false;}
		for(DetailJual dj: p.getProduks()){
			if(dj.getProduk().getId()==produkId){
				//p.setTotal(p.getTotal()+dj.getProduk().getHarga());
				//dj.setJumlah(dj.getJumlah()+1);
				return false;
			}
		}
		DetailJual dt=new DetailJual();
		dt.setPenjualan(p);
		Produk pr=new Produk();
		pr.setId(produkId);
		dt.setProduk(pr);
		dt.setHarga(pr.getHarga());
		dt.setJumlah(1);
				
		
		detailjualRepository.save(dt);
		pp.setStok(pp.getStok()-1);
		return true;
	}
	
	@Override
	@Transactional
	public boolean removeProdukFromPenjualan(String id, String produkId) {
		Penjualan p=getPenjualanById(id);
		DetailJual dj=detailjualRepository.findByProdukIdAndPenjualanId(produkId, p.getId());
		p.setTotal(p.getTotal()-(dj.getHarga()*dj.getJumlah()));
		
		Produk pp=produkRepository.findById(produkId);
		pp.setStok(pp.getStok()+dj.getJumlah());
		
		detailjualRepository.delete(dj);
		return true;	
	}
	
	@Override
	@Transactional
	public boolean removeAllProdukFromPenjualan(String id) {
		Penjualan p=getPenjualanById(id);
		for(DetailJual dj: p.getProduks()){
			Produk pp=produkRepository.findById(dj.getProduk().getId());
			pp.setStok(pp.getStok()+dj.getJumlah());
		}
		p.setTotal(0);
		detailjualRepository.delete(p.getProduks());
		return true;
	}
	
	@Override
	public Page<Produk> findProdukByPenjualan(String id, Pageable pageable){
		Penjualan p=getPenjualanById(id);
		Page<DetailJual> detailJuals=detailjualRepository.findByPenjualanId(p.getId(), pageable);
		
		List<Produk> produks=new ArrayList<Produk>();
		
		for(DetailJual dj:detailJuals){
			produks.add(dj.getProduk());
		}
		
		return new PageImpl<Produk>(produks,pageable,detailJuals.getTotalElements());
	}
}
