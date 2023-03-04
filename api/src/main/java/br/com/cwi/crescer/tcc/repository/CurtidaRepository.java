package br.com.cwi.crescer.tcc.repository;

import br.com.cwi.crescer.tcc.domain.Curtida;
import br.com.cwi.crescer.tcc.domain.Publicacao;
import br.com.cwi.crescer.tcc.security.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CurtidaRepository extends JpaRepository<Curtida, Long> {

    boolean existsByPublicacaoAndUsuario(Publicacao publicacao, Usuario usuario);

    Optional<Curtida> findByPublicacaoAndUsuario(Publicacao publicacao, Usuario usuario);
}