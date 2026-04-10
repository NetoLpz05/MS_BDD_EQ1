package com.mycompany.tienda.entidades;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "ordenes")
public class Orden extends PanacheEntity {

    @NotNull
    @Column(nullable = false)
    public Long productoId;

    @Column(nullable = false)
    public String productoNombre;

    @Column(nullable = false)
    public Double precioCapturado;

    @NotNull
    @Min(value = 1, message = "La cantidad debe ser al menos 1")
    @Column(nullable = false)
    public Integer cantidad;

    @Column(nullable = false)
    public Double total;               
    // precioCapturado * cantidad

    @Column(nullable = false)
    public String estado;              
    // PENDIENTE | PROCESADA | CANCELADA

    @Column(nullable = false)
    public LocalDateTime fechaCreacion;

    public Orden() {}

    public static Orden crear(Long productoId, String nombre, Double precio, Integer cantidad) {
        Orden o = new Orden();
        o.productoId      = productoId;
        o.productoNombre  = nombre;
        o.precioCapturado = precio;
        o.cantidad        = cantidad;
        o.total           = precio * cantidad;
        o.estado          = "PENDIENTE";
        o.fechaCreacion   = LocalDateTime.now();
        return o;
    }

    public static List<Orden> encontrarPorId(Long productoId) {
        return list("productoId", productoId);
    }

    public static List<Orden> encontrarPorEstado(String estado) {
        return list("estado", estado);
    }
}
