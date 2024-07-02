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

# A closer look at streams
See also [`java.util.stream.Stream`](https://docs.oracle.com/javase/8/docs/api/index.html?java/util/stream/Stream.html)

## creating a stream
In most cases we can create a stream from a collection and we already saw a couple examples of those. In 
[BuildingStreams#main](BuildingStreams.java) we can see a summary of other ways we can create a stream.

## mapping
- Used to transform elements in a stream
- the map operator accepts (an implementation of) the 
  [`java.util.function.Function<T,R>`](https://docs.oracle.com/javase/8/docs/api/index.html?java/util/function/Function.html) as argument.
  - Its `R apply(T t)` method can be implemented/expressed as lambda or method reference. 
  - We can see that mapping can also transform the type from T to R, but T and R may be the same when the mapping just transforms
    the state of an element of type T.
### flat mapping
flat mapping is a special case of mapping that can be a bit confusing at first. With the map operation you expect your function
to return a single value of type R, but what if type R is a stream with multiple values or an Optional that may or may not contain
a value. This will disrupt the chaining of any further intermediate operations that are expected to act on a single stream of elements
and not on a stream of streams or a stream of objects encapsulated by Option objects.
To deal with these types of situation we use `flatMap` instead of `map`. The `flatMap` operator also accepts an implementation of
[`java.util.function.Function<T,R>`](https://docs.oracle.com/javase/8/docs/api/index.html?java/util/function/Function.html) as argument.
However,
* when the transformation of the mapping results in a stream of streams, all these streams are subsequently *flattened* into a single
  continuous stream of the elements in the inner streams,
* when the transformation of the mapping results in a stream of Optional objects, all these Optional objects are subsequently 
  *flattened* into a stream of all objects contained in these Optional objects.
### Some examples of mapping and flat-mapping
See [Mapping#main](Mapping.java)

## collecting
- In a lot of cases we need to collect the resulting elements of a stream in a new Collection
- the [`java.util.stream.Stream.collect()`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html#collect-java.util.stream.Collector-) 
  operator is a terminal operator and it has 
  [`java.util.stream.Collector<T,A,R>`](https://docs.oracle.com/javase/8/docs/api/index.html?java/util/stream/Collector.html) as its
  parameter.
- This method is overloaded with a 3 parameter one 
  [`java.util.stream.Stream.collect()`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html#collect-java.util.function.Supplier-java.util.function.BiConsumer-java.util.function.BiConsumer-) if you want to break down the collector and use the 3 building parts directly
- In complicated cases you can use the 3 parameter version or write your own implementation of `java.util.stream.Collector<T,A,R>`
  (which isn't easy) ,but in most situations you can use the static methods of the 
  [`java.util.stream.Collectors`](https://docs.oracle.com/javase/8/docs/api/index.html?java/util/stream/Collectors.html) class that return an implementation of 
  `java.util.stream.Collector<T,A,R>`. Commonly used examples:
  - [`Collectors#toList()`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collectors.html#toList--)
  - [`Collectors#toSet()`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collectors.html#toSet--)

## reducing
In many cases we have seen the result of a processed stream being collected into one of the available Java collections.
Another important use case, however, is to aggregate all elements of a stream and reduce them to a single value. For example
a total sum or average of a single attribute of objects that were first filtered based on another attribute value.
For some concrete examples see [Reducing#main](Reducing.java)

## Grouping
Grouping objects in a collection based on the value of one of their attributes is something that is not so easy to do imperatively
in a for loop, but quite easy with the functions of the streaming API.
For example, see [GroupingTransactions](GroupingTransactions.java) where we want to group Transaction objects from a List by currency.
This is done by grouping them into a Map where the key is the value of the currency and the value a list of all the Transaction objects
with that currency value. We do this once imperatively and once functionally with the help of a stream and
[static <T,K> Collector<T,?,Map<K,List<T>>> java.util.stream.Collectors#groupingBy(Function<? super T,? extends K> classifier)](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collectors.html#groupingBy-java.util.function.Function-)

## laziness of intermediate operations
**Intermediate operators are lazy.** This means they are evaluated and executed when the last operator or terminal operator is
executed. This may lead to optimizations in the order of execution and also to skipping operations on elements when they prove
to be redundant by the optimizations. This laziness is actually a very powerful feature that can result in much better performance
especially with streams derived from large datasets. Moreover, such optimizations are difficult to achieve with imperative programming.
See [Laziness#main](Laziness.java) for a concrete example, which is explained by the comments within the code.

## caveats: things to avoid
- You can consume a stream only once (as opposed to a collection)
- You cannot update shared mutable variables within a stream.
  - Remember in functional programming you cannot share globally mutable state, all mutable state has to encapsulated within
    the stream and only immutable data can pass in.
  - If you want to pass in a variable that has a wider scope you are asked by the compiler to make it immutable with the final keyword.
  - trying something like this will get a compiler error:
```java
List<Book> newBooks = new ArrayList<>();
// books are added to newBooks somehow
        
//getting your bookcollection        
List<Books> bookCollection = retrieveBookCollection();

//trying to add the new books to your collection like this will fail
newBooks.steam().foreach(book -> bookCollection.add(book));

```