# Data Structure & Graph Performance Analyzer

**Module:** CIT300 Data Structures and Algorithms
**Assignment:** Graded Practical Assignment 2 (Week 12)
**Language:** Java (console-based application)

## Project Description

A Java console application that demonstrates the practical use of data structures, searching algorithms, graph traversal and algorithmic complexity. The user works with each data structure through its own submenu, and the system records the number of steps and execution time of key algorithms so their performance can be compared.

## Team Members

| Student Name | Student ID | Assigned Responsibility | Branch |
|---|---|---|---|
| AM. AATHIF | 23DA2-0725 | Array and Searching | `feature/array-searching` |
| ABF. ANEESIYA | 23DA2-0812 | Stack and Queue, GitHub repository management, integration | `feature/stack-queue` |
| IA. SAMA | 23DA2-0938 | Linked List | `feature/linkedlist` |
| IM. SUJA | 23DA2-0724 | Graph and Performance Comparison | `feature/graph-performance` |

## Individual Contributions

### AM. AATHIF (23DA2-0725): Array and Searching
- Implemented `ArrayOperations` (insert, delete, search, display with position validation)
- Implemented `SearchOperations` (Linear Search O(n) and Binary Search O(log n))
- Added step counting and execution time for both searches
- Added sorting (insertion sort), sorted sample data generation and a Linear vs Binary comparison
- Tested array and searching functionality

### ABF. ANEESIYA (23DA2-0812): Stack and Queue, GitHub, Integration
- Implemented `MyStack` (push, pop, peek, display, stack overflow/underflow handling)
- Implemented `MyQueue` (circular array queue: enqueue, dequeue, peek, display, overflow/underflow handling)
- Created and managed the GitHub repository, feature branches, commits and pull requests
- Integrated all modules into the main menu (`Main`) and tested the complete system

### IA. SAMA (23DA2-0938): Linked List
- Implemented `MyLinkedList` (singly linked list)
- Insert at beginning, insert at end, delete by value, search, display
- Handled empty list and value-not-found cases
- Tested linked list functionality and integrated it with the main application

### IM. SUJA (23DA2-0724): Graph and Performance Comparison
- Implemented `Graph` (undirected graph using an adjacency list)
- Implemented add vertex, add edge, display graph, BFS traversal and DFS traversal
- Implemented `PerformanceAnalyzer` comparing Linear vs Binary Search and BFS vs DFS (steps and time)
- Tested graph and performance functionality and integrated them with the main application

## GitHub Collaboration Note

All commits, branch pushes and pull requests in this repository were made from a single GitHub account, **23da2-0812-ai**, which belongs to member **23DA2-0812 (ABF. ANEESIYA)**. This is why only this username appears in the commit history. The class-level work of each member is identified by the `@author` tag (student ID) in every source file and by the branch names listed above.

## Technologies Used
- Java (JDK 8 or later)
- Eclipse IDE
- Git and GitHub

## Main System Features
- **Array:** insert, delete, search, display
- **Stack:** push, pop, peek, display (underflow/overflow handling)
- **Queue:** enqueue, dequeue, peek, display (underflow/overflow handling)
- **Linked List:** insert (beginning/end), delete, search, display
- **Searching:** Linear Search and Binary Search with step count and time
- **Graph:** add vertex, add edge, display, BFS and DFS traversal, sample graph loader
- **Performance Comparison:** steps and time table with a complexity explanation
- **Display All Results:** shows every logged operation
- Input validation and empty-structure handling throughout

## Project Structure

```
src/com/analyzer/Main.java
src/com/analyzer/ds/ArrayOperations.java
src/com/analyzer/ds/MyStack.java
src/com/analyzer/ds/MyQueue.java
src/com/analyzer/ds/MyLinkedList.java
src/com/analyzer/search/SearchOperations.java
src/com/analyzer/graph/Graph.java
src/com/analyzer/perf/PerformanceAnalyzer.java
```

## How to Run

**Using Eclipse**
1. Clone the repository: `git clone https://github.com/23da2-0812-ai/CIT300_Assignment2.git`
2. In Eclipse: File > Import > Existing Projects into Workspace, and select the folder.
3. Open `src/com/analyzer/Main.java`.
4. Right-click > Run As > Java Application.

**Using the command line**
```
cd CIT300_Assignment2
mkdir bin
javac -d bin src/com/analyzer/Main.java src/com/analyzer/ds/*.java src/com/analyzer/search/*.java src/com/analyzer/graph/*.java src/com/analyzer/perf/*.java
java -cp bin com.analyzer.Main
```

## Complexity Summary

| Operation | Algorithm | Complexity |
|---|---|---|
| Search | Linear Search | O(n) |
| Search | Binary Search (sorted data) | O(log n) |
| Graph traversal | BFS | O(V + E) |
| Graph traversal | DFS | O(V + E) |
| Stack / Queue | push, pop, enqueue, dequeue | O(1) |
