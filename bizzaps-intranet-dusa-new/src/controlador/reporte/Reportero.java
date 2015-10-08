package controlador.reporte;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;

import org.zkforge.json.simple.JSONObject;

import controlador.maestros.CEmpleado;
import controlador.portal.CPortal;
import controlador.portal.CTodasNoticiasPortal;

/**
 * Servlet implementation class Reportero
 */
@WebServlet("/Reportero")
public class Reportero extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Reportero() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		CCumple empleado = new CCumple();
		CPortal portal = new CPortal();
		CTodasNoticiasPortal noti = new CTodasNoticiasPortal();

		ServletOutputStream out;
		Long part2 = (long) 0;
		String par1 = request.getParameter("valor");
		String part21 = request.getParameter("valor2");
		if (part21 != null)
			part2 = Long.parseLong(part21);
		String par3 = request.getParameter("valor3");

		String par6 = request.getParameter("valor6");
		String par7 = request.getParameter("valor7");
		String par8 = request.getParameter("valor8");
		String par9 = request.getParameter("valor9");
		String tipo = request.getParameter("valor20");

		byte[] fichero = null;
		switch (par1) {
		case "1":
			fichero = empleado.reporteResumen(par6, par7, par8,par9, tipo);
			break;
		case "2":
			fichero = portal.reporteResumen(par6);
			break;
		default:
			break;
		}

		if (tipo != null) {
			if (tipo.equals("EXCEL")) {
				response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
				response.setHeader("Content-Disposition",
						"inline; filename=Reporte.xlsx");
			} else {
				response.setContentType("application/pdf");
				response.setHeader("Content-disposition",
						"inline; filename=Reporte.pdf");
			}
		} else {
			response.setContentType("application/pdf");
			response.setHeader("Content-disposition",
					"inline; filename=Reporte.pdf");
		}
		response.setHeader("Cache-Control", "max-age=30");
		response.setHeader("Pragma", "No-cache");
		response.setDateHeader("Expires", 0);
		response.setContentLength(fichero.length);
		out = response.getOutputStream();
		out.write(fichero, 0, fichero.length);
		out.flush();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
