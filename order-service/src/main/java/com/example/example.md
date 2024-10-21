Create Order (POST)
http
Copy code
POST /donhang
Content-Type: application/json

{
"ma_nguoi_dung": 1,
"ngay_dat_hang": "2024-10-21T10:00:00",
"tong_tien": 100000,
"trang_thai": "đang xử lý",
"ho_ten": "Nguyễn Văn A"
}
Get All Orders (GET)
http
Copy code
GET /donhang
Update Order (PUT)
http
Copy code
PUT /donhang/1
Content-Type: application/json

{
"ma_nguoi_dung": 1,
"ngay_dat_hang": "2024-10-21T10:00:00",
"tong_tien": 120000,
"trang_thai": "đã giao",
"ho_ten": "Nguyễn Văn A"
}
Delete Order (DELETE)
http
Copy code
DELETE /donhang/1
2. ChiTietDonHangResource (Order Detail Resource)
   Create Order Detail (POST)
   http
   Copy code
   POST /chitietdonhang
   Content-Type: application/json

{
"ma_dh": 1,
"ma_sp": 101,
"so_luong": 2,
"thanh_tien": 200000
}
Get All Order Details (GET)
http
Copy code
GET /chitietdonhang
Update Order Detail (PUT)
http
Copy code
PUT /chitietdonhang/1
Content-Type: application/json

{
"ma_dh": 1,
"ma_sp": 101,
"so_luong": 3,
"thanh_tien": 300000
}
Delete Order Detail (DELETE)
http
Copy code
DELETE /chitietdonhang/1
3. DanhGiaResource (Review Resource)
   Create Review (POST)
   http
   Copy code
   POST /danhgia
   Content-Type: application/json

{
"ma_nguoi_dung": 1,
"noi_dung": "Sản phẩm rất tốt!",
"ma_sp": 101,
"ngay_danh_gia": "2024-10-21T10:00:00"
}
Get All Reviews (GET)
http
Copy code
GET /danhgia
Update Review (PUT)
http
Copy code
PUT /danhgia/1
Content-Type: application/json

{
"ma_nguoi_dung": 1,
"noi_dung": "Sản phẩm rất tuyệt vời!",
"ma_sp": 101,
"ngay_danh_gia": "2024-10-21T10:00:00"
}
Delete Review (DELETE)
http
Copy code
DELETE /danhgia/1
4. GiamGiaResource (Discount Resource)
   Create Discount (POST)
   http
   Copy code
   POST /giamgia
   Content-Type: application/json

{
"code": "Giam10",
"ty_le_giam": 10,
"ngay_bat_dau": "2024-10-21T00:00:00",
"ngay_ket_thuc": "2024-12-31T23:59:59"
}
Get All Discounts (GET)
http
Copy code
GET /giamgia
Update Discount (PUT)
http
Copy code
PUT /giamgia/1
Content-Type: application/json

{
"code": "Giam20",
"ty_le_giam": 20,
"ngay_bat_dau": "2024-10-21T00:00:00",
"ngay_ket_thuc": "2024-12-31T23:59:59"
}
Delete Discount (DELETE)
http
Copy code
DELETE /giamgia/1
5. GiaoHangResource (Delivery Resource)
   Create Delivery (POST)
   http
   Copy code
   POST /giaohang
   Content-Type: application/json

{
"ten_phuong_thuc": "Giao nhanh",
"phi_giao_hang": 20000,
"thoi_gian_du_kien": "2024-10-22T15:00:00"
}
Get All Deliveries (GET)
http
Copy code
GET /giaohang
Update Delivery (PUT)
http
Copy code
PUT /giaohang/1
Content-Type: application/json

{
"ten_phuong_thuc": "Giao thường",
"phi_giao_hang": 15000,
"thoi_gian_du_kien": "2024-10-23T17:00:00"
}
Delete Delivery (DELETE)
http
Copy code
DELETE /giaohang/1
6. ThanhToanResource (Payment Resource)
   Create Payment (POST)
   http
   Copy code
   POST /thanhtoan
   Content-Type: application/json

{
"phuong_thuc_thanh_toan": "Thẻ",
"ngay_thanh_toan": "2024-10-21T10:00:00"
}
Get All Payments (GET)
http
Copy code
GET /thanhtoan
Update Payment (PUT)
http
Copy code
PUT /thanhtoan/1
Content-Type: application/json

{
"phuong_thuc_thanh_toan": "Tiền mặt",
"ngay_thanh_toan": "2024-10-21T10:00:00"
}
Delete Payment (DELETE)
http
Copy code
DELETE /thanhtoan/1
