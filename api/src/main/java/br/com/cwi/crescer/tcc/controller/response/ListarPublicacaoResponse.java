package br.com.cwi.crescer.tcc.controller.response;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ListarPublicacaoResponse {

    private Long id;

    private String titulo;

    private String descricao;

    private String imagem;

    private LocalDateTime dataCriacao;

    private UsuarioResumidoResponse autor;
}
