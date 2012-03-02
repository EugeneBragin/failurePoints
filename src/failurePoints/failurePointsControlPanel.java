/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package failurePoints;

import cytoscape.CyNetwork;
import cytoscape.Cytoscape;
import cytoscape.task.Task;
import cytoscape.task.ui.JTaskConfig;
import cytoscape.task.util.TaskManager;
import cytoscape.view.CyNetworkView;
import giny.model.GraphPerspective;
import java.util.Random;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import org.apache.commons.lang.ArrayUtils;

/**
 *
 * @author eb4
 */
public class failurePointsControlPanel extends javax.swing.JPanel {

    /**
     * Creates new form failurePointsControlPanel
     */
    public failurePointsControlPanel() {
        initComponents();
    }

    public JLabel getNOfFailureNodesLabel() {
        return nOfFailureNodesLabel;
    }

    public void updateUI(CyNetwork network){
        int allNodes[] = network.getNodeIndicesArray();
        maxNumberOfNodesToRemoveCombo.removeAllItems();
        for (int i = 1; i < allNodes.length; i++) {
            maxNumberOfNodesToRemoveCombo.addItem(i);
        }

    }
    
    public void enableUI() {
        maxNumberOfNodesToRemoveCombo.setEnabled(true);
        nOfIterationsCombo.setEnabled(true);
        testNodeButton.setEnabled(true);
        highlightFailureNodesButton.setEnabled(true);
        startSimulationButton.setEnabled(true);
        simulationStatsTable.setEnabled(true);
        jLabel5.setEnabled(true);
        jLabel6.setEnabled(true);
        jLabel1.setEnabled(true);
        jLabel2.setEnabled(true);
        jLabel3.setEnabled(true);
    }
    
    public void disableUI() {
        maxNumberOfNodesToRemoveCombo.setEnabled(false);
        nOfIterationsCombo.setEnabled(false);
        testNodeButton.setEnabled(false);
        highlightFailureNodesButton.setEnabled(false);
        startSimulationButton.setEnabled(false);
        simulationStatsTable.setEnabled(false);
        jLabel5.setEnabled(false);
        jLabel6.setEnabled(false);
        jLabel1.setEnabled(false);
        jLabel2.setEnabled(false);
        jLabel3.setEnabled(false);
    }
    
    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT
     * modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        testNodeButton = new javax.swing.JButton();
        highlightFailureNodesButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        nOfFailureNodesLabel = new javax.swing.JLabel();
        averageConnDegreeLabel = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        selectedNodeTestLabel = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        maxNumberOfNodesToRemoveCombo = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        nOfIterationsCombo = new javax.swing.JComboBox();
        startSimulationButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        simulationStatsTable = new javax.swing.JTable();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Basic operations"));

