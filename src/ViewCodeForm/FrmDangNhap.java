package ViewCodeForm;

import Controller.DAO;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;


public class FrmDangNhap extends javax.swing.JFrame {
    
    private Connection conn=null;
    public static String tenNV = "";
    
 
    private void kn() {
        String url = "jdbc:sqlserver://LAPTOP-AP54ORLO\\SQLEXPRESS:1433;databasename=QuanLiQuanCaPhe;" + "username=sa;password=sa;encrypt=true;trustServerCertificate=true";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(url);
        } catch (Exception e) {
            System.out.println("Kết nối cơ sở dữ liệu thất bại");
        }
    }
    
    public FrmDangNhap() {
        initComponents();
        this.setTitle("Đăng nhập");
        this.setLocationRelativeTo(null);
        tfTendangnhap.requestFocus();
    }
    
    //Hàm kiểm tra nhập rỗng hay không vào dữ liệu
    private boolean kiemtrarong(){
        if(tfTendangnhap.getText().equals("")){
            lblTrangThai.setText("Vui lòng nhập tên đăng nhập!");
            return false;
        }
        else
        if(tfMatkhau.getText().equals("")){
            lblTrangThai.setText("Vui lòng nhập mật khẩu!");
            return false;
        }
        return true;
    }

    // INIT COMPONENT
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tfTendangnhap = new javax.swing.JTextField();
        btnDangnhap = new javax.swing.JButton();
        btnThoat = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        tfMatkhau = new javax.swing.JPasswordField();
        lblTrangThai = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        jLabel1.setText("Tên đăng nhập");

        jLabel2.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        jLabel2.setText("Mật khẩu");

        btnDangnhap.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnDangnhap.setText("Đăng nhập");
        btnDangnhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangnhapActionPerformed(evt);
            }
        });

        btnThoat.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnThoat.setText("Thoát");
        btnThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("HỆ THỐNG QUẢN LÍ QUÁN CÀ PHÊ");

        tfMatkhau.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tfMatkhauKeyPressed(evt);
            }
        });

        lblTrangThai.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        lblTrangThai.setText("    ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(btnDangnhap)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThoat, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55))
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tfTendangnhap)
                    .addComponent(tfMatkhau, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblTrangThai)
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfTendangnhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tfMatkhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDangnhap)
                    .addComponent(btnThoat))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    // Sự kiện nút thoát
    private void btnThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatActionPerformed
        int click=JOptionPane.showConfirmDialog(null, "Bạn có muốn thoát chương trình hay không?", "Thông báo", 2);
        if(click==JOptionPane.YES_OPTION){
            System.exit(0);
        }
    }//GEN-LAST:event_btnThoatActionPerformed

    private void btnDangnhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangnhapActionPerformed
        if(kiemtrarong())
        try {
            kn();
            String sql="SELECT * FROM NhanVien";
            Statement ps = conn.createStatement();
            ResultSet rs = ps.executeQuery(sql);
            
            while(rs.next()){
                if(rs.getString("NVTenDangNhap").trim().equals(tfTendangnhap.getText())&& rs.getString("NVMatKhau").trim().equals(tfMatkhau.getText())){
                    tenNV = rs.getString("NVHoTen");
                    FrmGiaoDienChinh home = new FrmGiaoDienChinh();
                    this.setVisible(false);
                    home.setVisible(true);
                }
                else lblTrangThai.setText("Bạn đã nhập sai tài khoản hoặc mật khẩu!");
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }//GEN-LAST:event_btnDangnhapActionPerformed

    private void tfMatkhauKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfMatkhauKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            btnDangnhap.doClick();
        }
    }//GEN-LAST:event_tfMatkhauKeyPressed

    // Sự kiện nút đăng nhập
    
    // Hàm main
    public static void main(String args[]) {
            
            java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmDangNhap().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDangnhap;
    private javax.swing.JButton btnThoat;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel lblTrangThai;
    private javax.swing.JPasswordField tfMatkhau;
    private javax.swing.JTextField tfTendangnhap;
    // End of variables declaration//GEN-END:variables
}
