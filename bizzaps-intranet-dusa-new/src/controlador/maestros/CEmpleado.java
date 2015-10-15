package controlador.maestros;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;

import modelo.maestros.Empleado;

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
import org.zkoss.zul.Radio;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Textbox;

import componente.Botonera;
import componente.Catalogo;
import componente.Mensaje;
import componente.Validador;

public class CEmpleado extends CGenerico {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Wire
	private Div divEmpleado;
	@Wire
	private Div botoneraEmpleado;
	@Wire
	private Div catalogoEmpleado;
	@Wire
	private Textbox txtNombre;
	@Wire
	private Textbox txtApellido;
	@Wire
	private Textbox txtSegundoNombre;
	@Wire
	private Textbox txtSegundoApellido;
	@Wire
	private Textbox txtTelefonoFijo;
	@Wire
	private Textbox txtTelefonoCedular;
	@Wire
	private Textbox txtDireccion;
	@Wire
	private Textbox txtFicha;
	@Wire
	private Textbox txtEmpresa;
	@Wire
	private Datebox dtbFecha;
	@Wire
	private Datebox dtbFechaIngreso;
	@Wire
	private Radiogroup rdgExcepcion;
	@Wire
	private Radio rdoSi;
	@Wire
	private Radio rdoNo;
	@Wire
	private Groupbox gpxDatos;
	@Wire
	private Groupbox gpxRegistro;
	@Wire
	private Image imagen;
	@Wire
	private Media media;
	Long clave = null;
	Catalogo<Empleado> catalogo;
	protected List<Empleado> listaGeneral = new ArrayList<Empleado>();
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
						Empleado producto = catalogo
								.objetoSeleccionadoDelCatalogo();
						clave = producto.getIdEmpleado();
						txtNombre.setValue(producto.getNombre());
						txtApellido.setValue(producto.getApellido());
						txtSegundoNombre.setValue(producto.getSegundoNombre());
						txtSegundoApellido.setValue(producto
								.getSegundoApellido());
						txtDireccion.setValue(producto.getDireccion());
						txtFicha.setValue(producto.getFicha());
						txtEmpresa.setValue(producto.getEmpresa());
						txtTelefonoCedular.setValue(producto
								.getTelefonoCelular());
						txtTelefonoFijo.setValue(producto.getTelefonoFijo());
						dtbFecha.setValue(producto.getFechaNacimiento());
						dtbFechaIngreso.setValue(producto.getFechaIngreso());
						if (producto.isExcepcion())
							rdoSi.setChecked(true);
						else
							rdoNo.setChecked(true);
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
				cerrarVentana(divEmpleado, cerrar, tabs);
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
					Empleado empleado = new Empleado(clave,
							txtFicha.getValue(), txtNombre.getValue(),
							txtApellido.getValue(), txtDireccion.getValue(),
							txtTelefonoFijo.getValue(),
							txtTelefonoCedular.getValue(), imagenUsuario,
							new Timestamp(dtbFecha.getValue().getTime()),
							fechaHora, horaAuditoria, nombreUsuarioSesion(),
							"", "");
					empleado.setEmpresa(txtEmpresa.getValue());
					empleado.setSegundoApellido(txtSegundoApellido.getValue());
					empleado.setSegundoNombre(txtSegundoNombre.getValue());
					empleado.setFechaIngreso(new Timestamp(dtbFechaIngreso
							.getValue().getTime()));

					if (rdoSi.isChecked())
						empleado.setExcepcion(true);
					else
						empleado.setExcepcion(false);
					empleado.setEstatus(true);

