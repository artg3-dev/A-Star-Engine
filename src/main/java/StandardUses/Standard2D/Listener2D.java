/*------------------------------------------------------------------------------
 @Author Art Garcia
 Copyright (c) 2/2020
 Project: AStarEngine
 Last Modified: 2/26/20, 6:38 PM
 License: GNU Lesser General Public License v3.0
 -----------------------------------------------------------------------------*/

package StandardUses.Standard2D;

import Engine.Abstracts.AbstractNode;
import Engine.Interfaces.AStarListener;

import java.util.List;

public class Listener2D implements AStarListener {
	public void setupAction(List<AbstractNode> openSet) {
		System.out.println("Setup");
	}
	
	public void updatedCurrentNodeAction(List<AbstractNode> openSet,
	                                     List<AbstractNode> closedSet,
	                                     AbstractNode current) {
		System.out.println("New Current: " + current);
	}
	
	public void searchCompleteAction(List<AbstractNode> optimalPath) {
		System.out.println("Optimal Path: ");
		for (AbstractNode abstractNode : optimalPath) {
			System.out.println(abstractNode);
		}
	}
	
	public void noResultAction() {
		System.out.println("No result");
	}
}
