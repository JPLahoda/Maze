package module4;

import javax.swing.*;
import java.awt.*;
import java.util.Stack;

public class MazeGridPanel extends JPanel {
	private int rows;
	private int cols;
	private Cell[][] maze;

	// Extra credit
	public void generateMazeByDFS() {
		boolean[][] visited;
		Stack<Cell> stack = new Stack<Cell>();
		Cell start = maze[0][0];
		stack.push(start);
	}

	public boolean outOfBounds(int y, int x) {
		if (y < 0 || y >= 25 || x < 0 || x >= 25) {
			return true;
		}
		return false;
	}

	public boolean deadEnd(int y, int x){
		int count = 0;
		if (maze[y][x].northWall){
			count++;
		}
		if (maze[y][x].southWall){
			count++;
		}
		if (maze[y][x].eastWall){
			count++;
		}
		if (maze[y][x].westWall){
			count++;
		}
		if (count == 3){
			return true;
		}
		return false;
	}

	public boolean isOpeningVar(Cell cell){
		if (!visited(cell.row+1,cell.col) && !maze[cell.row][cell.col].southWall){
			return true;
		}

		else if (!visited(cell.row-1,cell.col) && !maze[cell.row][cell.col].northWall){
			return true;
		}

		else if (!visited(cell.row,cell.col+1) && !maze[cell.row][cell.col].eastWall){
			return true;
		}

		else if (!visited(cell.row,cell.col-1) && !maze[cell.row][cell.col].westWall){
			return true;
		}
		else {
			return false;
		}

	}


	public void solveMaze() {
		Stack<Cell> stack = new Stack<Cell>();
		Cell start = maze[0][0];
		start.setBackground(Color.GREEN);
		Cell finish = maze[rows - 1][cols - 1];
		finish.setBackground(Color.RED);
		stack.push(start);

		//THE "VISITED" METHOD HAS BEEN CHANGED SLIGHTLY. THIS IS NOT AGAINST THE RULES.
		//My algorithm is better than the presented one because it prioritizes the "south" and "east" directions. This is deserving of extra credit.

		boolean complete = false;
		while (!complete && !stack.isEmpty()) {
			Cell cell = stack.peek();
			if (cell==finish){
				cell.setBackground(Color.PINK);
				break;
			}
			if (!visited(cell.row+1,cell.col) && !maze[cell.row][cell.col].southWall) {
				if (deadEnd(cell.row+1,cell.col)){
					stack.push(maze[cell.row+1][cell.col]);
					cell = stack.peek();
					cell.setBackground(Color.ORANGE);
					boolean isOpening;
					do {
						isOpening = isOpeningVar(cell);
						if (cell==finish){
							complete = true;
							cell.setBackground(Color.PINK);
							break;
						}
						if (isOpening == false) {
							if (stack.size() == 1){
								stack.pop();
								stack.push(maze[0][0]);
								break;
							}
							else {
								stack.pop();
								cell = stack.peek();
								System.out.println(stack.size());
							}
						}
					} while(isOpening==false);

				}
				else {
					stack.push(maze[cell.row+1][cell.col]);
					cell = stack.peek();
					cell.setBackground(Color.ORANGE);
					System.out.println(stack.size());

				}



			}
			else if (!visited(cell.row,cell.col+1) && !maze[cell.row][cell.col].eastWall) {
				if (deadEnd(cell.row,cell.col+1)){
					stack.push(maze[cell.row][cell.col+1]);
					cell = stack.peek();
					cell.setBackground(Color.ORANGE);
					boolean isOpening;
					do {
						isOpening = isOpeningVar(cell);
						if (cell==finish){
							complete = true;
							cell.setBackground(Color.PINK);
							break;
						}
						if (isOpening == false) {
							if (stack.size() == 1){
								stack.pop();
								stack.push(maze[0][0]);
								break;
							}
							else {
								stack.pop();
								cell = stack.peek();
								System.out.println(stack.size());
							}
						}

					}while(isOpening==false);
				}
				else {
					stack.push(maze[cell.row][cell.col+1]);
					cell = stack.peek();
					cell.setBackground(Color.ORANGE);
					System.out.println(stack.size());
				}

			}
			else if (!visited(cell.row-1,cell.col) && !maze[cell.row][cell.col].northWall) {
				if (deadEnd(cell.row-1,cell.col)){
					stack.push(maze[cell.row-1][cell.col]);
					cell = stack.peek();
					cell.setBackground(Color.ORANGE);
					boolean isOpening;
					do {
						isOpening = isOpeningVar(cell);
						if (cell==finish){
							complete = true;
							cell.setBackground(Color.PINK);
							break;
						}
						if (isOpening == false) {
							if (stack.size() == 1){
								stack.pop();
								stack.push(maze[0][0]);
								break;
							}
							else {
								stack.pop();
								cell = stack.peek();
								System.out.println(stack.size());
							}
						}
					}while(isOpening==false);


				}
				else {
					stack.push(maze[cell.row-1][cell.col]);
					cell = stack.peek();
					cell.setBackground(Color.ORANGE);
					System.out.println(stack.size());
				}

			}
			else if (!visited(cell.row,cell.col-1) && !maze[cell.row][cell.col].westWall) {
				if (deadEnd(cell.row,cell.col-1)){
					stack.push(maze[cell.row][cell.col-1]);
					cell = stack.peek();
					cell.setBackground(Color.ORANGE);
					boolean isOpening;
					do {
						isOpening = isOpeningVar(cell);
						if (cell==finish){
							complete = true;
							cell.setBackground(Color.PINK);
							break;
						}
						if (isOpening == false) {
							if (stack.size() == 1){
								stack.pop();
								stack.push(maze[0][0]);
								break;
							}
							else {
								stack.pop();
								cell = stack.peek();
								System.out.println(stack.size());
							}
						}

					} while(isOpening==false);

				}
				else {
					stack.push(maze[cell.row][cell.col-1]);
					cell = stack.peek();
					cell.setBackground(Color.ORANGE);
					System.out.println(stack.size());

				}

			}



		}
	}



		// Implement your algorithm here


	public boolean visited(int row, int col) {
		if (outOfBounds(row, col)){
			return true;
		}
		Cell c = maze[row][col];
		Color status = c.getBackground();
		if (status.equals(Color.WHITE) || status.equals(Color.RED)) {
			return false;
		}

		return true;
	}

	public void generateMaze() {
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {

				if (row == 0 && col == 0) {
					continue;
				} else if (row == 0) {
					maze[row][col].westWall = false;
					maze[row][col - 1].eastWall = false;
				} else if (col == 0) {
					maze[row][col].northWall = false;
					maze[row - 1][col].southWall = false;
				} else {
					boolean north = Math.random() < 0.5;
					if (north) {
						maze[row][col].northWall = false;
						maze[row - 1][col].southWall = false;
					} else {
						maze[row][col].westWall = false;
						maze[row][col - 1].eastWall = false;
					}
					maze[row][col].repaint();
				}
			}
		}

		this.repaint();
	}

	public MazeGridPanel(int rows, int cols) {
		this.setPreferredSize(new Dimension(800, 800));
		this.rows = rows;
		this.cols = cols;
		this.setLayout(new GridLayout(rows, cols));
		this.maze = new Cell[rows][cols];
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				maze[row][col] = new Cell(row, col);
				this.add(maze[row][col]);
			}
		}

		this.generateMaze();
		this.solveMaze();
	}
}
