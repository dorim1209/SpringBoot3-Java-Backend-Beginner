package com.example.firstproject.service;

import com.example.firstproject.dto.PizzaFormDto;
import com.example.firstproject.entity.Pizza;
import com.example.firstproject.repository.PizzaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PizzaService {
    @Autowired
    PizzaRepository pizzaRepository;

    public List<Pizza> index() {
        return pizzaRepository.findAll();
    }

    public Pizza show(Long id) {
        return pizzaRepository.findById(id).orElse(null);
    }

    public Pizza create(PizzaFormDto dto) {
        Pizza created = dto.toEntity();
        return pizzaRepository.save(created);
    }

    public Pizza update(Long id, PizzaFormDto dto) {
        Pizza target = pizzaRepository.findById(id).orElse(null);

        if (target == null) {
            return null;
        }

        target.patch(dto);
        return pizzaRepository.save(target);
    }

    public Pizza delete(Long id) {
        Pizza target = pizzaRepository.findById(id).orElse(null);

        if (target == null) {
            return null;
        }

        pizzaRepository.delete(target);
        return target;
    }
}
