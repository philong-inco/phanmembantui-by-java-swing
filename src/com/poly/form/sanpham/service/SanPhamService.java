/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.form.sanpham.service;

import com.poly.form.sanpham.entity.SanPham;
import com.poly.form.sanpham.entity.SanPhamDTO;
import com.poly.form.sanpham.repository.SanPhamRepository;
import java.util.List;

/**
 *
 * @author longnvph31848
 */
public class SanPhamService implements ISanPhamService {

    private SanPhamRepository repo;

    public SanPhamService() {
        repo = new SanPhamRepository();
    }

    @Override
    public List<SanPhamDTO> getAll() {
        return repo.getAll();
    }

    @Override
    public boolean isExistMa(String str) {
        return repo.isExistMa(str.trim());
    }

    @Override
    public void insertSanPham(SanPham sanPham) {
        repo.insertSanPham(sanPham);
    }

    @Override
    public void updateSanPham(SanPham sanPham) {
        repo.updateSanPham(sanPham);
    }

    @Override
    public void deleteSanPham(Long id) {
        repo.deleteSanPham(id);
    }

    @Override
    public void tatTrangThai(Long id) {
        repo.tatTrangThai(id);
    }

    @Override
    public List<String> getImageSanPham(Long id) {
        return repo.getImageSanPham(id);
    }

    @Override
    public List<SanPhamDTO> search(String keyword, Long idTheLoai, Long idNCC,
            Long idMau, int cbxSearchSoLuongCai, Integer txtCbxSearchSoLuongCai,
            int cbxSearchBienThe, Integer txtCbxSearchBienThe, int cbxSearchDateType,
            String txtDateFirst, String txtTimeFirst, String txtDateSecond,
            String txtTimeSecond, int cbxSearchTrangThai) {
        return repo.search(keyword, idTheLoai, idNCC, idMau, cbxSearchSoLuongCai,
                txtCbxSearchSoLuongCai, cbxSearchBienThe, txtCbxSearchBienThe, cbxSearchDateType,
                txtDateFirst, txtTimeFirst, txtDateSecond, txtTimeSecond, cbxSearchTrangThai);
    }

}
