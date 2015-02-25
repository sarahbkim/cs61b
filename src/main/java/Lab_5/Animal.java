package Lab_5;

/**
 * Created by sarahbkim on 2/22/15.
 */
public class Animal {
    protected int legs;
    protected String name;
    protected int age;
    public static final int ERROR_CODE = 123;

    /* constructors */
    public Animal(String name, int legs, int age) {
        this.name = name;
        this.legs = legs;
        this.age = age;
    }

    public Animal(String name, int age) {
        this(name, 4, age); // make legs default to 4
    }

    public Animal() {
        this("None", 4, 0);
    }

    /* get name */
    public String getName() {
        return name;
    }

    /* get age */
    public int getAge() {
        return age;
    }

    /* get num of legs */
    public int getNumberOfLegs() {
        return legs;
    }

    /* get dog years */
    public int ageInAnimalYears(String animalType) {
        System.out.println("Original method!");
        if(animalType.equals("Dog")){
            return age * 7;
        } else {
            return age * 30;
        }
    }

    public static void main(String[] args) {
    System.out.println(ERROR_CODE);


    }
}
