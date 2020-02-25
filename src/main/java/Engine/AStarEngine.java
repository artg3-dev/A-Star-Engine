
/*------------------------------------------------------------------------------
 @Author Art Garcia
 Copyright (c) 2/2020
 Project: AStarEngine
 Last Modified: 2/23/20, 10:11 AM
 License: GNU Lesser General Public License v3.0
 -----------------------------------------------------------------------------*/

package Engine;

import Engine.Abstracts.AbstractNode;
import Engine.Interfaces.AStarFramework;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class AStarEngine {
	private AStarFramework framework;
	
	public AStarEngine(AStarFramework framework) {
		this.framework = framework;
	}
	
	public void search(AbstractNode start, AbstractNode end) {
		List<AbstractNode> openSet = new LinkedList();
		List<AbstractNode> closedSet = new ArrayList();
		Map<AbstractNode, Double> gScores = new HashMap();
		Map<AbstractNode, AbstractNode> cameFrom = new HashMap();
		openSet.add(start);
		gScores.put(start, 0.0);
		
		// Loops while there are Nodes in openSet
		while (!openSet.isEmpty()) {
			// Current = the AbstractNode with the lowest fScore in openSet
			AbstractNode current = null;
			for (AbstractNode i : openSet) {
				if (current == null || framework.getF(i, gScores.get(i)) <
						framework.getF(current, gScores.get(current))) {
					current = i;
				}
			}
			
			// If the end has been reached
			if (current.equals(end)) {
				System.out.println("Done!");
				// Do the reconstruct function here
				List<AbstractNode> result = reconstruct(cameFrom, end);
				// Do the finished stuff here
			}
			
			// Remove current from openSet and evaluate each neighbor
			openSet.remove(current);
			closedSet.add(current);
			
			// For each neighbor of current
			for (AbstractNode neighbor : framework.getNeighbors(current)) {
				if (!closedSet.contains(neighbor)) {
					double tempG = gScores.get(current) +
							framework.getDistance(current, neighbor);
					if (!openSet.contains(neighbor) ||
							gScores.get(neighbor) == null) {
						cameFrom.put(neighbor, current);
						gScores.put(neighbor, tempG);
					} else if (tempG <= gScores.get(neighbor)) {
						cameFrom.put(neighbor, current);
						gScores.put(neighbor, tempG);
					}
					
					// Add neighbor to openSet if not already
					if (!openSet.contains(neighbor)) {
						openSet.add(neighbor);
					}
				}
			}
		}
		
		// If openSet has no more elements
		System.out.println("No solution.");
		// do the no result here
	}
	
	private static List<AbstractNode> reconstruct(
			Map<AbstractNode, AbstractNode> cameFrom, AbstractNode end) {
		List<AbstractNode> r = new LinkedList();
		r.add(end);
		AbstractNode current = end;
		while (cameFrom.containsKey(current)) {
			current = cameFrom.get(current);
			r.add(current);
		}
		
		return r;
	}
}
