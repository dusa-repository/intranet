package controlador.portal;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import modelo.maestros.Norma;

import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Html;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;

import controlador.maestros.CGenerico;

@Controller
public class CNormaPortal extends CGenerico {

	@Wire
	private Label titulo;
	@Wire
	private Html primeraA;
	@Wire
	private Html primeraB;
	@Wire
	private Image imagen1;
	@Wire
	private Label titulo2;
	@Wire
	private Html segundaA;
	@Wire
	private Html segundaB;
	@Wire
	private Image imagen2;

	@Override
	public void inicializar() throws IOException {

		Norma normaa = servicioNorma.buscar(1);
		if (normaa != null) {
			titulo.setValue(normaa.getTituloPrimera());
			String texto = normaa.getTextoPrimera();
			titulo2.setValue(normaa.getTituloSegunda());
			String texto2 = normaa.getTextoSegunda();
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
			if (texto2 != null) {
				if (texto2.length() <= 1399)
					segundaA.setContent(texto2);
				else {
					for (int i = 1300; i < texto2.length(); i++) {

						if (texto2.charAt(i) == ' ') {
							segundaA.setContent(texto2.substring(0, i));
							segundaB.setContent(texto2.substring(i,
									texto2.length()));
							i = texto2.length();
						}
					}
				}
			}
			BufferedImage imag1;
			if (normaa.getImagenPrimera() != null) {
				try {
					imag1 = ImageIO.read(new ByteArrayInputStream(normaa
							.getImagenPrimera()));
					imagen1.setContent(imag1);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			BufferedImage imag2;
			if (normaa.getImagenSegunda() != null) {
				try {
					imag2 = ImageIO.read(new ByteArrayInputStream(normaa
							.getImagenSegunda()));
					imagen2.setContent(imag2);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
