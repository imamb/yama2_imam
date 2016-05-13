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
public interface DetailBeliRepository extends DefaultRepository<DetailBeli>{
	Page<DetailBeli> findByProdukId(String id, Pageable pageable);
	Page<DetailBeli> findByProdukNama(String nama, Pageable pageable);
	
	Page<DetailBeli> findByPembelianId(String id, Pageable pageable);
	Page<DetailBeli> findByPembelianNomor(String nomor, Pageable pageable);
	
	DetailBeli findByProdukIdAndPembelianId(String produkId, String pembelianId);
}
