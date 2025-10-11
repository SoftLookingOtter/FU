package zooapp;

class Dog extends Animal { // Hundklass som ärver från Animal
    public Dog(String name) { // Konstruktor som tar ett namn som parameter
        super(name); // Anropar Animal-konstruktorn och skickar vidare "Bamse"
        // Animal sätter då sin variabel 'name' = "Bamse"
        // Eftersom Dog ärver från Animal, får Dog automatiskt samma 'name'
    }

    @Override // // markerar att denna metod ersätter (override:ar) en metod i superklassen
    public void makeSound() {
        System.out.println("Dog (" + name + ") says: Woof!");
    }

    @Override
    public void eat() {
        System.out.println("Dog (" + name + ") eats: dog food.");
    }
}
