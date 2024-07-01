# Simple Search Engine (Kotlin)

Simple Search Engine is one of the projects in Kotlin Developer track at Hyperskill.

[Open poroject at Hyperskill](https://hyperskill.org/projects/89)

As the name sounds, it is a basic search-engine
program that uses terminal to work with given data, search for specific 
keywords and apply different searching strategies. 

## Stage 1/6:

A very basic searching engine!
Takes a list of space-separated string as the first input, and a string as the next input.
It returns the position of that string in the list if it is present.

Run either [main.kt](src/Main.kt) with the input `1` or, [Stage1.kt](search/Stage1.kt) file

input

    roses daises dahlias
    daises

output

    2

input 
    
    Germany Russia Czechia Netherlands 
    SaintPetersburg

output 

    Not found

## Stage 2/6:
In this stage, the program reads text lines from the standard input and processes single-word queries.
The program outputs all lines that contain the string from the query. 

Run [main.kt](src/Main.kt) and input `2`, or [Stage2.kt](search/Stage2.kt).

Example

    Enter the number of people:
    > 6
    Enter all people:
    > Dwight Joseph djo@gmail.com
    > Rene Webb webb@gmail.com
    > Katie Jacobs
    > Erick Harrington harrington@gmail.com
    > Myrtle Medina
    > Erick Burgess
    
    Enter the number of search queries:
    > 3
    
    Enter data to search people:
    > ERICK
    
    People found:
    Erick Harrington harrington@gmail.com
    Erick Burgess
    
    Enter data to search people:
    > unknown
    No matching people found.
    
    Enter data to search people:
    > WEBB@gmail.com
    
    People found:
    Rene Webb webb@gmail.com

## Stage 3/6:
This stage includes extending the previous stage by adding a user menu for a better experience

Run [main.kt](src/Main.kt) and input `3`, or [Stage3.kt](search/Stage3.kt).

Example

    Enter the number of people:
    > 6
    Enter all people:
    > Dwight Joseph djo@gmail.com
    > Rene Webb webb@gmail.com
    > Katie Jacobs
    > Erick Harrington harrington@gmail.com
    > Myrtle Medina
    > Erick Burgess
    
    === Menu ===
    1. Find a person
       2. Print all people
       0. Exit
    > 3
    
    Incorrect option! Try again.
    
    === Menu ===
    1. Find a person
       2. Print all people
       0. Exit
    > 1
    
    Enter a name or email to search all suitable people.
    > KATIE
    Katie Jacobs
    
    === Menu ===
    1. Find a person
       2. Print all people
       0. Exit
    > 2
    
    === List of people ===
    Dwight Joseph djo@gmail.com
    Rene Webb webb@gmail.com
    Katie Jacobs
    Erick Harrington harrington@gmail.com
    Myrtle Medina
    Erick Burgess
    
    === Menu ===
    1. Find a person
       2. Print all people
       0. Exit
    > 0
    
    Bye!


