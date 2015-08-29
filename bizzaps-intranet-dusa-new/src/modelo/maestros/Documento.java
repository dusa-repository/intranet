package modelo.maestros;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "documento")
@NamedQuery(name = "Documento.findAll", query = "SELECT t FROM Documento t")
public class Documento implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_documento")
	private long idDocumento;

	@Column(length = 250)
	private String nombre;

	@Column(length = 250)
	private String descripcionDocumento;

	@Column(length = 500)
	private String descripcion;

	@Column(length = 250)
	private String tipo;

	@Lob
	private byte[] contenido;

	@Column(name = "fecha_auditoria")
	private Timestamp fechaAuditoria;

	@Column(name = "hora_auditoria", length = 10)
	private String horaAuditoria;

	@Column(name = "usuario_auditoria", length = 50)
	private String usuarioAuditoria;

	@Column(length = 250)
	private String futuro1;

	@Column(length = 250)
	private String futuro2;

	public Documento() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Documento(long idEnlace, String nombre, String descripcion,
			String descripcionDocumento, String tipo, byte[] contenido,
			Timestamp fechaAuditoria, String horaAuditoria,
			String usuarioAuditoria, String futuro1, String futuro2) {
		super();
		this.idDocumento = idEnlace;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.descripcionDocumento = descripcionDocumento;
		this.tipo = tipo;
		this.contenido = contenido;
		this.fechaAuditoria = fechaAuditoria;
		this.horaAuditoria = horaAuditoria;
		this.usuarioAuditoria = usuarioAuditoria;
		this.futuro1 = futuro1;
		this.futuro2 = futuro2;
	}

	public long getIdDocumento() {
		return idDocumento;
	}

	public void setIdDocumento(long idEnlace) {
		this.idDocumento = idEnlace;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcionDocumento() {
		return descripcionDocumento;
	}

	public void setDescripcionDocumento(String descripcionDocumento) {
		this.descripcionDocumento = descripcionDocumento;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public byte[] getContenido() {
		return contenido;
	}

	public void setContenido(byte[] contenido) {
		this.contenido = contenido;
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

	public String getFuturo1() {
		return futuro1;
	}

	public void setFuturo1(String futuro1) {
		this.futuro1 = futuro1;
	}

	public String getFuturo2() {
		return futuro2;
	}

	public void setFuturo2(String futuro2) {
		this.futuro2 = futuro2;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(contenido);
		result = prime * result
				+ ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime
				* result
				+ ((descripcionDocumento == null) ? 0 : descripcionDocumento
						.hashCode());
		result = prime * result
				+ ((fechaAuditoria == null) ? 0 : fechaAuditoria.hashCode());
		result = prime * result + ((futuro1 == null) ? 0 : futuro1.hashCode());
		result = prime * result + ((futuro2 == null) ? 0 : futuro2.hashCode());
		result = prime * result
				+ ((horaAuditoria == null) ? 0 : horaAuditoria.hashCode());
		result = prime * result + (int) (idDocumento ^ (idDocumento >>> 32));
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		result = prime
				* result
				+ ((usuarioAuditoria == null) ? 0 : usuarioAuditoria.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Documento other = (Documento) obj;
		if (!Arrays.equals(contenido, other.contenido))
			return false;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (descripcionDocumento == null) {
			if (other.descripcionDocumento != null)
				return false;
		} else if (!descripcionDocumento.equals(other.descripcionDocumento))
			return false;
		if (fechaAuditoria == null) {
			if (other.fechaAuditoria != null)
				return false;
		} else if (!fechaAuditoria.equals(other.fechaAuditoria))
			return false;
		if (futuro1 == null) {
			if (other.futuro1 != null)
				return false;
		} else if (!futuro1.equals(other.futuro1))
			return false;
		if (futuro2 == null) {
			if (other.futuro2 != null)
				return false;
		} else if (!futuro2.equals(other.futuro2))
			return false;
		if (horaAuditoria == null) {
			if (other.horaAuditoria != null)
				return false;
		} else if (!horaAuditoria.equals(other.horaAuditoria))
			return false;
		if (idDocumento != other.idDocumento)
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		if (usuarioAuditoria == null) {
			if (other.usuarioAuditoria != null)
				return false;
		} else if (!usuarioAuditoria.equals(other.usuarioAuditoria))
			return false;
		return true;
	}

	public String nombreReal() {
		switch (tipo) {
		case "application/pdf":
			return "PDF";
		case "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet":
			return "EXCEL";
		case "application/vnd.openxmlformats-officedocument.wordprocessingml.document":
			return "WORD";
		case "application/vnd.ms-excel":
			return "EXCEL(97-03)";
		case "application/msword":
			return "WORD(97-03)";
		default:
			return "N/A";
		}
	}

}
