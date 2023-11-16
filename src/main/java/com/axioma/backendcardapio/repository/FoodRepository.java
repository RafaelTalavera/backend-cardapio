package com.axioma.backendcardapio.repository;

import com.axioma.backendcardapio.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Long> {
}
