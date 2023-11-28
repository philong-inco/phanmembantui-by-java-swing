/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.form.hoadon.repository;

import com.poly.database.DBConnect;
import com.poly.form.hoadon.entity.BienTheSearch;
import com.poly.form.hoadon.entity.GioHang;
import com.poly.form.hoadon.entity.HoaDon;
import com.poly.form.hoadon.entity.HoaDonDTO;
import com.poly.form.hoadon.entity.KhachHangSearch;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static com.poly.util.ph31848.Validate.renderStringSearchSQL;

/**
 *
 * @author longnvph31848
 */
public class HoaDonRepository {

    private Connection cn;

    public HoaDonRepository() {
        cn = DBConnect.getConnection();
    }

    public List<HoaDonDTO> getAll() {
        List<HoaDonDTO> list = new ArrayList<>();
        String query = "SELECT hd.IDHoaDon, hd.GhiChu, hd.TongTienHoaDon, \n"
                + "hd.TongTienSauKhuyenMai, nv.ten, kh.ho_ten, \n"
                + "hd.SoTienDaGiam, hd.TongTienPhaiTra, hd.MaHoaDon, \n"
                + "hd.TrangThai, hd.TienMatKhachTra, hd.HinhThucThanhToan, hd.HinhThuc,\n"
                + "(SELECT COUNT(id) FROM HoaDon_SanPhamChiTiet WHERE IDHoaDon = hd.IDHoaDon) AS TongSanPham, kh.sdt, kh.ho_ten\n"
                + "FROM HoaDon hd\n"
                + "LEFT JOIN nhan_vien nv ON hd.IDNhanVien=nv.id\n"
                + "LEFT JOIN khach_hang kh ON hd.IDKhachHang=kh.id";
        try {
            PreparedStatement ps = cn.prepareStatement(query);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDonDTO hd = new HoaDonDTO(
                        rs.getLong(1),
                        rs.getString(2),
                        rs.getFloat(3),
                        rs.getFloat(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getFloat(7),
                        rs.getFloat(8),
                        rs.getString(9),
                        rs.getInt(10),
                        rs.getFloat(11),
                        rs.getInt(12),
                        rs.getInt(13),
                        rs.getInt(14),
                        rs.getString(15),
                        rs.getString(16)
                );
                list.add(hd);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<HoaDonDTO> getAllSearch(Integer hinhThucMua, Integer trangThai) {
        List<HoaDonDTO> list = new ArrayList<>();
        StringBuilder strQuery = new StringBuilder(
                "SELECT hd.IDHoaDon, hd.GhiChu, hd.TongTienHoaDon, \n"
                + "hd.TongTienSauKhuyenMai, nv.ten, kh.ho_ten, \n"
                + "hd.SoTienDaGiam, hd.TongTienPhaiTra, hd.MaHoaDon, \n"
                + "hd.TrangThai, hd.TienMatKhachTra, hd.HinhThucThanhToan, hd.HinhThuc,\n"
                + "(SELECT COUNT(id) FROM HoaDon_SanPhamChiTiet WHERE IDHoaDon = hd.IDHoaDon) AS TongSanPham, kh.sdt, kh.ho_ten\n"
                + "FROM HoaDon hd\n"
                + "LEFT JOIN nhan_vien nv ON hd.IDNhanVien=nv.id\n"
                + "LEFT JOIN khach_hang kh ON hd.IDKhachHang=kh.id WHERE 1=1"
        );

        if (hinhThucMua != null) {
            strQuery.append(" AND hd.HinhThuc = " + hinhThucMua);
        }
        if (trangThai != null) {
            strQuery.append(" AND hd.TrangThai = " + trangThai);
        }
        String query = strQuery.toString();
        System.out.println("\n\n" + query);
        try {
            PreparedStatement ps = cn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDonDTO hd = new HoaDonDTO(
                        rs.getLong(1),
                        rs.getString(2),
                        rs.getFloat(3),
                        rs.getFloat(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getFloat(7),
                        rs.getFloat(8),
                        rs.getString(9),
                        rs.getInt(10),
                        rs.getFloat(11),
                        rs.getInt(12),
                        rs.getInt(13),
                        rs.getInt(14),
                        rs.getString(15),
                        rs.getString(16));
                list.add(hd);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<GioHang> getAllBienTheByIdHoaDon(Long id) {
        List<GioHang> list = new ArrayList<>();
        String query = "SELECT spct.IDSanPhamChiTiet, s.MaSanPham, s.TenSanPham, m.TenMau, hdct.SoLuong, spct.GiaBan, \n"
                + "hdct.SoLuong*spct.GiaBan*SUM(km.phan_tram_giam_gia)/100 AS KhuyenMai, \n"
                + "hdct.SoLuong*spct.GiaBan-hdct.SoLuong*spct.GiaBan*SUM(km.phan_tram_giam_gia)/100 AS ThanhTien\n"
                + "FROM HoaDon_SanPhamChiTiet hdct\n"
                + "JOIN SanPhamChiTiet spct ON hdct.IDSanPhamChiTiet=spct.IDSanPhamChiTiet\n"
                + "JOIN Mau m ON spct.IDMau=m.IDMau\n"
                + "JOIN SanPham s ON spct.IDSanPham=s.IDSanPham\n"
                + "JOIN sanphamchitiet_khuyenmai spkm ON spct.IDSanPhamChiTiet=spkm.id_san_pham_chi_tiet\n"
                + "JOIN khuyen_mai km ON spkm.id_khuyen_mai=km.id\n"
                + "WHERE hdct.IDHoaDon = ? \n"
                + "GROUP BY s.MaSanPham, s.TenSanPham, m.TenMau, hdct.SoLuong, spct.GiaBan,spct.IDSanPhamChiTiet";
        try {
            PreparedStatement ps = cn.prepareStatement(query);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                GioHang gioHang = new GioHang(
                        rs.getLong(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getFloat(6),
                        rs.getFloat(7),
                        rs.getFloat(8));
                list.add(gioHang);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void insertHoaDon(HoaDon hd) {
        String query = "INSERT INTO HoaDon(IDNhanVien,MaHoaDon,TrangThai) VALUES(?,?,?)";
        try {
            PreparedStatement ps = cn.prepareStatement(query);
            ps.setLong(1, hd.getIdNhanVien());
            ps.setString(2, hd.getMaHoaDon());
            ps.setInt(3, 0);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateHoaDon(HoaDon hd) {
        String query = "UPDATE HoaDon SET GhiChu = ?, TongTienHoaDon = ?, "
                + "TongTienSauKhuyenMai = ?, IDKhachHang = ?, SoTienDaGiam = ?, "
                + "TongTienPhaiTra = ?, ThoiGianSua = GETDATE(), TrangThai = ?, "
                + "TienMatKhachTra = ?, HinhThucThanhToan = ?, HinhThuc = ? "
                + "WHERE IDHoaDon = ?";
        try {
            PreparedStatement ps = cn.prepareStatement(query);
            ps.setString(1, hd.getGhiChu());
            ps.setFloat(2, hd.getTongTienHoaDon());
            ps.setFloat(3, hd.getTongTienSauKhuyenMai());
            ps.setLong(4, hd.getIdKhachHang());
            ps.setFloat(5, hd.getSoTienDaGiam());
            ps.setFloat(6, hd.getTongTienPhaiTra());
            ps.setInt(7, hd.getTrangThai());
            ps.setFloat(8, hd.getTienMatKhachTra());
            ps.setInt(9, hd.getHinhThucThanhToan());
            ps.setInt(10, hd.getHinhThucMua());
            ps.setLong(11, hd.getIdHoaDon());
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isExistMa(String str) {
        String query = "SELECT MaHoaDon FROM HoaDon WHERE MaHoaDon = ? ";
        if (cn != null) {
            try {
                PreparedStatement ps = cn.prepareStatement(query);
                ps.setString(1, str);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    return true;
                }
            } catch (SQLException e) {
                e.printStackTrace();

            }
        }
        return false;
    }

    public Long getIDBienTheByMa(String ma) {
        String query = "SELECT IDSanPhamChiTiet FROM SanPhamChiTiet WHERE Ma = ?";
        Long result = 0L;
        try {
            PreparedStatement ps = cn.prepareStatement(query);
            ps.setString(1, ma);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result = rs.getLong(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<BienTheSearch> searchSanPham(String keyword, String mau) {
        List<BienTheSearch> list = new ArrayList<>();
        StringBuilder queryString = new StringBuilder(
                "SELECT spct.IDSanPhamChiTiet ,s.MaSanPham, s.TenSanPham, m.TenMau, spct.SoLuong, spct.GiaBan, SUM(km.phan_tram_giam_gia) AS KhuyenMai\n"
                + "	FROM SanPhamChiTiet spct \n"
                + "	JOIN SanPham s ON spct.IDSanPham=s.IDSanPham\n"
                + "	JOIN Mau m ON spct.IDMau=m.IDMau\n"
                + "	JOIN sanphamchitiet_khuyenmai spkm ON spct.IDSanPhamChiTiet=spkm.id_san_pham_chi_tiet\n"
                + "	JOIN khuyen_mai km ON spkm.id_khuyen_mai=km.id\n"
                + "	GROUP BY s.MaSanPham, s.TenSanPham, m.TenMau, spct.SoLuong, spct.GiaBan,spct.IDSanPhamChiTiet\n"
                + "	Having 1=1"
        );

        if (mau != null) {
            queryString.append(" AND m.TenMau = N\'" + mau + "\' ");
        }
        if (keyword != null) {
            queryString.append(" AND " + renderStringSearchSQL("s.MaSanPham", "s.TenSanPham", keyword));
        }

        String query = queryString.toString();
        try {
            PreparedStatement ps = cn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                BienTheSearch bt = new BienTheSearch(
                        rs.getLong(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getFloat(6),
                        rs.getInt(7));
                list.add(bt);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<KhachHangSearch> searchKhachHang(String key) {
        List<KhachHangSearch> list = new ArrayList<>();
        StringBuilder queryString = new StringBuilder(
                "SELECT id, ho_ten, sdt, email, dia_chi FROM khach_hang WHERE 1=1"
        );

        if (key != null) {
            queryString.append(" AND " + renderStringSearchSQL("ho_ten", "sdt", key));
        }

        String query = queryString.toString();
        try {
            PreparedStatement ps = cn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KhachHangSearch bt = new KhachHangSearch(
                        rs.getLong(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5));
                list.add(bt);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public KhachHangSearch findByID(Long id) {
        String query = "SELECT id, ho_ten, sdt, email, dia_chi FROM khach_hang WHERE id = ?";

        try {
            PreparedStatement ps = cn.prepareStatement(query);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return new KhachHangSearch(
                        rs.getLong(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void insertKhachHangSearch(KhachHangSearch kh) {
        String query = "INSERT INTO khach_hang(ho_ten, sdt, email, dia_chi) VALUES(?,?,?,?)";
        try {
            PreparedStatement ps = cn.prepareStatement(query);
            ps.setString(1, kh.getHoTen());
            ps.setString(2, kh.getSdt());
            ps.setString(3, kh.getEmail());
            ps.setString(4, kh.getDiaChi());
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<KhachHangSearch> findKhachHangSearchBySDTEmail(String sdt, String email) {
        String query = "SELECT id, ho_ten, sdt, email, dia_chi FROM khach_hang WHERE sdt = ? AND email = ?";
        List<KhachHangSearch> list = new ArrayList<>();
        try {
            PreparedStatement ps = cn.prepareStatement(query);
            ps.setString(1, sdt);
            ps.setString(2, email);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KhachHangSearch kh = new KhachHangSearch(
                        rs.getLong(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5));
                list.add(kh);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Float getTongTienHoaDonById(Long id) {
        String query = "SELECT (SELECT SUM(GiaTien) FROM HoaDon_SanPhamChiTiet WHERE IDHoaDon = hd.IDHoaDon) AS TongTien\n"
                + "FROM HoaDon hd\n"
                + "LEFT JOIN nhan_vien nv ON hd.IDNhanVien=nv.id\n"
                + "LEFT JOIN khach_hang kh ON hd.IDKhachHang=kh.id\n"
                + "WHERE 1 = 1\n"
                + "AND hd.IDHoaDon = ?";

        Float result = 0f;
        try {
            PreparedStatement ps = cn.prepareStatement(query);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result = rs.getFloat(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = null;
        }

        return result;
    }

    public Float getTienGiamHoaDonById(Long id) {
        String query = "SELECT (SELECT COALESCE(SUM((hdct.GiaTien*hdct.SoLuong*km.phan_tram_giam_gia)/100),0) \n"
                + "		FROM HoaDon_SanPhamChiTiet hdct\n"
                + "		LEFT JOIN sanphamchitiet_khuyenmai kmct ON hdct.ID=kmct.id_san_pham_chi_tiet\n"
                + "		LEFT JOIN khuyen_mai km ON kmct.id_khuyen_mai=km.id\n"
                + "		WHERE hdct.IDHoaDon = hd.IDHoaDon\n"
                + "		AND 1636337000 BETWEEN km.thoi_gian_bat_dau AND km.thoi_gian_ket_thuc) AS SoTienGiam\n"
                + "FROM HoaDon hd\n"
                + "LEFT JOIN nhan_vien nv ON hd.IDNhanVien=nv.id\n"
                + "LEFT JOIN khach_hang kh ON hd.IDKhachHang=kh.id\n"
                + "WHERE 1 = 1\n"
                + "AND hd.IDHoaDon = ?";
        Float result = 0f;
        try {
            PreparedStatement ps = cn.prepareStatement(query);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result = rs.getFloat(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = null;
        }

        return result;
    }

    public Float getTienThanhToanHoaDonById(Long id) {
        String query = "SELECT (SELECT SUM(GiaTien) FROM HoaDon_SanPhamChiTiet WHERE IDHoaDon = hd.IDHoaDon)\n"
                + "-	(\n"
                + "		SELECT COALESCE(SUM((hdct.GiaTien*hdct.SoLuong*km.phan_tram_giam_gia)/100),0) \n"
                + "		FROM HoaDon_SanPhamChiTiet hdct\n"
                + "		JOIN sanphamchitiet_khuyenmai kmct ON hdct.ID=kmct.id_san_pham_chi_tiet\n"
                + "		JOIN khuyen_mai km ON kmct.id_khuyen_mai=km.id\n"
                + "		WHERE hdct.IDHoaDon = hd.IDHoaDon\n"
                + "		AND 1636337000 BETWEEN km.thoi_gian_bat_dau AND km.thoi_gian_ket_thuc\n"
                + "	) AS TongTienThanhToan\n"
                + "FROM HoaDon hd\n"
                + "LEFT JOIN nhan_vien nv ON hd.IDNhanVien=nv.id\n"
                + "LEFT JOIN khach_hang kh ON hd.IDKhachHang=kh.id\n"
                + "WHERE 1 = 1\n"
                + "AND hd.IDHoaDon = ?";
        Float result = 0f;
        try {
            PreparedStatement ps = cn.prepareStatement(query);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result = rs.getFloat(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = null;
        }

        return result;
    }
// bắt đầu làm thêm sản phẩm vào hóa đơn
    public void insertBienTheToHoaDon(Long idHoaDonLong, Long idBienThe, Float giaTien, Integer soLuong) {
        String query = "INSERT INTO HoaDon_SanPhamChiTiet(IDHoaDon, IDSanPhamChiTiet, GiaTien, SoLuong) VALUES (?,?,?,?)";
        try {
            PreparedStatement ps = cn.prepareStatement(query);
            ps.setLong(1, idHoaDonLong);
            ps.setLong(1, idBienThe);
            ps.setFloat(3, giaTien);
            ps.setInt(4, soLuong);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void updateBienTheToHoaDon(Long idHoaDonLong, Long idBienThe, Integer soLuong){
        
    }
    
    

}
