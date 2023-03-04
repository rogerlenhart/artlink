package br.com.cwi.crescer.tcc.service.core;

import br.com.cwi.crescer.tcc.domain.Publicacao;
import br.com.cwi.crescer.tcc.repository.PublicacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class BuscarPublicacaoService {

    private static final String NOT_FOUND_MESSAGE = "Publicação não encontrada.";

    @Autowired
    private PublicacaoRepository publicacaoRepository;

    public Publicacao porId(Long id) {
        return publicacaoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, NOT_FOUND_MESSAGE));
    }
}
