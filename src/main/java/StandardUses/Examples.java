/*------------------------------------------------------------------------------
 @Author Art Garcia
 Copyright (c) 2/2020
 Project: AStarEngine
 Last Modified: 2/28/20, 10:52 AM
 License: GNU Lesser General Public License v3.0
 -----------------------------------------------------------------------------*/

package StandardUses;

import Engine.AStarEngine;
import StandardUses.Standard2D.Framework2D;
import StandardUses.Standard2D.Grid2D;
import StandardUses.Standard2D.Listener2D;
import StandardUses.Standard2D.Node2D;

import java.util.List;

public class Examples {
	public static void run2DTextBasedExample() {
		// Setup example
		Grid2D grid = new Grid2D(5, 5);
		Node2D start = grid.getNode(0, 0);
		Node2D end = grid.getNode(4, 4);
		boolean diagonals = true;
		grid.setObstacle(2, 0, true);
		grid.setObstacle(2, 1, true);
		grid.setObstacle(1, 2, true);
		grid.setObstacle(1, 3, true);
		grid.setObstacle(3, 4, true);
		Framework2D framework = new Framework2D(grid, start, end, diagonals);
		Listener2D listener = new Listener2D();
		AStarEngine<Node2D> engine = new AStarEngine(framework);
		engine.addAStarListener(listener);
		
		// Display explanation text
		System.out.println("An example of an A* Search performed " +
				"on a 2D grid.");
		System.out.println("Framework: Framework2D");
		System.out.println("AStarListener: Listener2D");
		System.out.println(grid);
		System.out.println("-------------------------");
		System.out.println("---------SETUP-----------");
		System.out.println("-------------------------");
		
		// Print Grid
		for (int j = 0; j < grid.height; j++) {
			for (int i = 0; i < grid.width; i++) {
				if (grid.getNode(i, j).isObstacle()) {
					System.out.print("X");
				} else if (grid.getNode(i, j).equals(start)) {
					System.out.print("S");
				} else if (grid.getNode(i, j).equals(end)) {
					System.out.print("E");
				} else {
					System.out.print("*");
				}
			}
			System.out.println();
		}
		
		// Run the search
		engine.search();
		
		// Display the solution
		List<Node2D> previousSolution = engine.getPreviousSolution();
		
		System.out.println("-------------------------");
		System.out.println("---------SOLUTION--------");
		System.out.println("-------------------------");
		for (int j = 0; j < grid.height; j++) {
			for (int i = 0; i < grid.width; i++) {
				Node2D node = grid.getNode(i, j);
				if (node.isObstacle()) {
					System.out.print("X");
				} else if (previousSolution.contains(node)) {
					System.out.print(previousSolution.indexOf(node));
				} else {
					System.out.print("*");
				}
			}
			System.out.println();
		}
		
		// Run Analytics
		System.out.println();
		engine.printAnalytics();
		
	}
}
