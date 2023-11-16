package com.axioma.backendcardapio.service;

import com.axioma.backendcardapio.entity.Food;

import java.util.List;

public interface IFoodRepositoryService{

    public List<Food> finAll();

    public Food findById(Long id);

    public Food save (Food food);

    public void delete(Food food);
}
