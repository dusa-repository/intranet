package interfacedao.maestros;

import modelo.maestros.Documento;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IDocumentoDAO extends JpaRepository<Documento, Long> {

}
