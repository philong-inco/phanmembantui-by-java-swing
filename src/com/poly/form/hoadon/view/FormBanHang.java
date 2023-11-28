package com.poly.form.hoadon.view;

import com.formdev.flatlaf.FlatClientProperties;
import com.poly.form.hoadon.entity.BienTheSearch;
import com.poly.form.hoadon.entity.GioHang;
import com.poly.form.hoadon.entity.HoaDon;
import com.poly.form.hoadon.entity.HoaDonDTO;
import com.poly.form.hoadon.entity.KhachHangSearch;
import com.poly.form.hoadon.service.HoaDonService;
import com.poly.form.thuoctinh.entity.ThuocTinhMau;
import static com.poly.util.ph31848.Validate.checkEmpty;
import static com.poly.util.ph31848.Validate.checkNumber;
import static com.poly.util.ph31848.Validate.checkFloat;
import static com.poly.util.ph31848.Validate.checkChar;
import com.poly.form.thuoctinh.entity.ThuocTinhMauDTO;
import com.poly.form.thuoctinh.service.ThuocTinhMauService;
import static com.poly.util.ph31848.ConvertDateToLong.getLongTimeHienTai;
import static com.poly.util.ph31848.MaRandom.renderMa;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import raven.toast.Notifications;

public class FormBanHang extends javax.swing.JPanel {
    
    private DefaultTableModel dtmHoaDon;
    private DefaultTableModel dtmGioHang;
    private DefaultTableModel dtmBienThe;
    
    private HoaDonService serviceHoaDon;
    private ThuocTinhMauService serviceMau;
    
    private List<HoaDonDTO> listHoaDon;
    private List<GioHang> listGioHang;
    private List<BienTheSearch> listBienThe;
    private List<ThuocTinhMauDTO> listMau;
    private KhachHangSearch thongTinKhachHang;
    
    public FormBanHang() {
        initComponents();
        lb.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h1.font");
        dtmBienThe = (DefaultTableModel) tblBienThe.getModel();
        dtmHoaDon = (DefaultTableModel) tblHoaDon.getModel();
        dtmGioHang = (DefaultTableModel) tblGioHang.getModel();
        dtmBienThe = (DefaultTableModel) tblBienThe.getModel();
        serviceHoaDon = new HoaDonService();
        serviceMau = new ThuocTinhMauService();
        setDefaulView();
    }
    
    public void setDefaulView() {
        cboHinhThucGiaoHang.setSelectedIndex(2);
        cboTrangThaiHoaDon.setSelectedIndex(7);
        txtMaHoaDon.setEnabled(false);
        txtTenNhanVien.setEnabled(false);
        txtTongTien.setEnabled(false);
        txtTongTienGiam.setEnabled(false);
        txtTongTienThanhToan.setEnabled(false);
        txtTienKhachChuyenKhoan.setEnabled(false);
        txtSDTKhachHang.setEnabled(false);
        txtHoTenKhachHang.setEnabled(false);
        
        loadComboBoxMau();
        listBienThe = serviceHoaDon.searchSanPham(null, null);
        showTableBienThe(listBienThe);
    }
    
    public void resertTableHoaDon(String type) {
        Long timeHienTai = getLongTimeHienTai();
        Integer ht = cboHinhThucGiaoHang.getSelectedIndex();
        if (ht == 2) {
            ht = null;
        }
        Integer tt = cboTrangThaiHoaDon.getSelectedIndex();
        if (tt == 7) {
            tt = null;
        }
        if (type.contains("all")) {
            listHoaDon = serviceHoaDon.getAll();
        } else if (type.contains("search")) {
            listHoaDon = serviceHoaDon.getAllSearch(ht, tt);
        }
        showTableHoaDon(listHoaDon);
        System.out.println(listHoaDon);
    }
    
