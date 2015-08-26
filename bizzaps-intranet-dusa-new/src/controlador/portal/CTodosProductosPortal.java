package controlador.portal;

import java.io.IOException;

import modelo.maestros.Producto;

import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;

import controlador.maestros.CGenerico;

public class CTodosProductosPortal extends CGenerico {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Wire
	private Listbox ltbProductos;

	@Override
	public void inicializar() throws IOException {
		ltbProductos.setModel(new ListModelList<Producto>(servicioProducto
				.buscarTodosCantidadPositiva()));
	}

}
