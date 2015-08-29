package servicio.maestros;

import interfacedao.maestros.ICarruselDAO;

import java.util.List;

import modelo.maestros.Carrusel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SCarrusel")
public class SCarrusel {

	@Autowired
	private ICarruselDAO carruselDAO;

	public void guardar(Carrusel carrusel) {
		carruselDAO.save(carrusel);
	}

	public List<Carrusel> buscar() {
		return carruselDAO.findAll();
	}

	public void eliminarTodas() {
		carruselDAO.deleteAll();
	}

	public void eliminar(int i) {
		carruselDAO.delete((long) i);
	}

	public boolean existe(int i) {
		return carruselDAO.exists((long) i);
	}

}