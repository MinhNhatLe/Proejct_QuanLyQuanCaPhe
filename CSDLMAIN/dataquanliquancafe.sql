USE [QuanLiQuanCaPhe]
GO
/****** Object:  Table [dbo].[Loai]    Script Date: 5/28/2023 11:29:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Loai](
	[SPLoai] [nvarchar](50) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[SPLoai] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Menu]    Script Date: 5/28/2023 11:29:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Menu](
	[SPMa] [char](5) NOT NULL,
	[SPLoai] [nvarchar](50) NULL,
	[SPTen] [nvarchar](100) NULL,
	[SPDonVi] [nvarchar](10) NULL,
	[SPGia] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[SPMa] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NhanVien]    Script Date: 5/28/2023 11:29:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NhanVien](
	[NVMa] [char](5) NOT NULL,
	[NVHoTen] [nvarchar](100) NOT NULL,
	[NVGioiTinh] [nvarchar](3) NOT NULL,
	[NVNgaySinh] [date] NULL,
	[NVQue] [nvarchar](50) NULL,
	[NVSDT] [char](11) NULL,
	[NVCMND] [char](12) NULL,
	[NVTenDangNhap] [char](20) NULL,
	[NVMatKhau] [char](20) NULL,
PRIMARY KEY CLUSTERED 
(
	[NVMa] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ThongKe]    Script Date: 5/28/2023 11:29:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ThongKe](
	[TKBan] [char](5) NULL,
	[NVHoTen] [nvarchar](100) NULL,
	[TKNgay] [nvarchar](20) NOT NULL,
	[TKThoiGian] [nvarchar](20) NOT NULL,
	[TKTongTien] [int] NULL,
	[TKTienKhach] [int] NULL,
	[TKTienThua] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[TKNgay] ASC,
	[TKThoiGian] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Menu]  WITH CHECK ADD FOREIGN KEY([SPLoai])
REFERENCES [dbo].[Loai] ([SPLoai])
GO
