package org.lessons.java.spring_la_mia_pizzeria_crud.controller;

import org.lessons.java.spring_la_mia_pizzeria_crud.model.Offer;
import org.lessons.java.spring_la_mia_pizzeria_crud.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
@RequestMapping("/offers")
public class OffersController {

    @Autowired
    private OfferService offerService;

    @GetMapping("/{id}")
    public String show(@PathVariable Integer id, Model model) {

        model.addAttribute("offer", offerService.getById(id));

        return "offers/show";
    }


    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("offer") Offer formOffer, BindingResult bindingResult, Model model ) {
        
        if(bindingResult.hasErrors()){
            return "offers/create-or-edit";
        }

        offerService.create(formOffer);

        return "redirect:/pizzas/" + formOffer.getPizza().getId();
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("edit", true);
        model.addAttribute("offer", offerService.getById(id));

        return "offers/create-or-edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("offer") Offer formOffer, BindingResult bindingResult, Model model ) {
        if(bindingResult.hasErrors()){
            return "offers/create-or-edit";
        }
        offerService.update(formOffer);
        
        return "redirect:/pizzas/" + formOffer.getPizza().getId();
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        int pizzaIdToReturnTo = offerService.getById(id).getPizza().getId();

        offerService.deleteById(id);

        return "redirect:/pizzas/" + pizzaIdToReturnTo;
    }

      
}
