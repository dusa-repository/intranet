package servicio.maestros;

import interfacedao.maestros.INoticiaDAO;

import java.util.List;

import modelo.maestros.Noticia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service("SNoticia")
public class SNoticia {

	@Autowired
	private INoticiaDAO noticiaDAO;

	public List<Noticia> buscarTodosOrdenados() {
		Sort sort = new Sort(Direction.ASC, "fecha", "titulo");
		return noticiaDAO.findAll(sort);
	}

	public void guardar(Noticia noticia) {
		noticiaDAO.save(noticia);
	}

	public void eliminarVarios(List<Noticia> eliminarLista) {
		noticiaDAO.delete(eliminarLista);
	}

	public void eliminarUno(Long clave) {
		noticiaDAO.delete(clave);
	}

	public List<Noticia> buscarTodosPortal() {
		Sort sort = new Sort(Direction.ASC, "fecha", "titulo");
		Pageable topTen = new PageRequest(0, 3, sort);
		return noticiaDAO.findByIdNoticiaGreaterThan((long)0, topTen);
	}

	public Noticia buscar(Long valueOf) {
		return noticiaDAO.findOne(valueOf);
	}
}
