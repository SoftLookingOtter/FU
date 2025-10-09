class Eagle extends Animal implements Flyable {
    public Eagle(String name) {
        super(name);
    }

    @Override
    public void makeSound() {
        System.out.println("Eagle (" + name + ") says: Screech!");
    }

    @Override
    public void eat() {
        System.out.println("Eagle (" + name + ") eats: small animals.");
    }

    @Override
    public void fly() {
        System.out.println("Eagle (" + name + ") flies high in the sky!");
    }
}
