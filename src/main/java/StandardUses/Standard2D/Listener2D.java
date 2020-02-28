/*------------------------------------------------------------------------------
 @Author Art Garcia
 Copyright (c) 2/2020
 Project: AStarEngine
 Last Modified: 2/26/20, 6:38 PM
 License: GNU Lesser General Public License v3.0
 -----------------------------------------------------------------------------*/

package StandardUses.Standard2D;

import Engine.Interfaces.AStarListener;

import java.util.List;
import java.util.Map;

public class Listener2D implements AStarListener<Node2D> {
	public void setupAction(List<Node2D> openSet) {
		System.out.println("Setup");
	}
	
	public void updatedCurrentNodeAction(List<Node2D> openSet,
	                                     List<Node2D> closedSet,
	                                     Map<Node2D, Node2D> cameFrom,
	                                     Node2D current) {
		System.out.println("New Current: " + current);
	}
	
	public void searchCompleteAction(List<Node2D> optimalPath) {
		System.out.println("Optimal Path: ");
		for (Node2D node : optimalPath) {
			System.out.println(node);
		}
	}
	
	public void noResultAction() {
		System.out.println("No result");
	}
}
