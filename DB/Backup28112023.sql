-- sửa phieu_giao_hang cho nghĩa
--CREATE DATABASE LONG_HUNG_NGHIA3_Yen_Luat4
--USE LONG_HUNG_NGHIA3_Yen_Luat4
--GO


-- Mạnh
CREATE TABLE phan_quyen (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    ten_quyen NVARCHAR(50) NOT NULL
);

CREATE TABLE nhan_vien (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    username NVARCHAR(50) NOT NULL,
    ten NVARCHAR(100) NOT NULL,
    ma_dinh_danh NVARCHAR(10) NOT NULL,
    so_dien_thoai NVARCHAR(15) NOT NULL,
    ngay_sinh DATE NOT NULL,
    email NVARCHAR(100) NOT NULL,
    gioi_tinh BIT NOT NULL,
    dia_chi NVARCHAR(200) NOT NULL,
    trang_thai BIT NOT NULL,
    id_phan_quyen BIGINT NOT NULL,
    thoi_gian_tao DATETIME NOT NULL DEFAULT GETDATE(),
    thoi_gian_sua DATETIME NOT NULL DEFAULT GETDATE(),
    FOREIGN KEY (id_phan_quyen) REFERENCES phan_quyen(id)
);

-- Thêm 5 bản ghi vào bảng phan_quyen
INSERT INTO phan_quyen (ten_quyen)
VALUES 
    (N'Quyền 1'),
    (N'Quyền 2'),
    (N'Quyền 3'),
    (N'Quyền 4'),
    (N'Quyền 5');

-- Thêm 5 bản ghi vào bảng nhan_vien
INSERT INTO nhan_vien (username, ten, ma_dinh_danh, so_dien_thoai, ngay_sinh, email, gioi_tinh, dia_chi, trang_thai, id_phan_quyen)
VALUES
    (N'user1', N'Nguyen Van A', N'MA001', N'0987654321', '1990-01-01', N'user1@example.com', 1, N'123 Đường ABC, Quận XYZ, TP HCM', 1, 1),
    (N'user2', N'Nguyen Van B', N'MA002', N'0987654322', '1995-02-15', N'user2@example.com', 0, N'456 Đường XYZ, Quận ABC, TP HCM', 1, 2),
    (N'user3', N'Nguyen Van C', N'MA003', N'0987654323', '1988-03-20', N'user3@example.com', 1, N'789 Đường DEF, Quận LMN, TP HCM', 0, 3), -- Đã thay đổi trạng thái thành nghỉ làm
    (N'user4', N'Nguyen Van D', N'MA004', N'0987654324', '1992-04-25', N'user4@example.com', 0, N'012 Đường UVW, Quận XYZ, TP HCM', 1, 4),
    (N'user5', N'Nguyen Van E', N'MA005', N'0987654325', '1997-05-30', N'user5@example.com', 1, N'345 Đường LMN, Quận DEF, TP HCM', 0, 5); -- Đã thay đổi trạng thái thành nghỉ làm
	
-- Long
CREATE TABLE NhaCungCap(
	IDNhaCungCap BIGINT IDENTITY(1,1) NOT NULL PRIMARY KEY,
	TenNhaCungCap NVARCHAR(50) UNIQUE NOT NULL,
	MoTaNhaCungCap NVARCHAR(255) DEFAULT NULL,
	TrangThai BIT NOT NULL,
	SDTNhaCungCap VARCHAR(11) NOT NULL,
	EmailNhaCungCap VARCHAR(255) NOT NULL,
	HopDongThoaThuan VARCHAR(255) NOT NULL,
	ThoiGianTao DATETIME DEFAULT GETDATE(),
	ThoiGianSua DATETIME DEFAULT GETDATE(),
	Ma VARCHAR(6) NOT NULL UNIQUE
) 


INSERT INTO NhaCungCap(TenNhaCungCap,MoTaNhaCungCap,TrangThai,SDTNhaCungCap,EmailNhaCungCap,HopDongThoaThuan, Ma) 
	VALUES	(N'Xưởng đồ da Long Biên', N'Cung cấp đồ da bò chất lượng loại 1',1,'0969315806', 'philong.inco@gmail.com','xuongdodalongbien.docx','CC1111'),
			(N'Xưởng đồ da Cầu Giấy', N'Cung cấp đồ da cá sấu chất lượng loại 2',1,'0976597684', 'vinhlong221097@gmail.com','xuongdodacaugiay.docx','CC1112'),
			(N'Xưởng đồ da Thạch Bàn', N'Cung cấp đồ da đà điểu',1,'0976597333', 'jobdoda11@gmail.com','jobdoda11.docx','CC1113'),
			(N'Xưởng đồ da Thái Bình', N'Cung cấp đồ da cừu',1,'0976597222', 'dodacaocap89@gmail.com','dodacaocap89.docx','CC1114'),
			(N'Xưởng đồ da Vĩnh Long', N'Cung cấp đồ da PU cao cấp',1,'0976595555', 'dapuccaocaphn@gmail.com','dapuccaocaphn.docx','CC1115')





CREATE TABLE Mau(
	IDMau BIGINT IDENTITY(1,1) NOT NULL PRIMARY KEY,
	TenMau NVARCHAR(50) UNIQUE NOT NULL,
	MoTaMau NVARCHAR(255) DEFAULT NULL,
	TrangThai BIT NOT NULL,
	ThoiGianTao DATETIME DEFAULT GETDATE(),
	ThoiGianSua DATETIME DEFAULT GETDATE(),
	Ma VARCHAR(6) NOT NULL
)





