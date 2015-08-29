package controlador.maestros;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import modelo.maestros.Documento;

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

public class CDocumento extends CGenerico {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Wire
	private Div divDocumento;
	@Wire
	private Div botoneraDocumento;
	@Wire
	private Div catalogoDocumento;
	@Wire
	private Textbox txtNombre;
	@Wire
	private Textbox txtDescripcion;
	@Wire
	private Textbox txtTipo;
	@Wire
	private Groupbox gpxDatos;
	@Wire
	private Groupbox gpxRegistro;
	@Wire
	private Media media;
	@Wire
	private Label lblNombre;
	@Wire
	private org.zkoss.zul.Row row;
	@Wire
	private Label lblDescripcion;
	Long clave = null;
	Catalogo<Documento> catalogo;
	protected List<Documento> listaGeneral = new ArrayList<Documento>();
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
		gpxRegistro.setOpen(false);
		mostrarCatalogo();
		botonera = new Botonera() {

			@Override
			public void seleccionar() {
				if (validarSeleccion(catalogo)) {
					if (catalogo.obtenerSeleccionados().size() == 1) {
						mostrarBotones(botonera, false);
						abrirRegistro();
						Documento producto = catalogo
								.objetoSeleccionadoDelCatalogo();
						clave = producto.getIdDocumento();
						txtNombre.setValue(producto.getNombre());
						txtDescripcion.setValue(producto.getDescripcion());
						lblDescripcion.setValue(producto
								.getDescripcionDocumento());
						txtTipo.setValue(producto.getTipo());
						String formato = "";
						switch (producto.getTipo()) {
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
						media = new AMedia(producto.getDescripcionDocumento(),
								formato, producto.getTipo(),
								producto.getContenido());
						final A rm = new A("Remover");
						rm.addEventListener(
								Events.ON_CLICK,
								new org.zkoss.zk.ui.event.EventListener<Event>() {
									public void onEvent(Event event)
											throws Exception {
										lblDescripcion.setValue("");
										txtTipo.setValue("");
										rm.detach();
										media = null;
									}
								});
						row.appendChild(rm);
					} else
						Mensaje.mensajeAlerta(Mensaje.editarSoloUno);
				}
			}

			@Override
			public void salir() {
				cerrarVentana(divDocumento, cerrar, tabs);
			}

			@Override
			public void reporte() {
				// TODO Auto-generated method stub

			}

			@Override
			public void limpiar() {
				mostrarBotones(botonera, false);
				limpiarCampos();
				clave = null;
				if (row.getChildren().size() == 3) {
					A linea = (A) row.getChildren().get(2);
					Events.postEvent("onClick", linea, null);
				}
			}

			@Override
			public void guardar() {
				if (validar()) {
					if (clave == null)
						clave = (long) 0;
					Documento documento = new Documento(clave,
							txtNombre.getValue(), txtDescripcion.getValue(),
							lblDescripcion.getValue(), txtTipo.getValue(),
							media.getByteData(), fechaHora, horaAuditoria,
							nombreUsuarioSesion(), "", "");
					servicioDocumento.guardar(documento);
					msj.mensajeInformacion(Mensaje.guardado);
					limpiar();
					listaGeneral = servicioDocumento.buscarTodosOrdenados();
					catalogo.actualizarLista(listaGeneral, true);
				}
			}

			@Override
			public void eliminar() {
				if (gpxDatos.isOpen()) {
					/* Elimina Varios Registros */
					if (validarSeleccion(catalogo)) {
						final List<Documento> eliminarLista = catalogo
								.obtenerSeleccionados();
						Messagebox
								.show("¿Desea Eliminar los "
										+ eliminarLista.size() + " Registros?",
										"Alerta",
										Messagebox.OK | Messagebox.CANCEL,
										Messagebox.QUESTION,
										new org.zkoss.zk.ui.event.EventListener<Event>() {
											public void onEvent(Event evt)
													throws InterruptedException {
												if (evt.getName()
														.equals("onOK")) {
													servicioDocumento
															.eliminarVarios(eliminarLista);
													msj.mensajeInformacion(Mensaje.eliminado);
													listaGeneral = servicioDocumento
															.buscarTodosOrdenados();
													catalogo.actualizarLista(
															listaGeneral, true);
												}
											}
										});
					}
				} else {
					/* Elimina un solo registro */
					if (clave != null) {
						Messagebox
								.show(Mensaje.deseaEliminar,
										"Alerta",
										Messagebox.OK | Messagebox.CANCEL,
										Messagebox.QUESTION,
										new org.zkoss.zk.ui.event.EventListener<Event>() {
											public void onEvent(Event evt)
													throws InterruptedException {
												if (evt.getName()
														.equals("onOK")) {
													servicioDocumento
															.eliminarUno(clave);
													msj.mensajeInformacion(Mensaje.eliminado);
													limpiar();
													listaGeneral = servicioDocumento
															.buscarTodosOrdenados();
													catalogo.actualizarLista(
															listaGeneral, true);
												}
											}
										});
					} else
						Mensaje.mensajeAlerta(Mensaje.noSeleccionoRegistro);
				}
			}

			@Override
			public void buscar() {
				abrirCatalogo();
			}

			@Override
			public void ayuda() {
				// TODO Auto-generated method stub

			}

			@Override
			public void annadir() {
				abrirRegistro();
				mostrarBotones(botonera, false);
			}
		};
		botonera.getChildren().get(6).setVisible(false);
		botonera.getChildren().get(8).setVisible(false);
		botonera.getChildren().get(1).setVisible(false);
		botonera.getChildren().get(3).setVisible(false);
		botonera.getChildren().get(5).setVisible(false);
		botoneraDocumento.appendChild(botonera);

	}

