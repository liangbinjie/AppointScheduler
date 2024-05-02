package poo.view;

import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import poo.barberia.AppointScheduler;
import poo.barberia.Cita;
import poo.view.Citas.ConsultarCita;
import poo.view.Citas.CrearCita;
import poo.view.Citas.EliminarCita;
import poo.view.Citas.ModificarCita;

/**
 *
 * @author 123
 */
public class CitasPanel extends javax.swing.JPanel {
    
    JFrame parent;
    /**
     * Creates new form HomePanel
     * @param parent
     */
    public CitasPanel(JFrame parent) {
        initComponents();
        this.parent = parent;
        actualizarTabla();
    }

    public void actualizarTabla() {
        DefaultTableModel tablaClientes = (DefaultTableModel)tablaCitas.getModel();
        AppointScheduler as = AppointScheduler.getInstance();
        HashMap <String, Cita> listaCitas = as.obtenerListaCitas();
        
        tablaClientes.setRowCount(0);
        
        for (HashMap.Entry<String, Cita> entry : listaCitas.entrySet()) {
            Cita c = entry.getValue();
            Object[] fila = new Object[5];
            fila[0] = c.getCliente().getNombre();
            fila[1] = c.getCliente().getApellido();
            fila[2] = c.getDiaCita();
            fila[3] = c.getHoraCita();
            fila[4] = c.getEstadoCita();
            tablaClientes.addRow(fila);
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        btnPanel = new javax.swing.JPanel();
        searchBtn = new javax.swing.JButton();
        modifyBtn = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();
        createBtn = new javax.swing.JButton();
        controlMsg = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaCitas = new javax.swing.JTable();

        setPreferredSize(new java.awt.Dimension(620, 550));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(620, 550));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 108, 103));
        jLabel1.setText("Administracion de Citas");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 18, -1, -1));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 45, 608, 10));

        btnPanel.setBackground(new java.awt.Color(0, 108, 103));

        searchBtn.setToolTipText("Buscar Cita");
        searchBtn.setContentAreaFilled(false);
        searchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtnActionPerformed(evt);
            }
        });

        modifyBtn.setToolTipText("Modificar Cita");
        modifyBtn.setContentAreaFilled(false);
        modifyBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifyBtnActionPerformed(evt);
            }
        });

        deleteBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bin.png"))); // NOI18N
        deleteBtn.setToolTipText("Eliminar Cita");
        deleteBtn.setContentAreaFilled(false);
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });

        createBtn.setToolTipText("Agendar Cita");
        createBtn.setContentAreaFilled(false);
        createBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout btnPanelLayout = new javax.swing.GroupLayout(btnPanel);
        btnPanel.setLayout(btnPanelLayout);
        btnPanelLayout.setHorizontalGroup(
            btnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(btnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(searchBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(modifyBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(createBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        btnPanelLayout.setVerticalGroup(
            btnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(searchBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(createBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(modifyBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(181, Short.MAX_VALUE))
        );

        jPanel1.add(btnPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 100, -1, -1));

        controlMsg.setForeground(new java.awt.Color(255, 102, 51));
        controlMsg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(controlMsg, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 476, 550, -1));

        tablaCitas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Apellido", "Dia", "Hora", "Estado"
            }
        ));
        tablaCitas.setPreferredSize(new java.awt.Dimension(300, 0));
        jScrollPane1.setViewportView(tablaCitas);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 548, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void searchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtnActionPerformed
        // TODO add your handling code here:
        ConsultarCita ventana = new ConsultarCita(this, parent);
        ventana.setVisible(true);
    }//GEN-LAST:event_searchBtnActionPerformed

    private void createBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createBtnActionPerformed
        // TODO add your handling code here:
        CrearCita ventana = new CrearCita(this, parent);
        ventana.setVisible(true);
    }//GEN-LAST:event_createBtnActionPerformed

    private void modifyBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modifyBtnActionPerformed
        // TODO add your handling code here:
        ModificarCita ventana = new ModificarCita(this, parent);
        ventana.setVisible(true);
    }//GEN-LAST:event_modifyBtnActionPerformed

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        // TODO add your handling code here:
        EliminarCita ventana = new EliminarCita(this, parent);
        ventana.setVisible(true);
    }//GEN-LAST:event_deleteBtnActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel btnPanel;
    private javax.swing.JLabel controlMsg;
    private javax.swing.JButton createBtn;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton modifyBtn;
    private javax.swing.JButton searchBtn;
    private javax.swing.JTable tablaCitas;
    // End of variables declaration//GEN-END:variables

    public Object getCitasPanel() {
         throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public DefaultTableModel getCitasTableModel() {
        return (DefaultTableModel)tablaCitas.getModel();
    }
}
