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
import org.springframework.transaction.annotation.Transactional;

public class RestPembelianService implements PembelianService{
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
		return pembelianRepository.findByNomor(nomor, nomor, nomor, LogInformation.ACTIVE, pageable);
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
	
	@Override
	@Transactional
	public boolean addProdukToPembelian(String id, String produkId) {
		Pembelian p=getPembelianById(id);
		Produk pp=produkRepository.findById(produkId);
		
		for(DetailBeli dt:p.getProduks()){
			if(dt.getProduk().getId()==produkId){
				return false;
			}
		}
		
		DetailBeli dt=new DetailBeli();
		dt.setPembelian(p);
		Produk pr=new Produk();
		pr.setId(produkId);
		dt.setProduk(pr);
		dt.setJumlah(1);
		dt.setHarga(pr.getHarga());
		
		p.setTotal(p.getTotal()-(dt.getHarga()*dt.getJumlah()));
		
		detailbeliRepository.save(dt);
		pp.setStok(pp.getStok()+1);
		return true;
	}
	
	@Override
	@Transactional
	public boolean removeProdukFromPembelian(String id, String produkId) {
		Pembelian p=getPembelianById(id);
		DetailBeli dt=detailbeliRepository.findByProdukIdAndPembelianId(produkId, p.getId());
		Produk pp=produkRepository.findById(produkId);
		pp.setStok(pp.getStok()-dt.getJumlah());
		p.setTotal(p.getTotal()-(dt.getHarga()*dt.getJumlah()));
		detailbeliRepository.delete(dt);
		return true;
	}
	
	@Override
	@Transactional
	public boolean removeAllProdukFromPembelian(String id) {
		Pembelian p=getPembelianById(id);
		for(DetailBeli dt:p.getProduks()){
			Produk pp=produkRepository.findById(dt.getProduk().getId());
			pp.setStok(pp.getStok()-dt.getJumlah());
		}
		p.setTotal(0);
		detailbeliRepository.delete(p.getProduks());
		return true;
	}
	
	@Override
	public Page<Produk> findProdukByPembelian(String id, Pageable pageable){
		Pembelian p=getPembelianById(id);
		Page<DetailBeli> detailBelis=detailbeliRepository.findByPembelianId(p.getId(), pageable);
		
		List<Produk> produks=new ArrayList<Produk>();
		for(DetailBeli dt:detailBelis){
			produks.add(dt.getProduk());
		}
		return new PageImpl<Produk>(produks,pageable,detailBelis.getTotalElements());
	}
}
