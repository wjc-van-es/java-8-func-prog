# Functional Interfaces
There are more functional interfaces than you may think. Of course, there are all new ones introduced in the
[`java.util.function`](https://docs.oracle.com/javase/8/docs/api/java/util/function/package-frame.html) package,
but there are older ones: Interfaces that just happen to have but one abstract method to implement.
## Examples of older functional interfaces
* [`java.awt.event.ActionListener`](https://docs.oracle.com/javase/8/docs/api/index.html?java/awt/event/ActionListener.html)
  This has one abstract method `void actionPerformed(ActionEvent e)`.
* [`java.lang.Runnable`](https://docs.oracle.com/javase/8/docs/api/index.html?java/lang/Runnable.html)
  This has one abstract method `void run()`.
* [`java.util.concurrent.Callable<V>`](https://docs.oracle.com/javase/8/docs/api/index.html?java/util/concurrent/Callable.html)
  This has one abstract method `V call()`.

Therefore, all these single abstract methods of these interfaces can be implemented by a lambda expression instead of an anonymous
inner class.

## Code examples
As an example we use a very simple Swing application consisting of only one class [`CelsiusConverterGUI`](CelsiusConverterGUI.java).
It has one `java.awt.event.ActionListener` implemented as anonymous inner class at line 91 and one `java.lang.Runnable`
implementation at line 154.
In [`CelsiusConverterGUILambda`](CelsiusConverterGUILambda.java) we replaced these anonymous inner classes with a method reference at line 94 and a lambda
expression at line 153, respectively.

## Create your own functional interfaces
Of course if none of the existing functional interfaces meet your needs, you can always create your own, complete with any default
method implementation you may need. But this probably won't be very often. 