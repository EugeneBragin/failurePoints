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
import cytoscape.task.Task;
import cytoscape.task.sample.SampleTask;
import cytoscape.task.ui.JTaskConfig;
import cytoscape.task.util.TaskManager;
import cytoscape.util.CytoscapeAction;
import cytoscape.view.CyNetworkView;
import cytoscape.view.CytoscapeDesktop;
import cytoscape.view.cytopanels.CytoPanelImp;
import giny.view.NodeView;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author eb4
 */
public class plugin extends CytoscapePlugin {

    failurePointsControlPanel failurePointsPanel = new failurePointsControlPanel();
    
    
    public plugin() {
        
		// Three steps
		
		// Get the handler to cytoPanel west
		CytoPanelImp ctrlPanel = (CytoPanelImp) Cytoscape.getDesktop().getCytoPanel(SwingConstants.WEST); 
				
		// Add the failurePointsPanel object to the cytoPanel west
		ctrlPanel.add("Failure Points", failurePointsPanel);

		// Select the panel after the plugin is initialized
		int indexInCytoPanel = ctrlPanel.indexOfComponent("Failure Points");
		ctrlPanel.setSelectedIndex(indexInCytoPanel);        
        
		EventListener eventListener = new EventListener();
    }

    
	public class EventListener implements  PropertyChangeListener {
		
		public EventListener() {
			// Register this class as a listener to listen Cytoscape events
			Cytoscape.getPropertyChangeSupport().addPropertyChangeListener(Cytoscape.NETWORK_CREATED, this);
			Cytoscape.getPropertyChangeSupport().addPropertyChangeListener(Cytoscape.NETWORK_MODIFIED, this);
			Cytoscape.getPropertyChangeSupport().addPropertyChangeListener(Cytoscape.NETWORK_DESTROYED, this);
		}
		
		// Handle PropertyChangeEvent
		public void propertyChange(PropertyChangeEvent e) {
            if (e.getPropertyName().equalsIgnoreCase(Cytoscape.NETWORK_CREATED)) {
                failurePointsPanel.updateUI(Cytoscape.getCurrentNetwork());
                failurePointsPanel.enableUI();
            } 
            if (e.getPropertyName().equalsIgnoreCase(Cytoscape.NETWORK_MODIFIED)) {
                failurePointsPanel.updateUI(Cytoscape.getCurrentNetwork());
            } 
            if (e.getPropertyName().equalsIgnoreCase(Cytoscape.NETWORK_DESTROYED)) {
                failurePointsPanel.disableUI();
            }
		}
	}
    
}