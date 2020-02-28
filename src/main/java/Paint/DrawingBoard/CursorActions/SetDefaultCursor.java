/*------------------------------------------------------------------------------
 @Author Art Garcia
 Copyright (c) 2/2020
 Project: AStarEngine
 Last Modified: 2/28/20, 1:28 PM
 License: GNU Lesser General Public License v3.0
 -----------------------------------------------------------------------------*/

package Paint.DrawingBoard.CursorActions;

import Paint.DrawingBoard.DrawingBoard;

public class SetDefaultCursor extends AbstractCursorAction {
	public SetDefaultCursor(DrawingBoard canvas) {
		super(canvas);
	}
	
	@Override
	void setCursor() {
		System.out.println("Default");
	}
}
