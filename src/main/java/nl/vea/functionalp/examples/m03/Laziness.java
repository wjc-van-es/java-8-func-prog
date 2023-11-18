package nl.vea.functionalp.examples.m03;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;

/**
 * Created by raoul-gabrielurma on 14/01/2014.
 */
public class Laziness {


    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
        List<Integer> twoEvenSquares = numbers.stream().filter(n -> {
            System.out.println("filtering " + n);
            return n % 2 == 0;
        }).map(n -> {
            System.out.println("mapping " + n);
            return n * n;
        }).limit(2).collect(toList());
        // you may have expected that all numbers 1 up to 8 will be evaluated in the filter and then mapped by squaring them
        // and that only then the first two square results (4 and 8) are put in the twoEvenSquares list
        // however, the calls to the System.out.println in the lambda expressions of both filter and map
        // reveal that the processing stops after the mapping of two elements (2 and 4) that made it through the filter
        // as we will need only two elements as indicated by limit(2).
        // Hence, the elements 5,6,7 and 8 are never processed, because after processing 1,2,3 and 4 we already had two resulting elements.
        //
        // This laziness is extremely valuable with large datasets and difficult to realize with imperative iteration
    }

}
