package controlador.portal;

import java.io.IOException;

import modelo.maestros.Destilo;
import modelo.maestros.Noticia;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.A;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Window;

import controlador.maestros.CGenerico;

public class CTodasNoticiasPortal extends CGenerico {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Wire
	private Listbox ltbNoticias;

	@Override
	public void inicializar() throws IOException {
		ltbNoticias.setModel(new ListModelList<Noticia>(servicioNoticia
				.buscarTodosOrdenados()));
	}

	public void ventanaBoton(A a) {
		Label label = (Label) a.getParent().getParent().getParent().getParent()
				.getParent().getParent().getParent().getChildren().get(1)
				.getFirstChild();
		Clients.evalJavaScript("window.open('zk/verNoticia.zul?type="
				+ label.getValue() + "','_blank')");
	}
	
	public void ventanaImagen(Image a) {
	Label label = (Label) a.getParent().getParent().getParent().getParent()
				.getParent().getParent().getParent().getChildren().get(1)
				.getFirstChild();
		
		if(label!=null)
		{
			Image image = new Image();
			image.setContent(servicioNoticia.buscar(Long.valueOf(label.getValue())).traerImagen());		
			image.setWidth("100%");
			image.setHeight("100%");
			Window window = (Window) Executions.createComponents(
					"/vistas/imagen.zul", null, null);
			window.appendChild(image);
			window.doModal();
		}

	}


}
