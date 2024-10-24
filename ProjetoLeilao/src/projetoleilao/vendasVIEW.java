/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projetoleilao;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class vendasVIEW extends javax.swing.JFrame {

    public vendasVIEW() {
        initComponents();
        listarProdutosVendidos();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaVendas = new javax.swing.JTable();
        btnVoltar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tabelaVendas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Nome", "Valor", "Status"
            }
        ));
        jScrollPane1.setViewportView(tabelaVendas);

        btnVoltar.setText("Voltar");
        btnVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltarActionPerformed(evt);
            }
        });

        // Layout da tela
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVoltar))
                .addGap(0, 25, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnVoltar)
                .addGap(0, 20, Short.MAX_VALUE))
        );

        pack();
    }

    private void btnVoltarActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();  // Fecha a tela de Vendas e retorna à tela anterior
    }

    private void listarProdutosVendidos() {
        try {
            ProdutosDAO produtosDAO = new ProdutosDAO();
            DefaultTableModel model = (DefaultTableModel) tabelaVendas.getModel();
            model.setNumRows(0);
            
            ArrayList<ProdutosDTO> listagem = produtosDAO.listarProdutosVendidos();
            
            for (int i = 0; i < listagem.size(); i++) {
                model.addRow(new Object[] {
                    listagem.get(i).getId(),
                    listagem.get(i).getNome(),
                    listagem.get(i).getValor(),
                    listagem.get(i).getStatus()
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Variables declaration
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelaVendas;
    private javax.swing.JButton btnVoltar;
}
