package org.meruvian.yama.core.pos;

import org.meruvian.yama.core.DefaultRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

/**
 * @author IMAM BADRUDIN
 * 
 */
@Repository
public interface DetailJualRepository extends DefaultRepository<DetailJual> {
	Page<DetailJual> findByProdukId(String id, Pageable pageable);
	Page<DetailJual> findByProdukNama(String nama, Pageable pageable);
	
	Page<DetailJual> findByPenjualanId(String id, Pageable pageable);
	Page<DetailJual> findByPenjualanNomor(String nomor, Pageable pageable);
	
	DetailJual findByProdukIdAndPenjualanId(String produkId, String penjualanId);
}
