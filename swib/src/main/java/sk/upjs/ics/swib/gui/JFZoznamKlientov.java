/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.upjs.ics.swib.gui;

import sk.upjs.ics.swib.entity.Klient;

/**
 *
 * @author Johnny
 */
public class JFZoznamKlientov extends javax.swing.JFrame {
    
    private final KlientTableModel zoznamKlientovModel = new KlientTableModel();
    private Klient vybranyKlient;

    /**
     * Creates new form JFListOfUsers
     */
    public JFZoznamKlientov() {        
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

        lblHladaj = new javax.swing.JTextField();
        btnZobraz = new javax.swing.JToggleButton();
        btnUprav = new javax.swing.JToggleButton();
        btnVymaz = new javax.swing.JToggleButton();
        btnPocitajUver = new javax.swing.JToggleButton();
        btnSpravujUvery = new javax.swing.JToggleButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTZoznamKlientov = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnZobraz.setText("Zobraz");
        btnZobraz.setEnabled(false);
        btnZobraz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnZobrazActionPerformed(evt);
            }
        });

        btnUprav.setText("Uprav");
        btnUprav.setEnabled(false);

        btnVymaz.setText("Vymaž");
        btnVymaz.setEnabled(false);

        btnPocitajUver.setText("Počítaj Úver");

        btnSpravujUvery.setText("Spravuj úvery");

        jTZoznamKlientov.setModel(zoznamKlientovModel);
        jTZoznamKlientov.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTZoznamKlientovMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTZoznamKlientov);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblHladaj, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnZobraz)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUprav)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnVymaz)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPocitajUver)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSpravujUvery))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblHladaj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnZobraz)
                    .addComponent(btnUprav)
                    .addComponent(btnVymaz)
                    .addComponent(btnPocitajUver))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSpravujUvery)
                .addGap(5, 5, 5))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTZoznamKlientovMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTZoznamKlientovMouseClicked
        int riadok = jTZoznamKlientov.getSelectedRow();
        int index = jTZoznamKlientov.convertRowIndexToModel(riadok);
        vybranyKlient = zoznamKlientovModel.getKlient(index);
        
        this.btnUprav.setEnabled(true);
        this.btnVymaz.setEnabled(true);
        this.btnZobraz.setEnabled(true);
            
        if (evt.getClickCount() == 2) {            
            zobrazKlientInfo();
        }
    }//GEN-LAST:event_jTZoznamKlientovMouseClicked

    private void zobrazKlientInfo() {
        JDKlientInfo klientInfo = new JDKlientInfo(this, vybranyKlient);
        klientInfo.setVisible(true);
    }

    private void btnZobrazActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnZobrazActionPerformed
        zobrazKlientInfo();
    }//GEN-LAST:event_btnZobrazActionPerformed

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
            java.util.logging.Logger.getLogger(JFZoznamKlientov.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFZoznamKlientov.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFZoznamKlientov.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFZoznamKlientov.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFZoznamKlientov().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btnPocitajUver;
    private javax.swing.JToggleButton btnSpravujUvery;
    private javax.swing.JToggleButton btnUprav;
    private javax.swing.JToggleButton btnVymaz;
    private javax.swing.JToggleButton btnZobraz;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTZoznamKlientov;
    private javax.swing.JTextField lblHladaj;
    // End of variables declaration//GEN-END:variables
}