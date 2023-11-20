/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.form.bienthesanpham.repository;

import com.poly.database.DBConnect;
import com.poly.form.bienthesanpham.entity.BienTheSanPham;
import com.poly.form.bienthesanpham.entity.BienTheSanPhamDTO;

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
public class BienTheSanPhamRepository {

    private Connection cn;

    public BienTheSanPhamRepository() {
        cn = DBConnect.getConnection();
    }

    public List<BienTheSanPhamDTO> findByMaSanPham(String maSanPham) {
        List<BienTheSanPhamDTO> list = new ArrayList<>();
        String query = "SELECT ct.IDSanPhamChiTiet, ct.SoLuong, ct.TrangThai, ct.MainImage,\n"
                + " ct.GiaBan, ct.ThoiGianTao, ct.Ma, ct.ThoiGianSua, SUM(hdsp.SoLuong), m.TenMau\n"
                + " FROM SanPhamChiTiet ct\n"
                + " LEFT JOIN SanPham s ON ct.IDSanPham=s.IDSanPham\n"
                + " LEFT JOIN Mau m ON ct.IDMau=m.IDMau\n"
                + " LEFT JOIN HoaDon_SanPhamChiTiet hdsp ON ct.IDSanPhamChiTiet=hdsp.IDSanPhamChiTiet\n"
                + " GROUP BY ct.IDSanPhamChiTiet, ct.SoLuong, ct.TrangThai, ct.MainImage, ct.IDSanPham,\n"
                + " ct.GiaBan, ct.ThoiGianTao, ct.Ma, ct.ThoiGianSua, s.MaSanPham, m.TenMau, s.MaSanPham\n"
                + " HAVING s.MaSanPham = ?";
        if (cn != null) {
            try {
                PreparedStatement ps = cn.prepareStatement(query);
                ps.setString(1, maSanPham);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    BienTheSanPhamDTO sanPham = new BienTheSanPhamDTO(
                            rs.getLong(1),
                            rs.getInt(2),
                            rs.getInt(3),
                            rs.getString(4),
                            rs.getFloat(5),
                            rs.getString(6),
                            rs.getString(7),
                            rs.getString(8),
                            rs.getInt(9),
                            rs.getString(10)
                    );
                    list.add(sanPham);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public boolean isExistMa(String str) {
        String query = "SELECT Ma FROM SanPhamChiTiet WHERE Ma = ? ";
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

    public Long getIDMauBienTheByIDSanPham(Long idSanPham) {
        String query = "SELECT IDMau FROM SanPhamChiTiet WHERE IDSanPhamChiTiet = ?";
        if (cn != null) {
            try {
                PreparedStatement ps = cn.prepareStatement(query);
                ps.setLong(1, idSanPham);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    return rs.getLong(1);
                }
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    public boolean isExistBienThe(BienTheSanPham bienThe) {
        String query = "SELECT IDSanPhamChiTiet FROM SanPhamChiTiet\n"
                + "WHERE IDSanPham = ? AND IDMau = ?";
        if (cn != null) {
            try {
                PreparedStatement ps = cn.prepareStatement(query);
                ps.setLong(1, bienThe.getIdSanPham());
                ps.setLong(2, bienThe.getIdMau());
                System.out.println(ps.toString());
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

    public void insertBienThe(BienTheSanPham bienThe) {
        String query = "INSERT INTO SanPhamChiTiet(SoLuong,TrangThai,MainImage,IDSanPham,IDMau,GiaBan, Ma) "
                + " VALUES (?,?,?,?,?,?,?)";
        if (cn != null) {
            try {
                PreparedStatement ps = cn.prepareStatement(query);

                ps.setInt(1, bienThe.getSoLuong());
                ps.setInt(2, bienThe.getTrangThai());
                ps.setString(3, bienThe.getMainImage());
                ps.setLong(4, bienThe.getIdSanPham());
                ps.setLong(5, bienThe.getIdMau());
                ps.setFloat(6, bienThe.getGiaBan());
                ps.setString(7, bienThe.getMa());
                System.out.println(ps.toString());
                ps.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public void updateBienThe(BienTheSanPham bienThe) {
        String query = "UPDATE SanPhamChiTiet SET SoLuong = ?, "
                + " TrangThai = ?, MainImage = ?, IDMau = ?, GiaBan = ? "
                + " WHERE IDSanPhamChiTiet = ? ";
        if (cn != null) {
            try {
                PreparedStatement ps = cn.prepareStatement(query);
                ps.setInt(1, bienThe.getSoLuong());
                ps.setInt(2, bienThe.getTrangThai());
                ps.setString(3, bienThe.getMainImage());
                ps.setLong(4, bienThe.getIdMau());
                ps.setFloat(5, bienThe.getGiaBan());
                ps.setLong(6, bienThe.getIdBienThe());
                ps.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteBienThe(Long id) {
        String query = "DELETE FROM SanPhamChiTiet WHERE IDSanPhamChiTiet = ? ";
        if (cn != null) {
            try {
                PreparedStatement ps = cn.prepareStatement(query);
                ps.setLong(1, id);
                ps.execute();
            } catch (SQLException e) {
                e.printStackTrace();

            }
        }
    }

    public void tatTrangThai(Long id) {
        String query = "UPDATE SanPhamChiTiet SET "
                + " TrangThai = ? WHERE IDSanPhamChiTiet = ? ";
        if (cn != null) {
            try {
                PreparedStatement ps = cn.prepareStatement(query);
                ps.setInt(1, 0);
                ps.setLong(2, id);
                ps.execute();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<BienTheSanPhamDTO> getAll() {
        List<BienTheSanPhamDTO> list = new ArrayList<>();
        String query = "SELECT ct.IDSanPhamChiTiet, ct.SoLuong, ct.TrangThai, ct.MainImage,\n"
                + "ct.GiaBan, ct.ThoiGianTao, ct.Ma, ct.ThoiGianSua, SUM(hdsp.SoLuong), m.TenMau\n"
                + "FROM SanPhamChiTiet ct\n"
                + "JOIN SanPham s ON ct.IDSanPham=s.IDSanPham\n"
                + "JOIN Mau m ON ct.IDMau=m.IDMau\n"
                + "JOIN HoaDon_SanPhamChiTiet hdsp ON ct.IDSanPhamChiTiet=hdsp.IDSanPhamChiTiet\n"
                + "GROUP BY ct.IDSanPhamChiTiet, ct.SoLuong, ct.TrangThai, ct.MainImage, ct.IDSanPham, \n"
                + "ct.GiaBan, ct.ThoiGianTao, ct.Ma, ct.ThoiGianSua, s.MaSanPham, m.TenMau\n";
        if (cn != null) {
            try {
                PreparedStatement ps = cn.prepareStatement(query);

                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    BienTheSanPhamDTO sanPham = new BienTheSanPhamDTO(
                            rs.getLong(1),
                            rs.getInt(2),
                            rs.getInt(3),
                            rs.getString(4),
                            rs.getFloat(5),
                            rs.getString(6),
                            rs.getString(7),
                            rs.getString(8),
                            rs.getInt(9),
                            rs.getString(10)
                    );
                    list.add(sanPham);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public BienTheSanPhamDTO findBienTheByID(Long id) {

        String query = "SELECT ct.IDSanPhamChiTiet, ct.SoLuong, ct.TrangThai, ct.MainImage,\n"
                + "ct.GiaBan, ct.ThoiGianTao, ct.Ma, ct.ThoiGianSua, SUM(hdsp.SoLuong), m.TenMau\n"
                + "FROM SanPhamChiTiet ct\n"
                + "JOIN SanPham s ON ct.IDSanPham=s.IDSanPham\n"
                + "JOIN Mau m ON ct.IDMau=m.IDMau\n"
                + "JOIN HoaDon_SanPhamChiTiet hdsp ON ct.IDSanPhamChiTiet=hdsp.IDSanPhamChiTiet\n"
                + "GROUP BY ct.IDSanPhamChiTiet, ct.SoLuong, ct.TrangThai, ct.MainImage, ct.IDSanPham, \n"
                + "ct.GiaBan, ct.ThoiGianTao, ct.Ma, ct.ThoiGianSua, s.MaSanPham, m.TenMau\n"
                + "WHERE ct.IDSanPhamChiTiet = ?";
        if (cn != null) {
            try {
                PreparedStatement ps = cn.prepareStatement(query);
                ps.setLong(1, id);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    BienTheSanPhamDTO bienThe = new BienTheSanPhamDTO(
                            rs.getLong(1),
                            rs.getInt(2),
                            rs.getInt(3),
                            rs.getString(4),
                            rs.getFloat(5),
                            rs.getString(6),
                            rs.getString(7),
                            rs.getString(8),
                            rs.getInt(9),
                            rs.getString(10)
                    );
                    return bienThe;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

//    public boolean isExistBienThe(Long idSanPham, Long idMau) {
//        String query = "SELECT IDSanPhamChiTiet FROM SanPhamChiTiet WHERE IDSanPham = ? AND IDMau = ?";
//        if (cn != null) {
//            try {
//                PreparedStatement ps = cn.prepareStatement(query);
//                ResultSet rs = ps.executeQuery();
//                while (rs.next()) {
//                    return true;
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//                return true;
//            }
//        }
//        return false;
//    }
}
