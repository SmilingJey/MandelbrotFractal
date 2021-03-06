package mandelbrotfractal;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.ImageIcon;

public class MandelbrotFractal extends javax.swing.JFrame {

    public MandelbrotFractal() {
        initComponents();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ConfigPanel = new javax.swing.JPanel();
        jComboBoxColor = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jButtonSave = new javax.swing.JButton();
        jButtonZoomM = new javax.swing.JButton();
        jButtonZoomP = new javax.swing.JButton();
        jSpinnerIterCount = new javax.swing.JSpinner();
        jSpinnerIterCount.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                drawingPanel.setIterationsCount((Integer)jSpinnerIterCount.getValue());
                drawingPanel.drawMandelborFractal();
            }
        });
        jLabel2 = new javax.swing.JLabel();
        drawingPanel = new mandelbrotfractal.DrawingPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Mandelbrot set");
        setBackground(new java.awt.Color(255, 255, 255));
        setIconImages(null);

        ConfigPanel.setBackground(new java.awt.Color(255, 255, 255));
        ConfigPanel.setPreferredSize(new java.awt.Dimension(446, 50));

        jComboBoxColor.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4" }));
        jComboBoxColor.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                jComboBoxColorPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jLabel1.setText("Color:");

        jButtonSave.setText("Save image");
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });

        jButtonZoomM.setText("Zoom -");
        jButtonZoomM.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonZoomMMouseClicked(evt);
            }
        });

        jButtonZoomP.setText("Zoom +");
        jButtonZoomP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonZoomPMouseClicked(evt);
            }
        });

        jSpinnerIterCount.setModel(new javax.swing.SpinnerNumberModel(200, 0, 2000, 1));
        jSpinnerIterCount.setName(""); // NOI18N
        jSpinnerIterCount.setRequestFocusEnabled(false);

        jLabel2.setText("Iteration count:");

        javax.swing.GroupLayout ConfigPanelLayout = new javax.swing.GroupLayout(ConfigPanel);
        ConfigPanel.setLayout(ConfigPanelLayout);
        ConfigPanelLayout.setHorizontalGroup(
            ConfigPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ConfigPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSpinnerIterCount, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBoxColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                .addComponent(jButtonZoomM, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonZoomP, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        ConfigPanelLayout.setVerticalGroup(
            ConfigPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ConfigPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ConfigPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSave)
                    .addComponent(jButtonZoomP)
                    .addComponent(jLabel1)
                    .addComponent(jComboBoxColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSpinnerIterCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jButtonZoomM))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        getContentPane().add(ConfigPanel, java.awt.BorderLayout.PAGE_END);

        javax.swing.GroupLayout drawingPanelLayout = new javax.swing.GroupLayout(drawingPanel);
        drawingPanel.setLayout(drawingPanelLayout);
        drawingPanelLayout.setHorizontalGroup(
            drawingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 615, Short.MAX_VALUE)
        );
        drawingPanelLayout.setVerticalGroup(
            drawingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 391, Short.MAX_VALUE)
        );

        getContentPane().add(drawingPanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonZoomPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonZoomPMouseClicked
        drawingPanel.ZoomIn(-1, -1);
    }//GEN-LAST:event_jButtonZoomPMouseClicked

    private void jButtonZoomMMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonZoomMMouseClicked
        drawingPanel.ZoomOut(-1, -1);
    }//GEN-LAST:event_jButtonZoomMMouseClicked

    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
        drawingPanel.savaImage();
    }//GEN-LAST:event_jButtonSaveActionPerformed

    private void jComboBoxColorPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jComboBoxColorPopupMenuWillBecomeInvisible
        drawingPanel.setColor(jComboBoxColor.getSelectedIndex());
        drawingPanel.drawMandelborFractal();
    }//GEN-LAST:event_jComboBoxColorPopupMenuWillBecomeInvisible

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                MandelbrotFractal frame = new MandelbrotFractal();
                URL url = this.getClass().getResource("icon.png");
                ImageIcon image = new ImageIcon(url);
                frame.setIconImage(image.getImage());
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                Dimension frameSize = frame.getSize();
                frame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
                frame.setVisible(true);
                frame.drawingPanel.setCoordinats(-2, -1, 1);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ConfigPanel;
    public mandelbrotfractal.DrawingPanel drawingPanel;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JButton jButtonZoomM;
    private javax.swing.JButton jButtonZoomP;
    private javax.swing.JComboBox jComboBoxColor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JSpinner jSpinnerIterCount;
    // End of variables declaration//GEN-END:variables
}
