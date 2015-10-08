package controlador.maestros;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import modelo.maestros.PrensaPreviene;

import org.zkoss.util.media.AMedia;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.A;
import org.zkoss.zul.Div;
import org.zkoss.zul.Groupbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Textbox;

import componente.Botonera;
import componente.Catalogo;
import componente.Mensaje;
import componente.Validador;

public class CPrensaPreviene extends CGenerico {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Wire
	private Div divPrensa;
	@Wire
	private Div botoneraPrensa;
	@Wire
	private Textbox txtPrensa;
	@Wire
	private Media mediaPrensa;
	@Wire
	private Label lblPrensa;
	@Wire
	private Label lblDescripcionPrensa;
	@Wire
	private Textbox txtPreviene;
	@Wire
	private Media mediaPreviene;
	@Wire
	private Label lblPreviene;
	@Wire
	private Label lblDescripcionPreviene;
	Botonera botonera;

	@Override
	public void inicializar() throws IOException {
		HashMap<String, Object> map = (HashMap<String, Object>) Sessions
				.getCurrent().getAttribute("mapaGeneral");
		if (map != null) {
			if (map.get("tabsGenerales") != null) {
				tabs = (List<Tab>) map.get("tabsGenerales");
				cerrar = (String) map.get("titulo");
				map.clear();
				map = null;
			}
		}
		
       llenarCampos();
		botonera = new Botonera() {

			@Override
			public void seleccionar() {

			}

			@Override
			public void salir() {
				cerrarVentana(divPrensa, cerrar, tabs);
			}

			@Override
			public void reporte() {
				// TODO Auto-generated method stub

			}

			@Override
			public void limpiar() {
				llenarCampos();
			}

			@Override
			public void guardar() {
				if (validar()) {
					PrensaPreviene p = new PrensaPreviene(1,lblDescripcionPrensa.getValue(), txtPrensa.getValue(),
							mediaPrensa.getByteData(),lblDescripcionPreviene.getValue(), txtPreviene.getValue(),
							mediaPreviene.getByteData(), fechaHora, horaAuditoria,
							nombreUsuarioSesion());
					servicioPrensaPreviene.guardar(p);
					msj.mensajeInformacion(Mensaje.guardado);
					limpiar();
				}
			}

			@Override
			public void eliminar() {
			
			}

			@Override
			public void buscar() {

			}

			@Override
			public void ayuda() {
				// TODO Auto-generated method stub

			}

			@Override
			public void annadir() {
			}
		};
		botonera.getChildren().get(0).setVisible(false);
		botonera.getChildren().get(1).setVisible(false);
		botonera.getChildren().get(2).setVisible(false);
		botonera.getChildren().get(4).setVisible(false);
		botonera.getChildren().get(6).setVisible(false);
		botonera.getChildren().get(8).setVisible(false);
		botoneraPrensa.appendChild(botonera);

	}


	protected boolean validar() {
		if (!camposLLenos()) {
			msj.mensajeError(Mensaje.camposVacios);
			return false;
		} else
			return true;
	}

	private boolean camposLLenos() {
		if (lblDescripcionPrensa.getValue().compareTo("") == 0
				|| lblDescripcionPreviene.getValue().compareTo("") == 0) {
			return false;
		} else
			return true;
	}


	@Listen("onUpload = #btnPrensa")
	public void cargar(UploadEvent event) {
		mediaPrensa = event.getMedia();
		if (mediaPrensa != null && Validador.validarDocumento(mediaPrensa) && Validador.validarTamannoDocumento(mediaPrensa)) {
			lblDescripcionPrensa.setValue(mediaPrensa.getName());
			txtPrensa.setValue(mediaPrensa.getContentType());
		} else
			msj.mensajeError(Mensaje.archivoExcel);
	}
	@Listen("onUpload = #btnPreviene")
	public void cargarPreviene(UploadEvent event) {
		mediaPreviene = event.getMedia();
		if (mediaPreviene != null && Validador.validarDocumento(mediaPreviene) && Validador.validarTamannoDocumento(mediaPreviene)) {
			lblDescripcionPreviene.setValue(mediaPreviene.getName());
			txtPreviene.setValue(mediaPreviene.getContentType());
		} else
			msj.mensajeError(Mensaje.archivoExcel);
	}


	private void llenarCampos() {
		PrensaPreviene prensa = servicioPrensaPreviene.buscar(1);
		if (prensa!=null)
		{
			lblDescripcionPrensa.setValue(prensa
					.getDescripcionPrensa());
			txtPrensa.setValue(prensa.getTipoPrensa());
			String formato = "";
			switch (prensa.getTipoPrensa()) {
			case "application/pdf":
				formato = "pdf";
			case "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet":
				formato = "xlsx";
			case "application/vnd.openxmlformats-officedocument.wordprocessingml.document":
				formato = "docx";
			case "application/vnd.ms-excel":
				formato = "xls";
			case "application/msword":
				formato = "doc";
			default:
				formato = "N/A";
			}
			mediaPrensa = new AMedia(prensa.getDescripcionPrensa(),
					formato, prensa.getTipoPrensa(),
					prensa.getContenidoPrensa());
			
			lblDescripcionPreviene.setValue(prensa
					.getDescripcionPreviene());
			txtPreviene.setValue(prensa.getTipoPrensa());
			String formato2 = "";
			switch (prensa.getTipoPreviene()) {
			case "application/pdf":
				formato2 = "pdf";
			case "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet":
				formato2 = "xlsx";
			case "application/vnd.openxmlformats-officedocument.wordprocessingml.document":
				formato2 = "docx";
			case "application/vnd.ms-excel":
				formato2 = "xls";
			case "application/msword":
				formato2 = "doc";
			default:
				formato = "N/A";
			}
			mediaPreviene = new AMedia(prensa.getDescripcionPreviene(),
					formato2, prensa.getTipoPreviene(),
					prensa.getContenidoPreviene());

		}
		
	}
	
}
