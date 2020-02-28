/*------------------------------------------------------------------------------
 @Author Art Garcia
 Copyright (c) 2/2020
 Project: AStarEngine
 Last Modified: 2/25/20, 5:20 PM
 License: GNU Lesser General Public License v3.0
 -----------------------------------------------------------------------------*/

package StandardUses.Standard2D;

import Engine.Interfaces.AStarFramework;

import java.util.ArrayList;
import java.util.List;

public class Framework2D implements AStarFramework<Node2D> {
	private final Grid2D grid;
	private final Node2D endNode, startNode;
	private final boolean diagonalsAllowed;
	
	public Framework2D(Grid2D grid,
	                   Node2D startNode,
	                   Node2D endNode,
	                   boolean diagonalsAllowed) {
		this.grid = grid;
		this.startNode = startNode;
		this.endNode = endNode;
		this.diagonalsAllowed = diagonalsAllowed;
	}
	
	@Override
	public Node2D getStartNode() {
		return startNode;
	}
	
	@Override
	public Node2D getEndNode() {
		return endNode;
	}
	
	@Override
	public double getHeuristic(Node2D node) {
		double a = endNode.getX() - node.getX();
		double b = endNode.getY() - node.getY();
		double cSq = Math.pow(a, 2) + Math.pow(b, 2);
		return Math.sqrt(cSq);
	}
	
	@Override
	public double getF(Node2D node, double gScore) {
		return gScore + getHeuristic(node);
	}
	
	@Override
	public double getDistance(Node2D node1, Node2D node2) {
		double a = node2.getX() - node1.getX();
		double b = node2.getY() - node1.getY();
		double cSq = Math.pow(a, 2) + Math.pow(b, 2);
		return Math.sqrt(cSq);
	}
	
	@Override
	public List<Node2D> getNeighbors(Node2D node) {
		//  TODO remove ability for neighbor to be diagonal through two obstacles
		List<Node2D> neighbors = new ArrayList();
		int x = node.x;
		int y = node.y;
		for (int i = - 1; i <= 1; i++) {
			for (int j = - 1; j <= 1; j++) {
				int neighborX = x + i;
				int neighborY = y + j;
				// Checks if neighbor is inbounds and != current target ('node')
				if ((neighborX >= 0 && neighborY >= 0) &&
						(neighborX < grid.width && neighborY < grid.height) &&
						! (neighborX == x && neighborY == y)) {
					Node2D neighbor = grid.getNode(neighborX, neighborY);
					
					// Checks adjacent neighbors only
					if (! neighbor.isObstacle() && (i == 0 || j == 0)) {
						neighbors.add(neighbor);
					}
					
					// Checks diagonals if diagonals are allowed
					else if (diagonalsAllowed && ! neighbor.isObstacle() &&
							diagonalValid(node, neighbor)) {
						neighbors.add(neighbor);
					}
				}
			}
		}
		
		return neighbors;
	}
	
	private boolean diagonalValid(Node2D node, Node2D neighbor) {
		int xDif = neighbor.x - node.x;
		int yDif = neighbor.y - node.y;
		Node2D toCheck1 = grid.getNode(node.x, node.y + yDif);
		Node2D toCheck2 = grid.getNode(node.x + xDif, node.y);
		return ! (toCheck1.isObstacle() && toCheck2.isObstacle());
	}
}
