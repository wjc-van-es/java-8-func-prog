package nl.vea.functionalp.examples.m03;

import static java.util.stream.Collectors.groupingBy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupingTransactions {

    public static List<Transaction> transactions = Arrays.asList(new Transaction(Currency.EUR, 1500.0),
            new Transaction(Currency.USD, 2300.0),
            new Transaction(Currency.GBP, 9900.0),
            new Transaction(Currency.EUR, 1100.0),
            new Transaction(Currency.JPY, 7800.0),
            new Transaction(Currency.CHF, 6700.0),
            new Transaction(Currency.EUR, 5600.0),
            new Transaction(Currency.USD, 4500.0),
            new Transaction(Currency.CHF, 3400.0),
            new Transaction(Currency.GBP, 3200.0),
            new Transaction(Currency.USD, 4600.0),
            new Transaction(Currency.JPY, 5700.0),
            new Transaction(Currency.EUR, 6800.0));

    public static void main(String... args) {
        groupImperatively();
        groupFunctionally();
        groupFunctionallySumValuesInList();
    }

    /**
     * group a list of transactions by their currency into a map, which has the currencies as key and a list of
     * the transactions of that currency as the corresponding value.
     */
    private static void groupImperatively() {
        Map<Currency, List<Transaction>> transactionsByCurrencies = new HashMap<>();
        for (Transaction transaction : transactions) {
            Currency currency = transaction.getCurrency();
            List<Transaction> transactionsForCurrency = transactionsByCurrencies.get(currency);
            if (transactionsForCurrency == null) {
                transactionsForCurrency = new ArrayList<>();
                transactionsByCurrencies.put(currency, transactionsForCurrency);
            }
            transactionsForCurrency.add(transaction);
        }

        System.out.println(transactionsByCurrencies);
    }

    private static void groupFunctionally() {
        Map<Currency, List<Transaction>> transactionsByCurrencies = transactions
                .stream()
                .collect(groupingBy(Transaction::getCurrency));
        System.out.println(transactionsByCurrencies);
    }

    private static void groupFunctionallySumValuesInList() {
        Map<Currency, Double> transactionsByCurrencies = transactions
                .stream()
                // the 1rst param of groupingBy creates a stream of Map.Entry<Currency, List<Transaction>> elements
                .collect(groupingBy(Transaction::getCurrency,
                        // the 2nd param of groupingBy reduces the original value part of
                        // Map.Entry<Currency, List<Transaction>>, the List<Transaction>
                        // elements to a single Double by adding all Transaction values
                        Collectors.reducing(0.0, Transaction::getValue, Double::sum)));
        System.out.println(transactionsByCurrencies);
    }

    public static class Transaction {

        private final Currency currency;
        private final double value;

        public Transaction(Currency currency, double value) {
            this.currency = currency;
            this.value = value;
        }

        public Currency getCurrency() {
            return currency;
        }

        public double getValue() {
            return value;
        }

        @Override
        public String toString() {
            return currency + " " + value;
        }

    }

    public enum Currency {
        EUR, USD, JPY, GBP, CHF
    }

}
