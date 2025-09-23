public class Main {
    public static void main(String[] args) {
        Dog vilda = new Dog("Vilda", "Labrador", 4, 28.4, 80);
        Dog bill = new Dog("Bill", "Tax", 14, 9.2, 60);
        Dog misiu = new Dog("Misiu", "Katt i hundspelet?!", 1, (double)4.5F, 90);
        Dog[] dogs = new Dog[]{vilda, bill, misiu};
        System.out.println("== Före aktiviteter ==");

        for(Dog d : dogs) {
            System.out.println(d.description());
        }

        vilda.walk(3);
        bill.feed(200);
        misiu.birthday();
        System.out.println("\n== Efter aktiviteter ==");

        for(Dog d : dogs) {
            System.out.println(d.description());
        }

        Dog older = Dog.older(vilda, bill);
        if (older != null) {
            System.out.println("\nÄldst av Vilda och Bill: " + older.getName());
        }

    }
}