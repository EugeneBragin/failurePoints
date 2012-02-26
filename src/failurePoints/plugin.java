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
    selectFailurePointsAction selectAction = new selectFailurePointsAction(this);
    Cytoscape.getDesktop().getCyMenus().addCytoscapeAction((CytoscapeAction)selectAction);
  }
  

  public class selectFailurePointsAction extends CytoscapeAction {

    public selectFailurePointsAction(plugin myPlugin) {
      super("Select Failure Points");
      setPreferredMenu("Plugins.Failure Points");
    }

    public void actionPerformed(ActionEvent e) {
    }

  }  
  

}