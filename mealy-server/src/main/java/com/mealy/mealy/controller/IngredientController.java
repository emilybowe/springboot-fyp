package com.mealy.mealy.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mealy.mealy.domain.Ingredient;
import com.mealy.mealy.service.IngredientService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/ingredient")
public class IngredientController {

    @Autowired
    IngredientService ingredientService;

    // Get All Ingredients
    @GetMapping("/getAllIngredients")
    public List<Ingredient> getAllIngredients() {
        return ingredientService.getAllIngredients();
    }

    // Create a new Ingredients
    @PostMapping("/createIngredient")
    public Ingredient createIngredient(@Valid @RequestBody Ingredient ingredient) {
        return ingredientService.createNote(ingredient);
    }

    // Get a Single Ingredient
    @GetMapping("/getIngredient/{id}")
    public Ingredient getIngredientById(@PathVariable(value = "id") Long ingredientId) {
        return ingredientService.getIngredientById(ingredientId);
    }

    // Update a Ingredients
    @PutMapping("/updateIngredient/{id}")
    public Ingredient updateIngredientById(@PathVariable(value = "id") Long ingredientId,
                                            @Valid @RequestBody Ingredient ingredientDetails) {
    	return ingredientService.updateIngredientById(ingredientId, ingredientDetails);
        
    }

    // Delete a Ingredients
    @DeleteMapping("/deleteIngredient/{id}")
    public ResponseEntity<?> deleteIngredientById(@PathVariable(value = "id") Long ingredientId) {
       
        return ingredientService.deleteIngredientById(ingredientId);
    }
    
    
    
    
    
}