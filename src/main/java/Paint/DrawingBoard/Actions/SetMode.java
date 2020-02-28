/*------------------------------------------------------------------------------
 @Author Art Garcia
 Copyright (c) 2/2020
 Project: AStarEngine
 Last Modified: 2/28/20, 2:17 PM
 License: GNU Lesser General Public License v3.0
 -----------------------------------------------------------------------------*/

package Paint.DrawingBoard.Actions;

import Paint.DrawingBoard.DrawingBoard;

import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;

public class SetMode extends AbstractAction {
	private final DrawingBoard canvas;
	private final int mode;
	
	public SetMode(DrawingBoard canvas, int mode) {
		this.canvas = canvas;
		this.mode = mode;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		canvas.setMode(this.mode);
		System.out.println(this.mode);
	}
}
