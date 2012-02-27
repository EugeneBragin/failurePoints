/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package failurePoints;

import cytoscape.CyNetwork;
import java.util.HashMap;
import java.util.Vector;

/**
 *
 * @author Eugene
 */
public class util {

  public static boolean hasPathAvoiding(int source, int target, int avoid, CyNetwork net) {

    //the hasPath boolean is created with inital value false
    boolean hasPath = false;

    HashMap visited = new HashMap();
    visited.put(avoid, 1);
    
    //The following wector contains the nodes that we still have to process
    Vector<Integer> toConsider = new Vector<Integer>();
    //The only node to consider at the beginning is the source node
    toConsider.add(source);

    //While we have nodes to consider and we have not visited all the nodes and we
    //have not found the target node on the way...
    while (toConsider.size() > 0 && !hasPath) {
      //Retrieve the current node from the nodes to consider
      int curNode = toConsider.elementAt(0);
      //Remove the current node from the nodes to consider
      toConsider.removeElementAt(0);
      //If the node was not visited before
      //then let's mark it as visited
      //(i.e. add an entry to the visited hash map).
      if (!visited.containsKey(curNode)) {
        visited.put(curNode, 1);
        //If the current node is the target node then we are done
        //set the result to current node and let the loop know that we found the target
        if (curNode == target) {
          //result = curNode;
          hasPath = true;
        } else {
          //We have to retrieve all the children nodes of curNode and
          //add them to the list of nodes to consider

          //Let's get all the outgoing edges from the curNode
          int kids[] = net.getAdjacentEdgeIndicesArray(curNode, false, true, true);
          //Let's loop through all the outgoing edges to get the children
          //of the curNode
          for (int i = 0; i < kids.length; i++) {
            //The neighbor node of the kids[i]-th edge is going to be the node that is the target
            //of the kids[i]-th edge
            int neighborNodeID = net.getEdgeTargetIndex(kids[i]);
            if (neighborNodeID == curNode) {
              neighborNodeID = net.getEdgeSourceIndex(kids[i]);
            }
            //Has the neighbor node been visited yet ??
            if (!visited.containsKey(neighborNodeID)) {
              //If not, add it as a child of the currentNode
              //curNode.addChild(neighborNodeID);
              //Create a pathnode object for it
              //pathNode nextNode = new pathNode(neighborNodeID, curNode.ID, path.size());
              //and add to the nodes to be considered
              toConsider.add(neighborNodeID);
            }

          }
        }

      }
    }

    //Some cleaning up if the structure is not empty.
    toConsider.clear();
    //Finally let's return the result


    return hasPath;
  }
  
  
  /*
   * This is a method that returns a boolean value true if there is a path between the source and target nodes, and
   * false if no path can be identified. the method signature is expecting 3 parameters: 1. the ID of the source node
   * (int value) 2. the ID of the target node (int value) a CyNetwork instance
   */
  public static boolean hasPath(int source, int target, CyNetwork net) {
    //the hasPath boolean is created with inital value false
    boolean hasPath = false;
    HashMap visited = new HashMap();
    //The following wector contains the nodes that we still have to process
    Vector<Integer> toConsider = new Vector<Integer>();
    //The only node to consider at the beginning is the source node
    toConsider.add(source);

    //While we have nodes to consider and we have not visited all the nodes and we
    //have not found the target node on the way...
    while (toConsider.size() > 0 && !hasPath) {
      //Retrieve the current node from the nodes to consider
      int curNode = toConsider.elementAt(0);
      //Remove the current node from the nodes to consider
      toConsider.removeElementAt(0);
      //If the node was not visited before
      //then let's mark it as visited
      //(i.e. add an entry to the visited hash map).
      if (!visited.containsKey(curNode)) {
        visited.put(curNode, 1);
        //If the current node is the target node then we are done
        //set the result to current node and let the loop know that we found the target
        if (curNode == target) {
          //result = curNode;
          hasPath = true;
        } else {
          //We have to retrieve all the children nodes of curNode and
          //add them to the list of nodes to consider

          //Let's get all the outgoing edges from the curNode
          int kids[] = net.getAdjacentEdgeIndicesArray(curNode, false, true, true);
          //Let's loop through all the outgoing edges to get the children
          //of the curNode
          for (int i = 0; i < kids.length; i++) {
            //The neighbor node of the kids[i]-th edge is going to be the node that is the target
            //of the kids[i]-th edge
            int neighborNodeID = net.getEdgeTargetIndex(kids[i]);
            if (neighborNodeID == curNode) {
              neighborNodeID = net.getEdgeSourceIndex(kids[i]);
            }
            //Has the neighbor node been visited yet ??
            if (!visited.containsKey(neighborNodeID)) {
              //If not, add it as a child of the currentNode
              //curNode.addChild(neighborNodeID);
              //Create a pathnode object for it
              //pathNode nextNode = new pathNode(neighborNodeID, curNode.ID, path.size());
              //and add to the nodes to be considered
              toConsider.add(neighborNodeID);
            }

          }
        }

      }
    }

    //Some cleaning up if the structure is not empty.
    toConsider.clear();
    //Finally let's return the result


    return hasPath;
  }
}
