package interfacedao.maestros;

import java.util.List;

import modelo.maestros.Produccion;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IProduccion extends JpaRepository<Produccion, Long> {

	List<Produccion> findByEmpresa(String value);

}
