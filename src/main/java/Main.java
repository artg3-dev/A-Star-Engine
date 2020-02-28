
/*------------------------------------------------------------------------------
 @Author Art Garcia
 Copyright (c) 2/2020
 Project: AStarEngine
 Last Modified: 2/23/20, 10:11 AM
 License: GNU Lesser General Public License v3.0
 -----------------------------------------------------------------------------*/

import Paint.PaintWindow;
import StandardUses.Examples;
import StandardUses.Standard2D.Framework2D;

import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        PaintWindow gui = new PaintWindow();
        SwingUtilities.invokeLater(gui);
    }
}
