package com.axioma.backendcardapio.controllers;

import com.axioma.backendcardapio.entity.Food;
import com.axioma.backendcardapio.repository.FoodRepository;
import com.axioma.backendcardapio.dto.FoodRequestDTO;
import com.axioma.backendcardapio.dto.FoodResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("food")
public class FoodController {

    @Autowired
    private FoodRepository repository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public void saveFood(@RequestBody FoodRequestDTO data){
        Food foodData = new Food(data);
        repository.save(foodData);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<FoodResponseDTO> getAll(){

        return repository.findAll().stream().map(FoodResponseDTO::new).toList();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{id}")
    public void deleteFood(@PathVariable Long id) {
        repository.deleteById(id);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/{id}")
    public String editFood(@PathVariable Long id, @RequestBody FoodRequestDTO data) {
        // Verificar si el alimento con el ID proporcionado existe en la base de datos
        Optional<Food> existingFood = repository.findById(id);

        if (existingFood.isPresent()) {
            // Si existe, actualizar sus datos
            Food foodToUpdate = existingFood.get();
            foodToUpdate.setTitle(data.title());
            foodToUpdate.setImage(data.image());
            foodToUpdate.setPrice(data.price());

            repository.save(foodToUpdate);

            return "Éxito: El alimento con ID " + id + " ha sido actualizado.";
        } else {
            // Manejar el caso en el que no se encuentra el alimento con el ID proporcionado
            // Devolver un mensaje de error
            return "Error: No se encontró el alimento con ID " + id + ".";
        }
    }

}
