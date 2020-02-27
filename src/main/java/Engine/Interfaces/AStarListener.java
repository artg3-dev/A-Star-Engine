/*------------------------------------------------------------------------------
 @Author Art Garcia
 Copyright (c) 2/2020
 Project: AStarEngine
 Last Modified: 2/25/20, 7:14 PM
 License: GNU Lesser General Public License v3.0
 -----------------------------------------------------------------------------*/

package Engine.Interfaces;

import Engine.Abstracts.AbstractNode;

import java.util.List;

public interface AStarListener {
	void setupAction(List<AbstractNode> openSet);
	void updatedCurrentNodeAction(List<AbstractNode> openSet,
	                              List<AbstractNode> closedSet,
	                              AbstractNode current);
	void searchCompleteAction(List<AbstractNode> optimalPath);
	void noResultAction();
}
