package org.lessons.java.spring_la_mia_pizzeria_crud.service;

import java.util.Optional;

import org.lessons.java.spring_la_mia_pizzeria_crud.model.Offer;
import org.lessons.java.spring_la_mia_pizzeria_crud.repo.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfferService {
    
    @Autowired
    private OfferRepository offerRepository;

    public Offer getById(Integer id) {
        Optional<Offer> offerOpt = offerRepository.findById(id);
        if (offerOpt.isEmpty()) {
            // ResponseEntity.notFound()
        }
        return offerOpt.get();
    }

    public Offer create(Offer offer) {
        return offerRepository.save(offer);
    }

    public Offer update(Offer offer) {
        return offerRepository.save(offer);
    }

    public void deleteById(Integer id) {
        offerRepository.deleteById(id);
    }


}
