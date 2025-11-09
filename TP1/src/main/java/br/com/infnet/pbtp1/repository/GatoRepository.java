package br.com.infnet.pbtp1.repository;

import br.com.infnet.pbtp1.entity.Gato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GatoRepository extends JpaRepository<Gato, Long> {
}
