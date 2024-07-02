<style>
body {
  font-family: "Gentium Basic", Cardo , "Linux Libertine o", "Palatino Linotype", Cambria, serif;
  font-size: 130% !important;
}
code {
	padding: 0 .25em;
	
	white-space: pre;
	font-family: "Tlwg mono", Consolas, "Liberation Mono", Menlo, Courier, monospace;
	
	background-color: #ECFFFA;
	//border: 1px solid #ccc;
	//border-radius: 3px;
}

kbd {
	display: inline-block;
	padding: 3px 5px;
	font-family: "Tlwg mono", Consolas, "Liberation Mono", Menlo, Courier, monospace;
	line-height: 10px;
	color: #555;
	vertical-align: middle;
	background-color: #ECFFFA;
	border: solid 1px #ccc;
	border-bottom-color: #bbb;
	border-radius: 3px;
	box-shadow: inset 0 -1px 0 #bbb;
}

h1,h2,h3,h4,h5 {
  color: #269B7D; 
  font-family: "fira sans", "Latin Modern Sans", Calibri, "Trebuchet MS", sans-serif;
}

</style>

# What is a functional programming? And how is it implemented in the Java language (since version 8)?
## Short explanation
In functional programming emphasis is on *what* must be done rather than *how*.
- Queries in SQL or XPath expressions are an example of this.
- Since Java 8 we can express this as lambda expressions (we will see lots of examples, later on)
- The opposite of functional programming is imperative programming, where you have to specify exactly *how* things
  should be done.
  - e.g. when you iterate over a collection in a for loop, and you need to
    - filter out certain elements,
    - sort them,
    - group them,
  then you need to specify exactly how this should be done.

Strict functional programming also has limiting rules like encapsulating variables, which means limiting the scope of accessibility.
So we aren't able to share globally kept state among different parts of the code. This restriction, although not 
enforced, is generally a good thing in imperative coding as well, because it
  - makes thread contained concurrent programming possible,
  - is more secure,
  - less error-prone.

We will see some of these restrictions when we use a functional programming style later on. In Java, however, the imperative 
programming style is still available, and you can decide where you need to use either functional or imperative programming.
Moreover, functional programming is *not* the opposite of object-oriented programming, and you can mix these styles.
In fact, you have little choice, since (contrary to functional programming) OOP *is* strongly enforced in Java.

## A simple use case
To make the previous, rather abstract, explanation more concrete we start with a simple use case as example. And we will go from an 
imperative style to a functional programming style in small steps of improvement. 
- we have a simple inventory, a list of [`Apple`](Apple.java) objects
- now we need to split the inventory into subsets of Apples based on specific characteristics

### First naive attempt [`FilteringApplesVersion01`](FilteringApplesVersion01.java)
#### What do we notice?
- For each filter criterium we write a new method.
- All methods look a lot alike, there is a lot of code duplication,
  - therefore, we violate the DRY (Do Not Repeat Yourself) design principle.
  - This could get tedious in real life when a lot of different filter criteria may exist.
  - This impairs maintainability as a functional change could warrant modification in lots of
    places in the code.

#### How could we improve this?
Is there a way to extract the filter criterium code from the code iterating over all [`Apple`](Apple.java)
items? Then we could write one generic `filterApples(List<Apple> inventory, {filter criterium function})`,
which we would reuse for all different filter criteria use cases we have.

