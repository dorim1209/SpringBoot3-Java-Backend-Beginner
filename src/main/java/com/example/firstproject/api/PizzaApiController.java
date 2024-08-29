package com.example.firstproject.api;

import com.example.firstproject.dto.PizzaFormDto;
import com.example.firstproject.entity.Pizza;
import com.example.firstproject.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PizzaApiController {
    @Autowired
    PizzaService pizzaService;

    @GetMapping("/api/pizzas")
    public List<Pizza> index() {
        return pizzaService.index();
    }

    @GetMapping("/api/pizzas/{id}")
    public Pizza show(@PathVariable Long id) {
        return pizzaService.show(id);
    }

    @PostMapping("/api/pizzas")
    public ResponseEntity<Pizza> create(@RequestBody PizzaFormDto dto) {
        Pizza created = pizzaService.create(dto);
        return (created != null)
                ? ResponseEntity.status(HttpStatus.OK).body(created)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PatchMapping("/api/pizzas/{id}")
    public ResponseEntity<Pizza> update(@PathVariable Long id, @RequestBody PizzaFormDto dto) {
        Pizza updated = pizzaService.update(id, dto);
        return (updated != null)
                ? ResponseEntity.status(HttpStatus.OK).body(updated)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @DeleteMapping("/api/pizzas/{id}")
    public ResponseEntity<Pizza> delete(@PathVariable Long id) {
        Pizza deleted = pizzaService.delete(id);
        return (deleted != null)
                ? ResponseEntity.status(HttpStatus.OK).body(deleted)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

}
