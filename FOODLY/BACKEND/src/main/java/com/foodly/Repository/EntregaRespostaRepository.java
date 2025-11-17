package com.foodly.repository;

import com.foodly.models.EntregaResposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntregaRespostaRepository extends JpaRepository<EntregaResposta, Integer> {
    List<EntregaResposta> findByEntregaId(Integer entregaId);
    List<EntregaResposta> findByEntregadorId(Integer entregadorId);
}
