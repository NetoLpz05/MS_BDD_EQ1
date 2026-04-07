package com.mycompany.tienda.servicios;

import com.mycompany.tienda.dto.ProductoDTO;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "producto-service")
@Path("/productos")
@Produces(MediaType.APPLICATION_JSON)
public interface ProductoServiceClient {

    @GET
    @Path("/{id}")
    ProductoDTO encontrarPorId(@PathParam("id") Long id);
}