INSERT INTO Mau(TenMau,MoTaMau,TrangThai, Ma)
	VALUES	(N'Đỏ', N'Đỏ đậm sang trọng', 1, 'MA1111'),
			(N'Xanh lá', N'Xanh lá đặc biệt', 1, 'MA1112'),
			(N'Vàng', N'Vàng cam tinh tế', 1, 'MA1113'),
			(N'Xanh dương', N'Xanh dương năng động', 1, 'MA1114'),
			(N'Đen', N'Đen bí ẩn quý phái', 1, 'MA1115'),
			(N'Trắng', N'Trắng tinh tế', 0, 'MA1116')

			
CREATE TABLE TheLoai(
	IDTheLoai BIGINT IDENTITY(1,1) NOT NULL PRIMARY KEY,
	MaTheLoai VARCHAR(6) UNIQUE NOT NULL,
	TenTheLoai NVARCHAR(255) UNIQUE NOT NULL,
	MoTaTheLoai NVARCHAR(255) DEFAULT NULL,
	TrangThai BIT NOT NULL,
	ThoiGianTao DATETIME DEFAULT GETDATE(),
	ThoiGianSua DATETIME DEFAULT GETDATE()
)


INSERT INTO TheLoai(MaTheLoai,TenTheLoai,MoTaTheLoai,TrangThai) 
VALUES('TL0006', N'Chưa phân loại', N'Các sản phẩm chưa được phân loại', 1),
		('TL0001', N'Túi hàng hiệu', N'Túi xách hàng hiệu từ các nhãn hàng nổi tiếng', 1),
			('TL0002', N'Túi xách tay', N'Túi xách tay nhỏ gọn thời trang', 1),
			('TL0003', N'Túi đeo vai', N'Túi đeo vai tinh tế cách điệu', 1),
			('TL0004', N'Túi đeo chéo', N'Túi xách đeo chéo linh hoạt', 1),
			('TL0005', N'Balo nữ', N'Balo nữ thời trang', 0)

			
			

CREATE TABLE SanPham(
	IDSanPham BIGINT IDENTITY(1,1) NOT NULL PRIMARY KEY,
	MaSanPham VARCHAR(6) NOT NULL,
	TenSanPham NVARCHAR(255) UNIQUE NOT NULL,
	MoTaSanPham NVARCHAR (255) NOT NULL,
	IDNhaCungCap BIGINT NOT NULL FOREIGN KEY REFERENCES NhaCungCap(IDNhaCungCap),
	TrangThai BIT NOT NULL,
	IDTheLoai BIGINT NOT NULL FOREIGN KEY REFERENCES TheLoai(IDTheLoai),
	IDNhanVien BIGINT NOT NULL FOREIGN KEY REFERENCES nhan_vien(id),
	ThoiGianTao DATETIME DEFAULT GETDATE(),
	ThoiGianSua DATETIME DEFAULT GETDATE()
)

INSERT INTO SanPham(MaSanPham,TenSanPham,MoTaSanPham,IDNhaCungCap,TrangThai,IDTheLoai,IDNhanVien)
	VALUES	('SP0001',N'TÚI ĐEO VAI MINI', N'1 Ngăn lớn, 3 ngăn nhỏ',1,1,3,1),
			('SP0002',N'TÚI XÁCH TAY DỄ THƯƠNG', N'1 Ngăn lớn, 3 ngăn nhỏ',2,1,2,1),
			('SP0003',N'TÚI CHANNEL', N'1 Ngăn lớn, 3 ngăn nhỏ',1,1,2,2),
			('SP0004',N'TÚI HERMES', N'1 Ngăn lớn, 3 ngăn nhỏ',2,1,2,1),
			('SP0005',N'TÚI ĐEO VAI CỠ VỪA', N'1 Ngăn lớn, 3 ngăn nhỏ',1,1,3,1),
			('SP0006',N'TÚI XÁCH TAY QUÝ PHÁI', N'1 Ngăn lớn, 3 ngăn nhỏ',2,1,2,1),
			('SP0007',N'TÚI GUCCI', N'1 Ngăn lớn, 3 ngăn nhỏ',1,1,2,2),
			('SP0008',N'TÚI LV', N'1 Ngăn lớn, 3 ngăn nhỏ',2,1,2,1)

CREATE TABLE AnhSanPham(
	IDAnhSanPham BIGINT IDENTITY(1,1) NOT NULL PRIMARY KEY,
	Link VARCHAR(255) NOT NULL,
	IDSanPham BIGINT NOT NULL FOREIGN KEY REFERENCES SanPham(IDSanPham)
)
INSERT INTO AnhSanPham(Link,IDSanPham) VALUES
	('img/Untitled-1.png',1),
	('img/Untitled-1.png',1),
	('img/Untitled-1.png',1),
	('img/Untitled-1.png',1),

	('img/Untitled-1.png',2),
	('img/Untitled-1.png',2),
	('img/Untitled-1.png',2),
	('img/Untitled-1.png',2),

	('img/Untitled-1.png',3),
	('img/Untitled-1.png',3),
	('img/Untitled-1.png',3),
	('img/Untitled-1.png',3),

	('img/Untitled-1.png',4),
	('img/Untitled-1.png',4),
	('img/Untitled-1.png',4),
	('img/Untitled-1.png',4),

	('img/Untitled-1.png',5),
	('img/Untitled-1.png',5),
	('img/Untitled-1.png',5),
	('img/Untitled-1.png',5),

	('img/Untitled-1.png',6),
	('img/Untitled-1.png',6),
	('img/Untitled-1.png',6),
	('img/Untitled-1.png',6),

	('img/Untitled-1.png',7),
	('img/Untitled-1.png',7),
	('img/Untitled-1.png',7),
	('img/Untitled-1.png',7),

	('img/Untitled-1.png',8),
	('img/Untitled-1.png',8),
	('img/Untitled-1.png',8),
	('img/Untitled-1.png',8)


