package br.com.cwi.crescer.tcc.factories;

import br.com.cwi.crescer.tcc.domain.Curtida;

public class CurtidaFactory {

    public static Curtida get() {
        return Curtida.builder()
                .id(SimpleFactory.getRandomLong())
                .publicacao(PublicacaoFactory.getPublica())
                .usuario(UsuarioFactory.get())
                .build();
    }
}
