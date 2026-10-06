package com.analyzer.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;

import com.analyzer.Main;

/**
 * Graph component.
 * Undirected graph stored as an adjacency list.
 * BFS and DFS both count their steps so they can be compared.
 *
 * @author 23DA2-0724
 */
public class Graph {

    /** Result of a traversal: visiting order, steps and time taken. */
    public static class TraversalResult {
        public final String order;
        public final int steps;
        public final long nanos;

        TraversalResult(String order, int steps, long nanos) {
            this.order = order;
            this.steps = steps;
            this.nanos = nanos;
        }
    }

    // adjacency.get(v) holds all neighbours of vertex v
    private final ArrayList<ArrayList<Integer>> adjacency = new ArrayList<>();
    private int edgeCount = 0;

    // Used only while DFS is running
    private int dfsSteps;
    private StringBuilder dfsOrder;

    public int vertexCount() {
        return adjacency.size();
    }

    public int edgeCount() {
        return edgeCount;
    }

    public boolean isEmpty() {
        return adjacency.isEmpty();
    }

    public boolean isValidVertex(int v) {
        return v >= 0 && v < adjacency.size();
    }

    /** Add a new vertex. Vertices are numbered 0, 1, 2, ... automatically. */
    public int addVertex() {
        adjacency.add(new ArrayList<>());
        return adjacency.size() - 1;
    }

    /** Add an undirected edge between u and v. Returns false if it is invalid. */
    public boolean addEdge(int u, int v) {
        if (!isValidVertex(u) || !isValidVertex(v)) {
            System.out.println("Invalid vertex! Vertices must be between 0 and " + (vertexCount() - 1) + ".");
            return false;
        }
        if (u == v) {
            System.out.println("Self-loops are not allowed.");
            return false;
        }
        if (adjacency.get(u).contains(v)) {
            System.out.println("Edge " + u + " - " + v + " already exists.");
            return false;
        }
        adjacency.get(u).add(v);
        adjacency.get(v).add(u);
        edgeCount++;
        return true;
    }

    /** Display the graph as an adjacency list. */
    public void display() {
        if (isEmpty()) {
            System.out.println("Graph is empty.");
            return;
        }
        System.out.println("Graph (" + vertexCount() + " vertices, " + edgeCount + " edges):");
        for (int v = 0; v < adjacency.size(); v++) {
            System.out.println("  " + v + " -> " + adjacency.get(v));
        }
    }

    /**
     * Breadth-First Search from the start vertex (uses a queue).
     * Steps = vertices visited + edges examined. O(V + E).
     */
    public TraversalResult bfs(int start) {
        long begin = System.nanoTime();
        int steps = 0;
        StringBuilder order = new StringBuilder();
        boolean[] visited = new boolean[vertexCount()];
        Queue<Integer> queue = new ArrayDeque<>();

        visited[start] = true;
        queue.add(start);
        while (!queue.isEmpty()) {
            int current = queue.poll();
            steps++; // visiting a vertex
            order.append(current).append(" ");
            for (int neighbour : adjacency.get(current)) {
                steps++; // examining an edge
                if (!visited[neighbour]) {
                    visited[neighbour] = true;
                    queue.add(neighbour);
                }
            }
        }
        return new TraversalResult(order.toString().trim(), steps, System.nanoTime() - begin);
    }

    /**
     * Depth-First Search from the start vertex (uses recursion = call stack).
     * Steps = vertices visited + edges examined. O(V + E).
     */
    public TraversalResult dfs(int start) {
        long begin = System.nanoTime();
        dfsSteps = 0;
        dfsOrder = new StringBuilder();
        boolean[] visited = new boolean[vertexCount()];
        dfsVisit(start, visited);
        return new TraversalResult(dfsOrder.toString().trim(), dfsSteps, System.nanoTime() - begin);
    }

    private void dfsVisit(int current, boolean[] visited) {
        visited[current] = true;
        dfsSteps++; // visiting a vertex
        dfsOrder.append(current).append(" ");
        for (int neighbour : adjacency.get(current)) {
            dfsSteps++; // examining an edge
            if (!visited[neighbour]) {
                dfsVisit(neighbour, visited);
            }
        }
    }

    /** Replace the graph with a fixed sample graph (6 vertices) for quick testing. */
    public void loadSampleGraph() {
        adjacency.clear();
        edgeCount = 0;
        for (int i = 0; i < 6; i++) {
            addVertex();
        }
        addEdge(0, 1);
        addEdge(0, 2);
        addEdge(1, 3);
        addEdge(2, 4);
        addEdge(3, 5);
        addEdge(4, 5);
    }

    private void printTraversal(String name, int start, TraversalResult r) {
        System.out.println(name + " from vertex " + start + ": " + r.order);
        System.out.println("Steps: " + r.steps + ", Time: " + r.nanos + " ns");
    }

    /** Graph submenu. */
    public void menu(Scanner sc) {
        boolean back = false;
        while (!back) {
            System.out.println();
            System.out.println("--------------- GRAPH OPERATIONS ---------------");
            System.out.println("1. Add Vertex");
            System.out.println("2. Add Edge");
            System.out.println("3. Display Graph");
            System.out.println("4. BFS Traversal");
            System.out.println("5. DFS Traversal");
            System.out.println("6. Load Sample Graph");
            System.out.println("7. Return to Main Menu");
            int choice = Main.readInt(sc, "Enter your choice: ");

            switch (choice) {
                case 1:
                    int id = addVertex();
                    System.out.println("Added vertex " + id + ".");
                    Main.log("Graph: added vertex " + id);
                    break;
                case 2:
                    if (vertexCount() < 2) {
                        System.out.println("Add at least 2 vertices first.");
                        break;
                    }
                    int u = Main.readInt(sc, "Enter first vertex: ");
                    int v = Main.readInt(sc, "Enter second vertex: ");
                    if (addEdge(u, v)) {
                        System.out.println("Added edge " + u + " - " + v + ".");
                        Main.log("Graph: added edge " + u + " - " + v);
                    }
                    break;
                case 3:
                    display();
                    break;
                case 4:
                    if (isEmpty()) {
                        System.out.println("Graph is empty! Add vertices first.");
                        break;
                    }
                    int s1 = Main.readInt(sc, "Enter start vertex: ");
                    if (!isValidVertex(s1)) {
                        System.out.println("Invalid vertex! Enter 0 to " + (vertexCount() - 1) + ".");
                        break;
                    }
                    TraversalResult b = bfs(s1);
                    printTraversal("BFS", s1, b);
                    Main.log("Graph: BFS from " + s1 + " -> " + b.order + " (steps " + b.steps + ")");
                    break;
                case 5:
                    if (isEmpty()) {
                        System.out.println("Graph is empty! Add vertices first.");
                        break;
                    }
                    int s2 = Main.readInt(sc, "Enter start vertex: ");
                    if (!isValidVertex(s2)) {
                        System.out.println("Invalid vertex! Enter 0 to " + (vertexCount() - 1) + ".");
                        break;
                    }
                    TraversalResult d = dfs(s2);
                    printTraversal("DFS", s2, d);
                    Main.log("Graph: DFS from " + s2 + " -> " + d.order + " (steps " + d.steps + ")");
                    break;
                case 6:
                    loadSampleGraph();
                    System.out.println("Sample graph loaded (6 vertices, 6 edges).");
                    display();
                    Main.log("Graph: sample graph loaded");
                    break;
                case 7:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice! Please enter a number between 1 and 7.");
            }
        }
    }
}