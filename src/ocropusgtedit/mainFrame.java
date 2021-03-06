/*
 * Released under Attribution-ShareAlike 4.0 International (CC BY-SA 4.0)
 * http://creativecommons.org/licenses/by-sa/4.0/
 * 
 */

package ocropusgtedit;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import org.apache.commons.io.IOCase;
import org.apache.commons.io.filefilter.SuffixFileFilter;
import org.imgscalr.Scalr;



/**
 *
 * @author stexandev
 */
public class mainFrame extends javax.swing.JFrame {
    private BufferedImage img;
    File[] txtFilesArray;
    File[] imgFilesArray;
    int globalCounter;

 
    
    /**
     * Creates new form mainFrame
     */
    public mainFrame() {
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

        jFileChooser1 = new javax.swing.JFileChooser();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        GTLabel = new javax.swing.JLabel();
        GTTextField = new javax.swing.JTextField();
        GTWorkDirField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("OpenDirectory");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("SaveText");

        GTLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        GTLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        GTTextField.setFont(new java.awt.Font("DejaVu Serif", 0, 36)); // NOI18N
        GTTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GTTextFieldActionPerformed(evt);
            }
        });
        GTTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                GTTextFieldKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(GTLabel)
                    .addComponent(GTTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(963, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(GTLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(GTTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(431, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel1);

        GTWorkDirField.setText("/home/stefan/progs/ocropus/ocropy/mub/train/0001/");
        GTWorkDirField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GTWorkDirFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(GTWorkDirField, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 580, Short.MAX_VALUE)
                .addComponent(jButton1))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(GTWorkDirField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(49, 49, 49)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 483, Short.MAX_VALUE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        /** INITIALIZAZION of GT-Editing process
         *  1. Direcotory files to img and txt Arrays
         *  2. Loading of first set of img/txt
         */
        globalCounter=0;
        GTTextField.setText("");
        try {
                     
            
                        
            String workDIR=GTWorkDirField.getText();
            //System.out.println(workDIR);
            
            File f = new File(workDIR);
            //File[] fileArray = f.listFiles();
            txtFilesArray = f.listFiles((FileFilter) new SuffixFileFilter(".txt", IOCase.INSENSITIVE));
            imgFilesArray = f.listFiles((FileFilter) new SuffixFileFilter(".png", IOCase.INSENSITIVE));
            Arrays.sort(txtFilesArray);
            Arrays.sort(imgFilesArray);
            
            img = ImageIO.read(imgFilesArray[globalCounter]);
            Icon ico = new ImageIcon(Scalr.resize(img, Scalr.Method.SPEED, Scalr.Mode.FIT_TO_HEIGHT, 50, Scalr.OP_ANTIALIAS));
            GTLabel.setIcon(ico);
            
                      
            
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(txtFilesArray[globalCounter]), "UTF8"));
            String str;
 
		while ((str = in.readLine()) != null) {
		    System.out.println(str);
                    GTTextField.setText(GTTextField.getText()+str);
		}
 
                in.close();
            
            
          
            
        } catch (IOException ex) {
            Logger.getLogger(mainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
	    
      
      
          
    }//GEN-LAST:event_jButton1ActionPerformed

    private void GTTextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_GTTextFieldKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode()==KeyEvent.VK_ENTER){
            try {
                // TODO: save
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(txtFilesArray[globalCounter]), "UTF8"));
                out.write(GTTextField.getText());
                out.close();
                
                // load new img+txt
                globalCounter++;
                img = ImageIO.read(imgFilesArray[globalCounter]);
                Icon ico = new ImageIcon(Scalr.resize(img, Scalr.Method.SPEED, Scalr.Mode.FIT_TO_HEIGHT, 50, Scalr.OP_ANTIALIAS));
                GTLabel.setIcon(ico);
 
                GTTextField.setText("");
                BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(txtFilesArray[globalCounter]), "UTF8"));
                String str;
 		while ((str = in.readLine()) != null) {
                   GTTextField.setText(GTTextField.getText()+str);
		}
                in.close();
                
            } catch (IOException ex) {
                Logger.getLogger(mainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_GTTextFieldKeyPressed

    private void GTTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GTTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_GTTextFieldActionPerformed

    private void GTWorkDirFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GTWorkDirFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_GTWorkDirFieldActionPerformed

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
            java.util.logging.Logger.getLogger(mainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new mainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel GTLabel;
    private javax.swing.JTextField GTTextField;
    private javax.swing.JTextField GTWorkDirField;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

}
