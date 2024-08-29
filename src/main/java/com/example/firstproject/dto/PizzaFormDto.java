package com.example.firstproject.dto;

import com.example.firstproject.entity.Pizza;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PizzaFormDto {
    private String name;
    private String price;

    public Pizza toEntity() {
        return new Pizza(null, name, price);
    }
}