	protected boolean validar() {
		if (!camposLLenos()) {
			msj.mensajeError(Mensaje.camposVacios);
			return false;
		} else
			return true;
	}

	private boolean camposLLenos() {
		if (txtNombre.getText().compareTo("") == 0
				|| txtDescripcion.getText().compareTo("") == 0
				|| lblDescripcion.getValue().compareTo("") == 0) {
			return false;
		} else
			return true;
	}

	public boolean camposEditando() {
		if (txtNombre.getText().compareTo("") != 0
				|| txtDescripcion.getText().compareTo("") != 0
				|| txtTipo.getText().compareTo("") != 0
				|| lblDescripcion.getValue().compareTo("") != 0) {
			return true;
		} else
			return false;
	}

	protected void limpiarCampos() {
		txtNombre.setValue("");
		txtDescripcion.setValue("");
		txtTipo.setValue("");
		lblDescripcion.setValue("");
		clave = null;
	}

	private void mostrarCatalogo() {
		listaGeneral = servicioDocumento.buscarTodosOrdenados();
		catalogo = new Catalogo<Documento>(catalogoDocumento, "Documentos",
				listaGeneral, false, false, false, "Nombre", "Descripcion",
				"Tipo Doc.") {

			@Override
			protected List<Documento> buscar(List<String> valores) {

				List<Documento> lista = new ArrayList<Documento>();

				for (Documento objeto : listaGeneral) {
					if (objeto.getNombre().toLowerCase()
							.contains(valores.get(0).toLowerCase())
							&& objeto.getDescripcion().toLowerCase()
									.contains(valores.get(1).toLowerCase())
							&& objeto.getTipo().toLowerCase()
									.contains(valores.get(2).toLowerCase())) {
						lista.add(objeto);
					}
				}
				return lista;
			}

			@Override
			protected String[] crearRegistros(Documento objeto) {
				String[] registros = new String[3];
				registros[0] = objeto.getNombre();
				registros[1] = objeto.getDescripcion();
				registros[2] = objeto.getTipo();
				return registros;
			}
		};
		catalogo.setParent(catalogoDocumento);
	}

	@Listen("onOpen = #gpxDatos")
	public void abrirCatalogo() {
		gpxDatos.setOpen(false);
		if (camposEditando()) {
			Messagebox.show(Mensaje.estaEditando, "Alerta", Messagebox.YES
					| Messagebox.NO, Messagebox.QUESTION,
					new org.zkoss.zk.ui.event.EventListener<Event>() {
						public void onEvent(Event evt)
								throws InterruptedException {
							if (evt.getName().equals("onYes")) {
								gpxDatos.setOpen(false);
								gpxRegistro.setOpen(true);
							} else {
								if (evt.getName().equals("onNo")) {
									gpxDatos.setOpen(true);
									gpxRegistro.setOpen(false);
									limpiarCampos();
									mostrarBotones(botonera, true);
								}
							}
						}
					});
		} else {
			gpxDatos.setOpen(true);
			gpxRegistro.setOpen(false);
			mostrarBotones(botonera, true);
		}
	}

	@Listen("onClick = #gpxRegistro")
	public void abrirRegistro() {
		gpxDatos.setOpen(false);
		gpxRegistro.setOpen(true);
		mostrarBotones(botonera, false);
	}

	@Listen("onUpload = #btnImportar")
	public void cargar(UploadEvent event) {
		media = event.getMedia();
		if (media != null && Validador.validarDocumento(media) && Validador.validarTamannoDocumento(media)) {
			lblDescripcion.setValue(media.getName());
			txtTipo.setValue(media.getContentType());
			final A rm = new A("Remover");
			rm.addEventListener(Events.ON_CLICK,
					new org.zkoss.zk.ui.event.EventListener<Event>() {
						public void onEvent(Event event) throws Exception {
							lblDescripcion.setValue("");
							txtTipo.setValue("");
							rm.detach();
							media = null;
						}
					});
			row.appendChild(rm);
		} else
			msj.mensajeError(Mensaje.archivoExcel);
	}

}