CREATE TABLE ChuongTrinhBanSi(
	IDChuongTrinhBanSi BIGINT IDENTITY(1,1) NOT NULL PRIMARY KEY,
	SoLuong INT NOT NULL,
	GiaSiApDung FLOAT NOT NULL,
	TrangThai BIT NOT NULL,
	IDSanPham BIGINT NOT NULL FOREIGN KEY REFERENCES SanPham(IDSanPham),
	ThoiHanHieuLuc DATE NOT NULL,
	ThoiGianTao DATETIME DEFAULT GETDATE(),
	ThoiGianSua DATETIME DEFAULT GETDATE()
)
INSERT INTO ChuongTrinhBanSi(SoLuong,GiaSiApDung,TrangThai,IDSanPham,ThoiHanHieuLuc) VALUES
	(5, 250.500, 1, 1, '2023-12-01'),
	(10, 220.800, 1, 1, '2023-12-23'),
	(15, 200.700, 1, 1, '2023-12-22'),
	(30, 180.900, 1, 1, '2023-12-07'),

	(7, 550.900, 1, 2, '2023-12-25'),
	(9, 520.200, 1, 2, '2023-12-27'),
	(13, 490.600, 1, 2, '2023-12-27'),
	(40, 350.200, 1, 2, '2023-12-27')

	
CREATE TABLE SanPhamChiTiet(
	IDSanPhamChiTiet BIGINT IDENTITY(1,1) NOT NULL PRIMARY KEY,
	SoLuong INT NOT NULL,
	TrangThai BIT NOT NULL,
	MainImage VARCHAR(255) NOT NULL,
	IDSanPham BIGINT NOT NULL FOREIGN KEY REFERENCES SanPham(IDSanPham),
	IDMau BIGINT NOT NULL FOREIGN KEY REFERENCES Mau(IDMau),
	GiaBan FLOAT NOT NULL,
	ThoiGianTao DATETIME DEFAULT GETDATE(),
	ThoiGianSua DATETIME DEFAULT GETDATE(),
	Ma VARCHAR(6) NOT NULL UNIQUE,
	GiaNiemYet FLOAT NOT NULL
)


INSERT INTO SanPhamChiTiet(SoLuong,TrangThai,MainImage,IDSanPham,IDMau,GiaBan,GiaNiemYet, Ma) VALUES
	(23, 1, 'tuideovaimini-1.png',1,1,350.000,100.000, 'CT1111'),
	(67, 1, 'tuideovaimini-2.png',1,3,340.000,100.000, 'CT2222'),
	(12, 1, 'tuideovaimini-3.png',1,1,370.000,100.000, 'CT3333'),
	(22, 1, 'tuixachtay-1.png',2,1, 630.000,100.000, 'CT4444'),
	(8, 1, 'tuixachtay-2.png',2,1, 650.000,100.000, 'CT5555'),
	(0, 0, 'tuixachtay-3.png',2,1,615.000,100.000, 'CT6666')
	INSERT INTO SanPhamChiTiet(SoLuong,TrangThai,MainImage,IDSanPham,IDMau,GiaBan,GiaNiemYet, Ma) VALUES
	(23, 1, 'tuideovaimini-1.png',1,1,350.000,100.000, 'CTAAAA'),
	(67, 1, 'tuideovaimini-2.png',1,3,340.000,100.000, 'CTBBBB'),
	(12, 1, 'tuideovaimini-3.png',1,1,370.000,100.000, 'CTCCCC'),
	(22, 1, 'tuixachtay-1.png',2,1, 630.000,100.000, 'CTDDDD'),
	(8, 1, 'tuixachtay-2.png',2,1, 650.000,100.000, 'CTEEEE'),
	(0, 0, 'tuixachtay-3.png',2,1,615.000,100.000, 'CTGGGG'),
	(0, 0, 'tuixachtay-3.png',2,1,615.000,100.000, 'CTFFFF')
	
CREATE TABLE SanPhamChiTietHistory(
	IDSanPhamChiTietHistory BIGINT IDENTITY(1,1) NOT NULL PRIMARY KEY,
	SoLuong INT NOT NULL,
	TrangThai BIT NOT NULL,
	MainImage VARCHAR(255) NOT NULL,
	IDSanPham INT NOT NULL,
	IDLoaiKhoa INT NOT NULL,
	IDMau INT NOT NULL,
	IDChatLieu INT NOT NULL,
	GiaNiemYet FLOAT NOT NULL,
	GiaBan FLOAT NOT NULL,
	NgaySaoLuu DATE NOT NULL,
	IDSanPhamChiTiet BIGINT NOT NULL FOREIGN KEY REFERENCES SanPhamChiTiet(IDSanPhamChiTiet)
)
INSERT INTO SanPhamChiTietHistory(SoLuong,TrangThai,MainImage,IDSanPham,IDLoaiKhoa,IDMau,IDChatLieu,GiaNiemYet,GiaBan,NgaySaoLuu,IDSanPhamChiTiet) VALUES
	(23, 1, 'tuideovaimini-1.png',1,1,1,1,450.000,350.000, GETDATE(),1),
	(23, 1, 'tuideovaimini-1.png',1,1,1,1,750.000,750.000, GETDATE(),1),
	(23, 1, 'tuideovaimini-1.png',1,1,1,1,755.000,750.000, GETDATE(),1),
	(23, 1, 'tuideovaimini-1.png',1,1,1,1,754.000,750.000, GETDATE(),1),
	(23, 1, 'tuideovaimini-1.png',1,1,1,1,751.000,750.000, GETDATE(),1)
	
