public class Dog {
    private String name;
    private String breed;
    private int age;
    private double weight;
    private int energy;

    public Dog(String name, String breed, int age, double weight, int energy) {
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.weight = weight;
        this.energy = energy;
    }

    public String description() {
        String var10000 = this.name;
        return var10000 + " (" + this.breed + "), " + this.age + " år, " + String.format("%.1f", this.weight) + " kg, energi: " + this.energy + "/100";
    }

    public void birthday() {
        ++this.age;
    }

    public void walk(int km) {
        this.energy -= km * 7;
        this.weight -= (double)km * 0.05;
        if (this.energy < 0) {
            this.energy = 0;
        }

        if (this.weight < 0.0) {
            this.weight = 0.0;
        }

    }

    public void feed(int grams) {
        this.energy += grams / 10;
        this.weight += (double)grams * 0.001;
        if (this.energy > 100) {
            this.energy = 100;
        }

    }

    public static Dog older(Dog a, Dog b) {
        if (a.age > b.age) {
            return a;
        } else {
            return b.age > a.age ? b : null;
        }
    }

    public String getName() {
        return this.name;
    }
}