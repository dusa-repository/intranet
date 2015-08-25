package interfacedao.portal;

import java.util.List;
import modelo.portal.Carrusel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ICarruselDAO extends JpaRepository<Carrusel, Long> {


}