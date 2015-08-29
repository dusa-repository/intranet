package controlador.portal;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import modelo.maestros.Noticia;
import modelo.maestros.Responsabilidad;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Html;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;

import controlador.maestros.CGenerico;

public class CVerNoticia extends CGenerico {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String valor = null;

	@Wire
	private Label titulo;
	@Wire
	private Label fecha;
	@Wire
	private Html noticiaA;
	@Wire
	private Html noticiaB;
	@Wire
	private Image imagen1;

	@Override
	public void inicializar() throws IOException {
		valor = Executions.getCurrent().getParameter("type");

		Noticia noticia = servicioNoticia.buscar(Long.valueOf(valor));

		if (noticia != null) {
			titulo.setValue(noticia.getTitulo());
			String fech = formatoFecha.format(noticia.getFecha());
			fecha.setValue(fech);
			String texto = noticia.getTexto();
			if (texto != null) {
				if (texto.length() <= 1499)
					noticiaA.setContent(texto);
				else {
					for (int i = 1500; i < texto.length(); i++) {

						if (texto.charAt(i) == ' ') {
							noticiaA.setContent(texto.substring(0, i));
							noticiaB.setContent(texto.substring(i,
									texto.length()));
							i = texto.length();
						}
					}
				}
			}
			BufferedImage imag1;
			if (noticia.getImagen() != null) {
				try {
					imag1 = ImageIO.read(new ByteArrayInputStream(noticia
							.getImagen()));
					imagen1.setContent(imag1);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