    public void resertTableBienThe() {
        String key = txtSearchSanPham.getText().trim();
        if (key.equals("")) {
            key = null;
        }
        String mau = cboMau.getSelectedItem() + "";
        if (mau.equalsIgnoreCase("Tất cả")) {
            mau = null;
        }
        listBienThe = serviceHoaDon.searchSanPham(key, mau);
        showTableBienThe(listBienThe);
    }
    
    public void showTableHoaDon(List<HoaDonDTO> list) {
        dtmHoaDon.setRowCount(0);
        for (HoaDonDTO hd : list) {
            dtmHoaDon.addRow(hd.toDataRow());
        }
    }
    
    public void showTableBienThe(List<BienTheSearch> list) {
        dtmBienThe.setRowCount(0);
        for (BienTheSearch bt : list) {
            dtmBienThe.addRow(bt.toDataRow());
        }
    }
    
    public void loadComboBoxMau() {
        cboMau.removeAllItems();
        listMau = serviceMau.getAll();
        cboMau.addItem("Tất cả");
        for (ThuocTinhMauDTO mau : listMau) {
            cboMau.addItem(mau.getTenMau());
        }
        cboMau.setSelectedIndex(0);
    }
    
    public void fillData(int index) {
        int indexTrangThaiHD = cboTrangThaiHoaDon.getSelectedIndex();
        HoaDonDTO hd = listHoaDon.get(index);
        txtMaHoaDon.setText(hd.getMaHoaDon());
        txtTenNhanVien.setText(hd.getTenNhanVien());
        txtTongTien.setText(hd.getTongTienHoaDon() + "");
        txtTongTienGiam.setText(hd.getTongTienSauKhuyenMai() + "");
        txtTongTienThanhToan.setText(hd.getTongTienPhaiTra() + "");
        txtTienKhachDua.setText(hd.getTienMatKhachTra() + "");
        txtTienKhachChuyenKhoan.setText(hd.getTongTienPhaiTra() - hd.getTienMatKhachTra() + "");
        txtSDTKhachHang.setText(hd.getMaKhachHang());
        txtHoTenKhachHang.setText(hd.getHoTenKhachHang());
        // nếu hóa đơn chờ thanh toán thì tính auto bằng SQL
        if (indexTrangThaiHD == 0) {
            reloadTongTienHoaDon(hd.getIdHoaDon());
        }
    }
    
    public void showTableGioHang(List<GioHang> list) {
        dtmGioHang.setRowCount(0);
        for (GioHang giohang : list) {
            dtmGioHang.addRow(giohang.toDataRow());
        }
    }
    
    public void setKhachHangForHoaDon(Long id) {
        thongTinKhachHang = serviceHoaDon.findByID(id);
        if (thongTinKhachHang != null) {
            txtSDTKhachHang.setText(thongTinKhachHang.getSdt());
            txtHoTenKhachHang.setText(thongTinKhachHang.getHoTen());
        }
    }
    
