package controlador.portal;

import java.io.IOException;

import modelo.maestros.Produccion;

import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;

import controlador.maestros.CGenerico;

public class CProduccionPortal extends CGenerico {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Wire
	private Listbox listaProduccion;

	@Override
	public void inicializar() throws IOException {
		listaProduccion.setModel(new ListModelList<Produccion>(servicioProduccion
				.buscarTodosOrdenados()));
		
	}
}
