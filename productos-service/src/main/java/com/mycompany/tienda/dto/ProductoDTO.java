package com.mycompany.tienda.dto;

/**
 * DTO usado por el REST Client para deserializar la respuesta
 * de GET /productos/{id} dentro del mismo servidor.
 */
public class ProductoDTO {
    public Long   id;
    public String nombre;
    public Double precio;
    public String descripcion;

    public ProductoDTO() {
    }
}