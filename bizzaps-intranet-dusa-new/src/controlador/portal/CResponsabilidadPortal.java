package controlador.portal;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import modelo.maestros.Responsabilidad;

import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Html;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;

import controlador.maestros.CGenerico;

public class CResponsabilidadPortal extends CGenerico {

	@Wire
	private Label titulo;
	@Wire
	private Html primeraA;
	@Wire
	private Html primeraB;
	@Wire
	private Image imagen1;
	@Override
	public void inicializar() throws IOException {

		Responsabilidad respon = servicioResponsabilidad.buscar(1);
		if (respon != null) {
			titulo.setValue(respon.getTitulo());
			String texto = respon.getTexto();
			if (texto != null) {
				if (texto.length() <= 1299)
					primeraA.setContent(texto);
				else {
					for (int i = 1300; i < texto.length(); i++) {

						if (texto.charAt(i) == ' ') {
							primeraA.setContent(texto.substring(0, i));
							primeraB.setContent(texto.substring(i,
									texto.length()));
							i = texto.length();
						}
					}
				}
			}
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


}
