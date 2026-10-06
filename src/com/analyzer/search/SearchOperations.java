package com.analyzer.search;

import java.util.Random;
import java.util.Scanner;

import com.analyzer.Main;

/**
 * Searching component.
 * Linear Search O(n) and Binary Search O(log n), both counting the steps
 * (comparisons) so the two approaches can be compared.
 *
 * @author 23DA2-0725
 */
public class SearchOperations {

    private static final int CAPACITY = 1000;

    private final int[] data = new int[CAPACITY];
    private int size = 0;

    /** Result of a search: where it was found (-1 if not) and how many steps it took. */
    public static class SearchResult {
        public final int index;
        public final int steps;
        public final long nanos;

        SearchResult(int index, int steps, long nanos) {
            this.index = index;
            this.steps = steps;
            this.nanos = nanos;
        }
    }

    /** Linear search: check every element from the start. Works on any array. */
    public static SearchResult linearSearch(int[] arr, int count, int target) {
        long start = System.nanoTime();
        int steps = 0;
        for (int i = 0; i < count; i++) {
            steps++;
            if (arr[i] == target) {
                return new SearchResult(i, steps, System.nanoTime() - start);
            }
        }
        return new SearchResult(-1, steps, System.nanoTime() - start);
    }

    /** Binary search: repeatedly halve the range. The array MUST be sorted. */
    public static SearchResult binarySearch(int[] arr, int count, int target) {
        long start = System.nanoTime();
        int steps = 0;
        int low = 0;
        int high = count - 1;
        while (low <= high) {
            steps++;
            int mid = low + (high - low) / 2;
            if (arr[mid] == target) {
                return new SearchResult(mid, steps, System.nanoTime() - start);
            } else if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return new SearchResult(-1, steps, System.nanoTime() - start);
    }

    /** Check whether the first 'count' elements are in ascending order. */
    public static boolean isSorted(int[] arr, int count) {
        for (int i = 1; i < count; i++) {
            if (arr[i - 1] > arr[i]) {
                return false;
            }
        }
        return true;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /** Add a value at the end of the array. */
    public boolean add(int value) {
        if (size == CAPACITY) {
            System.out.println("Array is full! Cannot add " + value + ".");
            return false;
        }
        data[size++] = value;
        return true;
    }

    /** Sort using insertion sort (simple and easy to explain). */
    public void sort() {
        for (int i = 1; i < size; i++) {
            int key = data[i];
            int j = i - 1;
            while (j >= 0 && data[j] > key) {
                data[j + 1] = data[j];
                j--;
            }
            data[j + 1] = key;
        }
    }

    /** Replace the array with n sorted values (so binary search can be tested). */
    public void generateSample(int n) {
        Random random = new Random();
        size = 0;
        for (int i = 0; i < n; i++) {
            data[size++] = random.nextInt(10000);
        }
        sort();
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("Array is empty.");
            return;
        }
        System.out.print("Array (" + size + " elements): ");
        int limit = Math.min(size, 30);
        for (int i = 0; i < limit; i++) {
            System.out.print(data[i] + " ");
        }
        if (size > limit) {
            System.out.print("... (showing first 30)");
        }
        System.out.println();
    }

    private void printResult(String name, int target, SearchResult r) {
        if (r.index == -1) {
            System.out.println(name + ": " + target + " not found. Steps: " + r.steps
                    + ", Time: " + r.nanos + " ns");
        } else {
            System.out.println(name + ": " + target + " found at index " + r.index
                    + ". Steps: " + r.steps + ", Time: " + r.nanos + " ns");
        }
    }

    /** Searching submenu. */
    public void menu(Scanner sc) {
        boolean back = false;
        while (!back) {
            System.out.println();
            System.out.println("-------------- SEARCHING OPERATIONS --------------");
            System.out.println("1. Add Element");
            System.out.println("2. Display Array");
            System.out.println("3. Sort Array");
            System.out.println("4. Generate Sorted Sample Data");
            System.out.println("5. Linear Search");
            System.out.println("6. Binary Search");
            System.out.println("7. Compare Linear vs Binary");
            System.out.println("8. Return to Main Menu");
            int choice = Main.readInt(sc, "Enter your choice: ");

            switch (choice) {
                case 1:
                    int value = Main.readInt(sc, "Enter value to add: ");
                    if (add(value)) {
                        System.out.println("Added " + value + ".");
                    }
                    break;
                case 2:
                    display();
                    break;
                case 3:
                    if (isEmpty()) {
                        System.out.println("Array is empty! Nothing to sort.");
                        break;
                    }
                    sort();
                    System.out.println("Array sorted.");
                    display();
                    break;
                case 4:
                    int n = Main.readInt(sc, "How many elements (1-" + CAPACITY + ")? ");
                    if (n < 1 || n > CAPACITY) {
                        System.out.println("Invalid size! Enter a number between 1 and " + CAPACITY + ".");
                        break;
                    }
                    generateSample(n);
                    System.out.println("Generated " + n + " sorted elements.");
                    break;
                case 5:
                    if (isEmpty()) {
                        System.out.println("Array is empty! Add elements first.");
                        break;
                    }
                    int t1 = Main.readInt(sc, "Enter value to search: ");
                    SearchResult lin = linearSearch(data, size, t1);
                    printResult("Linear Search", t1, lin);
                    Main.log("Search: Linear " + t1 + " -> steps " + lin.steps);
                    break;
                case 6:
                    if (isEmpty()) {
                        System.out.println("Array is empty! Add elements first.");
                        break;
                    }
                    if (!isSorted(data, size)) {
                        System.out.println("Array is not sorted! Use option 3 to sort it first.");
                        break;
                    }
                    int t2 = Main.readInt(sc, "Enter value to search: ");
                    SearchResult bin = binarySearch(data, size, t2);
                    printResult("Binary Search", t2, bin);
                    Main.log("Search: Binary " + t2 + " -> steps " + bin.steps);
                    break;
                case 7:
                    if (isEmpty()) {
                        System.out.println("Array is empty! Add elements first.");
                        break;
                    }
                    if (!isSorted(data, size)) {
                        System.out.println("Array is not sorted! Use option 3 to sort it first.");
                        break;
                    }
                    int t3 = Main.readInt(sc, "Enter value to search: ");
                    SearchResult l = linearSearch(data, size, t3);
                    SearchResult b = binarySearch(data, size, t3);
                    System.out.println();
                    printResult("Linear Search", t3, l);
                    printResult("Binary Search", t3, b);
                    System.out.println("Binary search used " + (l.steps - b.steps)
                            + " fewer steps (O(n) vs O(log n)).");
                    Main.log("Search compare " + t3 + " -> Linear " + l.steps
                            + " steps, Binary " + b.steps + " steps");
                    break;
                case 8:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice! Please enter a number between 1 and 8.");
            }
        }
    }
}