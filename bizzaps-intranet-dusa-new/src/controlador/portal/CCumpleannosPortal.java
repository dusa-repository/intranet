package controlador.portal;

import java.io.IOException;
import java.util.Date;

import modelo.maestros.Empleado;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;

import controlador.maestros.CGenerico;

public class CCumpleannosPortal extends CGenerico {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Wire
	private Listbox ltbCumpleanios;
	@Wire
	private Datebox dtbFecha;

	@Override
	public void inicializar() throws IOException {
		ltbCumpleanios.setModel(new ListModelList<Empleado>(servicioEmpleado
				.buscarTodosOrdenadosFecha(new Date())));

	}

	@Listen("onChange = #dtbFecha")
	public void buscar() {
		ltbCumpleanios.setModel(new ListModelList<Empleado>(servicioEmpleado
				.buscarTodosOrdenadosFecha(dtbFecha.getValue())));
	}

}
