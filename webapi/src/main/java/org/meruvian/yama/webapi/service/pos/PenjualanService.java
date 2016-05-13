package org.meruvian.yama.webapi.service.pos;

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

import org.meruvian.yama.core.pos.Penjualan;
import org.meruvian.yama.core.pos.Produk;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Path("/api/penjualan")
@Produces(MediaType.APPLICATION_JSON)
public interface PenjualanService {
	@GET
	@Path("/{id}")
	Penjualan getPenjualanById(@PathParam("id") @DefaultValue("") String id);
	
	@GET
	Page<Penjualan> findPenjualanByNomor(@QueryParam("q") @DefaultValue("") String nomor,Pageable pageable);
	
	@DELETE
	@Path("/{id}")
	void removePenjualan(@PathParam("id") String id);
	
	@POST
	Penjualan savePenjualan(Penjualan penjualan);
	
	@PUT
	@Path("/{id}")
	Penjualan updatePenjualan(Penjualan penjualan);
	
	@GET
	@Path("/{id}/produks")
	Page<Produk> findProdukByPenjualan(@PathParam("id") String id, Pageable pageable);
	
	@PUT
	@Path("/{id}/produks/{produkId}")
	boolean addProdukToPenjualan(@PathParam("id") String id, @PathParam("produkId") String produkId);
	
	@DELETE
	@Path("/{id}/produks/{produkId}")
	boolean removeProdukFromPenjualan(@PathParam("id") String id, @PathParam("produkId") String produkId);
	
	@DELETE
	@Path("/{id}/produks")
	boolean removeAllProdukFromPenjualan(@PathParam("id") String id);
}
