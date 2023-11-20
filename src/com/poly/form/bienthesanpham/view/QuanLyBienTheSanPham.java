package com.poly.form.bienthesanpham.view;

import com.formdev.flatlaf.FlatClientProperties;
import com.poly.form.bienthesanpham.entity.BienTheSanPham;
import com.poly.form.bienthesanpham.entity.BienTheSanPhamDTO;
import com.poly.form.bienthesanpham.service.BienTheSanPhamService;
import com.poly.form.sanpham.entity.SanPhamDTO;
import com.poly.form.sanpham.service.SanPhamService;
import static com.poly.util.ph31848.MaRandom.renderMa;
import static com.poly.util.ph31848.Validate.checkChar;
import static com.poly.util.ph31848.Validate.checkEmpty;
import static com.poly.util.ph31848.Validate.checkFloat;
import static com.poly.util.ph31848.Validate.checkNumber;
import com.poly.form.thuoctinh.entity.ThuocTinhMauDTO;
import com.poly.form.thuoctinh.service.ThuocTinhMauService;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import raven.toast.Notifications;

/**
 *
 * @author Raven
 */
public class QuanLyBienTheSanPham extends javax.swing.JPanel {

    private BienTheSanPhamService service;
    private ThuocTinhMauService serviceMau;
    private DefaultTableModel dtm;
    private List<BienTheSanPhamDTO> list = new ArrayList<>();
    private List<ThuocTinhMauDTO> listMau = new ArrayList<>();

    // Biến xử lý phân trang
    private int currentPage = 0;
    private int length = 0;
    private int pageSize = 15;
    private int totalPage = 0;
    // SP
    private SanPhamDTO sp;
    
    // BTSP
    private String pathImageIcon = "";

    public QuanLyBienTheSanPham() {
        initComponents();
        lb.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h1.font");
        dtm = (DefaultTableModel) tblBienThe.getModel();
    }

    public QuanLyBienTheSanPham(SanPhamDTO sp) {
        initComponents();
        lb.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h1.font");
        this.sp = sp;
        setInfoSanPham();
        dtm = (DefaultTableModel) tblBienThe.getModel();
        service = new BienTheSanPhamService();
        serviceMau = new ThuocTinhMauService();
        list = service.findByMaSanPham(this.sp.getMaSanPham());
        listMau = serviceMau.getAll();
        showTable(list);
        loadComboBoxMau(listMau);
        txtMa.setEnabled(false);
    }

    public void loadComboBoxMau(List<ThuocTinhMauDTO> list) {
        cbxMau.removeAllItems();
        for (ThuocTinhMauDTO mau : list) {
            cbxMau.addItem(mau.getTenMau());
        }
    }

    public void showTable(List<BienTheSanPhamDTO> list) {
        dtm.setRowCount(0);
        for (BienTheSanPhamDTO bt : list) {
            dtm.addRow(bt.toDataRow());
        }
    }

    public void setInfoSanPham() {
        lbIDSanPham.setText(sp.getIdSanPham() + "");
        lbMaSanPham.setText(sp.getMaSanPham());
        lbTenSanPham.setText(sp.getTenSanPham());
        lbTheLoai.setText(sp.getTheLoai());
        lbNCC.setText(sp.getNhaCungCap());
    }

