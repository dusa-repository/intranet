package interfacedao.maestros;

import modelo.maestros.Enlace;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IEnlaceDAO extends JpaRepository<Enlace, Long> {

}
