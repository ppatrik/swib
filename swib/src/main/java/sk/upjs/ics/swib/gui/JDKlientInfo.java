/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.upjs.ics.swib.gui;

import sk.upjs.ics.swib.entity.Klient;

/**
 *
 * @author Uživateľ
 */
public class JDKlientInfo extends javax.swing.JDialog {
    private Klient klient;
    private UctyTableModel uctyTableModel;

    /**
     * Creates new form JDKlientInfo
     */
    JDKlientInfo(java.awt.Frame parent, boolean modal) {
        super(parent, modal);        
    }

    JDKlientInfo(java.awt.Frame parent, Klient klient) {
        this(parent, true);
        this.klient = klient;
        uctyTableModel = new UctyTableModel(klient);
        setTitle(klient.getMeno()+" "+klient.getPriezvisko());
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

        lblMeno = new javax.swing.JLabel();
        lblMenoKlienta = new javax.swing.JLabel();
        lblPriezvisko = new javax.swing.JLabel();
        lblPriezviskoKlienta = new javax.swing.JLabel();
        lblCisloPreukazu = new javax.swing.JLabel();
        lblCisloPreukazuKlienta = new javax.swing.JLabel();
        btnOK = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTUctyKlienta = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblMeno.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblMeno.setText("Meno:");

        lblMenoKlienta.setText(klient.getMeno());

        lblPriezvisko.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblPriezvisko.setText("Priezvisko: ");
        lblPriezvisko.setToolTipText("");

        lblPriezviskoKlienta.setText(klient.getPriezvisko());

        lblCisloPreukazu.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblCisloPreukazu.setText("Číslo preukazu: ");

        lblCisloPreukazuKlienta.setText(klient.getCisloPreukazu());

        btnOK.setText("OK");
        btnOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOKActionPerformed(evt);
            }
        });

        jTUctyKlienta.setModel(uctyTableModel);
        jTUctyKlienta.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(jTUctyKlienta);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnOK))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblMeno)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblMenoKlienta))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblPriezvisko)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblPriezviskoKlienta))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblCisloPreukazu)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblCisloPreukazuKlienta)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMeno)
                    .addComponent(lblMenoKlienta))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPriezvisko)
                    .addComponent(lblPriezviskoKlienta))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCisloPreukazu)
                    .addComponent(lblCisloPreukazuKlienta))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnOK)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOKActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnOKActionPerformed

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
            java.util.logging.Logger.getLogger(JDKlientInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDKlientInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDKlientInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDKlientInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JDKlientInfo dialog = new JDKlientInfo(new javax.swing.JFrame(), true);
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTUctyKlienta;
    private javax.swing.JLabel lblCisloPreukazu;
    private javax.swing.JLabel lblCisloPreukazuKlienta;
    private javax.swing.JLabel lblMeno;
    private javax.swing.JLabel lblMenoKlienta;
    private javax.swing.JLabel lblPriezvisko;
    private javax.swing.JLabel lblPriezviskoKlienta;
    // End of variables declaration//GEN-END:variables
}
