package controlador.maestros;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;

import modelo.maestros.Carrusel;

import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Div;
import org.zkoss.zul.Fileupload;
import org.zkoss.zul.Image;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Textbox;

import componente.Botonera;
import componente.Mensaje;

public class CCarrusel extends CGenerico {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Wire
	private Div divCarrusel;
	@Wire
	private Div botoneraCarrusel;
	@Wire
	private Textbox txtNombre1;
	@Wire
	private Textbox txtNombre2;
	@Wire
	private Textbox txtNombre3;
	@Wire
	private Textbox txtNombre4;
	@Wire
	private Image imagen1;
	@Wire
	private Fileupload fudImagen1;
	@Wire
	private Media media1;
	@Wire
	private Image imagen2;
	@Wire
	private Fileupload fudImagen2;
	@Wire
	private Media media2;
	@Wire
	private Image imagen3;
	@Wire
	private Fileupload fudImagen3;
	@Wire
	private Media media3;
	@Wire
	private Image imagen4;
	@Wire
	private Fileupload fudImagen4;
	@Wire
	private Media media4;
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
		cargarImagenes();
		botonera = new Botonera() {

			@Override
			public void seleccionar() {
				// TODO Auto-generated method stub

			}

			@Override
			public void salir() {
				cerrarVentana(divCarrusel, cerrar, tabs);
			}

			@Override
			public void reporte() {
				// TODO Auto-generated method stub

			}

			@Override
			public void limpiar() {
				cargarImagenes();
			}

			@Override
			public void guardar() {
				if (validar()) {
					if (imagen1.getContent() == null)
						if (servicioCarrusel.existe(1))
							servicioCarrusel.eliminar(1);
					if (imagen2.getContent() == null)
						if (servicioCarrusel.existe(2))
							servicioCarrusel.eliminar(2);
					if (imagen3.getContent() == null)
						if (servicioCarrusel.existe(3))
							servicioCarrusel.eliminar(3);
					if (imagen4.getContent() == null)
						if (servicioCarrusel.existe(4))
							servicioCarrusel.eliminar(4);
					byte[] imagenUsuario1 = null;
					if (media1 instanceof org.zkoss.image.Image
							&& imagen1.getContent() != null) {
						imagenUsuario1 = imagen1.getContent().getByteData();
						Carrusel objeto = new Carrusel(1,
								txtNombre1.getValue(), imagenUsuario1);
						servicioCarrusel.guardar(objeto);
					}

					byte[] imagenUsuario2 = null;
					if (media2 instanceof org.zkoss.image.Image
							&& imagen2.getContent() != null) {
						imagenUsuario2 = imagen2.getContent().getByteData();
						Carrusel objeto = new Carrusel(2,
								txtNombre2.getValue(), imagenUsuario2);
						servicioCarrusel.guardar(objeto);
					}
					byte[] imagenUsuario3 = null;
					if (media3 instanceof org.zkoss.image.Image
							&& imagen3.getContent() != null) {
						imagenUsuario3 = imagen3.getContent().getByteData();
						Carrusel objeto = new Carrusel(3,
								txtNombre3.getValue(), imagenUsuario3);
						servicioCarrusel.guardar(objeto);
					}
					byte[] imagenUsuario4 = null;
					if (media4 instanceof org.zkoss.image.Image
							&& imagen4.getContent() != null) {
						imagenUsuario4 = imagen4.getContent().getByteData();
						Carrusel objeto = new Carrusel(4,
								txtNombre4.getValue(), imagenUsuario4);
						servicioCarrusel.guardar(objeto);
					}
					msj.mensajeInformacion(Mensaje.guardado);
				}
			}

			@Override
			public void eliminar() {
				// TODO Auto-generated method stub

			}

			@Override
			public void buscar() {
				// TODO Auto-generated method stub

			}

			@Override
			public void ayuda() {
				// TODO Auto-generated method stub

			}

			@Override
			public void annadir() {
				// TODO Auto-generated method stub

			}
		};
		botonera.getChildren().get(0).setVisible(false);
		botonera.getChildren().get(1).setVisible(false);
		botonera.getChildren().get(2).setVisible(false);
		botonera.getChildren().get(4).setVisible(false);
		botonera.getChildren().get(6).setVisible(false);
		botonera.getChildren().get(8).setVisible(false);
		botoneraCarrusel.appendChild(botonera);
	}

	protected boolean validar() {
		if ((txtNombre1.getText().compareTo("") == 0 && imagen1.getContent() != null)
				|| (txtNombre2.getText().compareTo("") == 0 && imagen2
						.getContent() != null)
				|| (txtNombre3.getText().compareTo("") == 0 && imagen3
						.getContent() != null)
				|| (txtNombre4.getText().compareTo("") == 0 && imagen4
						.getContent() != null)) {
			msj.mensajeError(Mensaje.camposVacios);
			return false;
		} else
			return true;
	}

	private void cargarImagenes() {
		List<Carrusel> lista = servicioCarrusel.buscar();
		org.zkoss.image.Image imagenUsuario1 = null;
		imagen1.setContent(imagenUsuario1);
		imagen2.setContent(imagenUsuario1);
		imagen3.setContent(imagenUsuario1);
		imagen4.setContent(imagenUsuario1);
		txtNombre1.setValue("");
		txtNombre2.setValue("");
		txtNombre3.setValue("");
		txtNombre4.setValue("");
		for (int i = 0; i < lista.size(); i++) {
			switch ((int) lista.get(i).getIdCarrusel()) {
			case 1:
				settearImagen(lista.get(i).getImagen(), lista.get(i)
						.getNombre(), imagen1, txtNombre1);
				break;
			case 2:
				settearImagen(lista.get(i).getImagen(), lista.get(i)
						.getNombre(), imagen2, txtNombre2);
				break;
			case 3:
				settearImagen(lista.get(i).getImagen(), lista.get(i)
						.getNombre(), imagen3, txtNombre3);
				break;
			case 4:
				settearImagen(lista.get(i).getImagen(), lista.get(i)
						.getNombre(), imagen4, txtNombre4);
				break;
			}
		}
	}

	private void settearImagen(byte[] bs, String string, Image imagen12,
			Textbox txtNombre12) {
		txtNombre12.setValue(string);
		BufferedImage imag1;
		try {
			imag1 = ImageIO.read(new ByteArrayInputStream(bs));
			imagen12.setContent(imag1);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Listen("onClick = #btnRemover1")
	public void limpiar1() {
		org.zkoss.image.Image imagenUsuario1 = null;
		imagen1.setContent(imagenUsuario1);
	}

	@Listen("onClick = #btnRemover2")
	public void limpiar2() {
		org.zkoss.image.Image imagenUsuario1 = null;
		imagen2.setContent(imagenUsuario1);
	}

	@Listen("onClick = #btnRemover3")
	public void limpiar3() {
		org.zkoss.image.Image imagenUsuario1 = null;
		imagen3.setContent(imagenUsuario1);
	}

	@Listen("onClick = #btnRemover4")
	public void limpiar4() {
		org.zkoss.image.Image imagenUsuario1 = null;
		imagen4.setContent(imagenUsuario1);
	}

	@Listen("onUpload = #fudImagen1")
	public void processMedia1(UploadEvent event) {
		media1 = event.getMedia();
		if (media1 != null)
			if (validarMedia(media1))
				imagen1.setContent((org.zkoss.image.Image) media1);
	}

	@Listen("onUpload = #fudImagen2")
	public void processMedia2(UploadEvent event) {
		media2 = event.getMedia();
		if (media2 != null)
			if (validarMedia(media2))
				imagen2.setContent((org.zkoss.image.Image) media2);
	}

	@Listen("onUpload = #fudImagen3")
	public void processMedia3(UploadEvent event) {
		media3 = event.getMedia();
		if (media3 != null)
			if (validarMedia(media3))
				imagen3.setContent((org.zkoss.image.Image) media3);
	}

	@Listen("onUpload = #fudImagen4")
	public void processMedia4(UploadEvent event) {
		media4 = event.getMedia();
		if (media4 != null)
			if (validarMedia(media4))
				imagen4.setContent((org.zkoss.image.Image) media4);
	}

}
