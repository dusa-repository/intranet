package controlador.portal;

import java.io.IOException;

import modelo.maestros.Empleado;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;

import controlador.maestros.CGenerico;

public class CDirectorio extends CGenerico {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Wire
	private Listbox ltbDirectorio;
	@Wire
	private Textbox txtNombre;
	@Wire
	private Textbox txtCelular;
	@Wire
	private Textbox txtFijo;
	@Wire
	private Textbox txtDireccion;

	@Override
	public void inicializar() throws IOException {
		ltbDirectorio.setModel(new ListModelList<Empleado>(servicioEmpleado
				.buscarPrimerosOrdenados()));

	}

	@Listen("onOK= #txtNombre,#txtCelular,#txtFijo,#txtDireccion")
	public void filtrar() {
		if (txtNombre.getValue().compareTo("") == 0
				&& txtCelular.getValue().compareTo("") == 0
				&& txtFijo.getValue().compareTo("") == 0
				&& txtDireccion.getValue().compareTo("") == 0)
			ltbDirectorio.setModel(new ListModelList<Empleado>(servicioEmpleado
					.buscarPrimerosOrdenados()));
		else {
			String celular = txtCelular.getValue();
			String nombre = txtNombre.getValue();
			String fijo = txtFijo.getValue();
			String direccion = txtDireccion.getValue();
			ltbDirectorio.setModel(new ListModelList<Empleado>(servicioEmpleado
					.buscarFiltradosOrdenados("%"+nombre+"%", "%"+celular+"%","%"+fijo+"%","%"+direccion+"%")));
		}
	}
}