-- Yến
-- Tạo bảng trang_thai_voucher
CREATE TABLE trang_thai_voucher (
    id BIGINT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	ma_trang_thai NVARCHAR(100) NOT NULL,
    ten_trang_thai NVARCHAR(100) NOT NULL,
	mo_ta NVARCHAR(100)
);

-- Chèn dữ liệu vào bảng trang_thai_voucher
INSERT INTO trang_thai_voucher (ma_trang_thai, ten_trang_thai, mo_ta)
VALUES
    ('TT001', N'Hoạt động', N'Voucher hiện đang hoạt động'),
    ('TT002', N'Hết hạn', N'Voucher đã hết hạn'),
    ('TT003', N'Đã sử dụng', N'Voucher đã được sử dụng');

-- Tạo bảng loai_voucher
CREATE TABLE loai_voucher (
    id BIGINT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	ma_loai NVARCHAR(100) NOT NULL,
    ten_loai NVARCHAR(100) NOT NULL,
	mo_ta NVARCHAR(100)
);

-- Chèn dữ liệu vào bảng loai_voucher
INSERT INTO loai_voucher (ma_loai, ten_loai, mo_ta)
VALUES
    ('LV001', N'Giảm giá theo tiền', N'Voucher cung cấp giảm giá'),
    ('LV002', N'Giảm giá theo phần trăm', N'Voucher tặng sản phẩm kèm theo mua hàng');

CREATE TABLE voucher (
    id BIGINT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	ma_voucher NVARCHAR(100) NOT NULL,
    ten_voucher NVARCHAR(100) NOT NULL,
	loai_voucher BIGINT FOREIGN KEY REFERENCES loai_voucher(id),
    muc_giam_gia INT NOT NULL,
    thoi_gian_bat_dau DATE NOT NULL,
    thoi_gian_ket_thuc DATE NOT NULL,
    thoi_gian_sua DATETIME NOT NULL DEFAULT GETDATE(),
    thoi_gian_tao DATETIME NOT NULL DEFAULT GETDATE(),
	so_luong INT NOT NULL,
	trang_thai BIGINT FOREIGN KEY REFERENCES trang_thai_voucher(id)
);

-- Thêm 10 bản ghi cho bảng voucher
INSERT INTO voucher (ma_voucher, ten_voucher, loai_voucher, muc_giam_gia, thoi_gian_bat_dau, thoi_gian_ket_thuc, so_luong, trang_thai)
VALUES
    ('V001', N'Giảm 50k', 1, 50000, '2023-01-01', '2023-02-01', 100, 1),
    ('V002', N'Giảm 10%', 2, 10, '2023-01-15', '2023-02-15', 200, 2),
    ('V003', N'Giảm 100k', 1, 100000, '2023-02-01', '2023-03-01', 150, 1),
    ('V004', N'Giảm 20%', 2, 20, '2023-02-15', '2023-03-15', 50, 2),
    ('V005', N'Giảm 30k', 1, 30000, '2023-03-01', '2023-04-01', 120, 1),
    ('V006', N'Giảm 15%', 2, 15, '2023-03-15', '2023-04-15', 80, 1),
    ('V007', N'Giảm 200k', 1, 200000, '2023-04-01', '2023-05-01', 150, 2),
	('V008', N'Giảm 25k', 1, 25000, '2023-04-15', '2023-05-15', 60, 1),
    ('V009', N'Giảm 50%', 2, 50, '2023-05-01', '2023-06-01', 180, 2),
    ('V010', N'Giảm 150k', 1, 150000, '2023-05-15', '2023-06-15', 100, 1);
	
	-- Tạo bảng voucher_history
CREATE TABLE voucher_history (
    id BIGINT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	ma_voucher_history NVARCHAR(100) NOT NULL,
	id_voucher BIGINT FOREIGN KEY REFERENCES voucher(id),
	thoi_gian_su_dung DATETIME NOT NULL DEFAULT GETDATE(),
    so_tien_giam_gia INT NOT NULL,
	so_tien_before_giam_gia INT NOT NULL,
	so_tien_after_giam_gia INT NOT NULL,
	ghi_chu NVARCHAR(MAX) 
); 

