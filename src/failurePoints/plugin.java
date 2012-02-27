/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package failurePoints;


import cytoscape.CyNetwork;
import java.awt.event.ActionEvent;

import cytoscape.Cytoscape;
import cytoscape.view.CyMenus;
import cytoscape.plugin.CytoscapePlugin;
import cytoscape.util.CytoscapeAction;
import cytoscape.view.CyNetworkView;
import javax.swing.JOptionPane;


/**
 *
 * @author eb4
 */
public class plugin extends CytoscapePlugin {

  public plugin() {
    Cytoscape.getDesktop().getCyMenus().addCytoscapeAction(new highlightFailurePointsAction(this));
    Cytoscape.getDesktop().getCyMenus().addCytoscapeAction(new hasPathAction(this));
    Cytoscape.getDesktop().getCyMenus().addCytoscapeAction(new hasPathAvoidingAction(this));
    Cytoscape.getDesktop().getCyMenus().addCytoscapeAction(new isFailureNodeAction(this));
  }
  

  public class highlightFailurePointsAction extends CytoscapeAction {

    CyNetwork network;
    CyNetworkView view;
    
    public highlightFailurePointsAction(plugin myPlugin) {
      super("Highlight Failure Points");
      setPreferredMenu("Plugins.Failure Points");
    }

    public void actionPerformed(ActionEvent e) {
      view = Cytoscape.getCurrentNetworkView();
      network = Cytoscape.getCurrentNetwork();
      
      // Loop through all nodes
      int allNodes[] = network.getNodeIndicesArray();
      for (int i = 0; i < allNodes.length; i++) {
        if (util.isFailurePoint(allNodes[i], network)) {
          System.out.println(allNodes[i] + "is a failure point");
        }
      }
      
    }
  }  
  
  public class hasPathAction extends CytoscapeAction {

    CyNetwork network;
    CyNetworkView view;    

    public hasPathAction(plugin myPlugin) {
      super("Has path");
      setPreferredMenu("Plugins.Failure Points");
    }

    public void actionPerformed(ActionEvent e) {
      view = Cytoscape.getCurrentNetworkView();
      network = Cytoscape.getCurrentNetwork();
      
      if (view.getSelectedNodeIndices().length != 2) {
          JOptionPane.showMessageDialog(view.getComponent(), "Please select 2 nodes");
      } else {
          int selectedNodes[] = view.getSelectedNodeIndices();
          //calling the method hasPath (implemented below) to see if there is a path
          //between selectedNode[0] and selectedNodes[1], then print the result to the
          //command line
          boolean isConnected = util.hasPath(selectedNodes[0], selectedNodes[1], network);
          System.out.println(isConnected);
      }
    }
  }  
  
  public class hasPathAvoidingAction extends CytoscapeAction {

    CyNetwork network;
    CyNetworkView view;    

    public hasPathAvoidingAction(plugin myPlugin) {
      super("Has path avoiding");
      setPreferredMenu("Plugins.Failure Points");
    }

    public void actionPerformed(ActionEvent e) {
      view = Cytoscape.getCurrentNetworkView();
      network = Cytoscape.getCurrentNetwork();
      
      if (view.getSelectedNodeIndices().length != 2) {
          JOptionPane.showMessageDialog(view.getComponent(), "Please select 2 nodes");
      } else {
          int selectedNodes[] = view.getSelectedNodeIndices();
          //calling the method hasPath (implemented below) to see if there is a path
          //between selectedNode[0] and selectedNodes[1], then print the result to the
          //command line
          boolean isConnected = util.hasPathAvoiding(selectedNodes[0], selectedNodes[1], -1, network);
          System.out.println(isConnected);
      }
    }
  }  

  public class isFailureNodeAction extends CytoscapeAction {

    CyNetwork network;
    CyNetworkView view;    

    public isFailureNodeAction(plugin myPlugin) {
      super("Is Failure Node");
      setPreferredMenu("Plugins.Failure Points");
    }

    public void actionPerformed(ActionEvent e) {
      view = Cytoscape.getCurrentNetworkView();
      network = Cytoscape.getCurrentNetwork();
      boolean failure = false;
      
      if (view.getSelectedNodeIndices().length != 1) {
          JOptionPane.showMessageDialog(view.getComponent(), "Please select 1 node");
      } else {
        int selectedNodes[] = view.getSelectedNodeIndices();
        int nodeId = selectedNodes[0];
          
        int kids[] = network.getAdjacentEdgeIndicesArray(nodeId, false, true, true);
        if (kids.length >= 2) {
          int firstKidID = network.getEdgeTargetIndex(kids[0]);
          if (firstKidID == nodeId) {
            firstKidID = network.getEdgeSourceIndex(kids[0]);
          }            
            
          for (int i = 1; i < kids.length; i++) {
            int xKidId = network.getEdgeTargetIndex(kids[i]);
            if (xKidId == nodeId) {
              xKidId = network.getEdgeSourceIndex(kids[i]);
            }
            
            if (!util.hasPathAvoiding(firstKidID, xKidId, nodeId, network)) {
              failure = true;
              break;
            }
            
          }
        }
      }
      System.out.println("It " + (failure ? "is" : "isn't") + " failure node");
    }
  }    
  

}