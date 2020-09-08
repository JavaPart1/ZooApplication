package be.vdab.Zoo.domain;

import be.vdab.Zoo.domain.enums.FoodType;

public class Food extends BaseEntity{
    private FoodType foodType;
    private String foodName;

    public Food(int id,FoodType foodType, String foodName) {
        this.foodType = foodType;
        this.foodName = foodName;
        this.setId(id);
    }

    public Food() {
    }

    public FoodType getFoodType() {
        return foodType;
    }

    public void setFoodType(FoodType foodType) {
        this.foodType = foodType;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    @Override
    public String toString() {
        return getFoodName();
    }
}
