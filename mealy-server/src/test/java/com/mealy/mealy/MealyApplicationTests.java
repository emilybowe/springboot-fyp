//using https://www.blazemeter.com/blog/spring-boot-rest-api-unit-testing-with-junit/ (take 5!)

package com.mealy.mealy;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.mealy.mealy.controller.IngredientController;
import com.mealy.mealy.domain.Ingredient;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import static java.util.Collections.singletonList;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(IngredientController.class)

public class MealyApplicationTests {

   @Autowired
   private MockMvc mvc;

   @MockBean
   private IngredientController ingredientController;

   @Test
   public void getIngredientByIDTest() throws Exception {
       Ingredient ingredient = new Ingredient();
       ingredient.setName("pear");
       ingredient.setId(18L);

       given(ingredientController.getIngredientById(ingredient.getId())).willReturn(ingredient);

       mvc.perform(get("/ingredient/getIngredient/{id}", 18L)            
               .contentType(APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id", is(18)))
               .andExpect(jsonPath("$.name", is("pear")));
   }
   
   @Test
   public void getAllIngredientsTest() throws Exception {
       Ingredient ingredient = new Ingredient();
       ingredient.setName("grape");
       ingredient.setId(17L);
       
       List<Ingredient> allIngredients = singletonList(ingredient);

       given(ingredientController.getAllIngredients()).willReturn(allIngredients);

       mvc.perform(get("/ingredient/getAllIngredients")            
               .contentType(APPLICATION_JSON))
               .andExpect(status().isOk())
		       .andExpect(jsonPath("$", hasSize(1)))
		       .andExpect(jsonPath("$[0].name", is("grape")));
   }
     
   
  
   public static String asJsonString(final Object obj) {
       try {
           return new ObjectMapper().writeValueAsString(obj);
       } catch (Exception e) {
           throw new RuntimeException(e);
       }
   }
   
   @Test
   public void createIngredientTest() throws Exception {
       Ingredient ingredient = new Ingredient();
       ingredient.setName("plum");
       ingredient.setId(16L);

       given(ingredientController.createIngredient(ingredient)).willReturn(ingredient);
       
       mvc.perform(post("/ingredient/createIngredient")    
    		   .content(asJsonString(ingredient))
               .contentType(APPLICATION_JSON))            
               .andExpect(status().isOk());

   }
   
   @Test
   public void updateIngredientTest() throws Exception {
       Ingredient ingredient = new Ingredient();
       ingredient.setName("plum");
       ingredient.setId(16L);

       given(ingredientController.updateIngredientById(ingredient.getId(), ingredient)).willReturn(ingredient);
       
       mvc.perform(put("/ingredient/updateIngredient/{id}", 16)    
    		   .content(asJsonString(ingredient))
               .contentType(APPLICATION_JSON))            
               .andExpect(status().isOk());

   }
   
   
   @Test
   public void deleteIngredientByIDTest() throws Exception {
       Ingredient ingredient = new Ingredient();
       ingredient.setName("pear");
       ingredient.setId(18L);

       given(ingredientController.deleteIngredientById(ingredient.getId())).willReturn(null);

       mvc.perform(delete("/ingredient/deleteIngredient/{id}", 18)            
               .contentType(APPLICATION_JSON))
               .andExpect(status().isOk());
   }
   


}
 