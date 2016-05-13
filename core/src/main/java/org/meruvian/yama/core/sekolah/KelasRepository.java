package org.meruvian.yama.core.sekolah;

import org.meruvian.yama.core.DefaultRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

public interface KelasRepository extends DefaultRepository<Kelas>{
	Kelas findByKelas(String kelas);
	
	Page<Kelas> findKelasById(String id, Pageable pageable);
	
	@Query("SELECT k FROM Kelas k WHERE k.kelas LIKE %?1%")
	Page<Kelas> findByKelas(String kelas, Pageable pageable);
	
	@Query("SELECT k FROM Kelas k WHERE k.kelas LIKE %?1% AND k.logInformation.activeFlag = ?2")
	Page<Kelas> findByKelas(String kelas, int activeFlag, Pageable pageable);
}
