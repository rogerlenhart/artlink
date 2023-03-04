package br.com.cwi.crescer.tcc.controller.response;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AmizadeResponse {

    private Long id;

    private UsuarioResumidoResponse usuario;

    private boolean aceito;
}
