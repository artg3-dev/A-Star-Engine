
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

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

// Todo add javadoc

// Todo add tick - controlled search 
public class AStarEngine<T> {
	private final AStarFramework<T> framework;
	private final Set<AStarListener<T>> listeners;
	private List<T> previousSolution;
	
	// Analytics
	private boolean hasSearched;
	private int nodesChecked;
	private int nodesInOptimalPath;
	private String searchDuration;
	
	public AStarEngine(AStarFramework<T> framework) {
		this.framework = framework;
		this.listeners = new HashSet<AStarListener<T>>();
		this.hasSearched = false;
		this.previousSolution = null;
	}
	
	public void addAStarListener(AStarListener<T> listener) {
		listeners.add(listener);
	}
	
	public List<T> search() {
		hasSearched = true;
		
		// Sets up necessary data structures for search
		T start = framework.getStartNode();
		T end = framework.getEndNode();
		List<T> openSet = new LinkedList<T>();
		List<T> closedSet = new ArrayList<T>();
		Map<T, Double> gScores = new HashMap<T, Double>();
		Map<T, T> cameFrom = new HashMap<T, T>();
		
		openSet.add(start);
		gScores.put(start, 0.0);
		
		// Clears analytics
		nodesChecked = 1;
		LocalTime timeStart = LocalTime.now();
		
		// Setup Actions
		for (AStarListener<T> listener : listeners) {
			listener.setupAction(openSet);
		}
		
		// Loops while there are Nodes in openSet
		while (! openSet.isEmpty()) {
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
				LocalTime timeEnd = LocalTime.now();
				previousSolution = reconstruct(cameFrom, end);
				
				// Finished Actions
				for (AStarListener<T> listener : listeners) {
					listener.searchCompleteAction(previousSolution);
				}
				
				// End the search
				searchDuration = calculateTimeElapsed(timeStart, timeEnd);
				return previousSolution;
			}
			
			// Remove current from openSet and evaluate each neighbor
			nodesChecked++;
			openSet.remove(current);
			closedSet.add(current);
			
			// Current Node Update Actions
			for (AStarListener<T> listener : listeners) {
				listener.updatedCurrentNodeAction(openSet, closedSet,
						cameFrom, current);
			}
			
			// For each neighbor of current
			for (T neighbor : framework.getNeighbors(current)) {
				if (! closedSet.contains(neighbor)) {
					double tempG = gScores.get(current) +
							framework.getDistance(current, neighbor);
					if (! openSet.contains(neighbor) ||
							gScores.get(neighbor) == null) {
						cameFrom.put(neighbor, current);
						gScores.put(neighbor, tempG);
					} else if (tempG <= gScores.get(neighbor)) {
						cameFrom.put(neighbor, current);
						gScores.put(neighbor, tempG);
					}
					
					// Add neighbor to openSet if not already
					if (! openSet.contains(neighbor)) {
						openSet.add(neighbor);
					}
				}
			}
		}
		
		// If openSet has no more elements
		// Stops time and does NoResult Actions
		LocalTime timeEnd = LocalTime.now();
		for (AStarListener<T> listener : listeners) {
			listener.noResultAction();
		}
		
		// Sets analytics
		previousSolution = null;
		nodesInOptimalPath = - 1;
		searchDuration = calculateTimeElapsed(timeStart, timeEnd);
		return null;
	}
	
	public List<T> getPreviousSolution() {
		return previousSolution;
	}
	
	public int getNodesChecked() {
		return nodesChecked;
	}
	
	public int getNodesInOptimalPath() {
		return nodesInOptimalPath;
	}
	
	public String getSearchDuration() {
		return searchDuration;
	}
	
	public void printAnalytics() {
		if (hasSearched) {
			System.out.println("-------------------------------"); // 31
			System.out.println("-----------ANALYTICS-----------"); // 31
			System.out.println("-------------------------------"); // 31
			System.out.println();
			
			// Duration
			System.out.print("Search Duration: ");
			System.out.println(searchDuration);
			
			// Search size
			System.out.print("Search size: ");
			System.out.print(nodesInOptimalPath);
			System.out.println(" nodes.");
			
			System.out.println();
			System.out.println("-------------------------------"); // 31
			System.out.println("------------RESULTS------------"); // 31
			System.out.println("-------------------------------"); // 31
			System.out.println();
			
			// Previous Result
			if (previousSolution == null) {
				System.out.println("No solution");
			} else {
				System.out.print("Optimal Path Length: ");
				System.out.print(nodesInOptimalPath);
				System.out.println(" nodes.");
				System.out.println("Solution:");
				for (T t : previousSolution) {
					System.out.println(t);
				}
			}
		} else {
			System.out.println("No searches performed.");
		}
		
	}
	
	private List<T> reconstruct(
			Map<T, T> cameFrom, T end) {
		// Sets up data structures
		List<T> o = new LinkedList();
		
		// Adds end node to the optimalPath
		o.add(end);
		
		// Resets analytics
		nodesInOptimalPath = 1;
		
		T current = end;
		while (cameFrom.containsKey(current)) {
			current = cameFrom.get(current);
			o.add(0, current);
			nodesInOptimalPath++;
		}
		
		return o;
	}
	
	private String calculateTimeElapsed(LocalTime timeStart,
	                                    LocalTime timeEnd) {
		// Determines time passed in seconds
		Duration duration = Duration.between(timeStart, timeEnd);
		double doubleMills = (double) duration.toMillis();
		double rawTime = doubleMills / 1000;
		
		// Calculates minutes
		int minutes = 0;
		while (rawTime >= 60) {
			rawTime -= 60;
			minutes++;
		}
		
		// Calculates Seconds
		int seconds = 0;
		while (rawTime >= 1) {
			rawTime -= 1;
			seconds++;
		}
		
		// Calculates milliseconds
		double m = rawTime * 1000;
		int milliSeconds = (int) m;
		
		// Formats strings
		
		StringBuilder sb = new StringBuilder();
		if (minutes > 0) {
			sb.append(minutes);
			sb.append(":");
			sb.append(seconds);
			sb.append(".");
			sb.append(milliSeconds);
			sb.append(" minutes");
		} else {
			sb.append(seconds);
			sb.append(".");
			sb.append(milliSeconds);
			sb.append(" seconds");
		}
		
		return sb.toString();
	}
}
