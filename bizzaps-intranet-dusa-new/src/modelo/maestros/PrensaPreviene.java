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
@Table(name = "prensa_previene")
@NamedQuery(name = "PrensaPreviene.findAll", query = "SELECT t FROM PrensaPreviene t")
public class PrensaPreviene implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	private long id;

	@Column(length = 500,name = "descripcion_prensa")
	private String descripcionPrensa;

	@Column(length = 250,name = "tipo_prensa")
	private String tipoPrensa;

	@Lob
	@Column(name = "contenido_prensa")
	private byte[] contenidoPrensa;
	
	@Column(length = 500,name = "descripcion_previene")
	private String descripcionPreviene;

	@Column(length = 250,name = "tipo_previene")
	private String tipoPreviene;

	@Lob
	@Column(name = "contenido_previene")
	private byte[] contenidoPreviene;

	@Column(name = "fecha_auditoria")
	private Timestamp fechaAuditoria;

	@Column(name = "hora_auditoria", length = 10)
	private String horaAuditoria;

	@Column(name = "usuario_auditoria", length = 50)
	private String usuarioAuditoria;

	public PrensaPreviene(long id, String descripcionPrensa, String tipoPrensa,
			byte[] contenidoPrensa, String descripcionPreviene,
			String tipoPreviene, byte[] contenidoPreviene,
			Timestamp fechaAuditoria, String horaAuditoria,
			String usuarioAuditoria) {
		super();
		this.id = id;
		this.descripcionPrensa = descripcionPrensa;
		this.tipoPrensa = tipoPrensa;
		this.contenidoPrensa = contenidoPrensa;
		this.descripcionPreviene = descripcionPreviene;
		this.tipoPreviene = tipoPreviene;
		this.contenidoPreviene = contenidoPreviene;
		this.fechaAuditoria = fechaAuditoria;
		this.horaAuditoria = horaAuditoria;
		this.usuarioAuditoria = usuarioAuditoria;
	}

	public PrensaPreviene() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescripcionPrensa() {
		return descripcionPrensa;
	}

	public void setDescripcionPrensa(String descripcionPrensa) {
		this.descripcionPrensa = descripcionPrensa;
	}

	public String getTipoPrensa() {
		return tipoPrensa;
	}

	public void setTipoPrensa(String tipoPrensa) {
		this.tipoPrensa = tipoPrensa;
	}

	public byte[] getContenidoPrensa() {
		return contenidoPrensa;
	}

	public void setContenidoPrensa(byte[] contenidoPrensa) {
		this.contenidoPrensa = contenidoPrensa;
	}

	public String getDescripcionPreviene() {
		return descripcionPreviene;
	}

	public void setDescripcionPreviene(String descripcionPreviene) {
		this.descripcionPreviene = descripcionPreviene;
	}

	public String getTipoPreviene() {
		return tipoPreviene;
	}

	public void setTipoPreviene(String tipoPreviene) {
		this.tipoPreviene = tipoPreviene;
	}

	public byte[] getContenidoPreviene() {
		return contenidoPreviene;
	}

	public void setContenidoPreviene(byte[] contenidoPreviene) {
		this.contenidoPreviene = contenidoPreviene;
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
