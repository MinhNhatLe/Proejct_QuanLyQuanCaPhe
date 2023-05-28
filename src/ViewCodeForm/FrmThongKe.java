package ViewCodeForm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class FrmThongKe extends javax.swing.JFrame {
    
    private PreparedStatement ps;
    private Connection conn=null;
    private ResultSet rs=null;
    private DefaultTableModel model;
    
    private String sql = "Select * from ThongKe";
    
    private boolean them=false, thaydoi=false;
    private boolean namNhuan=false, nam=false, thang=false, ngay=false; 
    
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
    
    // Hàm xây dựng mặc nhiên - khởi tạo
    public FrmThongKe() {
        initComponents();
        this.setTitle("Thống kê doanh thu");
        setResizable(false);
        this.setLocationRelativeTo(null);
        kn();
        Date dt = new Date();
        //spnNam.setValue( Integer.parseInt(new SimpleDateFormat("yyyy").format(dt)) ) ;
        //spnNam.setValue( 2020 ) ;
        cboNam.setSelectedItem(new SimpleDateFormat("yyyy").format(dt).toString() );
        kiemTraNam(); 
        addDay();
        xem(sql);
        reset();
    }
    
    // Hàm lấy dữ liệu lên bảng
    private void xem(String sql){
        int i =1, count = 0;
        long tongTien=0;
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        try{
            kn();
            String[] arry={"STT","Bàn","Nhân viên bán","Ngày bán","Giờ bán","Tổng tiền","Tiền nhận của khách","Tiền thừa"};
            model=new DefaultTableModel(arry,0);
            bangThongKe.setModel(model);
            model = (DefaultTableModel)bangThongKe.getModel();
            ps = conn.prepareStatement(sql);
            rs= ps.executeQuery();
            model.setRowCount(0);  
            while(rs.next()){
                Vector vector=new Vector();
                vector.add(i++);
                vector.add(rs.getString("TKBan").trim());
                vector.add(rs.getString("NVHoTen").trim());
                vector.add(rs.getString("TKNgay").trim());
                vector.add(rs.getString("TKThoiGian").trim());
                vector.add(rs.getInt("TKTongTien"));
                vector.add(rs.getInt("TKTienKhach"));
                vector.add(rs.getInt("TKTienThua"));
                model.addRow(vector);
                tongTien += rs.getInt("TKTongTien");
                count++;
            }
            
            //bangThongKe.setModel(model);
            conn.close();
            lblTongHoaDon.setText(String.valueOf(count));
            lblTongDoanhThu.setText(formatter.format(tongTien)+" "+"VND");
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    // Hàm INIT COMPONENT
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        bangThongKe = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        home = new javax.swing.JButton();
        sdasd = new javax.swing.JLabel();
        sdas = new javax.swing.JLabel();
        rdbNgay = new javax.swing.JRadioButton();
        rdbThang = new javax.swing.JRadioButton();
        rdbNam = new javax.swing.JRadioButton();
        cboNgay = new javax.swing.JComboBox<>();
        cboThang = new javax.swing.JComboBox<>();
        btnTim = new javax.swing.JButton();
        lblTrangThai = new javax.swing.JLabel();
        btnQuayLai = new javax.swing.JButton();
        lblTongDoanhThu = new javax.swing.JLabel();
        lblTongHoaDon = new javax.swing.JLabel();
        cboNam = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        bangThongKe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(bangThongKe);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setText("THỐNG KÊ DOANH THU");

        home.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hình/home.jpg"))); // NOI18N
        home.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeActionPerformed(evt);
            }
        });

        sdasd.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        sdasd.setText("Tổng số hóa đơn đã bán: ");

        sdas.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        sdas.setText("Tổng doanh thu:");

        buttonGroup1.add(rdbNgay);
        rdbNgay.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        rdbNgay.setText("Xem theo ngày");
        rdbNgay.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rdbNgayStateChanged(evt);
            }
        });

        buttonGroup1.add(rdbThang);
        rdbThang.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        rdbThang.setText("Xem theo tháng");
        rdbThang.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rdbThangStateChanged(evt);
            }
        });
        rdbThang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbThangActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdbNam);
        rdbNam.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        rdbNam.setText("Xem theo năm");
        rdbNam.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rdbNamStateChanged(evt);
            }
        });

        cboThang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
        cboThang.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
                cboThangPopupMenuWillBecomeVisible(evt);
            }
        });
        cboThang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboThangActionPerformed(evt);
            }
        });

        btnTim.setText("Tìm");
        btnTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimActionPerformed(evt);
            }
        });

        lblTrangThai.setText("------------");

        btnQuayLai.setText("Hủy");
        btnQuayLai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuayLaiActionPerformed(evt);
            }
        });

        lblTongDoanhThu.setText("jLabel2");

        lblTongHoaDon.setText("jLabel2");

        cboNam.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 810, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(home, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(162, 162, 162)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(sdasd)
                                .addGap(18, 18, 18)
                                .addComponent(lblTongHoaDon)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(sdas)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblTongDoanhThu)
                                .addGap(131, 131, 131))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rdbNgay)
                                    .addComponent(cboNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(72, 72, 72)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cboThang, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(rdbThang))
                                .addGap(104, 104, 104)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rdbNam)
                                    .addComponent(cboNam, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTrangThai)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnTim)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnQuayLai)))))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(home)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(sdasd, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sdas, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTongDoanhThu)
                            .addComponent(lblTongHoaDon))
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnTim)
                            .addComponent(btnQuayLai))
                        .addGap(7, 7, 7)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdbNgay)
                    .addComponent(rdbThang)
                    .addComponent(rdbNam))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboNgay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboThang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTrangThai)
                    .addComponent(cboNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(64, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    // Hàm chuyển từ chuỗi sang số
    private long convertedToNumbers(String s){
        String number="";
        String []array=s.replace(","," ").split("\\s");
        for(String i:array){
            number=number.concat(i);
        }
        return Long.parseLong(number);
    }
    
    // Hàm kiểm tra năm có nhuận hay không
    private void kiemTraNam(){
        if(Integer.parseInt(String.valueOf(cboNam.getSelectedItem() )) % 4 == 0
                && Integer.parseInt(String.valueOf(cboNam.getSelectedItem() )) % 100 != 0 
                || Integer.parseInt(String.valueOf(cboNam.getSelectedItem() )) % 400 == 0 )
        {
            namNhuan=true;
        }
        else    namNhuan=false;
    }
    
    // Hàm reset lại các giá trị đã chọn
    private void reset(){
        nam=false;
        thang=false;
        ngay=false;
        cboNgay.setEnabled(false);
        cboThang.setEnabled(false);
        cboNam.setEnabled(false);
        cboThang.setSelectedIndex(0);
        cboNgay.setSelectedIndex(0);
    }
    
    // Hàm đánh dấu sự lựa chọn tìm kiếm thống kê
    private void chon(){
        //reset();
        if(rdbNam.isSelected()){
            cboNam.setEnabled(true);
            nam=true;
            thang=false;
            ngay=false;
        }
        else
        if(rdbThang.isSelected()){
            cboThang.setEnabled(true);
            cboNam.setEnabled(true);
            thang=true;
            nam=false;
            //thang=false;
            ngay=false;
        }
        else
        if(rdbNgay.isSelected()){
            cboNgay.setEnabled(true);
            cboThang.setEnabled(true);
            cboNam.setEnabled(true);
            ngay=true;
            nam=false;
            thang=false;
            //ngay=false;
        } 
    }
    
    // Thêm ngày vào combobox ngày
    private void addDay(){
        cboNgay.setEnabled(true);
        String []day={"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
        switch(Integer.parseInt(cboThang.getSelectedItem().toString())){
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                cboNgay.removeAllItems();
                for(String i:day){
                    cboNgay.addItem(i);
                }
                break;
            
            case 4:
            case 6:
            case 9:
            case 11:
                cboNgay.removeAllItems();
                for(int i=0;i<day.length-1;i++){
                    cboNgay.addItem(day[i]);
                }
                break;
            
            case 2:
                cboNgay.removeAllItems();
                if(namNhuan==true){
                    for(int i=0;i<day.length-2;i++){
                        cboNgay.addItem(day[i]);
                    }
                }
                else{
                    for(int i=0;i<day.length-3;i++){
                        cboNgay.addItem(day[i]);
                    }
                }
                break;
        }
    }
    
    // Hàm lấy ngày
//    private String getDay(String s){
//        
//    }
    
    // Hàm lấy tháng 
//    private String getMonth(String s){
//        String []arry=s.replace("/"," ").split("\\s");
//        return  Double.parseDouble(arry[arry.length-2]);
//    }
    
    // Hàm lấy năm
//    private String getYear(String s){
//        String []arry=s.replace("/"," ").split("\\s");
//        return  Double.parseDouble(arry[arry.length-1]);
//    }

    // Sự kiện của nút home
    private void homeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeActionPerformed
        FrmGiaoDienChinh home = new FrmGiaoDienChinh();
        this.setVisible(false);
        home.setVisible(true);
    }//GEN-LAST:event_homeActionPerformed

    // Sự kiện của nút tìm
    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimActionPerformed
       model.setRowCount(0);
        if(ngay==true)
            timTheoNgay();
        else
        if(thang==true)
            timTheoThang();
        else
        if(nam==true)
            timTheoNam();
        else lblTrangThai.setText("Bạn cần chọn phương thức tìm kiếm!");
    }//GEN-LAST:event_btnTimActionPerformed

    // Sự kiện của nút quay lại hủy các thao tác tìm kiếm
    private void btnQuayLaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuayLaiActionPerformed
        xem(sql);
    }//GEN-LAST:event_btnQuayLaiActionPerformed
   
    private void rdbNgayStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rdbNgayStateChanged
        chon();
        
    }//GEN-LAST:event_rdbNgayStateChanged

    private void rdbThangStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rdbThangStateChanged
        chon();
        cboNgay.setEnabled(false);
    }//GEN-LAST:event_rdbThangStateChanged

    private void rdbNamStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rdbNamStateChanged
        chon();
        cboThang.setEnabled(false);
        cboNam.setEnabled(false);
    }//GEN-LAST:event_rdbNamStateChanged

    private void cboThangPopupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cboThangPopupMenuWillBecomeVisible
        kiemTraNam();
        if(ngay==true)   addDay();
        else    return;
    }//GEN-LAST:event_cboThangPopupMenuWillBecomeVisible

    private void cboThangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboThangActionPerformed
        addDay();
    }//GEN-LAST:event_cboThangActionPerformed

    private void rdbThangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbThangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdbThangActionPerformed

    // Hàm tìm kiếm thông kê theo ngày
    private void timTheoNgay(){
        String ngayString = cboNgay.getSelectedItem().toString().trim();
        if(Integer.parseInt(ngayString ) < 10)
            ngayString = "0"+cboNgay.getSelectedItem().toString().trim();
        
        String thangString = cboThang.getSelectedItem().toString().trim();
        if( Integer.parseInt(thangString ) < 10 )
            thangString = "0"+cboThang.getSelectedItem().toString().trim();
        
        String temp =ngayString + "/" + 
                    thangString + "/"
                    + cboNam.getSelectedItem().toString().trim();
        System.out.println(temp);
        
        int i = 1,count=0;
        int tongTien=0;
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        try{
            kn();
            String[] arry={"STT","Bàn","Nhân viên bán","Ngày bán","Giờ bán","Tổng tiền","Tiền nhận của khách","Tiền thừa"};
             model=new DefaultTableModel(arry,0);
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                if(rs.getString("TKNgay").trim().equals(temp.trim()) ){
                    //System.out.println(rs.getString("TKNgay").trim());
                    //System.out.println(temp);
                    Vector vector=new Vector();
                    vector.add(i++);
                    vector.add(rs.getString("TKBan").trim());
                    vector.add(rs.getString("NVHoTen").trim());
                    vector.add(rs.getString("TKNgay"));
                    vector.add(rs.getString("TKThoiGian").trim());
                    vector.add(rs.getInt("TKTongTien"));
                    vector.add(rs.getInt("TKTienKhach"));
                    vector.add(rs.getInt("TKTienThua"));
                    model.addRow(vector);
                    System.out.println(vector);
                    tongTien += rs.getInt("TKTongTien");
                    count++;
                }
            }
            bangThongKe.setModel(model);
            conn.close();
            lblTongHoaDon.setText(String.valueOf(count));
            lblTongDoanhThu.setText(formatter.format(tongTien)+" "+"VND");
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    // Hàm tìm kiếm thống kê theo tháng
    private void timTheoThang(){
        int i = 1, count=0;
        int tongTien=0;
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        try{
            kn();
            String[] arry={"STT","Bàn","Nhân viên bán","Ngày bán","Giờ bán","Tổng tiền","Tiền nhận của khách","Tiền thừa"};
            model=new DefaultTableModel(arry,0);
            
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            String temp = cboThang.getSelectedItem().toString().trim() + "/"
                    + cboNam.getSelectedItem().toString().trim();
            System.out.println(temp);
            
            while(rs.next()){
                String thangNam = rs.getString("TKNgay").trim().substring(3,10);
                System.out.println(thangNam);
                if( thangNam.equals(temp) ){
                    Vector vector=new Vector();
                    vector.add(i++);
                    vector.add(rs.getString("TKBan").trim());
                    vector.add(rs.getString("NVHoTen").trim());
                    vector.add(rs.getString("TKNgay"));
                    vector.add(rs.getString("TKThoiGian").trim());
                    vector.add(rs.getInt("TKTongTien"));
                    vector.add(rs.getInt("TKTienKhach"));
                    vector.add(rs.getInt("TKTienThua"));
                    model.addRow(vector);
                    tongTien += rs.getInt("TKTongTien");
                    count++;
                }
            }
            bangThongKe.setModel(model);
            conn.close();
            lblTongHoaDon.setText(String.valueOf(count));
            lblTongDoanhThu.setText(formatter.format(tongTien)+" "+"VND");
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    // Hàm tìm kiếm thống kê theo năm
    private void  timTheoNam(){
        int i = 1, count=0;
        int tongTien=0;
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        try{
            kn();
            String[] arry={"STT","Bàn","Nhân viên bán","Ngày bán","Giờ bán","Tổng tiền","Tiền nhận của khách","Tiền thừa"};
            model=new DefaultTableModel(arry,0);
            
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            String nam = cboNam.getSelectedItem().toString().trim();
            

            
            while(rs.next()){
                String thangNam = rs.getString("TKNgay").trim().substring(6,10);
                System.out.println(thangNam);
                if( nam.equals(thangNam)  ){
                    Vector vector=new Vector();
                    vector.add(i++);
                    vector.add(rs.getString("TKBan").trim());
                    vector.add(rs.getString("NVHoTen").trim());
                    vector.add(rs.getString("TKNgay"));
                    vector.add(rs.getString("TKThoiGian").trim());
                    vector.add(rs.getInt("TKTongTien"));
                    vector.add(rs.getInt("TKTienKhach"));
                    vector.add(rs.getInt("TKTienThua"));
                    model.addRow(vector);
                    tongTien += rs.getInt("TKTongTien");
                    count++;
                }
            }
            bangThongKe.setModel(model);
            conn.close();
            lblTongHoaDon.setText(String.valueOf(count));
            lblTongDoanhThu.setText(formatter.format(tongTien)+" "+"VND");
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    // Hàm main
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmThongKe().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable bangThongKe;
    private javax.swing.JButton btnQuayLai;
    private javax.swing.JButton btnTim;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboNam;
    private javax.swing.JComboBox<String> cboNgay;
    private javax.swing.JComboBox<String> cboThang;
    private javax.swing.JButton home;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTongDoanhThu;
    private javax.swing.JLabel lblTongHoaDon;
    private javax.swing.JLabel lblTrangThai;
    private javax.swing.JRadioButton rdbNam;
    private javax.swing.JRadioButton rdbNgay;
    private javax.swing.JRadioButton rdbThang;
    private javax.swing.JLabel sdas;
    private javax.swing.JLabel sdasd;
    // End of variables declaration//GEN-END:variables
}
