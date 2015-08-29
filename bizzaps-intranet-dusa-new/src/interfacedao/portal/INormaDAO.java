package interfacedao.portal;

import modelo.portal.Carrusel;
import modelo.portal.Norma;

import org.springframework.data.jpa.repository.JpaRepository;

public interface INormaDAO extends JpaRepository<Norma, Long> {

}
