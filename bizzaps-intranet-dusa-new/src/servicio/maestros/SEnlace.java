package servicio.maestros;

import interfacedao.maestros.IEnlaceDAO;

import java.util.List;

import modelo.maestros.Enlace;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service("SEnlace")
public class SEnlace {

	@Autowired
	private IEnlaceDAO enlaceDAO;

	public List<Enlace> buscarTodosOrdenados() {
		Sort sort = new Sort(Direction.ASC, "nombre", "descripcion");
		return enlaceDAO.findAll(sort);
	}

	public void guardar(Enlace noticia) {
		enlaceDAO.save(noticia);
	}

	public void eliminarVarios(List<Enlace> eliminarLista) {
		enlaceDAO.delete(eliminarLista);
	}

	public void eliminarUno(Long clave) {
		enlaceDAO.delete(clave);
	}
}
