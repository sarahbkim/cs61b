package Lab_5;

/**
 * Created by sarahbkim on 2/22/15.
 */
public class Dog extends Animal{
    protected String greeting;
    public static final int ERROR_CODE = 123;

    /* constructors */
    public Dog(String name, int legs, int age, String greeting) {
        super(name, legs, age); // this calls the Animal constructor with all these args
        this.greeting = greeting;
    }

    public Dog() {
    }

    /* get age */
    public int getAge() {
        return age;
    }

    public int ageInAnimalYears(String animal) {
        super.ageInAnimalYears(animal);
        if(animal.equals("Dog")){
            return age * 7;
        } else {
            return age * 30;
        }
    }

    public static void main(String[] args) {
        if(ERROR_CODE==123) {
            System.out.println("error!");
        } else {
            System.out.println("no error!");
        }

        Dog fido = new Dog("Fido", 4, 1, "Yip");
        System.out.println(fido.getName());


        ((Animal) fido).ageInAnimalYears("Dog");
        Animal original = new Dog();
        ((Animal) original).ageInAnimalYears("Dog");

        fido.ageInAnimalYears("Dog");

        // learning about inheritance...
        Animal greg = new Dog();
        System.out.println(greg.getName());

        // Downcasting...
        Animal y = new Dog();
        Dog test = (Dog)y;

        Animal c = new Animal();

        // an array of objects
        Dog[] xa = new Dog[4];
        for(int i=0;i<4;i++) {
            xa[i] = new Dog();
        }

        Animal[] ya = new Animal[4];
        for(int i=0;i<4;i++) {
            ya[i] = new Animal();
        }

        Dog[] xb = new Dog[4];
        for(int i=0;i<4;i++){
            Animal z = new Dog();
            xb[i] = (Dog) z;
        }

        ya = xa;
        xa = (Dog[]) ya;

        Animal s;
        Dog t = new Dog();
        s = t;
        t = (Dog)s;

        s = new Animal();
        t = (Dog) s; // Run-time errord


    }
}
