
/*------------------------------------------------------------------------------
 @Author Art Garcia
 Copyright (c) 2/2020
 Project: AStarEngine
 Last Modified: 2/23/20, 10:11 AM
 License: GNU Lesser General Public License v3.0
 -----------------------------------------------------------------------------*/

import Engine.AStarEngine;
import StandardUses.Standard2D.Grid2D;
import Engine.Node;
import StandardUses.Standard2D.Framework2D;
import StandardUses.Standard2D.Listener2D;
import StandardUses.Standard2D.Node2D;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Main {
    private final static Node end = new Node(5, 5);

    public static void main(String[] args) {
        Grid2D grid = new Grid2D(5, 5);
        Node2D start = grid.getNode(0,0);
        Node2D end = grid.getNode(4,4);
        grid.setObstacle(2, 1, true);
        grid.setObstacle(2, 1, true);
        grid.setObstacle(2, 2, true);
        grid.setObstacle(2, 3, true);
        Framework2D framework = new Framework2D(grid, start, end);
        Listener2D listener = new Listener2D();
        AStarEngine engine = new AStarEngine(framework);
        engine.addAStarListener(listener);
        engine.search();
    }

    private static List<Node> runAStar(Node start) {
        List<Node> openSet = new LinkedList();
        List<Node> closedSet = new ArrayList();
        Map<Node, Double> gScores = new HashMap();
        Map<Node, Node> cameFrom = new HashMap();
        openSet.add(start);
        gScores.put(start, 0.0);

        // Loops while there are Nodes in openSet
        while (!openSet.isEmpty()) {
            // Defining current as the Node with the lowest fScore in openSet
            Node current = null;
            for (Node i : openSet) {
                if (current == null || getF(i, gScores.get(i)) <
                        getF(current, gScores.get(current))) {
                    current = i;
                }
            }

            // If the end has been reached
            if (current.equals(end)) {
                System.out.println("Done!");
                // Do the reconstruct function here
                List<Node> result = reconstruct(cameFrom);
                return result;
            }
            
            // Remove current from openSet and evaluate each neighbor
            openSet.remove(current);
            closedSet.add(current);
            
            // For each neighbor of current
            for (Node neighbor : getNeighbors(current)) {
                if (!closedSet.contains(neighbor)) {
                    double tempG = gScores.get(current) +
                            getDistance(current, neighbor);
                    if (!openSet.contains(neighbor) ||
                            gScores.get(neighbor) == null) {
                        cameFrom.put(neighbor, current);
                        gScores.put(neighbor, tempG);
                    } else if (tempG <= gScores.get(neighbor)) {
                        cameFrom.put(neighbor, current);
                        gScores.put(neighbor, tempG);
                    }
                    
                    // Add neighbor to openSet if not already
                    if (!openSet.contains(neighbor)) {
                        openSet.add(neighbor);
                    }
                }
            }
        }
        
        // If openSet has no more elements
        System.out.println("No solution.");
        return null;
    }

    private static double getHeuristic(Node node) {
        double a = end.getX() - node.getX();
        double b = end.getY() - node.getY();
        double cSq = Math.pow(a, 2) + Math.pow(b, 2);
        return Math.sqrt(cSq);
    }

    private static double getF(Node node, double gScore) {
        return gScore + getHeuristic(node);
    }
    
    private static double getDistance(Node n1, Node n2) {
        double a = n2.getX() - n1.getX();
        double b = n2.getY() - n1.getY();
        double cSq = Math.pow(a, 2) + Math.pow(b, 2);
        return Math.sqrt(cSq);
    }
    
    private static ArrayList<Node> getNeighbors(Node n) {
        ArrayList<Node> neighbors = new ArrayList();
        double x = n.getX();
        double y = n.getY();
        for (int i = -1; i <= 1; i ++) {
            for (int j = -1; j <= 1; j ++) {
                double neighborX = x + i;
                double neighborY = y + j;
                if ((neighborX >= 0 && neighborY >= 0) &&
                        !(neighborX == x && neighborY == y))  {
                    neighbors.add(new Node(neighborX, neighborY));
                }
            }
        }
        
        return neighbors;
    }
    
    private static List<Node> reconstruct(Map<Node, Node> cameFrom) {
        List<Node> r = new LinkedList();
        r.add(end);
        Node current = end;
        while (cameFrom.containsKey(current)) {
            current = cameFrom.get(current);
            r.add(current);
        }
        
        return r;
    }
}
