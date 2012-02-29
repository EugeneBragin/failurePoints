/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package failurePoints;

import cytoscape.CyNetwork;
import cytoscape.view.CyNetworkView;

import cytoscape.task.Task;
import cytoscape.task.TaskMonitor;
import giny.view.NodeView;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.io.IOException;
import java.text.DecimalFormat;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

public class SimulateNodesRemovalTask implements Task {

    private int N; // Max number of nodes to remove
    private int M; // Number of iterations
   
    private CyNetwork network;
    private CyNetworkView view;
    private TaskMonitor taskMonitor = null;
    private boolean interrupted = false;

    private JTable simulationStatsTable;

    /**
     * Constructor.
     *
     * @param network CyNetwork
     */
    public SimulateNodesRemovalTask(CyNetwork network, CyNetworkView view, int N, int M, JTable simulationStatsTable) {
        this.network = network;
        this.view = view;
        this.N = N;
        this.M = M;
        this.simulationStatsTable = simulationStatsTable;
    }

    /**
     * Run the Task.
     */
    public void run() {
        int count = 0;
        double avgDegree = 0;

        if (taskMonitor == null) {
            throw new IllegalStateException("Task Monitor is not set.");
        }
        try {
            int allNodes[] = this.network.getNodeIndicesArray();
            int rndNodes[] = core.pickNRandomNodes(N, network);

            for (int i = 0; i < rndNodes.length; i++) {
                NodeView nView = this.view.getNodeView(rndNodes[i]);
                nView.setSelected(true);
            }
            
            this.view.redrawGraph(true, true);

        } catch (Exception e) {
            taskMonitor.setException(e, "Exception");
        } finally {
        }
    }

    /**
     * Non-blocking call to interrupt the task.
     */
    public void halt() {
        this.interrupted = true;
    }

    /**
     * Sets the Task Monitor.
     *
     * @param taskMonitor TaskMonitor Object.
     */
    public void setTaskMonitor(TaskMonitor taskMonitor) {
        if (this.taskMonitor != null) {
            throw new IllegalStateException("Task Monitor is already set.");
        }
        this.taskMonitor = taskMonitor;
    }

    /**
     * Gets the Task Title.
     *
     * @return human readable task title.
     */
    public String getTitle() {
        return new String("Simulation...");
    }
}