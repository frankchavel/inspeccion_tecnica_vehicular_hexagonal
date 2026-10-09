package org.centro.msvc_vehiculo.domain.model;

import java.util.Objects;

public final class TituloPropiedad {

    private final String numero;
    private final Boolean valido;

    public TituloPropiedad(String numero, Boolean valido) {
        if (numero == null || numero.isBlank()) {
            throw new IllegalArgumentException("El número de título de propiedad es obligatorio");
        }
        if (valido == null) {
            throw new IllegalArgumentException("La validez del título de propiedad es obligatoria");
        }
        this.numero = numero.trim();
        this.valido = valido;
    }

    public String getNumero() {
        return numero;
    }

    public Boolean getValido() {
        return valido;
    }

    public boolean esValido() {
        return valido;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof TituloPropiedad otro)) {
            return false;
        }
        return numero.equals(otro.numero) && valido.equals(otro.valido);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero, valido);
    }
}
