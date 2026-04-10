package com.mycompany.tienda.servicios;

import com.mycompany.tienda.dto.ProductoDTO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/productos")
@RegisterRestClient(configKey = "producto-service")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface ProductoServiceClient {

    @GET
    @Path("/{id}")
    ProductoDTO encontrarPorId(@PathParam("id") Long id);
}