##### We are used to inject data arguments into a method as argument, but what would `{filter criterium function}` look like?
- Since java is Object-Oriented, a function would be an object as well, implemented by a class and declared by an interface.
- As it happens the [`Predicate<T>`](https://docs.oracle.com/javase/8/docs/api/index.html?java/util/function/Predicate.html) interface
  could fit the bill:
  - it has one abstract method `boolean test(T t)`, which could be implemented to examine an Apple object (as the T
    object) for different criteria. Whenever the criterion is met the method would return `true` otherwise it would
    return `false`.

### The second attempt [`FilteringApplesVersion02`](FilteringApplesVersion02.java)
- This is done in [`FilteringApplesVersion02.filterApples(List<Apple> inventory, Predicate<Apple> predicate)`](FilteringApplesVersion02.java)
- We separated the thing that varied: the filter criterium from the thing that stayed the same: iterating over the inventory and 
  creating and filling a new list filled with every apple that meets the criterion.
- So now for each criterium we implement the `Predicate<Apple>` interface. 

#### Room for further improvement with new Java 8 features
- We were used to implementing an interface as a complete class definition and an anonymous inner class was the most concise  way to do so.
- However, Predicate is a special interface, a **functional interface**. 
  - Any interface that has just 1 abstract method declared is a functional
    interface.
  - The one abstract method of a functional interface can be implemented by a lambda expression as all information you omit in that
    lambda expression can be inferred by the Java compiler.
- Therefore, in a further improved version we can use a lambda expression as substitute for the anonymous inner class definitions.

#### The Predicate interface has more new features
- An annotation [`@FunctionalInterface`](https://docs.oracle.com/javase/8/docs/api/java/lang/FunctionalInterface.html), this is not
  required for a functional interface, but it makes the intention clear.
- Moreover, the `Predicate<T>` interface has several methods with a default keyword, that have a default implementation.
  - Interfaces with methods that are not abstract, but have a default implementation are new to Java 8. 
    - This was introduced to be backward compatible.
    - This makes an interface a little less a clean interface declaration and more akin to an abstract class
    - Default methods cannot be implemented with a lambda expression only the one abstract method can (and if there are
      more than one abstract methods the interface is *not* functional).

### The third attempt [`FilteringApplesVersion03`](FilteringApplesVersion03.java)
- In [`FilteringApplesVersion03.main(String... args)`](FilteringApplesVersion03.java) the Predicate implementations are lambda expressions.
- A second improvement in [`FilteringApplesVersion03.filterApples(List<Apple> inventory, Predicate<Apple> predicate)`](FilteringApplesVersion03.java)
  is the replacement of the imperative for loop by a stream or more precisely an implementation of the
  [`java.util.stream.Stream<T>`](https://docs.oracle.com/javase/8/docs/api/index.html?java/util/stream/Stream.html) interface.

## A short introduction to streams
A Java stream is a pipeline of functions that can be evaluated. It is tempting to think of a stream as a data structure, where all
the elements are stored in memory and can potentially be mutated, but that is *not* what they are. Therefore, streams cannot mutate 
the data of the elements they process; they can only transform data.
Mutating data means changing the value of the memory space to which a variable is pointing. Transforming data
means processing it as (immutable) input and either writing the resulting output to a new variable (new memory space) or using it as 
input for a next step in the pipeline.

### The structure of a stream
- Streams can be constructed in many ways, constructing a stream from a collection is very common, but there are more ways. It is even
  possible to create a (potentially) infinite stream, i.e. a stream that can keep processing an infinite number of elements, which is
  something you cannot accomplish with a collection data structure as these must always occupy a finite memory space. 
- After construction, we can link subsequent operators to a stream
  - These operators usually take a function (implementation of a functional interface) as argument and these functions
    then are applied to all the data elements that are processed through the stream, whereby these elements are
    transformed not mutated (as we mentioned earlier)
  - The functional interfaces are most conveniently implemented as lambda expressions or even method references whenever possible
    - these lambda expressions usually don't need to specify type information of the elements they process as that can be inferred.
  - All but the last operator are **intermediate operators**.
    - Intermediate operators will return a stream reference on which we can call another operator, thus providing for the
      **method chaining pattern**,
    - **Intermediate operators are lazy.** This means they are evaluated and executed when the last operator or 
      **terminal operator** is executed. This may lead to optimizations in the order of execution and also to skipping
      operations on elements when they prove to be redundant by the optimizations. We will see a concrete example of
      this later. This laziness is actually a very powerful feature that can result in much better performance 
      especially with streams derived from large datasets. 
      Moreover, such optimizations are difficult to achieve with imperative programming.
    - examples of intermediate operators:
      - `filter`
      - `map`
      - `distinct`
      - `limit`
      - `sorted`
  - The last operator must be a **terminal operator**. These operators
    - must return a concrete type or produce a side effect,
    - are eagerly executed, triggering the evaluation, optimization and execution of all the previous intermediate operations.
    - Examples of terminal operators:
      - `reduce`
      - `collect`
      - `forEach`

In module 3 we will zoom in on various aspects of streams and their operators. The other modules have more emphasis on 
the applications of streams: [closer-look-at-streams.md](../m03/closer-look-at-streams.md)