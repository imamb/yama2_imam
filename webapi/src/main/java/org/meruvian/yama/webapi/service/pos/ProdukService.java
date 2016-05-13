package org.meruvian.yama.webapi.service.pos;

//import java.util.List;

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

import org.meruvian.yama.core.pos.Produk;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Path("/api/produk")
@Produces(MediaType.APPLICATION_JSON)
public interface ProdukService {
	@GET
	@Path("/{id}")
	Produk getProdukById(@PathParam("id") @DefaultValue("") String id);
	
	@GET
	Page<Produk> findProdukByNama(@QueryParam("q") @DefaultValue("") String nama,Pageable pageable);
	
	@POST
	Produk saveProduk(Produk produk);
	
	@PUT
	@Path("/{id}")
	Produk updateProduk(Produk produk);
	
	@DELETE
	@Path("/{id}")
	void removeProduk(@PathParam("id") String id);
}
