package servicio.maestros;

import interfacedao.maestros.IDestiloDAO;

import java.util.List;

import modelo.maestros.Destilo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SDestilo")
public class SDestilo {
	
	@Autowired
	private IDestiloDAO destiloDAO;
	
	public List<Destilo> buscarTodos() {
		return destiloDAO.findAll();
	}

	public void guardar(Destilo noticia) {
		destiloDAO.save(noticia);
	}

	public void eliminarVarios(List<Destilo> eliminarLista) {
		destiloDAO.delete(eliminarLista);
	}

	public void eliminarUno(Long clave) {
		destiloDAO.delete(clave);
	}

	public Destilo buscar(long parseLong) {
		return destiloDAO.findOne(parseLong);
	}

	public Destilo buscarUltimo() {
		long id = destiloDAO.findMaxIdDestilo();
		if (id != 0)
			return destiloDAO.findOne(id);
		return null;
	}
}
