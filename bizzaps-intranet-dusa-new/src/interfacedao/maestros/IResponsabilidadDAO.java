package interfacedao.maestros;

import modelo.maestros.Responsabilidad;
import modelo.portal.Norma;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IResponsabilidadDAO extends JpaRepository<Responsabilidad, Long> {

}
