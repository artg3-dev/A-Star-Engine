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
	private final AbstractNode endNode;
	private AbstractNode node;
	
	public Framework2D(AbstractNode endNode) {
		this.endNode = endNode;
	}
	
	public double getHeuristic(AbstractNode node) {
		double a = endNode.getX() - node.getX();
		double b = endNode.getY() - node.getY();
		double cSq = Math.pow(a, 2) + Math.pow(b, 2);
		return Math.sqrt(cSq);
	}
	
	public double getF(AbstractNode node, double gScore) {
		return gScore + getHeuristic(node);
	}
	
	public double getDistance(AbstractNode node1, AbstractNode node2) {
		double a = node2.getX() - node1.getX();
		double b = node2.getY() - node1.getY();
		double cSq = Math.pow(a, 2) + Math.pow(b, 2);
		return Math.sqrt(cSq);
	}
	
	public List<AbstractNode> getNeighbors(AbstractNode node) {
		List<AbstractNode> neighbors = new ArrayList();
		double x = node.getX();
		double y = node.getY();
		for (int i = -1; i <= 1; i ++) {
			for (int j = -1; j <= 1; j ++) {
				double neighborX = x + i;
				double neighborY = y + j;
				if ((neighborX >= 0 && neighborY >= 0) &&
						!(neighborX == x && neighborY == y))  {
					neighbors.add(new Node2D(neighborX, neighborY, true));
				}
			}
		}
		
		return neighbors;
	}
}
