package controlador.maestros;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;

import modelo.maestros.Noticia;

import org.zkforge.ckez.CKeditor;
import org.zkoss.image.AImage;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Groupbox;
import org.zkoss.zul.Image;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Textbox;

import componente.Botonera;
import componente.Catalogo;
import componente.Mensaje;

public class CNoticia extends CGenerico {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Wire
	private Div divNoticia;
	@Wire
	private Div botoneraNoticia;
	@Wire
	private Div catalogoNoticia;
	@Wire
	private Textbox txtNombre;
	@Wire
	private CKeditor txtDescripcion;
	@Wire
	private Datebox dtbFecha;
	@Wire
	private Groupbox gpxDatos;
	@Wire
	private Groupbox gpxRegistro;
	@Wire
	private Image imagen;
	@Wire
	private Media media;
	Long clave = null;
	Catalogo<Noticia> catalogo;
	protected List<Noticia> listaGeneral = new ArrayList<Noticia>();
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
		try {
			imagen.setContent(new AImage(url));
		} catch (IOException e1) {
			e1.printStackTrace();
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
						Noticia producto = catalogo
								.objetoSeleccionadoDelCatalogo();
						clave = producto.getIdNoticia();
						txtNombre.setValue(producto.getTitulo());
						txtDescripcion.setValue(producto.getTexto());
						BufferedImage imag;
						if (producto.getImagen() != null) {
							try {
								imag = ImageIO.read(new ByteArrayInputStream(
										producto.getImagen()));
								imagen.setContent(imag);
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					} else
						Mensaje.mensajeAlerta(Mensaje.editarSoloUno);
				}
			}

			@Override
			public void salir() {
				cerrarVentana(divNoticia, cerrar, tabs);
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
			}

			@Override
			public void guardar() {
				if (validar()) {
					byte[] imagenUsuario = null;
					if (media instanceof org.zkoss.image.Image) {
						imagenUsuario = imagen.getContent().getByteData();

					} else {
						try {
							imagen.setContent(new AImage(url));
						} catch (IOException e) {
							e.printStackTrace();
						}
						imagenUsuario = imagen.getContent().getByteData();
					}
					if (clave == null)
						clave = (long) 0;
					Noticia noticia = new Noticia(clave, txtNombre.getValue(),
							imagenUsuario, txtDescripcion.getValue(),
							new Timestamp(dtbFecha.getValue().getTime()),
							fechaHora, horaAuditoria, nombreUsuarioSesion());
					servicioNoticia.guardar(noticia);
					msj.mensajeInformacion(Mensaje.guardado);
					limpiar();
					listaGeneral = servicioNoticia.buscarTodosOrdenados();
					catalogo.actualizarLista(listaGeneral, true);
				}
			}

			@Override
			public void eliminar() {
				if (gpxDatos.isOpen()) {
					/* Elimina Varios Registros */
					if (validarSeleccion(catalogo)) {
						final List<Noticia> eliminarLista = catalogo
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
													servicioNoticia
															.eliminarVarios(eliminarLista);
													msj.mensajeInformacion(Mensaje.eliminado);
													listaGeneral = servicioNoticia
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
													servicioNoticia
															.eliminarUno(clave);
													msj.mensajeInformacion(Mensaje.eliminado);
													limpiar();
													listaGeneral = servicioNoticia
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
		botoneraNoticia.appendChild(botonera);
	}

	protected boolean validar() {
		if (!camposLLenos()) {
			msj.mensajeError(Mensaje.camposVacios);
			return false;
		} else
			{
			if (txtDescripcion.getValue().length() > 4999) {
				msj.mensajeAlerta("Longitud maxima excedida de la noticia(5000 caracteres)");
				return false;
			}
			return true;
			}
	}

	private boolean camposLLenos() {
		if (txtNombre.getText().compareTo("") == 0
				|| txtDescripcion.getValue().compareTo("") == 0
				|| imagen.getContent() == null) {
			return false;
		} else
			return true;
	}

	public boolean camposEditando() {
		if (txtNombre.getText().compareTo("") != 0
				|| txtDescripcion.getValue().compareTo("") != 0) {
			return true;
		} else
			return false;
	}

	protected void limpiarCampos() {
		txtNombre.setValue("");
		txtDescripcion.setValue("");
		dtbFecha.setValue(fecha);
		try {
			imagen.setContent(new AImage(url));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		clave = null;
	}

	private void mostrarCatalogo() {
		listaGeneral = servicioNoticia.buscarTodosOrdenados();
		catalogo = new Catalogo<Noticia>(catalogoNoticia, "Noticias",
				listaGeneral, false, false, false, "Titulo", "Fecha"
				) {

			@Override
			protected List<Noticia> buscar(List<String> valores) {

				List<Noticia> lista = new ArrayList<Noticia>();

				for (Noticia objeto : listaGeneral) {
					if (objeto.getTitulo().toLowerCase()
							.contains(valores.get(0).toLowerCase())
							&& formatoFecha.format(objeto.getFecha()).toLowerCase()
									.contains(valores.get(1).toLowerCase())) {
						lista.add(objeto);
					}
				}
				return lista;
			}

			@Override
			protected String[] crearRegistros(Noticia objeto) {
				String[] registros = new String[2];
				registros[0] = objeto.getTitulo();
				registros[1] = formatoFecha.format(objeto.getFecha());
				return registros;
			}
		};
		catalogo.setParent(catalogoNoticia);
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

	@Listen("onUpload = #fudImagenUsuario")
	public void processMedia(UploadEvent event) {
		media = event.getMedia();
		if (media != null)
			if (validarMedia(media))
				imagen.setContent((org.zkoss.image.Image) media);
	}
	
	@Listen("onChange = #txtDescripcion")
	public boolean validarMax1() {
		if (txtDescripcion.getValue().length() > 4999) {
			msj.mensajeAlerta("Longitud maxima excedida (5000 caracteres)");
			return false;
		}
		return true;
	}

}
