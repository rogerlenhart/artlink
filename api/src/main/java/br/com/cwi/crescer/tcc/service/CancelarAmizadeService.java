package br.com.cwi.crescer.tcc.service;

import br.com.cwi.crescer.tcc.domain.Amizade;
import br.com.cwi.crescer.tcc.repository.AmizadeRepository;
import br.com.cwi.crescer.tcc.security.domain.Usuario;
import br.com.cwi.crescer.tcc.security.service.UsuarioAutenticadoService;
import br.com.cwi.crescer.tcc.service.core.BuscarAmizadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
public class CancelarAmizadeService {

    @Autowired
    private BuscarAmizadeService buscarAmizadeService;

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Autowired
    private AmizadeRepository amizadeRepository;

    public void cancelar(Long id) {
        Usuario usuario = usuarioAutenticadoService.get();

        Amizade amizade = amizadeRepository.findByUsuarioIdAndAmigoId(usuario.getId(), id)
                .orElseThrow(() -> new ResponseStatusException(BAD_REQUEST, "Você não possui um vínculo de amizade com este usuário."));

        amizadeRepository.delete(amizade);
    }
}
