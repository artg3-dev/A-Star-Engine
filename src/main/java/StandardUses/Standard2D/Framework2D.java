/*------------------------------------------------------------------------------
 @Author Art Garcia
 Copyright (c) 2/2020
 Project: AStarEngine
 Last Modified: 2/25/20, 5:20 PM
 License: GNU Lesser General Public License v3.0
 -----------------------------------------------------------------------------*/

package StandardUses.Standard2D;

import Engine.AStarEngine;
import Engine.Abstracts.AbstractNode;
import Engine.Interfaces.AStarFramework;

import java.util.ArrayList;
import java.util.List;

public class Framework2D implements AStarFramework {
	private final Grid2D grid;
	private final AbstractNode endNode, startNode;
	
	public Framework2D(Grid2D grid,
	                   AbstractNode startNode,
	                   AbstractNode endNode) {
		this.grid = grid;
		this.startNode = startNode;
		this.endNode = endNode;
	}
	
	@Override
	public AbstractNode getStartNode() {
		return startNode;
	}
	
	@Override
	public AbstractNode getEndNode() {
		return endNode;
	}
	
	@Override
	public double getHeuristic(AbstractNode node) {
		double a = endNode.getX() - node.getX();
		double b = endNode.getY() - node.getY();
		double cSq = Math.pow(a, 2) + Math.pow(b, 2);
		return Math.sqrt(cSq);
	}
	
	@Override
	public double getF(AbstractNode node, double gScore) {
		return gScore + getHeuristic(node);
	}
	
	@Override
	public double getDistance(AbstractNode node1, AbstractNode node2) {
		double a = node2.getX() - node1.getX();
		double b = node2.getY() - node1.getY();
		double cSq = Math.pow(a, 2) + Math.pow(b, 2);
		return Math.sqrt(cSq);
	}
	
	@Override
	public List<AbstractNode> getNeighbors(AbstractNode node) {
		List<AbstractNode> neighbors = new ArrayList();
		double x = node.getX();
		double y = node.getY();
		for (int i = - 1; i <= 1; i++) {
			for (int j = - 1; j <= 1; j++) {
				int neighborX = (int) x + i;
				int neighborY = (int) y + j;
				if ((neighborX >= 0 && neighborY >= 0) &&
						(neighborX < grid.width && neighborY < grid.height) &&
						! (neighborX == x && neighborY == y)) {
					Node2D neighbor = grid.getNode(neighborX, neighborY);
					if (! neighbor.isObstacle()) {
						neighbors.add(neighbor);
					}
				}
			}
		}
		
		return neighbors;
	}
	
	public static void runExample() {
		// Setup example
		Grid2D grid = new Grid2D(5, 5);
		Node2D start = grid.getNode(0, 0);
		Node2D end = grid.getNode(4, 4);
		grid.setObstacle(2, 0, true);
		grid.setObstacle(2, 2, true);
		grid.setObstacle(2, 3, true);
		grid.setObstacle(2, 4, true);
		Framework2D framework = new Framework2D(grid, start, end);
		Listener2D listener = new Listener2D();
		AStarEngine engine = new AStarEngine(framework);
		engine.addAStarListener(listener);
		
		// Display explanation text
		System.out.println("An example of an A* Search performed " +
				"on a 2D grid.");
		System.out.println("Framework: Framework2D");
		System.out.println("AStarListener: Listener2D");
		System.out.println(grid);
		System.out.println("-------------------------");
		System.out.println("---------SETUP-----------");
		System.out.println("-------------------------");
		
		// Print Grid
		for (int j = 0; j < grid.height; j++) {
			for (int i = 0; i < grid.width; i++) {
				if (grid.getNode(i, j).isObstacle()) {
					System.out.print("X");
				} else if (grid.getNode(i, j).equals(start)) {
					System.out.print("S");
				} else if (grid.getNode(i, j).equals(end)) {
					System.out.print("E");
				} else {
					System.out.print("*");
				}
			}
			System.out.println();
		}
		
		// Run the search
		engine.search();
		
		// Display the solution
		List<AbstractNode> previousSolution = engine.getPreviousSolution();
		
		System.out.println("-------------------------");
		System.out.println("---------SOLUTION--------");
		System.out.println("-------------------------");
		for (int j = 0; j < grid.height; j++) {
			for (int i = 0; i < grid.width; i++) {
				Node2D node = grid.getNode(i, j);
				if (node.isObstacle()) {
					System.out.print("X");
				} else if (previousSolution.contains(node)) {
					System.out.print(previousSolution.indexOf(node));
				} else {
					System.out.print("*");
				}
			}
			System.out.println();
		}
		
	}
}
