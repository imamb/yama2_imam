package org.meruvian.yama.webapi.service.pos;

//import java.util.List;

import javax.inject.Inject;

import org.meruvian.yama.core.LogInformation;
import org.meruvian.yama.core.pos.Produk;
import org.meruvian.yama.core.pos.ProdukRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class RestProdukService implements ProdukService {
	@Inject
	private ProdukRepository produkRepository;
	
	@Override
	public Produk getProdukById(String id){
		return produkRepository.findById(id);
	}
	
	@Override
	public Page<Produk> findProdukByNama(String nama,Pageable pageable){
		return produkRepository.findByNama(nama, nama, LogInformation.ACTIVE, pageable);
	}
	
	@Override
	@Transactional
	public void removeProduk(String id) {
		getProdukById(id).getLogInformation().setActiveFlag(LogInformation.INACTIVE);
	}
	
	@Override
	@Transactional
	public Produk saveProduk(Produk produk){
		return produkRepository.save(produk);
	}
	
	@Override
	@Transactional
	public Produk updateProduk(Produk produk){
		Produk p=produkRepository.findById(produk.getId());
		p.setHarga(produk.getHarga());
		p.setKeterangan(produk.getKeterangan());
		p.setKode(produk.getKode());
		p.setNama(produk.getNama());
		
		return p;
	}

}
