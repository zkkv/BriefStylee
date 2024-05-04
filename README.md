# BriefStylee

## Description
A simple Java methods complexity evaluator and style checker.

## How to Build
1. Start by cloning the repository and navigating to the root folder.
2. Open the terminal.
3. Run one of the following.
- Mac/Linux:
```
./gradlew build
```
- Windows:
```
gradlew build
```
Alternatively, use an IDE in which you can execute Gradle `build` configuration.

## How to Run
Similarly to `build`, execute `run`. The `run` configuration will execute with a command line argument passed to the `main` method. That argument is the path to the file under the review. By default, it's a file `src/main/resources/Sample.java` but you can change it to anything else. You can also execute `test` to see all unit tests passing.

## Solution Summary
### General Idea
I used an existing library called Spoon that allows to build abstract syntax trees from `.java` files. Processing the input in plain text would be very complicated so would writing a parser myself. Spoon provides a very easy interface for traversing the tree. 

For the complexity evaluator part, I had to go through all the classes in the file and then go through each method. In the method body I count the number of elements which match the filter (only conditionals) and add that count to the result list along with the method name. The list is then sorted in decreasing order based on the counts and a slice of it is taken.

For the style checker part, I used a similar approach, but now I just counted the total number of methods and the number of methods where the name didn't match the camelCase regular expression. I then divided one by another.

Both classes are tested with unit tests. JavaDocs are there for the methods in the classes.

### Design Decisions
- I made an abstract class that handles the piece of logic of generating an AST and converting a relative path to an absolute path. My two classes inherit from it. That reduces code duplication and allows to add more classes easily.
- In `analyzeConditionals` I made the number of top results that need to be preserved a parameter. The assignment asks for three but any number can be used there.
- In `ComplexityEvaluator`, the filter variable can be easily modified to allow for checking for different constructs.
- In `checkStyle` I decided to return 0 when there are no methods at all. This is an arbitrary choice.

### Learning and Initiative
I applied my knowledge about DRY principle, clean coding style, good documentation and thorough testing.

I learned that there exist awesome AST-builder libraries out there and got to know how to work with files in Java and set up Gradle config a bit more than before.

## Developer
Developed by zkkv, 05/2024
