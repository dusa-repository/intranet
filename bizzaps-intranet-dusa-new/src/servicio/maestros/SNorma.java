package servicio.maestros;

import interfacedao.maestros.INormaDAO;

import java.util.List;

import modelo.maestros.Norma;

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
