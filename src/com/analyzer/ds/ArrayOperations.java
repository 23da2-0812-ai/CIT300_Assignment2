/**
 * Array component.
 * A fixed-size array with insert, delete, search and display operations.
 *
 * @author 23DA2-0725
 */
package com.analyzer.ds;

import java.util.Scanner;

import com.analyzer.Main;

/**
 * Array component.
 * A fixed-size array with insert, delete, search and display operations.
 */
public class ArrayOperations {

    private static final int CAPACITY = 20;

    private int[] data = new int[CAPACITY];
    private int size = 0; // number of elements currently stored

    /** Insert a value at a given position (0 to size). Elements shift right. */
    public boolean insert(int index, int value) {
        if (size == CAPACITY) {
            System.out.println("Array is full! Cannot insert.");
            return false;
        }
        if (index < 0 || index > size) {
            System.out.println("Invalid position! Enter a value between 0 and " + size + ".");
            return false;
        }
        for (int i = size; i > index; i--) {
            data[i] = data[i - 1]; // shift right
        }
        data[index] = value;
        size++;
        return true;
    }

    /** Delete the element at a given position. Elements shift left. */
    public boolean delete(int index) {
        if (size == 0) {
            System.out.println("Array is empty! Nothing to delete.");
            return false;
        }
        if (index < 0 || index >= size) {
            System.out.println("Invalid position! Enter a value between 0 and " + (size - 1) + ".");
            return false;
        }
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1]; // shift left
        }
        size--;
        return true;
    }

    /** Linear search. Returns the index, or -1 if not found. */
    public int search(int value) {
        for (int i = 0; i < size; i++) {
            if (data[i] == value) {
                return i;
            }
        }
        return -1;
    }

    /** Print all elements. */
    public void display() {
        if (size == 0) {
            System.out.println("Array is empty.");
            return;
        }
        System.out.print("Array: [");
        for (int i = 0; i < size; i++) {
            System.out.print(data[i]);
            if (i < size - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]  (size = " + size + ")");
    }

    /** Returns a copy of the stored elements (used by Searching and Performance). */
    public int[] toArray() {
        int[] copy = new int[size];
        for (int i = 0; i < size; i++) {
            copy[i] = data[i];
        }
        return copy;
    }

    public int getSize() {
        return size;
    }

    /** Array submenu. */
    public void menu(Scanner sc) {
        boolean back = false;
        while (!back) {
            System.out.println();
            System.out.println("--------------- ARRAY OPERATIONS ---------------");
            System.out.println("1. Insert");
            System.out.println("2. Delete");
            System.out.println("3. Search");
            System.out.println("4. Display");
            System.out.println("5. Return to Main Menu");
            int choice = Main.readInt(sc, "Enter your choice: ");

            switch (choice) {
                case 1:
                    int value = Main.readInt(sc, "Enter value to insert: ");
                    int index = Main.readInt(sc, "Enter position (0 to " + size + "): ");
                    if (insert(index, value)) {
                        System.out.println("Inserted " + value + " at position " + index + ".");
                        Main.log("Array: inserted " + value + " at position " + index);
                    }
                    break;
                case 2:
                    if (size == 0) {
                        System.out.println("Array is empty! Nothing to delete.");
                        break;
                    }
                    int delIndex = Main.readInt(sc, "Enter position to delete (0 to " + (size - 1) + "): ");
                    if (delete(delIndex)) {
                        System.out.println("Deleted element at position " + delIndex + ".");
                        Main.log("Array: deleted element at position " + delIndex);
                    }
                    break;
                case 3:
                    if (size == 0) {
                        System.out.println("Array is empty! Nothing to search.");
                        break;
                    }
                    int key = Main.readInt(sc, "Enter value to search: ");
                    int found = search(key);
                    if (found == -1) {
                        System.out.println(key + " not found in the array.");
                        Main.log("Array: search " + key + " -> not found");
                    } else {
                        System.out.println(key + " found at position " + found + ".");
                        Main.log("Array: search " + key + " -> found at position " + found);
                    }
                    break;
                case 4:
                    display();
                    break;
                case 5:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice! Please enter a number between 1 and 5.");
            }
        }
    }
}