package interfacedao.portal;

import modelo.portal.Carrusel;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ICarruselDAO extends JpaRepository<Carrusel, Long> {


}