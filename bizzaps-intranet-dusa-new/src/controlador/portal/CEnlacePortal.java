package controlador.portal;

import java.io.IOException;

import modelo.maestros.Enlace;

import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Button;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;

import controlador.maestros.CGenerico;

public class CEnlacePortal extends CGenerico {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Wire
	private Listbox listaEnlaces;

	@Override
	public void inicializar() throws IOException {
		listaEnlaces.setModel(new ListModelList<Enlace>(servicioEnlace
				.buscarTodosOrdenados()));
	}

	public void ventanaBoton(Button a) {
		Label label = (Label) a.getParent().getParent().getChildren().get(1)
				.getFirstChild();
		Clients.evalJavaScript("window.open('http://" + label.getValue()
				+ "','_blank')");
	}

}