    public void thayDoiMainImage() {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File fileSelect = fileChooser.getSelectedFile();
            pathImageIcon = fileSelect.getPath();
            ImageIcon icon = new ImageIcon(pathImageIcon);
            lbMainImage.setIcon(icon);
        }
    }

    public BienTheSanPham getValue(String type) {
        Long id = 0l;
        String soLuong = txtSoLuong.getText().trim();
        String giaBan = txtGiaBan.getText().trim();
        String mainImage = pathImageIcon;
        Long idSanPham = Long.valueOf(lbIDSanPham.getText());
        Long idMau = listMau.get(cbxMau.getSelectedIndex()).getIDMau();
        Integer trangThai = cbxTrangThai.getSelectedIndex() == 0 ? 1 : 0;
        String ma = "";
        do {
            ma = "BT" + renderMa();
        } while (service.isExistMa(ma));
        try {
            if (checkEmpty(soLuong) || checkEmpty(giaBan)) {
                Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Vui lòng điển đủ thông tin");
                return null;
            }
            if (!checkNumber(soLuong)) {
                Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Số lượng phải là số nguyên dương");
                return null;
            }
            if (!checkFloat(giaBan)) {
                Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Giá không hợp lệ");
                return null;
            }

            if (type.equals("insert")) {
                return new BienTheSanPham(Integer.valueOf(soLuong), trangThai, mainImage, idSanPham, idMau, Float.valueOf(giaBan), ma);
            } else if (type.equals("update")) {
                id = Long.valueOf(lbIDBienThe.getText());
                return new BienTheSanPham(id, Integer.valueOf(soLuong), trangThai, mainImage, idSanPham, idMau, Float.valueOf(giaBan), ma);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lb = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBienThe = new javax.swing.JTable();
        btnPre = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        lbClick = new javax.swing.JLabel();
        lbClick1 = new javax.swing.JLabel();
        lbSum = new javax.swing.JLabel();
        lbTongTheLoai = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lbMainImage = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtMa = new javax.swing.JTextField();
        txtSoLuong = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cbxMau = new javax.swing.JComboBox<>();
        cbxTrangThai = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        txtGiaBan = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        lbIDBienThe = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lbMaSanPham = new javax.swing.JLabel();
        lbTheLoai = new javax.swing.JLabel();
        lbTenSanPham = new javax.swing.JLabel();
        lbNCC = new javax.swing.JLabel();
        lbIDSanPham = new javax.swing.JLabel();

        lb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb.setText("Biến thể sản phẩm");

        tblBienThe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã biến thể", "Màu", "Số lượng", "Giá bán", "Đã bán"
            }
        ));
        tblBienThe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBienTheMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblBienThe);

        btnPre.setText("|<");

        btnBack.setText("<");

        btnLast.setText(">|");

        btnNext.setText(">");

        jLabel7.setText("Tổng biến thể:");

        lbClick.setText("...");

        lbClick1.setText("of");

        lbSum.setText("...");

        lbTongTheLoai.setForeground(new java.awt.Color(255, 153, 0));
        lbTongTheLoai.setText("...");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 690, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnPre, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lbClick)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbClick1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbSum)
                        .addGap(34, 34, 34)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbTongTheLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbClick)
                        .addComponent(lbClick1)
                        .addComponent(lbSum)
                        .addComponent(jLabel7)
                        .addComponent(lbTongTheLoai))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnNext)
                        .addComponent(btnLast))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnPre)
                        .addComponent(btnBack)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lbMainImage.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 153, 0)));
        lbMainImage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbMainImageMousePressed(evt);
            }
        });

        jLabel2.setText("Mã");

        jLabel3.setText("Số lượng");

        jLabel4.setText("Màu");

        cbxMau.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbxTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hoạt động", "Không hoạt động" }));

        jLabel5.setText("Trạng thái:");

        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        jLabel11.setText("Giá bán");

        jLabel12.setText("ID:");

        lbIDBienThe.setText("0");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(lbMainImage, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbxTrangThai, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbxMau, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel11)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lbIDBienThe, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtGiaBan)
                            .addComponent(txtSoLuong, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(14, 14, 14)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(lbMainImage, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel12)
                    .addComponent(lbIDBienThe))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cbxMau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(cbxTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSua)
                    .addComponent(btnXoa)
                    .addComponent(btnThem))
                .addGap(14, 14, 14))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Tìm kiếm"));

        jLabel10.setText("Mã SP:");

        txtSearch.setBackground(new java.awt.Color(255, 255, 204));

        jLabel1.setText("Tên sản phẩm: ");

        jLabel6.setText("Thể loại:");

        jLabel8.setText("Nhà cung cấp:");

        jLabel9.setText("Mã SP:");

        lbMaSanPham.setText("lbMaSanPham");

        lbTheLoai.setText("lbTheLoai");

        lbTenSanPham.setText("lbTenSanPham");

        lbNCC.setText("lbNCC");

        lbIDSanPham.setText("id");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel1)
                    .addComponent(jLabel6)
                    .addComponent(jLabel8))
                .addGap(36, 36, 36)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbTheLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lbMaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbIDSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(lbMaSanPham)
                    .addComponent(lbIDSanPham))
                .addGap(2, 2, 2)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lbTenSanPham))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(lbTheLoai))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(lbNCC))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lb, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblBienTheMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBienTheMouseClicked
        int index = tblBienThe.getSelectedRow();
        BienTheSanPhamDTO bienThe = list.get(index);
        pathImageIcon = bienThe.getMainImage();
        ImageIcon icon = new ImageIcon(pathImageIcon);
        lbMainImage.setIcon(icon);
        lbIDBienThe.setText(bienThe.getIdBienThe() + "");
        txtMa.setText(bienThe.getMa());
        txtSoLuong.setText(bienThe.getSoLuong() + "");
        txtGiaBan.setText(bienThe.getGiaBan() + "");
        cbxMau.setSelectedItem(bienThe.getMauSac());
        cbxTrangThai.setSelectedIndex(1);
        if (bienThe.getTrangThai() == 1) {
            cbxTrangThai.setSelectedIndex(0);
        }
    }//GEN-LAST:event_tblBienTheMouseClicked

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        BienTheSanPham bienThe = getValue("insert");
        System.out.println(bienThe);
        if (bienThe != null) {
            if (!service.isExistBienThe(bienThe)) {
                if (JOptionPane.showConfirmDialog(this, "Xác nhận thêm biến thể", "Xác nhận", JOptionPane.YES_NO_OPTION) == 0) {
                    service.insertBienThe(bienThe);
                    list = service.findByMaSanPham(sp.getMaSanPham());
                    showTable(list);
                }
            } else {
                Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Đã có sản phẩm màu " + cbxMau.getSelectedItem());

            }
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        if (tblBienThe.getSelectedRow() != -1) {
            BienTheSanPham bienThe = getValue("update");
            System.out.println(bienThe);
            if (bienThe != null) {
                Long idMauMoi = bienThe.getIdMau();
                Long idMauCu = service.getIDMauBienTheByIDSanPham(Long.valueOf(lbIDBienThe.getText()));

                if (idMauMoi == idMauCu) {
                    System.out.println("tới đây 1");
                    service.updateBienThe(bienThe);
                    System.out.println(bienThe.toString());
                    list = service.findByMaSanPham(sp.getMaSanPham());
                    showTable(list);
                    System.out.println("tới đây 2");
                } else {
                    if (!service.isExistBienThe(bienThe)) {
                        service.updateBienThe(bienThe);
                        list = service.findByMaSanPham(sp.getMaSanPham());
                        showTable(list);
                    } else {
                        Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Đã có sản phẩm màu " + cbxMau.getSelectedItem());

                    }
                }
            }
        } else {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Chọn hàng để sửa");

        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        int index = tblBienThe.getSelectedRow();
        if (index != -1) {
            if (Integer.valueOf(tblBienThe.getValueAt(index, 4) + "") == 0) {

                if (JOptionPane.showConfirmDialog(this, "Xóa biến thể\n", "Xác nhận xóa", JOptionPane.YES_NO_OPTION) == 0) {
                    service.deleteBienThe(Long.valueOf(lbIDBienThe.getText()));
                    list = service.findByMaSanPham(sp.getMaSanPham());
                    showTable(list);
                }
            } else {
                if (JOptionPane.showConfirmDialog(this, "Biến thể đã được bán\nHỗ trợ tắt trạng thái", "Xác nhận", JOptionPane.YES_NO_OPTION) == 0) {
                    service.tatTrangThai(Long.valueOf(lbIDBienThe.getText()));
                    list = service.findByMaSanPham(sp.getMaSanPham());
                    showTable(list);
                }
            }
        } else {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Chọn hàng để xóa");

        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void lbMainImageMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbMainImageMousePressed
        if (evt.getButton() == MouseEvent.BUTTON3) {
            thayDoiMainImage();
        }
    }//GEN-LAST:event_lbMainImageMousePressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPre;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cbxMau;
    private javax.swing.JComboBox<String> cbxTrangThai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb;
    private javax.swing.JLabel lbClick;
    private javax.swing.JLabel lbClick1;
    private javax.swing.JLabel lbIDBienThe;
    private javax.swing.JLabel lbIDSanPham;
    private javax.swing.JLabel lbMaSanPham;
    private javax.swing.JLabel lbMainImage;
    private javax.swing.JLabel lbNCC;
    private javax.swing.JLabel lbSum;
    private javax.swing.JLabel lbTenSanPham;
    private javax.swing.JLabel lbTheLoai;
    private javax.swing.JLabel lbTongTheLoai;
    private javax.swing.JTable tblBienThe;
    private javax.swing.JTextField txtGiaBan;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtSoLuong;
    // End of variables declaration//GEN-END:variables
}
