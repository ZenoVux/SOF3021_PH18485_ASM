<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html lang="vi">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title><c:out value="${title}" /></title>
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/css/icon/fontawesome.min.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/icon/brands.min.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/icon/solid.min.css"
	rel="stylesheet">
<script
	src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/admin/style.css">
<style>
.table>tbody>tr>td, th {
	vertical-align: middle;
}
</style>
</head>

<body class="bg-light" id="body-pd">
	<div class="l-navbar bg-dark" id="nav-bar">
		<nav class="nav">
			<div>
				<a href="${pageContext.request.contextPath}/home" class="nav_logo">
					<i class='fa-solid fa-star nav_logo-icon'></i> <span
					class="nav_logo-name">PH18485</span>
				</a>
				<div class="nav_list">
					<a href="${pageContext.request.contextPath}/admin/accounts"
						class="nav_link ${menuId == 1 ? 'active' : ''}"> <i
						class="fa-solid fa-user nav_icon"></i> <span class="nav_name">Người
							dùng</span>
					</a> <a href="${pageContext.request.contextPath}/admin/products"
						class="nav_link ${menuId == 2 ? 'active' : ''}"> <i
						class="fa-solid fa-box nav_icon"></i> <span class="nav_name">Sản
							phẩm</span>
					</a> <a href="${pageContext.request.contextPath}/admin/categories"
						class="nav_link ${menuId == 3 ? 'active' : ''}"> <i
						class="fa-solid fa-list nav_icon"></i> <span class="nav_name">Danh
							mục</span>
					</a> <a href="${pageContext.request.contextPath}/admin/orders"
						class="nav_link ${menuId == 4 ? 'active' : ''}"> <i
						class="fa-solid fa-cart-shopping nav_icon"></i> <span
						class="nav_name">Đơn hàng</span>
					</a> <a href="${pageContext.request.contextPath}/admin/order-details"
						class="nav_link ${menuId == 7 ? 'active' : ''}"> <i
						class="fa-solid fa-cart-flatbed nav_icon"></i> <span
						class="nav_name">Đơn hàng chi tiết</span>
					</a><a href="${pageContext.request.contextPath}/admin/reports"
						class="nav_link  ${menuId == 5 ? 'active' : ''}"> <i
						class="fa-solid fa-chart-simple nav_icon"></i> <span
						class="nav_name">Thống kê</span>
					</a> <a href="${pageContext.request.contextPath}/admin/import"
						class="nav_link  ${menuId == 6 ? 'active' : ''}"> <i
						class="fa-solid fa-file-import nav_icon"></i> <span
						class="nav_name">Thống kê</span>
					</a>
				</div>
			</div>
			<a href="${pageContext.request.contextPath}/logout" class="nav_link">
				<i class="fa-solid fa-right-from-bracket nav_icon"></i> <span
				class="nav_name">Đăng xuất</span>
			</a>
		</nav>
	</div>
	<!--Container Main start-->
	<div class="bg-light">