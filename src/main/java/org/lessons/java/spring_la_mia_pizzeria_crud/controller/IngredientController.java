package org.lessons.java.spring_la_mia_pizzeria_crud.controller;


import org.lessons.java.spring_la_mia_pizzeria_crud.model.Ingredient;
import org.lessons.java.spring_la_mia_pizzeria_crud.model.Pizza;
import org.lessons.java.spring_la_mia_pizzeria_crud.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/ingredients")
public class IngredientController {
    
    @Autowired
    private IngredientService ingredientService;

    @GetMapping
    public String index(Model model) {
    model.addAttribute("ingredients", ingredientService.findAll());
    return "ingredients/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Integer id, Model model) {

        model.addAttribute("ingredient", ingredientService.getById(id));

        return "ingredients/show";
    }

    @GetMapping("/create")
    public String create(Model model) {
    model.addAttribute("ingredient", new Ingredient());
    return "ingredients/create-or-edit";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("ingredient") Ingredient ingredient,
    BindingResult bindingResult, Model model) {
    if (bindingResult.hasErrors()) {
    return "ingredients/create-or-edit";
    }

    ingredientService.create(ingredient);

    return "redirect:/ingredients";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("edit", true);
        model.addAttribute("ingredient", ingredientService.getById(id));

        return "ingredients/create-or-edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("offer") Ingredient FormIngredient, BindingResult bindingResult, Model model ) {
        if(bindingResult.hasErrors()){
            return "ingredients/create-or-edit";
        }
        ingredientService.update(FormIngredient);
        
        return "redirect:/ingredients";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        Ingredient ingredientToDelete = ingredientService.getById(id);

        for (Pizza pizza : ingredientToDelete.getPizzas()) {
            pizza.getIngredients().remove(ingredientToDelete);
        }

        ingredientService.delete(ingredientToDelete);

        return "redirect:/ingredients";
    }
}
