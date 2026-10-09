package org.centro.msvc_vehiculo.infrastructure.adapters;

import org.centro.msvc_vehiculo.domain.model.Soat;
import org.centro.msvc_vehiculo.domain.model.TituloPropiedad;
import org.centro.msvc_vehiculo.domain.model.Vehiculo;
import org.centro.msvc_vehiculo.domain.port.out.VehiculoRepositoryPort;
import org.centro.msvc_vehiculo.infrastructure.entities.VehiculoEntity;
import org.centro.msvc_vehiculo.infrastructure.repositories.VehiculoJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class VehiculoJpaAdapter implements VehiculoRepositoryPort {

    private final VehiculoJpaRepository jpaRepository;

    public VehiculoJpaAdapter(VehiculoJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    private VehiculoEntity toEntity(Vehiculo vehiculo) {
        return new VehiculoEntity(
                vehiculo.getVehiculoId(),
                vehiculo.getPlaca(),
                vehiculo.getMarca(),
                vehiculo.getModelo(),
                vehiculo.getAnioFabricacion(),
                vehiculo.getSoat().getNumero(),
                vehiculo.getSoat().getFechaVencimiento(),
                vehiculo.getTituloPropiedad().getNumero(),
                vehiculo.getTituloPropiedad().getValido()
        );
    }

    private Vehiculo toDomain(VehiculoEntity entity) {
        return new Vehiculo(
                entity.getVehiculoId(),
                entity.getPlaca(),
                entity.getMarca(),
                entity.getModelo(),
                entity.getAnioFabricacion(),
                new Soat(entity.getNumeroSoat(), entity.getFechaVencimientoSoat()),
                new TituloPropiedad(entity.getNumeroTituloPropiedad(), entity.getTituloValido())
        );
    }

    @Override
    public Vehiculo guardar(Vehiculo vehiculo) {
        VehiculoEntity entity = toEntity(vehiculo);
        VehiculoEntity saved = jpaRepository.save(entity);
        return toDomain(saved);
    }

    @Override
    public Optional<Vehiculo> buscarPorId(Long id) {
        return jpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public List<Vehiculo> listar() {
        return jpaRepository.findAll().stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public void eliminar(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public boolean existePlaca(String placa) {
        return jpaRepository.existsByPlaca(placa);
    }
}
