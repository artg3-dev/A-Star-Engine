/*------------------------------------------------------------------------------
 @Author Art Garcia
 Copyright (c) 2/2020
 Project: AStarEngine
 Last Modified: 2/25/20, 5:20 PM
 License: GNU Lesser General Public License v3.0
 -----------------------------------------------------------------------------*/

package StandardUses.Standard2D;

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
		for (int i = -1; i <= 1; i ++) {
			for (int j = -1; j <= 1; j ++) {
				int neighborX = (int) x + i;
				int neighborY = (int) y + j;
				if ((neighborX >= 0 && neighborY >= 0) &&
						(neighborX < grid.width && neighborY < grid.height) &&
						!(neighborX == x && neighborY == y))  {
					Node2D neighbor = grid.getNode(neighborX, neighborY);
					if (!neighbor.isObstacle()) {
						neighbors.add(neighbor);
					}
				}
			}
		}
		
		return neighbors;
	}
}