-- Them 10 ban ghi bang voucher_history
INSERT INTO voucher_history ( ma_voucher_history, id_voucher, thoi_gian_su_dung, so_tien_giam_gia, so_tien_before_giam_gia, so_tien_after_giam_gia, ghi_chu)
VALUES
    ('VH1',1, '2023-01-05 12:30:00', 10, 100, 90, N'Sử dụng voucher cho đơn hàng A'),
    ('VH2',2, '2023-02-10 14:45:00', 15, 200, 185, N'Sử dụng voucher cho đơn hàng B'),
    ('VH3',3,'2023-03-15 10:00:00', 20, 150, 130, N'Sử dụng voucher cho đơn hàng C'),
	('VH4',4, '2023-04-01 08:00:00', 10, 120, 110, N'Sử dụng voucher cho đơn hàng D'),
    ('VH5',5, '2023-05-12 16:30:00', 25, 180, 155, N'Sử dụng voucher cho đơn hàng E'),
    ('VH6',6, '2023-06-20 11:15:00', 15, 90, 75, N'Sử dụng voucher cho đơn hàng F'),
    ('VH7',7, '2023-07-03 09:45:00', 12, 150, 138, N'Sử dụng voucher cho đơn hàng G'),
    ('VH8',8, '2023-08-18 14:00:00', 18, 200, 182, N'Sử dụng voucher cho đơn hàng H'),
	('VH9',9, '2023-02-10 14:45:00', 15, 200, 185, N'Sử dụng voucher cho đơn hàng K'),
    ('VH10',10, '2023-10-20 18:20:00', 5, 50, 45, N'Sử dụng voucher cho đơn hàng J');



	INSERT INTO voucher (ma_voucher, ten_voucher, loai_voucher, muc_giam_gia, thoi_gian_bat_dau, thoi_gian_ket_thuc, so_luong, trang_thai)
	VALUES ('V1','VOUCHER1',2,10,'2023-01-01', '2023-02-01',10,1);


	
-- Hùng --
CREATE TABLE khach_hang(
	id BIGINT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	ho_ten NVARCHAR(50) NOT NULL,
	gioi_tinh BIT,
	sdt VARCHAR(12),
	dia_chi NVARCHAR(255) ,
	email VARCHAR(125) NOT NULL,
	ngay_sinh DATE ,
	cap_bac int,
	thoi_gian_tao DATE,
	thoi_gian_sua DATE,
	is_delete bit,
	ma varchar(20),
	id_nhanvien BIGINT,
	FOREIGN KEY (id_nhanvien) REFERENCES nhan_vien(id),
)

INSERT INTO khach_hang (ho_ten, gioi_tinh, sdt, dia_chi, email, ngay_sinh, cap_bac, thoi_gian_tao, thoi_gian_sua, is_delete, ma,id_nhanvien)
VALUES
    (N'Nguyen Van An', 1, '0987654321', N'Quỳnh Lưu - Nghệ An', 'nguyenvana@example.com', '2000-01-15', 1, GETDATE(), GETDATE(), 0, 'KH001',1),
    (N'Tran Thi Bình', 0, '0123456789', N'Thanh Trì - Hà Nội', 'tranthib@example.com', '1995-05-20', 2, GETDATE(), GETDATE(), 0,'KH002',2),
    (N'Le Van Chính', 1, '0978563412', N'Đống đa - Hà Nội', 'levanc@example.com', '1980-09-10', 1, GETDATE(), GETDATE(),0,'KH003',1),
    (N'Pham Van Dũng', 1, '0956743218', N'63 Lê đức thọ - Hà Nội', 'phamvand@example.com', '1992-03-25', 3, GETDATE(), GETDATE(),0,'KH004',2),
    (N'Nguyen Thi Em', 0, '0912345678', N'Thanh Hóa', 'nguyenthe@example.com', '1998-07-30', 1, GETDATE(), GETDATE(),0, 'KH005',1),
    (N'Tran Van Phong', 1, '0945632178', N'Quỳnh Long - Nghệ An', 'tranvanf@example.com', '1975-11-05', 2, GETDATE(), GETDATE(),0,'KH006',2),
    (N'Le Thi Giang', 0, '0909876543', N'Bình Định', 'lethig@example.com', '1988-12-12', 1, GETDATE(), GETDATE(),0,'KH007',1),
    (N'Pham Thi Hương', 0, '0932167845', N'Đô Lương - Nghệ An', 'phamthih@example.com', '1990-02-28', 3, GETDATE(), GETDATE(),0,'KH008',1),
    (N'Nguyen Van Yến', 1, '0965432178', N'Tràng an - Ninh Bình', 'nguyenvani@example.com', '2002-08-18', 1, GETDATE(), GETDATE(),0,'KH009',1),
    (N'Tran Van Khiên', 1, '0978563421', N'Kiểu mai - Hà Nội', 'tranvank@example.com', '1994-06-10', 2, GETDATE(), GETDATE(),0,'KH0010',2);

	

	-- Hóa đơn --
	CREATE TABLE HoaDon(
		IDHoaDon BIGINT IDENTITY(1,1) NOT NULL PRIMARY KEY,
		GhiChu NVARCHAR(255),
		TongTienHoaDon FLOAT,
		TongTienSauKhuyenMai FLOAT,
		IDNhanVien BIGINT NOT NULL FOREIGN KEY REFERENCES nhan_vien(id),
		IDKhachHang BIGINT FOREIGN KEY REFERENCES khach_hang(id),
		IDVoucher BIGINT FOREIGN KEY REFERENCES voucher(id),
		SoTienDaGiam FLOAT,
		TongTienPhaiTra FLOAT,
		MaHoaDon VARCHAR(6) NOT NULL,
		ThoiGianSua DATETIME DEFAULT GETDATE(),
		THoiGianTao DATETIME DEFAULT GETDATE(),
		TrangThai INT NOT NULL,
		TienMatKhachTra FLOAT, --
		HinhThucThanhToan INT, --
		HinhThuc INT 
	)


	

