1. Add a new LoaiSanPham (Product Category)
   POST /loai-san-pham
   Content-Type: application/json
   {
   "tenDanhMuc": "Electronics",
   "moTa": "Electronic products"
   }

2. Add a new SanPham (Product)
   POST /san-pham?loaiSanPhamId=1
   Content-Type: application/json
   {
   "tenSP": "Smartphone",
   "moTa": "Latest smartphone",
   "giaSP": 700.00,
   "soLuongTonKho": 50,
   "hinhSP": "url/to/image"
   }

1.1. Get All Product Categories
GET /loai-san-pham
Response Example:
[
{
"maDanhMuc": 1,
"tenDanhMuc": "Electronics",
"moTa": "Electronic products"
},
{
"maDanhMuc": 2,
"tenDanhMuc": "Clothing",
"moTa": "All types of clothing"
}
]

1.2. Get a Product Category by ID
GET /loai-san-pham/1
Response Example:
{
"maDanhMuc": 1,
"tenDanhMuc": "Electronics",
"moTa": "Electronic products"
}

1.3. Add a New Product Category
POST /loai-san-pham
Content-Type: application/json
{
"tenDanhMuc": "Home Appliances",
"moTa": "Appliances for home use"
}
Response: HTTP 201 CREATED

1.4. Update a Product Category by ID
PUT /loai-san-pham/1
Content-Type: application/json
{
"tenDanhMuc": "Updated Electronics",
"moTa": "Updated description for electronics"
}
Response: HTTP 200 OK

1.5. Delete a Product Category by ID
DELETE /loai-san-pham/1
Response: HTTP 204 NO CONTENT

2. SanPhamResource (Product) Requests
   2.1. Get All Products
   GET /san-pham
   Response Example:
   [
   {
   "maSP": 1,
   "tenSP": "Smartphone",
   "moTa": "Latest smartphone",
   "giaSP": 700.00,
   "soLuongTonKho": 50,
   "hinhSP": "url/to/image",
   "loaiSanPham": {
   "maDanhMuc": 1,
   "tenDanhMuc": "Electronics",
   "moTa": "Electronic products"
   }
   },
   {
   "maSP": 2,
   "tenSP": "Laptop",
   "moTa": "High performance laptop",
   "giaSP": 1500.00,
   "soLuongTonKho": 20,
   "hinhSP": "url/to/laptop_image",
   "loaiSanPham": {
   "maDanhMuc": 1,
   "tenDanhMuc": "Electronics",
   "moTa": "Electronic products"
   }
   }
   ]

2.2. Get a Product by ID
GET /san-pham/1
Response Example:
{
"maSP": 1,
"tenSP": "Smartphone",
"moTa": "Latest smartphone",
"giaSP": 700.00,
"soLuongTonKho": 50,
"hinhSP": "url/to/image",
"loaiSanPham": {
"maDanhMuc": 1,
"tenDanhMuc": "Electronics",
"moTa": "Electronic products"
}
}

2.3. Add a New Product
POST /san-pham?loaiSanPhamId=1
Content-Type: application/json
{
"tenSP": "Washing Machine",
"moTa": "Latest washing machine with smart features",
"giaSP": 1200.00,
"soLuongTonKho": 10,
"hinhSP": "url/to/washing_machine_image"
}
Response: HTTP 201 CREATED

2.4. Update a Product by ID
PUT /san-pham/1?loaiSanPhamId=1
Content-Type: application/json
{
"tenSP": "Updated Smartphone",
"moTa": "Updated smartphone description",
"giaSP": 750.00,
"soLuongTonKho": 45,
"hinhSP": "url/to/updated_image"
}
Response: HTTP 200 OK

2.5. Delete a Product by ID
DELETE /san-pham/1
Response: HTTP 204 NO CONTENT

Summary of API Requests:
GET /loai-san-pham: Get all product categories.
GET /loai-san-pham/{id}: Get a specific product category by ID.
POST /loai-san-pham: Add a new product category.
PUT /loai-san-pham/{id}: Update a product category.
DELETE /loai-san-pham/{id}: Delete a product category.
GET /san-pham: Get all products.
GET /san-pham/{id}: Get a specific product by ID.
POST /san-pham?loaiSanPhamId={id}: Add a new product associated with a category.
PUT /san-pham/{id}?loaiSanPhamId={id}: Update a product and associate it with a category.
DELETE /san-pham/{id}: Delete a product.
