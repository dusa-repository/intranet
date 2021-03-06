package servicio.maestros;

import interfacedao.maestros.IResponsabilidadDAO;

import java.util.List;

import modelo.maestros.Responsabilidad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SResponsabilidad")
public class SResponsabilidad {
	
	@Autowired
	private IResponsabilidadDAO responsabilidadDAO;

	public void guardar(Responsabilidad responsabilidad) {
		responsabilidadDAO.save(responsabilidad);
	}

	public List<Responsabilidad> buscarTodos() {
		return responsabilidadDAO.findAll();
	}

	public Responsabilidad buscar(long i) {
		return responsabilidadDAO.findOne(i);
	}
}
