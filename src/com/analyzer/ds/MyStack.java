package com.analyzer.ds;

import java.util.Scanner;

import com.analyzer.Main;

/**
 * Stack component (LIFO).
 * Array-based stack with push, pop, peek and display.
 *
 * @author 23DA2-0812
 */
public class MyStack {

    private static final int CAPACITY = 10;

    private int[] data = new int[CAPACITY];
    private int top = -1; // index of the top element (-1 means empty)

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == CAPACITY - 1;
    }

    /** Push a value on top. Fails if the stack is full (overflow). */
    public boolean push(int value) {
        if (isFull()) {
            System.out.println("Stack Overflow! Stack is full.");
            return false;
        }
        data[++top] = value;
        return true;
    }

    /** Remove and return the top value. Returns null if the stack is empty (underflow). */
    public Integer pop() {
        if (isEmpty()) {
            System.out.println("Stack Underflow! Cannot pop from an empty stack.");
            return null;
        }
        return data[top--];
    }

    /** Return the top value without removing it. */
    public Integer peek() {
        if (isEmpty()) {
            System.out.println("Stack is empty! Nothing to peek.");
            return null;
        }
        return data[top];
    }

    /** Display from top to bottom. */
    public void display() {
        if (isEmpty()) {
            System.out.println("Stack is empty.");
            return;
        }
        System.out.println("Stack (top -> bottom):");
        for (int i = top; i >= 0; i--) {
            System.out.println("  | " + data[i] + " |");
        }
        System.out.println("  -------");
    }

    /** Stack submenu. */
    public void menu(Scanner sc) {
        boolean back = false;
        while (!back) {
            System.out.println();
            System.out.println("--------------- STACK OPERATIONS ---------------");
            System.out.println("1. Push");
            System.out.println("2. Pop");
            System.out.println("3. Peek");
            System.out.println("4. Display");
            System.out.println("5. Return to Main Menu");
            int choice = Main.readInt(sc, "Enter your choice: ");

            switch (choice) {
                case 1:
                    int value = Main.readInt(sc, "Enter value to push: ");
                    if (push(value)) {
                        System.out.println("Pushed " + value + ".");
                        Main.log("Stack: pushed " + value);
                    }
                    break;
                case 2:
                    Integer popped = pop();
                    if (popped != null) {
                        System.out.println("Popped " + popped + ".");
                        Main.log("Stack: popped " + popped);
                    }
                    break;
                case 3:
                    Integer topValue = peek();
                    if (topValue != null) {
                        System.out.println("Top element is " + topValue + ".");
                        Main.log("Stack: peek -> " + topValue);
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