package com.analyzer.ds;

import java.util.Scanner;

import com.analyzer.Main;

/**
 * Linked List component.
 * Singly linked list with insert (beginning/end), delete by value,
 * search and display.
 *
 * @author 23DA2-0938
 */
public class MyLinkedList {

    /** A single node of the list. */
    private static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node head = null; // first node (null means the list is empty)
    private int size = 0;

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        return size;
    }

    /** Insert a value at the beginning. O(1). */
    public void insertAtBeginning(int value) {
        Node newNode = new Node(value);
        newNode.next = head;
        head = newNode;
        size++;
    }

    /** Insert a value at the end. O(n) because we walk to the last node. */
    public void insertAtEnd(int value) {
        Node newNode = new Node(value);
        if (isEmpty()) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    /** Delete the first node holding the value. Returns false if not found or list is empty. */
    public boolean delete(int value) {
        if (isEmpty()) {
            System.out.println("Linked list is empty! Nothing to delete.");
            return false;
        }

        // Case 1: the value is in the head node
        if (head.data == value) {
            head = head.next;
            size--;
            return true;
        }

        // Case 2: the value is somewhere after the head
        Node current = head;
        while (current.next != null && current.next.data != value) {
            current = current.next;
        }
        if (current.next == null) {
            System.out.println("Value " + value + " not found in the list.");
            return false;
        }
        current.next = current.next.next;
        size--;
        return true;
    }

    /**
     * Search for a value. Returns its position (1 = first node),
     * or -1 if it is not found. O(n).
     */
    public int search(int value) {
        Node current = head;
        int position = 1;
        while (current != null) {
            if (current.data == value) {
                return position;
            }
            current = current.next;
            position++;
        }
        return -1;
    }

    /** Display the list from head to tail. */
    public void display() {
        if (isEmpty()) {
            System.out.println("Linked list is empty.");
            return;
        }
        System.out.print("Linked List: ");
        Node current = head;
        while (current != null) {
            System.out.print("[" + current.data + "] -> ");
            current = current.next;
        }
        System.out.println("null   (size " + size + ")");
    }

    /** Linked list submenu. */
    public void menu(Scanner sc) {
        boolean back = false;
        while (!back) {
            System.out.println();
            System.out.println("------------ LINKED LIST OPERATIONS ------------");
            System.out.println("1. Insert at Beginning");
            System.out.println("2. Insert at End");
            System.out.println("3. Delete by Value");
            System.out.println("4. Search");
            System.out.println("5. Display");
            System.out.println("6. Return to Main Menu");
            int choice = Main.readInt(sc, "Enter your choice: ");

            switch (choice) {
                case 1:
                    int first = Main.readInt(sc, "Enter value to insert at beginning: ");
                    insertAtBeginning(first);
                    System.out.println("Inserted " + first + " at the beginning.");
                    Main.log("LinkedList: inserted " + first + " at beginning");
                    break;
                case 2:
                    int last = Main.readInt(sc, "Enter value to insert at end: ");
                    insertAtEnd(last);
                    System.out.println("Inserted " + last + " at the end.");
                    Main.log("LinkedList: inserted " + last + " at end");
                    break;
                case 3:
                    if (isEmpty()) {
                        System.out.println("Linked list is empty! Nothing to delete.");
                        break;
                    }
                    int toDelete = Main.readInt(sc, "Enter value to delete: ");
                    if (delete(toDelete)) {
                        System.out.println("Deleted " + toDelete + ".");
                        Main.log("LinkedList: deleted " + toDelete);
                    }
                    break;
                case 4:
                    if (isEmpty()) {
                        System.out.println("Linked list is empty! Nothing to search.");
                        break;
                    }
                    int target = Main.readInt(sc, "Enter value to search: ");
                    int position = search(target);
                    if (position == -1) {
                        System.out.println(target + " was not found.");
                        Main.log("LinkedList: search " + target + " -> not found");
                    } else {
                        System.out.println(target + " found at position " + position + ".");
                        Main.log("LinkedList: search " + target + " -> position " + position);
                    }
                    break;
                case 5:
                    display();
                    break;
                case 6:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice! Please enter a number between 1 and 6.");
            }
        }
    }
}