package modelo.maestros;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "empleado")
@NamedQuery(name = "Empleado.findAll", query = "SELECT t FROM Empleado t")
public class Empleado implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_empleado")
	private long idEmpleado;

	@Column(length = 250)
	private String ficha;

	@Column(length = 250)
	private String nombre;

	@Column(length = 250)
	private String apellido;

	@Column(length = 500)
	private String direccion;

	@Column(name = "telefono_fijo", length = 250)
	private String telefonoFijo;

	@Column(name = "telefono_celular", length = 250)
	private String telefonoCelular;

	@Lob
	private byte[] imagen;

	@Column(name = "fecha_nacimiento")
	private Timestamp fechaNacimiento;

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

	public Empleado() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Empleado(long idEmpleado, String cedula, String nombre,
			String apellido, String direccion, String telefonoFijo,
			String telefonoCelular, byte[] imagen, Timestamp fechaNacimiento,
			Timestamp fechaAuditoria, String horaAuditoria,
			String usuarioAuditoria, String futuro1, String futuro2) {
		super();
		this.idEmpleado = idEmpleado;
		this.ficha = cedula;
		this.nombre = nombre;
		this.apellido = apellido;
		this.direccion = direccion;
		this.telefonoFijo = telefonoFijo;
		this.telefonoCelular = telefonoCelular;
		this.imagen = imagen;
		this.fechaNacimiento = fechaNacimiento;
		this.fechaAuditoria = fechaAuditoria;
		this.horaAuditoria = horaAuditoria;
		this.usuarioAuditoria = usuarioAuditoria;
		this.futuro1 = futuro1;
		this.futuro2 = futuro2;
	}

	public long getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(long idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public String getFicha() {
		return ficha;
	}

	public void setFicha(String ficha) {
		this.ficha = ficha;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefonoFijo() {
		return telefonoFijo;
	}

	public void setTelefonoFijo(String telefonoFijo) {
		this.telefonoFijo = telefonoFijo;
	}

	public String getTelefonoCelular() {
		return telefonoCelular;
	}

	public void setTelefonoCelular(String telefonoCelular) {
		this.telefonoCelular = telefonoCelular;
	}

	public byte[] getImagen() {
		return imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}

	public Timestamp getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Timestamp fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
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
				+ ((apellido == null) ? 0 : apellido.hashCode());
		result = prime * result + ((ficha == null) ? 0 : ficha.hashCode());
		result = prime * result
				+ ((direccion == null) ? 0 : direccion.hashCode());
		result = prime * result
				+ ((fechaAuditoria == null) ? 0 : fechaAuditoria.hashCode());
		result = prime * result
				+ ((fechaNacimiento == null) ? 0 : fechaNacimiento.hashCode());
		result = prime * result + ((futuro1 == null) ? 0 : futuro1.hashCode());
		result = prime * result + ((futuro2 == null) ? 0 : futuro2.hashCode());
		result = prime * result
				+ ((horaAuditoria == null) ? 0 : horaAuditoria.hashCode());
		result = prime * result + (int) (idEmpleado ^ (idEmpleado >>> 32));
		result = prime * result + Arrays.hashCode(imagen);
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result
				+ ((telefonoCelular == null) ? 0 : telefonoCelular.hashCode());
		result = prime * result
				+ ((telefonoFijo == null) ? 0 : telefonoFijo.hashCode());
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
		Empleado other = (Empleado) obj;
		if (apellido == null) {
			if (other.apellido != null)
				return false;
		} else if (!apellido.equals(other.apellido))
			return false;
		if (ficha == null) {
			if (other.ficha != null)
				return false;
		} else if (!ficha.equals(other.ficha))
			return false;
		if (direccion == null) {
			if (other.direccion != null)
				return false;
		} else if (!direccion.equals(other.direccion))
			return false;
		if (fechaAuditoria == null) {
			if (other.fechaAuditoria != null)
				return false;
		} else if (!fechaAuditoria.equals(other.fechaAuditoria))
			return false;
		if (fechaNacimiento == null) {
			if (other.fechaNacimiento != null)
				return false;
		} else if (!fechaNacimiento.equals(other.fechaNacimiento))
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
		if (idEmpleado != other.idEmpleado)
			return false;
		if (!Arrays.equals(imagen, other.imagen))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (telefonoCelular == null) {
			if (other.telefonoCelular != null)
				return false;
		} else if (!telefonoCelular.equals(other.telefonoCelular))
			return false;
		if (telefonoFijo == null) {
			if (other.telefonoFijo != null)
				return false;
		} else if (!telefonoFijo.equals(other.telefonoFijo))
			return false;
		if (usuarioAuditoria == null) {
			if (other.usuarioAuditoria != null)
				return false;
		} else if (!usuarioAuditoria.equals(other.usuarioAuditoria))
			return false;
		return true;
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

	public String fechaCumple() {
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy");
		return formatoFecha.format(fechaNacimiento);
	}

}
