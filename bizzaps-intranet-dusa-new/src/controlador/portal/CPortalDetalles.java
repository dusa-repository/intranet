package controlador.portal;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Include;

import controlador.maestros.CGenerico;

@Controller
public class CPortalDetalles  extends CGenerico {
	
	
	
	@Wire
	private Include contenido;
	
	
	@Override
	public void inicializar() throws IOException {
		// TODO Auto-generated method stub
		
	}

	public Include a()
	{
		return contenido;
		
	}

}
