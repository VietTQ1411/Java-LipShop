USE [shop]
GO
/****** Object:  Table [dbo].[account]    Script Date: 3/13/2020 10:59:49 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[account](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[user_name] [nchar](150) NULL,
	[password] [nchar](150) NULL,
	[name] [nvarchar](500) NULL,
	[date_create] [date] NULL,
	[acc_type] [int] NULL,
	[status] [int] NULL,
 CONSTRAINT [PK_account] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[brand_type]    Script Date: 3/13/2020 10:59:49 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[brand_type](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[brand_id] [int] NOT NULL,
	[type_id] [int] NOT NULL,
 CONSTRAINT [PK_brand_type] PRIMARY KEY CLUSTERED 
(
	[id] ASC,
	[brand_id] ASC,
	[type_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[brands]    Script Date: 3/13/2020 10:59:49 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[brands](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](300) NULL,
	[status] [bit] NULL,
 CONSTRAINT [PK_brands] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[coupons]    Script Date: 3/13/2020 10:59:49 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[coupons](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[code] [nchar](1000) NULL,
	[value] [int] NULL,
	[ship] [float] NULL,
	[status] [int] NULL,
 CONSTRAINT [PK_coupons] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[customer_info]    Script Date: 3/13/2020 10:59:50 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[customer_info](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[acc_id] [int] NOT NULL,
	[name] [nvarchar](100) NULL,
	[mobile] [nvarchar](15) NULL,
	[email] [nvarchar](100) NULL,
	[gender] [bit] NULL,
	[address] [nvarchar](2000) NULL,
	[avatar] [nvarchar](100) NULL,
 CONSTRAINT [PK__customer__3213E83FA28FEFA4] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[images]    Script Date: 3/13/2020 10:59:50 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[images](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[product_id] [int] NULL,
	[image_name] [nvarchar](300) NULL,
	[cover] [bit] NULL,
	[status] [int] NULL,
 CONSTRAINT [PK_images] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[order]    Script Date: 3/13/2020 10:59:50 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[order](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[customer_info_id] [int] NULL,
	[receiver_id] [int] NULL,
	[total_price] [float] NULL,
	[coupons_id] [int] NULL,
	[note] [nvarchar](1000) NULL,
	[create_date] [date] NULL,
	[status] [int] NULL,
 CONSTRAINT [PK__order__3213E83F2AD0BDBB] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[order_detail]    Script Date: 3/13/2020 10:59:50 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[order_detail](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[order_id] [int] NULL,
	[product_id] [int] NULL,
	[product_name] [nvarchar](200) NULL,
	[product_price] [decimal](18, 0) NULL,
	[quantity] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[products]    Script Date: 3/13/2020 10:59:50 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[products](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[brand_id] [int] NULL,
	[name] [nvarchar](300) NULL,
	[price] [float] NULL,
	[sale] [int] NULL,
	[quantity] [int] NULL,
	[type] [int] NULL,
	[color] [nvarchar](300) NULL,
	[description] [nvarchar](2000) NULL,
	[sold] [int] NULL,
	[rate] [float] NULL,
	[status] [int] NULL,
 CONSTRAINT [PK_products] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[receiver]    Script Date: 3/13/2020 10:59:50 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[receiver](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](300) NULL,
	[mobile] [nchar](100) NULL,
	[address] [nvarchar](max) NULL,
 CONSTRAINT [PK_receiver] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[type_style]    Script Date: 3/13/2020 10:59:50 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[type_style](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](150) NULL,
 CONSTRAINT [PK_type_style] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[types]    Script Date: 3/13/2020 10:59:50 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[types](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[style] [int] NULL,
	[name] [nvarchar](300) NULL,
	[description] [nvarchar](3000) NULL,
 CONSTRAINT [PK_types] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[account] ON 

INSERT [dbo].[account] ([id], [user_name], [password], [name], [date_create], [acc_type], [status]) VALUES (13, N'viet                                                                                                                                                  ', N'123                                                                                                                                                   ', N'Trần Việt', CAST(N'2018-10-18' AS Date), 0, 1)
INSERT [dbo].[account] ([id], [user_name], [password], [name], [date_create], [acc_type], [status]) VALUES (14, N'duong                                                                                                                                                 ', N'123                                                                                                                                                   ', N'Minh Dương', CAST(N'2018-10-18' AS Date), 1, 1)
INSERT [dbo].[account] ([id], [user_name], [password], [name], [date_create], [acc_type], [status]) VALUES (38, N'viettqhe130524@fpt.edu.vn                                                                                                                             ', N'a                                                                                                                                                     ', N'A A', CAST(N'2019-11-04' AS Date), 2, 1)
SET IDENTITY_INSERT [dbo].[account] OFF
SET IDENTITY_INSERT [dbo].[brand_type] ON 

INSERT [dbo].[brand_type] ([id], [brand_id], [type_id]) VALUES (1, 1, 2)
INSERT [dbo].[brand_type] ([id], [brand_id], [type_id]) VALUES (2, 1, 3)
INSERT [dbo].[brand_type] ([id], [brand_id], [type_id]) VALUES (3, 1, 4)
INSERT [dbo].[brand_type] ([id], [brand_id], [type_id]) VALUES (4, 2, 1)
INSERT [dbo].[brand_type] ([id], [brand_id], [type_id]) VALUES (5, 2, 2)
INSERT [dbo].[brand_type] ([id], [brand_id], [type_id]) VALUES (6, 2, 3)
SET IDENTITY_INSERT [dbo].[brand_type] OFF
SET IDENTITY_INSERT [dbo].[brands] ON 

INSERT [dbo].[brands] ([id], [name], [status]) VALUES (1, N'3CE', 1)
INSERT [dbo].[brands] ([id], [name], [status]) VALUES (2, N'MAYBELLINE', 1)
INSERT [dbo].[brands] ([id], [name], [status]) VALUES (3, N'MAC', 1)
INSERT [dbo].[brands] ([id], [name], [status]) VALUES (4, N'ESTEE LAUDER', 1)
INSERT [dbo].[brands] ([id], [name], [status]) VALUES (5, N'SILKYGIRL', 1)
INSERT [dbo].[brands] ([id], [name], [status]) VALUES (6, N'LEMONADE', 1)
INSERT [dbo].[brands] ([id], [name], [status]) VALUES (7, N'FABULOUS SEVEN DAYS', 1)
SET IDENTITY_INSERT [dbo].[brands] OFF
SET IDENTITY_INSERT [dbo].[coupons] ON 

INSERT [dbo].[coupons] ([id], [code], [value], [ship], [status]) VALUES (1, N'emptyCoupons                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            ', 0, 0, 0)
INSERT [dbo].[coupons] ([id], [code], [value], [ship], [status]) VALUES (2, N'a1                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      ', 10, 10000, 0)
SET IDENTITY_INSERT [dbo].[coupons] OFF
SET IDENTITY_INSERT [dbo].[customer_info] ON 

INSERT [dbo].[customer_info] ([id], [acc_id], [name], [mobile], [email], [gender], [address], [avatar]) VALUES (1, 13, N'Trần Quang Việt', N'0365469543', NULL, 1, N'Cầu Cáp - Phù Cừ - Hưng Yên', N'unknown.jpg')
INSERT [dbo].[customer_info] ([id], [acc_id], [name], [mobile], [email], [gender], [address], [avatar]) VALUES (6, 14, N'Trần thị minh dương', N'031231312', N'viettqhe130524@fpt.edu.vn', 0, N'Phù cừ - Hưng Yên', N'unknown.jpg')
SET IDENTITY_INSERT [dbo].[customer_info] OFF
SET IDENTITY_INSERT [dbo].[images] ON 

INSERT [dbo].[images] ([id], [product_id], [image_name], [cover], [status]) VALUES (1, 1, N'son-kem-li-nhe-moi-maybelline-01.jpg', 1, NULL)
INSERT [dbo].[images] ([id], [product_id], [image_name], [cover], [status]) VALUES (2, 2, N'son-kem-li-nhe-moi-maybelline-02.jpg', 0, NULL)
INSERT [dbo].[images] ([id], [product_id], [image_name], [cover], [status]) VALUES (3, 1, N'son-kem-li-nhe-moi-maybelline-1.jpg', 0, NULL)
INSERT [dbo].[images] ([id], [product_id], [image_name], [cover], [status]) VALUES (4, 3, N'3CE-LIP.jpg', 1, NULL)
INSERT [dbo].[images] ([id], [product_id], [image_name], [cover], [status]) VALUES (5, 4, N'3CE-LIP.jpg', 1, NULL)
INSERT [dbo].[images] ([id], [product_id], [image_name], [cover], [status]) VALUES (6, 5, N'Son-3CE-Velvet-Liptint-Private-14.jpg', 1, NULL)
INSERT [dbo].[images] ([id], [product_id], [image_name], [cover], [status]) VALUES (7, 6, N'mac-mehr.jpg', 1, NULL)
INSERT [dbo].[images] ([id], [product_id], [image_name], [cover], [status]) VALUES (8, 7, N'MAC-Tropic-Tonic-1.jpg', 0, NULL)
INSERT [dbo].[images] ([id], [product_id], [image_name], [cover], [status]) VALUES (9, 7, N'MAC-Tropic-Tonic.jpg', 1, NULL)
INSERT [dbo].[images] ([id], [product_id], [image_name], [cover], [status]) VALUES (10, 8, N'Mac-Matte-Velvet-Teddy-13.jpg', 1, NULL)
INSERT [dbo].[images] ([id], [product_id], [image_name], [cover], [status]) VALUES (11, 9, N'son-kem-li-mau-cam-dat-01.jpg', 1, NULL)
INSERT [dbo].[images] ([id], [product_id], [image_name], [cover], [status]) VALUES (12, 10, N'son-kem-li-silkygirl-mau-hong-02.jpg', 1, NULL)
INSERT [dbo].[images] ([id], [product_id], [image_name], [cover], [status]) VALUES (13, 11, N'son-kem-li-mau-hong-dat-04.jpg', 1, NULL)
INSERT [dbo].[images] ([id], [product_id], [image_name], [cover], [status]) VALUES (14, 12, N'son-kem-li-mau-do-cam-05.jpg', 1, NULL)
INSERT [dbo].[images] ([id], [product_id], [image_name], [cover], [status]) VALUES (15, 13, N'son-kem-li-mau-nude-06.jpg', 1, NULL)
INSERT [dbo].[images] ([id], [product_id], [image_name], [cover], [status]) VALUES (16, 14, N'son-kem-li-mau-do-9.jpg', 1, NULL)
INSERT [dbo].[images] ([id], [product_id], [image_name], [cover], [status]) VALUES (17, 9, N'son-kem-li-silkygirl-matte-junkie-lip-cream2.jpg', 0, NULL)
INSERT [dbo].[images] ([id], [product_id], [image_name], [cover], [status]) VALUES (18, 9, N'son-kem-li-silkygirl-5-8ml.jpg', 0, NULL)
INSERT [dbo].[images] ([id], [product_id], [image_name], [cover], [status]) VALUES (19, 15, N'son-li-lemonade-mau-do-dat-a02.jpg', 1, NULL)
INSERT [dbo].[images] ([id], [product_id], [image_name], [cover], [status]) VALUES (20, 23, N'son-kem-li-nhe-moi-maybelline-03.jpg', 1, NULL)
INSERT [dbo].[images] ([id], [product_id], [image_name], [cover], [status]) VALUES (21, 24, N'son-kem-li-nhe-moi-maybelline-04.jpg', 1, NULL)
INSERT [dbo].[images] ([id], [product_id], [image_name], [cover], [status]) VALUES (22, 25, N'son-kem-li-nhe-moi-maybelline-05.jpg', 1, NULL)
INSERT [dbo].[images] ([id], [product_id], [image_name], [cover], [status]) VALUES (23, 26, N'son-kem-li-nhe-moi-maybelline-07.jpg', 1, NULL)
INSERT [dbo].[images] ([id], [product_id], [image_name], [cover], [status]) VALUES (24, 27, N'son-kem-li-nhe-moi-maybelline-08.jpg', 1, NULL)
INSERT [dbo].[images] ([id], [product_id], [image_name], [cover], [status]) VALUES (25, 1, N'son-kem-li-nhe-moi-maybelline-3.jpg', 0, NULL)
INSERT [dbo].[images] ([id], [product_id], [image_name], [cover], [status]) VALUES (26, 1, N'son-kem-li-nhe-moi-maybelline-4.jpg', 0, NULL)
INSERT [dbo].[images] ([id], [product_id], [image_name], [cover], [status]) VALUES (27, 16, N'son-li-lemonade-mau-cam-dat-a03.jpg', 1, NULL)
INSERT [dbo].[images] ([id], [product_id], [image_name], [cover], [status]) VALUES (28, 17, N'son-li-lemonade-mau-cam-nau-a04.jpg', 1, NULL)
INSERT [dbo].[images] ([id], [product_id], [image_name], [cover], [status]) VALUES (29, 18, N'son-li-lemonade-mau-hong-do-a01.jpg', 1, NULL)
INSERT [dbo].[images] ([id], [product_id], [image_name], [cover], [status]) VALUES (30, 19, N'son-li-lemonade-mau-do-tuoi-a05.jpg', 1, NULL)
INSERT [dbo].[images] ([id], [product_id], [image_name], [cover], [status]) VALUES (31, 20, N'son-kem-li-mau-cam-07.jpg', 1, NULL)
INSERT [dbo].[images] ([id], [product_id], [image_name], [cover], [status]) VALUES (32, 16, N'son-li-lemonade-1.jpg', 0, NULL)
INSERT [dbo].[images] ([id], [product_id], [image_name], [cover], [status]) VALUES (33, 16, N'1705_-_andybui8922_copy.jpg', 0, NULL)
INSERT [dbo].[images] ([id], [product_id], [image_name], [cover], [status]) VALUES (34, 28, N'son-kem-li-dang-but-fabulous-seven-days-n-3.jpg', 1, NULL)
INSERT [dbo].[images] ([id], [product_id], [image_name], [cover], [status]) VALUES (35, 29, N'son-kem-li-dang-but-fabulous-seven-days-n-4.jpg', 1, NULL)
INSERT [dbo].[images] ([id], [product_id], [image_name], [cover], [status]) VALUES (36, 30, N'son-kem-li-dang-but-fabulous-seven-days-n-5.jpg', 1, NULL)
INSERT [dbo].[images] ([id], [product_id], [image_name], [cover], [status]) VALUES (37, 21, N'son-kem-li-dang-but-fabulous-seven-days-n-1.jpg', 1, NULL)
INSERT [dbo].[images] ([id], [product_id], [image_name], [cover], [status]) VALUES (38, 22, N'son-kem-li-dang-but-fabulous-seven-days-n-2.jpg', 1, NULL)
INSERT [dbo].[images] ([id], [product_id], [image_name], [cover], [status]) VALUES (39, 21, N'son-kem-li-dang-but-fabulous-seven-days-4-5g-7.jpg', 0, NULL)
INSERT [dbo].[images] ([id], [product_id], [image_name], [cover], [status]) VALUES (40, NULL, N'son-kem-li-dang-but-fabulous-seven-days-4-5g-6.jpg', 0, NULL)
INSERT [dbo].[images] ([id], [product_id], [image_name], [cover], [status]) VALUES (41, 34, N'son-mac-rouge-en-snow.jpg', 1, NULL)
INSERT [dbo].[images] ([id], [product_id], [image_name], [cover], [status]) VALUES (42, 34, N'Son-MAC-Rouge-En-Snow-Holiday-2017-Collection-3-510x518.jpg', 0, NULL)
INSERT [dbo].[images] ([id], [product_id], [image_name], [cover], [status]) VALUES (43, 34, N'swatch-son-mac-rouge-en-snow-510x493.jpg', 0, NULL)
INSERT [dbo].[images] ([id], [product_id], [image_name], [cover], [status]) VALUES (44, 35, N'Son-MAC-Fashion-Legacy.jpg', 1, NULL)
INSERT [dbo].[images] ([id], [product_id], [image_name], [cover], [status]) VALUES (45, 35, N'Son-MAC-Fashion-Legacy2.jpg', 0, NULL)
INSERT [dbo].[images] ([id], [product_id], [image_name], [cover], [status]) VALUES (46, 36, N'Phan-ma-hong-Guerlain-Rose-Aux-Joues-1.png', 1, NULL)
INSERT [dbo].[images] ([id], [product_id], [image_name], [cover], [status]) VALUES (47, 37, N'phan-ma-hong-Guerlain-Meteorites-Happy-Glow-Blush.png', 1, NULL)
INSERT [dbo].[images] ([id], [product_id], [image_name], [cover], [status]) VALUES (48, 37, N'phan-ma-hong-Guerlain-Meteorites-Happy-Glow-Blush2.png', 0, NULL)
INSERT [dbo].[images] ([id], [product_id], [image_name], [cover], [status]) VALUES (49, 38, N'kem-duong-am-clinique-turnaround-overnight-revitalizing-moisturizer.png', 1, NULL)
INSERT [dbo].[images] ([id], [product_id], [image_name], [cover], [status]) VALUES (50, 38, N'kem-duong-am-clinique-turnaround-overnight-revitalizing-moisturizer2.png', 0, NULL)
INSERT [dbo].[images] ([id], [product_id], [image_name], [cover], [status]) VALUES (51, 38, N'kem-duong-am-clinique-turnaround-overnight-revitalizing-moisturizer3.png', 0, NULL)
INSERT [dbo].[images] ([id], [product_id], [image_name], [cover], [status]) VALUES (52, 39, N'kem-duong-clinique-moisture-surge-72-hour.png', 1, NULL)
INSERT [dbo].[images] ([id], [product_id], [image_name], [cover], [status]) VALUES (53, 39, N'kem-duong-clinique-moisture-surge-72-hour2.png', 0, NULL)
INSERT [dbo].[images] ([id], [product_id], [image_name], [cover], [status]) VALUES (54, 39, N'kem-duong-clinique-moisture-surge-72-hour3.png', 0, NULL)
INSERT [dbo].[images] ([id], [product_id], [image_name], [cover], [status]) VALUES (55, 40, N'kem-duong-clinique-repairwear-uplifting-firming-cream.png', 1, NULL)
INSERT [dbo].[images] ([id], [product_id], [image_name], [cover], [status]) VALUES (56, 40, N'kem-duong-clinique-repairwear-uplifting-firming-cream2.png', 0, NULL)
INSERT [dbo].[images] ([id], [product_id], [image_name], [cover], [status]) VALUES (57, 40, N'kem-duong-clinique-repairwear-uplifting-firming-cream3.png', 0, NULL)
SET IDENTITY_INSERT [dbo].[images] OFF
SET IDENTITY_INSERT [dbo].[order] ON 

INSERT [dbo].[order] ([id], [customer_info_id], [receiver_id], [total_price], [coupons_id], [note], [create_date], [status]) VALUES (18, 1, 11, 494000, 2, N'giao hàng sớm', CAST(N'2019-11-10' AS Date), 5)
INSERT [dbo].[order] ([id], [customer_info_id], [receiver_id], [total_price], [coupons_id], [note], [create_date], [status]) VALUES (19, 1, 12, 763000, 1, N'', CAST(N'2019-11-10' AS Date), 1)
INSERT [dbo].[order] ([id], [customer_info_id], [receiver_id], [total_price], [coupons_id], [note], [create_date], [status]) VALUES (20, 6, 13, 642000, 1, N'', CAST(N'2019-11-11' AS Date), 2)
INSERT [dbo].[order] ([id], [customer_info_id], [receiver_id], [total_price], [coupons_id], [note], [create_date], [status]) VALUES (1019, 1, 1012, 297000, 1, N'', CAST(N'2019-11-19' AS Date), 5)
INSERT [dbo].[order] ([id], [customer_info_id], [receiver_id], [total_price], [coupons_id], [note], [create_date], [status]) VALUES (1020, 6, 1013, 649000, 1, N'<script>alert("abcjdhak")</script>', CAST(N'2019-11-19' AS Date), 4)
SET IDENTITY_INSERT [dbo].[order] OFF
SET IDENTITY_INSERT [dbo].[order_detail] ON 

INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [product_name], [product_price], [quantity]) VALUES (14, 18, 25, N'Maybelline Sensational Liquid Matte', CAST(99000 AS Decimal(18, 0)), 1)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [product_name], [product_price], [quantity]) VALUES (15, 18, 5, N'3CE Velvet Lip', CAST(395000 AS Decimal(18, 0)), 1)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [product_name], [product_price], [quantity]) VALUES (16, 19, 5, N'3CE Velvet Lip', CAST(395000 AS Decimal(18, 0)), 1)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [product_name], [product_price], [quantity]) VALUES (17, 19, 24, N'Maybelline Sensational Liquid Matte', CAST(99000 AS Decimal(18, 0)), 1)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [product_name], [product_price], [quantity]) VALUES (18, 19, 19, N'LEMONADE Matte Addict', CAST(269000 AS Decimal(18, 0)), 1)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [product_name], [product_price], [quantity]) VALUES (19, 20, 11, N'SILKYGIRL Matte Junkie Lip', CAST(148000 AS Decimal(18, 0)), 1)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [product_name], [product_price], [quantity]) VALUES (20, 20, 5, N'3CE Velvet Lip', CAST(395000 AS Decimal(18, 0)), 1)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [product_name], [product_price], [quantity]) VALUES (21, 20, 24, N'Maybelline Sensational Liquid Matte', CAST(99000 AS Decimal(18, 0)), 1)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [product_name], [product_price], [quantity]) VALUES (1016, 1019, 25, N'Maybelline Sensational Liquid Matte', CAST(99000 AS Decimal(18, 0)), 3)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [product_name], [product_price], [quantity]) VALUES (1017, 1020, 34, N'MAC Rouge En Snow – Snow Ball Holiday 2017 Collection ', CAST(550000 AS Decimal(18, 0)), 1)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [product_name], [product_price], [quantity]) VALUES (1018, 1020, 25, N'Maybelline Sensational Liquid Matte', CAST(99000 AS Decimal(18, 0)), 1)
SET IDENTITY_INSERT [dbo].[order_detail] OFF
SET IDENTITY_INSERT [dbo].[products] ON 

INSERT [dbo].[products] ([id], [brand_id], [name], [price], [sale], [quantity], [type], [color], [description], [sold], [rate], [status]) VALUES (1, 2, N'Maybelline Sensational Liquid Matte Lipstick', 99000, 0, 0, 2, N'01 To The Fullest', N'nothing', 126, 3, 1)
INSERT [dbo].[products] ([id], [brand_id], [name], [price], [sale], [quantity], [type], [color], [description], [sold], [rate], [status]) VALUES (2, 2, N'Maybelline Sensational Liquid Matte Lipstick', 99000, 10, 0, 2, N'02 Soft Wine', N'nothing', 12, 4, 1)
INSERT [dbo].[products] ([id], [brand_id], [name], [price], [sale], [quantity], [type], [color], [description], [sold], [rate], [status]) VALUES (3, 1, N'3CE Matte Lip', 460000, 0, 13, 1, N'227 - cam đất', N'nothing', 34, 2.5, 1)
INSERT [dbo].[products] ([id], [brand_id], [name], [price], [sale], [quantity], [type], [color], [description], [sold], [rate], [status]) VALUES (4, 1, N'3CE Matte Lip ', 460000, 0, 30, 1, N'909 - đỏ đất', N'nothing', 123, 4, 1)
INSERT [dbo].[products] ([id], [brand_id], [name], [price], [sale], [quantity], [type], [color], [description], [sold], [rate], [status]) VALUES (5, 1, N'3CE Velvet Lip', 395000, 0, 27, 1, N'Tint Daffodil – Đỏ Đất', N'nothing', 100, 5, 1)
INSERT [dbo].[products] ([id], [brand_id], [name], [price], [sale], [quantity], [type], [color], [description], [sold], [rate], [status]) VALUES (6, 3, N'MAC Matte Mehr', 480000, 0, 27, 1, N'Hồng đất', N'nothing', 42, 4, 1)
INSERT [dbo].[products] ([id], [brand_id], [name], [price], [sale], [quantity], [type], [color], [description], [sold], [rate], [status]) VALUES (7, 3, N'MAC Matte Tropic Tonic', 480000, 20, 34, 1, N'Cam Đào', N'nothing', 421, 3, 1)
INSERT [dbo].[products] ([id], [brand_id], [name], [price], [sale], [quantity], [type], [color], [description], [sold], [rate], [status]) VALUES (8, 3, N'Son Mac Matte Velvet Teddy', 480000, 10, 34, 1, N'Cam Đất', N'nothing', 42, 4, 1)
INSERT [dbo].[products] ([id], [brand_id], [name], [price], [sale], [quantity], [type], [color], [description], [sold], [rate], [status]) VALUES (9, 5, N'SILKYGIRL Matte Junkie Lip', 119000, 0, 14, 2, N'Cam Đất', N'nothing', 123, 3, 1)
INSERT [dbo].[products] ([id], [brand_id], [name], [price], [sale], [quantity], [type], [color], [description], [sold], [rate], [status]) VALUES (10, 5, N'SILKYGIRL Matte Junkie Lip', 148000, 0, 12, 3, N'Hồng', N'nothing', 41, 2, 1)
INSERT [dbo].[products] ([id], [brand_id], [name], [price], [sale], [quantity], [type], [color], [description], [sold], [rate], [status]) VALUES (11, 5, N'SILKYGIRL Matte Junkie Lip', 148000, 0, 16, 4, N'Hồng Đất', N'nothing', 429, 4, 1)
INSERT [dbo].[products] ([id], [brand_id], [name], [price], [sale], [quantity], [type], [color], [description], [sold], [rate], [status]) VALUES (12, 5, N'SILKYGIRL Matte Junkie Lip', 119000, 10, 0, 3, N'Đỏ Cam', N'nothing', 419, 5, 1)
INSERT [dbo].[products] ([id], [brand_id], [name], [price], [sale], [quantity], [type], [color], [description], [sold], [rate], [status]) VALUES (13, 5, N'SILKYGIRL Matte Junkie Lip', 148000, 0, 24, 2, N'Nude', N'nothing', 31, 1, 1)
INSERT [dbo].[products] ([id], [brand_id], [name], [price], [sale], [quantity], [type], [color], [description], [sold], [rate], [status]) VALUES (14, 5, N'SILKYGIRL Matte Junkie Lip', 119000, 0, 11, 2, N' Đỏ ', N'nothing', 51, 2, 1)
INSERT [dbo].[products] ([id], [brand_id], [name], [price], [sale], [quantity], [type], [color], [description], [sold], [rate], [status]) VALUES (15, 6, N'LEMONADE Matte Addict', 269000, 0, 32, 1, N'Đỏ Đất A02', N'nothing', 532, 4, 1)
INSERT [dbo].[products] ([id], [brand_id], [name], [price], [sale], [quantity], [type], [color], [description], [sold], [rate], [status]) VALUES (16, 6, N'LEMONADE Matte Addict', 269000, 0, 0, 1, N'Cam Đất A03 ', N'nothing', 234, 3, 1)
INSERT [dbo].[products] ([id], [brand_id], [name], [price], [sale], [quantity], [type], [color], [description], [sold], [rate], [status]) VALUES (17, 6, N'LEMONADE Matte Addict', 269000, 0, 12, 1, N'Cam Nâu A04', N'nothing', 14, 4, 1)
INSERT [dbo].[products] ([id], [brand_id], [name], [price], [sale], [quantity], [type], [color], [description], [sold], [rate], [status]) VALUES (18, 6, N'LEMONADE Matte Addict', 269000, 0, 17, 1, N'Hồng Đỏ A01', N'nothing', 231, 2, 1)
INSERT [dbo].[products] ([id], [brand_id], [name], [price], [sale], [quantity], [type], [color], [description], [sold], [rate], [status]) VALUES (19, 6, N'LEMONADE Matte Addict', 269000, 0, 20, 1, N'Đỏ Tươi A05', N'nothing', 36, 4, 1)
INSERT [dbo].[products] ([id], [brand_id], [name], [price], [sale], [quantity], [type], [color], [description], [sold], [rate], [status]) VALUES (20, 5, N'SILKYGIRL Matte Junkie Lip', 119000, 0, 0, 2, N'Cam', N'nothing', 119, 5, 1)
INSERT [dbo].[products] ([id], [brand_id], [name], [price], [sale], [quantity], [type], [color], [description], [sold], [rate], [status]) VALUES (21, 7, N' FABULOUS SEVEN DAYS F7D Kissing Cushion Tint', 159000, 0, 24, 3, N'Đỏ Cam', N'nothing', 13, 4, 1)
INSERT [dbo].[products] ([id], [brand_id], [name], [price], [sale], [quantity], [type], [color], [description], [sold], [rate], [status]) VALUES (22, 7, N' FABULOUS SEVEN DAYS F7D Kissing Cushion Tint', 159000, 0, 0, 3, N'Coral', N'nothing', 53, 3, 1)
INSERT [dbo].[products] ([id], [brand_id], [name], [price], [sale], [quantity], [type], [color], [description], [sold], [rate], [status]) VALUES (23, 2, N'Maybelline Sensational Liquid Matte', 99000, 0, 45, 2, N'03 Flush It Red', N'nothing', 12, 2, 1)
INSERT [dbo].[products] ([id], [brand_id], [name], [price], [sale], [quantity], [type], [color], [description], [sold], [rate], [status]) VALUES (24, 2, N'Maybelline Sensational Liquid Matte', 99000, 0, 19, 2, N'04 Easy Berry', N'nothing', 94, 4, 1)
INSERT [dbo].[products] ([id], [brand_id], [name], [price], [sale], [quantity], [type], [color], [description], [sold], [rate], [status]) VALUES (25, 2, N'Maybelline Sensational Liquid Matte', 99000, 0, 16, 2, N'05 Keep It Mellow', N'nothing', 626, 5, 1)
INSERT [dbo].[products] ([id], [brand_id], [name], [price], [sale], [quantity], [type], [color], [description], [sold], [rate], [status]) VALUES (26, 2, N'Maybelline Sensational Liquid Matte', 99000, 0, 14, 2, N'07 Barely Nude', N'nothing', 14, 2, 1)
INSERT [dbo].[products] ([id], [brand_id], [name], [price], [sale], [quantity], [type], [color], [description], [sold], [rate], [status]) VALUES (27, 2, N'Maybelline Sensational Liquid Matte', 99000, 10, 32, 2, N'08 Sensationally Me', N'nothing', 12, 2, 1)
INSERT [dbo].[products] ([id], [brand_id], [name], [price], [sale], [quantity], [type], [color], [description], [sold], [rate], [status]) VALUES (28, 7, N' FABULOUS SEVEN DAYS F7D Kissing Cushion Tint', 159000, 0, 21, 3, N'Đỏ Hồng', N'nothing', 532, 3, 1)
INSERT [dbo].[products] ([id], [brand_id], [name], [price], [sale], [quantity], [type], [color], [description], [sold], [rate], [status]) VALUES (29, 7, N' FABULOUS SEVEN DAYS F7D Kissing Cushion Tint', 159000, 0, 13, 3, N'Đỏ Đất', N'nothing', 513, 2, 1)
INSERT [dbo].[products] ([id], [brand_id], [name], [price], [sale], [quantity], [type], [color], [description], [sold], [rate], [status]) VALUES (30, 7, N' FABULOUS SEVEN DAYS F7D Kissing Cushion Tint', 159000, 0, 25, 3, N'Cam Đất', N'nothing', 312, 2, 1)
INSERT [dbo].[products] ([id], [brand_id], [name], [price], [sale], [quantity], [type], [color], [description], [sold], [rate], [status]) VALUES (32, 1, N'viet', 100000, NULL, 3, 1, NULL, NULL, NULL, NULL, 1)
INSERT [dbo].[products] ([id], [brand_id], [name], [price], [sale], [quantity], [type], [color], [description], [sold], [rate], [status]) VALUES (33, 1, N'viet', 100000, NULL, 3, 1, NULL, NULL, NULL, NULL, 1)
INSERT [dbo].[products] ([id], [brand_id], [name], [price], [sale], [quantity], [type], [color], [description], [sold], [rate], [status]) VALUES (34, 3, N'MAC Rouge En Snow – Snow Ball Holiday 2017 Collection ', 550000, NULL, 33, 3, N'hồng', N' thuộc dòng son Retro Matte của MAC- là thỏi son mà bất kì tín đồ thời trang nào cũng biết đến và mong muốn được sở hữu. Với đặc điểm nổi bật là chất son siêu lì, lên màu chuẩn sắc nét cả với môi thâm, cùng với đó sắc cam đỏ thời thượng, quyến rũ của MAC Dangerous sẽ hoàn thiện phong cách thời trang đẳng cấp, giúp bạn thu hút từ cái nhìn đầu tiên.', NULL, NULL, 1)
INSERT [dbo].[products] ([id], [brand_id], [name], [price], [sale], [quantity], [type], [color], [description], [sold], [rate], [status]) VALUES (35, 3, N'MAC Fashion Legacy – Retro Matte Liquid Lipcolour', 639000, NULL, 23, 2, N'hồng', N'Son Kem MAC Fashion Legacy – Retro Matte Liquid Lipcolour sở hữu tông màu đỏ pha cam sôi động rực rỡ đầy lôi cuốn. Chất son kem lần đầu tiên xuất hiện ở thương hiệu son MAC đình đám gây ấn tượng tuyệt đối với một nửa thế giới bởi không gây khô môi hay bết dính. Son phù hợp với nhiều bạn gái bởi không kén da, là sự lựa chọn sáng suốt của những cô nàng có làn da ngăm đen. ', NULL, NULL, 1)
INSERT [dbo].[products] ([id], [brand_id], [name], [price], [sale], [quantity], [type], [color], [description], [sold], [rate], [status]) VALUES (36, 4, N'Má Hồng Guerlain Rose Aux Joues Limited', 325000, NULL, 34, 5, N'hồng', N'Là một phiên bản giới hạn trong BST mùa xuân năm 2016 của Guerlain,mang đến một thiết kế đẹp mắt và vô cùng sang trọng với lớp vỏ đỏ quyền lực cùng với hai ô màu phấn má đỏ, hồng tinh tế, hiện đại, hứa hẹn sẽ mang đến cho các quý cô một diện mạo rạng ngời, tươi tắn hơn bao giờ hết. Với chất phấn mỏng, mịn, dễ tán trên da, độ bền cao, đặc biệt không gây kích ứng da, ', NULL, NULL, 1)
INSERT [dbo].[products] ([id], [brand_id], [name], [price], [sale], [quantity], [type], [color], [description], [sold], [rate], [status]) VALUES (37, 4, N'Guerlain Meteorites Happy Glow Blush', 550000, NULL, 6, 5, N'hồng', N'Lấy cảm hứng từ những viên ngọc trai cao cấp, là một sản phẩm phấn má hồng cao cấp đến từ thương hiệu Guerlain, được làm từ thành phần chính là bột ngọc trai được ép mịn, bột kim cương với công nghệ Stardust mang đến một lớp make up hoàn hảo và vô cùng tự nhiên, tạo điểm nhấn đầy ấn tượng giúp bạn luôn tỏa sáng dù ở bất kì nơi đâu.', NULL, NULL, 1)
INSERT [dbo].[products] ([id], [brand_id], [name], [price], [sale], [quantity], [type], [color], [description], [sold], [rate], [status]) VALUES (38, 7, N'Clinique Turnaround Overnight Revitalizing Moisturizer', 520000, NULL, 11, 4, N'', N'Được điều chế từ các thành phần dịu nhẹ, an toàn tuyệt đối cho da,  giúp phục hồi, cấp nước và làm mờ đi các nốt thâm trên da vào ban đêm. Duy trì thói quen sử dụng Clinique Turnaround Overnight Revitalizing Moisturizer chính là cách giúp da trẻ hóa và khỏe mạnh hơn mỗi ngày.', NULL, NULL, 1)
INSERT [dbo].[products] ([id], [brand_id], [name], [price], [sale], [quantity], [type], [color], [description], [sold], [rate], [status]) VALUES (39, 7, N'Clinique Moisture Surge 72 Hour', 520000, NULL, 34, 4, N'', N'Sử dụng công thức dưỡng ẩm đột phá với khả năng cấp nước suốt 72 giờ liên tục,  trở thành lựa chọn lý tưởng cho những cô nàng sở hữu làn da “khô cằn”, thiếu nước trầm trọng. Kể cả sau khi rửa mặt, làn da của bạn vẫn sẽ giữ được độ mềm mại, căng mọng đáng mơ ước.', NULL, NULL, 1)
INSERT [dbo].[products] ([id], [brand_id], [name], [price], [sale], [quantity], [type], [color], [description], [sold], [rate], [status]) VALUES (40, 7, N'Clinique Repairwear Uplifting Firming Cream', 616000, NULL, 16, 4, N'', N'Đến từ thương hiệu làm đẹp hàng đầu nước Mỹ,  được kiểm nghiệm về khả năng chống lão hóa “thần kì” cho các vùng da trên gương mặt. Nó giúp xóa mờ nếp nhăn, làm mịn đi dấu vết thời gian nơi khóe miệng, đuôi mắt hay trên cổ phái đẹp.', NULL, NULL, 1)
SET IDENTITY_INSERT [dbo].[products] OFF
SET IDENTITY_INSERT [dbo].[receiver] ON 

INSERT [dbo].[receiver] ([id], [name], [mobile], [address]) VALUES (11, N'Trần Quang Việt', N'036549543                                                                                           ', N'Phù Cừ - Hưng Yên')
INSERT [dbo].[receiver] ([id], [name], [mobile], [address]) VALUES (12, N'Trần Quang Việt', N'036549543                                                                                           ', N'Phù Cừ - Hưng Yên')
INSERT [dbo].[receiver] ([id], [name], [mobile], [address]) VALUES (13, N'Trần thị minh dương', N'031231312                                                                                           ', N'Phù cừ - Hưng Yên')
INSERT [dbo].[receiver] ([id], [name], [mobile], [address]) VALUES (1012, N'Trần Quang Việt', N'0365469543                                                                                          ', N'Cầu Cáp - Phù Cừ - Hưng Yên')
INSERT [dbo].[receiver] ([id], [name], [mobile], [address]) VALUES (1013, N'<script>alert("abcjdhak")</script>', N'0974635278                                                                                          ', N'<script>alert("abcjdhak")</script>')
SET IDENTITY_INSERT [dbo].[receiver] OFF
SET IDENTITY_INSERT [dbo].[type_style] ON 

INSERT [dbo].[type_style] ([id], [name]) VALUES (1, N'Son Môi')
INSERT [dbo].[type_style] ([id], [name]) VALUES (2, N'Đồ Trang Điểm')
INSERT [dbo].[type_style] ([id], [name]) VALUES (3, N'Đồ Dùng Khác')
SET IDENTITY_INSERT [dbo].[type_style] OFF
SET IDENTITY_INSERT [dbo].[types] ON 

INSERT [dbo].[types] ([id], [style], [name], [description]) VALUES (1, 1, N'Son Lì', N'nothing')
INSERT [dbo].[types] ([id], [style], [name], [description]) VALUES (2, 1, N'Son Kem Lì', N'nothing')
INSERT [dbo].[types] ([id], [style], [name], [description]) VALUES (3, 1, N'Son Thỏi', N'nothing')
INSERT [dbo].[types] ([id], [style], [name], [description]) VALUES (4, 2, N'Kem dưỡng da', N'nothing')
INSERT [dbo].[types] ([id], [style], [name], [description]) VALUES (5, 2, N'Phấn ', N'nothing')
SET IDENTITY_INSERT [dbo].[types] OFF
ALTER TABLE [dbo].[account] ADD  CONSTRAINT [DF_account_date_create]  DEFAULT (getdate()) FOR [date_create]
GO
ALTER TABLE [dbo].[order] ADD  CONSTRAINT [DF__order__create_da__44FF419A]  DEFAULT (getdate()) FOR [create_date]
GO
ALTER TABLE [dbo].[brand_type]  WITH CHECK ADD  CONSTRAINT [FK_brand_type_brands] FOREIGN KEY([brand_id])
REFERENCES [dbo].[brands] ([id])
GO
ALTER TABLE [dbo].[brand_type] CHECK CONSTRAINT [FK_brand_type_brands]
GO
ALTER TABLE [dbo].[brand_type]  WITH CHECK ADD  CONSTRAINT [FK_brand_type_types] FOREIGN KEY([type_id])
REFERENCES [dbo].[types] ([id])
GO
ALTER TABLE [dbo].[brand_type] CHECK CONSTRAINT [FK_brand_type_types]
GO
ALTER TABLE [dbo].[customer_info]  WITH CHECK ADD  CONSTRAINT [FK_customer_info_account] FOREIGN KEY([acc_id])
REFERENCES [dbo].[account] ([id])
GO
ALTER TABLE [dbo].[customer_info] CHECK CONSTRAINT [FK_customer_info_account]
GO
ALTER TABLE [dbo].[images]  WITH CHECK ADD  CONSTRAINT [FK_images_products] FOREIGN KEY([product_id])
REFERENCES [dbo].[products] ([id])
GO
ALTER TABLE [dbo].[images] CHECK CONSTRAINT [FK_images_products]
GO
ALTER TABLE [dbo].[order]  WITH CHECK ADD  CONSTRAINT [FK_order_coupons] FOREIGN KEY([coupons_id])
REFERENCES [dbo].[coupons] ([id])
GO
ALTER TABLE [dbo].[order] CHECK CONSTRAINT [FK_order_coupons]
GO
ALTER TABLE [dbo].[order]  WITH CHECK ADD  CONSTRAINT [FK_order_customer_info] FOREIGN KEY([customer_info_id])
REFERENCES [dbo].[customer_info] ([id])
GO
ALTER TABLE [dbo].[order] CHECK CONSTRAINT [FK_order_customer_info]
GO
ALTER TABLE [dbo].[order]  WITH CHECK ADD  CONSTRAINT [FK_order_receiver] FOREIGN KEY([receiver_id])
REFERENCES [dbo].[receiver] ([id])
GO
ALTER TABLE [dbo].[order] CHECK CONSTRAINT [FK_order_receiver]
GO
ALTER TABLE [dbo].[order_detail]  WITH CHECK ADD  CONSTRAINT [FK_order_detail_order] FOREIGN KEY([order_id])
REFERENCES [dbo].[order] ([id])
GO
ALTER TABLE [dbo].[order_detail] CHECK CONSTRAINT [FK_order_detail_order]
GO
ALTER TABLE [dbo].[order_detail]  WITH CHECK ADD  CONSTRAINT [FK_order_detail_products] FOREIGN KEY([product_id])
REFERENCES [dbo].[products] ([id])
GO
ALTER TABLE [dbo].[order_detail] CHECK CONSTRAINT [FK_order_detail_products]
GO
ALTER TABLE [dbo].[products]  WITH CHECK ADD  CONSTRAINT [FK_products_brands] FOREIGN KEY([brand_id])
REFERENCES [dbo].[brands] ([id])
GO
ALTER TABLE [dbo].[products] CHECK CONSTRAINT [FK_products_brands]
GO
ALTER TABLE [dbo].[products]  WITH CHECK ADD  CONSTRAINT [FK_products_types] FOREIGN KEY([type])
REFERENCES [dbo].[types] ([id])
GO
ALTER TABLE [dbo].[products] CHECK CONSTRAINT [FK_products_types]
GO
ALTER TABLE [dbo].[types]  WITH CHECK ADD  CONSTRAINT [FK_types_type_style] FOREIGN KEY([style])
REFERENCES [dbo].[type_style] ([id])
GO
ALTER TABLE [dbo].[types] CHECK CONSTRAINT [FK_types_type_style]
GO
