/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.poly.form.hoadon.service;

import com.poly.form.hoadon.entity.BienTheSearch;
import com.poly.form.hoadon.entity.GioHang;
import com.poly.form.hoadon.entity.HoaDon;
import com.poly.form.hoadon.entity.HoaDonDTO;
import com.poly.form.hoadon.entity.KhachHangSearch;
import java.util.List;

/**
 *
 * @author longnvph31848
 */
public interface IHoaDonService {

    List<HoaDonDTO> getAll();

    List<HoaDonDTO> getAllSearch(Integer hinhThucMua, Integer trangThai);

    List<GioHang> getAllBienTheByIdHoaDon(Long id);

    void insertHoaDon(HoaDon hd);

    void updateHoaDon(HoaDon hd);

    boolean isExistMa(String str);

    Long getIDBienTheByMa(String ma);

    List<BienTheSearch> searchSanPham(String keyword, String mau);

    List<KhachHangSearch> searchKhachHang(String key);

    KhachHangSearch findByID(Long id);

    void insertKhachHangSearch(KhachHangSearch kh);

    List<KhachHangSearch> findKhachHangSearchBySDTEmail(String sdt, String email);

    Float getTongTienHoaDonById(Long id);

    Float getTienGiamHoaDonById(Long id);

    Float getTienThanhToanHoaDonById(Long id);

    void insertBienTheToHoaDon(Long idHoaDonLong, Long idBienThe, Float giaTien, Integer soLuong);

    void updateBienTheToHoaDon(Long id, Integer soLuong);

    void updateSoLuongBienThe(Long id, Integer soLuong);

    Long isExistBienTheHoaDon(Long idHoaDon, Long idBienThe);

    Integer getSoLuongBienTheTrongGioHangById(Long id);

    Integer getSoLuongBienTheById(Long id);

    Float getGiaBanBienTheById(Long id);

}
