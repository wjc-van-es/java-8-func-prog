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


# Java 8: functional programming with streams, lambda expressions and method references
## introduction
We want to have an overview of functional programming principles and best practices in using lambda expressions, 
method references and the new streams API. 

The emphasis will be on basic understanding with lots of code examples and in what situations functional programming is better or
more convenient than the imperative way and also explaining or arguing why that is. In this I believe in thinking for your self not 
in "well Sonar tells me to do it". Sonar can be a big help, but you (and your team) should always be able to make your own final decision
and be able to justify them.

## Content
We zoom in on a specific subject in each module subpackage under the root package 
`nl.vea.functionalp.examples` e.g `nl.vea.functionalp.examples.m01` etc.
For a detailed explanation check the markup file within module package.
* [introduction.md](src/main/java/nl/vea/functionalp/examples/m01/introduction.md)
* [functional-interfaces-examples.md](src/main/java/nl/vea/functionalp/examples/m02/functional-interfaces-examples.md)
* [lambda-expression-details.md](src/main/java/nl/vea/functionalp/examples/m02/lambda-expression-details.md)
* [closer-look-at-streams.md](src/main/java/nl/vea/functionalp/examples/m03/closer-look-at-streams.md)
* [excercises.md](src/main/java/nl/vea/functionalp/examples/m04/excercises.md)
* [appendix-leftover-examples.md](src/main/java/nl/vea/functionalp/examples/m05/appendix-leftover-examples.md)