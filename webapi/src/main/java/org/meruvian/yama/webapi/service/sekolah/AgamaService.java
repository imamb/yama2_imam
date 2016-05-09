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

import org.meruvian.yama.core.siswa.Agama;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Path("/api/agama")
@Produces(MediaType.APPLICATION_JSON)
public interface AgamaService {
	@GET
	@Path("/{id}")
	Agama getAgamaById(@PathParam("id") String id);
	
	@GET
	Page<Agama> findAgamaByKeyword(@QueryParam("q") @DefaultValue("") String keyword, 
			Pageable pageable);
	
	@DELETE
	@Path("/{id}")
	void removeAgama(@PathParam("id") String id);
	
	@POST
	Agama saveAgama(Agama agama);
	
	@PUT
	@Path("/{id}")
	Agama updateAgama(Agama agama);
}
