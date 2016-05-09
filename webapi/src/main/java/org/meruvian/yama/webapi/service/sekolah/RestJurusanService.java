package org.meruvian.yama.webapi.service.sekolah;

import javax.inject.Inject;
//import javax.ws.rs.BadRequestException;

//import org.apache.commons.lang3.StringUtils;
import org.meruvian.yama.core.LogInformation;
import org.meruvian.yama.core.sekolah.Jurusan;
import org.meruvian.yama.core.sekolah.JurusanRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
//import org.springframework.beans.factory.BeanCreationException;

@Service
@Transactional(readOnly = true)
public class RestJurusanService implements JurusanService {
	@Inject
	private JurusanRepository jurusanRepository;
	
	@Override
	public Jurusan getJurusanById(String id){
		return jurusanRepository.findById(id);
	}

	@Override
	public Page<Jurusan> findJurusanByKeyword(String keyword, Pageable pageable) {
		return jurusanRepository.findByJurusan(keyword, LogInformation.ACTIVE, pageable);
	}
	
	@Override
	@Transactional
	public void removeJurusan(String jurusan) {
		getJurusanById(jurusan).getLogInformation().setActiveFlag(LogInformation.INACTIVE);
	}
	
	@Override
	@Transactional
	public Jurusan saveJurusan(Jurusan jurusan){
		/* if (StringUtils.isBlank(jurusan.getId())){
			jurusan.setId(null);
			jurusan.setJur(jurusan.getJur());
			jurusan.setKeterangan(jurusan.getKeterangan());
			jurusanRepository.save(jurusan);	
		}
		throw new BadRequestException("Id must be empty, use PUT method to update record");
		*/
		//jurusan.setId(null);
		return jurusanRepository.save(jurusan);
	}
	
	@Override
	@Transactional
	public Jurusan updateJurusan(Jurusan jurusan){
		Jurusan j=jurusanRepository.findById(jurusan.getId());
			j.setJur(jurusan.getJur());
			j.setKeterangan(jurusan.getKeterangan());
			return j;	
	}
}
