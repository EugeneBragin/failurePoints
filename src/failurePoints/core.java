package failurePoints;

import giny.model.GraphPerspective;
import java.util.HashMap;
import java.util.Vector;


public class core {

    /**
     * isFailurePoint checks if given node is a failure point or not
     * 
     * @param nodeId id of the node to check
     * @param graph containing the node (clone of the CyNetwork)
     * @return true/false respectively
     */
    public static boolean isFailurePoint(int nodeId, GraphPerspective graph) {
        boolean failure = false;
        HashMap visited = new HashMap();
        visited.put(nodeId, 1);

        int kids[] = graph.getAdjacentEdgeIndicesArray(nodeId, false, true, true);

        if (kids.length >= 2) {
            int firstKidID = graph.getEdgeTargetIndex(kids[0]);
            if (firstKidID == nodeId) {
                firstKidID = graph.getEdgeSourceIndex(kids[0]);
            }

            for (int i = 1; i < kids.length; i++) {
                int xKidId = graph.getEdgeTargetIndex(kids[i]);
                if (xKidId == nodeId) {
                    xKidId = graph.getEdgeSourceIndex(kids[i]);
                }

                if (!core.hasPath(firstKidID, xKidId, new HashMap(visited), graph)) {
                    failure = true;
                    break;
                }
            }
        }

        return failure;
    }

    
    /**
     * hasPath checks if path between two nodes exist, avoiding visited
     * modified version of the hasPath function given with assignment
     * main difference is it accepts visited vector with node in question 
     * pre-populated and uses GraphPerspective clone of the network instead of CyNetwork itself
     * 
     * @param source id of the start node
     * @param target id of the end node
     * @param visited nodes to avoid in {id => 1} format
     * @param graph which to perform operation on (clone of the CyNetwork)
     * @return true/false respectively
     */
    
    public static boolean hasPath(int source, int target, HashMap visited, GraphPerspective graph) {

        //the hasPath boolean is created with inital value false
        boolean hasPath = false;

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
                    int kids[] = graph.getAdjacentEdgeIndicesArray(curNode, false, true, true);
                    //Let's loop through all the outgoing edges to get the children
                    //of the curNode
                    for (int i = 0; i < kids.length; i++) {
                        //The neighbor node of the kids[i]-th edge is going to be the node that is the target
                        //of the kids[i]-th edge
                        int neighborNodeID = graph.getEdgeTargetIndex(kids[i]);
                        if (neighborNodeID == curNode) {
                            neighborNodeID = graph.getEdgeSourceIndex(kids[i]);
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
