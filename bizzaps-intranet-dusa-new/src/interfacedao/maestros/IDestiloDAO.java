package interfacedao.maestros;

import java.util.List;

import modelo.maestros.Destilo;
import modelo.maestros.Documento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IDestiloDAO extends JpaRepository<Destilo, Long> {

	@Query("select coalesce(max(d.idDestilo), '0') from Destilo d")
	long findMaxIdDestilo();


}
