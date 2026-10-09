package org.centro.springcloud.msvc.msvc_cliente.domain.model;

import java.time.LocalDate;
import java.util.Objects;

public final class LicenciaConducir {

    private final String numero;
    private final LocalDate fechaVencimiento;

    public LicenciaConducir(String numero, LocalDate fechaVencimiento) {
        if (numero == null || numero.isBlank()) {
            throw new IllegalArgumentException("El número de licencia es obligatorio");
        }
        if (fechaVencimiento == null) {
            throw new IllegalArgumentException("La fecha de vencimiento de la licencia es obligatoria");
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
        if (!(object instanceof LicenciaConducir licencia)) {
            return false;
        }
        return numero.equals(licencia.numero)
                && fechaVencimiento.equals(licencia.fechaVencimiento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero, fechaVencimiento);
    }
}
