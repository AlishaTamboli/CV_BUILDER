
import java.awt.Color;
import java.awt.Font;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author MEET
 */
public class Admin_Recruiter extends javax.swing.JFrame {

    private Connection conn;
    private Statement stmt;
    private ResultSet rs;
    private ResultSetMetaData rsmd;

    public Admin_Recruiter() {
        initComponents();
        this.setLocationRelativeTo(null);
        jPanel1.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel9 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        searchButton = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        detailTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1420, 799));
        setResizable(false);
        getContentPane().setLayout(null);

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/left-arrow (1).png"))); // NOI18N
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel9);
        jLabel9.setBounds(0, 0, 32, 32);

        jLabel2.setFont(new java.awt.Font("Calibri", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("Search Job seeker");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(720, 90, 290, 40);

        jTextField1.setFont(new java.awt.Font("Calibri", 0, 36)); // NOI18N
        jTextField1.setBorder(null);
        jTextField1.setOpaque(false);
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
        });
        getContentPane().add(jTextField1);
        jTextField1.setBounds(790, 150, 340, 30);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        getContentPane().add(jLabel3);
        jLabel3.setBounds(720, 140, 440, 50);

        searchButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/job-search(4).png"))); // NOI18N
        searchButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchButtonMouseClicked(evt);
            }
        });
        getContentPane().add(searchButton);
        searchButton.setBounds(740, 140, 40, 50);

        jPanel1.setOpaque(false);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        detailTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "JOBSeeker", "Contact"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        detailTable.setFillsViewportHeight(true);
        detailTable.setOpaque(false);
        jScrollPane2.setViewportView(detailTable);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, 420, 160));

        getContentPane().add(jPanel1);
        jPanel1.setBounds(700, 330, 510, 210);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Admin_recruiter.png"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 1420, 799);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void searchButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchButtonMouseClicked

        //DefaultTableModel model = (DefaultTableModel) detailTable.getModel();
        DefaultTableModel model = new DefaultTableModel();
        String[] columns = {"Job Seeker ", " Contact "};
        model.setColumnIdentifiers(columns);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        
        detailTable.setFillsViewportHeight( true );
        detailTable.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
        detailTable.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
        detailTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 20));
       
        detailTable.setRowHeight(33);
        detailTable.setBackground(Color.LIGHT_GRAY);
        TableColumnModel colModel = detailTable.getColumnModel();
        colModel.getColumn(0).setPreferredWidth(10);
        colModel.getColumn(1).setPreferredWidth(40);

        try {
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cv_resume_builder", "root", "mayank");

            stmt = conn.createStatement();

            rs = stmt.executeQuery("SELECT `UserName`, Email FROM register, skills WHERE register.UserId = skills.UserId AND skills.Skill_Name = '" + jTextField1.getText() + "';");

            jPanel1.setVisible(true);
            boolean flag =false;
            while (rs.next()) {
                model.addRow(new Object[]{rs.getString("UserName"), rs.getString("Email")});
                flag=true;
            }
            detailTable.setModel(model);
            if (flag == false) {
                jPanel1.setVisible(false);
                JOptionPane.showMessageDialog(this, "No matches found !", "INFORMATION", JOptionPane.INFORMATION_MESSAGE);

            } 

        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

    }//GEN-LAST:event_searchButtonMouseClicked

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        
        
    }//GEN-LAST:event_jTextField1KeyPressed

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        // TODO add your handling code here:
        this.setVisible(false);
        Login_register_screen_new l=new Login_register_screen_new();
        this.setVisible(false);
        l.setVisible(true);
    }//GEN-LAST:event_jLabel9MouseClicked

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Admin_Recruiter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Admin_Recruiter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Admin_Recruiter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Admin_Recruiter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Admin_Recruiter().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable detailTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel searchButton;
    // End of variables declaration//GEN-END:variables
}
