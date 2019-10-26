package com.mealy.mealy.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.mealy.mealy.domain.Ingredient;
import com.mealy.mealy.exception.ResourceNotFoundException;
import com.mealy.mealy.repository.IngredientRepository;

@Service
public class IngredientService {

	@Autowired
    IngredientRepository ingredientRepository;
	
	public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }
	
	public Ingredient createNote(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }
	
	public Ingredient getIngredientById(Long ingredientId) {
        return ingredientRepository.findById(ingredientId)
        		.orElseThrow(() -> new ResourceNotFoundException("Ingredient", "id", ingredientId));
    }
	
	public Ingredient updateIngredientById(Long ingredientId,
            @Valid @RequestBody Ingredient ingredientDetails){
	
		Ingredient ingredient = getIngredientById(ingredientId);

        ingredient.setName(ingredientDetails.getName());
        ingredient.setDescription(ingredientDetails.getDescription());

        Ingredient updatedIngredient = ingredientRepository.save(ingredient);
        return updatedIngredient;
	}
	
    public ResponseEntity<?> deleteIngredientById(Long ingredientId) {
        Ingredient ingredient = getIngredientById(ingredientId);
        
        ingredientRepository.delete(ingredient);
        
        return ResponseEntity.ok().build();
        
    }
	
}
	
	

