package servicio.maestros;

import java.util.Collection;
import java.util.List;

import interfacedao.maestros.IProductoDAO;
import modelo.maestros.Producto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;


@Service("SProducto")
public class SProducto {

	@Autowired
	private IProductoDAO productoDAO;

	public void guardar(Producto producto) {
		productoDAO.save(producto);
	}

	public List<Producto> buscarTodosOrdenados() {
		Sort sort = new Sort(Direction.ASC, "nombre", "descripcion");
		return productoDAO.findAll(sort);
	}

	public void eliminarVarios(List<Producto> eliminarLista) {
		productoDAO.delete(eliminarLista);
	}

	public void eliminarUno(Long clave) {
		productoDAO.delete(clave);
	}

	public List<Producto> buscarTodosCantidadPositiva() {
		Sort sort = new Sort(Direction.ASC, "nombre", "descripcion");
		return productoDAO.findByCantidadGreaterThan(0, sort);
	}
	
	public List<Producto> buscarTodosCantidadPositivaPortal() {
		Sort sort = new Sort(Direction.ASC, "nombre", "descripcion");
		Pageable topTen = new PageRequest(0, 3, sort);
		return productoDAO.findByCantidadGreaterThan(0, topTen);
	}
}
