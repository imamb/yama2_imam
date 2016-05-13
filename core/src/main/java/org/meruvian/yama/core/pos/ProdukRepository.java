package org.meruvian.yama.core.pos;

import org.meruvian.yama.core.DefaultRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author IMAM BADRUDIN
 * 
 */
@Repository
public interface ProdukRepository extends DefaultRepository<Produk>{
	Produk findByKode(String kode);
	Produk findByNama(String nama);
	
	@Query("SELECT p FROM Produk p WHERE (p.nama LIKE %?1% OR p.kode LIKE %?2%) AND p.logInformation.activeFlag = ?3")
	Page<Produk> findByNama(String nama,String kode,int activeFlag,Pageable pageable);
	

}
