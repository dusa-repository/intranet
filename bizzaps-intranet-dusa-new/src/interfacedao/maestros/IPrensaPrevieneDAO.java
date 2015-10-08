package interfacedao.maestros;

import modelo.maestros.Documento;
import modelo.maestros.PrensaPreviene;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IPrensaPrevieneDAO extends JpaRepository<PrensaPreviene, Long> {

}
