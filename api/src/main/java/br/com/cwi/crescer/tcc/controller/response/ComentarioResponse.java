package br.com.cwi.crescer.tcc.controller.response;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ComentarioResponse {

    private Long id;

    private UsuarioResumidoResponse autor;

    private String mensagem;

    private LocalDateTime dataCriacao;
}
