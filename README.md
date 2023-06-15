# BruteForceProject

This project is a Java application that performs a brute force analysis of a backpack problem.

## Description

The application reads input data from a file specified as a command-line argument. The input file should contain the following information:

- The first line contains two space-separated integers: the backpack capacity and the number of items.
- The second line contains comma-separated values representing the values of the items.
- The third line contains comma-separated values representing the weights of the items.

The application then analyzes all possible combinations of items to find the best combination that fits within the backpack's capacity and yields the maximum total value.

## Prerequisites

- Java Development Kit (JDK) 16 or higher
- Apache Maven

## How to Run

1. Ensure that you have JDK 16 and Maven installed on your system.
2. Clone this repository or download the source code files.
3. Open a terminal or command prompt and navigate to the project directory.
4. Build the project using Maven by running the following command:
   ```mvn clean package```

5. Once the build is successful, you can run the application with the following command:

```bash
java -jar target/BruteForceProject-1.0-SNAPSHOT.jar files/backpack.txt
```


6. Follow the instructions provided by the application in the console.

## Input File Format

The input file should follow the format described below:

- `<backpack-capacity>`: An integer representing the capacity of the backpack.
- `<number-of-items>`: An integer representing the number of items.
- `<item-values>`: Comma-separated integers representing the values of the items.
- `<item-weights>`: Comma-separated integers representing the weights of the items.

Example input file content:

```
50 25
1,9,5,13,3,3,6,4,11,17,7,2,3,6,1,9,7,15,1,2,3,20,5,6,12
11,6,2,13,4,5,7,18,9,12,2,3,15,7,5,1,2,10,8,5,17,3,7,7,2
```

## Dependencies

- [Lombok](https://projectlombok.org/): Used for generating boilerplate code, such as getters, setters, and constructors.

