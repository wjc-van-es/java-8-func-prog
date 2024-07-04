package nl.vea.functionalp.examples.m03;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.*;

public class BuildingStreamFromFileContent {

    public static void main(String[] args) throws IOException {
        Pattern whole_words_allowing_dashes_at_least_2_chars_long = Pattern.compile("(\\w|-){2,}");
        Pattern whole_words_at_least_2_chars_long = Pattern.compile("[A-Za-z]{2,}");
        System.out.println(Paths.get(".").toAbsolutePath());
        long uniqueWords = Files.lines(Paths.get(
                                "src/main/java/nl/vea/functionalp/examples/m03/closer-look-at-streams.md"),
                        Charset.forName("UTF-8"))
                .flatMap(line -> Arrays.stream(line.split("[^-\\w]")))
                .filter(whole_words_allowing_dashes_at_least_2_chars_long.asPredicate())
                .distinct()
                .count();
        System.out.println("There are " + uniqueWords + " unique words in closer-look-at-streams.md");


        // https://www.baeldung.com/java-groupingby-count
        // https://docs.oracle.com/javase/8/docs/api/index.html?java/util/function/Function.html The identity default method
        Map<String, Long> map = Files.lines(Paths.get(
                                "src/main/java/nl/vea/functionalp/examples/m03/closer-look-at-streams.md"),
                        Charset.forName("UTF-8"))
                .flatMap(line -> Arrays.stream(line.split("[^-\\w]"))) //split non-word characters except dash
                .filter(whole_words_allowing_dashes_at_least_2_chars_long.asPredicate())
                .collect(groupingBy(Function.identity(), counting()));
        System.out.println("result:\t" + map);

        //Now rank words from most to least common
        //https://stackoverflow.com/questions/72582736/sort-the-map-entries-after-grouping-by-in-a-better-way-java8
        Map<String, Long> sortedValuesMap = map.entrySet().stream()
                .sorted(Map.Entry.comparingByKey()) // first order keys alphabetically
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed()) //then order value in descending order
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        System.out.println("result:\t" + sortedValuesMap);
    }
}
