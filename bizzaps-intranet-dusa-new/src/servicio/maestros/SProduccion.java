package servicio.maestros;

import interfacedao.maestros.IProduccion;

import java.util.List;

import modelo.maestros.Produccion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service("SProduccion")
public class SProduccion {

	@Autowired
	private IProduccion produccionDAO;

	public List<Produccion> buscarTodosOrdenados() {
		Sort sort = new Sort(Direction.ASC, "acumulada", "empresa",
				"tipo");
		return produccionDAO.findAll(sort);
	}

	public void guardar(Produccion noticia) {
		produccionDAO.save(noticia);
	}

	public void eliminarVarios(List<Produccion> eliminarLista) {
		produccionDAO.delete(eliminarLista);
	}

	public void eliminarUno(Long clave) {
		produccionDAO.delete(clave);
	}

	public List<Produccion> buscarPorEmpresa(String value) {
		return produccionDAO.findByEmpresa(value);
	}

	public Produccion buscar(Long clave) {
		return produccionDAO.findOne(clave);
	}
}
