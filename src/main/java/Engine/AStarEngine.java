
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
import Engine.Interfaces.AStarListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AStarEngine {
	private final AStarFramework framework;
	private final Set<AStarListener> listeners;
	
	public AStarEngine(AStarFramework framework) {
		this.framework = framework;
		this.listeners = new HashSet();
	}
	
	public void addAStarListener(AStarListener listener) {
		listeners.add(listener);
	}
	
	public void search() {
		AbstractNode start = framework.getStartNode();
		AbstractNode end = framework.getEndNode();
		List<AbstractNode> openSet = new LinkedList();
		List<AbstractNode> closedSet = new ArrayList();
		Map<AbstractNode, Double> gScores = new HashMap();
		Map<AbstractNode, AbstractNode> cameFrom = new HashMap();
		openSet.add(start);
		gScores.put(start, 0.0);
		
		/*
		 * Setup action
		 */
		for (AStarListener listener : listeners) {
			listener.setupAction(openSet);
		}

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
				// Do the reconstruct function here
				List<AbstractNode> result = reconstruct(cameFrom, end);
				
				/*
				 * Finished action
				 */
				for (AStarListener listener : listeners) {
					listener.searchCompleteAction(result);
				}
				
				// End the search
				return;
			}
			
			// Remove current from openSet and evaluate each neighbor
			openSet.remove(current);
			closedSet.add(current);
			
			/*
			 * Current Update Action
			 */
			for (AStarListener listener : listeners) {
				listener.updatedCurrentNodeAction(openSet, closedSet, current);
			}
			
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
		/*
		 * No Result action
		 */
		for (AStarListener listener : listeners) {
			listener.noResultAction();
		}
	}
	
	private static List<AbstractNode> reconstruct(
			Map<AbstractNode, AbstractNode> cameFrom, AbstractNode end) {
		List<AbstractNode> r = new LinkedList();
		r.add(end);
		AbstractNode current = end;
		while (cameFrom.containsKey(current)) {
			current = cameFrom.get(current);
			r.add(0, current);
		}
		
		return r;
	}
}
