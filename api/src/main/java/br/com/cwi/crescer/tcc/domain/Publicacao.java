package br.com.cwi.crescer.tcc.domain;

import br.com.cwi.crescer.tcc.security.domain.Usuario;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
public class Publicacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "autor_id", nullable = false)
    private Usuario autor;

    private String titulo;

    private String descricao;

    private String imagem;

    private LocalDateTime dataCriacao;

    private boolean privado;

    @OneToMany(mappedBy = "publicacao")
    private List<Comentario> comentarios = new ArrayList<>();

    @OneToMany(mappedBy = "publicacao")
    private List<Curtida> curtidas = new ArrayList<>();

    public int getNumeroCurtidas() {
        return curtidas.size();
    }

    public int getNumeroComentarios() {
        return comentarios.size();
    }

    public boolean foiCurtidoPorUsuario(Usuario usuario) {
        Long usuarioId = usuario.getId();
        return curtidas.stream()
                .anyMatch(curtida -> curtida.getUsuario().getId().equals(usuarioId));
    }

}