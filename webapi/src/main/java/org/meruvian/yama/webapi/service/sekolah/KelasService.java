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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Path("/api/kelas")
@Produces(MediaType.APPLICATION_JSON)
public interface KelasService {
	@GET
	@Path("/{id}")
	Kelas getKelasById(@PathParam("id") String id);
	
	@GET
	Page<Kelas> findKelasByKeyword(@QueryParam("q") @DefaultValue("") String keyword, 
			Pageable pageable);
	
	@DELETE
	@Path("/{id}")
	void removeKelas(@PathParam("id") String id);
	
	@POST
	Kelas saveKelas(Kelas kelas);
	
	@PUT
	@Path("/{id}")
	Kelas updateKelas(Kelas kelas);
}
