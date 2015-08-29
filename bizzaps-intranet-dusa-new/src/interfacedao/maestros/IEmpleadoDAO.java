package interfacedao.maestros;

import java.util.Collection;
import java.util.List;

import modelo.maestros.Empleado;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IEmpleadoDAO extends JpaRepository<Empleado, Long> {

	List<Empleado> findByFicha(String value);

	List<Empleado> findByFichaGreaterThan(String i, Pageable topTen);

	@Query(value = "select * from empleado where datepart(month, fecha_nacimiento) = ?1 order by datepart(day, fecha_nacimiento) asc", nativeQuery = true)
	List<Empleado> findByMonth(int i);

	@Query("select e from Empleado e where (e.nombre like ?1 or e.apellido like ?2)"
			+ " and e.direccion like ?5 and e.telefonoCelular like ?3 and e.telefonoFijo like ?4")
	Collection<? extends Empleado> findByNombreLikeOrApellidoLikeAndTelefonoCelularLikeAndTelefonoFijoLikeAndDireccionLike(
			String nombre, String apellido, String celullar, String fijo,
			String direccion, Sort sort);

}