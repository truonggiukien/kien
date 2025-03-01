USE [master]
GO
/****** Object:  Database [feastfiesta]    Script Date: 8/19/2024 5:47:11 PM ******/
CREATE DATABASE [feastfiesta]
GO
USE [feastfiesta]
GO
CREATE TABLE [dbo].[Categories](
	[CategoryID] [int] IDENTITY(1,1) NOT NULL,
	[Name] [varchar](max) NULL,
 CONSTRAINT [PK_MenuCategory] PRIMARY KEY CLUSTERED 
(
	[CategoryID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Caterers]    Script Date: 8/19/2024 5:47:11 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Caterers](
	[CatererId] [int] IDENTITY(1,1) NOT NULL,
	[Name] [varchar](max) NULL,
	[Address] [varchar](max) NULL,
	[Phone] [varchar](max) NULL,
	[DateOfJoin] [datetime] NULL,
 CONSTRAINT [PK_Workers] PRIMARY KEY CLUSTERED 
(
	[CatererId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Customers]    Script Date: 8/19/2024 5:47:11 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Customers](
	[CustomerID] [int] IDENTITY(1,1) NOT NULL,
	[Name] [varchar](max) NULL,
	[Address] [varchar](max) NULL,
	[Phone] [varchar](max) NULL,
	[Email] [varchar](max) NULL,
	[Username] [varchar](max) NULL,
	[Password] [varchar](max) NULL,
 CONSTRAINT [PK_Customers] PRIMARY KEY CLUSTERED 
(
	[CustomerID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Dishes]    Script Date: 8/19/2024 5:47:11 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Dishes](
	[DishID] [int] IDENTITY(1,1) NOT NULL,
	[ItemName] [varchar](max) NULL,
	[Price] [decimal](10, 2) NULL,
	[CategoryID] [int] NULL,
	[Picture] [varchar](max) NULL,
 CONSTRAINT [PK_Menu] PRIMARY KEY CLUSTERED 
(
	[DishID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[DishIngChild]    Script Date: 8/19/2024 5:47:11 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DishIngChild](
	[DishID] [int] NOT NULL,
	[IngredientID] [int] NOT NULL,
	[Quantity] [int] NULL,
	[DishIngID] [int] IDENTITY(1,1) NOT NULL,
 CONSTRAINT [PK_DishIngChild] PRIMARY KEY CLUSTERED 
(
	[DishIngID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Events]    Script Date: 8/19/2024 5:47:11 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Events](
	[EventID] [int] IDENTITY(1,1) NOT NULL,
	[EventName] [varchar](max) NULL,
	[BeginDate] [datetime] NULL,
	[EndDate] [datetime] NULL,
	[Quantity] [int] NULL,
	[Discount] [varchar](max) NULL,
 CONSTRAINT [PK_Events] PRIMARY KEY CLUSTERED 
(
	[EventID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[FavoriteList]    Script Date: 8/19/2024 5:47:11 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[FavoriteList](
	[FavoriteID] [int] IDENTITY(1,1) NOT NULL,
	[CustomerID] [int] NULL,
	[DishID] [int] NULL,
 CONSTRAINT [PK_FavoriteList] PRIMARY KEY CLUSTERED 
(
	[FavoriteID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Feedbacks]    Script Date: 8/19/2024 5:47:11 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Feedbacks](
	[FeedbackID] [int] IDENTITY(1,1) NOT NULL,
	[CustomerID] [int] NULL,
	[Message] [varchar](max) NULL,
 CONSTRAINT [PK_Feedbacks] PRIMARY KEY CLUSTERED 
(
	[FeedbackID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Ingredients]    Script Date: 8/19/2024 5:47:11 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Ingredients](
	[IngredientID] [int] IDENTITY(1,1) NOT NULL,
	[Name] [varchar](max) NULL,
 CONSTRAINT [PK_Ingredients] PRIMARY KEY CLUSTERED 
(
	[IngredientID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Menu]    Script Date: 8/19/2024 5:47:11 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Menu](
	[MenuID] [int] IDENTITY(1,1) NOT NULL,
	[MenuName] [varchar](max) NULL,
	[Description] [varchar](max) NULL,
	[Picture] [varchar](max) NULL,
 CONSTRAINT [PK_Menu_1] PRIMARY KEY CLUSTERED 
(
	[MenuID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[MenuDishChild]    Script Date: 8/19/2024 5:47:11 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[MenuDishChild](
	[MenuID] [int] NOT NULL,
	[DishID] [int] NOT NULL,
	[Quantity] [int] NULL,
	[MenuDishID] [int] IDENTITY(1,1) NOT NULL,
 CONSTRAINT [PK_MenuDishChild] PRIMARY KEY CLUSTERED 
(
	[MenuDishID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[OrderDishChild]    Script Date: 8/19/2024 5:47:11 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[OrderDishChild](
	[OrderDishID] [int] IDENTITY(1,1) NOT NULL,
	[DishID] [int] NOT NULL,
	[OrderNo] [int] NOT NULL,
	[Quantity] [int] NULL,
 CONSTRAINT [PK_OrderDishChild_1] PRIMARY KEY CLUSTERED 
(
	[OrderDishID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Orders]    Script Date: 8/19/2024 5:47:11 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Orders](
	[OrderNo] [int] IDENTITY(1,1) NOT NULL,
	[OrderDate] [datetime] NULL,
	[CustomerID] [int] NULL,
	[DeliveryDate] [datetime] NULL,
	[DeliveryAddress] [varchar](max) NULL,
	[CatererID] [int] NULL,
	[EventID] [int] NULL,
	[TotalPrice] [decimal](10, 2) NULL,
	[Status] [varchar](max) NULL,
	[Name] [varchar](max) NULL,
	[Notes] [varchar](max) NULL,
 CONSTRAINT [PK_CustOrder] PRIMARY KEY CLUSTERED 
(
	[OrderNo] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[Categories] ON 

INSERT [dbo].[Categories] ([CategoryID], [Name]) VALUES (1, N'Appetizers')
INSERT [dbo].[Categories] ([CategoryID], [Name]) VALUES (2, N'Main Courses
')
INSERT [dbo].[Categories] ([CategoryID], [Name]) VALUES (3, N'Side Dishes
')
INSERT [dbo].[Categories] ([CategoryID], [Name]) VALUES (4, N'Desserts')
INSERT [dbo].[Categories] ([CategoryID], [Name]) VALUES (5, N'Beverages')
INSERT [dbo].[Categories] ([CategoryID], [Name]) VALUES (8, N'df')
SET IDENTITY_INSERT [dbo].[Categories] OFF
GO
SET IDENTITY_INSERT [dbo].[Customers] ON 

INSERT [dbo].[Customers] ([CustomerID], [Name], [Address], [Phone], [Email], [Username], [Password]) VALUES (1, N'Admin', N'', N'', NULL, N'admin', N'123')
INSERT [dbo].[Customers] ([CustomerID], [Name], [Address], [Phone], [Email], [Username], [Password]) VALUES (2, N'Le Huynh', N'Can Tho', N'0299839232', N'huynh@gmail.com', N'user', N'1234')
SET IDENTITY_INSERT [dbo].[Customers] OFF
GO
SET IDENTITY_INSERT [dbo].[Dishes] ON 

INSERT [dbo].[Dishes] ([DishID], [ItemName], [Price], [CategoryID], [Picture]) VALUES (1, N'Dish 1', CAST(20000.00 AS Decimal(10, 2)), 1, N'test.jpg')
INSERT [dbo].[Dishes] ([DishID], [ItemName], [Price], [CategoryID], [Picture]) VALUES (2, N'Dish 2 ', CAST(1000.00 AS Decimal(10, 2)), 2, N'test.jpg')
INSERT [dbo].[Dishes] ([DishID], [ItemName], [Price], [CategoryID], [Picture]) VALUES (3, N'Dish 3', CAST(3033.00 AS Decimal(10, 2)), 4, N'test.jpg')
INSERT [dbo].[Dishes] ([DishID], [ItemName], [Price], [CategoryID], [Picture]) VALUES (4, N'Dish 4', CAST(1111.00 AS Decimal(10, 2)), 3, N'test.jpg')
SET IDENTITY_INSERT [dbo].[Dishes] OFF
GO
SET IDENTITY_INSERT [dbo].[Events] ON 

INSERT [dbo].[Events] ([EventID], [EventName], [BeginDate], [EndDate], [Quantity], [Discount]) VALUES (1, N'New Year', CAST(N'2024-03-03T00:00:00.000' AS DateTime), CAST(N'2024-03-03T00:00:00.000' AS DateTime), 2, N'NEWYEAR')
INSERT [dbo].[Events] ([EventID], [EventName], [BeginDate], [EndDate], [Quantity], [Discount]) VALUES (2, N'Event 1', CAST(N'2022-02-02T07:00:00.000' AS DateTime), CAST(N'2024-04-04T07:00:00.000' AS DateTime), 100, N'AAHS')
SET IDENTITY_INSERT [dbo].[Events] OFF
GO
SET IDENTITY_INSERT [dbo].[Feedbacks] ON 

INSERT [dbo].[Feedbacks] ([FeedbackID], [CustomerID], [Message]) VALUES (1, 2, N'nice food')
SET IDENTITY_INSERT [dbo].[Feedbacks] OFF
GO
SET IDENTITY_INSERT [dbo].[Ingredients] ON 

INSERT [dbo].[Ingredients] ([IngredientID], [Name]) VALUES (1, N'Salt')
SET IDENTITY_INSERT [dbo].[Ingredients] OFF
GO
SET IDENTITY_INSERT [dbo].[OrderDishChild] ON 

INSERT [dbo].[OrderDishChild] ([OrderDishID], [DishID], [OrderNo], [Quantity]) VALUES (1, 1, 21, 2)
INSERT [dbo].[OrderDishChild] ([OrderDishID], [DishID], [OrderNo], [Quantity]) VALUES (2, 1, 22, 1)
INSERT [dbo].[OrderDishChild] ([OrderDishID], [DishID], [OrderNo], [Quantity]) VALUES (3, 2, 22, 1)
INSERT [dbo].[OrderDishChild] ([OrderDishID], [DishID], [OrderNo], [Quantity]) VALUES (4, 3, 22, 1)
SET IDENTITY_INSERT [dbo].[OrderDishChild] OFF
GO
SET IDENTITY_INSERT [dbo].[Orders] ON 

INSERT [dbo].[Orders] ([OrderNo], [OrderDate], [CustomerID], [DeliveryDate], [DeliveryAddress], [CatererID], [EventID], [TotalPrice], [Status], [Name], [Notes]) VALUES (1, CAST(N'2024-03-03T00:00:00.000' AS DateTime), NULL, CAST(N'2024-03-03T00:00:00.000' AS DateTime), N'Can Tho', NULL, 1, CAST(1000.00 AS Decimal(10, 2)), N'', NULL, NULL)
INSERT [dbo].[Orders] ([OrderNo], [OrderDate], [CustomerID], [DeliveryDate], [DeliveryAddress], [CatererID], [EventID], [TotalPrice], [Status], [Name], [Notes]) VALUES (4, NULL, NULL, NULL, NULL, NULL, NULL, NULL, N'Waiting', N'User', N'None')
INSERT [dbo].[Orders] ([OrderNo], [OrderDate], [CustomerID], [DeliveryDate], [DeliveryAddress], [CatererID], [EventID], [TotalPrice], [Status], [Name], [Notes]) VALUES (5, CAST(N'2024-08-16T09:27:39.067' AS DateTime), 1, NULL, NULL, NULL, NULL, NULL, N'Waiting', N'User', N'None')
INSERT [dbo].[Orders] ([OrderNo], [OrderDate], [CustomerID], [DeliveryDate], [DeliveryAddress], [CatererID], [EventID], [TotalPrice], [Status], [Name], [Notes]) VALUES (6, CAST(N'2024-08-16T09:28:37.060' AS DateTime), 1, NULL, NULL, NULL, NULL, NULL, N'Waiting', N'User', N'None')
INSERT [dbo].[Orders] ([OrderNo], [OrderDate], [CustomerID], [DeliveryDate], [DeliveryAddress], [CatererID], [EventID], [TotalPrice], [Status], [Name], [Notes]) VALUES (7, CAST(N'2024-08-16T09:28:37.120' AS DateTime), 1, NULL, NULL, NULL, NULL, NULL, N'Waiting', N'User', N'None')
INSERT [dbo].[Orders] ([OrderNo], [OrderDate], [CustomerID], [DeliveryDate], [DeliveryAddress], [CatererID], [EventID], [TotalPrice], [Status], [Name], [Notes]) VALUES (8, CAST(N'2024-08-16T09:33:23.737' AS DateTime), 1, NULL, N'Can Tho', NULL, NULL, NULL, N'Waiting', N'User', N'None')
INSERT [dbo].[Orders] ([OrderNo], [OrderDate], [CustomerID], [DeliveryDate], [DeliveryAddress], [CatererID], [EventID], [TotalPrice], [Status], [Name], [Notes]) VALUES (9, CAST(N'2024-08-16T15:57:28.170' AS DateTime), 1, CAST(N'2024-08-13T00:00:00.000' AS DateTime), NULL, NULL, NULL, NULL, N'Waiting', N'User', N'None')
INSERT [dbo].[Orders] ([OrderNo], [OrderDate], [CustomerID], [DeliveryDate], [DeliveryAddress], [CatererID], [EventID], [TotalPrice], [Status], [Name], [Notes]) VALUES (10, CAST(N'2024-08-16T16:25:13.130' AS DateTime), 1, CAST(N'2024-08-20T00:00:00.000' AS DateTime), N'New York', NULL, NULL, NULL, N'Waiting', N'admin', N'23')
INSERT [dbo].[Orders] ([OrderNo], [OrderDate], [CustomerID], [DeliveryDate], [DeliveryAddress], [CatererID], [EventID], [TotalPrice], [Status], [Name], [Notes]) VALUES (11, CAST(N'2024-08-19T14:41:47.860' AS DateTime), 1, CAST(N'2024-08-20T00:00:00.000' AS DateTime), N'admin', NULL, NULL, NULL, N'Waiting', N'admin', N'None')
INSERT [dbo].[Orders] ([OrderNo], [OrderDate], [CustomerID], [DeliveryDate], [DeliveryAddress], [CatererID], [EventID], [TotalPrice], [Status], [Name], [Notes]) VALUES (12, CAST(N'2024-08-19T14:41:47.867' AS DateTime), 1, CAST(N'2024-08-20T00:00:00.000' AS DateTime), N'admin', NULL, NULL, NULL, N'Waiting', N'admin', N'None')
INSERT [dbo].[Orders] ([OrderNo], [OrderDate], [CustomerID], [DeliveryDate], [DeliveryAddress], [CatererID], [EventID], [TotalPrice], [Status], [Name], [Notes]) VALUES (13, CAST(N'2024-08-19T14:41:47.860' AS DateTime), 1, CAST(N'2024-08-20T00:00:00.000' AS DateTime), N'admin', NULL, NULL, NULL, N'Waiting', N'admin', N'None')
INSERT [dbo].[Orders] ([OrderNo], [OrderDate], [CustomerID], [DeliveryDate], [DeliveryAddress], [CatererID], [EventID], [TotalPrice], [Status], [Name], [Notes]) VALUES (14, CAST(N'2024-08-19T15:04:51.637' AS DateTime), 1, CAST(N'2024-08-06T00:00:00.000' AS DateTime), N'dd', NULL, NULL, NULL, N'Waiting', N'admin', N'None')
INSERT [dbo].[Orders] ([OrderNo], [OrderDate], [CustomerID], [DeliveryDate], [DeliveryAddress], [CatererID], [EventID], [TotalPrice], [Status], [Name], [Notes]) VALUES (15, CAST(N'2024-08-19T15:09:22.640' AS DateTime), 1, CAST(N'2024-08-16T00:00:00.000' AS DateTime), N'sd', NULL, NULL, NULL, N'Waiting', N'admin', N'None')
INSERT [dbo].[Orders] ([OrderNo], [OrderDate], [CustomerID], [DeliveryDate], [DeliveryAddress], [CatererID], [EventID], [TotalPrice], [Status], [Name], [Notes]) VALUES (16, CAST(N'2024-08-19T15:09:22.623' AS DateTime), 1, CAST(N'2024-08-16T00:00:00.000' AS DateTime), N'sd', NULL, NULL, NULL, N'Waiting', N'admin', N'None')
INSERT [dbo].[Orders] ([OrderNo], [OrderDate], [CustomerID], [DeliveryDate], [DeliveryAddress], [CatererID], [EventID], [TotalPrice], [Status], [Name], [Notes]) VALUES (17, CAST(N'2024-08-19T15:13:07.997' AS DateTime), 1, CAST(N'2024-08-07T00:00:00.000' AS DateTime), N'New York', NULL, NULL, NULL, N'Waiting', N'admin', N'None')
INSERT [dbo].[Orders] ([OrderNo], [OrderDate], [CustomerID], [DeliveryDate], [DeliveryAddress], [CatererID], [EventID], [TotalPrice], [Status], [Name], [Notes]) VALUES (18, CAST(N'2024-08-19T15:16:51.327' AS DateTime), 1, CAST(N'2024-08-02T00:00:00.000' AS DateTime), N'12', NULL, NULL, NULL, N'Waiting', N'admin', N'None')
INSERT [dbo].[Orders] ([OrderNo], [OrderDate], [CustomerID], [DeliveryDate], [DeliveryAddress], [CatererID], [EventID], [TotalPrice], [Status], [Name], [Notes]) VALUES (19, CAST(N'2024-08-19T15:21:56.643' AS DateTime), 1, CAST(N'2024-08-09T00:00:00.000' AS DateTime), N'24, Ly Tu Trong', NULL, NULL, NULL, N'Waiting', N'admin', N'None')
INSERT [dbo].[Orders] ([OrderNo], [OrderDate], [CustomerID], [DeliveryDate], [DeliveryAddress], [CatererID], [EventID], [TotalPrice], [Status], [Name], [Notes]) VALUES (20, CAST(N'2024-08-19T16:47:26.933' AS DateTime), 1, CAST(N'2024-08-21T00:00:00.000' AS DateTime), N'New York', NULL, NULL, NULL, N'Waiting', N'admin', N'None')
INSERT [dbo].[Orders] ([OrderNo], [OrderDate], [CustomerID], [DeliveryDate], [DeliveryAddress], [CatererID], [EventID], [TotalPrice], [Status], [Name], [Notes]) VALUES (21, CAST(N'2024-08-19T16:54:25.427' AS DateTime), 1, CAST(N'2024-08-14T00:00:00.000' AS DateTime), N'ad', NULL, NULL, NULL, N'Waiting', N'admin', N'None')
INSERT [dbo].[Orders] ([OrderNo], [OrderDate], [CustomerID], [DeliveryDate], [DeliveryAddress], [CatererID], [EventID], [TotalPrice], [Status], [Name], [Notes]) VALUES (22, CAST(N'2024-08-19T16:55:30.283' AS DateTime), 1, CAST(N'2024-08-07T00:00:00.000' AS DateTime), N'sd', NULL, NULL, NULL, N'Waiting', N'admin', N'None')
SET IDENTITY_INSERT [dbo].[Orders] OFF
GO
ALTER TABLE [dbo].[Dishes]  WITH CHECK ADD  CONSTRAINT [FK_Dishes_Categories] FOREIGN KEY([CategoryID])
REFERENCES [dbo].[Categories] ([CategoryID])
GO
ALTER TABLE [dbo].[Dishes] CHECK CONSTRAINT [FK_Dishes_Categories]
GO
ALTER TABLE [dbo].[DishIngChild]  WITH CHECK ADD  CONSTRAINT [FK_DishIngChild_Dishes] FOREIGN KEY([DishID])
REFERENCES [dbo].[Dishes] ([DishID])
GO
ALTER TABLE [dbo].[DishIngChild] CHECK CONSTRAINT [FK_DishIngChild_Dishes]
GO
ALTER TABLE [dbo].[DishIngChild]  WITH CHECK ADD  CONSTRAINT [FK_DishIngChild_Ingredients] FOREIGN KEY([IngredientID])
REFERENCES [dbo].[Ingredients] ([IngredientID])
GO
ALTER TABLE [dbo].[DishIngChild] CHECK CONSTRAINT [FK_DishIngChild_Ingredients]
GO
ALTER TABLE [dbo].[FavoriteList]  WITH CHECK ADD  CONSTRAINT [FK_FavoriteList_Customers] FOREIGN KEY([CustomerID])
REFERENCES [dbo].[Customers] ([CustomerID])
GO
ALTER TABLE [dbo].[FavoriteList] CHECK CONSTRAINT [FK_FavoriteList_Customers]
GO
ALTER TABLE [dbo].[FavoriteList]  WITH CHECK ADD  CONSTRAINT [FK_FavoriteList_Menu] FOREIGN KEY([DishID])
REFERENCES [dbo].[Dishes] ([DishID])
GO
ALTER TABLE [dbo].[FavoriteList] CHECK CONSTRAINT [FK_FavoriteList_Menu]
GO
ALTER TABLE [dbo].[Feedbacks]  WITH CHECK ADD  CONSTRAINT [FK_Feedbacks_Customers] FOREIGN KEY([CustomerID])
REFERENCES [dbo].[Customers] ([CustomerID])
GO
ALTER TABLE [dbo].[Feedbacks] CHECK CONSTRAINT [FK_Feedbacks_Customers]
GO
ALTER TABLE [dbo].[MenuDishChild]  WITH CHECK ADD  CONSTRAINT [FK_MenuDishChild_Menu] FOREIGN KEY([MenuID])
REFERENCES [dbo].[Menu] ([MenuID])
GO
ALTER TABLE [dbo].[MenuDishChild] CHECK CONSTRAINT [FK_MenuDishChild_Menu]
GO
ALTER TABLE [dbo].[MenuDishChild]  WITH CHECK ADD  CONSTRAINT [FK_MenuDishChild_MenuDishChild] FOREIGN KEY([DishID])
REFERENCES [dbo].[Dishes] ([DishID])
GO
ALTER TABLE [dbo].[MenuDishChild] CHECK CONSTRAINT [FK_MenuDishChild_MenuDishChild]
GO
ALTER TABLE [dbo].[OrderDishChild]  WITH CHECK ADD  CONSTRAINT [FK_OrderDishChild_Dishes] FOREIGN KEY([DishID])
REFERENCES [dbo].[Dishes] ([DishID])
GO
ALTER TABLE [dbo].[OrderDishChild] CHECK CONSTRAINT [FK_OrderDishChild_Dishes]
GO
ALTER TABLE [dbo].[OrderDishChild]  WITH CHECK ADD  CONSTRAINT [FK_OrderDishChild_Orders] FOREIGN KEY([OrderNo])
REFERENCES [dbo].[Orders] ([OrderNo])
GO
ALTER TABLE [dbo].[OrderDishChild] CHECK CONSTRAINT [FK_OrderDishChild_Orders]
GO
ALTER TABLE [dbo].[Orders]  WITH CHECK ADD  CONSTRAINT [FK_Orders_Caterers] FOREIGN KEY([CatererID])
REFERENCES [dbo].[Caterers] ([CatererId])
GO
ALTER TABLE [dbo].[Orders] CHECK CONSTRAINT [FK_Orders_Caterers]
GO
ALTER TABLE [dbo].[Orders]  WITH CHECK ADD  CONSTRAINT [FK_Orders_Customers] FOREIGN KEY([CustomerID])
REFERENCES [dbo].[Customers] ([CustomerID])
GO
ALTER TABLE [dbo].[Orders] CHECK CONSTRAINT [FK_Orders_Customers]
GO
ALTER TABLE [dbo].[Orders]  WITH CHECK ADD  CONSTRAINT [FK_Orders_Events] FOREIGN KEY([EventID])
REFERENCES [dbo].[Events] ([EventID])
GO
ALTER TABLE [dbo].[Orders] CHECK CONSTRAINT [FK_Orders_Events]
GO
USE [master]
GO
ALTER DATABASE [feastfiesta] SET  READ_WRITE 
GO
