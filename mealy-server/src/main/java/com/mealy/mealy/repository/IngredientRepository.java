package com.mealy.mealy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mealy.mealy.domain.Ingredient;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

}
