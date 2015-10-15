package controlador.reporte;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import modelo.maestros.Empleado;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRLoader;

import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Spinner;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Textbox;

import componente.Botonera;
import componente.Mensaje;
import controlador.maestros.CGenerico;

public class CCumple extends CGenerico {

	private static final long serialVersionUID = 1L;
	@Wire
	private Datebox dtbDesde;
	@Wire
	private Combobox cmbTipo;
	@Wire
	private Div botoneraCumple;
	@Wire
	private Radiogroup rdgReporte;
	@Wire
	private Radio rdoCumple;
	@Wire
	private Radio rdoAniversario;
	@Wire
	private Textbox txtObservacion;
	@Wire
	private Div divCumple;
	@Wire
	private Spinner spnAnnos;
	private String nombre;

	@Override
	public void inicializar() throws IOException {
		HashMap<String, Object> mapa = (HashMap<String, Object>) Sessions
				.getCurrent().getAttribute("mapaGeneral");
		if (mapa != null) {
			if (mapa.get("tabsGenerales") != null) {
				tabs = (List<Tab>) mapa.get("tabsGenerales");
				nombre = (String) mapa.get("titulo");
				mapa.clear();
				mapa = null;
			}
		}

		rdoCumple.setChecked(true);
		Botonera botonera = new Botonera() {

			@Override
			public void seleccionar() {
				Date desde = dtbDesde.getValue();
				String fecha1 = formatoReporte.format(desde);
				String tipoReporte = cmbTipo.getValue();
				String tipo = "";
				String observacion = txtObservacion.getValue();
				Integer anios = spnAnnos.getValue();
				if (rdoAniversario.isChecked())
					tipo = "a";
				else
					tipo = "c";

				Collection<? extends Empleado> empleados = new ArrayList<Empleado>();
				if (tipo.equals("c"))
					empleados = servicioEmpleado
							.buscarTodosOrdenadosFecha(dtbDesde.getValue());
				else
					empleados = servicioEmpleado.buscarAniversarios(desde,
							anios);

				if (!empleados.isEmpty())
					Clients.evalJavaScript("window.open('"
							+ damePath()
							+ "Reportero?valor=1&valor6="
							+ fecha1
							+ "&valor7="
							+ String.valueOf(anios)
							+ "&valor8="
							+ tipo
							+ "&valor9="
							+ observacion
							+ "&valor20="
							+ tipoReporte
							+ "','','top=100,left=200,height=600,width=800,scrollbars=1,resizable=1')");
				else
					Mensaje.mensajeAlerta(Mensaje.noHayRegistros);

			}

			@Override
			public void salir() {
				cerrarVentana(divCumple, nombre, tabs);

			}

			@Override
			public void reporte() {
				// TODO Auto-generated method stub

			}

			@Override
			public void limpiar() {
				dtbDesde.setValue(fecha);
				rdoCumple.setChecked(true);
				cmbTipo.setValue("PDF");
				txtObservacion.setValue("");

			}

			@Override
			public void guardar() {

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
		botonera.getChildren().get(8).setVisible(false);
		botonera.getChildren().get(1).setVisible(false);
		botonera.getChildren().get(2).setVisible(false);
		botonera.getChildren().get(3).setVisible(false);
		botonera.getChildren().get(4).setVisible(false);
		botonera.getChildren().get(6).setVisible(false);
		Button guardar = (Button) botonera.getChildren().get(0);
		guardar.setLabel("Reporte");
		guardar.setImage("/public/imagenes/botones/reporte.png");
		botoneraCumple.appendChild(botonera);

	}

	public byte[] reporteResumen(String par6, String par7, String tipo,
			String observacion, String tipo2) {
		byte[] fichero = null;
		Integer anios = Integer.valueOf(par7);
		SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
		Date fecha1 = null;
		try {
			fecha1 = formato.parse(par6);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Collection<? extends Empleado> empleados = new ArrayList<Empleado>();
		if (tipo.equals("c"))
			empleados = getServicioEmpleado().buscarTodosOrdenadosFecha(fecha1);
		else
			empleados = getServicioEmpleado().buscarAniversarios(fecha1, anios);

		Map<String, Object> p = new HashMap<String, Object>();
		p.put("desde", par6);
		p.put("fechaReal", new Timestamp(fecha1.getTime()));
		p.put("hasta", par7);
		p.put("observacion", observacion);

		if (tipo.equals("c"))
			p.put("titulo", "CUMPLEAÑEROS");
		else
			p.put("titulo", "ANIVERSARIOS");
		JasperReport reporte = null;
		try {
			reporte = (JasperReport) JRLoader.loadObject(getClass()
					.getResource("/reporte/RCumpleannosAniversarios.jasper"));
		} catch (JRException e) {
			msj = new Mensaje();
			msj.mensajeError("Recurso no Encontrado");
		}
		if (tipo2.equals("EXCEL")) {

			JasperPrint jasperPrint = null;
			try {
				jasperPrint = JasperFillManager.fillReport(reporte, p,
						new JRBeanCollectionDataSource(empleados));
			} catch (JRException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ByteArrayOutputStream xlsReport = new ByteArrayOutputStream();
			JRXlsxExporter exporter = new JRXlsxExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, xlsReport);
			try {
				exporter.exportReport();
			} catch (JRException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return xlsReport.toByteArray();
		} else {
			try {
				fichero = JasperRunManager.runReportToPdf(reporte, p,
						new JRBeanCollectionDataSource(empleados));
			} catch (JRException e) {
				e.printStackTrace();
			}
			return fichero;
		}
	}

}
