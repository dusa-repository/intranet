package servicio.maestros;

import interfacedao.maestros.IVentaDAO;

import java.util.List;

import modelo.maestros.Venta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service("SVenta")
public class SVenta {

	@Autowired
	private IVentaDAO ventaDAO;

	public List<Venta> buscarTodosOrdenados() {
		Sort sort = new Sort(Direction.ASC, "marca", "acumulada");
		return ventaDAO.findAll(sort);
	}

	public void guardar(Venta noticia) {
		ventaDAO.save(noticia);
	}

	public void eliminarVarios(List<Venta> eliminarLista) {
		ventaDAO.delete(eliminarLista);
	}

	public void eliminarUno(Long clave) {
		ventaDAO.delete(clave);
	}

	public List<Venta> buscarPorMarca(String value) {
		return ventaDAO.findByMarca(value);
	}

	public Venta buscar(Long clave) {
		return ventaDAO.findOne(clave);
	}
}
