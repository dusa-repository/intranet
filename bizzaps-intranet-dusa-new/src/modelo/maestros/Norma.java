package modelo.maestros;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="norma")
@NamedQuery(name="Norma.findAll", query="SELECT t FROM Norma t")
public class Norma implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_norma")
	private long idNorma;

	@Column(length = 250,name = "titulo_primera" )
	private String tituloPrimera;
	
	@Lob
	@Column(name = "imagen_primera" )
	private byte[] imagenPrimera;
	
	@Column(length = 5000,name = "texto_primera")
	private String textoPrimera;
	
	
	@Column(length = 250,name = "titulo_segunda")
	private String tituloSegunda;
	
	@Lob
	@Column(name = "imagen_segunda")
	private byte[] imagenSegunda;
	
	@Column(length = 5000,name = "texto_segunda")
	private String textoSegunda;
	
	
	@Column(name = "fecha_auditoria")
	private Timestamp fechaAuditoria;

	@Column(name = "hora_auditoria", length = 10)
	private String horaAuditoria;

	@Column(name = "usuario_auditoria", length = 50)
	private String usuarioAuditoria;

	public Norma() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getIdNorma() {
		return idNorma;
	}

	public void setIdNorma(long idNorma) {
		this.idNorma = idNorma;
	}

	public String getTituloPrimera() {
		return tituloPrimera;
	}

	public void setTituloPrimera(String titulo_primera) {
		this.tituloPrimera = titulo_primera;
	}

	public byte[] getImagenPrimera() {
		return imagenPrimera;
	}

	public void setImagenPrimera(byte[] imagen_primera) {
		this.imagenPrimera = imagen_primera;
	}

	public String getTextoPrimera() {
		return textoPrimera;
	}

	public void setTextoPrimera(String texto_primera) {
		this.textoPrimera = texto_primera;
	}

	public String getTituloSegunda() {
		return tituloSegunda;
	}

	public void setTituloSegunda(String titulo_segunda) {
		this.tituloSegunda = titulo_segunda;
	}

	public byte[] getImagenSegunda() {
		return imagenSegunda;
	}

	public void setImagenSegunda(byte[] imagen_segunda) {
		this.imagenSegunda = imagen_segunda;
	}

	public String getTextoSegunda() {
		return textoSegunda;
	}

	public void setTextoSegunda(String texto_segunda) {
		this.textoSegunda = texto_segunda;
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
	
	

}
