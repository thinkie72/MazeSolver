/**
 * Solves the given maze using DFS or BFS
 *
 * @author Ms. Namasivayam
 * @version 03/10/2023
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

// By Tyler Hinkie

public class MazeSolver {
    private Maze maze;

    public MazeSolver() {
        this.maze = null;
    }

    public MazeSolver(Maze maze) {
        this.maze = maze;
    }

    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    /**
     * Starting from the end cell, backtracks through
     * the parents to determine the solution
     *
     * @return An arraylist of MazeCells to visit in order
     */
    public ArrayList<MazeCell> getSolution() {
        // TODO: Get the solution from the maze
        // Should be from start to end cells
        Stack<MazeCell> s = new Stack<MazeCell>();
        MazeCell previous = maze.getEndCell();
        s.push(previous);
        // Adds each parent to the stack until it is the start cell, which has no parent
        while (previous != maze.getStartCell()) {
            previous = previous.getParent();
            s.push(previous);
        }

        ArrayList<MazeCell> solution = new ArrayList<MazeCell>();
        // Adds each element from the stack to an ArrayList to export
        while (!s.empty()) solution.add(s.pop());

        return solution;
    }

    /**
     * Performs a Depth-First Search to solve the Maze
     *
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeDFS() {
        // TODO: Use DFS to solve the maze
        // Explore the cells in the order: NORTH, EAST, SOUTH, WEST
        Stack<MazeCell> s = new Stack<MazeCell>();
        s.push(maze.getStartCell());
        MazeCell cell;
        int row;
        int col;
        MazeCell temp;
        // Continues to pop cells onto the stack until one path reaches the end
        while (s.peek() != maze.getEndCell()) {
            cell = s.pop();
            row = cell.getRow();
            col = cell.getCol();
            // North
            if (isInBounds(row - 1, col) && maze.isValidCell(row - 1, col)) {
                temp = maze.getCell(row - 1, col);
                temp.setExplored(true);
                temp.setParent(cell);
                s.push(temp);
            }
            // East
            if (isInBounds(row, col + 1) && maze.isValidCell(row, col + 1)) {
                temp = maze.getCell(row, col + 1);
                temp.setExplored(true);
                temp.setParent(cell);
                s.push(temp);
            }
            // South
            if (isInBounds(row + 1, col) && maze.isValidCell(row + 1, col)) {
                temp = maze.getCell(row + 1, col);
                temp.setExplored(true);
                temp.setParent(cell);
                s.push(temp);
            }
            // West
            if (isInBounds(row, col - 1) && maze.isValidCell(row, col - 1)) {
                temp = maze.getCell(row, col - 1);
                temp.setExplored(true);
                temp.setParent(cell);
                s.push(temp);
            }
        }
        return getSolution();
    }

    // Checks if the row and col values are in bounds to prevent index out of bounds errors
    public boolean isInBounds(int row, int col) {
        return row < maze.getNumRows() && row >= 0 && col >= 0 && col < maze.getNumCols();
    }

    /**
     * Performs a Breadth-First Search to solve the Maze
     *
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeBFS() {
        // TODO: Use BFS to solve the maze
        // Explore the cells in the order: NORTH, EAST, SOUTH, WEST
        Queue<MazeCell> q = new LinkedList<MazeCell>();
        q.add(maze.getStartCell());
        MazeCell cell;
        int row;
        int col;
        MazeCell temp;
        // Continues to add cells onto the queue until one path reaches the end
        while (q.peek() != maze.getEndCell()) {
            cell = q.remove();
            row = cell.getRow();
            col = cell.getCol();
            // North
            if (isInBounds(row - 1, col) && maze.isValidCell(row - 1, col)) {
                temp = maze.getCell(row - 1, col);
                temp.setExplored(true);
                temp.setParent(cell);
                q.add(temp);
            }
            // East
            if (isInBounds(row, col + 1) && maze.isValidCell(row, col + 1)) {
                temp = maze.getCell(row, col + 1);
                temp.setExplored(true);
                temp.setParent(cell);
                q.add(temp);
            }
            // South
            if (isInBounds(row + 1, col) && maze.isValidCell(row + 1, col)) {
                temp = maze.getCell(row + 1, col);
                temp.setExplored(true);
                temp.setParent(cell);
                q.add(temp);
            }
            // West
            if (isInBounds(row, col - 1) && maze.isValidCell(row, col - 1)) {
                temp = maze.getCell(row, col - 1);
                temp.setExplored(true);
                temp.setParent(cell);
                q.add(temp);
            }
        }
        return getSolution();
    }

    public static void main(String[] args) {
        // Create the Maze to be solved
        Maze maze = new Maze("Resources/maze3.txt");

        // Create the MazeSolver object and give it the maze
        MazeSolver ms = new MazeSolver();
        ms.setMaze(maze);

        // Solve the maze using DFS and print the solution
        ArrayList<MazeCell> sol = ms.solveMazeDFS();
        maze.printSolution(sol);

        // Reset the maze
        maze.reset();

        // Solve the maze using BFS and print the solution
        sol = ms.solveMazeBFS();
        maze.printSolution(sol);
    }
}
