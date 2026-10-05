package com.analyzer.ds;

import java.util.Scanner;

import com.analyzer.Main;

/**
 * Queue component (FIFO).
 * Array-based circular queue with enqueue, dequeue, peek and display.
 *
 * @author 23DA2-0812
 */
public class MyQueue {

    private static final int CAPACITY = 10;

    private final int[] data = new int[CAPACITY];
    private int front = 0; // index of the first element
    private int rear = 0;  // index where the next element will be inserted
    private int size = 0;  // current number of elements

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == CAPACITY;
    }

    /** Add a value at the rear. Fails if the queue is full (overflow). */
    public boolean enqueue(int value) {
        if (isFull()) {
            System.out.println("Queue Overflow! Queue is full.");
            return false;
        }
        data[rear] = value;
        rear = (rear + 1) % CAPACITY;
        size++;
        return true;
    }

    /** Remove and return the front value. Returns null if the queue is empty (underflow). */
    public Integer dequeue() {
        if (isEmpty()) {
            System.out.println("Queue Underflow! Cannot dequeue from an empty queue.");
            return null;
        }
        int value = data[front];
        front = (front + 1) % CAPACITY;
        size--;
        return value;
    }

    /** Return the front value without removing it. */
    public Integer peek() {
        if (isEmpty()) {
            System.out.println("Queue is empty! Nothing to peek.");
            return null;
        }
        return data[front];
    }

    /** Display from front to rear. */
    public void display() {
        if (isEmpty()) {
            System.out.println("Queue is empty.");
            return;
        }
        System.out.print("Queue (front -> rear): ");
        for (int i = 0; i < size; i++) {
            System.out.print("[" + data[(front + i) % CAPACITY] + "] ");
        }
        System.out.println();
    }

    /** Queue submenu. */
    public void menu(Scanner sc) {
        boolean back = false;
        while (!back) {
            System.out.println();
            System.out.println("--------------- QUEUE OPERATIONS ---------------");
            System.out.println("1. Enqueue");
            System.out.println("2. Dequeue");
            System.out.println("3. Peek (Front)");
            System.out.println("4. Display");
            System.out.println("5. Return to Main Menu");
            int choice = Main.readInt(sc, "Enter your choice: ");

            switch (choice) {
                case 1:
                    int value = Main.readInt(sc, "Enter value to enqueue: ");
                    if (enqueue(value)) {
                        System.out.println("Enqueued " + value + ".");
                        Main.log("Queue: enqueued " + value);
                    }
                    break;
                case 2:
                    Integer removed = dequeue();
                    if (removed != null) {
                        System.out.println("Dequeued " + removed + ".");
                        Main.log("Queue: dequeued " + removed);
                    }
                    break;
                case 3:
                    Integer frontValue = peek();
                    if (frontValue != null) {
                        System.out.println("Front element is " + frontValue + ".");
                        Main.log("Queue: peek -> " + frontValue);
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