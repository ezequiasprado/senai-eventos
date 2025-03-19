package com.evento.services;

import com.evento.dtos.EnderecoDTO;
import com.evento.dtos.EventoDTO;
import com.evento.dtos.ProdutoraDTO;
import com.evento.exceptions.BussinesException;
import com.evento.models.Evento;
import com.evento.repositories.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventoService {

    private static final String MSG_EVENTO = "Evento não encontrado";

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private ProdutoraService produtoraService;

    public EventoDTO cadastrarEvento(EventoDTO eventoDTO){
        Evento evento = converterEventoDTOParaEvento(eventoDTO);
        evento = eventoRepository.save(evento);
        return converterEventoParaEventoDTO(evento);
    }

    public EventoDTO converterEventoParaEventoDTO(Evento evento){
        EnderecoDTO enderecoDTO = enderecoService.
                converterEnderecoParaEnderecoDTO(evento.getEndereco());

        ProdutoraDTO produtoraDTO = produtoraService.
                converterProdutoraParaProdutoraDTO(evento.getProdutora());

        EventoDTO eventoDTO = new EventoDTO(evento.getId(),
                evento.getData(),
                evento.getDescricao(),
                evento.getClassificacao(),
                evento.getHoraInicio(),
                evento.getHoraFim(),
                evento.getHoraAbertura(),
                enderecoDTO,
                produtoraDTO);
        return eventoDTO;
    }

    public Evento converterEventoDTOParaEvento(EventoDTO eventoDTO){
        Evento evento = new Evento(eventoDTO.getId(),
                eventoDTO.getData(),
                eventoDTO.getDescricao(),
                eventoDTO.getClassificacao(),
                eventoDTO.getHoraInicio(),
                eventoDTO.getHoraFim(),
                eventoDTO.getHoraAbertura(),
                enderecoService.converterEnderecoDTOParaEndereco(eventoDTO.getEndereco()),
                produtoraService.converterProdutoraDTOParaProdutora(eventoDTO.getProdutora()));
        return evento;
    }

    public void deletarEvento(Long id){
        eventoRepository.deleteById(id);
    }

    public EventoDTO buscarEventoPorId(Long id){
        Evento evento = eventoRepository.findById(id)
                .orElseThrow(() ->
                        new BussinesException(MSG_EVENTO));
        return converterEventoParaEventoDTO(evento);
    }

    public EventoDTO atualizarEvento(EventoDTO eventoDTO){
        eventoRepository.findById(eventoDTO.getId())
                .orElseThrow(() ->
                        new BussinesException(MSG_EVENTO));
        Evento evento = converterEventoDTOParaEvento(eventoDTO);
        eventoRepository.save(evento);
        return converterEventoParaEventoDTO(evento);
    }

    public EventoDTO buscarEventoPorDescricao(String descricao){
        Evento evento = eventoRepository.findByDescricao(descricao)
                .orElseThrow(() ->
                        new BussinesException(MSG_EVENTO));
        return converterEventoParaEventoDTO(evento);
    }
}
