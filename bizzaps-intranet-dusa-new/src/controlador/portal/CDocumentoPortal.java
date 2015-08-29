package controlador.portal;

import java.io.IOException;

import modelo.maestros.Documento;

import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;

import controlador.maestros.CGenerico;

public class CDocumentoPortal extends CGenerico {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Wire
	private Listbox listaDocumentos;

	@Override
	public void inicializar() throws IOException {
		listaDocumentos.setModel(new ListModelList<Documento>(servicioDocumento
				.buscarTodosOrdenados()));
	}

	public void ventana(Button a) {
		Label label = (Label) a.getParent().getParent().getChildren().get(4)
				.getFirstChild();
		Documento doc = servicioDocumento.buscar(Long.parseLong(label
				.getValue()));
		Filedownload.save(doc.getContenido(), doc.getTipo(),
				doc.getDescripcionDocumento());
	}

}
