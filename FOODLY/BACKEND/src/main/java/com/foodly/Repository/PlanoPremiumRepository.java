package com.foodly.repository;

import com.foodly.models.PlanoPremium;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanoPremiumRepository extends JpaRepository<PlanoPremium, Integer> {
    List<PlanoPremium> findByAtivo(boolean ativo);
}
