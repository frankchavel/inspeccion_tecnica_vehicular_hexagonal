package org.centro.msvc_vehiculo.domain.model;

import java.time.LocalDate;
import java.util.Objects;

public final class Soat {

    private final String numero;
    private final LocalDate fechaVencimiento;

    public Soat(String numero, LocalDate fechaVencimiento) {
        if (numero == null || numero.isBlank()) {
            throw new IllegalArgumentException("El número de SOAT es obligatorio");
        }
        if (fechaVencimiento == null) {
            throw new IllegalArgumentException("La fecha de vencimiento del SOAT es obligatoria");
        }
        this.numero = numero.trim();
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getNumero() {
        return numero;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public boolean estaVigente() {
        return !fechaVencimiento.isBefore(LocalDate.now());
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Soat otro)) {
            return false;
        }
        return numero.equals(otro.numero) && fechaVencimiento.equals(otro.fechaVencimiento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero, fechaVencimiento);
    }
}
