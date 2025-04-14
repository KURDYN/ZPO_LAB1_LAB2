package LAB1;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {
        // prosto, interfejs Runnable
        Runnable lambda1 = () -> System.out.println("Lambda wywołanie proste");
        lambda1.run();

        // blokowo, interfejs Supplier
        Supplier<String> lambda2 = () -> {
            String result;
            result = String.join(" ", "Lambda", "wywołanie", "blokowe");
            return result;
        };
        System.out.println(lambda2.get());

        // odwołanie przez klasę, interfejs Consumer
        Integer[] numbers = {1, 2, 3, 4};
        processArrayConsumer(numbers, Main::doubleNumber);

        // odwołanie przez referencję do obiektu, interfejs Function
        Tripler x3 = new Tripler();
        processArrayFunction(numbers, x3::tripleNumber);

        //odwołanie do konstruktora klasy, interfejs BiFunction
        BiFunction<String, Integer, Person> createPerson = Person::new;

        Person person1 = createPerson.apply("Jan", 30);
        Person person2 = createPerson.apply("Kasia", 25);

        System.out.println(person1);
        System.out.println(person2);
    }

    static <T> void processArrayConsumer(T[] array, Consumer<T> action) {
        for (T element : array) {
            action.accept(element);
        }
    }

    static void doubleNumber(int num) {
        System.out.println(num * 2);
    }

    static <T, R> void processArrayFunction(T[] array, Function<T, R> action) {
        for (T element : array) {
            R result = action.apply(element);
            System.out.println(result);
        }
    }
}

class Tripler {
    public int tripleNumber(int num) {
        return num * 3;
    }
}

class Person {
    private String name;
    private int age;

    // konstruktor klasy Person przyjmujący imię i wiek
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // przeciążenie metody toString, aby wyświetlać dane
    @Override
    public String toString() {
        return name + ", " + age;
    }
}