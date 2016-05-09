package org.meruvian.yama.core.sekolah;

import org.meruvian.yama.core.DefaultRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.mapping.PropertyReferenceException;

public interface JurusanRepository extends DefaultRepository<Jurusan>{
	//Jurusan findByKode(String jurusan);
	
	@Query("SELECT j FROM Jurusan j WHERE j.keterangan LIKE %?1% AND j.logInformation.activeFlag = ?2")
	Page<Jurusan> findByJurusan(String keterangan, int activeFlag, Pageable pageable);
}
