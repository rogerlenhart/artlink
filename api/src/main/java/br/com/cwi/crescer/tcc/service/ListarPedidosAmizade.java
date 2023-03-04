package br.com.cwi.crescer.tcc.service;

import br.com.cwi.crescer.tcc.controller.response.AmizadeResponse;
import br.com.cwi.crescer.tcc.mapper.AmizadeMapper;
import br.com.cwi.crescer.tcc.repository.AmizadeRepository;
import br.com.cwi.crescer.tcc.security.domain.Usuario;
import br.com.cwi.crescer.tcc.security.service.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListarPedidosAmizade {

    @Autowired
    private AmizadeRepository amizadeRepository;

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    public List<AmizadeResponse> listar() {
        Usuario usuario = usuarioAutenticadoService.get();

        return amizadeRepository.findByAmigoAndAceito(usuario, false).stream()
                .map(AmizadeMapper::toResponse)
                .collect(Collectors.toList());
    }
}
