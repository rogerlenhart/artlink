package br.com.cwi.crescer.tcc.controller.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PublicacaoDetalhadaResponse {

    private Long id;

    private UsuarioResumidoResponse autor;

    private String titulo;

    private String descricao;

    private String imagem;

    private LocalDateTime dataCriacao;

    private boolean privado;

    @Builder.Default
    private List<ComentarioResponse> comentarios = new ArrayList<>();

    private int curtidas;

    private int numeroComentarios;

    private boolean foiCurtido;
}
