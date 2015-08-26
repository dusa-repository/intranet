package interfacedao.maestros;

import java.util.List;

import modelo.maestros.Producto;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductoDAO extends JpaRepository<Producto, Long> {

	List<Producto> findByCantidadGreaterThan(int i, Sort sort);

	List<Producto> findByCantidadGreaterThan(int i, Pageable topTen);

}
