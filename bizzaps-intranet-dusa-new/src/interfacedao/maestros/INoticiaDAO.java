package interfacedao.maestros;

import java.util.List;

import modelo.maestros.Noticia;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface INoticiaDAO extends JpaRepository<Noticia, Long> {

	List<Noticia> findByIdNoticiaGreaterThan(long l, Pageable topTen);

}
