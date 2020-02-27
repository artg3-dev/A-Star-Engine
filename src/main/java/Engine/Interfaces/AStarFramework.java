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

public interface AStarFramework {
	AbstractNode getStartNode();
	AbstractNode getEndNode();
	double getHeuristic(AbstractNode node);
	double getF(AbstractNode node, double gScore);
	double getDistance(AbstractNode node1, AbstractNode node2);
	List<AbstractNode> getNeighbors(AbstractNode node);
}
