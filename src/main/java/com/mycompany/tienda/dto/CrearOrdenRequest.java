package com.mycompany.tienda.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

/** Cuerpo JSON que espera POST /ordenes. */
public class CrearOrdenRequest {

    @NotNull(message = "productoId es obligatorio")
    public Long productoId;

    @NotNull(message = "cantidad es obligatoria")
    @Min(value = 1, message = "La cantidad debe ser al menos 1")
    public Integer cantidad;

    public CrearOrdenRequest() {
    }
}