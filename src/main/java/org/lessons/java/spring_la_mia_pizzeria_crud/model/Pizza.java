package org.lessons.java.spring_la_mia_pizzeria_crud.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "pizzas")
public class Pizza implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank (message = "nome must not be null, empty or blank")
    private String nome;

    @Lob
    @Size(max = 255, message = "descrizione must not be longer than 255 characters")
    private String descrizione;

    private String foto;

    @NotNull
    @Min(value = 0, message = "prezzo cannot be negative")
    private BigDecimal prezzo;

    @OneToMany(mappedBy = "pizza", cascade = (CascadeType.REMOVE))
    private List<Offer> offers;

    @ManyToMany()
    @JoinTable(name = "ingredient_pizza",
            joinColumns = @JoinColumn(name = "pizza_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    private List<Ingredient> ingredients;

    public Integer getId() {
        return this.id;
    }

    public List<Ingredient> getIngredients() {
        return this.ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Offer> getOffers() {
        return this.offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return this.descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getFoto() {
        return this.foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public BigDecimal getPrezzo() {
        return this.prezzo;
    }

    public void setPrezzo(BigDecimal prezzo) {
        this.prezzo = prezzo;
    }

    @Override
    public String toString(){
        return String.format("%s, prezzo: €%.2f", this.nome, this.prezzo);
    }

}
