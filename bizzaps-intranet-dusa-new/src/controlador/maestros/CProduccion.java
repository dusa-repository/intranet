package controlador.maestros;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import modelo.maestros.Produccion;

import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Div;
import org.zkoss.zul.Doublebox;
import org.zkoss.zul.Doublespinner;
import org.zkoss.zul.Groupbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Textbox;

import componente.Botonera;
import componente.Catalogo;
import componente.Mensaje;

public class CProduccion extends CGenerico {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Wire
	private Div divProduccion;
	@Wire
	private Div botoneraProduccion;
	@Wire
	private Div catalogoProduccion;
	@Wire
	private Textbox txtEmpresa;
	@Wire
	private Textbox txtTipo;
	@Wire
	private Doublebox txtPorcentaje;
	@Wire
	private Doublespinner spnPlanificada;
	@Wire
	private Doublespinner spnAcumulada;
	@Wire
	private Doublespinner spnDiaria;
	@Wire
	private Groupbox gpxDatos;
	@Wire
	private Groupbox gpxRegistro;
	Long clave = null;
	Catalogo<Produccion> catalogo;
	protected List<Produccion> listaGeneral = new ArrayList<Produccion>();
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
						Produccion producto = catalogo
								.objetoSeleccionadoDelCatalogo();
						clave = producto.getIdProduccion();
						txtEmpresa.setValue(producto.getEmpresa());
						txtTipo.setValue(producto.getTipo());
						txtPorcentaje.setValue(round(producto.getAcumulada()
								/ producto.getPlanificada() * 100,2));
						spnAcumulada.setValue(producto.getAcumulada());
						spnDiaria.setValue(producto.getDiaria());
						spnPlanificada.setValue(producto.getPlanificada());
					} else
						Mensaje.mensajeAlerta(Mensaje.editarSoloUno);
				}
			}

			@Override
			public void salir() {
				cerrarVentana(divProduccion, cerrar, tabs);
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
					if (clave == null)
						clave = (long) 0;
					Produccion produccion = new Produccion(clave,
							txtEmpresa.getValue(), txtTipo.getValue(),
							spnDiaria.getValue(), spnAcumulada.getValue(),
							spnPlanificada.getValue(), fechaHora,
							horaAuditoria, nombreUsuarioSesion(), "", "");
					servicioProduccion.guardar(produccion);
					msj.mensajeInformacion(Mensaje.guardado);
					limpiar();
					listaGeneral = servicioProduccion.buscarTodosOrdenados();
					catalogo.actualizarLista(listaGeneral, true);
				}
			}

			@Override
			public void eliminar() {
				if (gpxDatos.isOpen()) {
					/* Elimina Varios Registros */
					if (validarSeleccion(catalogo)) {
						final List<Produccion> eliminarLista = catalogo
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
													servicioProduccion
															.eliminarVarios(eliminarLista);
													msj.mensajeInformacion(Mensaje.eliminado);
													listaGeneral = servicioProduccion
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
													servicioProduccion
															.eliminarUno(clave);
													msj.mensajeInformacion(Mensaje.eliminado);
													limpiar();
													listaGeneral = servicioProduccion
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
		botoneraProduccion.appendChild(botonera);

	}

	protected boolean validar() {
		if (!camposLLenos()) {
			msj.mensajeError(Mensaje.camposVacios);
			return false;
		} else {
				if (!validarFicha())
					return false;
				else
					return true;
		}
	}

	private boolean camposLLenos() {
		if (txtEmpresa.getText().compareTo("") == 0
				|| txtPorcentaje.getText().compareTo("") == 0
				|| txtTipo.getText().compareTo("") == 0) {
			return false;
		} else
			return true;
	}

	public boolean camposEditando() {
		if (txtEmpresa.getText().compareTo("") != 0
				|| txtPorcentaje.getText().compareTo("") != 0
				|| txtTipo.getText().compareTo("") != 0) {
			return true;
		} else
			return false;
	}

	protected void limpiarCampos() {
		txtEmpresa.setValue("");
		txtTipo.setValue("");
		txtPorcentaje.setValue(null);
		spnAcumulada.setValue((double) 0);
		spnDiaria.setValue((double) 0);
		spnPlanificada.setValue((double) 0);
		clave = null;
	}

	private void mostrarCatalogo() {
		listaGeneral = servicioProduccion.buscarTodosOrdenados();
		catalogo = new Catalogo<Produccion>(catalogoProduccion, "Producciones",
				listaGeneral, false, false, false, "Empresa", "Tipo", "Diaria",
				"Acumulada", "Planificada") {

			@Override
			protected List<Produccion> buscar(List<String> valores) {

				List<Produccion> lista = new ArrayList<Produccion>();

				for (Produccion objeto : listaGeneral) {
					if (objeto.getEmpresa().toLowerCase()
							.contains(valores.get(0).toLowerCase())
							&& objeto.getTipo().toLowerCase()
									.contains(valores.get(1).toLowerCase())
							&& String.valueOf(objeto.getDiaria()).toLowerCase()
									.contains(valores.get(2).toLowerCase())
							&& String.valueOf(objeto.getAcumulada())
									.toLowerCase()
									.contains(valores.get(3).toLowerCase())
							&& String.valueOf(objeto.getPlanificada())
									.toLowerCase()
									.contains(valores.get(4).toLowerCase())) {
						lista.add(objeto);
					}
				}
				return lista;
			}

			@Override
			protected String[] crearRegistros(Produccion objeto) {
				String[] registros = new String[5];
				registros[0] = objeto.getEmpresa();
				registros[1] = objeto.getTipo();
				registros[2] = String.valueOf(objeto.getDiaria());
				registros[3] = String.valueOf(objeto.getAcumulada());
				registros[4] = String.valueOf(objeto.getPlanificada());
				return registros;
			}
		};
		catalogo.setParent(catalogoProduccion);
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

	/* Valida la Ficha */
	@Listen("onChange = #txtEmpresa; onOK = #txtEmpresa")
	public boolean validarFicha() {
		List<Produccion> validador = servicioProduccion
				.buscarPorEmpresa(txtEmpresa.getValue());
		if (!validador.isEmpty()) {
			if (clave != null) {
				Produccion em = servicioProduccion.buscar(clave);
				if (em.getEmpresa().equals(validador.get(0).getEmpresa()))
					return true;
			}
			Mensaje.mensajeAlerta("Ya se ha establecido la produccion para esta empresa");
			return false;
		}
		return true;
	}

	@Listen("onChange = #spnPlanificada, #spnAcumulada")
	public void settear() {
		if (spnPlanificada.getValue() != 0) {
			txtPorcentaje.setValue(round(spnAcumulada.getValue()
					/ spnPlanificada.getValue()* 100, 2));
			;
		} else
			txtPorcentaje.setValue(0);
	}

}
