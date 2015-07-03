package modelo.portal;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;

import javax.persistence.Table;
@Entity
@Table(name="carrusel")
@NamedQuery(name="Carrusel.findAll", query="SELECT t FROM Carrusel t")
public class Carrusel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_carrusel")
	private long idCarrusel;

	private String nombre;
	
	@Lob
	private byte[] imagen;
	
	private String tipo;

	public Carrusel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getIdCarrusel() {
		return idCarrusel;
	}

	public void setIdCarrusel(long idCarrusel) {
		this.idCarrusel = idCarrusel;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public byte[] getImagen() {
		return imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}
	
	
}