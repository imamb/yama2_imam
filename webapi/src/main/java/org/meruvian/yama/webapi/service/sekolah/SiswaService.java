package org.meruvian.yama.webapi.service.sekolah;

import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.meruvian.yama.core.sekolah.Kelas;
import org.meruvian.yama.core.siswa.Agama;
import org.meruvian.yama.core.siswa.Siswa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Path("/api/siswa")
@Produces(MediaType.APPLICATION_JSON)
public interface SiswaService {
	@GET
	@Path("/{id}")
	Siswa getSiswaById(@PathParam("id") String id);
	
	@GET
	Page<Siswa> findSiswaByKeyword(@QueryParam("q") @DefaultValue("") String keyword, 
			Pageable pageable);
	
	@DELETE
	@Path("/{id}")
	void removeSiswa(@PathParam("id") String id);
	
	@POST
	Siswa saveSiswa(Siswa siswa);
	
	@PUT
	@Path("/{id}")
	Siswa updateSiswa(Siswa siswa);
	
	@GET
	@Path("/{id}/kelass")
	Page<Kelas> findKelasBySiswa(@QueryParam("q") @DefaultValue("") String kelas, 
			Pageable pageable);
	
	@GET
	@Path("/{id}/agamas")
	Page<Agama> findAgamaBySiswa(@QueryParam("q") @DefaultValue("") String agama, 
			Pageable pageable);
	
	@GET
	@Path("/{id}/kelas")
	Page<Kelas> findSiswaByKelas(@QueryParam("q") @DefaultValue("") String kelas, 
			Pageable pageable);
	
	@GET
	@Path("/{id}/agama")
	Page<Agama> findSiswaByAgama(@QueryParam("q") @DefaultValue("") String agama, 
			Pageable pageable);
}
