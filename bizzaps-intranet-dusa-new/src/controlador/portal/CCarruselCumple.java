package controlador.portal;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;



import modelo.portal.Carrusel;

import org.springframework.stereotype.Controller;
import org.zkoss.zhtml.Li;
import org.zkoss.zhtml.Ul;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Div;
import org.zkoss.zul.Image;

import controlador.maestros.CGenerico;


@Controller
public class CCarruselCumple extends CGenerico {

	@Wire
	private Div divCumpleannos;


	/*
	 * Dibuja el Ul y Li en el Index.zul para lograr que el carrusel de
	 * imagenes fuera dinamico
	 */
	private void cargarCarrusel() {
		List<Carrusel> imagenes2 =servicioCarrusel.buscarPorTipo("Cumple");
		Ul diapos2 = new Ul();
		diapos2.setId("diapos2");
		divCumpleannos.appendChild(diapos2);
		for (Carrusel c : imagenes2) {
			Image i = new Image();
			i.setWidth("100%");
			i.setHeight("100%");
			BufferedImage imag;
			try {
				imag = ImageIO.read(new ByteArrayInputStream(c.getImagen()));
				i.setContent(imag);
				Li li = new Li();
				li.appendChild(i);
				diapos2.appendChild(li);
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