package zooapp;

import java.util.ArrayList; // Importerar ArrayList-klassen för att kunna använda den

public class ZooApp { // Huvudklass för applikationen
    public static void main(String[] args) { // Huvudmetod där programmet startar
        ArrayList<Animal> animals = new ArrayList<>(); // Skapar en lista för att hålla olika Animal-objekt

        animals.add(new Dog("Bamse")); // Skapar ett Dog-objekt och skickar in "Bamse" som namn och lägger till det i listan
        animals.add(new Eagle("Sky"));
        animals.add(new Shark("Jaws"));

        for (Animal a : animals) { // Loopar igenom alla djur i listan
            a.makeSound(); // Anropar makeSound-metoden för varje djur
            a.eat(); // Anropar eat-metoden för varje djur

            if (a instanceof Flyable) { // Kollar om djuret kan flyga
                ((Flyable) a).fly();
            }
            if (a instanceof Swimmable) { // Kollar om djuret kan simma
                ((Swimmable) a).swim();
            }

            System.out.println(); // Skriver ut en tom rad för bättre läsbarhet i konsolen
        }
    }
}
