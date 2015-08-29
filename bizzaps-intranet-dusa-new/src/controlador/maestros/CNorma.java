package controlador.maestros;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;

import modelo.maestros.Norma;

import org.springframework.stereotype.Controller;
import org.zkforge.ckez.CKeditor;
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

@Controller
public class CNorma extends CGenerico {

	@Wire
	private Textbox txtTituloPrimera;
	@Wire
	private CKeditor ckTextoPrimera;
	@Wire
	private Textbox txtTituloSegunda;
	@Wire
	private CKeditor ckTextoSegunda;
	@Wire
	private Div divNorma;
	@Wire
	private Div botoneraNorma;
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
		actualizar();

		botonera = new Botonera() {

			@Override
			public void seleccionar() {
			}

			@Override
			public void salir() {
				cerrarVentana(divNorma, cerrar, tabs);

			}

			@Override
			public void reporte() {
				// TODO Auto-generated method stub

			}

			@Override
			public void limpiar() {
				actualizar();

			}

			@Override
			public void guardar() {
				if (validar()) {
					Norma norma;
					if(servicioNorma.buscar(1)!=null)
					norma = servicioNorma.buscar(1);
					else
						norma = new Norma();
					byte[] imagenUsuario1 = null;
					if (media1 instanceof org.zkoss.image.Image
							&& imagen1.getContent() != null) {
						imagenUsuario1 = imagen1.getContent().getByteData();
						norma.setImagenPrimera(imagenUsuario1);
					}

					byte[] imagenUsuario2 = null;
					if (media2 instanceof org.zkoss.image.Image
							&& imagen2.getContent() != null) {
						imagenUsuario2 = imagen2.getContent().getByteData();
						norma.setImagenSegunda(imagenUsuario2);
					}

					String textoPrimera = ckTextoPrimera.getValue();
					String titulo1 = txtTituloPrimera.getValue();
					String textoSegunda = ckTextoSegunda.getValue();
					String titulo2 = txtTituloSegunda.getValue();
					norma.setTituloPrimera(titulo1);
					norma.setTextoPrimera(textoPrimera);
					norma.setTituloSegunda(titulo2);
					norma.setTextoSegunda(textoSegunda);
					norma.setFechaAuditoria(fechaHora);
					norma.setHoraAuditoria(horaAuditoria);
					norma.setUsuarioAuditoria(nombreUsuarioSesion());
					servicioNorma.guardar(norma);
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
		botonera.getChildren().get(6).setVisible(false);
		botonera.getChildren().get(8).setVisible(false);
		botonera.getChildren().get(1).setVisible(false);
		botonera.getChildren().get(0).setVisible(false);
		botonera.getChildren().get(2).setVisible(false);
		botonera.getChildren().get(4).setVisible(false);
		botoneraNorma.appendChild(botonera);
	}

	public boolean validar() {
		if (txtTituloPrimera.getText().compareTo("") == 0
				|| ckTextoPrimera.getValue().compareTo("") == 0
				|| imagen1.getContent() == null || imagen2.getContent() == null
				|| txtTituloSegunda.getText().compareTo("") == 0
				|| ckTextoSegunda.getValue().compareTo("") == 0) {
			msj.mensajeError(Mensaje.camposVacios);
			return false;
		} else
			return true;
	}

	private void actualizar() {

		org.zkoss.image.Image imagenUsuario1 = null;
		imagen1.setContent(imagenUsuario1);
		imagen2.setContent(imagenUsuario1);
		txtTituloPrimera.setValue("");
		ckTextoPrimera.setValue("");
		txtTituloSegunda.setValue("");
		ckTextoSegunda.setValue("");
		Norma norma = servicioNorma.buscar(1);
		if (norma != null) {
			txtTituloPrimera.setValue(norma.getTituloPrimera());
			ckTextoPrimera.setValue(norma.getTextoPrimera());
			txtTituloSegunda.setValue(norma.getTituloSegunda());
			ckTextoSegunda.setValue(norma.getTextoSegunda());
			BufferedImage imag1;
			if (norma.getImagenPrimera() != null) {
				try {
					imag1 = ImageIO.read(new ByteArrayInputStream(norma
							.getImagenPrimera()));
					imagen1.setContent(imag1);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			BufferedImage imag2;
			if (norma.getImagenSegunda() != null) {
				try {
					imag2 = ImageIO.read(new ByteArrayInputStream(norma
							.getImagenSegunda()));
					imagen2.setContent(imag2);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
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

	@Listen("onChange = #ckTextoPrimera")
	public boolean validarMax1() {
		if (ckTextoPrimera.getValue().length() > 4999) {
			msj.mensajeAlerta("Longitud maxima excedida (5000 caracteres)");
			return false;
		}
		return true;
	}

	@Listen("onChange = #ckTextoSegunda")
	public boolean validarMax2() {
		if (ckTextoSegunda.getValue().length() > 4999) {
			msj.mensajeAlerta("Longitud maxima excedida (5000 caracteres)");
			return false;
		}
		return true;
	}
}
