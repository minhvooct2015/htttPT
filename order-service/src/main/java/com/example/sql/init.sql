

latest:


create table Don_Hang (
                          Ma_DH varchar(255) not null,
                          Ma_nguoi_dung varchar(255),
                          Ngay_dat_hang date,
                          Ngay_thanh_toan date,
                          Phi_giao_hang float(53),
                          Ten_phuong_thuc_gd enum ('NHANH','THUONG'),
                          Phuong_thuc_thanh_toan enum ('THE','TIEN_MAT'),
                          Thoi_gian_du_kien date,
                          Tong_tien decimal(38,2),
                          Trang_thai enum ('DANG_DAT','DANG_XU_LY','DA_GIAO','HUY'),
                          primary key (Ma_DH)

) engine=InnoDB;

create table Chi_Tiet_Don_Hang (
                                   Ma_CTDH varchar(255) not null,
                                   Ma_SP varchar(255),
                                   So_luong integer,
                                   Thanh_tien decimal(38,2),
                                   Ma_DH varchar(255),
                                   primary key (Ma_CTDH),
                                   CONSTRAINT fk_don_hang FOREIGN KEY (Ma_DH) REFERENCES Don_Hang(Ma_DH)
) engine=InnoDB;

-- alter table Chi_Tiet_Don_Hang
--     add constraint FKolr6spd1mt0pnksltnlmiou3e
--         foreign key (Ma_DH)
--             references Don_Hang (Ma_DH);
--
--

======================================================
create table Don_Hang (
                          Ma_DH varchar(255) not null,
                          Ho_ten varchar(255),
                          Ma_nguoi_dung varchar(255),
                          Ngay_dat_hang date,
                          Tong_tien decimal(38,2),
                          Trang_thai enum ('DANG_DAT','DANG_XU_LY','DA_GIAO','HUY'),
                          primary key (Ma_DH)
) engine=InnoDB;

create table Chi_Tiet_Don_Hang (
                                   Ma_CTDH varchar(255) not null,
                                   Ma_DH varchar(255),
                                   Ma_SP varchar(255),
                                   So_luong integer,
                                   Thanh_tien decimal(38,2),
                                   primary key (Ma_CTDH),
                                   CONSTRAINT fk_don_hang FOREIGN KEY (Ma_DH) REFERENCES Don_Hang(Ma_DH) -- Foreign key referencing Don_Hang

) engine=InnoDB;

create table Danh_Gia (
                          Ma_danh_gia varchar(255) not null,
                          Ma_nguoi_dung varchar(255),
                          Ma_SP varchar(255),
                          Ngay_danh_gia date,
                          Noi_dung varchar(255),
                          primary key (Ma_danh_gia)
) engine=InnoDB;


create table Giam_Gia (
                          Ma_giam_gia varchar(255) not null,
                          Code varchar(255),
                          Ngay_bat_dau date,
                          Ngay_ket_thuc date,
                          Ty_le_giam float(53),
                          primary key (Ma_giam_gia)
) engine=InnoDB;

create table Giao_Hang (
                           Ma_giao_hang varchar(255) not null,
                           Phi_giao_hang float(53),
                           Ten_phuong_thuc enum ('NHANH','THUONG'),
                           Thoi_gian_du_kien date,
                           primary key (Ma_giao_hang)
) engine=InnoDB;

create table Thanh_Toan (
                            Ma_thanh_toan bigint not null auto_increment,
                            Ngay_thanh_toan date,
                            Phuong_thuc_thanh_toan enum ('THE','TIEN_MAT'),
                            primary key (Ma_thanh_toan)
) engine=InnoDB;