package LAB2;

import java.util.*;
import java.util.stream.*;

public class Main {
    public static void main(String[] args) {
        Random rand = new Random();
        ArrayList<Integer> numbers = new ArrayList<>();

        // Wypełnianie kolekcji losowymi liczbami
        for (int i = 0; i < 10; i++) {
            numbers.add(rand.nextInt(20));
        }
        System.out.println("Kolekcja: "+ numbers);

        // Metoda min
        int min = numbers.stream().min(Integer::compare).get();
        System.out.println("Minimalna wartość: "+ min);

        // Metoda filter
        System.out.println("\nLiczby parzyste: ");
        numbers.stream().filter(n -> n % 2 == 0).forEach(System.out::println);

        // Stworzenie listy osób
        List<Person> people;
        people = Arrays.asList(
                new Person("Alicja", 30),
                new Person("Marek", 25),
                new Person("Robert", 22),
                new Person("Kuba", 35)
        );

        // Metoda sorted
        System.out.println("\nPosortowane osoby: ");
        people.stream()
                .sorted(Comparator.comparing(Person::getNick)
                        .thenComparing(Person::getAge))
                .forEach(System.out::println);

        // Stworzenie listy punktów 3D
        List<PunktXYZ> points3D = Arrays.asList(
                new PunktXYZ(4, 2, 2),
                new PunktXYZ(1, 5, 7),
                new PunktXYZ(7, 4, 6)
        );

        // Konwersja na listę punktów 2D
        List<PunktXY> points2D = points3D.stream()
                .map(p -> new PunktXY(p.getX(), p.getY()))
                .toList();

        // Wypisanie listy punktów 2D
        System.out.println("\nPunkty XY:");
        for (PunktXY p : points2D) {
            System.out.println(p);
        }

        // Stworzenie grup Eagles i Bikers oraz listy grup (z wykorzystaniem lity osób z zad. 5)
        Group bikers = new Group("Bikers",
                Arrays.asList(people.get(0), people.get(1)));
        Group eagles = new Group("Eagles",
                Arrays.asList(people.get(2), people.get(3)));
        List<Group> groups = Arrays.asList(eagles,bikers);

        // Stworzenie listy wszystkich członków
        List<Person> allMembers = groups.stream()
                .flatMap(p -> p.getMembers().stream())
                .toList();

        // Wypisanie listy wszystkich członków
        System.out.println("\nOsoby w grupach: ");
        for (Person p : allMembers) {
            System.out.println(p);
        }

        // Przypomnienie listy z zad. 3 i 4
        System.out.println("\nKolekcja: "+ numbers);

        // Sumowanie liczb przy użyciu reduce() i Optional
        Optional<Integer> suma = numbers.stream().reduce(Integer::sum);
        suma.ifPresent(s -> System.out.println("Suma elementów: " + s));

        // Mnożenie liczb przy użyciu reduce() bez optional
        int iloczyn = numbers.stream().reduce(1, (a, b) -> a * b);
        System.out.println("Iloczyn elementów: " + iloczyn);

        // Stworzenie listy i wypełnienie jej
        List<String> idlist = new ArrayList<>();
        for (int i = 0; i < 1_000_000; i++) {
            idlist.add(UUID.randomUUID().toString());
        }

        // Pomiar czasu sortowania sekwencyjnego
        long startSequential = System.nanoTime();
        List<String> sortedList = idlist.stream().sorted().collect(Collectors.toList());
        long endSequential = System.nanoTime();
        System.out.println("\nCzas sortowania sekwencyjnego: " + (endSequential - startSequential) / 1_000_000 + " ms");

        // Pomiar czasu sortowania równoległego
        long startParallel = System.nanoTime();
        List<String> sortedParallelList = idlist.parallelStream().sorted().collect(Collectors.toList());
        long endParallel = System.nanoTime();
        System.out.println("Czas sortowania równoległego: " + (endParallel - startParallel) / 1_000_000 + " ms");
    }
}
