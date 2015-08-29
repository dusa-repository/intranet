package modelo.maestros;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
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
	@Column(name = "id_carrusel")
	private long idCarrusel;

	@Column(length = 500)
	private String nombre;
	
	@Lob
	private byte[] imagen;


	public Carrusel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Carrusel(long idCarrusel, String nombre, byte[] imagen) {
		super();
		this.idCarrusel = idCarrusel;
		this.nombre = nombre;
		this.imagen = imagen;
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