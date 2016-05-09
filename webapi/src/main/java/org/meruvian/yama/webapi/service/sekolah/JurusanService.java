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

import org.meruvian.yama.core.sekolah.Jurusan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Path("/api/jurusan")
@Produces(MediaType.APPLICATION_JSON)
public interface JurusanService {
	@GET
	@Path("/{id}")
	Jurusan getJurusanById(@PathParam("id") String id);
	
	@GET
	Page<Jurusan> findJurusanByKeyword(@QueryParam("q") @DefaultValue("") String keyword, 
			Pageable pageable);
	
	@DELETE
	@Path("/{id}")
	void removeJurusan(@PathParam("id") String id);
	
	@POST
	Jurusan saveJurusan(Jurusan jurusan);
	
	@PUT
	@Path("/{id}")
	Jurusan updateJurusan(Jurusan jurusan);
}
