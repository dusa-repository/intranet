package modelo.maestros;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;
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

import org.zkoss.image.AImage;

@Entity
@Table(name = "producto")
@NamedQuery(name = "Producto.findAll", query = "SELECT t FROM Producto t")
public class Producto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pruducto")
	private long idProducto;

	@Column(length = 250)
	private String nombre;

	@Column(length = 500)
	private String descripcion;

	@Lob
	private byte[] imagen;

	@Column(name = "cantidad")
	private Integer cantidad;

	@Column(name = "precio")
	private Double precio;

	@Column(length = 250)
	private String futuro1;

	@Column(length = 250)
	private String futuro2;

	public Producto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Producto(long idProducto, String nombre, String descripcion,
			byte[] imagen, Integer cantidad, Double precio, String futuro1,
			String futuro2) {
		super();
		this.idProducto = idProducto;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.imagen = imagen;
		this.cantidad = cantidad;
		this.precio = precio;
		this.futuro1 = futuro1;
		this.futuro2 = futuro2;
	}

	public long getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(long idProducto) {
		this.idProducto = idProducto;
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

	public byte[] getImagen() {
		return imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
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
				+ ((cantidad == null) ? 0 : cantidad.hashCode());
		result = prime * result
				+ ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result + ((futuro1 == null) ? 0 : futuro1.hashCode());
		result = prime * result + ((futuro2 == null) ? 0 : futuro2.hashCode());
		result = prime * result + (int) (idProducto ^ (idProducto >>> 32));
		result = prime * result + Arrays.hashCode(imagen);
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((precio == null) ? 0 : precio.hashCode());
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
		Producto other = (Producto) obj;
		if (cantidad == null) {
			if (other.cantidad != null)
				return false;
		} else if (!cantidad.equals(other.cantidad))
			return false;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
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
		if (idProducto != other.idProducto)
			return false;
		if (!Arrays.equals(imagen, other.imagen))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (precio == null) {
			if (other.precio != null)
				return false;
		} else if (!precio.equals(other.precio))
			return false;
		return true;
	}

	public BufferedImage traerImagen() {
		BufferedImage imag;
		try {
			imag = ImageIO.read(new ByteArrayInputStream(imagen));
			return imag;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}
