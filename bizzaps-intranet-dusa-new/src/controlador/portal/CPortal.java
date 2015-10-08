package controlador.portal;

import java.io.IOException;

import modelo.maestros.Destilo;
import modelo.maestros.PrensaPreviene;

import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.ListModelList;

import controlador.maestros.CGenerico;

@Controller
public class CPortal extends CGenerico {

	@Wire
	private Combobox cmbFecha;
	
	@Override
	public void inicializar() throws IOException {
		Destilo des = servicioDestilo.buscarUltimo();
		if(des!=null)
		{
		cmbFecha.setModel(new ListModelList<Destilo>(servicioDestilo.buscarTodos()));
		cmbFecha.setValue(des.traerFecha());
		}

	}

	@Listen("onClick = #prensa")
	public void mostrarInfo2() {
		PrensaPreviene doc = servicioPrensaPreviene.buscar(1);
		if (doc != null) {
			Filedownload.save(doc.getContenidoPrensa(), doc.getTipoPrensa(),
					doc.getDescripcionPrensa());
		}
	}

	@Listen("onClick = #previene")
	public void mostrarInfo() {
		PrensaPreviene doc = servicioPrensaPreviene.buscar(1);
		if (doc != null) {
			Filedownload.save(doc.getContenidoPreviene(),
					doc.getTipoPreviene(), doc.getDescripcionPreviene());
		}
	}

	@Listen("onClick = #destilo")
	public void mostrarInfo3() {
		long id =0;
		if(cmbFecha.getText().compareTo("") != 0)
		{
		if(cmbFecha.getSelectedItem()
				.getContext()!=null)
		id = Long.valueOf(cmbFecha.getSelectedItem()
				.getContext());
		Destilo destilo = servicioDestilo.buscar(id);
		if(destilo!=null)
		{
		Clients.evalJavaScript("window.open('"
				+ damePath()
				+ "Reportero?valor=2&valor6="
				+ id
				+ "','','top=100,left=200,height=600,width=800,scrollbars=1,resizable=1')");
		}
		}
		
	}
	
	public byte[] reporteResumen(String par6) {
	Destilo destilo = getServicioDestilo().buscar(Long.parseLong(par6));
	return destilo.getContenido();
	}

	@Listen("onOpen = #cmbFecha")
	public void llenarCombo() {
		
		Destilo des = servicioDestilo.buscarUltimo();
		cmbFecha.setModel(new ListModelList<Destilo>(servicioDestilo.buscarTodos()));
	}
}
