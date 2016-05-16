package org.meruvian.yama.webapi.service.pos;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.meruvian.yama.core.LogInformation;
import org.meruvian.yama.core.pos.DetailBeli;
import org.meruvian.yama.core.pos.DetailBeliRepository;
import org.meruvian.yama.core.pos.Pembelian;
import org.meruvian.yama.core.pos.PembelianRepository;
import org.meruvian.yama.core.pos.Produk;
import org.meruvian.yama.core.pos.ProdukRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class RestPembelianService implements PembelianService {
	@Inject
	private PembelianRepository pembelianRepository;
	
	@Inject
	private DetailBeliRepository detailbeliRepository;
	
	@Inject
	private ProdukRepository produkRepository;
	
	@Override
	public Pembelian getPembelianById(String id){
		return pembelianRepository.findById(id);
	}
	
	@Override
	public Page<Pembelian> findPembelianByNomor(String nomor,Pageable pageable){
		return pembelianRepository.findByNomor(nomor,nomor, nomor, LogInformation.ACTIVE, pageable);
	}
	
	@Override
	@Transactional
	public void removePembelian(String id) {
		getPembelianById(id).getLogInformation().setActiveFlag(LogInformation.INACTIVE);
	}
	
	@Override
	@Transactional
	public Pembelian savePembelian(Pembelian pembelian){
		return pembelianRepository.save(pembelian);
	}
	
	@Override
	@Transactional
	public Pembelian updatePembelian(Pembelian pembelian){
		Pembelian p=getPembelianById(pembelian.getId());
		p.setKasir(pembelian.getKasir());
		p.setNomor(pembelian.getNomor());
		p.setPenjual(pembelian.getPenjual());
		p.setSurat(pembelian.getSurat());
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
	public boolean addProdukToPembelian(String id, String produkId) {
		Pembelian p=getPembelianById(id);
		Produk pp=produkRepository.findById(produkId);
		Pembelian pj=pembelianRepository.findById(id);
		
		if(pp.getStok()==0){return false;}
		for(DetailBeli dj: p.getProduks()){
			if(dj.getProduk().getId()==produkId){
				//pj.setTotal(pj.getTotal()+dj.getProduk().getHarga());
				//dj.setJumlah(dj.getJumlah()+1);
				return false;
			}
		}
		DetailBeli dt=new DetailBeli();
		dt.setPembelian(p);
		Produk pr=new Produk();
		pr.setId(produkId);
		dt.setProduk(pr);
		dt.setHarga(pr.getHarga());
		dt.setJumlah(1);
		pj.setTotal(pj.getTotal()+(dt.getHarga()*dt.getJumlah()));	
		pp.setStok(pp.getStok()+dt.getJumlah());
		
		detailbeliRepository.save(dt);
		
		return true;
	}
	
	@Override
	@Transactional
	public boolean removeProdukFromPembelian(String id, String produkId) {
		Pembelian p=getPembelianById(id);
		Pembelian pj=pembelianRepository.findById(id);
		DetailBeli dj=detailbeliRepository.findByProdukIdAndPembelianId(produkId, p.getId());
		pj.setTotal(pj.getTotal()-(dj.getHarga()*dj.getJumlah()));
		
		Produk pp=produkRepository.findById(produkId);
		pp.setStok(pp.getStok()-dj.getJumlah());
		
		detailbeliRepository.delete(dj);
		return true;	
	}
	
	@Override
	@Transactional
	public boolean removeAllProdukFromPembelian(String id) {
		Pembelian p=getPembelianById(id);
		Pembelian pj=pembelianRepository.findById(id);
		for(DetailBeli dj: p.getProduks()){
			Produk pp=produkRepository.findById(dj.getProduk().getId());
			pp.setStok(pp.getStok()-dj.getJumlah());
		}
		pj.setTotal(0);
		detailbeliRepository.delete(p.getProduks());
		return true;
	}
	
	@Override
	public Page<Produk> findProdukByPembelian(String id, Pageable pageable){
		Pembelian p=getPembelianById(id);
		Page<DetailBeli> detailBelis=detailbeliRepository.findByPembelianId(p.getId(), pageable);
		
		List<Produk> produks=new ArrayList<Produk>();
		
		for(DetailBeli dj:detailBelis){
			produks.add(dj.getProduk());
		}
		
		return new PageImpl<Produk>(produks,pageable,detailBelis.getTotalElements());
	}

}
