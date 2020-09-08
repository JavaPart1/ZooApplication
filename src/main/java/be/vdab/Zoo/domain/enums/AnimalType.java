package be.vdab.Zoo.domain.enums;

public enum AnimalType {
    BEAR("Bear"),DOG("Dog"),GIRAFFE("Giraffe"),LION("Lion");

    private final String name;

    AnimalType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
