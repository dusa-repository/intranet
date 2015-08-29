package controlador.portal;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Label;

import controlador.maestros.CGenerico;

@Controller
public class CNoticiaPortal extends CGenerico {

	@Wire
	private Label titulo;
	@Wire
	private Label noticia;

	@Override
	public void inicializar() throws IOException {
		noticia.setValue("nknjvdfv-------NOTICIA-----------hdfsoibh");
		titulo.setValue("nknjvdfv-------NOTICIA-----------hdfsoibh");
		
	}
}
