package ViewCodeForm;

// Khai báo
import java.sql.Connection;
import java.sql.ResultSet;
import Controller.DAO;
import Model.ClsMenu;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class FrmMenu extends javax.swing.JFrame {

    private Connection conn = null;
    private ResultSet rs = null;
    PreparedStatement ps = null;
    private boolean them = false, thayDoi = false;
    private DefaultTableModel model;
    String sql = "SELECT * FROM MENU";
    

    

    // Hàm kết nối cơ sở dữ liệu
    private void kn() {
        String url = "jdbc:sqlserver://LAPTOP-AP54ORLO\\SQLEXPRESS:1433;databasename=QuanLiQuanCaPhe;" 
            + "username=sa;password=sa;encrypt=true;trustServerCertificate=true";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(url);
        } catch (Exception e) {
            System.out.println("Kết nối cơ sở dữ liệu thất bại thất bại");
        }
    }
    
    // Hàm xây dựng mặc nhiên - khởi tạo giá trị ban đầu
    public FrmMenu() {
        initComponents();
        this.setTitle("Menu");
        this.setLocationRelativeTo(null);
        Xem(sql);
        Disabled();
        btnSua.setEnabled(false);
        btnXoa.setEnabled(false);
        btnLuu.setEnabled(false);
    }

    @SuppressWarnings("unchecked")
    // Hàm lấy danh sách loại sản phẩm trong menu
    private void Loai() {
        cbbLoai.removeAllItems();
        cbbLoai.addItem("Thức uống nóng");
        cbbLoai.addItem("Thức uống đá");
        cbbLoai.addItem("Nước có ga");
        cbbLoai.addItem("Nước đóng chai");
        cbbLoai.addItem("Sinh tố");
        cbbLoai.addItem("Đồ ăn");
        cbbLoai.addItem("Trà sữa");       
    }
    
    // Hàm lấy dữ liệu hiển thị lên bảng 
    private void Xem(String sql){
        try{
            kn();
            int i = 1;
            String[] arry={"STT","Mã sản phẩm","Loại sản phẩm","Tên sản phẩm","Đơn vị","Giá"};
            model=new DefaultTableModel(arry,0);
            bangMenu.setModel(model);
            model = (DefaultTableModel)bangMenu.getModel();
            ps = conn.prepareStatement(sql);
            rs= ps.executeQuery();
            model.setRowCount(0);  
            while(rs.next()){
                Vector vector=new Vector();
                vector.add(i++);
                vector.add(rs.getString("SPMa").trim());
                vector.add(rs.getString("SPLoai").trim());
                vector.add(rs.getString("SPTen").trim());
                vector.add(rs.getString("SPDonVi").trim());
                vector.add(rs.getString("SPGia").trim());
                
                model.addRow(vector);
            }
           // bangMenu.setModel(model);
            conn.close();
            rs.close();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    // Hàm kiểm tra có kí tự nhập vào số lượng hay không
    private void kiemTraKiTu(String arry){
        char[] kitu = arry.toCharArray();
        for(int i = 0; i<kitu.length;i++){
            if(String.valueOf(kitu[i]).matches("\\D+")){
                btnLuu.setEnabled(false);
                lblTrangthai.setText("Số lượng không thể chứa kí tự");
                break;
            }
            else btnLuu.setEnabled(true);
        }
    }
    
    // Hàm kiểm tra có trùng sản phẩm đã có trong menu hay không
    private boolean kiemtra() {
        try {
            kn();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("SPMa").toString().trim().equals(tfMaSP.getText())) {
                    lblTrangthai.setText("Mã sản phẩm bạn nhập đã tồn tại");
                    return false;
                } else if (rs.getString("SPTen").toString().trim().equals(tfTenSP.getText())) {
                    lblTrangthai.setText("Sản phẩm bạn nhập đã tồn tại");
                    return false;
                }
            }
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return true;
    }
    
    // INIT COMPONENT
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        bangMenu = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        tfMaSP = new javax.swing.JTextField();
        tfTenSP = new javax.swing.JTextField();
        tfDonVi = new javax.swing.JTextField();
        tfGia = new javax.swing.JTextField();
        cbbLoai = new javax.swing.JComboBox<>();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        tfTim = new javax.swing.JTextField();
        btnTim = new javax.swing.JButton();
        btnLuu = new javax.swing.JButton();
        btnHome = new javax.swing.JButton();
        lblTrangthai = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        bangMenu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã sản phẩm", "Loại sản phẩm", "Tên sản phẩm", "Đơn vị", "Giá"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Long.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        bangMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                bangMenuMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(bangMenu);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setText("THÔNG TIN SẢN PHẨM");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Mã sản phẩm");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Tên sản phẩm");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Loại sản phẩm");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Đơn vị tính");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Giá");

        btnThem.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hình/Thêm.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hình/Sửa.jpg"))); // NOI18N
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hình/Xóa.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel9.setText("MENU");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Tìm kiếm");

        tfTim.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        tfTim.setText("Nhập mã / Loại / Tên sản phẩm cần tìm kiếm");
        tfTim.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tfTimMouseReleased(evt);
            }
        });

        btnTim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hình/Tìm (1).png"))); // NOI18N
        btnTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimActionPerformed(evt);
            }
        });

        btnLuu.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnLuu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hình/dấu tích.png"))); // NOI18N
        btnLuu.setText("Lưu");
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });

        btnHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hình/home.jpg"))); // NOI18N
        btnHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomeActionPerformed(evt);
            }
        });

        lblTrangthai.setText("  ");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("VNĐ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnHome, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(334, 334, 334)
                        .addComponent(jLabel9))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(39, 39, 39))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnSua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(74, 74, 74)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(26, 26, 26))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(tfTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(cbbLoai, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(tfMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addComponent(tfDonVi, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addComponent(tfGia, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGap(12, 12, 12))))
                                    .addComponent(lblTrangthai, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 11, Short.MAX_VALUE))))))
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tfTim, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnTim, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9)
                    .addComponent(btnHome, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnTim, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(tfTim, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(27, 27, 27)
                        .addComponent(lblTrangthai, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(tfMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(cbbLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(tfTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfDonVi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(tfGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnThem)
                            .addComponent(btnXoa))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnLuu)
                            .addComponent(btnSua)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    // Hàm reset lại tất cả các text field và button
    private void reset() {
        them = false;
        thayDoi = false;
        Loai();
        tfMaSP.setText("");
        cbbLoai.setSelectedIndex(0);
        tfTenSP.setText("");
        tfDonVi.setText("");
        tfGia.setText("");
        btnThem.setEnabled(true);
        btnLuu.setEnabled(false);
        btnSua.setEnabled(false);
        btnXoa.setEnabled(false);

    }

    // Hàm kiểm tra xem thông tin nhập đầy đủ chưa
    private boolean kiemtrarong() {
        if (tfMaSP.getText().equals("")) {
            lblTrangthai.setText("Bạn chưa nhập mã cho sản phẩm!");
            return false;
        } else if (cbbLoai.getSelectedItem().equals("")) {
            lblTrangthai.setText("Bạn chưa chọn loại cho sản phẩm!");
            return false;
        } else if (tfTenSP.getText().equals("")) {
            lblTrangthai.setText("Bạn chưa nhập tên sản phẩm");
            return false;
        } else if (tfDonVi.getText().equals("")) {
            lblTrangthai.setText("Bạn chưa nhập đơn vị tính!");
            return false;
        } else if (tfGia.getText().equals("")) {
            lblTrangthai.setText("Bạn chưa nhập giá!");
            return false;
        }
        return true;
    }

    // Hàm bật tất cả text field
    private void Enabled() {
        tfMaSP.setEnabled(true);
        cbbLoai.setEnabled(true);
        tfTenSP.setEnabled(true);
        tfDonVi.setEnabled(true);
        tfGia.setEnabled(true);
    }

    // Hàm tắt tất cả text field
    private void Disabled() {
        tfMaSP.setEnabled(false);
        cbbLoai.setEnabled(false);
        tfTenSP.setEnabled(false);
        tfDonVi.setEnabled(false);
        tfGia.setEnabled(false);
    }

    // Sự kiện của nút thêm
    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
//        reset();
//        them = true;
//        Enabled();
//        btnThem.setEnabled(false);
//        btnLuu.setEnabled(true);
//        reset();
//        tfMaSP.requestFocus();
        
        
        
        reset();
        them=true;
        thayDoi=false;
        Enabled();
        btnThem.setEnabled(false);
        btnXoa.setEnabled(false);
        btnSua.setEnabled(false);
        btnLuu.setEnabled(true);
        tfMaSP.requestFocus();
    }//GEN-LAST:event_btnThemActionPerformed

    // Hàm thêm dữ liệu vào Menu
//    private void themMenu(){
//        if(kiemtrarong()){
//            try {
//               kn();
//               String sqlthem="Insert into Menu (SPMa,SPLoai,SPTen,SPDonVi,SPGia) "
//                    + "VALUES (N'"+tfMaSP.getText()+"',N'"+cbbLoai.getSelectedItem().toString()
//                    +"',N'"+tfTenSP.getText()+"',N'"+ "',N'"+tfDonVi.getText()
//                    +(tfGia.getText()+" "+"VNĐ")+")";
//
//               
//
//                ps = conn.prepareStatement(sqlthem);
//                rs = ps.executeQuery();
//                conn.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            Xem(sql);
//            reset();
//            Disabled();
//            lblTrangthai.setText("Đã thêm 1 sản phẩm!");
//        }
//    }
//    
//    
//
//
//
//
//    
//    // Hàm sửa dữ liệu trên Menu
//    private void suaMenu(){
//         if(kiemtrarong()){
//            try {
//                kn();
//                int click=bangMenu.getSelectedRow();
//                TableModel model=bangMenu.getModel();
////                String sqlsua="UPDATE Menu SET SPMa='"+tfMaSP.getText()+"',"
////                        + " SPLoai=N'"+cbbLoai.getSelectedItem()+"'"
////                        + ", SPTen=N'"+tfTenSP.getText()
////                        +", SPDonVi=N'"+tfDonVi.getText()  +"', SPGia='"+(tfGia.getText()
////                        +" WHERE SPMa=N'"+tfMaSP.getText()+"'");
//
//
//                String sqlsua="UPDATE Menu SET SPMa='"+tfMaSP.getText()+"',"
//                    + " SPLoai=N'"+cbbLoai.getSelectedItem()+"'"
//                    + ", SPTen=N'"+tfTenSP.getText()+"'"
//                    +", SPDonVi=N'"+tfDonVi.getText()  +"', SPGia='"+tfGia.getText()+"'"
//                    +" WHERE SPMa=N'"+tfMaSP.getText()+"'";
//
//
//
//                ps = conn.prepareStatement(sqlsua);
//                rs = ps.executeQuery();    
//                
//                conn.close();
//            } catch (Exception e) {
//                    e.printStackTrace();
//            }
//            Xem(sql);
//            reset();
//            Disabled();
//            lblTrangthai.setText("Cập nhật thành công");
//        }
//     }   
    
    
    
    
    
    
    
    
    
    
    private void themMenu() {
    if (kiemtrarong()) {
        try {
            kn();
            String sqlthem = "Insert into Menu (SPMa, SPLoai, SPTen, SPDonVi, SPGia) values (?, ?, ?, ?, ?)";

            ps = conn.prepareStatement(sqlthem);
            ps.setString(1, tfMaSP.getText());
            ps.setString(2, cbbLoai.getSelectedItem().toString());
            ps.setString(3, tfTenSP.getText());
            ps.setString(4, tfDonVi.getText());
            ps.setString(5, tfGia.getText());


            ps.executeUpdate();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Xem(sql);
        reset();
        Disabled();
        lblTrangthai.setText("Đã thêm 1 sản phẩm!");
    }
}


private void suaMenu() {
    if (kiemtrarong()) {
        try {
            kn();
            String sqlsua = "UPDATE Menu SET SPMa=?, SPLoai=?, SPTen=?, SPDonVi=?, SPGia=? WHERE SPMa=?";

            ps = conn.prepareStatement(sqlsua);
            ps.setString(1, tfMaSP.getText());
            ps.setString(2, cbbLoai.getSelectedItem().toString());
            ps.setString(3, tfTenSP.getText());
            ps.setString(4, tfDonVi.getText());
            ps.setString(5, tfGia.getText());
            ps.setString(6, tfMaSP.getText());

            ps.executeUpdate();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Xem(sql);
        reset();
        Disabled();
        lblTrangthai.setText("Cập nhật thành công");
    }
}

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    // Sự kiện của nút home quay về trang chủ
    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeActionPerformed
        FrmGiaoDienChinh home = new FrmGiaoDienChinh();
        this.setVisible(false);
        home.setVisible(true);
    }//GEN-LAST:event_btnHomeActionPerformed
    
    // Sự kiện của nút xóa
    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        int click=JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa sản phẩm này không?", "Thông báo", 2);
        if(click==JOptionPane.YES_OPTION){
            try {
                kn();
                String sqlxoa="Delete from Menu where SPMa='"+tfMaSP.getText()+"'";
                ps = conn.prepareStatement(sqlxoa);
                rs = ps.executeQuery();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            reset();
            Xem(sql);
            Disabled();
            lblTrangthai.setText("Xóa thành công thức uống!");
        }
        else reset();
    }//GEN-LAST:event_btnXoaActionPerformed

    // Sự kiện sau khi mở form
    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        Loai();
    }//GEN-LAST:event_formWindowOpened

    // Sự kiện lấy dữ liệu từ bảng lên text field để chỉnh sửa
    private void bangMenuMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bangMenuMouseReleased
        int hang = bangMenu.getSelectedRow();
        cbbLoai.setSelectedIndex(0);
        Object ob1 = (Object) bangMenu.getValueAt(hang, 1);
        String m1 = String.valueOf(ob1);
        tfMaSP.setText(m1);
        Object ob2 = (Object) bangMenu.getValueAt(hang, 2);
        String m2 = String.valueOf(ob2);
        cbbLoai.setSelectedItem(m2);
        Object ob3 = (Object) bangMenu.getValueAt(hang, 3);
        String m3 = String.valueOf(ob3);
        tfTenSP.setText(m3);
        Object ob4 = (Object) bangMenu.getValueAt(hang, 4);
        String m4 = String.valueOf(ob4);
        tfDonVi.setText(m4);
        Object ob5 = (Object) bangMenu.getValueAt(hang, 5);
        String m5 = String.valueOf(ob5);
        tfGia.setText(m5);
        Disabled();
        
        btnXoa.setEnabled(true);
        btnSua.setEnabled(true);
    }//GEN-LAST:event_bangMenuMouseReleased

    // Sự kiện của nút sửa
    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        them=false;
        thayDoi=true;
        Enabled();
        btnThem.setEnabled(false);
        btnXoa.setEnabled(false);
        btnSua.setEnabled(false);
        btnLuu.setEnabled(true);
    }//GEN-LAST:event_btnSuaActionPerformed

    // Sự kiện của nút lưu
    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        if(them==true)  {
            if(kiemtra()) {themMenu(); Enabled();}
        }
        else if(thayDoi==true)     {suaMenu(); Enabled();}              
    }//GEN-LAST:event_btnLuuActionPerformed

    // Sự kiện của nút tìm
    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimActionPerformed
        try {
            String sqltim = "Select * from Menu where SPMa like N'%"
                +tfTim.getText()+"%' or SPTen like N'%"+tfTim.getText()
                +"%' or SPLoai like N'%"+tfTim.getText()+"%'";
            Xem(sqltim);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Disabled();
        reset();
    }//GEN-LAST:event_btnTimActionPerformed

    // Sự kiện click vào ô tìm kiếm
    private void tfTimMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfTimMouseReleased
        tfTim.setText("");
    }//GEN-LAST:event_tfTimMouseReleased

    // Sự kiện hủy thao tác chọn button
    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        Disabled();
        reset();
        Xem(sql);
    }//GEN-LAST:event_formMouseReleased

    // Hàm main
    public static void main(String args[]) {
       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable bangMenu;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTim;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cbbLoai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTrangthai;
    private javax.swing.JTextField tfDonVi;
    private javax.swing.JTextField tfGia;
    private javax.swing.JTextField tfMaSP;
    private javax.swing.JTextField tfTenSP;
    private javax.swing.JTextField tfTim;
    // End of variables declaration//GEN-END:variables
}
