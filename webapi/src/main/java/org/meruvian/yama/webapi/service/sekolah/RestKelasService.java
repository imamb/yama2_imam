package org.meruvian.yama.webapi.service.sekolah;

import javax.inject.Inject;
import javax.ws.rs.BadRequestException;

import org.apache.commons.lang3.StringUtils;
import org.meruvian.yama.core.LogInformation;
//import org.meruvian.yama.core.sekolah.JurusanRepository;
import org.meruvian.yama.core.sekolah.Kelas;
import org.meruvian.yama.core.sekolah.KelasRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class RestKelasService implements KelasService {
	@Inject
	private KelasRepository kelasRepository;
	
	//@Inject
	//private JurusanRepository jurusanRepository;
	
	@Override
	public Kelas getKelasById(String id){
		return kelasRepository.findById(id);
	}
	
	@Override
	public Page<Kelas> findKelasByKeyword(String keyword, Pageable pageable) {
		return kelasRepository.findByKelas(keyword, LogInformation.ACTIVE, pageable);
	}
	
	@Override
	@Transactional
	public void removeKelas(String kelas){
		getKelasById(kelas).getLogInformation().setActiveFlag(LogInformation.INACTIVE);
	}
	
	@Override
	@Transactional
	public Kelas saveKelas(Kelas kelas){
		//if (StringUtils.isBlank(kelas.getId())){
		//	kelas.setId(null);
		//	kelas.setKelas(kelas.getKelas());
			//kelas.setJurusan(jurusan);
			return kelasRepository.save(kelas);
		//}
		//throw new BadRequestException("Id must be empty, use PUT method to update record");
	}
	
	@Override
	@Transactional
	public Kelas updateKelas(Kelas kelas){
		Kelas k=kelasRepository.findById(kelas.getId());
		k.setKelas(kelas.getKelas());
		//k.setJurusan(jurusanRepository.findById(kelas.getJurusan()));
		k.setJurusan(kelas.getJurusan());
		return k;
	}
}