					servicioEmpleado.guardar(empleado);
					msj.mensajeInformacion(Mensaje.guardado);
					limpiar();
					listaGeneral = servicioEmpleado.buscarTodosOrdenados();
					catalogo.actualizarLista(listaGeneral, true);
				}
			}

			@Override
			public void eliminar() {
				if (gpxDatos.isOpen()) {
					/* Elimina Varios Registros */
					if (validarSeleccion(catalogo)) {
						final List<Empleado> eliminarLista = catalogo
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
													for (int i = 0; i < eliminarLista
															.size(); i++) {

														Empleado emple = eliminarLista
																.get(i);
														if (emple != null) {
															emple.setEstatus(false);
															servicioEmpleado
																	.guardar(emple);
														}
													}

													msj.mensajeInformacion(Mensaje.eliminado);
													listaGeneral = servicioEmpleado
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
													Empleado emple = servicioEmpleado
															.buscar(clave);
													if (emple != null) {
														emple.setEstatus(false);
														servicioEmpleado
																.guardar(emple);
													}
													msj.mensajeInformacion(Mensaje.eliminado);
													limpiar();
													listaGeneral = servicioEmpleado
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
		botoneraEmpleado.appendChild(botonera);
	}

	protected boolean validar() {
		if (!camposLLenos()) {
			msj.mensajeError(Mensaje.camposVacios);
			return false;
		} else {
			if (!Validador.validarTelefono(txtTelefonoCedular.getValue())
					|| (txtTelefonoFijo.getText().compareTo("") != 0 && !Validador
							.validarTelefono(txtTelefonoFijo.getValue()))) {
				msj.mensajeError(Mensaje.telefonoInvalido);
				return false;
			} else {
				if (!validarFicha())
					return false;
				else
					return true;
			}
		}
	}

	private boolean camposLLenos() {
		if (txtNombre.getText().compareTo("") == 0
				|| txtApellido.getText().compareTo("") == 0
				|| txtDireccion.getText().compareTo("") == 0
				|| txtSegundoNombre.getText().compareTo("") == 0
				|| txtSegundoApellido.getText().compareTo("") == 0
				|| txtEmpresa.getText().compareTo("") == 0
				|| txtTelefonoCedular.getText().compareTo("") == 0
				|| !rdoSi.isChecked() && !rdoNo.isChecked()) {
			return false;
		} else
			return true;
	}

	public boolean camposEditando() {
		if (txtNombre.getText().compareTo("") != 0
				|| txtApellido.getText().compareTo("") != 0
				|| txtDireccion.getText().compareTo("") != 0
				|| txtFicha.getText().compareTo("") != 0
				|| txtTelefonoCedular.getText().compareTo("") != 0
				|| txtTelefonoFijo.getText().compareTo("") != 0
				|| txtEmpresa.getText().compareTo("") != 0
				|| txtSegundoApellido.getText().compareTo("") != 0
				|| txtSegundoNombre.getText().compareTo("") != 0) {
			return true;
		} else
			return false;
	}

	protected void limpiarCampos() {
		txtNombre.setValue("");
		txtApellido.setValue("");
		txtDireccion.setValue("");
		txtFicha.setValue("");
		txtTelefonoCedular.setValue("");
		txtTelefonoFijo.setValue("");
		dtbFecha.setValue(new Date());
		try {
			imagen.setContent(new AImage(url));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		clave = null;
		txtSegundoNombre.setValue("");
		txtSegundoApellido.setValue("");
		txtEmpresa.setValue("");
		rdoNo.setChecked(true);
		dtbFechaIngreso.setValue(new Date());
		
	}

	private void mostrarCatalogo() {
		listaGeneral = servicioEmpleado.buscarTodosOrdenados();
		catalogo = new Catalogo<Empleado>(catalogoEmpleado, "Empleados",
				listaGeneral, false, false, false, "Ficha", "Nombre",
				"Apellido", "Fecha Nacimiento", "Tlf. Cedular", "Estatus") {

			@Override
			protected List<Empleado> buscar(List<String> valores) {

				List<Empleado> lista = new ArrayList<Empleado>();

				for (Empleado objeto : listaGeneral) {
					if (objeto.getFicha().toLowerCase()
							.contains(valores.get(0).toLowerCase())
							&& objeto.getNombre().toLowerCase()
									.contains(valores.get(1).toLowerCase())
							&& objeto.getApellido().toLowerCase()
									.contains(valores.get(2).toLowerCase())
							&& formatoFecha.format(objeto.getFechaNacimiento())
									.toLowerCase()
									.contains(valores.get(3).toLowerCase())
							&& String.valueOf(objeto.getTelefonoCelular()).toLowerCase()
									.contains(valores.get(4).toLowerCase())) {
						lista.add(objeto);
					}
				}
				return lista;
			}

			@Override
			protected String[] crearRegistros(Empleado objeto) {
				String[] registros = new String[6];
				String activo = "Activo";
				if (!objeto.isEstatus())
					activo = "Inactivo";
				registros[0] = objeto.getFicha();
				registros[1] = objeto.getNombre();
				registros[2] = objeto.getApellido();
				registros[3] = formatoFecha.format(objeto.getFechaNacimiento());
				if(objeto.getTelefonoCelular()!=null)
				registros[4] = objeto.getTelefonoCelular();
				else
					registros[4] ="";
				registros[5] = activo;
				return registros;
			}
		};
		catalogo.setParent(catalogoEmpleado);
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

	/* Valida la Ficha */
	@Listen("onChange = #txtFicha; onOK = #txtFicha")
	public boolean validarFicha() {
		List<Empleado> validador = servicioEmpleado.buscarPorFicha(txtFicha
				.getValue());
		if (!validador.isEmpty()) {
			if (clave != null) {
				Empleado em = servicioEmpleado.buscar(clave);
				if (em.getFicha().equals(validador.get(0).getFicha()))
					return true;
			}
			Mensaje.mensajeAlerta("La ficha que ha ingresado pertenece a otro Empleado");
			return false;
		}
		return true;
	}

	/* Metodo que valida el formmato del telefono ingresado */
	@Listen("onChange = #txtTelefonoCedular")
	public void validarTelefono() throws IOException {
		if (Validador.validarTelefono(txtTelefonoCedular.getValue()) == false) {
			Mensaje.mensajeAlerta(Mensaje.telefonoInvalido);
		}
	}

	/* Metodo que valida el formmato del telefono ingresado */
	@Listen("onChange = #txtTelefonoFijo")
	public void validarTelefono2() throws IOException {
		if (Validador.validarTelefono(txtTelefonoFijo.getValue()) == false) {
			Mensaje.mensajeAlerta(Mensaje.telefonoInvalido);
		}
	}

}
