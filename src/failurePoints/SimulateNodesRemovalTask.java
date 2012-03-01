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
import java.util.Random;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.apache.commons.lang.ArrayUtils;

public class SimulateNodesRemovalTask implements Task {

    private int N; // Max number of nodes to remove
    private int M; // Number of iterations
   
    private CyNetwork network;
    private CyNetworkView view;
    private TaskMonitor taskMonitor = null;
    private boolean interrupted = false;

    private JTable simulationStatsTable;
    
    public static Random generator = new Random();
    

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

        if (taskMonitor == null) {
            throw new IllegalStateException("Task Monitor is not set.");
        }
        try {
            ((DefaultTableModel)simulationStatsTable.getModel()).setRowCount(0);
            ((DefaultTableModel)simulationStatsTable.getModel()).setRowCount(N);
            
            for (int n = 1; n <= N && !interrupted; n++) {
                int percentComplete = (int) (((double) (n-1)/N) * 100);
                taskMonitor.setPercentCompleted(percentComplete);
                double avgCount = 0;
                for (int m = 1; m <= M && !interrupted; m++) {
                    // Calculate Percentage.  This must be a value between 0..100.
                    
                    taskMonitor.setStatus("Nodes removed: " + Integer.toString(n) + "; iteration: " + Integer.toString(m));

                    int count = 0;
                    
                    int allNodes[] = network.getNodeIndicesArray();
                    int rndNodes[] = new int[n];

                    // Pick n random nodes, remove them from allNodes
                    for (int i = 1; i <= n && !interrupted; i++) {
                        int rnd = generator.nextInt(allNodes.length);
                        rndNodes[i-1] = allNodes[rnd];
                        allNodes = ArrayUtils.remove(allNodes, rnd);
                    }

                    int i = 0;
                    while (i < allNodes.length && !interrupted) {
                        boolean failure = false;

                        if (core.isFailurePoint(allNodes[i], this.network)) {
                            failure = true;
                            count++;
                        }
                        i++;
                    }
                    avgCount += count;
                }
                avgCount = avgCount/M;
                
                System.out.println(Integer.toString(n) + " : " + new DecimalFormat("#.##").format(avgCount));
                // TODO update table with n and avgCount
                simulationStatsTable.setValueAt(n, n-1, 0);
                simulationStatsTable.setValueAt(new DecimalFormat("#.##").format(avgCount), n-1, 1);
            }
        } catch (Exception e) {
            taskMonitor.setException(e, "Exception");
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
        return new String("Nodes Removal Simulation");
    }
}