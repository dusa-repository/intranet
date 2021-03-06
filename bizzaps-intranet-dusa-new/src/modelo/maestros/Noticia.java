package modelo.maestros;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.imageio.ImageIO;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import controlador.maestros.CGenerico;

@Entity
@Table(name = "noticia")
@NamedQuery(name = "Noticia.findAll", query = "SELECT t FROM Noticia t")
public class Noticia implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_noticia")
	private long idNoticia;

	@Column(length = 250)
	private String titulo;

	@Lob
	private byte[] imagen;

	@Column(length = 5000)
	private String texto;

	@Column(name = "fecha")
	private Timestamp fecha;

	@Column(name = "fecha_auditoria")
	private Timestamp fechaAuditoria;

	@Column(name = "hora_auditoria", length = 10)
	private String horaAuditoria;

	@Column(name = "usuario_auditoria", length = 50)
	private String usuarioAuditoria;

	public Noticia() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Noticia(long idNoticia, String titulo, byte[] imagen, String texto,
			Timestamp fecha, Timestamp fechaAuditoria, String horaAuditoria,
			String usuarioAuditoria) {
		super();
		this.idNoticia = idNoticia;
		this.titulo = titulo;
		this.imagen = imagen;
		this.texto = texto;
		this.fecha = fecha;
		this.fechaAuditoria = fechaAuditoria;
		this.horaAuditoria = horaAuditoria;
		this.usuarioAuditoria = usuarioAuditoria;
	}

	public long getIdNoticia() {
		return idNoticia;
	}

	public void setIdNoticia(long idNoticia) {
		this.idNoticia = idNoticia;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public byte[] getImagen() {
		return imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Timestamp getFecha() {
		return fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public Timestamp getFechaAuditoria() {
		return fechaAuditoria;
	}

	public void setFechaAuditoria(Timestamp fechaAuditoria) {
		this.fechaAuditoria = fechaAuditoria;
	}

	public String getHoraAuditoria() {
		return horaAuditoria;
	}

	public void setHoraAuditoria(String horaAuditoria) {
		this.horaAuditoria = horaAuditoria;
	}

	public String getUsuarioAuditoria() {
		return usuarioAuditoria;
	}

	public void setUsuarioAuditoria(String usuarioAuditoria) {
		this.usuarioAuditoria = usuarioAuditoria;
	}

	public BufferedImage traerImagen() {
		BufferedImage imag;
		try {
			if (imagen == null) {
				URL url = getClass().getResource(
						"/controlador/maestros/ohne.png");
				return imag = ImageIO.read(url);
			}
			imag = ImageIO.read(new ByteArrayInputStream(imagen));
			return imag;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public String fechaFormato() {
		return CGenerico.formatoFecha.format(fecha);
	}

	public String truncar() {
		if (texto != null) {
			if (texto.length() <= 400)
				return texto;
			else
				for (int i = 400; i < texto.length(); i++)
					if (texto.charAt(i) == ' ')
						return texto.substring(0, i);
		}
		return "";
	}

}
