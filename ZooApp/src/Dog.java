class Dog extends Animal {
    public Dog(String name) {
        super(name);
    }

    @Override
    public void makeSound() {
        System.out.println("Dog (" + name + ") says: Woof!");
    }

    @Override
    public void eat() {
        System.out.println("Dog (" + name + ") eats: dog food.");
    }
}
