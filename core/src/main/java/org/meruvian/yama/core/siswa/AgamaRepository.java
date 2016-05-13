package org.meruvian.yama.core.siswa;

import org.meruvian.yama.core.DefaultRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

public interface AgamaRepository extends DefaultRepository<Agama> {
	Agama findByAgama(String agama);
	
	//Page<Agama> findByAgamaId(String id, Pageable pageable);
	
	@Query("SELECT a FROM Agama a WHERE a.agama LIKE %?1%")
	Page<Agama> findByAgama(String agama, Pageable pageable);
	
	@Query("SELECT a FROM Agama a WHERE a.agama LIKE %?1% AND a.logInformation.activeFlag = ?2")
	Page<Agama> findByAgama(String agama, int activeFlag, Pageable pageable);

}
