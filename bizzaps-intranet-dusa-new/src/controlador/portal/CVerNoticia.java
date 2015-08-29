package controlador.portal;

import java.io.IOException;

import modelo.maestros.Noticia;

import org.zkoss.zk.ui.Executions;

import controlador.maestros.CGenerico;

public class CVerNoticia extends CGenerico{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String valor = null;

	@Override
	public void inicializar() throws IOException {
		valor = Executions.getCurrent().getParameter("type");
		System.out.println(valor);
		Noticia noticia = servicioNoticia.buscar(Long.valueOf(valor));
		
	}

}