INSERT INTO HoaDon (GhiChu, TongTienHoaDon, TongTienSauKhuyenMai, IDNhanVien, IDKhachHang, IDVoucher, TrangThai, SoTienDaGiam, TongTienPhaiTra, MaHoaDon, ThoiGianSua, THoiGianTao, TienMatKhachTra,HinhThucThanhToan)
VALUES
	('Giao dịch mua hàng', 1000000, 900000, 1, 1, 1, 1, 10000, 890000, 'HD0001', GETDATE(),GETDATE(),1334.5,1),
	('Giao dịch mua hàng', 1500000, 1400000, 2, 2, 2, 1, 10000, 1390000, 'HD0002', GETDATE(),GETDATE(),133.5,1),
	('Giao dịch mua hàng', 1200000, 1100000, 4, 4, 4, 1, 10000, 1090000, 'HD0010', GETDATE(),GETDATE(),133.5,2),
	('Giao dịch mua hàng', 900000, 850000, 1, 1, 1, 1, 5000, 845000, 'HD0011', GETDATE(),GETDATE(),111.5,3),
	('Giao dịch mua hàng', 1100000, 1000000, 2, 2, 2, 1, 10000, 990000, 'HD0012', GETDATE(),GETDATE(),1567.5,3),
	('Giao dịch mua hàng', 750000, 700000, 3, 3, 3, 1, 5000, 695000, 'HD0013', GETDATE(),GETDATE(),223.5,2),
	('Giao dịch mua hàng', 1300000, 1200000, 4, 4, 4, 1, 10000, 1190000, 'HD0014', GETDATE(),GETDATE(),333.7,1),
	('Giao dịch mua hàng', 950000, 900000, 1, 1, 1, 1, 5000, 895000, 'HD0015', GETDATE(),GETDATE(),2122.56,2),
	('Giao dịch mua hàng', 1200000, 1100000, 2, 2, 2, 1, 10000, 1090000, 'HD0016', GETDATE(),GETDATE(),122.9,1);
	


CREATE TABLE HoaDon_SanPhamChiTiet(
	ID BIGINT IDENTITY(1,1) NOT NULL PRIMARY KEY,
	IDHoaDon BIGINT NOT NULL FOREIGN KEY REFERENCES HoaDon(IDHoaDon),
	IDSanPhamChiTiet BIGINT NOT NULL FOREIGN KEY REFERENCES SanPhamChiTiet(IDSanPhamChiTiet),
	GiaTien FLOAT NOT NULL,
	SoLuong INT NOT NULL
)
INSERT INTO HoaDon_SanPhamChiTiet(IDHoaDon, IDSanPhamChiTiet, GiaTien, SoLuong) VALUES 
	(1,1,470.83,2),
	(1,2,270.84,4),
	(2,3,470.81,7),
	(2,4,660.89,5), 
	(3,5,220.82,1)

-- Nghĩa --

CREATE TABLE phieu_giao_hang (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    ma NVARCHAR(50),
	nguoi_nhan NVARCHAR(255),
	sdt_nguoi_nhan NVARCHAR(255),
	dia_chi NVARCHAR(255),
	nguoi_ship NVARCHAR(255),
	sdt_nguoi_ship NVARCHAR(255),
	thoi_gian_nhan BIGINT,
	mo_ta NVARCHAR(255),
	thoi_gian_giao BIGINT,
    trang_thai BIT,
	IDHoaDon BIGINT,
)

-- Thêm dữ liệu vào bảng phieu_giao_hang
INSERT INTO phieu_giao_hang (ma, nguoi_nhan, sdt_nguoi_nhan, dia_chi, nguoi_ship, sdt_nguoi_ship, thoi_gian_giao, thoi_gian_nhan, mo_ta, IDHoaDon, trang_thai)
VALUES
    ('MSP001', N'Nguyen Thi A', '0849070512', N'BacTuLiem 1', 'Nguyen văn c', '0849070512', 1636336800, 1636336800, N'Mô tả cho bản ghi 1', 1, 1),
    ('MSP002', N'Nguyen Văn B', '084907052', N'BacTuLiem 2', 'Nguyen văn c', '084907052', 1636336900, 1636336900, N'Mô tả cho bản ghi 2', 2, 1),
    ('MSP003', N'Nguyen Thi C', '0849070515', N'BacTuLiem 3', 'Nguyen văn c', '0849070515', 1636337000, 1636337000, N'Mô tả cho bản ghi 3', 3, 1),
    ('MSP004', N'Nguyen Bá D', '0849070516', N'BacTuLiem 4', 'Nguyen văn c', '0849070516', 1636337100, 1636337100, N'Mô tả cho bản ghi 4', 4, 1),
    ('MSP005', N'Nguyen Thi E', '0849070542', N'BacTuLiem 5', 'Nguyen văn c', '0849070542', 1636337200, 1636337200, N'Mô tả cho bản ghi 5', 5, 1),
    ('MSP006', N'Nguyen Bảo F', '0849070562', N'BacTuLiem 6', 'Nguyen văn c', '0849070562', 1636337300, 1636337300, N'Mô tả cho bản ghi 6', 6, 1),
    ('MSP007', N'Nguyen Thi G', '0849070512', N'BacTuLiem 7', 'Nguyen văn c', '0849070512', 1636337400, 1636337400, N'Mô tả cho bản ghi 7', 7, 1),
    ('MSP008', N'Nguyen Bách H', '0849070517', N'BacTuLiem 8', 'Nguyen văn c', '0849070517', 1636337500, 1636337500, N'Mô tả cho bản ghi 8', 8, 1),
    ('MSP009', N'Nguyen Hợp T', '0849070512', N'BacTuLiem 9', 'Nguyen văn c', '0849070512', 1636337600, 1636337600, N'Mô tả cho bản ghi 9', 9, 1),
    ('MSP010', N'Nguyen Thi U', '0849070562', N'BacTuLiem 10', 'Nguyen văn c', '0849070562', 1636337700, 1636337700, N'Mô tả cho bản ghi 10', 10, 1);
