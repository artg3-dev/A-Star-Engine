/*------------------------------------------------------------------------------
 @Author Art Garcia
 Copyright (c) 2/2020
 Project: AStarEngine
 Last Modified: 2/28/20, 2:27 PM
 License: GNU Lesser General Public License v3.0
 -----------------------------------------------------------------------------*/

package Paint.DrawingBoard.UI;

import Engine.AStarEngine;
import Engine.Interfaces.AStarFramework;
import Paint.DrawingAStarListener;
import Paint.DrawingBoard.DrawingBoard;
import Paint.DrawingBoard.PaintGrid;
import StandardUses.Standard2D.Framework2D;
import StandardUses.Standard2D.Node2D;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DrawingUI extends JPanel implements ActionListener {
	private final DrawingBoard canvas;
	private final JButton searchButton, resetButton;
	
	public DrawingUI(int gridWidth, int gridHeight) {
		this.canvas = new DrawingBoard(gridWidth, gridHeight);
		this.searchButton = new JButton("Search");
		this.resetButton = new JButton("Reset");
		searchButton.addActionListener(this);
		resetButton.addActionListener(this);
		layoutComponents();
	}
	
	private void layoutComponents() {
		setLayout(new GridBagLayout());
		GridBagConstraints c;
		
		// Canvas
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		add(canvas, c);
		
		// Search button
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		add(searchButton, c);
		
		// Reset Button
		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		add(resetButton, c);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(searchButton)) {
			PaintGrid grid = canvas.getGrid();
			DrawingAStarListener listener = new DrawingAStarListener(canvas);
			AStarFramework<Node2D> framework = new Framework2D(
					grid, grid.getStart(), grid.getEnd(), true);
			AStarEngine<Node2D> engine = new AStarEngine(framework);
			engine.addAStarListener(listener);
			engine.search();
		}
		if (e.getSource().equals(resetButton)) {
			canvas.reset();
		}
	}
}
