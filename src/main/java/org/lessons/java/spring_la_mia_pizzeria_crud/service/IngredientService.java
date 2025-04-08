package org.lessons.java.spring_la_mia_pizzeria_crud.service;

import org.lessons.java.spring_la_mia_pizzeria_crud.repo.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.lessons.java.spring_la_mia_pizzeria_crud.model.Ingredient;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientService {
    
    @Autowired
    private IngredientRepository ingredientRepository;

    public List<Ingredient> findAll() {
        return ingredientRepository.findAll();
    }

    public Ingredient getById(Integer id) {
        Optional<Ingredient> ingredientOpt = ingredientRepository.findById(id);
        if (ingredientOpt.isEmpty()) {
            // ResponseEntity.notFound()
        }
        return ingredientOpt.get();
    }

    public Ingredient create(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    public Ingredient update(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    public void deleteById(Integer id) {
        ingredientRepository.deleteById(id);
    }

    public void delete(Ingredient ingredient) {
        ingredientRepository.delete(ingredient);
    }


}
