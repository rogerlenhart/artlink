package br.com.cwi.crescer.tcc.service.core;

import br.com.cwi.crescer.tcc.domain.Amizade;
import br.com.cwi.crescer.tcc.repository.AmizadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;
import java.util.Optional;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
public class ValidaPodeSolicitarAmizadeService {
    private static final String SI_MESMO_MESSAGE = "Um usuário não pode ser amigo de si mesmo.";
    private static final String JA_EXISTE_AMIZADE_MESSAGE = "Você já é amigo deste usuário.";
    private static final String JA_EXISTE_SOLICITACAO_MESSAGE = "Já existe uma solicitação pendente envolvendo este usuário.";

    @Autowired
    private AmizadeRepository amizadeRepository;

    public void validar(Long usuarioId, Long amigoId) {
        if(Objects.equals(usuarioId, amigoId)) {
            throw new ResponseStatusException(BAD_REQUEST, SI_MESMO_MESSAGE);
        }

        Optional<Amizade> optionalAmizade = amizadeRepository.findByUsuarioIdAndAmigoId(usuarioId, amigoId);

        if(optionalAmizade.isEmpty()) return;

        Amizade amizade = optionalAmizade.get();

        if(amizade.isAceito()) {
            throw new ResponseStatusException(BAD_REQUEST, JA_EXISTE_AMIZADE_MESSAGE);
        } else {
            throw new ResponseStatusException(BAD_REQUEST, JA_EXISTE_SOLICITACAO_MESSAGE);
        }
    }
}
