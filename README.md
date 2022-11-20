# Mini-interpreter
we will consider a very simple compiler that can compile and execute a program written in a pseudo programming language.

Our compiler will parse and execute the program line by line and that's why we call it an interpreter.

Our interpreter can include two types of commands only:
 +let: is used to assign a value to a variable.
 +print: is used to display the value of a variable or the result of an expression.
 
The definition of a command line following the BNF form used in our project :

![Forme BNF](https://user-images.githubusercontent.com/68644652/202905795-6b271d21-1337-42df-a069-5d1d5d89952b.PNG)

How the interpreter works:
The user enters his program line by line. A command line cannot contain more than one command, and cannot be written on more than one line.
When the user hits the enter key on the keyboard, the command line entered is analyzed by the interpreter.
If there are no errors, the interpreter returns the result of the execution. This can be :
- The display of "ok" if the instruction is a let command
- The display of the value of a variable or a mathematical expression in the case of a print command

