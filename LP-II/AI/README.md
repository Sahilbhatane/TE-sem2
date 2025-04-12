# AI Laboratory Assignments

This repository contains implementations of various AI algorithms in Python, C++, and Java.

## List of Assignments

### 1. Graph Traversal Algorithms (DFS and BFS)
- **Files**: `1_DFS_BFS.py`, `1_DFS_BFS.cpp`
- **Theory**: 
  - **Depth-First Search (DFS)**: A traversal algorithm that explores as far as possible along each branch before backtracking.
  - **Breadth-First Search (BFS)**: A traversal algorithm that explores all neighbors at the present depth before moving to nodes at the next depth level.

### 2. A* Search Algorithm
- **Files**: `2_A_Star.py`, `2_A_Star.cpp`
- **Theory**:
  - A* is an informed search algorithm that uses a heuristic function to guide its search. It combines the advantages of uniform-cost search (Dijkstra's algorithm) and greedy best-first search.
  - It uses the formula f(n) = g(n) + h(n), where g(n) is the cost to reach node n from the start, and h(n) is the estimated cost to reach the goal from node n.

### 3. Optimization Problems
- **Files**: `3_Job_Scheduling.py`, `3_Job_Scheduling.java`, `3_JobScheduling.cpp`, `3_Kruskals_MST.cpp`, `3_gfgKruskals.cpp`, `3.Selection_Sort.py`
- **Theory**:
  - **Job Scheduling**: An optimization problem to schedule jobs to maximize profit while meeting deadlines.
  - **Kruskal's Algorithm**: A minimum spanning tree algorithm that finds an edge of the least possible weight that connects any two trees in the forest.
  - **Selection Sort**: A simple comparison-based sorting algorithm with O(n²) time complexity.

### 4. Constraint Satisfaction Problems
- **Files**: `4_Nqueens.py`, `4_Nqueen.cpp`, `4_Graph_Coloring.cpp`
- **Theory**:
  - **N-Queens Problem**: Place N queens on an N×N chessboard so that no two queens threaten each other.
  - **Graph Coloring**: Assign colors to vertices of a graph such that no two adjacent vertices have the same color.

### 5. Chatbot
- **Files**: `5_ChatBot.py`
- **Theory**:
  - A rule-based chatbot that matches user input against predefined patterns and returns appropriate responses.
  - Uses regular expressions to identify the intent of the user message.

## Running Instructions

### Python Programs
1. Make sure you have Python 3.x installed.
2. Run the program using the command:
   ```
   python filename.py
   ```
3. Follow the prompts to provide input as required by each program.

### C++ Programs
1. Compile the program using:
   ```
   g++ filename.cpp -o filename
   ```
2. Run the executable:
   ```
   ./filename
   ```
3. Follow the prompts to provide input as required by each program.

### Java Programs
1. Ensure you have JDK installed.
2. Note: The file `3_Job_Scheduling.java` contains C++ code but has a Java extension. Rename it to `3_Job_Scheduling.cpp` for proper compilation.

## Known Issues and Fixes

1. **3.Selection_Sort.py**: The minimum value initialization is incorrect. Fix by removing line 4 (`min = float('-inf')`) as it's not used.

2. **3_Job_Scheduling.java**: This file contains C++ code but has a Java extension. Rename it to `3_Job_Scheduling.cpp` for proper compilation.

## Expected Outputs

Each program will provide different outputs based on the inputs:

- DFS/BFS: Will display the traversal order of nodes in a graph.
- A* Search: Will show the solution path for the 8-puzzle problem if solvable.
- Job Scheduling: Outputs the maximum profit and selected jobs.
- N-Queens: Displays the placement of queens on the chessboard.
- Chatbot: Provides responses based on predefined patterns in user input.
