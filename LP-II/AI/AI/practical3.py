class DisjointSet:
    def __init__(self, vertices):
        self.parent = {v: v for v in vertices}

    def find(self, v):
        if self.parent[v] != v:
            self.parent[v] = self.find(self.parent[v])  # Path compression
        return self.parent[v]

    def union(self, u, v):
        root_u = self.find(u)
        root_v = self.find(v)
        if root_u != root_v:
            self.parent[root_v] = root_u
            return True
        return False


def kruskal_mst(vertices, edges):
    # Sort edges by weight (greedy choice)
    edges.sort(key=lambda edge: edge[2])

    dsu = DisjointSet(vertices)
    mst = []

    for u, v, weight in edges:
        if dsu.union(u, v):
            mst.append((u, v, weight))

    return mst


# Example Usage
def main():
    vertices = ['A', 'B', 'C', 'D', 'E', 'F']
    edges = [
        ('A', 'B', 4),
        ('A', 'F', 2),
        ('B', 'C', 6),
        ('B', 'F', 3),
        ('C', 'D', 3),
        ('C', 'F', 1),
        ('D', 'E', 2),
        ('E', 'F', 4)
    ]

    mst = kruskal_mst(vertices, edges)

    print("Edges in the Minimum Spanning Tree:")
    for u, v, weight in mst:
        print(f"{u} - {v} (Weight: {weight})")


if __name__ == "__main__":
    main()
