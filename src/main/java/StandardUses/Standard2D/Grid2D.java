/*------------------------------------------------------------------------------
 @Author Art Garcia
 Copyright (c) 2/2020
 Project: AStarEngine
 Last Modified: 2/26/20, 7:07 PM
 License: GNU Lesser General Public License v3.0
 -----------------------------------------------------------------------------*/

package StandardUses.Standard2D;

public class Grid2D {
	private final Node2D[][] grid;
	public final int width, height;
	
	public Grid2D(int width, int height) {
		this.grid = new Node2D[width][height];
		this.width = width;
		this.height = height;
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				grid[i][j] = new Node2D(i, j, false);
			}
		}
	}
	
	public Node2D getNode(int x, int y) {
		return grid[x][y];
	}
	
	public void setObstacle(int x, int y, boolean isObstacle) {
		grid[x][y].setObstacle(isObstacle);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Grid: ");
		sb.append(width);
		sb.append("x");
		sb.append(height);
		return sb.toString();
	}
}
