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

public class HighlightFailurePointsTask implements Task {

    private CyNetwork network;
    private CyNetworkView view;
    private TaskMonitor taskMonitor = null;
    private static final int MIN_VALUE = 0;
    private int maxValue;
    private long countDelay;
    private boolean interrupted = false;
    private int exceptionIndex = Integer.MAX_VALUE;
    
    private JLabel nOfFailureNodesLabel;
    private JLabel averageConnDegreeLabel;

    /**
     * Constructor.
     *
     * @param network CyNetwork
     */
    public HighlightFailurePointsTask(CyNetwork network, CyNetworkView view, JLabel nOfFailureNodesLabel, JLabel averageConnDegreeLabel) {
        this.network = network;
        this.view = view;
        this.nOfFailureNodesLabel = nOfFailureNodesLabel;
        this.averageConnDegreeLabel = averageConnDegreeLabel;
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
            int i = 0;
            taskMonitor.setStatus("Failure nodes found: ");

            for (i = 0; i < allNodes.length; i++) {
                NodeView nView = this.view.getNodeView(allNodes[i]);
                nView.setSelected(false);
            }
            this.view.redrawGraph(true, true);

            i = 0;
            while (i < allNodes.length && !interrupted) {
                boolean failure = false;

                // Calculate Percentage.  This must be a value between 0..100.
                int percentComplete = (int) (((double) (i + 1) / allNodes.length) * 100);
                taskMonitor.setPercentCompleted(percentComplete);
                if (core.isFailurePoint(allNodes[i], this.network)) {
                    failure = true;
                    count++;
                    avgDegree += this.network.getDegree(allNodes[i]);
                    taskMonitor.setStatus("Failure nodes found: " + count);
                }

                NodeView nView = this.view.getNodeView(allNodes[i]);
                nView.setSelected(failure);
                //this.view.redrawGraph(true, true);          
                //Thread.sleep(5);                
                i++;
            }
            avgDegree = avgDegree / count;

            this.view.redrawGraph(true, true);
        } catch (Exception e) {
            taskMonitor.setException(e, "Exception");
        } finally {
            nOfFailureNodesLabel.setText(Integer.toString(count));
            averageConnDegreeLabel.setText(new DecimalFormat("#.##").format(avgDegree));
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
        return new String("Searching for failure points");
    }
}