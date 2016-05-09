package org.meruvian.yama.core.siswa;

//import java.util.List;

import org.meruvian.yama.core.DefaultRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

public interface SiswaRepository extends DefaultRepository<Siswa>{
	Siswa findByNis(String nis);
	Siswa findByNik(String nik);
	Siswa findByNama(String nama);
	
	@Query("SELECT s FROM Siswa s WHERE (s.nis LIKE %?1% OR s.nik LIKE %?2% OR s.nama LIKE %?3%) AND s.logInformation.activeFlag = ?4")
	Page<Siswa> findByNisOrNikOrNama(String nis, String nik, String nama, int activeFlag, Pageable pageable);
	
	//@Query("SELECT s FROM Siswa s WHERE s.nis LIKE %?1% OR s.nik LIKE %?2% OR s.nama LIKE %?3%")
	//List<Siswa> findByNisNikNama(String nis, String nik, String nama);
}
