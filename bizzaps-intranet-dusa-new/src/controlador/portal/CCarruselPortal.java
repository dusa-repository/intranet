package controlador.portal;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import modelo.maestros.Carrusel;

import org.springframework.stereotype.Controller;
import org.zkoss.zhtml.Li;
import org.zkoss.zhtml.Ul;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Div;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;

import controlador.maestros.CGenerico;


@Controller
public class CCarruselPortal extends CGenerico {

	@Wire
	private Div divCarrusel;

	/*
	 * Dibuja el Ul y Li en el Index.zul para lograr que el carrusel de
	 * imagenes fuera dinamico
	 */
	private void cargarCarrusel() {
		List<Carrusel> imagenes = servicioCarrusel.buscar();
		Ul diapos = new Ul();
		diapos.setId("diapos");
		divCarrusel.appendChild(diapos);
		for (Carrusel c : imagenes) {
			Image i = new Image();
			i.setWidth("100%");
			i.setHeight("100%");
			BufferedImage imag;
			try {
				imag = ImageIO.read(new ByteArrayInputStream(c.getImagen()));
				i.setContent(imag);
				Li li = new Li();
				li.appendChild(i);
				diapos.appendChild(li);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}


	@Override
	public void inicializar() throws IOException {
		 cargarCarrusel();		
	}
}