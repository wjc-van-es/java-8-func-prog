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
method implementation you may need. But this probably won't be necessary very often. 