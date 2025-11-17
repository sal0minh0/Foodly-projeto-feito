package com.foodly.repository;

import com.foodly.models.AssinaturaPremium;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AssinaturaPremiumRepository extends JpaRepository<AssinaturaPremium, Integer> {
    Optional<AssinaturaPremium> findByClienteId(Integer clienteId);
}
