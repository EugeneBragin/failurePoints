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
        
        Cytoscape.getDesktop().getCyMenus().addCytoscapeAction(new sampleTaskAction(this));
        Cytoscape.getDesktop().getCyMenus().addCytoscapeAction(new highlightFailurePointsAction(this));
        Cytoscape.getDesktop().getCyMenus().addCytoscapeAction(new isFailureNodeAction(this));
        
		EventListener eventListener = new EventListener();
    }
    
    
	class MyPanel extends JPanel {
		public MyPanel() {
		}
	}    
    

    public class sampleTaskAction extends CytoscapeAction {

        public sampleTaskAction(plugin myPlugin) {
            super("Sample Task");
            setPreferredMenu("Plugins.Failure Points");
        }

        public void actionPerformed(ActionEvent e) {
            //  Create a Sample Task
            Task task = new SampleTask(100, 100);

            //  Configure JTask
            JTaskConfig config = new JTaskConfig();

            //  Show Cancel/Close Buttons
            //config.displayUserButtons(true);

            //  Execute Task via TaskManager
            //  This automatically pops-open a JTask Dialog Box.
            //  This method will block until the JTask Dialog Box is disposed.
            boolean success = TaskManager.executeTask(task, config);
        }
    }

    public class highlightFailurePointsAction extends CytoscapeAction {

        CyNetwork network;
        CyNetworkView view;

        public highlightFailurePointsAction(plugin myPlugin) {
            super("Highlight Failure Points");
            setPreferredMenu("Plugins.Failure Points");
        }

        public void actionPerformed(ActionEvent e) {

        }
    }

    public class isFailureNodeAction extends CytoscapeAction {

        CyNetwork network;
        CyNetworkView view;

        public isFailureNodeAction(plugin myPlugin) {
            super("Test Selected Node");
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

                        if (!core.hasPathAvoiding(firstKidID, xKidId, nodeId, network)) {
                            failure = true;
                            break;
                        }

                    }
                }
            }
            System.out.println("It " + (failure ? "is" : "isn't") + " failure node");
        }
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