    public void reloadTongTienHoaDon(Long idHoaDon) {
        Float tongTien = serviceHoaDon.getTongTienHoaDonById(idHoaDon);
        Float tongTienGiam = serviceHoaDon.getTienGiamHoaDonById(idHoaDon);
        Float tongThanhToan = serviceHoaDon.getTienThanhToanHoaDonById(idHoaDon);
        System.out.println("kết quả:" + tongTien + tongTienGiam + tongThanhToan);
        txtTongTien.setText(tongTien + "");
        txtTongTienGiam.setText(tongTienGiam + "");
        txtTongTienThanhToan.setText(tongThanhToan + "");
        txtTienKhachDua.setText(txtTongTien.getText());
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lb = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        cboTrangThaiHoaDon = new javax.swing.JComboBox<>();
        cboHinhThucGiaoHang = new javax.swing.JComboBox<>();
        btnThem = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        pnlWebcam = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        txtSDTKhachHang = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtHoTenKhachHang = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        btnChonKhachHang = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        txtMaHoaDon = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtTenNhanVien = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtTongTien = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txtTongTienGiam = new javax.swing.JTextField();
        txtTongTienThanhToan = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        txtTienKhachDua = new javax.swing.JTextField();
        cbxHTTT = new javax.swing.JComboBox<>();
        jLabel26 = new javax.swing.JLabel();
        txtTienKhachChuyenKhoan = new javax.swing.JTextField();
        btnLuaHoaDon = new javax.swing.JButton();
        txtGhiChu = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtSDTKHDatHang = new javax.swing.JTextField();
        txtDiaChiDatHang = new javax.swing.JTextField();
        btnChonKhachHangDatHang = new javax.swing.JButton();
        txtTenNguoiShipDatHang = new javax.swing.JTextField();
        txtSDTNguoiShipDatHang = new javax.swing.JTextField();
        btnChonNhanVienShip = new javax.swing.JButton();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        txtHoTenKHDatHang1 = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        txtHoTenKHDatHang = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        txtHoTenKHDatHang2 = new javax.swing.JTextField();
        txtHoTenKHDatHang3 = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        txtHoTenKHDatHang4 = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        txtHoTenKHDatHang6 = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        txtHoTenKHDatHang7 = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        txtHoTenKHDatHang8 = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblGioHang = new javax.swing.JTable();
        jButton6 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        txtSearchSanPham = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cboMau = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblBienThe = new javax.swing.JTable();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lb.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb.setText("Bán hàng");
        add(lb, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 563, -1));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 16))); // NOI18N

        tblHoaDon.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã HĐ", "Nhân viên", "Khách hàng", "Hình thức", "Trạng thái", "Tổng SP"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHoaDon.setSelectionBackground(new java.awt.Color(235, 235, 235));
        tblHoaDon.getTableHeader().setReorderingAllowed(false);
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblHoaDon);

        cboTrangThaiHoaDon.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chờ thanh toán", "Đã thanh toán", "Đã hủy", "Chờ giao hàng", "Đang giao", "Đã giao", "Khách hẹn lại", "Tất cả" }));
        cboTrangThaiHoaDon.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboTrangThaiHoaDonItemStateChanged(evt);
            }
        });
        cboTrangThaiHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTrangThaiHoaDonActionPerformed(evt);
            }
        });

        cboHinhThucGiaoHang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tại quầy", "Đặt hàng", "Tất cả" }));
        cboHinhThucGiaoHang.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboHinhThucGiaoHangItemStateChanged(evt);
            }
        });
        cboHinhThucGiaoHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboHinhThucGiaoHangActionPerformed(evt);
            }
        });

        btnThem.setText("Tạo");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnThem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
                .addComponent(cboTrangThaiHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboHinhThucGiaoHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboTrangThaiHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboHinhThucGiaoHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThem))
                .addGap(9, 9, 9)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE))
        );

        add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 26, -1, -1));

        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Quét mã vạch sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 16))); // NOI18N

        pnlWebcam.setBackground(new java.awt.Color(255, 255, 255));
        pnlWebcam.setMaximumSize(new java.awt.Dimension(50, 50));
        pnlWebcam.setMinimumSize(new java.awt.Dimension(50, 50));
        pnlWebcam.setPreferredSize(new java.awt.Dimension(50, 50));
        pnlWebcam.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlWebcam, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnlWebcam, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(139, 139, 139))
        );

        add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(402, 26, -1, 185));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Đơn hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 16))); // NOI18N

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin khách hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 16))); // NOI18N

        txtSDTKhachHang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSDTKhachHang.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));
        txtSDTKhachHang.setSelectionColor(new java.awt.Color(140, 140, 140));

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel14.setText("SĐT:");

        txtHoTenKhachHang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtHoTenKhachHang.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));
        txtHoTenKhachHang.setSelectionColor(new java.awt.Color(140, 140, 140));

        jLabel17.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel17.setText("Tên KH:");

        btnChonKhachHang.setText("Chọn");
        btnChonKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonKhachHangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addComponent(jLabel14))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtSDTKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                    .addComponent(txtHoTenKhachHang))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnChonKhachHang)
                .addGap(20, 20, 20))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel14)
                            .addComponent(txtSDTKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(txtHoTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(btnChonKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 16))); // NOI18N

        jLabel18.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel18.setText("Mã HD:");

        txtMaHoaDon.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMaHoaDon.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));
        txtMaHoaDon.setSelectionColor(new java.awt.Color(140, 140, 140));

        jLabel19.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel19.setText("Tên nhân viên:");

        txtTenNhanVien.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTenNhanVien.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));
        txtTenNhanVien.setSelectionColor(new java.awt.Color(140, 140, 140));

        jLabel20.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel20.setText("Tổng tiền:");

        txtTongTien.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTongTien.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));
        txtTongTien.setSelectionColor(new java.awt.Color(140, 140, 140));

        jLabel21.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel21.setText("Giảm giá khuyến mại:");

        txtTongTienGiam.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTongTienGiam.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));
        txtTongTienGiam.setSelectionColor(new java.awt.Color(140, 140, 140));

        txtTongTienThanhToan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTongTienThanhToan.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));
        txtTongTienThanhToan.setSelectionColor(new java.awt.Color(140, 140, 140));

        jLabel22.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel22.setText("Thanh toán (Số):");

        jLabel23.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel23.setText("Bằng chữ:");

        jLabel4.setText("10.000.000 vnd");

        jLabel24.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel24.setText("Hình thức thanh toán:");

        jLabel25.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel25.setText("Tiền khách đưa:");

        txtTienKhachDua.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTienKhachDua.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));
        txtTienKhachDua.setSelectionColor(new java.awt.Color(140, 140, 140));
        txtTienKhachDua.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTienKhachDuaKeyReleased(evt);
            }
        });

        cbxHTTT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tiền mặt", "Chuyển khoản", "Kết hợp" }));
        cbxHTTT.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxHTTTItemStateChanged(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel26.setText("Tiền khách CK:");

        txtTienKhachChuyenKhoan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTienKhachChuyenKhoan.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));
        txtTienKhachChuyenKhoan.setSelectionColor(new java.awt.Color(140, 140, 140));

        btnLuaHoaDon.setText("Lưu");
        btnLuaHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuaHoaDonActionPerformed(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel28.setText("Ghi chú:");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(jLabel20)
                            .addComponent(jLabel21)
                            .addComponent(jLabel22)
                            .addComponent(jLabel19)
                            .addComponent(jLabel23)
                            .addComponent(jLabel25))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTienKhachDua)
                            .addComponent(txtTongTien)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                                        .addComponent(txtTongTienThanhToan, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                                        .addComponent(txtMaHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtTenNhanVien))
                                    .addComponent(txtTongTienGiam, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel24)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbxHTTT, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel26)
                                    .addComponent(jLabel28))
                                .addGap(48, 48, 48)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addComponent(btnLuaHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(txtTienKhachChuyenKhoan)
                                    .addComponent(txtGhiChu))))
                        .addContainerGap())))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel18)
                    .addComponent(txtMaHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addComponent(txtTenNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20)
                    .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21)
                    .addComponent(txtTongTienGiam, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22)
                    .addComponent(txtTongTienThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(cbxHTTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel25)
                    .addComponent(txtTienKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(txtTienKhachChuyenKhoan, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtGhiChu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28))
                .addGap(18, 18, 18)
                .addComponent(btnLuaHoaDon)
                .addGap(12, 12, 12))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(10, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(106, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Tại quầy", jPanel4);

        jPanel19.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin người nhận và người ship", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 15))); // NOI18N

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel13.setText("Ng/nhận:");

        jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel15.setText("SDT:");

        jLabel16.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel16.setText("Địa chỉ:");

        txtSDTKHDatHang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSDTKHDatHang.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));

        txtDiaChiDatHang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtDiaChiDatHang.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));

        btnChonKhachHangDatHang.setBackground(new java.awt.Color(153, 204, 255));
        btnChonKhachHangDatHang.setText("Chọn");
        btnChonKhachHangDatHang.setToolTipText("Chọn khách hàng");
        btnChonKhachHangDatHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonKhachHangDatHangActionPerformed(evt);
            }
        });

        txtTenNguoiShipDatHang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTenNguoiShipDatHang.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));

        txtSDTNguoiShipDatHang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSDTNguoiShipDatHang.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));

        btnChonNhanVienShip.setBackground(new java.awt.Color(153, 204, 255));
        btnChonNhanVienShip.setToolTipText("Chọn nhân viên ship hàng");
        btnChonNhanVienShip.setMargin(new java.awt.Insets(2, 7, 2, 7));
        btnChonNhanVienShip.setMaximumSize(new java.awt.Dimension(44, 42));
        btnChonNhanVienShip.setMinimumSize(new java.awt.Dimension(44, 42));
        btnChonNhanVienShip.setPreferredSize(new java.awt.Dimension(44, 42));
        btnChonNhanVienShip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonNhanVienShipActionPerformed(evt);
            }
        });

        jLabel32.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel32.setText("Ng/ship:");

        jLabel33.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel33.setText("SDT:");

        txtHoTenKHDatHang1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtHoTenKHDatHang1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));
        txtHoTenKHDatHang1.setSelectionColor(new java.awt.Color(140, 140, 140));

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(jLabel15)
                            .addComponent(jLabel32)
                            .addComponent(jLabel33))
                        .addGap(6, 6, 6))
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(16, 16, 16)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDiaChiDatHang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                            .addComponent(txtTenNguoiShipDatHang)
                            .addComponent(txtSDTNguoiShipDatHang)
                            .addComponent(txtSDTKHDatHang))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnChonKhachHangDatHang, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnChonNhanVienShip, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(txtHoTenKHDatHang1))
                .addContainerGap())
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtSDTKHDatHang, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel33))
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtHoTenKHDatHang1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel32)
                    .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel19Layout.createSequentialGroup()
                            .addComponent(btnChonKhachHangDatHang, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(11, 11, 11)
                            .addComponent(btnChonNhanVienShip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel19Layout.createSequentialGroup()
                            .addGap(29, 29, 29)
                            .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel16)
                                .addComponent(txtDiaChiDatHang, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtTenNguoiShipDatHang, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSDTNguoiShipDatHang, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 16))); // NOI18N

        jLabel34.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel34.setText("SDT:");

        txtHoTenKHDatHang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtHoTenKHDatHang.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));

        jLabel35.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel35.setText("Tên Nhân viên:");

        txtHoTenKHDatHang2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtHoTenKHDatHang2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));

        txtHoTenKHDatHang3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtHoTenKHDatHang3.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));
        txtHoTenKHDatHang3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHoTenKHDatHang3ActionPerformed(evt);
            }
        });

        jLabel36.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel36.setText("Tổng tiền:");

        txtHoTenKHDatHang4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtHoTenKHDatHang4.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));

        jLabel37.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel37.setText("Giảm giá:");

        jLabel39.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel39.setText("Thanh toán:");

        txtHoTenKHDatHang6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtHoTenKHDatHang6.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));

        jLabel40.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel40.setText("Mong muốn:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel41.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel41.setText("Hình thức thanh toán:");

        jLabel42.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel42.setText("Trạng thái thanh toán");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        txtHoTenKHDatHang7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtHoTenKHDatHang7.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));

        jLabel43.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel43.setText("Tiền khách đưa:");

        txtHoTenKHDatHang8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtHoTenKHDatHang8.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));

        jLabel44.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel44.setText("Tiền khách chuyển khoản:");

        jButton2.setText("jButton2");

        jButton3.setText("jButton3");

        jButton4.setText("jButton4");

        jButton5.setText("jButton5");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtHoTenKHDatHang7, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jComboBox3, javax.swing.GroupLayout.Alignment.TRAILING, 0, 150, Short.MAX_VALUE))))
                        .addGap(59, 59, 59))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel44)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtHoTenKHDatHang8))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(jButton2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton5))
                                    .addComponent(jLabel43)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel35)
                                            .addComponent(jLabel36)
                                            .addComponent(jLabel37)
                                            .addComponent(jLabel39)
                                            .addComponent(jLabel40)
                                            .addComponent(jLabel34))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtHoTenKHDatHang)
                                            .addComponent(txtHoTenKHDatHang2)
                                            .addComponent(jComboBox1, 0, 221, Short.MAX_VALUE)
                                            .addComponent(txtHoTenKHDatHang6)
                                            .addComponent(txtHoTenKHDatHang4)
                                            .addComponent(txtHoTenKHDatHang3))))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(39, 39, 39))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(txtHoTenKHDatHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtHoTenKHDatHang2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(13, 13, 13)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel36)
                    .addComponent(txtHoTenKHDatHang3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtHoTenKHDatHang4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(13, 13, 13)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel39)
                    .addComponent(txtHoTenKHDatHang6, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel43)
                    .addComponent(txtHoTenKHDatHang7, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel44)
                    .addComponent(txtHoTenKHDatHang8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(jButton5))
                .addGap(12, 12, 12))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 24, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Đặt hàng", jPanel2);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 648, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 0, 400, 630));

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Giỏ hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 14))); // NOI18N

        tblGioHang.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        tblGioHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã SP", "Tên SP", "Màu", "Số lượng", "Giá bán", "Giảm giá", "Thành tiền"
            }
        ));
        jScrollPane1.setViewportView(tblGioHang);

        jButton6.setText("clear");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton6)
                .addContainerGap())
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 534, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jButton6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(9, Short.MAX_VALUE))
        );

        add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 223, -1, -1));

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 14))); // NOI18N

        txtSearchSanPham.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSearchSanPham.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchSanPhamKeyReleased(evt);
            }
        });

        jLabel1.setText("Tìm kiếm");

        jLabel2.setText("Màu sắc:");

        cboMau.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cboMau.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboMau.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboMauItemStateChanged(evt);
            }
        });

        tblBienThe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã SP", "Tên SP", "Màu", "Tồn kho", "Giá bán", "Giảm giá"
            }
        ));
        jScrollPane2.setViewportView(tblBienThe);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(txtSearchSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboMau, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(72, 72, 72))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 537, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cboMau, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSearchSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 406, 544, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked
        int index = tblHoaDon.getSelectedRow();
        fillData(index);
        Long idHoaDon = listHoaDon.get(index).getIdHoaDon();
        listGioHang = serviceHoaDon.getAllBienTheByIdHoaDon(idHoaDon);
        showTableGioHang(listGioHang);

    }//GEN-LAST:event_tblHoaDonMouseClicked

    private void cboTrangThaiHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTrangThaiHoaDonActionPerformed

    }//GEN-LAST:event_cboTrangThaiHoaDonActionPerformed

    private void cboHinhThucGiaoHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboHinhThucGiaoHangActionPerformed

    }//GEN-LAST:event_cboHinhThucGiaoHangActionPerformed

    private void btnChonKhachHangDatHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonKhachHangDatHangActionPerformed

    }//GEN-LAST:event_btnChonKhachHangDatHangActionPerformed

    private void btnChonNhanVienShipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonNhanVienShipActionPerformed
        

    }//GEN-LAST:event_btnChonNhanVienShipActionPerformed

    private void cboTrangThaiHoaDonItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboTrangThaiHoaDonItemStateChanged
        System.out.println("bắt sự kiện trạng thái");
        if (cboTrangThaiHoaDon.getSelectedIndex() == 7 && cboHinhThucGiaoHang.getSelectedIndex() == 2) {
            resertTableHoaDon("all");
            System.out.println("event all");
        } else
            resertTableHoaDon("search");
    }//GEN-LAST:event_cboTrangThaiHoaDonItemStateChanged

    private void cboHinhThucGiaoHangItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboHinhThucGiaoHangItemStateChanged
        System.out.println("bắt sự kiện hình thức");
        if (cboTrangThaiHoaDon.getSelectedIndex() == 7 && cboHinhThucGiaoHang.getSelectedIndex() == 2) {
            resertTableHoaDon("all");
            System.out.println("event all");
        } else
            resertTableHoaDon("search");
    }//GEN-LAST:event_cboHinhThucGiaoHangItemStateChanged

    private void txtHoTenKHDatHang3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHoTenKHDatHang3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHoTenKHDatHang3ActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        if (JOptionPane.showConfirmDialog(this, "Tạo hóa đơn mới?", "Xác nhận", JOptionPane.YES_NO_OPTION) == 0) {
            String maHD = "";
            do {
                maHD = "HD" + renderMa();
            } while (serviceHoaDon.isExistMa(maHD));
            HoaDon hd = new HoaDon(1L, maHD, 0);
            serviceHoaDon.insertHoaDon(hd);
            cboTrangThaiHoaDon.setSelectedIndex(0);
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void txtSearchSanPhamKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchSanPhamKeyReleased
        resertTableBienThe();
    }//GEN-LAST:event_txtSearchSanPhamKeyReleased

    private void cboMauItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboMauItemStateChanged
        resertTableBienThe();
    }//GEN-LAST:event_cboMauItemStateChanged

    private void btnChonKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonKhachHangActionPerformed
        new FormKhachHangSearch(this).setVisible(true);
    }//GEN-LAST:event_btnChonKhachHangActionPerformed

    private void cbxHTTTItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxHTTTItemStateChanged
        int indexSelectThanhToan = cbxHTTT.getSelectedIndex();
        switch (indexSelectThanhToan) {
            
            case 0:
                txtTienKhachDua.setText(txtTongTienThanhToan.getText());
                txtTienKhachChuyenKhoan.setText("0");
                txtTienKhachDua.setEnabled(false);
                break;
            case 1:
                txtTienKhachDua.setText("0");
                txtTienKhachChuyenKhoan.setText(txtTongTienThanhToan.getText());
                break;
            case 2:
                txtTienKhachDua.setEnabled(true);
                break;
            default:
                break;
        }
    }//GEN-LAST:event_cbxHTTTItemStateChanged

    private void txtTienKhachDuaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienKhachDuaKeyReleased
        String tienDua = txtTienKhachDua.getText().trim();
        String tongThanhToan = txtTongTienThanhToan.getText();
        if (checkFloat(tienDua) && !tienDua.isEmpty()) {
            Float tong = Float.valueOf(tongThanhToan);
            Float tienKhachDua = Float.valueOf(tienDua);
            if (tienKhachDua <= tong) {
                txtTienKhachChuyenKhoan.setText((tong - tienKhachDua) + "");
            } else {
                Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Nhập tiền khách đưa không lớn hơn tổng tiền");
            }
            
        } else {
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Sai định dạng tiền");
            
        }
    }//GEN-LAST:event_txtTienKhachDuaKeyReleased

    private void btnLuaHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuaHoaDonActionPerformed
        int indexHoaDon = tblHoaDon.getSelectedRow();
        if (indexHoaDon == -1) {
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Chọn hóa đơn cần lưu");
            return;
        }
        if (!checkFloat(txtTienKhachDua.getText())) {
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Tiền khách đưa không hợp lệ");
            return;
        }
        if (checkEmpty(txtSDTKhachHang.getText())) {
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Chọn khách hàng trước khi lưu hóa đơn");
            return;
        }
        Long idHoaDon = listHoaDon.get(indexHoaDon).getIdHoaDon();
        String ghiChu = "" + txtGhiChu.getText();
        Float tongTien = Float.valueOf(txtTongTien.getText());
        Float tongTienGiam = Float.valueOf(txtTongTienGiam.getText());
        Long idKhachHang = thongTinKhachHang.getIdKhachHang();
        Float soTienGiam = 0f; // voucher
        Float tongTienThanhToan = Float.valueOf(txtTongTienThanhToan.getText());
        Integer trangThai = 1;
        Float tienMatKhachTra = Float.valueOf(txtTienKhachDua.getText());
        Integer hinhThucThanhToan = cbxHTTT.getSelectedIndex();
        Integer hinhThucMua = 1;
        String maHoaDon = txtMaHoaDon.getText();
        
        HoaDon hd = new HoaDon(
                idHoaDon,
                ghiChu,
                tongTien,
                tongTienGiam,
                1l,
                idKhachHang,
                tongTienGiam,
                tongTienThanhToan,
                maHoaDon,
                trangThai,
                tienMatKhachTra,
                hinhThucThanhToan,
                hinhThucMua);
        if (JOptionPane.showConfirmDialog(this, "Xác nhận lưu hóa đơn\nHóa đơn sẽ ở trạng thái\"Đã thanh toán\"", "Lưu hóa đơn", JOptionPane.YES_NO_OPTION) == 0) {
            serviceHoaDon.updateHoaDon(hd);
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Lưu hóa đơn thành công");
            cboTrangThaiHoaDon.setSelectedIndex(1);
        }
    }//GEN-LAST:event_btnLuaHoaDonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChonKhachHang;
    private javax.swing.JButton btnChonKhachHangDatHang;
    private javax.swing.JButton btnChonNhanVienShip;
    private javax.swing.JButton btnLuaHoaDon;
    private javax.swing.JButton btnThem;
    private javax.swing.JComboBox<String> cboHinhThucGiaoHang;
    private javax.swing.JComboBox<String> cboMau;
    private javax.swing.JComboBox<String> cboTrangThaiHoaDon;
    private javax.swing.JComboBox<String> cbxHTTT;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lb;
    private javax.swing.JPanel pnlWebcam;
    private javax.swing.JTable tblBienThe;
    private javax.swing.JTable tblGioHang;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTextField txtDiaChiDatHang;
    private javax.swing.JTextField txtGhiChu;
    private javax.swing.JTextField txtHoTenKHDatHang;
    private javax.swing.JTextField txtHoTenKHDatHang1;
    private javax.swing.JTextField txtHoTenKHDatHang2;
    private javax.swing.JTextField txtHoTenKHDatHang3;
    private javax.swing.JTextField txtHoTenKHDatHang4;
    private javax.swing.JTextField txtHoTenKHDatHang6;
    private javax.swing.JTextField txtHoTenKHDatHang7;
    private javax.swing.JTextField txtHoTenKHDatHang8;
    private javax.swing.JTextField txtHoTenKhachHang;
    private javax.swing.JTextField txtMaHoaDon;
    private javax.swing.JTextField txtSDTKHDatHang;
    private javax.swing.JTextField txtSDTKhachHang;
    private javax.swing.JTextField txtSDTNguoiShipDatHang;
    private javax.swing.JTextField txtSearchSanPham;
    private javax.swing.JTextField txtTenNguoiShipDatHang;
    private javax.swing.JTextField txtTenNhanVien;
    private javax.swing.JTextField txtTienKhachChuyenKhoan;
    private javax.swing.JTextField txtTienKhachDua;
    private javax.swing.JTextField txtTongTien;
    private javax.swing.JTextField txtTongTienGiam;
    private javax.swing.JTextField txtTongTienThanhToan;
    // End of variables declaration//GEN-END:variables
}
