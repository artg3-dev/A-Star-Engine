
/*------------------------------------------------------------------------------
 @Author Art Garcia
 Copyright (c) 2/2020
 Project: AStarEngine
 Last Modified: 2/23/20, 10:11 AM
 License: GNU Lesser General Public License v3.0
 -----------------------------------------------------------------------------*/

package Engine;

import Engine.Interfaces.AStarFramework;
import Engine.Interfaces.AStarListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AStarEngine <T>{
	private final AStarFramework<T> framework;
	private final Set<AStarListener<T>> listeners;
	private List<T> previousSolution;
	// TODO add ability to view information about the past search via public
	//  methods and variables
	
	
	public AStarEngine(AStarFramework<T> framework) {
		this.framework = framework;
		this.listeners = new HashSet<AStarListener<T>>();
		this.previousSolution = null;
	}
	
	public void addAStarListener(AStarListener<T> listener) {
		listeners.add(listener);
	}
	
	public List<T> getPreviousSolution (){
		return previousSolution;
	}
	
	public List<T> search() {
		T start = framework.getStartNode();
		T end = framework.getEndNode();
		List<T> openSet = new LinkedList<T>();
		List<T> closedSet = new ArrayList<T>();
		Map<T, Double> gScores = new HashMap<T, Double>();
		Map<T, T> cameFrom = new HashMap<T, T>();
		openSet.add(start);
		gScores.put(start, 0.0);
		
		/*
		 * Setup action
		 */
		for (AStarListener<T> listener : listeners) {
			listener.setupAction(openSet);
		}

		// Loops while there are Nodes in openSet
		while (!openSet.isEmpty()) {
			// Current = the node with the lowest fScore in openSet
			T current = null;
			for (T i : openSet) {
				if (current == null || framework.getF(i, gScores.get(i)) <
						framework.getF(current, gScores.get(current))) {
					current = i;
				}
			}
			
			// If the end has been reached
			if (current.equals(end)) {
				// Do the reconstruct function here
				previousSolution = reconstruct(cameFrom, end);
				
				/*
				 * Finished action
				 */
				for (AStarListener<T> listener : listeners) {
					listener.searchCompleteAction(previousSolution);
				}
				
				// End the search
				return previousSolution;
			}
			
			// Remove current from openSet and evaluate each neighbor
			openSet.remove(current);
			closedSet.add(current);
			
			/*
			 * Current Update Action
			 */
			for (AStarListener<T> listener : listeners) {
				listener.updatedCurrentNodeAction(openSet, closedSet, current);
			}
			
			// For each neighbor of current
			for (T neighbor : framework.getNeighbors(current)) {
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
		for (AStarListener<T> listener : listeners) {
			listener.noResultAction();
		}
		this.previousSolution = null;
		return null;
	}
	
	private List<T> reconstruct(
			Map<T, T> cameFrom, T end) {
		List<T> r = new LinkedList();
		r.add(end);
		T current = end;
		while (cameFrom.containsKey(current)) {
			current = cameFrom.get(current);
			r.add(0, current);
		}
		
		return r;
	}
}
