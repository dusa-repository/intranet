package servicio.maestros;

import java.util.List;

import interfacedao.maestros.IResponsabilidadDAO;

import modelo.maestros.Responsabilidad;
import modelo.portal.Norma;

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
