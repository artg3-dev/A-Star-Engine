/*------------------------------------------------------------------------------
 @Author Art Garcia
 Copyright (c) 2/2020
 Project: AStarEngine
 Last Modified: 2/28/20, 2:34 PM
 License: GNU Lesser General Public License v3.0
 -----------------------------------------------------------------------------*/

package Paint;

import Engine.Interfaces.AStarListener;
import Paint.DrawingBoard.DrawingBoard;
import Paint.DrawingBoard.PaintGrid;
import StandardUses.Standard2D.Node2D;

import java.awt.Color;
import java.util.List;
import java.util.Map;

public class DrawingAStarListener implements AStarListener<Node2D> {
	private final DrawingBoard canvas;
	private final PaintGrid grid;
	
	public DrawingAStarListener(DrawingBoard canvas) {
		this.canvas = canvas;
		this.grid = canvas.getGrid();
	}
	
	@Override
	public void setupAction(List<Node2D> openSet) {
	
	}
	
	@Override
	public void updatedCurrentNodeAction(List<Node2D> openSet,
	                                     List<Node2D> closedSet,
	                                     Map<Node2D, Node2D> cameFrom,
	                                     Node2D current) {
		grid.clearMarkings();
		grid.markCell(current, Color.pink);
		canvas.repaint();
	}
	
	@Override
	public void searchCompleteAction(List<Node2D> optimalPath) {
		grid.clearMarkings();
		for (Node2D n : optimalPath) {
			grid.markCell(n, Color.gray);
		}
		canvas.repaint();
	}
	
	@Override
	public void noResultAction() {
		grid.clearMarkings();
		System.out.println("No Solution");
	}
}
