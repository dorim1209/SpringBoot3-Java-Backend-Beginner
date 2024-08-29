package com.example.firstproject.entity;

import com.example.firstproject.dto.PizzaFormDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Getter
public class Pizza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String price;

    public void patch(PizzaFormDto dto) {
        if(dto.getName() != null){
            this.name = dto.getName();
        }

        if(dto.getPrice() != null){
            this.price = dto.getPrice();
        }
    }
}
