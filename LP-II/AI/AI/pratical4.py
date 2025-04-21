def solve_n_queens(n):
    def is_safe(row, col):
        return not cols[col] and not diag1[row - col] and not diag2[row + col]

    def place_queen(row):
        if row == n:
            solutions.append(board[:])
            return

        for col in range(n):
            if is_safe(row, col):
                board[row] = '.' * col + 'Q' + '.' * (n - col - 1)
                cols[col] = diag1[row - col] = diag2[row + col] = True
                place_queen(row + 1)
                cols[col] = diag1[row - col] = diag2[row + col] = False

    board = ['.' * n for _ in range(n)]
    solutions = []

    # Branch-and-bound optimization: fast boolean checks
    cols = [False] * n              # Column check
    diag1 = {}                      # Main diagonal check (row - col)
    diag2 = {}                      # Anti-diagonal check (row + col)

    # Initialize diagonals
    for i in range(-n + 1, n):
        diag1[i] = False
    for i in range(2 * n):
        diag2[i] = False

    place_queen(0)
    return solutions

# Display function
def print_solutions(solutions):
    for idx, solution in enumerate(solutions, 1):
        print(f"\nSolution #{idx}")
        for row in solution:
            print(row)

# Example Usage
def main():
    n = 8  # Try different N values (e.g., 4, 8)
    solutions = solve_n_queens(n)
    print(f"\nTotal Solutions for {n}-Queens: {len(solutions)}")
    print_solutions(solutions[:1])  # Show the first solution

if __name__ == "__main__":
    main()
