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
public interface PenjualanRepository extends DefaultRepository<Penjualan>{
	Penjualan findByNomor(String nomor);
	Penjualan findByPembeli(String pembeli);
	
	@Query("SELECT p FROM Penjualan p WHERE (p.nomor LIKE %?1% OR p.pembeli LIKE %?2%) AND p.logInformation.activeFlag = ?3")
	Page<Penjualan> findByNama(String nomor,String pembeli,int activeFlag,Pageable pageable);
}
