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
@Table(name = "produccion")
@NamedQuery(name = "Produccion.findAll", query = "SELECT t FROM Produccion t")
public class Produccion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_produccion")
	private long idProduccion;

	@Column(length = 250)
	private String empresa;

	@Column(length = 250)
	private String tipo;

	@Column(name = "diaria")
	private Double diaria;

	@Column(name = "acumulada")
	private Double acumulada;

	@Column(name = "planificada")
	private Double planificada;

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

	public Produccion() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Produccion(long idProduccion, String empresa, String tipo,
			Double diaria, Double acumulada, Double planificada,
			Timestamp fechaAuditoria, String horaAuditoria,
			String usuarioAuditoria, String futuro1, String futuro2) {
		super();
		this.idProduccion = idProduccion;
		this.empresa = empresa;
		this.tipo = tipo;
		this.diaria = diaria;
		this.acumulada = acumulada;
		this.planificada = planificada;
		this.fechaAuditoria = fechaAuditoria;
		this.horaAuditoria = horaAuditoria;
		this.usuarioAuditoria = usuarioAuditoria;
		this.futuro1 = futuro1;
		this.futuro2 = futuro2;
	}

	public long getIdProduccion() {
		return idProduccion;
	}

	public void setIdProduccion(long idProduccion) {
		this.idProduccion = idProduccion;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Double getDiaria() {
		return diaria;
	}

	public void setDiaria(Double diaria) {
		this.diaria = diaria;
	}

	public Double getAcumulada() {
		return acumulada;
	}

	public void setAcumulada(Double acumulada) {
		this.acumulada = acumulada;
	}

	public Double getPlanificada() {
		return planificada;
	}

	public void setPlanificada(Double planificada) {
		this.planificada = planificada;
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
				+ ((acumulada == null) ? 0 : acumulada.hashCode());
		result = prime * result + ((diaria == null) ? 0 : diaria.hashCode());
		result = prime * result + ((empresa == null) ? 0 : empresa.hashCode());
		result = prime * result
				+ ((fechaAuditoria == null) ? 0 : fechaAuditoria.hashCode());
		result = prime * result + ((futuro1 == null) ? 0 : futuro1.hashCode());
		result = prime * result + ((futuro2 == null) ? 0 : futuro2.hashCode());
		result = prime * result
				+ ((horaAuditoria == null) ? 0 : horaAuditoria.hashCode());
		result = prime * result + (int) (idProduccion ^ (idProduccion >>> 32));
		result = prime * result
				+ ((planificada == null) ? 0 : planificada.hashCode());
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
		Produccion other = (Produccion) obj;
		if (acumulada == null) {
			if (other.acumulada != null)
				return false;
		} else if (!acumulada.equals(other.acumulada))
			return false;
		if (diaria == null) {
			if (other.diaria != null)
				return false;
		} else if (!diaria.equals(other.diaria))
			return false;
		if (empresa == null) {
			if (other.empresa != null)
				return false;
		} else if (!empresa.equals(other.empresa))
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
		if (idProduccion != other.idProduccion)
			return false;
		if (planificada == null) {
			if (other.planificada != null)
				return false;
		} else if (!planificada.equals(other.planificada))
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

	public String porcentaje() {
		try {
			return String.valueOf(acumulada / planificada * 100);
		} catch (Exception e) {
			return "";
		}
	}
	
}
