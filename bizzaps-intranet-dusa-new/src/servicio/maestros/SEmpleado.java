package servicio.maestros;

import interfacedao.maestros.IEmpleadoDAO;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import modelo.maestros.Empleado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service("SEmpleado")
public class SEmpleado {

	@Autowired
	private IEmpleadoDAO empleadoDAO;
	private DateFormat formato = new SimpleDateFormat("MM-yyyy");
	private DateFormat formatoLargo = new SimpleDateFormat("dd-MM-yyyy");

	public List<Empleado> buscarTodosOrdenados() {
		Sort sort = new Sort(Direction.ASC, "ficha", "nombre", "apellido");
		return empleadoDAO.findAll(sort);
	}

	public void guardar(Empleado noticia) {
		empleadoDAO.save(noticia);
	}

	public void eliminarVarios(List<Empleado> eliminarLista) {
		empleadoDAO.delete(eliminarLista);
	}

	public void eliminarUno(Long clave) {
		empleadoDAO.delete(clave);
	}

	public List<Empleado> buscarPorFicha(String value) {
		return empleadoDAO.findByFicha(value);
	}

	public Empleado buscar(Long clave) {
		return empleadoDAO.findOne(clave);
	}

	public List<Empleado> buscarPrimerosOrdenados() {
		Sort sort = new Sort(Direction.ASC, "nombre", "apellido", "ficha");
		Pageable topTen = new PageRequest(0, 10, sort);
		return empleadoDAO.findByEstatusTrueAndFichaGreaterThan("0", topTen);
	}

	public Collection<? extends Empleado> buscarTodosOrdenadosFecha(Date date) {
		Calendar calendario = Calendar.getInstance();
		calendario.setTime(date);
		int fecha = calendario.get(Calendar.MONTH);
		return empleadoDAO.findByMonth(fecha + 1);
	}

	public Collection<? extends Empleado> buscarFiltradosOrdenados(
			String nombre, String celular, String fijo, String direccion) {
		Sort sort = new Sort(Direction.ASC, "nombre", "apellido", "ficha");
		return empleadoDAO
				.findByNombreLikeOrApellidoLikeAndTelefonoCelularLikeAndTelefonoFijoLikeAndDireccionLike(
						nombre, nombre, celular, fijo, direccion, sort);
	}

	public Collection<? extends Empleado> buscarAniversarios(Date date,
			Integer anios) {
		Calendar calendario = Calendar.getInstance();
		calendario.setTime(date);
		int fecha = calendario.get(Calendar.MONTH);
		String formateada = formato.format(date);
		try {
			date = formatoLargo.parse("01-" + formateada);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		calendario.setTime(date);
		calendario.set(Calendar.MONTH, calendario.get(Calendar.MONTH) + 1);
		calendario.set(Calendar.DAY_OF_YEAR,
				calendario.get(Calendar.DAY_OF_YEAR) - 1);
		date = calendario.getTime();
		return empleadoDAO.findByFechaIngresoBetweenAndEstatusTrue(date, anios,
				fecha + 1);
	}

	public Collection<? extends Empleado> buscarCumpleannos(Date date,
			Date date2) {
		return empleadoDAO.findByFechaNacimientoBetweenAndEstatusTrue(date,
				date2);
	}
}
