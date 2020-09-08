package be.vdab.Zoo.domain;

import be.vdab.Zoo.domain.enums.AnimalType;
import be.vdab.Zoo.domain.enums.FoodType;

public class Animal  extends BaseEntity{
    private Food food;
    private FoodType foodType;
    private AnimalType animalType;
    private String name;

    public Animal(String name) {
        this.name = name;
    }

    public Animal() {
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public FoodType getFoodType() {
        return foodType;
    }

    public void setFoodType(FoodType foodType) {
        this.foodType = foodType;
    }

    public AnimalType getAnimalType() {
        return animalType;
    }

    public void setAnimalType(AnimalType animalType) {
        this.animalType = animalType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
