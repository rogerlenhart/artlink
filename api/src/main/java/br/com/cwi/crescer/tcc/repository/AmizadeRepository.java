package br.com.cwi.crescer.tcc.repository;

import br.com.cwi.crescer.tcc.domain.Amizade;
import br.com.cwi.crescer.tcc.security.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AmizadeRepository extends JpaRepository<Amizade, Long> {

    @Query("select (count(a) > 0) from Amizade a where (a.usuario.id = ?1 and a.amigo.id = ?2 or a.usuario.id = ?2 and a.amigo.id = ?1) and a.aceito = ?3")
    boolean existsByUsuarioIdAndAmigoIdAndAceito(Long usuarioId, Long amigoId, boolean aceito);

    @Query("select a from Amizade a where (a.usuario.id = ?1 and a.amigo.id = ?2 or a.usuario.id = ?2 and a.amigo.id = ?1)")
    Optional<Amizade> findByUsuarioIdAndAmigoId(Long usuarioId, Long amigoId);

    List<Amizade> findByAmigoAndAceito(Usuario usuario, boolean aceito);
}