package com.evento.services;

import com.evento.dtos.CidadeDTO;
import com.evento.dtos.EnderecoDTO;
import com.evento.exceptions.BussinesException;
import com.evento.models.Cidade;
import com.evento.models.Endereco;
import com.evento.repositories.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static java.util.Objects.*;

@Service
public class EnderecoService {
    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private CidadeService cidadeService;

    public EnderecoDTO cadastrarEndereco(EnderecoDTO enderecoDTO){
        Endereco endereco =
                converterEnderecoDTOParaEndereco(enderecoDTO);
        endereco = enderecoRepository.save(endereco);
        return converterEnderecoParaEnderecoDTO(endereco);
    }

    public EnderecoDTO converterEnderecoParaEnderecoDTO(Endereco endereco) {
        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setId(endereco.getId());
        enderecoDTO.setNumero(endereco.getNumero());
        enderecoDTO.setCep(endereco.getCep());
        enderecoDTO.setRua(endereco.getRua());
        enderecoDTO.setBairro(endereco.getBairro());
        enderecoDTO.setComplemento(endereco.getComplemento());

        CidadeDTO cidadeDTO = cidadeService.buscarCidadePorId(
                endereco.getCidade().getId());

        enderecoDTO.setCidade(cidadeDTO);
        return enderecoDTO;
    }

    public Endereco converterEnderecoDTOParaEndereco(EnderecoDTO enderecoDTO) {
        Endereco endereco = new Endereco();
        endereco.setId(enderecoDTO.getId());
        endereco.setNumero(enderecoDTO.getNumero());
        endereco.setCep(enderecoDTO.getCep());
        endereco.setRua(enderecoDTO.getRua());
        endereco.setBairro(enderecoDTO.getBairro());
        endereco.setComplemento(enderecoDTO.getComplemento());

        endereco.setCidade(cidadeService
                .converterCidadeDTOParaCidade(enderecoDTO.getCidade()));
        return endereco;
    }

    public void deletarEndereco(EnderecoDTO enderecoDTO){
        if (isNull(enderecoDTO.getId())) {
            throw new BussinesException("Endereço não encontrado");
        }
        enderecoRepository.deleteById(enderecoDTO.getId());
    }

    public EnderecoDTO buscarEnderecoPorId(Long id){
        Endereco endereco = enderecoRepository.findById(id)
                .orElseThrow(() ->
                        new BussinesException("Endereço não encontrado"));
        return converterEnderecoParaEnderecoDTO(endereco);
    }

    public EnderecoDTO atualizarEndereco(EnderecoDTO enderecoDTO){
        if (isNull(enderecoDTO.getId() )) {
            throw new BussinesException("Endereço não encontrado");
        }
        enderecoRepository.findById(enderecoDTO.getId())
                .orElseThrow(() ->
                        new BussinesException("Endereço não encontrado"));

        if (isNull(enderecoDTO.getCidade()) ||
                isNull(enderecoDTO.getCidade().getId())) {
            throw new BussinesException("Cidade não encontrada");
        }
        cidadeService.buscarCidadePorId(enderecoDTO.getCidade().getId());
        Endereco endereco =
                converterEnderecoDTOParaEndereco(enderecoDTO);

        enderecoRepository.save(endereco);

        return converterEnderecoParaEnderecoDTO(endereco);
    }

    public EnderecoDTO buscarEnderecoPorCep(String cep){
        Endereco endereco = enderecoRepository.findByCep(cep)
                .orElseThrow(() ->
                        new BussinesException("Cep Não Encontrado"));
        return converterEnderecoParaEnderecoDTO(endereco);
    }
}
