# Syntax details and variations of lambda expressions & method references
Please note that all text in this markdown file is copied literally from: [**Functional Programming in Java**, 
Harnessing the Power of Java 8 Lambda Expressions, by *Venkat Subramaniam*, The Pragmatic Bookshelf
Dallas, Texas • Raleigh, North Carolina, Copyright © 2014 The Pragmatic Programmers,
LLC. ISBN-13: 978-1-937785-46-8](https://pragprog.com/titles/vsjava8/functional-programming-in-java/),
**Appendix 2: Syntax Overview.**

This book is recommended if you really want to dive deep into the subject of functional programming in Java.

I have added a bit of extra explanation in a few cases.

## Lambda expressions
### Creating No-Parameter Lambda Expressions
```java
lazyEvaluator(() -> evaluate(1), () -> evaluate(2));
```
The parentheses () around the empty parameters list are required if the
lambda expression takes no parameters. The -> separates the parameters
from the body of a lambda expression.

### Creating a Single-Parameter Lambda Expression
```java
friends.forEach((final String name) -> System.out.println(name));
```
The Java compiler can infer the type of lambda expression based on the
context. In some situations where the context is not adequate for it to infer
or we want better clarity, we can specify the type in front of the parameter
names.

### Inferring a Lambda Expression’s Parameter Type
```java
friends.forEach((name) -> System.out.println(name));
```
The Java compiler will try to infer the types for parameters if we don’t provide
them. Using inferred types is less noisy and requires less effort, but if we
specify the type for one parameter, we have to specify it for all parameters in
a lambda expression.

### Dropping Parentheses for a Single-Parameter Inferred Type
```java
friends.forEach(name -> System.out.println(name));
```
The parentheses () around the parameter are optional if the lambda expression
takes only one parameter and its type is inferred. We could write `name -> ...;` or
`(name) -> ...;` lean toward the first since it’s less noisy.

### Creating a Multi-Parameter Lambda Expression
```java
friends.stream()
    .reduce((name1, name2) ->
	name1.length() >= name2.length() ? name1 : name2);
```
The parentheses () around the parameter list are required if the lambda
expression takes multiple parameters or no parameters.

### Calling a Method with Mixed Parameters
```java
friends.stream()
    .reduce("Steve", (name1, name2) ->
    name1.length() >= name2.length() ? name1 : name2);
```
Methods can have a mixture of regular classes, primitive types, and functional
interfaces as parameters. Any parameter of a method may be a functional
interface, and we can send a lambda expression or a method reference as an
argument in its place. 
In the above example of the reduce method, the first parameter
holds a String object and the second parameter holds the 
[`java.util.function.BiFunction<T,U,R>`](https://docs.oracle.com/javase/8/docs/api/index.html?java/util/function/BiFunction.html) 
functional interface.
Therefore, only the second argument is a lambda expression.

### Storing a Lambda Expression
```java
final Predicate<String> startsWithN = name -> name.startsWith("N");
```
To aid reuse and to avoid duplication, we often want to store lambda expressions
in variables.

### Creating a Multiline Lambda Expression
```java
FileWriterEAM.use("eam2.txt", writerEAM -> {
    writerEAM.writeStuff("how");
    writerEAM.writeStuff("sweet");
});
```
We should keep the lambda expressions short, but it’s easy to sneak in a few
lines of code. We have to pay penance by using curly braces {}, and the return
keyword is required if the lambda expression is expected to return a value.

### Returning a Lambda Expression
```java
public static Predicate<String> checkIfStartsWith(final String letter) {
    return name -> name.startsWith(letter);
}
```
If a method’s return type is a functional interface, we can return a lambda
expression from within its implementation.

### Returning a Lambda Expression from a Lambda Expression
```java
final Function<String, Predicate<String>> startsWithLetter =
    letter -> name -> name.startsWith(letter);
```
We can build lambda expressions that themselves return lambda expressions.
The implementation of the Function interface here takes in a String letter and
returns a lambda expression that conforms to the Predicate interface.

### Lexical Scoping in Closures
```java
public static Predicate<String> checkIfStartsWith(final String letter) {
    return name -> name.startsWith(letter);
}
```
From within a lambda expression we can access variables that are in the
enclosing method’s scope. For example, the variable letter in the checkIfStartsWith()
is accessed within the lambda expression. Lambda expressions that bind to
variables in enclosing scopes are called closures.

## Method references
All the cases where we can use a method reference as a briefer alternative to a lambda expression.
### Passing a Method Reference of an Instance Method
```java
friends.stream().map(String::toUpperCase);
```
We can replace a lambda expression with a method reference if it directly
routes the parameter as a **target** to a simple method call. The Stream
consists of String type elements on which we call their toUpperCase() method in the map operation.
Hence, the preceding sample code is equivalent to this:
```java
friends.stream().map(name -> name.toUpperCase());
```

### Passing a Method Reference to a static Method
```java
str.chars().filter(Character::isDigit);
```
We can replace a lambda expression with a method reference if it directly
routes the parameter as an **argument** to a static method. The stream consists of elements
of type `char` that we can pass into the static `Character.isDigit()` method in the filter operation.
Hence, The preceding sample code is equivalent to this:
```java
str.chars().filter(ch -> Character.isDigit(ch));
```

### Passing a Method Reference to a Method on Another Instance
```java
str.chars().forEach(System.out::println);
```
We can replace a lambda expression with a method reference if it directly
routes the parameter as an **argument** to a method on another instance; for example, `println()` on `System.out`.
Here, the stream consists of elements of type `char` that we can pass into the `println()` method of `System.out` in the foreach operation.
Hence, the preceding sample code is equivalent to this:
```java
str.chars().forEach(ch -> System.out.println(ch));
```

### Passing a Reference of a Method That Takes Parameters
```java
people.stream().sorted(Person::ageDifference)
```
We can replace a lambda expression with a method reference if it directly
routes **the first parameter as a target** of a method call, and **the remaining
parameters as** this method’s **arguments**.
Here, the stream consists of elements of type `Person` that apparantly has a method `ageDifference(Person person)`
that takes another `Person` type object as parameter to compare their ages. This method can be used in the sorted operation
as it complies with the `int compare(T t)` method of
[`java.util.Comparator<T>`](https://docs.oracle.com/javase/8/docs/api/index.html?java/util/Comparator.html).
Hence, the preceding sample code is equivalent to this:
```java
people.stream().sorted((person1, person2) -> person1.ageDifference(person2))
```

### Using a Constructor Reference
```java
Supplier<Heavy> supplier = Heavy::new;
```
Instead of invoking a constructor, we can ask the Java compiler to create the
calls to the appropriate constructor from the concise constructor-reference syntax.
These work much like method references, except they refer to a constructor and
they result in object instantiation. The preceding sample code is equivalent to
this:
```java
Supplier<Heavy> supplier = () -> new Heavy();
```

### Function Composition
```java
symbols.map(StockUtil::getPrice)
    .filter(StockUtil.isPriceLessThan(500))
    .reduce(StockUtil::pickHigh)
    .get();
```
We can compose functions to transform objects through a series of operations
like in this example. In the functional style of programming, function composition
or chaining is a very powerful construct to implement associative
operations.
Like in Java Streams all intermediate operators (methods) on a stream result in a new Stream instance.
```java
List<Apple> inventory = Apple.getInventory();
inventory
    .stream()
	.filter(apple -> apple.getWeight() > 110)
	.collect(Collectors.toList());
```