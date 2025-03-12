package com.evento.services;

import com.evento.dtos.CidadeDTO;
import com.evento.dtos.EnderecoDTO;
import com.evento.exceptions.BussinesException;
import com.evento.models.Cidade;
import com.evento.models.Endereco;
import com.evento.repositories.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    private EnderecoDTO converterEnderecoParaEnderecoDTO(Endereco endereco) {
        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setId(endereco.getId());
        enderecoDTO.setNumero(endereco.getNumero());
        enderecoDTO.setCep(endereco.getCep());
        enderecoDTO.setRua(endereco.getRua());
        enderecoDTO.setBairro(endereco.getBairro());
        enderecoDTO.setComplemento(endereco.getComplemento());

        enderecoDTO.setCidade(cidadeService.
                converterCidadeParaCidadeDTO(endereco.getCidade()));
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

    public void deletarEndereco(Long id){
        enderecoRepository.deleteById(id);
    }

    public EnderecoDTO buscarEnderecoPorId(Long id){
        Endereco endereco = enderecoRepository.findById(id)
                .orElseThrow(() ->
                        new BussinesException("Endereço não encontrado"));
        return converterEnderecoParaEnderecoDTO(endereco);
    }

    public EnderecoDTO atualizarEndereco(EnderecoDTO enderecoDTO){
        enderecoRepository.findById(enderecoDTO.getId())
                .orElseThrow(() ->
                        new BussinesException("Endereço não encontrado"));

        Endereco endereco =
                converterEnderecoDTOParaEndereco(enderecoDTO);

        enderecoRepository.save(endereco);

        return converterEnderecoParaEnderecoDTO(endereco);
    }
}
