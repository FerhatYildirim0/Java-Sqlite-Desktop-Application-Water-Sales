package appPack;

import DB.DB;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Ferhat
 */
public class MusteriDuzenle extends javax.swing.JFrame {

    /**
     * Creates new form Edit
     */
    public MusteriDuzenle() {
        initComponents();
         this.setIconImage(new ImageIcon(getClass().getResource("/icons/Su_ikonu.png")).getImage());
    }

     int cid;
    public void customerId(int customerid){
        cid = customerid;
        
        
    }
    
    public MusteriDuzenle(String name,String surname,String number, String address){
        initComponents();
        e_txt_name.setText(name);
        e_txt_surname.setText(surname);
        e_txt_number.setText(number);
        e_txt_address.setText(address);
        
        
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        e_txt_name = new javax.swing.JTextField();
        e_txt_surname = new javax.swing.JTextField();
        e_txt_number = new javax.swing.JTextField();
        e_txt_address = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(200, 200));
        setMinimumSize(new java.awt.Dimension(470, 600));
        setResizable(false);
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Adı");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(110, 230, 46, 17);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("  Soyadı");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(100, 270, 60, 17);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText(" Telefon");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(101, 307, 70, 20);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText(" Adres");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(100, 350, 70, 17);
        getContentPane().add(e_txt_name);
        e_txt_name.setBounds(200, 220, 180, 30);
        getContentPane().add(e_txt_surname);
        e_txt_surname.setBounds(200, 260, 180, 30);
        getContentPane().add(e_txt_number);
        e_txt_number.setBounds(200, 300, 180, 30);
        getContentPane().add(e_txt_address);
        e_txt_address.setBounds(200, 340, 180, 30);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/MusteriDuzenleme.png"))); // NOI18N
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(290, 380, 90, 70);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/MusteriKayitArkaPlan.png"))); // NOI18N
        getContentPane().add(jLabel5);
        jLabel5.setBounds(90, -160, 450, 570);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(cid>0){
            String name = e_txt_name.getText().trim().toLowerCase(); //update edilen değerleri alınır
            String surname = e_txt_surname.getText().trim().toLowerCase();
            String number = e_txt_number.getText().trim();
            String address = e_txt_address.getText().trim();

            DB db = new DB();
            int status = db.customerEdit(name, surname, number, address, cid);
            db.close();

            if( status>0){

                System.out.println("Düzenleme başarılı");
                MainFrame m = new MainFrame();
                m.setVisible(true);
                dispose();

            }
            if(status == -1){
                JOptionPane.showMessageDialog(rootPane, "Bu telefon numarası zaten kayıtlı");
            }

        }

    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(MusteriDuzenle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MusteriDuzenle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MusteriDuzenle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MusteriDuzenle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MusteriDuzenle().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField e_txt_address;
    private javax.swing.JTextField e_txt_name;
    private javax.swing.JTextField e_txt_number;
    private javax.swing.JTextField e_txt_surname;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    // End of variables declaration//GEN-END:variables
}
