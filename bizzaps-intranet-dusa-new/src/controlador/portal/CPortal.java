package controlador.portal;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.A;
import org.zkoss.zul.Div;
import org.zkoss.zul.Image;
import org.zkoss.zul.Include;
import org.zkoss.zul.Label;
import org.zkoss.zul.Separator;

import controlador.maestros.CGenerico;

@Controller
public class CPortal extends CGenerico {

	@Wire
	private A aNoticia1;
	@Wire
	private Include contenido;
	@Wire
	private Div divNoticia1;
	@Wire
	private Div divNoticia2;
	@Wire
	private Div divVerTodas;
	@Wire
	private Div calendar;
	@Wire
	private Div n1;
	@Wire
	private Div n2;
	@Wire
	private Div c1;
	@Wire
	private Label lblTitulo;
	@Wire
	private Label lblTitulo2;
	@Wire
	private Image imagenDetalle;
	@Wire
	private Image imagenDetalle2;
	@Wire
	private Label lblTexto;
	@Wire
	private Label lblTexto2;

	@Override
	public void inicializar() throws IOException {
		// TODO Auto-generated method stub

	}

	@Listen("onClick = #aNoticia1")
	public void mostrar() {

		quitar();
		Label l = new Label();
		l.setClass("titulo");
		l.setValue("TITULOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
		n2.appendChild(l);
	}

	@Listen("onClick = #info1")
	public void mostrarInfo1() {
		quitar();
		lblTitulo2.setClass("titulo2");
		lblTitulo.setClass("titulo");
		lblTitulo.setValue("Recertificación a la constancia: ISO 14001GestiónAmbiental");
		lblTitulo2.setValue("Siendo la primera y única empresa de bebidas alcohólicas en Venezuela en obtener la certificación y recertificación, la sexta productora de ron a nivel mundial, éste importante logro ha sido posible luego de someter a DUSA a una exigente auditoría que permitió constatar la implementación y utilización de un correcto sistema de gestión ambiental");
		lblTexto.setValue("El pasado mes de julio 2012, DUSA recibió la recertificación ISO 14001 en un acto realizado en la Planta Industrial de La Miel, junto con la presencia de FONDONORMA (Fondo para la Normalización y Certificación de Calidad), gracias a su política medioambiental basada en un sistema integrado que permite minimizar el impacto ambiental y optimizar la utilización de los recursos naturales.");
		lblTexto2.setValue("La recertificación, fue acreditada tras un arduo proceso de evaluación donde se estudiaron los programas para lograr los objetivos y metas ambientales y posteriormente, a través de una auditoría por parte del ente certificador, se confirmó el cumplimiento de los requisitos de la norma");
		lblTexto2.setClass("text");
		lblTexto.setClass("text");
		imagenDetalle2.setVisible(false);
		imagenDetalle.setVisible(true);
	}
	@Listen("onClick = #info2")
	public void mostrarInfo2() {
		quitar();
		lblTitulo2.setClass("titulo2");
		lblTitulo.setClass("titulo");
		lblTitulo.setValue("Una historia de retos y metas alcanzadas");
		lblTitulo2.setValue("Hoy en día, el concepto de Calidad puede considerarse plenamente arraigado e incorporado al acerbo empresarial. Sin embargo, se observa cierta confusión a la hora de manejar los términos de Control de Calidad, Aseguramiento de la Calidad y Calidad Total");
		lblTexto.setValue("Control de Calidad:  Esta primera etapa se caracteriza por la realización de inspecciones y ensayos para comprobar si una determinada materia prima, un producto en proceso o un producto terminado, cumple con las especificaciones establecidas y evitar la salida de productos defectuosos. Se trata, sin duda, de una concepción poco competitiva de la Gestión de la Calidad, ya que las inspecciones o ensayos tienen lugar “a posteriori”, cuando la materia prima se ha recibido, cuando un proceso productivo ha concluido o cuando el producto final está terminado.");
		lblTexto2.setValue("Aseguramiento de la Calidad:  Surge con el desarrollo tecnológico y económico, por la necesidad de industrias que no pueden permitirse el lujo de tener una falla de calidad, tales como la aeronáutica, la nuclear, o de la defensa. Bajo este concepto, la premisa es que es más conveniente y rentable prevenir las fallas de calidad que corregirlas y se incorpora a la Gestión de la Calidad el concepto de la prevención");
		lblTexto2.setClass("text");
		lblTexto.setClass("text");
		imagenDetalle.setVisible(false);
		imagenDetalle2.setVisible(true);
	}

	public void quitar() {
		divNoticia1.setVisible(false);
		divNoticia2.setVisible(false);
		divVerTodas.setVisible(false);
		calendar.setVisible(false);
		n1.setVisible(true);
		n2.setVisible(true);
		c1.setVisible(true);
	}
}
