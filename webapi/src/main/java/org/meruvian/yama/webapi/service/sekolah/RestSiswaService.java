package org.meruvian.yama.webapi.service.sekolah;

import javax.inject.Inject;
import javax.ws.rs.BadRequestException;

import org.meruvian.yama.core.LogInformation;
import org.meruvian.yama.core.siswa.Siswa;
import org.meruvian.yama.core.siswa.SiswaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class RestSiswaService implements SiswaService {
	@Inject
	private SiswaRepository siswaRepository;
	
	@Override
	public Siswa getSiswaById(String id){
		return siswaRepository.findById(id);
	} 
	
	@Override
	public Page<Siswa> findSiswaByKeyword(String keyword, Pageable pageable) {
		return siswaRepository.findByNisOrNikOrNama(keyword, keyword, keyword, LogInformation.ACTIVE, pageable);
	}
	
	@Override
	@Transactional
	public void removeSiswa(String siswa) {
		getSiswaById(siswa).getLogInformation().setActiveFlag(LogInformation.INACTIVE);
	}
	
	@Override
	@Transactional
	public Siswa saveSiswa(Siswa siswa){
		int sts=0;
		Siswa s=siswaRepository.findByNama(siswa.getNis());
		if (s!=null){sts=1;}
		s=siswaRepository.findByNis(siswa.getNis());
		if (s!=null){sts=1;}
		s=siswaRepository.findByNik(siswa.getNis());
		if (s!=null){sts=1;}
		s=siswaRepository.findByNis(siswa.getNik());
		if (s!=null){sts=1;}
		s=siswaRepository.findByNik(siswa.getNik());
		if (s!=null){sts=1;}
		
		if(sts==0){return siswaRepository.save(siswa);}
		//return false;
		throw new BadRequestException("Nomor Induk Siswa Tidak Boleh Sama");
	}
	@Override
	@Transactional
	public Siswa updateSiswa(Siswa siswa){
		Siswa s=siswaRepository.findById(siswa.getId());
		s.setNama(siswa.getNama());
		s.setNik(siswa.getNik());
		s.setNis(siswa.getNis());
		s.setAgama(siswa.getAgama());
		s.setAlamat(siswa.getAlamat());
		s.setStaktif(siswa.getStaktif());
		s.setKelamin(siswa.getKelamin());
		s.setOrtu(siswa.getOrtu());
		s.setTelp(siswa.getTelp());
		s.setWarga(siswa.getWarga());
		return s;
	}
	
}
