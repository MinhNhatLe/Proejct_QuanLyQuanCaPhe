package ViewCodeForm;

// Khai báo thư viện


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;


public class FrmNhanVien extends javax.swing.JFrame {

    private Connection conn = null;
    private ResultSet rs = null;
    private Statement s;
    private PreparedStatement ps = null;
    private String sql = "Select * from NhanVien";

    
    private boolean them = false, thayDoi = false;
    private DefaultTableModel model;
    
    private int hang;
    
    // Hàm kết nối cơ sở dữ liệu
    private void kn() {
        
        String url = "jdbc:sqlserver://LAPTOP-AP54ORLO\\SQLEXPRESS:1433;databaseName=QuanLiQuanCaPhe;" + "username=sa;password=sa;encrypt=true;trustServerCertificate=true";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(url);
        } catch (Exception e) {
            System.out.println("Kết nối cơ sở dữ liệu thất bại thất bại");
        }
    }
    
    // Hàm xây dựng mặc nhiên - khởi tạo giá trị ban đầu
    public FrmNhanVien() {
        initComponents();
        this.setTitle("Quản lí nhân viên");
        setResizable(false);
        this.setLocationRelativeTo(null);
        kn();
        btnThem.setEnabled(true);
        btnSua.setEnabled(false);
        btnXoa.setEnabled(false);
        btnLuu.setEnabled(false);
        Disabled();
    }
    
    // Hàm lấy dữ liệu lên bảng
    private void Xem(String sql) {
        try {
            kn();
            int i = 1;
            String[] arry={"STT","Mã Nhân Viên","Họ Tên","Giới Tính","Ngày Sinh","Quê quán","Số điện thoại","Số CMND"};
            model=new DefaultTableModel(arry,0);
            //bangNhanVien.setModel(model);
            //model = (DefaultTableModel)bangNhanVien.getModel();
            ps = conn.prepareStatement(sql);
            rs= ps.executeQuery();
            //model.setRowCount(0);
            while(rs.next()){
                Vector vector=new Vector();
                vector.add(i++);
                vector.add(rs.getString("NVMa").trim());
                vector.add(rs.getString("NVHoTen").trim());
                vector.add(rs.getString("NVGioiTinh").trim());
                vector.add(rs.getDate("NVNgaySinh"));
                vector.add(rs.getString("NVQue").trim());
                vector.add(rs.getString("NVSDT").trim());
                vector.add(rs.getString("NVCMND").trim());
                model.addRow(vector);
            }
            bangNhanVien.setModel(model);
           rs.close();
           conn.close();    
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        bangNhanVien = new javax.swing.JTable();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnTim = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        tfTim = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        lbMaNV = new javax.swing.JLabel();
        lbHoten = new javax.swing.JLabel();
        lbGioitinh = new javax.swing.JLabel();
        lbNgaysinh = new javax.swing.JLabel();
        lbSDT = new javax.swing.JLabel();
        lbDiachi = new javax.swing.JLabel();
        tfMaNV = new javax.swing.JTextField();
        tfHoten = new javax.swing.JTextField();
        tfSDT = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tfTaikhoan = new javax.swing.JTextField();
        tfMatkhau = new javax.swing.JPasswordField();
        tfQue = new javax.swing.JTextField();
        rbNam = new javax.swing.JRadioButton();
        rbNu = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        tfRematkhau = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        tfNgaysinh = new javax.swing.JTextField();
        lbDiachi1 = new javax.swing.JLabel();
        tfCMND = new javax.swing.JTextField();
        btnLuu = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        home = new javax.swing.JButton();
        lblTrangThai = new javax.swing.JLabel();

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

        bangNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        bangNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                bangNhanVienMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(bangNhanVien);

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

        btnTim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hình/Tìm (1).png"))); // NOI18N
        btnTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Tìm kiếm");

        tfTim.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        tfTim.setText("Nhập mã / Họ tên / Giới tính nhân viên cần tìm kiếm");
        tfTim.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tfTimMouseClicked(evt);
            }
        });

        lbMaNV.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        lbMaNV.setText("Mã Nhân viên:");

        lbHoten.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        lbHoten.setText("Họ và Tên:");

        lbGioitinh.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        lbGioitinh.setText("Giới tính:");

        lbNgaysinh.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        lbNgaysinh.setText("Ngày sinh:");

        lbSDT.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        lbSDT.setText("Số điện thoại:");

        lbDiachi.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        lbDiachi.setText("Quê quán:");

        tfMaNV.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tfMaNV.setEnabled(false);

        tfHoten.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        tfSDT.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tfSDT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfSDTKeyReleased(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        jLabel1.setText("Tài khoản:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        jLabel2.setText("Mật khẩu:");

        tfTaikhoan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        tfMatkhau.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        tfQue.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        buttonGroup1.add(rbNam);
        rbNam.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rbNam.setText("Nam");

        buttonGroup1.add(rbNu);
        rbNu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rbNu.setText("Nữ");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel3.setText("THÔNG TIN NHÂN VIÊN");

        tfRematkhau.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        jLabel4.setText("Xác nhận lại mật khẩu:");

        tfNgaysinh.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfNgaysinhKeyReleased(evt);
            }
        });

        lbDiachi1.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        lbDiachi1.setText("CMND:");

        tfCMND.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lbMaNV)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfMaNV, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(lbSDT)
                                        .addGap(7, 7, 7))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lbHoten, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(lbNgaysinh, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(lbDiachi, javax.swing.GroupLayout.Alignment.TRAILING))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfHoten)
                                    .addComponent(tfNgaysinh)
                                    .addComponent(tfSDT)
                                    .addComponent(tfQue))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfRematkhau, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfMatkhau, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(1, 1, 1))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lbDiachi1)
                                    .addComponent(jLabel1)
                                    .addComponent(lbGioitinh))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tfCMND)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(rbNu, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(8, 8, 8)
                                        .addComponent(rbNam, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(tfTaikhoan))))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addGap(155, 155, 155))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbDiachi1)
                        .addComponent(tfCMND, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbMaNV)
                        .addComponent(tfMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbHoten)
                    .addComponent(tfHoten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbGioitinh)
                    .addComponent(rbNu)
                    .addComponent(rbNam))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNgaysinh)
                    .addComponent(jLabel1)
                    .addComponent(tfTaikhoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfNgaysinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfMatkhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbDiachi)
                    .addComponent(jLabel4)
                    .addComponent(tfRematkhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfQue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        btnLuu.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnLuu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hình/dấu tích.png"))); // NOI18N
        btnLuu.setText("Lưu");
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel5.setText("QUẢN LÍ NHÂN VIÊN");

        home.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hình/home.jpg"))); // NOI18N
        home.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(home, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(203, 203, 203))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblTrangThai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(22, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 799, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tfTim, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnTim, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(165, 165, 165))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnTim, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(tfTim, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel10)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(home)
                        .addGap(45, 45, 45)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(lblTrangThai)
                        .addGap(44, 44, 44)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    // Hàm bật tất cả chức năng của text field và button
    private void Enabled(){
        tfMaNV.setEnabled(true);
        tfHoten.setEnabled(true);
        tfNgaysinh.setEnabled(true);
        tfSDT.setEnabled(true);
        tfCMND.setEnabled(true);
        tfTaikhoan.setEnabled(true);
        tfMatkhau.setEnabled(true);
        tfRematkhau.setEnabled(true);
        tfQue.setEnabled(true);
        rbNu.setEnabled(true);
        rbNam.setEnabled(true);
    }
    
    // Hàm tắt tất cả chức năng của text field và button
    private void Disabled(){
        tfMaNV.setEnabled(false);
        tfHoten.setEnabled(false);
        tfNgaysinh.setEnabled(false);
        tfSDT.setEnabled(false);
        tfCMND.setEnabled(false);
        tfTaikhoan.setEnabled(false);
        tfMatkhau.setEnabled(false);
        tfRematkhau.setEnabled(false);
        tfQue.setEnabled(false);
        rbNu.setEnabled(false);
        rbNam.setEnabled(false);
    }
    
    // Hàm đặt reset thông tin
    private void reset(){
        them = false;
        thayDoi = false;
        tfMaNV.setText("");
        tfHoten.setText("");
        tfNgaysinh.setText("");
        tfSDT.setText("");
        tfCMND.setText("");
        tfTaikhoan.setText("");
        tfMatkhau.setText("");
        tfRematkhau.setText("");
        tfQue.setText("");
        lblTrangThai.setText("");
        rbNam.setSelected(false);
        btnSua.setEnabled(false);
        btnXoa.setEnabled(false);
        btnLuu.setEnabled(false);
        btnThem.setEnabled(true);
    }
    
    // Hàm kiểm tra SĐT hợp lệ
    private void tfSDTKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfSDTKeyReleased
        if(tfSDT.getText().length()==11 || tfSDT.getText().length()==10 ){

            btnLuu.setEnabled(true);
            lblTrangThai.setText("Số điện thoại đã hợp lệ!!");
        }
        else
        if(tfSDT.getText().length()>11 || tfSDT.getText().length()<10){
            btnLuu.setEnabled(false);
            lblTrangThai.setText("Số điện thoại không hợp lệ!!");
        }
    }//GEN-LAST:event_tfSDTKeyReleased

    // Sự kiện nhấn nút Lưu
    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        if(them==true)  {
            if(kiemtratrung()) {themNV(); Enabled();}
        }
        else if(thayDoi==true)     {suaNV(); Enabled();}              
                                     
    }//GEN-LAST:event_btnLuuActionPerformed

    // Sự kiện nút home quay về trang chủ
    private void homeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeActionPerformed
        // TODO add your handling code here:
        FrmGiaoDienChinh home = new FrmGiaoDienChinh();
        this.setVisible(false);
        home.setVisible(true);
    }//GEN-LAST:event_homeActionPerformed

    // Hàm bắt tự động chọn cho radian button giới tính
    private void kiemtraGT(String GT){
        if(GT.equals("Nam"))
            rbNam.setSelected(true);
        else
            rbNu.setSelected(true);
    }
    
    // Hàm kiểm tra thông tin nhập đầy đủ
    private boolean kiemtrarong(){
        if(tfMaNV.getText().equals("")){
            lblTrangThai.setText("Bạn chưa nhập mã nhân viên!");
            return false;
        }
        else
        if(tfHoten.getText().equals("")){
            lblTrangThai.setText("Bạn chưa nhập họ tên nhân viên!");
            return false;
        }
        else
        if(rbNam.isSelected()==false && rbNu.isSelected()==false){
            lblTrangThai.setText("Bạn chưa chọn giới tính!");
            return false;
        }
        else
        if (tfNgaysinh.getText().equals("")){
            lblTrangThai.setText("Bạn chưa nhập ngày sinh!");
            return false;
        }
        else   
        if(tfSDT.getText().equals("")){
            lblTrangThai.setText("Bạn chưa nhập số điện thoại!");
            return false;
        }
        if(tfCMND.getText().equals("")){
            lblTrangThai.setText("Bạn chưa nhập số CMND!");
            return false;
        }
        else   
        if(tfQue.getText().equals("")){
            lblTrangThai.setText("Bạn chưa nhập quê quán của nhân viên!");
            return false;
        }
        else
        if(tfTaikhoan.getText().equals("")){
            lblTrangThai.setText("Bạn chưa nhập tài khoản!");
            return false;
        }
        else
        if(tfMatkhau.getText().equals("")){
            lblTrangThai.setText("Bạn chưa nhập mật khẩu!");
            return false;
        }
        else
        if(tfRematkhau.getText().equals("")){
            lblTrangThai.setText("Bạn chưa nhập lại mật khẩu!");
            return false;
        }
        else
        if(String.valueOf(tfMatkhau.getPassword()).equals(String.valueOf(tfRematkhau.getPassword()))){
            return true;
        }
        else {
            lblTrangThai.setText("Nhập lại mật khẩu không đúng!");
            return false;
        }
    }
   
    // Hàm thêm nhân viên
    private void themNV(){
        if(kiemtrarong()){
            try {
                kn();
                String sqlthem="Insert into NhanVien (NVMa,NVHoTen,NVGioiTinh,NVNgaySinh,NVQue,NVSDT,NVCMND,NVTenDangNhap,NVMatKhau) values "
                        + "('"+tfMaNV.getText()+"',N'"+tfHoten.getText()+"',N'"+gioiTinh()+"','"+tfNgaysinh.getText()
                        +"',N'"+tfQue.getText()+"','"+tfSDT.getText()+"','"+tfCMND.getText()
                        +"','"+tfTaikhoan.getText()+"','"+tfMatkhau.getText()+"')";
                ps = conn.prepareStatement(sqlthem);
                ps.executeUpdate();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            reset();
            Xem(sql);
            Disabled();
            lblTrangThai.setText("Thêm nhân viên thành công!");
        }
    }
    
    // Hàm lấy kết quả chọn giới tính
    private String gioiTinh(){
        if(rbNam.isSelected())
            return "Nam";
        else
            return "Nữ";
    }
    
    // Hàm sửa thông tin nhân viên
    private void suaNV(){
        if(kiemtrarong()){
            try {
                kn();
                int click = bangNhanVien.getSelectedRow();
                TableModel model = bangNhanVien.getModel();
                String sqlsua="Update NhanVien set NVMa='"+tfMaNV.getText()
                        +"', NVHoTen=N'"+tfHoten.getText()
                        +"', NVGioiTinh=N'"+gioiTinh()
                        +"', NVNgaySinh='"+tfNgaysinh.getText()
                        +"',NVQue=N'"+tfQue.getText()
                        +"', NVSDT='"+tfSDT.getText()
                        +"',NVTenDangNhap='"+tfTaikhoan.getText()
                        +"',NVMatKhau='"+tfMatkhau.getText()
                        +"' WHERE NVma='"+tfMaNV.getText()+"'";
                
                

                
                ps = conn.prepareStatement(sqlsua);              
                ps.executeUpdate();
                conn.close();
                

            } catch (Exception e) {
                System.out.println("Sửa thông tin thất bại");
//                e.printStackTrace();
            }
            reset();
            Xem(sql);
            Disabled();
            lblTrangThai.setText("Sửa thông tin nhân viên thành công!");
        }
    }
    
    
    
    




    


    


    
    // Hàm kiểm tra thông tin nhân viên mới có trùng không
    private boolean kiemtratrung(){
        try {
            kn();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                if(rs.getString("NVMa").toString().trim().equals(tfMaNV.getText())){
                    lblTrangThai.setText("Mã nhân viên bạn nhập đã tồn tại!");
                    return false;
                }
                else
                if(rs.getString("NVTenDangNhap").toString().trim().equals(tfTaikhoan.getText())){
                    lblTrangThai.setText("Tên đăng nhập bạn chọn đã tồn tại!");
                    return false;
                }
            }
            rs.close();
            conn.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return true;
    }
    
    // Hàm lấy thông tin tài khoản về text field
    private void loadAccount(String s){
        try{
            sql="Select * from NhanVien where NVMa='"+s+"'";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                tfTaikhoan.setText(rs.getString("NVTaiKhoan").trim());
                tfMatkhau.setText(rs.getString("NVMatkhau").trim());
                tfRematkhau.setText(rs.getString("matKhau").trim());
            }
            rs.close();
            conn.close();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    //  Sự kiện của nút Thêm
    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        reset();
        them=true;
        Enabled();
        btnThem.setEnabled(false);
        btnLuu.setEnabled(true);
        tfMaNV.requestFocus();
    }//GEN-LAST:event_btnThemActionPerformed

    // Sự kiện của nút tìm
    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimActionPerformed
        try {
            String sqlTim = "Select * from NhanVien where NVMa like N'%"+tfTim.getText()+"%' or NVHoTen like N'%"+tfTim.getText()+"%' or NVGioiTinh like N'%"+tfTim.getText()+"%' or NVNgaySinh like N'%"+tfTim.getText()+"%' or NVQue like N'%"+tfTim.getText()+"%' or NVTenDangNhap like N'%"+tfTim.getText()+"'";
            Disabled();
            Xem(sqlTim);
            reset();      
        } catch (Exception e) {
            e.printStackTrace();
            lblTrangThai.setText("Thông tin nhập vào không hợp lệ, vui lòng kiểm tra lại");
        }    
    }//GEN-LAST:event_btnTimActionPerformed
    
    // Sự kiện của nút Sửa
    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed

        them=false;
        thayDoi=true;
        Enabled();
        btnThem.setEnabled(false);
        btnXoa.setEnabled(false);
        btnSua.setEnabled(false);
        btnLuu.setEnabled(true);

    }//GEN-LAST:event_btnSuaActionPerformed

    // Sự kiện của nút Xóa
    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        int click=JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa nhân viên này hay không?", "Thông báo", 2);
        if(click==JOptionPane.YES_OPTION){
            String sqlXoa = "Delete from NhanVien where NVMa='"+tfMaNV.getText().trim()+"'";
            try {
                kn();
                ps = conn.prepareStatement(sqlXoa);
                ps.executeUpdate();
                conn.close();
                Xem(sql);
                reset();
                Disabled();
                lblTrangThai.setText("Xóa nhân viên thành công!");
            } catch (Exception e) {
                System.out.println("Xóa thất bại");
            }
            
        }
        else reset();
    }//GEN-LAST:event_btnXoaActionPerformed

    // Sự kiện lấy thông tin nhân viên từ bảng về TextField để chỉnh sửa
    private void bangNhanVienMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bangNhanVienMouseReleased
        hang = bangNhanVien.getSelectedRow();

        Object ob1 = (Object) bangNhanVien.getValueAt(hang, 1);
        String m1 = String.valueOf(ob1);
        tfMaNV.setText(m1);
        Object ob2 = (Object) bangNhanVien.getValueAt(hang, 2);
        String m2 = String.valueOf(ob2);
        tfHoten.setText(m2);
        Object ob3 = (Object) bangNhanVien.getValueAt(hang, 3);
        String m3 = String.valueOf(ob3);
        if(m3.equals("Nam")) rbNam.setSelected(true);else rbNu.setSelected(true);
        Object ob4 = (Object) bangNhanVien.getValueAt(hang, 4);
        String m4 = String.valueOf(ob4);
        tfNgaysinh.setText(m4);
        Object ob5 = (Object) bangNhanVien.getValueAt(hang, 5);
        String m5 = String.valueOf(ob5);
        tfQue.setText(m5);
        Object ob6 = (Object) bangNhanVien.getValueAt(hang, 6);
        String m6 = String.valueOf(ob6);
        tfSDT.setText(m6);
        Object ob7 = (Object) bangNhanVien.getValueAt(hang, 7);
        String m7 = String.valueOf(ob7);
        tfCMND.setText(m7); 
        
        Disabled();
        btnThem.setEnabled(true);
        btnXoa.setEnabled(true);
        btnSua.setEnabled(true);
        btnLuu.setEnabled(true);    
    }//GEN-LAST:event_bangNhanVienMouseReleased

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        Xem(sql);
    }//GEN-LAST:event_formWindowOpened

    // Sự kiện người dùng click vào text field tìm kiếm
    private void tfTimMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfTimMouseClicked
        // TODO add your handling code here:
        tfTim.setText("");
        btnThem.setEnabled(true);
        btnSua.setEnabled(false);
        btnXoa.setEnabled(false);
        btnLuu.setEnabled(false);
    }//GEN-LAST:event_tfTimMouseClicked

    // Sự kiện cancel
    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        Disabled();
        reset();
    }//GEN-LAST:event_formMouseReleased

    private void tfNgaysinhKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfNgaysinhKeyReleased
        lblTrangThai.setText("Nhập ngày sinh theo thứ tự năm - tháng - ngày");
    }//GEN-LAST:event_tfNgaysinhKeyReleased

    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                new FrmNhanVien().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable bangNhanVien;
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTim;
    private javax.swing.JButton btnXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton home;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbDiachi;
    private javax.swing.JLabel lbDiachi1;
    private javax.swing.JLabel lbGioitinh;
    private javax.swing.JLabel lbHoten;
    private javax.swing.JLabel lbMaNV;
    private javax.swing.JLabel lbNgaysinh;
    private javax.swing.JLabel lbSDT;
    private javax.swing.JLabel lblTrangThai;
    private javax.swing.JRadioButton rbNam;
    private javax.swing.JRadioButton rbNu;
    private javax.swing.JTextField tfCMND;
    private javax.swing.JTextField tfHoten;
    private javax.swing.JTextField tfMaNV;
    private javax.swing.JPasswordField tfMatkhau;
    private javax.swing.JTextField tfNgaysinh;
    private javax.swing.JTextField tfQue;
    private javax.swing.JPasswordField tfRematkhau;
    private javax.swing.JTextField tfSDT;
    private javax.swing.JTextField tfTaikhoan;
    private javax.swing.JTextField tfTim;
    // End of variables declaration//GEN-END:variables
}
