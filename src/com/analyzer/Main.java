package com.analyzer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.analyzer.ds.ArrayOperations;
import com.analyzer.ds.MyLinkedList;
import com.analyzer.ds.MyQueue;
import com.analyzer.ds.MyStack;
import com.analyzer.graph.Graph;
import com.analyzer.perf.PerformanceAnalyzer;
import com.analyzer.search.SearchOperations;

/**
 * Main class - Data Structure & Graph Performance Analyzer.
 * Shows the main menu and sends the user to each component.
 */
public class Main {

    // Shared list: every component stores its results here ("Display All Results")
    private static final List<String> RESULTS = new ArrayList<>();

    /** Save a result line so it can be shown later from the main menu. */
    public static void log(String message) {
        RESULTS.add(message);
    }

    /** Read an integer safely. Repeats until the user enters a valid number. */
    public static int readInt(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = sc.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a whole number.");
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Created once, so data stays when the user returns to the main menu
        ArrayOperations arrayOps = new ArrayOperations();
        MyStack stack = new MyStack();
        MyQueue queue = new MyQueue();
        MyLinkedList linkedList = new MyLinkedList();
        SearchOperations searchOps = new SearchOperations();
        Graph graph = new Graph();
        PerformanceAnalyzer analyzer = new PerformanceAnalyzer();

        boolean running = true;
        while (running) {
            printMainMenu();
            int choice = readInt(sc, "Enter your choice: ");

            switch (choice) {
                case 1:
                    arrayOps.menu(sc);
                    break;
                case 2:
                    stack.menu(sc);
                    break;
                case 3:
                    queue.menu(sc);
                    break;
                case 4:
                    linkedList.menu(sc);
                    break;
                case 5:
                    searchOps.menu(sc);
                    break;
                case 6:
                    graph.menu(sc);
                    break;
                case 7:
                    analyzer.menu(sc);
                    break;
                case 8:
                    displayAllResults();
                    break;
                case 9:
                    running = false;
                    System.out.println("Thank you! Exiting program...");
                    break;
                default:
                    System.out.println("Invalid choice! Please enter a number between 1 and 9.");
            }
        }
        sc.close();
    }

    private static void printMainMenu() {
        System.out.println();
        System.out.println("=============================================");
        System.out.println("   DATA STRUCTURE & GRAPH ANALYZER");
        System.out.println("=============================================");
        System.out.println("1. Array Operations");
        System.out.println("2. Stack Operations");
        System.out.println("3. Queue Operations");
        System.out.println("4. Linked List Operations");
        System.out.println("5. Searching Operations");
        System.out.println("6. Graph Operations");
        System.out.println("7. Performance Comparison");
        System.out.println("8. Display All Results");
        System.out.println("9. Exit");
    }

    private static void displayAllResults() {
        System.out.println();
        System.out.println("=============================================");
        System.out.println("   ALL RESULTS");
        System.out.println("=============================================");
        if (RESULTS.isEmpty()) {
            System.out.println("No results recorded yet.");
            return;
        }
        for (int i = 0; i < RESULTS.size(); i++) {
            System.out.println((i + 1) + ". " + RESULTS.get(i));
        }
    }
}