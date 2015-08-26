package controlador.portal;

import java.io.IOException;

import modelo.maestros.Producto;

import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;

import controlador.maestros.CGenerico;

public class CProductosPortal extends CGenerico {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Wire
	private Listbox ltbProductosPortal;

	@Override
	public void inicializar() throws IOException {
		ltbProductosPortal.setModel(new ListModelList<Producto>(
				servicioProducto.buscarTodosCantidadPositivaPortal()));
	}

}
