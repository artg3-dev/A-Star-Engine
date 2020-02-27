/*------------------------------------------------------------------------------
 @Author Art Garcia
 Copyright (c) 2/2020
 Project: AStarEngine
 Last Modified: 2/25/20, 4:44 PM
 License: GNU Lesser General Public License v3.0
 -----------------------------------------------------------------------------*/

package Engine.Interfaces;

import Engine.Abstracts.AbstractNode;

import java.util.List;

public interface AStarFramework<T>{
	T getStartNode();
	T getEndNode();
	double getHeuristic(T node);
	double getF(T node, double gScore);
	double getDistance(T node1, T node2);
	List<T> getNeighbors(T node);
}
