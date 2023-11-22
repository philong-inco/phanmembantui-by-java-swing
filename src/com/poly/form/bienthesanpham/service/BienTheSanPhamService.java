/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.form.bienthesanpham.service;

import com.poly.form.bienthesanpham.entity.BienTheSanPham;
import com.poly.form.bienthesanpham.entity.BienTheSanPhamDTO;
import com.poly.form.bienthesanpham.repository.BienTheSanPhamRepository;
import com.poly.form.sanpham.entity.SanPhamDTO;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import static com.poly.util.ph31848.GetDateTimeCurrent.getTimeNow;
import java.awt.Font;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;

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

    @Override
    public boolean bienTheIsSale(Long id) {
        return repo.bienTheIsSale(id);
    }

    @Override
    public void insertListBienThe(List<BienTheSanPham> list) {
        repo.insertListBienThe(list);
    }

    @Override
    public String exportListToExcel(List<BienTheSanPhamDTO> list, SanPhamDTO sp) {
        XSSFWorkbook wordkbook = new XSSFWorkbook();
        XSSFSheet sheet = wordkbook.createSheet("DanhSachBienThe");
        XSSFRow row = null;
        Cell cell = null;

        // Styte tiêu đề
        // Tạo CellStyle để tô nền và in đậm
        CellStyle style = wordkbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        

        // Mã sản phẩm
        row = sheet.createRow(1);
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("Mã SP: ");
        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue(sp.getMaSanPham());
        // Tên sản phẩm
        row = sheet.createRow(2);
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("Tên SP: ");
        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue(sp.getTenSanPham());
        // Thể loại
        row = sheet.createRow(3);
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("Thể loại: ");
        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue(sp.getTheLoai());
        // Nhà cung cấp
        row = sheet.createRow(4);
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("NCC: ");
        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue(sp.getNhaCungCap());

        // In danh sách
        row = sheet.createRow(6);
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("STT");
        cell.setCellStyle(style);
        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("Mã");
        cell.setCellStyle(style);
        cell = row.createCell(2, CellType.STRING);
        cell.setCellValue("Màu");
        cell.setCellStyle(style);
        cell = row.createCell(3, CellType.STRING);
        cell.setCellValue("Số lượng");
        cell.setCellStyle(style);
        cell = row.createCell(4, CellType.STRING);
        cell.setCellValue("Giá niêm yết");
        cell.setCellStyle(style);
        cell = row.createCell(5, CellType.STRING);
        cell.setCellValue("Giá bán");
        cell.setCellStyle(style);
        cell = row.createCell(6, CellType.STRING);
        cell.setCellValue("Trạng thái");
        cell.setCellStyle(style);
        cell = row.createCell(7, CellType.STRING);
        cell.setCellValue("Đã bán");
        cell.setCellStyle(style);

        for (int i = 0; i < list.size(); i++) {
            BienTheSanPhamDTO bt = list.get(i);
            row = sheet.createRow(9 + i);

            cell = row.createCell(0, CellType.NUMERIC);
            cell.setCellValue(i + 1);

            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue(bt.getMa());

            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue(bt.getMauSac());

            cell = row.createCell(3, CellType.NUMERIC);
            cell.setCellValue(bt.getSoLuong());

            cell = row.createCell(4, CellType.NUMERIC);
            cell.setCellValue(bt.getGiaNiemYet());

            cell = row.createCell(5, CellType.NUMERIC);
            cell.setCellValue(bt.getGiaBan());

            cell = row.createCell(6, CellType.STRING);
            cell.setCellValue((bt.getTrangThai() == 0) ? "Hoạt động" : "Không hoạt động");

            cell = row.createCell(7, CellType.NUMERIC);
            cell.setCellValue(bt.getTongDaBan());
        }
        String path = "D:\\1. Backend\\DuAn1\\quanlybantui\\quanlybantui\\documents\\excel\\" + sp.getMaSanPham() + " " + getTimeNow() + ".xlsx";
        File file = new File(path);
        try {
            FileOutputStream fis = new FileOutputStream(file);
            wordkbook.write(fis);
            fis.close();
            return path;
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;   // triển khai
    }

    @Override
    public boolean importExcel(File file) {
        return false;   // triển khai
    }

    @Override
    public void exportMauExcel() {
        // triển khai
    }

}