--CREATE TABLE phieu_giao_hang (
--    id BIGINT IDENTITY(1,1) PRIMARY KEY,
--    ma NVARCHAR(50),
--    so_luong BIGINT,
--    gia_tri BIGINT,
--    gia_giam_toi_da BIGINT,
--    gia_giam_toi_thieu BIGINT,
--	thoi_gian_sua BIGINT,
--	thoi_gian_tao BIGINT,
--	mo_ta NVARCHAR(MAX),
--    trang_thai BIT,
--)



-- Thêm dữ liệu vào bảng phieu_giao_hang
--INSERT INTO phieu_giao_hang (ma, so_luong, gia_tri, gia_giam_toi_da, gia_giam_toi_thieu, thoi_gian_sua, thoi_gian_tao, mo_ta, trang_thai)
--VALUES
--    ('MSP001', 10, 1000, 50, 10, 1636336800, 1636336800, N'Mô tả cho bản ghi 1', 1),
--    ('MSP002', 5, 500, 30, 5, 1636336900, 1636336900, N'Mô tả cho bản ghi 2', 0),
--    ('MSP003', 7, 700, 40, 8, 1636337000, 1636337000, N'Mô tả cho bản ghi 3', 1),
--    ('MSP004', 8, 800, 45, 9, 1636337100, 1636337100, N'Mô tả cho bản ghi 4', 0),
--    ('MSP005', 12, 1200, 60, 12, 1636337200, 1636337200, N'Mô tả cho bản ghi 5', 1),
--    ('MSP006', 3, 300, 20, 4, 1636337300, 1636337300, N'Mô tả cho bản ghi 6', 0),
--    ('MSP007', 9, 900, 50, 10, 1636337400, 1636337400, N'Mô tả cho bản ghi 7', 1),
--    ('MSP008', 15, 1500, 70, 14, 1636337500, 1636337500, N'Mô tả cho bản ghi 8', 0),
--    ('MSP009', 6, 600, 35, 7, 1636337600, 1636337600, N'Mô tả cho bản ghi 9', 1),
--    ('MSP010', 11, 1100, 55, 11, 1636337700, 1636337700, N'Mô tả cho bản ghi 10', 0);


-- khuyến mại sản phẩm 


CREATE TABLE khuyen_mai (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    ten_khuyen_mai NVARCHAR(100) UNIQUE,
	ma_khuyen_mai NVARCHAR(50) UNIQUE,
    phan_tram_giam_gia INT,
    thoi_gian_bat_dau BIGINT,
    thoi_gian_ket_thuc BIGINT,
    thoi_gian_sua BIGINT,
    thoi_gian_tao BIGINT,
	trang_thai BIT,
);
-- Thêm 10 bản ghi vào bảng id_khuyen_mai


INSERT INTO khuyen_mai (ten_khuyen_mai ,ma_khuyen_mai, phan_tram_giam_gia, thoi_gian_bat_dau, thoi_gian_ket_thuc, thoi_gian_sua, thoi_gian_tao, trang_thai)
VALUES
    (N'Khuyến mãi 1', N'KM01' , 10, 1636336800, 1636337400, 1636336800, 1636336800,1),
    (N'Khuyến mãi 2', N'KM02', 20, 1636336900, 1636337500, 1636336900, 1636336900,1),
    (N'Khuyến mãi 3', N'KM03', 30, 1636337000, 1636337600, 1636337000, 1636337000,1),
    (N'Khuyến mãi 4', N'KM04', 40, 1636337100, 1636337700, 1636337100, 1636337100,1),
    (N'Khuyến mãi 5', N'KM05', 50, 1636337200, 1636337800, 1636337200, 1636337200,1),
    (N'Khuyến mãi 6', N'KM06',10, 1636337300, 1636337400, 1636337300, 1636337300,1),
    (N'Khuyến mãi 7', N'KM07', 20, 1636337400, 1636337500, 1636337400, 1636337400,1),
    (N'Khuyến mãi 8', N'KM08', 30, 1636337500, 1636337600, 1636337500, 1636337500,1),
    (N'Khuyến mãi 9', N'KM09', 40, 1636337600, 1636337700, 1636337600, 1636337600,1),
    (N'Khuyến mãi 10',N'KM10',50, 1636337700, 1636337800, 1636337700, 1636337700,1);



CREATE TABLE sanphamchitiet_khuyenmai (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    id_san_pham_chi_tiet BIGINT,
    id_khuyen_mai BIGINT,
    xoa_mem BIT,
    thoi_gian_sua BIGINT,
    thoi_gian_tao BIGINT,
	CONSTRAINT FK_id_sanphamchitiet FOREIGN KEY (id_san_pham_chi_tiet) REFERENCES SanPhamChiTiet(IDSanPhamChiTiet),
    CONSTRAINT FK_id_khuyenmai FOREIGN KEY (id_khuyen_mai) REFERENCES khuyen_mai(id)
);


