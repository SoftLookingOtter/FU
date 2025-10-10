package zooapp;

import java.util.ArrayList;

public class ZooApp {
    public static void main(String[] args) {
        ArrayList<Animal> animals = new ArrayList<>();

        animals.add(new Dog("Bamse"));
        animals.add(new Eagle("Sky"));
        animals.add(new Shark("Jaws"));

        for (Animal a : animals) {
            a.makeSound();
            a.eat();

            if (a instanceof Flyable) {
                ((Flyable) a).fly();
            }
            if (a instanceof Swimmable) {
                ((Swimmable) a).swim();
            }

            System.out.println();
        }
    }
}
