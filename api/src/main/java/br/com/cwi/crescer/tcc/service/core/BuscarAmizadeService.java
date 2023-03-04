package br.com.cwi.crescer.tcc.service.core;

import br.com.cwi.crescer.tcc.domain.Amizade;
import br.com.cwi.crescer.tcc.repository.AmizadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class BuscarAmizadeService {

    private static final String NOT_FOUND_MESSAGE = "Amizade não encontrada.";

    @Autowired
    private AmizadeRepository amizadeRepository;

    public Amizade porId(Long id) {
        return amizadeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, NOT_FOUND_MESSAGE));
    }
}
