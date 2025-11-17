package com.foodly.repository;

import com.foodly.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
    List<Produto> findByRestauranteId(Integer restauranteId);
    List<Produto> findByRestauranteIdAndAtivo(Integer restauranteId, boolean ativo);
}
