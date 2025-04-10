package org.lessons.java.spring_la_mia_pizzeria_crud.service;

import java.util.List;
import java.util.Optional;

import org.lessons.java.spring_la_mia_pizzeria_crud.model.Pizza;
import org.lessons.java.spring_la_mia_pizzeria_crud.repo.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class PizzaService {
    
    @Autowired
    private PizzaRepository pizzaRepository;

    public List<Pizza> findAll() {
        return pizzaRepository.findAll();
    }
    
    public Optional<Pizza> findById(Integer id) {
        return pizzaRepository.findById(id);
    }

    public Pizza getById(Integer id){

        Optional<Pizza> pizzaOpt = pizzaRepository.findById(id);
        if (pizzaOpt.isEmpty()) {
            // ResponseEntity.notFound()
        }
        return pizzaOpt.get();
    }

    public List<Pizza> findByNomeOrDescrizione(String query) {
        return pizzaRepository.findByNomeContainingIgnoreCaseOrDescrizioneContaining(query, query);
    }

    public Pizza create(Pizza pizza) {
        
        return pizzaRepository.save(pizza);
    }

    public Pizza update(Pizza pizza) {
        return pizzaRepository.save(pizza);
    }

    public void deleteById(Integer id) {
        pizzaRepository.deleteById(id);
    }

    public void delete(Pizza pizza) {
        pizzaRepository.delete(pizza);
    }

    public boolean existsById(Integer id) {
        return pizzaRepository.existsById(id);
    }

    public boolean exists(Pizza pizza) {
        return existsById(pizza.getId());
    }

}
