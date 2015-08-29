package controlador.portal;

import java.io.IOException;

import modelo.maestros.Venta;

import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;

import controlador.maestros.CGenerico;

public class CVentaPortal extends CGenerico {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Wire
	private Listbox listaVentas;

	@Override
	public void inicializar() throws IOException {
		listaVentas.setModel(new ListModelList<Venta>(servicioVenta
				.buscarTodosOrdenados()));

	}
}
