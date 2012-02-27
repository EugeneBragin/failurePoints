/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package failurePoints;


import java.awt.event.ActionEvent;

import cytoscape.Cytoscape;
import cytoscape.view.CyMenus;
import cytoscape.plugin.CytoscapePlugin;
import cytoscape.util.CytoscapeAction;


/**
 *
 * @author eb4
 */
public class plugin extends CytoscapePlugin {

  public plugin() {
    highlightFailurePointsAction highlightAction = new highlightFailurePointsAction(this);
    Cytoscape.getDesktop().getCyMenus().addCytoscapeAction((CytoscapeAction)highlightAction);
  }
  

  public class highlightFailurePointsAction extends CytoscapeAction {

    public highlightFailurePointsAction(plugin myPlugin) {
      super("Highlight Failure Points");
      setPreferredMenu("Plugins.Failure Points");
    }

    public void actionPerformed(ActionEvent e) {
    }

  }  
  

}