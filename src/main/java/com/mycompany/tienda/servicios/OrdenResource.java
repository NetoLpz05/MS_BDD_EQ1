package com.mycompany.tienda.servicios;

import com.mycompany.tienda.dto.CrearOrdenRequest;
import com.mycompany.tienda.dto.ProductoDTO;
import com.mycompany.tienda.entidades.Orden;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import java.util.List;

@Path("/ordenes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrdenResource {

    @Inject
    @RestClient
    ProductoServiceClient productoClient;

    @GET
    public List<Orden> listarTodas() {
        return Orden.listAll();
    }

    @GET
    @Path("/{id}")
    public Response obtenerPorId(@PathParam("id") Long id) {
        Orden o = Orden.findById(id);
        if (o == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Orden " + id + " no encontrada").build();
        }
        return Response.ok(o).build();
    }

    @GET
    @Path("/producto/{productoId}")
    public List<Orden> porProducto(@PathParam("productoId") Long productoId) {
        return Orden.encontrarPorId(productoId);
    }

    @POST
    @Transactional
    public Response crear(@Valid CrearOrdenRequest request) {

        ProductoDTO producto;
        try {
            producto = productoClient.encontrarPorId(request.productoId);
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity("Producto " + request.productoId + " no encontrado").build();
        }

        Orden orden = Orden.crear(producto.id,producto.nombre,producto.precio,request.cantidad);
        orden.persist();

        return Response.status(Response.Status.CREATED).entity(orden).build();
    }

    @PUT
    @Path("/{id}/estado")
    @Transactional
    public Response actualizarEstado(@PathParam("id") Long id, @QueryParam("estado") String estado) {
        Orden o = Orden.findById(id);
        if (o == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Orden " + id + " no encontrada").build();
        }
        if (!List.of("PENDIENTE", "PROCESADA", "CANCELADA").contains(estado)) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Estado inválido. Valores aceptados: PENDIENTE, PROCESADA, CANCELADA").build();
        }
        o.estado = estado;
        return Response.ok(o).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response eliminar(@PathParam("id") Long id) {
        boolean eliminado = Orden.deleteById(id);
        if (!eliminado) {
            return Response.status(Response.Status.NOT_FOUND).entity("Orden " + id + " no encontrada").build();
        }
        return Response.noContent().build();
    }
}