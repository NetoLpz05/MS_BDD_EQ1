package com.mycompany.tienda.entidades;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


@Entity
@Table(name = "productos")
public class Producto extends PanacheEntity {

    @NotBlank(message = "El nombre no puede estar vacío")
    @Column(nullable = false)
    public String nombre;

    @NotNull(message = "El precio es obligatorio")
    @Min(value = 0, message = "El precio no puede ser negativo")
    @Column(nullable = false)
    public Double precio;

    @Column
    public String descripcion;

    public Producto() {
    }

    public Producto(String nombre, Double precio, String descripcion) {
        this.nombre      = nombre;
        this.precio      = precio;
        this.descripcion = descripcion;
    }
}