        testNodeButton.setText("Test selected node");
        testNodeButton.setEnabled(false);
        testNodeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                testNodeButtonActionPerformed(evt);
            }
        });

        highlightFailureNodesButton.setText("Highlight all failure nodes");
        highlightFailureNodesButton.setEnabled(false);
        highlightFailureNodesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                highlightFailureNodesButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("Number of failure nodes:");
        jLabel1.setEnabled(false);

        jLabel2.setText("Average degree of connectivity:");
        jLabel2.setEnabled(false);

        nOfFailureNodesLabel.setText("    ");

        averageConnDegreeLabel.setText("    ");

        jLabel3.setText("Selected node is:");
        jLabel3.setEnabled(false);

        selectedNodeTestLabel.setText("    ");

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(testNodeButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
                    .add(highlightFailureNodesButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(10, 10, 10)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(jLabel1)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                                .add(nOfFailureNodesLabel))
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(jLabel2)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                                .add(averageConnDegreeLabel))
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(jLabel3)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                                .add(selectedNodeTestLabel)))
                        .add(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(testNodeButton)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel3)
                    .add(selectedNodeTestLabel))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(highlightFailureNodesButton)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(nOfFailureNodesLabel))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel2)
                    .add(averageConnDegreeLabel))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Nodes removal simulation"));

        maxNumberOfNodesToRemoveCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
        maxNumberOfNodesToRemoveCombo.setEnabled(false);

        jLabel5.setText("Max number of nodes to remove:");
        jLabel5.setEnabled(false);

        jLabel6.setText("Number of iterations:");
        jLabel6.setEnabled(false);

        nOfIterationsCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25" }));
        nOfIterationsCombo.setEnabled(false);

        startSimulationButton.setText("Start simulation");
        startSimulationButton.setEnabled(false);
        startSimulationButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startSimulationButtonActionPerformed(evt);
            }
        });

        simulationStatsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nodes removed", "Avg. failure points"
            }
        ));
        simulationStatsTable.setEnabled(false);
        jScrollPane1.setViewportView(simulationStatsTable);

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(startSimulationButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel5)
                            .add(jLabel6))
                        .add(18, 18, 18)
                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(maxNumberOfNodesToRemoveCombo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(nOfIterationsCombo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(0, 54, Short.MAX_VALUE))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(maxNumberOfNodesToRemoveCombo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel5))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel6)
                    .add(nOfIterationsCombo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(startSimulationButton)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void startSimulationButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startSimulationButtonActionPerformed
        Task task = new SimulateNodesRemovalTask(
                Cytoscape.getCurrentNetwork(), 
                Cytoscape.getCurrentNetworkView(), 
                maxNumberOfNodesToRemoveCombo.getSelectedIndex() + 1,
                nOfIterationsCombo.getSelectedIndex() + 1,
                simulationStatsTable
        );

        //  Configure JTask
        JTaskConfig config = new JTaskConfig();
        config.displayCancelButton(true);
        config.displayStatus(true);
        boolean success = TaskManager.executeTask(task, config);        // TODO add your handling code here:
    }//GEN-LAST:event_startSimulationButtonActionPerformed

    private void highlightFailureNodesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_highlightFailureNodesButtonActionPerformed
        Task task = new HighlightFailurePointsTask(Cytoscape.getCurrentNetwork(), Cytoscape.getCurrentNetworkView(), nOfFailureNodesLabel, averageConnDegreeLabel);

        //  Configure JTask
        JTaskConfig config = new JTaskConfig();
        config.displayCancelButton(true);
        config.displayStatus(true);
        boolean success = TaskManager.executeTask(task, config);        // TODO add your handling code here:
    }//GEN-LAST:event_highlightFailureNodesButtonActionPerformed

    private void testNodeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_testNodeButtonActionPerformed
        CyNetworkView view = Cytoscape.getCurrentNetworkView();
        GraphPerspective graph = (GraphPerspective) Cytoscape.getCurrentNetwork().clone();

        if (view.getSelectedNodeIndices().length != 1) {
            JOptionPane.showMessageDialog(view.getComponent(), "Please select 1 node");
        } else {
            int selectedNodes[] = view.getSelectedNodeIndices();
            int nodeId = selectedNodes[0];
            if (core.isFailurePoint(nodeId, graph)) {
                selectedNodeTestLabel.setText("failure");
            } else {
                selectedNodeTestLabel.setText("not failure");
            }
        }        
    }//GEN-LAST:event_testNodeButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel averageConnDegreeLabel;
    private javax.swing.JButton highlightFailureNodesButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox maxNumberOfNodesToRemoveCombo;
    private javax.swing.JLabel nOfFailureNodesLabel;
    private javax.swing.JComboBox nOfIterationsCombo;
    private javax.swing.JLabel selectedNodeTestLabel;
    private javax.swing.JTable simulationStatsTable;
    private javax.swing.JButton startSimulationButton;
    private javax.swing.JButton testNodeButton;
    // End of variables declaration//GEN-END:variables
}
