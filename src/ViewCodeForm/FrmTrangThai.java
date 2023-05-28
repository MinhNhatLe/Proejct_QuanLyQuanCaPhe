package ViewCodeForm;

// Khai báo thư viện
import Model.ClsHoaDon;
import java.awt.Color;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class FrmTrangThai extends javax.swing.JFrame {

    private Connection conn = null;
    private ResultSet rs = null, rsCheck = null;
    private PreparedStatement ps = null;
    private Statement s = null;
    private boolean them = false, thayDoi = false;
    private DefaultTableModel model = new DefaultTableModel();

    private boolean tinh = false;
    private int count = 0;
    private int sttBan;
    private JButton tenban;
    private int dongTrongBangHoaDon;
    
    

    ClsHoaDon[] hoadon = new ClsHoaDon[30];
    

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
    public FrmTrangThai() {
        initComponents();
        this.setTitle("Trạng thái quán");
        this.setLocationRelativeTo(null);
        setResizable(false);
        kn();
        lblNhanVien.setText(FrmDangNhap.tenNV);
        lblThoiGian.setText(String.valueOf(new SimpleDateFormat("HH:mm:ss").format(new java.util.Date())));
        lblNgay.setText(String.valueOf(new SimpleDateFormat("EEEE dd/MM/yyyy").format(new java.util.Date())));
        Disabled();
        kiemTraHoaDon();
        model = (DefaultTableModel) bangHoaDon.getModel();
    }

    // Hàm tải combobox Loai
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

    // Hàm tắt các chức năng
    private void Disabled() {
        cbbLoai.setEnabled(false);
        cbxTenSanPham.setEnabled(false);
        spnSoLuong.setEnabled(false);
        btnThem.setEnabled(false);
    }

    // Ham bật các chức năng
    private void Enabled() {
        cbbLoai.setEnabled(true);
        cbxTenSanPham.setEnabled(true);
        spnSoLuong.setEnabled(true);
        btnThem.setEnabled(true);
    }

    // Hàm đặt lại tất cả các text field và button
    private void Refresh() {
        tinh = false;
        lblBan.setText("");
        spnSoLuong.setValue(1);
        lblGia.setText("");
        lblThanhTien.setText("");
        lblTongTien.setText("");
        tfTienNhanCuaKhach.setText("");
        lblTienThua.setText("");
        btnThanhToan.setEnabled(false);
        btnIn.setEnabled(false);
        Disabled();
    }

    // Hàm lấy giờ
    private int getHours(String s) {
        String[] array = s.replace(":", " ").split("\\s");
        return Integer.parseInt(array[0]);
    }

    // Hàm lấy phút
    private int getMinute(String s) {
        String[] array = s.replace(":", " ").split("\\s");
        return Integer.parseInt(array[1]);
    }

    // Hàm kiểm tra hóa đơn để sử dụng các button
    private void kiemTraHoaDon() {
        if (bangHoaDon.getRowCount() == 0) {
            btnThanhToan.setEnabled(false);
            tfTienNhanCuaKhach.setEnabled(false);
        } else {
            btnThanhToan.setEnabled(true);
            tfTienNhanCuaKhach.setEnabled(true);
        }
    }

    // Hàm lưu dữ liệu vào bảng thống kê
//    private void luuThongKe() {
//        int a = Integer.parseInt(lblTongTien.getText());
//        String sqlThongKe = "Insert into ThongKe (TKBan,TKTongTien,TKTienKhach,"
//                + "TKTienThua,NVHoTen,TKNgay,TKThoiGian) values "
//                + "( '" + lblBan.getText() + "'," + Integer.parseInt(lblTongTien.getText())
//                + "," + Integer.parseInt(tfTienNhanCuaKhach.getText())
//                + "," + Integer.parseInt(lblTienThua.getText()) + " ,N'" + lblNhanVien.getText()
//                + "',N'" + lblNgay.getText().substring(8,18) +
//                "','" + lblThoiGian.getText() + "')";
//        try {
//            kn();
//            Statement st = conn.createStatement();
//            st.executeUpdate(sqlThongKe);
//            conn.close();
//            System.out.println("Lưu thành công");
////            hoadon[sttBan] = null;
//            lblTrangThai.setText("Thanh toán thành công");
//
//        } catch (Exception e) {
//            System.out.println("Lưu thất bại");
//            e.printStackTrace();
//        }
//    }
    
    
    
    
    
    private void luuThongKe() {
    int a = Integer.parseInt(lblTongTien.getText());
    String sqlThongKe = "INSERT INTO ThongKe (TKBan, TKTongTien, TKTienKhach, TKTienThua, NVHoTen, TKNgay, TKThoiGian) VALUES (?, ?, ?, ?, ?, ?, ?)";
    try {
        kn();
        PreparedStatement ps = conn.prepareStatement(sqlThongKe);
        ps.setString(1, lblBan.getText());
        ps.setInt(2, Integer.parseInt(lblTongTien.getText()));
        ps.setInt(3, Integer.parseInt(tfTienNhanCuaKhach.getText()));
        ps.setInt(4, Integer.parseInt(lblTienThua.getText()));
        ps.setString(5, lblNhanVien.getText());
        ps.setString(6, lblNgay.getText().substring(9, 19));
        ps.setString(7, lblThoiGian.getText());

        ps.executeUpdate();
        conn.close();
        System.out.println("Lưu thành công");
        lblTrangThai.setText("Thanh toán thành công");
    } catch (Exception e) {
        System.out.println("Lưu thất bại");
//        e.printStackTrace();
    }
}

    
    
    
    
    
    
    

    // Hàm thêm món
    private void them() {
        int s = (Integer.parseInt(spnSoLuong.getValue().toString()) * Integer.parseInt(lblGia.getText()));
        lblThanhTien.setText(String.valueOf(s));
        String tenSP = cbxTenSanPham.getSelectedItem().toString();
        int soLuong = Integer.parseInt(spnSoLuong.getValue().toString());

        model.addRow(new Object[]{
            tenSP, soLuong, s
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        cbbLoai = new javax.swing.JComboBox<>();
        cbxTenSanPham = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        bangHoaDon = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        tfTienNhanCuaKhach = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        btnThem = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        spnSoLuong = new javax.swing.JSpinner();
        jLabel10 = new javax.swing.JLabel();
        lblNhanVien = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lblNgay = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        lblThoiGian = new javax.swing.JLabel();
        btnIn = new javax.swing.JButton();
        btnThanhToan = new javax.swing.JButton();
        home = new javax.swing.JButton();
        lblThanhTien = new javax.swing.JLabel();
        lblTienThua = new javax.swing.JLabel();
        lblTongTien = new javax.swing.JLabel();
        lblGia = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lblBan = new javax.swing.JLabel();
        panBan = new java.awt.Panel();
        Ban18 = new javax.swing.JButton();
        Ban19 = new javax.swing.JButton();
        Ban20 = new javax.swing.JButton();
        Ban22 = new javax.swing.JButton();
        Ban1 = new javax.swing.JButton();
        Ban23 = new javax.swing.JButton();
        Ban2 = new javax.swing.JButton();
        Ban24 = new javax.swing.JButton();
        Ban21 = new javax.swing.JButton();
        Ban3 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        Ban4 = new javax.swing.JButton();
        Ban5 = new javax.swing.JButton();
        Ban6 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        Ban7 = new javax.swing.JButton();
        Ban8 = new javax.swing.JButton();
        Ban9 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        Ban10 = new javax.swing.JButton();
        Ban11 = new javax.swing.JButton();
        Ban12 = new javax.swing.JButton();
        Ban13 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        Ban14 = new javax.swing.JButton();
        Ban15 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        Ban16 = new javax.swing.JButton();
        Ban17 = new javax.swing.JButton();
        lblTrangThai = new javax.swing.JLabel();
        btnXoa = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setText("QUÁN CÀ PHÊ - TRÀ SỮA LMN");

        cbbLoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbLoaiActionPerformed(evt);
            }
        });

        cbxTenSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxTenSanPhamActionPerformed(evt);
            }
        });

        jLabel4.setText("Giá: ");

        jLabel5.setText("Thành tiền:");

        bangHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên sản phẩm ", "Số lượng", "Thành tiền"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(bangHoaDon);

        jLabel6.setText("Tổng tiền:");

        jLabel7.setText("Tiền nhận của khách: ");

        tfTienNhanCuaKhach.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfTienNhanCuaKhachKeyReleased(evt);
            }
        });

        jLabel8.setText("Tiền thừa:");

        btnThem.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hình/Thêm.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        jLabel9.setText("Số lượng:");

        spnSoLuong.setModel(new javax.swing.SpinnerNumberModel(1, 1, 10, 1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel10.setText("Họ tên nhân viên:");

        lblNhanVien.setText("-----------------------------------");

        jLabel12.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel12.setText("Ngày:");

        lblNgay.setText("-----------------------------------");

        jLabel14.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel14.setText("Thời gian:");

        lblThoiGian.setText("----------------------------------");

        btnIn.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnIn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hình/in.jpg"))); // NOI18N
        btnIn.setText("In hóa đơn");
        btnIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInActionPerformed(evt);
            }
        });

        btnThanhToan.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnThanhToan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hình/Trả tiền.png"))); // NOI18N
        btnThanhToan.setText("Thanh toán");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        home.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hình/home.jpg"))); // NOI18N
        home.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeActionPerformed(evt);
            }
        });

        lblThanhTien.setText("-----------------------");

        lblTienThua.setText("-------------------------");

        lblTongTien.setText("---------------------------");

        lblGia.setText("---------------");

        jLabel11.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel11.setText("Bàn số: ");

        lblBan.setText("---------");

        Ban18.setText("18");
        Ban18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Ban18ActionPerformed(evt);
            }
        });

        Ban19.setText("19");
        Ban19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Ban19ActionPerformed(evt);
            }
        });

        Ban20.setText("20");
        Ban20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Ban20ActionPerformed(evt);
            }
        });

        Ban22.setText("22");
        Ban22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Ban22ActionPerformed(evt);
            }
        });

        Ban1.setText("1");
        Ban1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Ban1ActionPerformed(evt);
            }
        });

        Ban23.setText("23");
        Ban23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Ban23ActionPerformed(evt);
            }
        });

        Ban2.setText("2");
        Ban2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Ban2ActionPerformed(evt);
            }
        });

        Ban24.setText("24");
        Ban24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Ban24ActionPerformed(evt);
            }
        });

        Ban21.setText("21");
        Ban21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Ban21ActionPerformed(evt);
            }
        });

        Ban3.setText("3");
        Ban3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Ban3ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Sơ đồ quán");

        Ban4.setText("4");
        Ban4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Ban4ActionPerformed(evt);
            }
        });

        Ban5.setText("5");
        Ban5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Ban5ActionPerformed(evt);
            }
        });

        Ban6.setText("6");
        Ban6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Ban6ActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hình/Cửa.jpg"))); // NOI18N

        Ban7.setText("7");
        Ban7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Ban7ActionPerformed(evt);
            }
        });

        Ban8.setText("8");
        Ban8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Ban8ActionPerformed(evt);
            }
        });

        Ban9.setText("9");
        Ban9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Ban9ActionPerformed(evt);
            }
        });

        jLabel3.setText("Lối vào");

        Ban10.setText("10");
        Ban10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Ban10ActionPerformed(evt);
            }
        });

        Ban11.setText("11");
        Ban11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Ban11ActionPerformed(evt);
            }
        });

        Ban12.setText("12");
        Ban12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Ban12ActionPerformed(evt);
            }
        });

        Ban13.setText("13");
        Ban13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Ban13ActionPerformed(evt);
            }
        });

        jButton2.setText("QUẦY");

        Ban14.setText("14");
        Ban14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Ban14ActionPerformed(evt);
            }
        });

        Ban15.setText("15");
        Ban15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Ban15ActionPerformed(evt);
            }
        });

        jButton3.setText("WC");

        Ban16.setText("16");
        Ban16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Ban16ActionPerformed(evt);
            }
        });

        Ban17.setText("17");
        Ban17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Ban17ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panBanLayout = new javax.swing.GroupLayout(panBan);
        panBan.setLayout(panBanLayout);
        panBanLayout.setHorizontalGroup(
            panBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panBanLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panBanLayout.createSequentialGroup()
                        .addGroup(panBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Ban9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Ban5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Ban1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Ban13, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(panBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panBanLayout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(Ban10, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(Ban11, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panBanLayout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(Ban14, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(Ban15, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panBanLayout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(panBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Ban2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Ban6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(panBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(Ban3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(Ban7, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(panBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Ban16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Ban8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Ban4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Ban12, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panBanLayout.createSequentialGroup()
                        .addComponent(Ban17, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)
                        .addComponent(Ban18, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Ban19, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addComponent(Ban20, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panBanLayout.createSequentialGroup()
                        .addGroup(panBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(panBanLayout.createSequentialGroup()
                                .addComponent(Ban21, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24)
                                .addComponent(Ban22, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(Ban23, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(Ban24, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panBanLayout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel3)
                                .addGap(86, 86, 86)
                                .addGroup(panBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton2))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panBanLayout.setVerticalGroup(
            panBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panBanLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panBanLayout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addGroup(panBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Ban1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Ban2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Ban3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Ban4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Ban5, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Ban6, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Ban7, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Ban8, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Ban9, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Ban10, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Ban11, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Ban12, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Ban13, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Ban14, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Ban15, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Ban16, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Ban17, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Ban18, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Ban20, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Ban19, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Ban21, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Ban22, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Ban24, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Ban23, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panBanLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGroup(panBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panBanLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panBanLayout.createSequentialGroup()
                                .addGap(44, 44, 44)
                                .addComponent(jLabel3)))
                        .addGap(313, 313, 313)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblTrangThai.setText("--------------");

        btnXoa.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hình/Xóa.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(home, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(134, 134, 134))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(panBan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lblThanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel8)
                                            .addComponent(jLabel6))
                                        .addGap(23, 23, 23)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(tfTienNhanCuaKhach)
                                            .addComponent(lblTienThua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(lblTongTien)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnIn)
                                        .addGap(38, 38, 38)
                                        .addComponent(btnThanhToan))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(cbxTenSanPham, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(cbbLoai, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel9)
                                                    .addComponent(jLabel4))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(spnSoLuong, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                                                    .addComponent(lblGia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                        .addGap(30, 30, 30)
                                        .addComponent(btnThem)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNhanVien)
                            .addComponent(jLabel12)
                            .addComponent(lblNgay)
                            .addComponent(jLabel14)
                            .addComponent(lblThoiGian)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(18, 18, 18)
                                .addComponent(lblBan))
                            .addComponent(lblTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(btnXoa, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(19, 19, 19))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(17, 17, 17))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(home)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cbbLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cbxTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(lblGia))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(spnSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(lblThanhTien))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(57, 57, 57)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel11)
                                    .addComponent(lblBan))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel10)
                                .addGap(10, 10, 10)
                                .addComponent(lblNhanVien)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel12)
                                .addGap(11, 11, 11)
                                .addComponent(lblNgay)
                                .addGap(7, 7, 7)
                                .addComponent(jLabel14)
                                .addGap(9, 9, 9)
                                .addComponent(lblThoiGian))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(lblTongTien))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(tfTienNhanCuaKhach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(lblTienThua)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(lblTrangThai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnIn)
                            .addComponent(btnThanhToan))
                        .addGap(16, 16, 16))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void luuDL(){
        
            int tongTien = 0;
            dongTrongBangHoaDon = model.getRowCount();
            String[] dsTen = new String[dongTrongBangHoaDon];
            int[] dsSL = new int[dongTrongBangHoaDon];
            int[] dsThanhTien = new int[dongTrongBangHoaDon];

            for (int i = 0; i < dongTrongBangHoaDon; i++) {
                tongTien += Integer.parseInt(model.getValueAt(i, 2).toString());
                dsTen[i] = model.getValueAt(i, 0).toString();
                dsSL[i] = Integer.parseInt(model.getValueAt(i, 1).toString());
                dsThanhTien[i] = Integer.parseInt(model.getValueAt(i, 2).toString());
            }
            lblTongTien.setText(String.valueOf(tongTien));

            hoadon[sttBan] = new ClsHoaDon();

            hoadon[sttBan].setHoTenNV(lblNhanVien.getText());
            hoadon[sttBan].setBan(lblBan.getText());
            hoadon[sttBan].setNgay(lblNgay.getText());
            hoadon[sttBan].setThoigian(lblThoiGian.getText());
            hoadon[sttBan].setTenSP(dsTen);
            hoadon[sttBan].setSoLuong(dsSL);
            hoadon[sttBan].setTongTien(dsThanhTien);
            hoadon[sttBan].setTienTongCong(tongTien);
            
    }
    
    
    
    
    


    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    // Sự kiện cho button thêm
    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        try {
            
            them();
            luuDL();
//            int tongTien = 0;
//            dongTrongBangHoaDon = model.getRowCount();
//            String[] dsTen = new String[dongTrongBangHoaDon];
//            int[] dsSL = new int[dongTrongBangHoaDon];
//            int[] dsThanhTien = new int[dongTrongBangHoaDon];
//
//            for (int i = 0; i < dongTrongBangHoaDon; i++) {
//                tongTien += Integer.parseInt(model.getValueAt(i, 2).toString());
//                dsTen[i] = model.getValueAt(i, 0).toString();
//                dsSL[i] = Integer.parseInt(model.getValueAt(i, 1).toString());
//                dsThanhTien[i] = Integer.parseInt(model.getValueAt(i, 2).toString());
//            }
//            lblTongTien.setText(String.valueOf(tongTien));
//
//            hoadon[sttBan] = new ClsHoaDon();
//
//            hoadon[sttBan].setHoTenNV(lblNhanVien.getText());
//            hoadon[sttBan].setBan(lblBan.getText());
//            hoadon[sttBan].setNgay(lblNgay.getText());
//            hoadon[sttBan].setThoigian(lblThoiGian.getText());
//            hoadon[sttBan].setTenSP(dsTen);
//            hoadon[sttBan].setSoLuong(dsSL);
//            hoadon[sttBan].setTongTien(dsThanhTien);
//            hoadon[sttBan].setTienTongCong(tongTien);
            
//          hoadon[sttBan].setTienKhach(Integer.parseInt(tfTienNhanCuaKhach.getText()));
//          hoadon[sttBan].setTienThua(Integer.parseInt(lblTienThua.getText()));

            doimau();
            btnThanhToan.setEnabled(true);
            btnIn.setEnabled(true);
        } catch (Exception e) {
            lblTrangThai.setText("Chưa nhập thông tin gì");
        }
        
    }//GEN-LAST:event_btnThemActionPerformed

    // Sự kiện cho nút home
    private void homeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeActionPerformed
        FrmGiaoDienChinh home = new FrmGiaoDienChinh();
        this.setVisible(false);
        home.setVisible(true);
    }//GEN-LAST:event_homeActionPerformed

    // Sự kiện tải dữ liệu lên combobox Loại
    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        Loai();
    }//GEN-LAST:event_formWindowOpened

    // Sự kiện của nút thanh toán
    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        if(tfTienNhanCuaKhach.getText().equals("")) {lblTrangThai.setText("Chưa nhập tiền nhận");}
        if (tinh) {
            
            //Luu vao CSDL.
            luuThongKe();
            
            doimau();
            int n = JOptionPane.showConfirmDialog(null,"Bạn có muốn in hóa đơn","In hóa đơn",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(n == JOptionPane.YES_OPTION) {
                btnIn.doClick();
                
            }
            hoadon[sttBan] = null;
            Refresh();
        }
    }//GEN-LAST:event_btnThanhToanActionPerformed

    
    
    
    
    
    
    
    // Sự kiện khi chọn combobox Loại
    private void cbbLoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbLoaiActionPerformed
        String sp = cbbLoai.getSelectedItem().toString().trim();
        String sqlchon = "Select * from Menu where SPLoai like N'" + sp + "'";
        count = 0;
        try {
            kn();
            Statement d = conn.createStatement();
            rs = d.executeQuery(sqlchon);
            cbxTenSanPham.removeAllItems();
            while (rs.next()) {
                cbxTenSanPham.addItem(rs.getString("SPTen"));
            }
            count++;
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_cbbLoaiActionPerformed

    // Sự kiện khi chọn combobox Tên
    private void cbxTenSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxTenSanPhamActionPerformed
        if (count > 0) {
            String sp = cbxTenSanPham.getSelectedItem().toString().trim();
            String sqlchon = "Select SPGia from Menu where SPTen like N'%" + sp + "'";
            lblGia.setText("");
            try {
                kn();
                Statement d = conn.createStatement();
                rs = d.executeQuery(sqlchon);
                while (rs.next()) {
                    lblGia.setText(String.valueOf(rs.getInt("SPGia")));
                }
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }//GEN-LAST:event_cbxTenSanPhamActionPerformed

    // Sự kiện khi nhập tiền của khách đưa vào textfield để kiểm tra
    private void tfTienNhanCuaKhachKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfTienNhanCuaKhachKeyReleased
        int a = Integer.parseInt(tfTienNhanCuaKhach.getText().toString());
        int b = Integer.parseInt(lblTongTien.getText());
        try {
            if (a < b) {
                tinh = false;
                lblTrangThai.setText("Tiền nhận chưa đủ");
            } else {
                tinh = true;
                lblTrangThai.setText("OK");
                lblTienThua.setText(String.valueOf(a - b));
            }
        } catch (Exception e) {
            lblTrangThai.setText("Không hợp lệ");
        }

    }//GEN-LAST:event_tfTienNhanCuaKhachKeyReleased

    // Lay lai du lieu len bang Hoa Don
    private void taiLaiDuLieu() {
        if (hoadon[sttBan] != null) {
            String[] dsTen = hoadon[sttBan].getTenSP();
            int[] dsSL = hoadon[sttBan].getSoLuong();
            int[] dsThanhTien = hoadon[sttBan].getTongTien();
            for (int i = 0; i < hoadon[sttBan].getTenSP().length; i++) {
                model.addRow(new Object[]{
                    dsTen[i], dsSL[i], dsThanhTien[i]
                });
            }
            lblTongTien.setText(String.valueOf(hoadon[sttBan].getTienTongCong()));
        }
    }
    
    


    
    
    
    
    
    
    
    
    
    
    
    
    // Gán màu cho bàn
    private void doimau() {
        if(hoadon[sttBan] != null) {
            tenban.setBackground(Color.yellow);
        } else tenban.setBackground(null);
    }
    
    // Hàm in
    private void in(String file, String outPutName)   {
        //Tạo thư mục để lưu file outPut.
        String outputLoca = "C:\\nhattest\\";
        File locationOutput = new File(outputLoca);
        if(!locationOutput.exists())
            locationOutput.mkdir();
       
        //FileWriter fw = new FileWriter(output+outPutName+".txt")
        //fw.write(file);
        //FileOutputStream fileOutputStream = new FileOutputStream(output+outPutName+".txt")
        //fileOutputStream.write(file.getBytes());
       
        try {
           
            //Xuất ra file .txt ,khắc phục lỗi tiếng việt.
            File fileDir = new File(outputLoca + outPutName + ".txt");
            try (Writer out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(fileDir), "UTF8"))) {
                out.append(file);
                out.flush();
                out.close();
            }
           
            JOptionPane.showMessageDialog(null,
                    "In file thành công (" + outPutName + ".txt)");
           
        }
        catch (Exception e) {
           
            JOptionPane.showMessageDialog(null, "Lỗi in file !");
            System.out.println(e.getMessage());
        }
    }
            
    // Hàm định dạng hóa đơn
    private String dinhdanghoadon()   {
        Date dt = new Date();
        SimpleDateFormat d = new SimpleDateFormat("hh:mm dd:MM:yyyy");
        String gio = d.format(dt);
        
        String temp = "";
        temp += "         QUÁN CÀ PHÊ - TRÀ SỮA Nhật Lê\n\r"
                + "Giao Thông Vận Tải UTC2 \n"
                + "SĐT: 123456789\n\r"
                + "-----------------------------------------\n\r"
                + "            PHIÊU THANH TOÁN\n\r"
                + "Bàn số " + sttBan + "\n"
                + "STT  Tên món                  Số lượng  Thành Tiền\n";
                String[] tenmon = hoadon[sttBan].getTenSP();
                int[] soluong = hoadon[sttBan].getSoLuong();
                int[] thanhtien = hoadon[sttBan].getTongTien();
                for(int i=0;i<hoadon[sttBan].getSoLuong().length ;i++)
                {
                    String tam = String.format("%-6s%-28s%-10s%-15s", i+1,tenmon[i],soluong[i],thanhtien[i]);
                    temp += tam + "\n";
                }
        temp += "-------------------------------------------\n\r"
                + "Tông tiền: " +hoadon[sttBan].getTienTongCong() +"\n"
                + "Số tiền nhận: " +hoadon[sttBan].getTienKhach() + " vnd \n"
                + "Tiền thừa: " +hoadon[sttBan].getTienThua() + " vnd \n"
                + "Ngày " +gio +"\n"
                + "Nhân viên: " +hoadon[sttBan].getHoTenNV() +"\n"
                + "------------------------------------------\n"
                + "           Hẹn gặp lại quý khách\n"
                + "           Xin chân thành cảm ơn!";
        return temp;     
    }

    // Chuc nang tung ban
    private void Ban1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Ban1ActionPerformed
        // TODO add your handling code here:
        sttBan = 1;
        tenban = Ban1;
        lblBan.setText("1");
        Enabled();
        model.setRowCount(0);
        taiLaiDuLieu();
        tfTienNhanCuaKhach.setEnabled(true);
    }//GEN-LAST:event_Ban1ActionPerformed

    private void Ban2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Ban2ActionPerformed
        // TODO add your handling code here:
        sttBan = 2;
        tenban = Ban2;
        lblBan.setText("2");
        Enabled();
        model.setRowCount(0);
        taiLaiDuLieu();
        tfTienNhanCuaKhach.setEnabled(true);
    }//GEN-LAST:event_Ban2ActionPerformed

    private void Ban3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Ban3ActionPerformed
        // TODO add your handling code here:
        sttBan = 3;
        tenban = Ban3;
        lblBan.setText("3");
        Enabled();
        model.setRowCount(0);
        taiLaiDuLieu();
        tfTienNhanCuaKhach.setEnabled(true);
    }//GEN-LAST:event_Ban3ActionPerformed

    private void Ban4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Ban4ActionPerformed
        // TODO add your handling code here:
        sttBan = 4;
        tenban = Ban4;
        lblBan.setText("4");
        Enabled();
        model.setRowCount(0);
        taiLaiDuLieu();
        tfTienNhanCuaKhach.setEnabled(true);
    }//GEN-LAST:event_Ban4ActionPerformed

    private void Ban5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Ban5ActionPerformed
        // TODO add your handling code here:
        sttBan = 5;
        tenban = Ban5;
        lblBan.setText("5");
        Enabled();
        model.setRowCount(0);
        taiLaiDuLieu();
        tfTienNhanCuaKhach.setEnabled(true);
    }//GEN-LAST:event_Ban5ActionPerformed

    private void Ban6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Ban6ActionPerformed
        // TODO add your handling code here:
        sttBan = 6;
        tenban = Ban6;
        lblBan.setText("6");
        Enabled();
        model.setRowCount(0);
        taiLaiDuLieu();
        tfTienNhanCuaKhach.setEnabled(true);
    }//GEN-LAST:event_Ban6ActionPerformed

    private void Ban7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Ban7ActionPerformed
        // TODO add your handling code here:
        sttBan = 7;
        tenban = Ban7;
        lblBan.setText("7");
        Enabled();
        model.setRowCount(0);
        taiLaiDuLieu();
        tfTienNhanCuaKhach.setEnabled(true);
    }//GEN-LAST:event_Ban7ActionPerformed

    private void Ban8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Ban8ActionPerformed
        // TODO add your handling code here:
        sttBan = 8;
        tenban = Ban8;
        lblBan.setText("8");
        Enabled();
        model.setRowCount(0);
        taiLaiDuLieu();
        tfTienNhanCuaKhach.setEnabled(true);
    }//GEN-LAST:event_Ban8ActionPerformed

    private void Ban9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Ban9ActionPerformed
        // TODO add your handling code here:
        sttBan = 9;
        tenban = Ban9;
        lblBan.setText("9");
        Enabled();
        model.setRowCount(0);
        taiLaiDuLieu();
        tfTienNhanCuaKhach.setEnabled(true);
    }//GEN-LAST:event_Ban9ActionPerformed

    private void Ban10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Ban10ActionPerformed
        // TODO add your handling code here:
        sttBan = 10;
        tenban = Ban10;
        lblBan.setText("10");
        Enabled();
        model.setRowCount(0);
        taiLaiDuLieu();
        tfTienNhanCuaKhach.setEnabled(true);
    }//GEN-LAST:event_Ban10ActionPerformed

    private void Ban11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Ban11ActionPerformed
        // TODO add your handling code here:
        sttBan = 11;
        tenban = Ban11;
        lblBan.setText("11");
        Enabled();
        model.setRowCount(0);
        taiLaiDuLieu();
        tfTienNhanCuaKhach.setEnabled(true);
    }//GEN-LAST:event_Ban11ActionPerformed

    private void Ban12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Ban12ActionPerformed
        // TODO add your handling code here:
        sttBan = 12;
        tenban = Ban12;
        lblBan.setText("12");
        Enabled();
        model.setRowCount(0);
        taiLaiDuLieu();
        tfTienNhanCuaKhach.setEnabled(true);
    }//GEN-LAST:event_Ban12ActionPerformed

    private void Ban13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Ban13ActionPerformed
        // TODO add your handling code here:
        sttBan = 13;
        tenban = Ban13;
        lblBan.setText("13");
        Enabled();
        model.setRowCount(0);
        taiLaiDuLieu();
        tfTienNhanCuaKhach.setEnabled(true);
    }//GEN-LAST:event_Ban13ActionPerformed

    private void Ban14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Ban14ActionPerformed
        // TODO add your handling code here:
        sttBan = 14;
        tenban = Ban14;
        lblBan.setText("14");
        Enabled();
        model.setRowCount(0);
        taiLaiDuLieu();
        tfTienNhanCuaKhach.setEnabled(true);
    }//GEN-LAST:event_Ban14ActionPerformed

    private void Ban15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Ban15ActionPerformed
        // TODO add your handling code here:
        sttBan = 15;
        tenban = Ban15;
        lblBan.setText("15");
        Enabled();
        model.setRowCount(0);
        taiLaiDuLieu();
        tfTienNhanCuaKhach.setEnabled(true);
    }//GEN-LAST:event_Ban15ActionPerformed

    private void Ban16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Ban16ActionPerformed
        // TODO add your handling code here:
        sttBan = 16;
        tenban = Ban16;
        lblBan.setText("16");
        Enabled();
        model.setRowCount(0);
        taiLaiDuLieu();
        tfTienNhanCuaKhach.setEnabled(true);
    }//GEN-LAST:event_Ban16ActionPerformed

    private void Ban17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Ban17ActionPerformed
        // TODO add your handling code here:
        sttBan = 17;
        tenban = Ban17;
        lblBan.setText("17");
        Enabled();
        model.setRowCount(0);
        taiLaiDuLieu();
        tfTienNhanCuaKhach.setEnabled(true);
    }//GEN-LAST:event_Ban17ActionPerformed

    private void Ban18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Ban18ActionPerformed
        // TODO add your handling code here:
        sttBan = 18;
        tenban = Ban18;
        lblBan.setText("18");
        Enabled();
        model.setRowCount(0);
        taiLaiDuLieu();
        tfTienNhanCuaKhach.setEnabled(true);
    }//GEN-LAST:event_Ban18ActionPerformed

    private void Ban19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Ban19ActionPerformed
        // TODO add your handling code here:
        sttBan = 19;
        tenban = Ban19;
        lblBan.setText("19");
        Enabled();
        model.setRowCount(0);
        taiLaiDuLieu();
        tfTienNhanCuaKhach.setEnabled(true);
    }//GEN-LAST:event_Ban19ActionPerformed

    private void Ban20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Ban20ActionPerformed
        // TODO add your handling code here:
        sttBan = 20;
        tenban = Ban20;
        lblBan.setText("20");
        Enabled();
        model.setRowCount(0);
        taiLaiDuLieu();
        tfTienNhanCuaKhach.setEnabled(true);
    }//GEN-LAST:event_Ban20ActionPerformed

    private void Ban21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Ban21ActionPerformed
        // TODO add your handling code here:
        sttBan = 21;
        tenban = Ban21;
        lblBan.setText("21");
        Enabled();
        model.setRowCount(0);
        taiLaiDuLieu();
        tfTienNhanCuaKhach.setEnabled(true);
    }//GEN-LAST:event_Ban21ActionPerformed

    private void Ban22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Ban22ActionPerformed
        // TODO add your handling code here:
        sttBan = 22;
        tenban = Ban22;
        lblBan.setText("22");
        Enabled();
        model.setRowCount(0);
        taiLaiDuLieu();
        tfTienNhanCuaKhach.setEnabled(true);
    }//GEN-LAST:event_Ban22ActionPerformed

    private void Ban23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Ban23ActionPerformed
        // TODO add your handling code here:
        sttBan = 23;
        tenban = Ban23;
        lblBan.setText("23");
        Enabled();
        model.setRowCount(0);
        taiLaiDuLieu();
        tfTienNhanCuaKhach.setEnabled(true);
    }//GEN-LAST:event_Ban23ActionPerformed

    private void Ban24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Ban24ActionPerformed
        // TODO add your handling code here:
        sttBan = 24;
        tenban = Ban24;
        lblBan.setText("24");
        Enabled();
        model.setRowCount(0);
        taiLaiDuLieu();
        tfTienNhanCuaKhach.setEnabled(true);
    }//GEN-LAST:event_Ban24ActionPerformed

    private void btnInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInActionPerformed
        // TODO add your handling code here:
        Date dt = new Date();
        SimpleDateFormat d = new SimpleDateFormat("hh-mm-ss dd-MM-yyyy");
        String gio = d.format(dt);
        String nameOut = "BAN"+tenban.getText().trim() +"_("+gio+")".trim();
        System.out.println(nameOut);
        in(dinhdanghoadon(), nameOut);
    }//GEN-LAST:event_btnInActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        int click=JOptionPane.showConfirmDialog(null,
                "Bạn có chắc chắn muốn xóa sản phẩm này không?", 
                "Thông báo", 2);
        
        int idRow = bangHoaDon.getSelectedRow();
        
        String[] tenmon = hoadon[sttBan].getTenSP();
        int[] soluong = hoadon[sttBan].getSoLuong();
        int[] thanhtien = hoadon[sttBan].getTongTien();
        
        if(click==JOptionPane.YES_OPTION){
            model.removeRow(idRow);
            luuDL();
            
            model.setRowCount(0);
            taiLaiDuLieu();
        }   
        
    }//GEN-LAST:event_btnXoaActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmTrangThai().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Ban1;
    private javax.swing.JButton Ban10;
    private javax.swing.JButton Ban11;
    private javax.swing.JButton Ban12;
    private javax.swing.JButton Ban13;
    private javax.swing.JButton Ban14;
    private javax.swing.JButton Ban15;
    private javax.swing.JButton Ban16;
    private javax.swing.JButton Ban17;
    private javax.swing.JButton Ban18;
    private javax.swing.JButton Ban19;
    private javax.swing.JButton Ban2;
    private javax.swing.JButton Ban20;
    private javax.swing.JButton Ban21;
    private javax.swing.JButton Ban22;
    private javax.swing.JButton Ban23;
    private javax.swing.JButton Ban24;
    private javax.swing.JButton Ban3;
    private javax.swing.JButton Ban4;
    private javax.swing.JButton Ban5;
    private javax.swing.JButton Ban6;
    private javax.swing.JButton Ban7;
    private javax.swing.JButton Ban8;
    private javax.swing.JButton Ban9;
    private javax.swing.JTable bangHoaDon;
    private javax.swing.JButton btnIn;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cbbLoai;
    private javax.swing.JComboBox<String> cbxTenSanPham;
    private javax.swing.JButton home;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBan;
    private javax.swing.JLabel lblGia;
    private javax.swing.JLabel lblNgay;
    private javax.swing.JLabel lblNhanVien;
    private javax.swing.JLabel lblThanhTien;
    private javax.swing.JLabel lblThoiGian;
    private javax.swing.JLabel lblTienThua;
    private javax.swing.JLabel lblTongTien;
    private javax.swing.JLabel lblTrangThai;
    private java.awt.Panel panBan;
    private javax.swing.JSpinner spnSoLuong;
    private javax.swing.JTextField tfTienNhanCuaKhach;
    // End of variables declaration//GEN-END:variables
}
