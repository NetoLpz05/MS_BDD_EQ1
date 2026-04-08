package com.mycompany.tienda.servicios;

import com.mycompany.tienda.entidades.Producto;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/productos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductoResource {

    @GET
    public List<Producto> listarTodos() {
        return Producto.listAll();
    }

    @GET
    @Path("/{id}")
    public Response obtenerPorId(@PathParam("id") Long id) {
        Producto p = Producto.findById(id);
        if (p == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Producto " + id + " no encontrado").build();
        }
        return Response.ok(p).build();
    }

    @POST
    @Transactional
    public Response crear(@Valid Producto producto) {
        producto.persist();
        return Response.status(Response.Status.CREATED).entity(producto).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response actualizar(@PathParam("id") Long id, @Valid Producto datos) {
        Producto p = Producto.findById(id);
        if (p == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Producto " + id + " no encontrado").build();
        }
        
        p.nombre      = datos.nombre;
        p.precio      = datos.precio;
        p.descripcion = datos.descripcion;
        
        return Response.ok(p).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response eliminar(@PathParam("id") Long id) {
        boolean eliminado = Producto.deleteById(id);
        if (!eliminado) {
            return Response.status(Response.Status.NOT_FOUND).entity("Producto " + id + " no encontrado").build();
        }
        return Response.noContent().build();
    }
}