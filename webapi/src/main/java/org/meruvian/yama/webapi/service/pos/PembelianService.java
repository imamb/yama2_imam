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

import org.meruvian.yama.core.pos.Pembelian;
import org.meruvian.yama.core.pos.Produk;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Path("/api/pembelian")
@Produces(MediaType.APPLICATION_JSON)
public interface PembelianService {
	@GET
	@Path("/{id}")
	Pembelian getPembelianById(@PathParam("id") @DefaultValue("") String id);
	
	@GET
	Page<Pembelian> findPembelianByNomor(@QueryParam("q") @DefaultValue("") String nomor,Pageable pageable);
	
	@DELETE
	@Path("/{id}")
	void removePembelian(@PathParam("id") String id);
	
	@POST
	Pembelian savePembelian(Pembelian pembelian);
	
	@PUT
	@Path("/{id}")
	Pembelian updatePembelian(Pembelian pembelian);
	/*
	@PUT
	@Path("/{id}")
	Produk updateProduk(Produk produk);
	*/
	@GET
	@Path("/{id}/produks")
	Page<Produk> findProdukByPembelian(@PathParam("id") String id, Pageable pageable);
	
	@PUT
	@Path("/{id}/produks/{produkId}")
	boolean addProdukToPembelian(@PathParam("id") String id, @PathParam("produkId") String produkId);
	
	@DELETE
	@Path("/{id}/produks/{produkId}")
	boolean removeProdukFromPembelian(@PathParam("id") String id, @PathParam("produkId") String produkId);
	
	@DELETE
	@Path("/{id}/produks")
	boolean removeAllProdukFromPembelian(@PathParam("id") String id);
}
