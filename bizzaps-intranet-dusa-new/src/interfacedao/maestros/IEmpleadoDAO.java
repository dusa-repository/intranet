package interfacedao.maestros;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import modelo.maestros.Empleado;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IEmpleadoDAO extends JpaRepository<Empleado, Long> {

	List<Empleado> findByFicha(String value);

	List<Empleado> findByFichaGreaterThan(String i, Pageable topTen);

	@Query(value = "select * from empleado where datepart(month, fecha_nacimiento) = ?1 and estatus = 1 order by datepart(day, fecha_nacimiento) asc", nativeQuery = true)
	List<Empleado> findByMonth(int i);

	@Query("select e from Empleado e where ( isnull(e.nombre,'') like ?1 or isnull(e.apellido,'') like ?2)"
			+ " and isnull(e.direccion,'') like ?5 and isnull(e.telefonoCelular,'') like ?3 and isnull(e.telefonoFijo,'') like ?4 and e.estatus=1")
	Collection<? extends Empleado> findByNombreLikeOrApellidoLikeAndTelefonoCelularLikeAndTelefonoFijoLikeAndDireccionLike(
			String nombre, String apellido, String celullar, String fijo,
			String direccion, Sort sort);

	List<Empleado> findByEstatusTrueAndFichaGreaterThan(String string,
			Pageable topTen);

	@Query(value = "select * from empleado where datepart(month, fecha_ingreso) = ?3 and estatus = 1 "
			+ "and DATEDIFF(year, fecha_ingreso,?1)>=?2 "
			+ "order by datepart(year, fecha_ingreso), datepart(DAY, fecha_ingreso) asc", nativeQuery = true)
	Collection<? extends Empleado> findByFechaIngresoBetweenAndEstatusTrue(
			Date date, Integer anios, int fecha);

	@Query(value = "select * from empleado where fecha_nacimiento between ?1 and ?2 and estatus = 1 order by datepart(day, fecha_nacimiento) asc", nativeQuery = true)
	Collection<? extends Empleado> findByFechaNacimientoBetweenAndEstatusTrue(
			Date date, Date date2);
}
