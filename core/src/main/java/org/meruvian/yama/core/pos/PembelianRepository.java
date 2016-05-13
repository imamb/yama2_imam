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
public interface PembelianRepository extends DefaultRepository<Pembelian>{
	Pembelian findByNomor(String nomor);
	Pembelian findBySurat(String surat);
	Pembelian findByPenjual(String penjual);
	
	@Query("SELECT p FROM Pembelian p WHERE (p.nomor LIKE %?1% OR p.surat LIKE %?2% OR p.penjual LIKE %?3%) AND p.logInformation.activeFlag = ?4")
	Page<Pembelian> findByNomor(String nomor,String surat,String penjual,int activeFlag,Pageable pageable);

}
