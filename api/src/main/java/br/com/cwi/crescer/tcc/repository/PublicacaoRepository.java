package br.com.cwi.crescer.tcc.repository;

import br.com.cwi.crescer.tcc.domain.Publicacao;
import br.com.cwi.crescer.tcc.security.domain.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PublicacaoRepository extends JpaRepository<Publicacao, Long> {

    @Query(value = "select pub from Publicacao pub " +
            "where pub.autor = :usuario or " +
            "pub.autor.id in ( " +
                "select  " +
                "case " +
                "when us.id = ami.usuario.id then ami.amigo.id " +
                "else ami.usuario.id " +
                "end " +
                "from Usuario us " +
                "join Amizade ami on us.id = ami.usuario.id or us.id = ami.amigo.id " +
                "where us = :usuario and ami.aceito = true" +
            ")"
    )
    Page<Publicacao> findByPaginaInicial(@Param("usuario") Usuario usuario, Pageable pageable);
}