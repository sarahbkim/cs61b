class Human {
    // fields aka instance vars
    public int age;
    public String name;

    // static or class variable
    public static int numberOfHumans;

    // static method - doesn't take obj as a parameter
    // Human.printHumans() if you call from another class!
    public static void printHumans() {
        System.out.println(Human.numberOfHumans);
    }

    // a constructor has to have same name as the class & return an obj
    public Human (String givenName) {
        numberOfHumans++;
        age = 12;
        name = givenName;
    }

    // if you make a constructor, you need to add in the default constructor!
    public Human() {
        numberOfHumans++;
    };

    public void change(int age) {
        String name = "Chang";
        this.age = age; // age refers to param, this.age refers to object's age
        this.name = name; // name refers to local var
        }


    public void introduce() {
        System.out.println("I'm " + name + "and I am " + age + "years old");
    }

    public void copy (Human original) {
        age = original.age;
        name = original.name;
    }

    // main() is always static!
    // in a static method, there's no 'this'
    public static void main () {
        new Human("Andy"); // calling the constructor
        Human kayla = new Human("Kayla"); // replace creating Kayla (below)

        // Example usage
        // Human kayla = new Human();
        // kayla.age = 12;
        // kayla.name = "Kayla";

        kayla.introduce();

        Human rishi = new Human();
        rishi.name = "Rishi";
        rishi.age = 13;

        kayla.copy(rishi);
        kayla.introduce();

        kayla.change(8);

        // using static field
        int kids = Human.numberOfHumans/4;
        // System.out is a static field.

    }

}

