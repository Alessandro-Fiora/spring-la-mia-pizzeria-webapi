package org.lessons.java.spring_la_mia_pizzeria_crud.controller;

import java.util.Optional;
import java.util.List;

import org.lessons.java.spring_la_mia_pizzeria_crud.model.Pizza;
import org.lessons.java.spring_la_mia_pizzeria_crud.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping("/api/pizzas")
public class PizzaRestController {
    
    @Autowired
    private PizzaService pizzaService;

    @GetMapping
    public ResponseEntity<List<Pizza>> index() {

        return new ResponseEntity<List<Pizza>>(pizzaService.findAll(), HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Pizza> show(@PathVariable Integer id) {

        Optional<Pizza> pizzaOpt = pizzaService.findById(id);

        if(pizzaOpt.isEmpty()){
            return new ResponseEntity<Pizza>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Pizza>(pizzaOpt.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Pizza> store(@RequestBody Pizza pizza) {
        return new ResponseEntity<Pizza>(pizzaService.create(pizza), HttpStatus.CREATED);
}

    @PutMapping("/{id}")
    public ResponseEntity<Pizza> update(@PathVariable Integer id, @RequestBody Pizza pizza) {
    
        if(pizzaService.findById(id).isEmpty()){
            return new ResponseEntity<Pizza>(HttpStatus.NOT_FOUND);
        }
        
        pizza.setId(id);
        return new ResponseEntity<Pizza>(pizzaService.update(pizza), HttpStatus.OK);
       
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> destroy(@PathVariable Integer id) {
        
        if(pizzaService.findById(id).isEmpty()){
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        
        pizzaService.deleteById(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}