package controlador.maestros;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Properties;
import java.util.TimeZone;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Div;
import org.zkoss.zul.Tab;

import servicio.maestros.SCarrusel;
import servicio.maestros.SDestilo;
import servicio.maestros.SDocumento;
import servicio.maestros.SEmpleado;
import servicio.maestros.SEnlace;
import servicio.maestros.SNorma;
import servicio.maestros.SNoticia;
import servicio.maestros.SPrensaPreviene;
import servicio.maestros.SProduccion;
import servicio.maestros.SProducto;
import servicio.maestros.SResponsabilidad;
import servicio.maestros.SVenta;
import componente.Botonera;
import componente.Catalogo;
import componente.Mensaje;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public abstract class CGenerico extends SelectorComposer<Component> {

	private static final long serialVersionUID = -3701148488846104476L;

	@WireVariable("SCarrusel")
	protected SCarrusel servicioCarrusel;
	@WireVariable("SProducto")
	protected SProducto servicioProducto;
	@WireVariable("SNoticia")
	protected SNoticia servicioNoticia;
	@WireVariable("SNorma")
	protected SNorma servicioNorma;
	@WireVariable("SResponsabilidad")
	protected SResponsabilidad servicioResponsabilidad;
	@WireVariable("SEmpleado")
	protected SEmpleado servicioEmpleado;
	@WireVariable("SDocumento")
	protected SDocumento servicioDocumento;
	@WireVariable("SEnlace")
	protected SEnlace servicioEnlace;
	@WireVariable("SVenta")
	protected SVenta servicioVenta;
	@WireVariable("SProduccion")
	protected SProduccion servicioProduccion;
	@WireVariable("SPrensaPreviene")
	protected SPrensaPreviene servicioPrensaPreviene;
	@WireVariable("SDestilo")
	protected SDestilo servicioDestilo;
	
	protected DateFormat formatoReporte = new SimpleDateFormat("dd-MM-yyyy");
	public static SimpleDateFormat formatoFecha = new SimpleDateFormat(
			"dd-MM-yyyy");
	protected static SimpleDateFormat formatoFechaRara = new SimpleDateFormat(
			"yyyyMMdd");
	public List<Tab> tabs = new ArrayList<Tab>();
	protected DateFormat df = new SimpleDateFormat("HH:mm:ss");
	public Calendar calendario = Calendar.getInstance();
	public String horaAuditoria = String.valueOf(calendario
			.get(Calendar.HOUR_OF_DAY))
			+ String.valueOf(calendario.get(Calendar.MINUTE))
			+ String.valueOf(calendario.get(Calendar.SECOND));
	public java.util.Date fecha = new Date();
	public Timestamp fechaHora = new Timestamp(fecha.getTime());
	public Mensaje msj = new Mensaje();
	public String cerrar;
	public Time tiempo = new Time(fecha.getTime());
	URL url = getClass().getResource("/controlador/maestros/ohne.png");
	private static ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
			"/META-INF/ConfiguracionAplicacion.xml");
	
	public static SEmpleado getServicioEmpleado() {
		return applicationContext.getBean(SEmpleado.class);
	}
	public static SDestilo getServicioDestilo() {
		return applicationContext.getBean(SDestilo.class);
	}
	public static SNoticia getServicioNoticia() {
		return applicationContext.getBean(SNoticia.class);
	}
	
	public Timestamp metodoFecha() {
		fecha = new Date();
		return fechaHora = new Timestamp(fecha.getTime());
	}

	public String metodoHora() {
		fecha = new Date();
		calendario.setTime(fecha);
		return String.valueOf(calendario.get(Calendar.HOUR_OF_DAY)) + ":"
				+ String.valueOf(calendario.get(Calendar.MINUTE)) + ":"
				+ String.valueOf(calendario.get(Calendar.SECOND));
	}

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		inicializar();
	}



	public abstract void inicializar() throws IOException;

	public void cerrarVentana(Div div, String id, List<Tab> tabs2) {
		div.setVisible(false);
		tabs = tabs2;
		for (int i = 0; i < tabs.size(); i++) {
			if (tabs.get(i).getLabel().equals(id)) {
				if (i == (tabs.size() - 1) && tabs.size() > 1) {
					tabs.get(i - 1).setSelected(true);
				}
				tabs.get(i).onClose();
				tabs.remove(i);
			}
		}
	}

	public String nombreUsuarioSesion() {
		Authentication sesion = SecurityContextHolder.getContext()
				.getAuthentication();
		return sesion.getName();
	}

	public boolean enviarEmailNotificacion(String correo, String mensajes) {
		try {

			String cc = "NOTIFICACION DEL SISTEMA DE PESAJE";
			Properties props = new Properties();
			props.setProperty("mail.smtp.host", "172.23.20.66");
			props.setProperty("mail.smtp.starttls.enable", "true");
			props.setProperty("mail.smtp.port", "2525");
			props.setProperty("mail.smtp.auth", "true");

			Authenticator auth = new SMTPAuthenticator();
			Session session = Session.getInstance(props, auth);
			String remitente = "cdusa@dusa.com.ve";
			String destino = correo;
			String mensaje = mensajes;
			String destinos[] = destino.split(",");
			Message message = new MimeMessage(session);

			message.setFrom(new InternetAddress(remitente));

			Address[] receptores = new Address[destinos.length];
			int j = 0;
			while (j < destinos.length) {
				receptores[j] = new InternetAddress(destinos[j]);
				j++;
			}

			message.addRecipients(Message.RecipientType.TO, receptores);
			message.setSubject(cc);
			message.setText(mensaje);

			Transport.send(message);

			return true;
		}

		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public Date traerFech(String fechaString) {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");

		Date fecha = null;
		try {
			fecha = formatter.parse(fechaString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return fecha;
	}

	public String damePath() {
		return Executions.getCurrent().getContextPath() + "/";
	}

	class SMTPAuthenticator extends javax.mail.Authenticator {
		public PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication("cdusa", "cartucho");
		}
	}

	public int obtenerDiasHabiles(Date fecha, Date fecha2) {
		Calendar calendario = Calendar.getInstance();
		calendario.setTimeZone(TimeZone.getTimeZone("GMT-4:00"));
		calendario.setTime(fecha2);
		calendario.add(Calendar.DAY_OF_YEAR, +1);
		calendario.set(Calendar.HOUR, 0);
		calendario.set(Calendar.HOUR_OF_DAY, 0);
		calendario.set(Calendar.SECOND, 0);
		calendario.set(Calendar.MILLISECOND, 0);
		calendario.set(Calendar.MINUTE, 0);
		fecha2 = calendario.getTime();
		calendario.setTime(fecha);
		String fija = formatoFecha.format(fecha2);
		String hoy = "";
		int contador = 0;
		do {
			calendario.setTime(fecha);
			if (calendario.get(Calendar.DAY_OF_WEEK) != 1
					&& calendario.get(Calendar.DAY_OF_WEEK) != 7)
				contador++;
			calendario.add(Calendar.DAY_OF_YEAR, +1);
			fecha = calendario.getTime();
			hoy = formatoFecha.format(fecha);
		} while (!hoy.equals(fija));
		return contador;
	}

	public static double round(double value, int places) {
		if (places < 0)
			throw new IllegalArgumentException();
		Double valor = value;
		BigDecimal bd = new BigDecimal(value);
		if (!valor.isNaN() && !valor.isInfinite()) {
			bd = new BigDecimal(value);
			bd = bd.setScale(places, RoundingMode.HALF_UP);
		}
		return bd.doubleValue();
	}

	public boolean validarSeleccion(Catalogo<?> catalogo) {
		List<?> seleccionados = catalogo.obtenerSeleccionados();
		if (seleccionados == null) {
			Mensaje.mensajeAlerta(Mensaje.noHayRegistros);
			return false;
		} else {
			if (seleccionados.isEmpty()) {
				Mensaje.mensajeAlerta(Mensaje.noSeleccionoItem);
				return false;
			} else {
				return true;
			}
		}
	}

	protected void mostrarBotones(Botonera botonera, boolean b) {
		botonera.getChildren().get(1).setVisible(!b);
		botonera.getChildren().get(2).setVisible(b);
		botonera.getChildren().get(6).setVisible(false);
		botonera.getChildren().get(8).setVisible(false);
		botonera.getChildren().get(0).setVisible(b);
		botonera.getChildren().get(3).setVisible(!b);
		botonera.getChildren().get(5).setVisible(!b);
	}

	public boolean validarMedia(Media media) {
		if (!media.getContentType().equals("image/jpeg")
				&& !media.getContentType().equals("image/png")) {
			Mensaje.mensajeAlerta(Mensaje.noPermitido);
			return false;
		} else {
			if (media.getByteData().length < 10) {
				Mensaje.mensajeAlerta(Mensaje.tamanioMuyPequenio);
				return false;
			} else {
				if (media.getByteData().length > 5242880) {
					Mensaje.mensajeAlerta(Mensaje.tamanioMuyGrande);
					return false;
				} else
					return true;
			}
		}
	}
	
	public Date agregarDia(Date fecha) {
		Calendar calendario = Calendar.getInstance();
		calendario.setTime(fecha);
		calendario.add(Calendar.DAY_OF_YEAR, +1);
		return fecha = calendario.getTime();
	}

}