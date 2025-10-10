package zooapp;

class Shark extends Animal implements Swimmable {
    public Shark(String name) {
        super(name);
    }

    @Override
    public void makeSound() {
        System.out.println("Shark (" + name + ") says: ...");
    }

    @Override
    public void eat() {
        System.out.println("Shark (" + name + ") eats: fish.");
    }

    @Override
    public void swim() {
        System.out.println("Shark (" + name + ") swims in the ocean!");
    }
}
