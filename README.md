# SnailCompiler
A basic Compiler of a custom programming language called Snail


## Langage specifications 
- One and only one instruction per line
- Program always starts with "Snl_Start" and ends with "Snl_Close"
- A comment can only be contained in one line, and must start with "%.."
- An instruction always ends with "%."
- Regular expressions corresponding to variables types of this language are : 

Type | Description | RegEx 
-----| ------------| ---------
Identifier | Starts with a letter followed by one AlphaNum character or more | `{letter}(_?({letter}\|{digit})+)*` 
Integer number | Composed of at least one digit  |  `[0-9]+`
Real number | Composed of atleast two integers separated by a point "." |  `[0-9]+\.[0-9]+`

- Here are some of the language's commands : 

Command | Description | Example 
------- | ----------- | ---------------
Assignment of a number (Integer/Real) to a variable | Set <<identifier>> <<value>> %. | `Set i 23 %.`
Assignment of the value of a variable to another variable | Get <<identifier>> from <<identifier>> %. | `Get j from i %.`
Condition | If % condition % action %. | `If % i > j % Set Aft_5 10 %.`


## Code example
`Snl_Start`<br/>
`Snl_Int` i,j,Aft_5,f_f_5%.<br/>
`Set` i 23 %.<br/>
`Snl_Real` Aft34_2 %.<br/>
`If` % i > j % `Set` Aft_5 10 %.<br/>
`Else`<br/>
`Start`<br/>
`Get` j `from` i %.<br/>
`Set` Af34_2 123.54 %.<br/>
`Finish`<br/>
`Snl_Put` "This is a string" %.<br/>
`Snl_Put` i %.<br/>
`%..` This is a comment<br/>
`Snl_Close`
