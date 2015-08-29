package interfacedao.maestros;

import modelo.maestros.Noticia;

import org.springframework.data.jpa.repository.JpaRepository;

public interface INoticiaDAO extends JpaRepository<Noticia, Long> {

}
