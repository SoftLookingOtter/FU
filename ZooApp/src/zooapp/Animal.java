package zooapp;

abstract class Animal { // Abstrakt för att inte kunna instansiera objekt av Animal direkt
    protected String name; // Protected för att kunna nås av subklasser

    public Animal(String name) { // Konstruktor
        this.name = name; // this.name syftar på variabeln i klassen, medan name (utan this.) är värdet som skickas in till konstruktorn när ett objekt skapas.
    }

    public String getName() {
        return name;
    } // Getter för namn // Om du behöver åtkomst till namnet utanför klassen Animal eller dess subklasser. // en getter är en metod som returnerar värdet av en privat eller skyddad variabel.

    public abstract void makeSound(); // Abstrakt metod för att tvinga subklasser att implementera denna metod
    public abstract void eat();
}
