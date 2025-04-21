from collections import deque

# Define an undirected graph using adjacency list
graph = {
    'A': ['B', 'C'],
    'B': ['A', 'D', 'E'],
    'C': ['A', 'F'],
    'D': ['B'],
    'E': ['B', 'F'],
    'F': ['C', 'E']
}

# Recursive Depth First Search
def dfs(graph, vertex, visited=None):
    if visited is None:
        visited = set()
    visited.add(vertex)
    print(vertex, end=' ')  # Visit node

    for neighbor in graph[vertex]:
        if neighbor not in visited:
            dfs(graph, neighbor, visited)

    return visited

# Breadth First Search (uses queue)
def bfs(graph, start):
    visited = set()
    queue = deque([start])
    visited.add(start)

    while queue:
        vertex = queue.popleft()
        print(vertex, end=' ')  # Visit node

        for neighbor in graph[vertex]:
            if neighbor not in visited:
                visited.add(neighbor)
                queue.append(neighbor)

    return visited

# Main function to run the traversals
def main():
    print("Depth First Search (DFS) starting from 'A':")
    dfs(graph, 'A')
    print("\nBreadth First Search (BFS) starting from 'A':")
    bfs(graph, 'A')

if __name__ == "__main__":
    main()
