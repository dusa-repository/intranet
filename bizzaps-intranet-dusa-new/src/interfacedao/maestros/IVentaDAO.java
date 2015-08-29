package interfacedao.maestros;

import java.util.List;

import modelo.maestros.Venta;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IVentaDAO extends JpaRepository<Venta, Long> {

	List<Venta> findByMarca(String value);

}
