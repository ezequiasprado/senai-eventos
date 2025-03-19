package com.evento.resources;

import com.evento.dtos.EventoDTO;
import com.evento.services.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/eventos")
public class EventoResource {

    @Autowired
    private EventoService eventoService;

    @GetMapping("/{id}")
    public ResponseEntity<EventoDTO> buscarEventoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(eventoService.buscarEventoPorId(id));
    }

    @PostMapping()
    public ResponseEntity<EventoDTO> cadastrarEvento(@RequestBody EventoDTO eventoDTO) {
        EventoDTO evento = eventoService.cadastrarEvento(eventoDTO);
        return ResponseEntity.ok(evento);
    }

    @DeleteMapping()
    public ResponseEntity<Void> deletarEvento(@RequestBody EventoDTO eventoDTO) {
        eventoService.deletarEvento(eventoDTO.getId());
        return ResponseEntity.noContent().build();
    }

    @PutMapping()
    public ResponseEntity<EventoDTO> atualizarEvento(@RequestBody EventoDTO eventoDTO) {
        return ResponseEntity.ok(eventoService.atualizarEvento(eventoDTO));
    }

    @GetMapping("/buscar")
    public ResponseEntity<EventoDTO> buscarEventoPorDescricao(@RequestParam String descricao) {
        return ResponseEntity.ok(eventoService.buscarEventoPorDescricao(descricao));
    }
}
