package br.com.cwi.crescer.tcc.controller.response;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsuarioResumidoResponse {

    private Long id;

    private String nome;

    private String imagemPerfil;

    private String apelido;
}
