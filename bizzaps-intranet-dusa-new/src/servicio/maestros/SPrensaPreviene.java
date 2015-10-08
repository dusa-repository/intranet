package servicio.maestros;

import interfacedao.maestros.IPrensaPrevieneDAO;

import modelo.maestros.PrensaPreviene;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SPrensaPreviene")
public class SPrensaPreviene {

	@Autowired
	private IPrensaPrevieneDAO prensaDAO;
	
	public void guardar(PrensaPreviene noticia) {
		prensaDAO.save(noticia);
	}
	
	public PrensaPreviene buscar(long parseLong) {
		return prensaDAO.findOne(parseLong);
	}
}
