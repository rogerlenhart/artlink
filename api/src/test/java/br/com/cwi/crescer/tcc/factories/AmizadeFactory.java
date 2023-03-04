package br.com.cwi.crescer.tcc.factories;

import br.com.cwi.crescer.tcc.domain.Amizade;

public class AmizadeFactory {

    public static Amizade.AmizadeBuilder getBuilder() {
        return Amizade.builder()
                .id(SimpleFactory.getRandomLong())
                .usuario(UsuarioFactory.get())
                .amigo(UsuarioFactory.get());
    }

    public static Amizade getAceito() {
        return getBuilder().aceito(true).build();
    }

    public static Amizade getPendente() {
        return getBuilder().aceito(false).build();
    }
}
