package countries;

import java.io.IOException;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Optional;

public class Homework1 {

    private List<Country> countries;

    public Homework1() {
        countries = new CountryRepository().getAll();
    }

    /**
     * Returns whether there is at least one country with the word "island" in its name ignoring case.
     */
    public boolean streamPipeline1() {

        return countries.stream()
                .map(Country::getName)
                .map(a -> a.toLowerCase())
                .map(a -> a.contains("island"))
                .anyMatch(a -> a = true)
                ;
    }

    /**
     * Returns the first country name that contains the word "island" ignoring case.
     */
    public Optional<String> streamPipeline2() {

        return countries.stream()
                .map(Country::getName)
                .filter(a -> a.toLowerCase().contains("island"))
                .findFirst()
                ;
    }

    /**
     * Prints each country name in which the first and the last letters are the same ignoring case.
     */
    public void streamPipeline3() {
        countries.stream()
                .map(Country::getName)
                .filter(a -> a.toLowerCase().charAt(0) == a.toLowerCase().charAt(a.length() - 1))
                .forEach(System.out::println)
        ;


    }

    /**
     * Prints the populations of the first ten least populous countries.
     */
    public void streamPipeline4() {
        countries.stream()
                .mapToLong(Country::getPopulation)
                .sorted().limit(10)
                .forEach(System.out::println)
        ;
    }

    /**
     * Prints the names of the first ten least populous countries.
     */
    public void streamPipeline5() {


        countries.stream().sorted(new Comparator<Country>() {
            @Override
            public int compare(Country o1, Country o2) {
                return Long.compare(o1.getPopulation(), o2.getPopulation());
            }
        }).limit(10).map(Country::getName).forEach(System.out::println);

    }

    /**
     * Returns summary statistics about the number of country name translations associated with each country.
     */
    public IntSummaryStatistics streamPipeline6() {

        return countries.stream().mapToInt(a -> a.getTranslations().size()).summaryStatistics();
    }

    /**
     * Prints the names of countries in the ascending order of the number of timezones.
     */
    public void streamPipeline7() {
        countries.stream().sorted(new Comparator<Country>() {
            @Override
            public int compare(Country o1, Country o2) {
                return Long.compare(o1.getTimezones().size(), o2.getTimezones().size());
            }
        }).map(Country::getName).forEach(System.out::println);
    }

    /**
     * Prints the number of timezones for each country in the form {@code name:timezones}, in the ascending order of the number of timezones.
     */
    public void streamPipeline8() {
        countries.stream().sorted(new Comparator<Country>() {
            @Override
            public int compare(Country o1, Country o2) {
                return Long.compare(o1.getTimezones().size(), o2.getTimezones().size());
            }
        }).forEach(a -> System.out.println(a.getName() + ": " + a.getTimezones().size()));
    }

    /**
     * Returns the number of countries with no Spanish country name translation (the Spanish language is identifi
     * ed by the language code "es").
     */
    public long streamPipeline9() {

        return countries.stream().filter(a -> a.getTranslations().containsKey("es")).count();
    }

    /**
     * Prints the names of countries with null area.
     */
    public void streamPipeline10() {

        countries.stream().filter(a -> a.getArea() == null).forEach(a -> System.out.println(a.getName()));
    }

    /**
     * Prints all distinct language tags of country name translations sorted in alphabetical order.
     */
    public void streamPipeline11() {
        // TODO
        countries.stream().flatMap(a -> a.getTranslations().keySet().stream()).distinct().sorted().forEach(System.out::println);
    }

    /**
     * Returns the average length of country names.
     */
    public double streamPipeline12() {

        return countries.stream().mapToDouble(a -> a.getName().length()).average().getAsDouble();
    }

    /**
     * Prints all distinct regions of the countries with null area.
     */
    public void streamPipeline13() {
        // TODO
        countries.stream().filter(a -> a.getArea() == null).map(a -> a.getRegion()).distinct().forEach(System.out::println);
    }

    /**
     * Returns the largest country with non-null area.
     */
    public Optional<Country> streamPipeline14() {

        return countries.stream().filter(a -> a.getArea() != null).max(new Comparator<Country>() {
            @Override
            public int compare(Country o1, Country o2) {
                return (o1.getArea().compareTo(o2.getArea()));
            }

            ;
        });
    }

    /**
     * Prints the names of countries with a non-null area below 1.
     */
    public void streamPipeline15() {

        countries.stream().filter(a -> a.getArea() != null)
                .filter(a -> a.getArea().compareTo(BigDecimal.ONE) == (-1))
                .map(Country::getName)
                .forEach(System.out::println);
    }

    /**
     * Prints all distinct timezones of European and Asian countries.
     */
    public void streamPipeline16() {
        countries.stream()
                .filter(a -> ((a.getRegion() == Region.EUROPE) || (a.getRegion() == Region.ASIA)))
                .flatMap(a -> a.getTimezones().stream())
                .distinct()
                .forEach(System.out::println);
    }

}
