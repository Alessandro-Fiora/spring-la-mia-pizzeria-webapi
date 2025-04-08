package org.lessons.java.spring_la_mia_pizzeria_crud.controller;


import java.util.List;

import org.lessons.java.spring_la_mia_pizzeria_crud.model.Offer;
import org.lessons.java.spring_la_mia_pizzeria_crud.model.Pizza;
import org.lessons.java.spring_la_mia_pizzeria_crud.repo.IngredientRepository;
import org.lessons.java.spring_la_mia_pizzeria_crud.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;


@Controller
@RequestMapping("/pizzas")
public class PizzasController {

    @Autowired
    private PizzaService pizzaService;

    @Autowired
    private IngredientRepository ingredientRepo;

    @GetMapping
    public String index(Model model){

        List<Pizza> pizzas = pizzaService.findAll();
        model.addAttribute("pizzas", pizzas);
        return "pizzas/index";
    }

    @GetMapping("/{id}")
    public String show(Model model, @PathVariable("id") Integer id){
        model.addAttribute("pizza", pizzaService.getById(id));

        return "pizzas/show";
    }

    @GetMapping("/search")
    public String searchByName(@RequestParam("search") String search, Model model){
        List<Pizza> pizzas = pizzaService.findByNomeOrDescrizione(search);

        model.addAttribute("pizzas", pizzas);
        return "pizzas/index";
    }

    @GetMapping("/create")
    public String create(Model model){
        Pizza pizza = new Pizza();
        pizza.setFoto("https://fakeimg.pl/600x400/ff6666/ffffff?text=Pizza");
        model.addAttribute("pizza", pizza);
        model.addAttribute("ingredients", ingredientRepo.findAll());
        return "pizzas/create";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, Model model ){

        if(bindingResult.hasErrors()){
            model.addAttribute("ingredients", ingredientRepo.findAll());

            return "pizzas/create";
        }

        pizzaService.create(formPizza);

        return "redirect:/pizzas";
    }

    
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, @RequestParam(value = "redirect", required = false) String redirect, Model model){
        model.addAttribute("pizza", pizzaService.getById(id));
        model.addAttribute("redirect", redirect);
        model.addAttribute("ingredients", ingredientRepo.findAll());
        return "pizzas/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, @RequestParam(value = "redirect", required = false) String redirect, Model model ){
        if(bindingResult.hasErrors()){
            model.addAttribute("ingredients", ingredientRepo.findAll());

            return "pizzas/edit";
        }
        pizzaService.update(formPizza);
        if ("show".equals(redirect)) {
            return "redirect:/pizzas/" + formPizza.getId();
        }
        return "redirect:/pizzas";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        
        pizzaService.deleteById(id);

        return "redirect:/pizzas";
    }

    @GetMapping("/{id}/offers")
    public String createOffer(@PathVariable Integer id, Model model){

        Offer offer = new Offer();
        offer.setPizza(pizzaService.getById(id));

        model.addAttribute("offer", offer);

        return "offers/create-or-edit";
    }
    
    
}