-- Thêm 10 bản ghi vào bảng id_sanphamchitiet_khuyenmai
INSERT INTO sanphamchitiet_khuyenmai (id_san_pham_chi_tiet, id_khuyen_mai, xoa_mem, thoi_gian_sua, thoi_gian_tao)
VALUES
    (1, 1, 0, 1636336800, 1636336800),
    (2, 2, 1, 1636336900, 1636336900),
    (3, 3, 0, 1636337000, 1636337000),
    (4, 4, 1, 1636337100, 1636337100),
    (5, 5, 0, 1636337200, 1636337200),
    (6, 6, 1, 1636337300, 1636337300),
    (7, 7, 0, 1636337400, 1636337400),
    (8, 8, 1, 1636337500, 1636337500),
    (9, 9, 0, 1636337600, 1636337600),
    (10, 10, 1, 1636337700, 1636337700);
INSERT INTO sanphamchitiet_khuyenmai (id_san_pham_chi_tiet, id_khuyen_mai, xoa_mem, thoi_gian_sua, thoi_gian_tao)
VALUES
    (1, 2, 1, 1636336800, 1636336800)

-- Luật --
-- Luật --

create table trang_thai_phieu_doi(
	id BIGINT identity(1,1) primary key,
	ten_trang_thai nvarchar(50),
	mo_ta nvarchar(200)
)

create table ly_do_doi(
	id BIGINT identity(1,1) primary key,
	ly_do nvarchar(100),
	mo_ta nvarchar(200)
)

create table phieu_doi(
	id BIGINT identity(1,1) primary key,
	ghi_chu nvarchar(200),
	ngay_doi  date,
	id_hoa_don BIGINT  foreign key references HoaDon(IDHoaDon),
	id_trang_thai BIGINT foreign key references trang_thai_phieu_doi(id),
	id_nhan_vien BIGINT foreign key references nhan_vien(id),
	ngay_tao date,
	ngay_sua date
) 
 
create table phieu_doi_chi_tiet (
	id BIGINT identity(1,1) primary key,
	gia_ban float , 
	ten_san_pham nvarchar(100),
	mau nvarchar(50),
	chat_lieu nvarchar(100),
	so_luong_doi int,
	id_san_pham_chi_tiet BIGINT foreign key references SanPhamChiTiet(IDSanPhamChiTiet),
	id_ly_do_doi BIGINT foreign key references ly_do_doi(id),
	id_phieu_doi BIGINT foreign key references phieu_doi(id),
	mota nvarchar(200)
)
 INSERT INTO ly_do_doi (ly_do, mo_ta)
VALUES 
	(N'Sản phẩm lỗi', N'Sản phẩm bị lỗi trong quá trình sản xuất hoặc có vấn đề kỹ thuật.'),
    (N'Sản phẩm có dấu hiệu đã qua sử dụng', N'Sản phẩm có dấu vết cho thấy nó đã sử dụng trước đó.'),
	 (N'Sản phẩm bị rách', N'Sản phẩm bị rách do quá trình sản xuất hoặc quá trình vận chuyển.'),  
    (N'Sản phẩm không đúng với kích thước và màu sắc', N'Sản phẩm không đúng về kích thước hoặc màu sắc.'), 
    (N'Lỗi kỹ thuật do nhà sản xuất', N'Sản phẩm gặp lỗi hoặc vấn đề kỹ thuật do quá trình sản xuất.');
INSERT INTO trang_thai_phieu_doi (ten_trang_thai, mo_ta)
VALUES
    (N'Chờ xác nhận', N'Đang chờ xác nhận từ phía cửa hàng'),
    (N'Đã xác nhận', N'Phiếu đổi đã được xác nhận'),
    (N'Đã hoàn thành', N'Phiếu đổi đã hoàn thành'),
    (N'Đã hủy', N'Phiếu đổi đã bị hủy'),
    (N'Đang xử lý', N'Phiếu đổi đang trong quá trình xử lý');

 INSERT INTO  phieu_doi (ghi_chu, ngay_doi, id_hoa_don, id_trang_thai, id_nhan_vien,ngay_sua,ngay_tao)
 VALUES  
    (N'Khách hàng đổi sản phẩm lỗi', CURRENT_TIMESTAMP, 1, 1, 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (N'Khách hàng đổi sản phẩm không đúng màu', '2023-11-05', 2, 2, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
 (N'Khách hàng đổi sản phẩm không đúng kích thước', '2023-11-04', 3, 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
   (N'Khách hàng đổi sản phẩm bị rách', '2023-11-03', 4, 1, 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
   (N'Khách hàng đổi sản phẩm hỏng do quá trình vận chuyển', '2023-11-02', 5, 3, 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
  INSERT INTO phieu_doi_chi_tiet (gia_ban, ten_san_pham, mau, so_luong_doi, id_san_pham_chi_tiet, id_phieu_doi,id_ly_do_doi,mota)
VALUES
     (25.5, N'Túi sách da bò', 'Đen',  2, 1, 1,1,N'ảo thật đấy'),
   (30.0, N'Túi sách da trâu', 'Xanh',  1, 2, 2,2,N'ảo thật đấy'),
    (15.0, N'Túi sách da heo', 'Trắng',  3, 3, 3,3,N'ảo thật đấy'),
   (40.0, N'Túi sách da cá sấu', 'Xám',  1, 4, 4,4,N'ảo thật đấy'),
   (12.5, N'Túi sách da cáo', 'Đỏ', 2, 5, 5,5,N'ảo thật đấy');



	--SELECT * FROM NhaCungCap
	--		SELECT * FROM TheLoai
	--		SELECT * FROM  khach_hang
	--		SELECT * FROM SanPham
	--		SELECT * FROM SanPhamChiTiet

	--select * from khuyen_mai
	--select * from sanphamchitiet_khuyenmai
	--select * from phieu_doi_chi_tiet


	--SELECT * FROM sanphamchitiet_khuyenmai