package sk.upjs.ics.swib.gui;

import java.awt.Frame;
import javax.swing.JFrame;
import sk.upjs.ics.swib.entity.Bonus;
import sk.upjs.ics.swib.entity.Uver;

/**
 *
 * @author Johnny
 */
public class JDSpravujUvery extends javax.swing.JDialog {

    private final UverComboBoxModel uverComboBoxModel = new UverComboBoxModel();
    private BonusListModel bonusListModel = null;
    private Uver vybranyUver;
    private Bonus vybranyBonus;
    private final java.awt.Frame myParent;

    /**
     * Creates new form JDSpravujUvery
     *
     * @param parent
     * @param modal
     */
    public JDSpravujUvery(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        this.myParent = parent;
        setTitle("Spravuj úvery");
        initComponents();
        jcombUvery.setModel(uverComboBoxModel);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor. Oink! Oink!
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jcombUvery = new javax.swing.JComboBox();
        btnOK = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jlZoznamUverov = new javax.swing.JList();
        btnPridajPodmienku = new javax.swing.JButton();
        btnUpravPodmienku = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jcombUvery.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jcombUvery.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcombUveryActionPerformed(evt);
            }
        });

        btnOK.setText("OK");
        btnOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOKActionPerformed(evt);
            }
        });

        jlZoznamUverov.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jlZoznamUverov.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlZoznamUverovMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jlZoznamUverov);

        btnPridajPodmienku.setText("Pridaj podmienku");
        btnPridajPodmienku.setEnabled(false);
        btnPridajPodmienku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPridajPodmienkuActionPerformed(evt);
            }
        });

        btnUpravPodmienku.setText("Uprav Podmienku");
        btnUpravPodmienku.setEnabled(false);
        btnUpravPodmienku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpravPodmienkuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jcombUvery, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnUpravPodmienku, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
                            .addComponent(btnPridajPodmienku, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnOK)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jcombUvery, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnPridajPodmienku)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUpravPodmienku)))
                .addGap(19, 19, 19)
                .addComponent(btnOK)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOKActionPerformed
        dispose();
    }//GEN-LAST:event_btnOKActionPerformed

    private void jcombUveryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcombUveryActionPerformed
        int index = jcombUvery.getSelectedIndex();
        vybranyUver = uverComboBoxModel.getUver(index);
        bonusListModel = new BonusListModel(vybranyUver);
        jlZoznamUverov.setModel(bonusListModel);
        btnPridajPodmienku.setEnabled(true);        
    }//GEN-LAST:event_jcombUveryActionPerformed

    private void jlZoznamUverovMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlZoznamUverovMouseClicked
        int index = jlZoznamUverov.getSelectedIndex();
        vybranyBonus = bonusListModel.getBonus(index);
        btnUpravPodmienku.setEnabled(true);        
    }//GEN-LAST:event_jlZoznamUverovMouseClicked

    private void btnPridajPodmienkuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPridajPodmienkuActionPerformed
        JDPodmineka jDPodmineka = new JDPodmineka(myParent, vybranyUver);
        jDPodmineka.setVisible(true);
    }//GEN-LAST:event_btnPridajPodmienkuActionPerformed

    private void btnUpravPodmienkuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpravPodmienkuActionPerformed
        JDPodmineka jDPodmineka = new JDPodmineka(myParent, vybranyUver, vybranyBonus);
        jDPodmineka.setVisible(true);
    }//GEN-LAST:event_btnUpravPodmienkuActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JDSpravujUvery.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDSpravujUvery.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDSpravujUvery.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDSpravujUvery.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JDSpravujUvery dialog = new JDSpravujUvery(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnOK;
    private javax.swing.JButton btnPridajPodmienku;
    private javax.swing.JButton btnUpravPodmienku;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JComboBox jcombUvery;
    private javax.swing.JList jlZoznamUverov;
    // End of variables declaration//GEN-END:variables
}