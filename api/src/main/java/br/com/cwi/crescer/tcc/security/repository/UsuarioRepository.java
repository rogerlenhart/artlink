package br.com.cwi.crescer.tcc.security.repository;

import br.com.cwi.crescer.tcc.security.domain.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);

    boolean existsByEmail(String email);

    Page<Usuario> findByEmailContainsOrNomeContainsAllIgnoreCase(String textoEmail, String textoNome, Pageable pageable);

    @Query(value = "select amigos from Usuario amigos " +
            "where amigos.id in ( " +
                "select  " +
                "case " +
                "when us.id = ami.usuario.id then ami.amigo.id " +
                "else ami.usuario.id " +
                "end " +
                "from Usuario us " +
                "join Amizade ami on us.id = ami.usuario.id or us.id = ami.amigo.id " +
                "where us = :usuario and ami.aceito = true" +
            ") and (" +
                "upper(amigos.email) like upper(concat('%', :texto, '%')) " +
                "or " +
                "upper(amigos.nome) like upper(concat('%', :texto, '%'))" +
            ")"
    )
    Page<Usuario> findByAmigos(@Param("usuario") Usuario usuario, Pageable pageable, @Param("texto") String texto);
}
