package com.evento.repositories;

import com.evento.models.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {
    Optional<Evento> findByDescricao(String descricao);
}
