package controlador.maestros;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;

import modelo.maestros.Responsabilidad;
import modelo.portal.Norma;

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
public class CResponsabilidad  extends CGenerico {

	@Wire
	private Textbox txtTitulo;
	@Wire
	private CKeditor ckTexto;
	@Wire
	private Div divResponsabilidad;
	@Wire
	private Div botoneraResponsabilidad;
	@Wire
	private Image imagen1;
	@Wire
	private Fileupload fudImagen1;
	@Wire
	private Media media1;
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
				cerrarVentana(divResponsabilidad, cerrar, tabs);

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
					
					Responsabilidad repon;
					if(servicioResponsabilidad.buscar(1) !=null)
					repon = servicioResponsabilidad.buscar(1);
					else
						repon = new Responsabilidad();
					
					byte[] imagenUsuario1 = null;
					if (media1 instanceof org.zkoss.image.Image
							&& imagen1.getContent() != null) {
						imagenUsuario1 = imagen1.getContent().getByteData();
						repon.setImagen(imagenUsuario1);
					}

					String texto = ckTexto.getValue();
					String titulo = txtTitulo.getValue();
					repon.setTitulo(titulo);
					repon.setTexto(texto);
					repon.setFechaAuditoria(fechaHora);
					repon.setHoraAuditoria(horaAuditoria);
					repon.setUsuarioAuditoria(nombreUsuarioSesion());
					servicioResponsabilidad.guardar(repon);
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
		botoneraResponsabilidad.appendChild(botonera);
		
	}
	
	public boolean validar() {
		if (txtTitulo.getText().compareTo("") == 0
				|| ckTexto.getValue().compareTo("") == 0
				|| imagen1.getContent() == null) {
			msj.mensajeError(Mensaje.camposVacios);
			return false;
		} else
			return true;
	}


	private void actualizar() {

		org.zkoss.image.Image imagenUsuario1 = null;
		imagen1.setContent(imagenUsuario1);
		txtTitulo.setValue("");
		ckTexto.setValue("");
		Responsabilidad respon = servicioResponsabilidad.buscar(1);
		if (respon != null) {
			txtTitulo.setValue(respon.getTitulo());
			ckTexto.setValue(respon.getTexto());
			BufferedImage imag1;
			if (respon.getImagen() != null) {
				try {
					imag1 = ImageIO.read(new ByteArrayInputStream(respon
							.getImagen()));
					imagen1.setContent(imag1);
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

	@Listen("onUpload = #fudImagen1")
	public void processMedia1(UploadEvent event) {
		media1 = event.getMedia();
		if (media1 != null)
			if (validarMedia(media1))
				imagen1.setContent((org.zkoss.image.Image) media1);
	}
	

	@Listen("onChange = #ckTexto")
	public boolean validarMax1() {
		if (ckTexto.getValue().length() > 4999) {
			msj.mensajeAlerta("Longitud maxima excedida (5000 caracteres)");
			return false;
		}
		return true;
	}

}
