package org.meruvian.yama.webapi.service.sekolah;

import javax.inject.Inject;
import javax.ws.rs.BadRequestException;

import org.apache.commons.lang3.StringUtils;
import org.meruvian.yama.core.LogInformation;
import org.meruvian.yama.core.siswa.Agama;
import org.meruvian.yama.core.siswa.AgamaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
public class RestAgamaService implements AgamaService {
	@Inject
	private AgamaRepository agamaRepository;
	
	@Override
	public Agama getAgamaById(String id){
		return agamaRepository.findById(id);
	}
	
	@Override
	public Page<Agama> findAgamaByKeyword(String keyword, Pageable pageable) {
		return agamaRepository.findByAgama(keyword, LogInformation.ACTIVE, pageable);
	}
	
	@Override
	@Transactional
	public void removeAgama(String agama) {
		getAgamaById(agama).getLogInformation().setActiveFlag(LogInformation.INACTIVE);
	}
	
	@Override
	@Transactional
	public Agama saveAgama(Agama agama){
		/*if (StringUtils.isBlank(agama.getId())){
			agama.setId(null);
			agama.setAgama(agama.getAgama());
			*/
			return agamaRepository.save(agama);
		//}
		// throw new BadRequestException("Id must be empty, use PUT method to update record");
	}
	
	@Override
	@Transactional
	public Agama updateAgama(Agama agama){
		Agama a=agamaRepository.findById(agama.getId());
		a.setAgama(agama.getAgama());
		return a;
	}
}
