package poo.view;

import com.formdev.flatlaf.FlatLightLaf;
import java.awt.BorderLayout;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import poo.barberia.AppointScheduler;
import poo.view.CitasPanel;
import poo.view.ClientesPanel;

public class Dashboard extends javax.swing.JFrame {
    /**
     * Creates new form Dashboard
     */
    public Dashboard() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("Barberia POO");
        this.setResizable(false);
        this.setIconImage(new ImageIcon("src/main/resources/logo.png").getImage());
    }
    
    public void showPanel(JPanel p) {
        p.setSize(620,550);
        p.setLocation(0,0);
        
        contentPanel.removeAll();
        contentPanel.add(p, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JPanel();
        sidePanel = new javax.swing.JPanel();
        clienteBtn = new javax.swing.JButton();
        citaBtn = new javax.swing.JButton();
        colaBtn = new javax.swing.JButton();
        adminBtn = new javax.swing.JButton();
        logo = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        serviceBtn = new javax.swing.JButton();
        contentPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        bg.setBackground(new java.awt.Color(255, 255, 255));
        bg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        sidePanel.setBackground(new java.awt.Color(0, 56, 68));

        clienteBtn.setBackground(new java.awt.Color(0, 108, 103));
        clienteBtn.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12)); // NOI18N
        clienteBtn.setForeground(new java.awt.Color(255, 235, 198));
        clienteBtn.setText("Clientes");
        clienteBtn.setBorder(null);
        clienteBtn.setBorderPainted(false);
        clienteBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        clienteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clienteBtnActionPerformed(evt);
            }
        });

        citaBtn.setBackground(new java.awt.Color(0, 108, 103));
        citaBtn.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12)); // NOI18N
        citaBtn.setForeground(new java.awt.Color(255, 235, 198));
        citaBtn.setText("Citas");
        citaBtn.setBorder(null);
        citaBtn.setBorderPainted(false);
        citaBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        citaBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                citaBtnActionPerformed(evt);
            }
        });

        colaBtn.setBackground(new java.awt.Color(0, 108, 103));
        colaBtn.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12)); // NOI18N
        colaBtn.setForeground(new java.awt.Color(255, 235, 198));
        colaBtn.setText("Lista Espera");
        colaBtn.setBorder(null);
        colaBtn.setBorderPainted(false);
        colaBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        colaBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                colaBtnActionPerformed(evt);
            }
        });

        adminBtn.setBackground(new java.awt.Color(0, 108, 103));
        adminBtn.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12)); // NOI18N
        adminBtn.setForeground(new java.awt.Color(255, 235, 198));
        adminBtn.setText("Administracion");
        adminBtn.setBorder(null);
        adminBtn.setBorderPainted(false);
        adminBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        adminBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminBtnActionPerformed(evt);
            }
        });

        logo.setIcon(new javax.swing.ImageIcon("C:\\Users\\123\\Documents\\Codes\\AppointScheduler\\Barberia\\src\\main\\resources\\logo.png")); // NOI18N

        jLabel1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 235, 198));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("BARBERIA POO");

        serviceBtn.setBackground(new java.awt.Color(0, 108, 103));
        serviceBtn.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12)); // NOI18N
        serviceBtn.setForeground(new java.awt.Color(255, 235, 198));
        serviceBtn.setText("Servicios");
        serviceBtn.setBorder(null);
        serviceBtn.setBorderPainted(false);
        serviceBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        serviceBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                serviceBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout sidePanelLayout = new javax.swing.GroupLayout(sidePanel);
        sidePanel.setLayout(sidePanelLayout);
        sidePanelLayout.setHorizontalGroup(
            sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(clienteBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(citaBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(colaBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(adminBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sidePanelLayout.createSequentialGroup()
                .addContainerGap(47, Short.MAX_VALUE)
                .addGroup(sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(logo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(45, 45, 45))
            .addComponent(serviceBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        sidePanelLayout.setVerticalGroup(
            sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sidePanelLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(logo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(clienteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(citaBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(serviceBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(colaBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(adminBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(88, 88, 88))
        );

        bg.add(sidePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        contentPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        jLabel2.setText("DASHBOARD BARBERIA POO");

        javax.swing.GroupLayout contentPanelLayout = new javax.swing.GroupLayout(contentPanel);
        contentPanel.setLayout(contentPanelLayout);
        contentPanelLayout.setHorizontalGroup(
            contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, contentPanelLayout.createSequentialGroup()
                .addContainerGap(211, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(196, 196, 196))
        );
        contentPanelLayout.setVerticalGroup(
            contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentPanelLayout.createSequentialGroup()
                .addGap(251, 251, 251)
                .addComponent(jLabel2)
                .addContainerGap(278, Short.MAX_VALUE))
        );

        bg.add(contentPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 0, 620, 550));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.PREFERRED_SIZE, 554, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void clienteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clienteBtnActionPerformed
        showPanel(new ClientesPanel(this));
    }//GEN-LAST:event_clienteBtnActionPerformed

    private void citaBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_citaBtnActionPerformed
        showPanel(new CitasPanel(this));
    }//GEN-LAST:event_citaBtnActionPerformed

    private void serviceBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_serviceBtnActionPerformed
        showPanel(new ServiciosPanel(this));
    }//GEN-LAST:event_serviceBtnActionPerformed

    private void adminBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminBtnActionPerformed
        showPanel(new AdminPanel(this));
    }//GEN-LAST:event_adminBtnActionPerformed

    private void colaBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_colaBtnActionPerformed
        showPanel(new ListaEspera(this));
    }//GEN-LAST:event_colaBtnActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        AppointScheduler c = AppointScheduler.getInstance();
        try {
            AppointScheduler.guardarDatos(c);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }//GEN-LAST:event_formWindowClosing

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        FlatLightLaf.setup();
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Dashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton adminBtn;
    private javax.swing.JPanel bg;
    private javax.swing.JButton citaBtn;
    private javax.swing.JButton clienteBtn;
    private javax.swing.JButton colaBtn;
    private javax.swing.JPanel contentPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel logo;
    private javax.swing.JButton serviceBtn;
    private javax.swing.JPanel sidePanel;
    // End of variables declaration//GEN-END:variables
}
