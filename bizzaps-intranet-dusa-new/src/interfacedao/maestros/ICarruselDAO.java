package interfacedao.maestros;

import modelo.maestros.Carrusel;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ICarruselDAO extends JpaRepository<Carrusel, Long> {


}