/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.form.bienthesanpham.service;

import com.poly.form.bienthesanpham.entity.BienTheSanPham;
import com.poly.form.bienthesanpham.entity.BienTheSanPhamDTO;
import com.poly.form.bienthesanpham.repository.BienTheSanPhamRepository;
import java.util.List;

/**
 *
 * @author longnvph31848
 */
public class BienTheSanPhamService implements IBienTheSanPhamService {
    
    private BienTheSanPhamRepository repo;
    
    public BienTheSanPhamService() {
        repo = new BienTheSanPhamRepository();
    }
    
    @Override
    public List<BienTheSanPhamDTO> findByMaSanPham(String maSanPham) {
        return repo.findByMaSanPham(maSanPham);
    }
    
    @Override
    public boolean isExistMa(String str) {
        return repo.isExistMa(str.trim());
    }
    
    @Override
    public void insertBienThe(BienTheSanPham bienThe) {
        repo.insertBienThe(bienThe);
    }
    
    @Override
    public void updateBienThe(BienTheSanPham bienThe) {
        repo.updateBienThe(bienThe);
    }
    
    @Override
    public void deleteBienThe(Long id) {
        repo.deleteBienThe(id);
    }
    
    @Override
    public void tatTrangThai(Long id) {
        repo.tatTrangThai(id);
    }
    
    @Override
    public List<BienTheSanPhamDTO> getAll() {
        return repo.getAll();
    }
    
    @Override
    public boolean isExistBienThe(BienTheSanPham bienThe) {
        return repo.isExistBienThe(bienThe);
    }
    
    @Override
    public BienTheSanPhamDTO findBienTheByID(Long id) {
        return repo.findBienTheByID(id);
    }
    
    @Override
    public Long getIDMauBienTheByIDSanPham(Long idSanPham) {
        return repo.getIDMauBienTheByIDSanPham(idSanPham);
    }
    
}
