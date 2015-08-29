package modelo.maestros;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
@Entity
@Table(name = "enlace")
@NamedQuery(name = "Enlace.findAll", query = "SELECT t FROM Enlace t")
public class Enlace implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_enlace")
	private long idEnlace;

	@Column(length = 250)
	private String nombre;

	@Column(length = 500)
	private String descripcion;
	
	@Column(length = 250)
	private String url;

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

	public Enlace() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Enlace(long idProducto, String nombre, String descripcion,
			String url, Timestamp fechaAuditoria, String horaAuditoria,
			String usuarioAuditoria, String futuro1, String futuro2) {
		super();
		this.idEnlace = idProducto;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.url = url;
		this.fechaAuditoria = fechaAuditoria;
		this.horaAuditoria = horaAuditoria;
		this.usuarioAuditoria = usuarioAuditoria;
		this.futuro1 = futuro1;
		this.futuro2 = futuro2;
	}

	public long getIdEnlace() {
		return idEnlace;
	}

	public void setIdEnlace(long idProducto) {
		this.idEnlace = idProducto;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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
		result = prime * result
				+ ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result
				+ ((fechaAuditoria == null) ? 0 : fechaAuditoria.hashCode());
		result = prime * result + ((futuro1 == null) ? 0 : futuro1.hashCode());
		result = prime * result + ((futuro2 == null) ? 0 : futuro2.hashCode());
		result = prime * result
				+ ((horaAuditoria == null) ? 0 : horaAuditoria.hashCode());
		result = prime * result + (int) (idEnlace ^ (idEnlace >>> 32));
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
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
		Enlace other = (Enlace) obj;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
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
		if (idEnlace != other.idEnlace)
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		if (usuarioAuditoria == null) {
			if (other.usuarioAuditoria != null)
				return false;
		} else if (!usuarioAuditoria.equals(other.usuarioAuditoria))
			return false;
		return true;
	}

}
