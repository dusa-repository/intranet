package servicio.portal;

import java.util.List;

import interfacedao.portal.INormaDAO;

import modelo.portal.Norma;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SNorma")
public class SNorma {

	@Autowired
	private INormaDAO normaDAO;
	
	public void guardar(Norma norma) {
		normaDAO.save(norma);
	}

	public List<Norma> buscarTodos() {
		return normaDAO.findAll();
	}

	public Norma buscar(long i) {
		return normaDAO.findOne(i);
	}
}
