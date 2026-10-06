package com.analyzer.perf;

import java.util.Scanner;

import com.analyzer.Main;
import com.analyzer.graph.Graph;
import com.analyzer.search.SearchOperations;
import com.analyzer.search.SearchOperations.SearchResult;

/**
 * Performance comparison component.
 * Compares Linear vs Binary search and BFS vs DFS using
 * the number of steps and the execution time.
 *
 * @author 23DA2-0724
 */
public class PerformanceAnalyzer {

    private static final int MAX_SEARCH_SIZE = 100000;
    private static final int MAX_GRAPH_SIZE = 1000; // DFS is recursive, so keep it safe

    private static final int DEFAULT_SEARCH_SIZE = 1000;
    private static final int DEFAULT_GRAPH_SIZE = 100;

    /** Build a sorted array 0, 2, 4, 6, ... with n elements. */
    private int[] buildSortedArray(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i * 2;
        }
        return arr;
    }

    /** Build a connected graph with n vertices (a chain plus extra shortcut edges). */
    private Graph buildGraph(int n) {
        Graph graph = new Graph();
        for (int i = 0; i < n; i++) {
            graph.addVertex();
        }
        for (int i = 1; i < n; i++) {
            graph.addEdge(i - 1, i);
        }
        for (int i = 3; i < n; i++) {
            graph.addEdge(i - 3, i);
        }
        return graph;
    }

    private void printHeader() {
        System.out.println();
        System.out.println("=============================================");
        System.out.println("   PERFORMANCE COMPARISON");
        System.out.println("=============================================");
        System.out.printf("%-16s %-16s %-10s %-8s %-10s%n",
                "Operation", "Algorithm", "Input size", "Steps", "Time (ns)");
        System.out.println("-------------------------------------------------------------");
    }

    private void printRow(String operation, String algorithm, int size, int steps, long nanos) {
        System.out.printf("%-16s %-16s %-10d %-8d %-10d%n", operation, algorithm, size, steps, nanos);
    }

    /** Compare linear and binary search on a sorted array of n elements (worst case: last element). */
    private void compareSearch(int n) {
        int[] arr = buildSortedArray(n);
        int target = arr[n - 1]; // worst case for linear search

        SearchResult lin = SearchOperations.linearSearch(arr, n, target);
        SearchResult bin = SearchOperations.binarySearch(arr, n, target);

        printRow("Search", "Linear Search", n, lin.steps, lin.nanos);
        printRow("Search", "Binary Search", n, bin.steps, bin.nanos);

        Main.log("Performance: Search n=" + n + " -> Linear " + lin.steps
                + " steps, Binary " + bin.steps + " steps");
    }

    /** Compare BFS and DFS on a graph with n vertices, starting from vertex 0. */
    private void compareTraversal(int n) {
        Graph graph = buildGraph(n);

        Graph.TraversalResult bfs = graph.bfs(0);
        Graph.TraversalResult dfs = graph.dfs(0);

        printRow("Graph Traversal", "BFS", n, bfs.steps, bfs.nanos);
        printRow("Graph Traversal", "DFS", n, dfs.steps, dfs.nanos);

        Main.log("Performance: Graph V=" + n + " E=" + graph.edgeCount()
                + " -> BFS " + bfs.steps + " steps, DFS " + dfs.steps + " steps");
    }

    private void printExplanation() {
        System.out.println();
        System.out.println("Why do the results differ?");
        System.out.println("- Linear search is O(n): it may check every element.");
        System.out.println("- Binary search is O(log n): it halves the range each step (needs sorted data).");
        System.out.println("- BFS and DFS are both O(V + E): each visits every vertex and examines every edge,");
        System.out.println("  so their step counts are equal. Only the visiting order and the time can differ.");
        System.out.println("- Time in ns can vary between runs (JVM, CPU load). Steps are the reliable measure.");
    }

    /** Performance submenu. */
    public void menu(Scanner sc) {
        boolean back = false;
        while (!back) {
            System.out.println();
            System.out.println("------------ PERFORMANCE COMPARISON ------------");
            System.out.println("1. Compare Linear vs Binary Search");
            System.out.println("2. Compare BFS vs DFS");
            System.out.println("3. Run Full Comparison (default sizes)");
            System.out.println("4. Return to Main Menu");
            int choice = Main.readInt(sc, "Enter your choice: ");

            switch (choice) {
                case 1:
                    int n = Main.readInt(sc, "Enter array size (1-" + MAX_SEARCH_SIZE + "): ");
                    if (n < 1 || n > MAX_SEARCH_SIZE) {
                        System.out.println("Invalid size! Enter a number between 1 and " + MAX_SEARCH_SIZE + ".");
                        break;
                    }
                    printHeader();
                    compareSearch(n);
                    printExplanation();
                    break;
                case 2:
                    int v = Main.readInt(sc, "Enter number of vertices (1-" + MAX_GRAPH_SIZE + "): ");
                    if (v < 1 || v > MAX_GRAPH_SIZE) {
                        System.out.println("Invalid size! Enter a number between 1 and " + MAX_GRAPH_SIZE + ".");
                        break;
                    }
                    printHeader();
                    compareTraversal(v);
                    printExplanation();
                    break;
                case 3:
                    printHeader();
                    compareSearch(DEFAULT_SEARCH_SIZE);
                    compareTraversal(DEFAULT_GRAPH_SIZE);
                    printExplanation();
                    break;
                case 4:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice! Please enter a number between 1 and 4.");
            }
        }
    }
}