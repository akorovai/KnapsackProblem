package src.akorovai.bruteforce;

import src.akorovai.bruteforce.entity.Backpack;
import src.akorovai.bruteforce.entity.Item;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.IntStream;

public class App {
    public static void main(String[] args)  {

        String filePath = args[0];
        String[] splittedContent;
        try {
            System.out.println("Reading the file...");
            splittedContent = readFileLines(filePath);
            System.out.println("Reading successfully completed!\n");
            System.out.println("Preparing data for analysis...");
        } catch (IOException e) {
            throw new RuntimeException("Problem during reading the file: " + filePath);
        }

        Backpack backpack = new Backpack(Integer.parseInt(splittedContent[0].split(" ")[0]));
        int numberOfItems = Integer.parseInt(splittedContent[0].split(" ")[1]);

        String[] values = splittedContent[1].split(",");
        String[] weights = splittedContent[2].split(",");

        if (numberOfItems != values.length || numberOfItems != weights.length) {
            throw new RuntimeException("Incorrect input data: number of items does not match values and weights!");
        }

        List<Item> items = new ArrayList<>(IntStream.range(0, numberOfItems)
                .mapToObj(i -> new Item(i + 1, Integer.parseInt(values[i]), Integer.parseInt(weights[i])))
                .toList());
        Scanner scanner = new Scanner(System.in);

        System.out.print("Do you want to shuffle the list of items? (yes/no): ");
        String userInput = scanner.nextLine().toLowerCase();

        if (userInput.equals("yes")) {
            Collections.shuffle(items);
            System.out.println("List of items shuffled successfully!");
        } else {
            System.out.println("List of items remains unchanged.");
        }

        System.out.println();
        System.out.println("Preparing successfully completed!\n");

        System.out.println("Start Analyzing!");
        int numberOfCases = (int) Math.pow(2,numberOfItems);
        int totalWeight;
        int totalValue;
        int bestValueCase = 0;
        List<Integer> bestIndexes = new ArrayList<>();

        for (int i = 0; i < numberOfCases-1; i++) {
            StringBuilder currentCase = new StringBuilder(Integer.toBinaryString(i + 1)).reverse();
            totalWeight = 0;
            totalValue = 0;
            List<Integer> indexes = new ArrayList<>();

            for (int j = 0; j < currentCase.length(); j++) {
                if (currentCase.charAt(j) == '1') {
                    totalWeight += items.get(j).getWeight();
                    totalValue += items.get(j).getValue();
                    indexes.add(j);

                    if (totalWeight > backpack.getCapacity()) {
                        break;
                    }
                }
            }

            if (totalWeight <= backpack.getCapacity() && totalValue > bestValueCase) {
                bestValueCase = totalValue;
                bestIndexes = new ArrayList<>(indexes);
            }
        }
        System.out.println("Analysis finished successfully!");

        System.out.println("Press Enter to show results...");

        App.waitForEnterKey();

        App.printBestResult(bestIndexes, items);

    }

    public static String[] readFileLines(String filePath) throws IOException {
        File file = new File(filePath);
        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);

        String[] lines = reader.lines().toArray(String[]::new);

        reader.close();

        return lines;
    }
    public static void waitForEnterKey() {
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        scanner.close();
    }
    public static void printBestResult(List<Integer> bestIndexes, List<Item> items) {

        var resultInfo = new Object(){
            int totalWeight = 0;
            int totalValue = 0;
        };

        System.out.println("Best Result Case:");
        bestIndexes.stream()
                .map(items::get)
                .peek(System.out::println)
                .forEach(item -> {
                    resultInfo.totalWeight += item.getWeight();
                    resultInfo.totalValue += item.getValue();
                });

        System.out.println("Total Weight: " + resultInfo.totalWeight);
        System.out.println("Total Value: " + resultInfo.totalValue);
    }
}
