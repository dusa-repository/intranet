package servicio.maestros;

import interfacedao.maestros.IDocumentoDAO;

import java.util.List;

import modelo.maestros.Documento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service("SDocumento")
public class SDocumento {

	@Autowired
	private IDocumentoDAO documentoDAO;

	public List<Documento> buscarTodosOrdenados() {
		Sort sort = new Sort(Direction.ASC, "nombre", "descripcion");
		return documentoDAO.findAll(sort);
	}

	public void guardar(Documento noticia) {
		documentoDAO.save(noticia);
	}

	public void eliminarVarios(List<Documento> eliminarLista) {
		documentoDAO.delete(eliminarLista);
	}

	public void eliminarUno(Long clave) {
		documentoDAO.delete(clave);
	}

	public Documento buscar(long parseLong) {
		return documentoDAO.findOne(parseLong);
	}
}
