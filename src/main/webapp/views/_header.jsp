<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="sof3021.ph18485.entities.AccountRole"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><c:out value="${title}"/></title>
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/css/icon/fontawesome.min.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/icon/brands.min.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/icon/solid.min.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/style.css"
	rel="stylesheet">
<script
	src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
</head>
<body class="bg-secondary">
	<header>
		<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
			<div class="container">
				<a class="navbar-brand"
					href="${pageContext.request.contextPath}/home"><i
					class='fa-solid fa-star nav_logo-icon' width="30" height="24"
					class="d-inline-block align-text-top"></i> PH18485 </a>
				<button class="navbar-toggler" type="button"
					data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
					aria-controls="navbarSupportedContent" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="navbarSupportedContent">

					<div class="col-md-4">
						<ul class="navbar-nav me-auto mb-2 mb-lg-0">
							<li class="nav-item"><a class="nav-link" href="#"> <span
									class="glyphicon glyphicon-info-sign"></span> Giới thiệu
							</a></li>
							<li class="nav-item"><a class="nav-link" href="#"> <span
									class="glyphicon glyphicon-envelope"></span> Liên hệ
							</a></li>
							<li class="nav-item"><a class="nav-link" href="#"> <span
									class="glyphicon glyphicon-send"></span> Góp ý
							</a></li>
						</ul>
					</div>
					<div class="col-md-4">
						<form action="${pageContext.request.contextPath}/home"
							method="GET">
							<div class="input-group">
								<input type="search" name="keyword" class="form-control"
									value="${keyword}" placeholder="Search">
								<button class="btn btn-secondary">
									<i class="fa-solid fa-magnifying-glass"></i>
								</button>
							</div>
						</form>
					</div>
					<div class="col-md-4">
						<ul class="navbar-nav d-flex flex-row-reverse">
							<c:if test="${sessionScope.username == null}">
								<li class="nav-item"><a
									href="${pageContext.request.contextPath}/register"
									class="btn btn-success">Đăng ký</a></li>
								<li class="nav-item mx-2"><a
									href="${pageContext.request.contextPath}/login"
									class="btn btn-primary">Đăng nhập</a></li>
							</c:if>
							<c:if test="${sessionScope.role == AccountRole.ADMIN}">
								<li class="nav-item mx-2"><a
									href="${pageContext.request.contextPath}/admin"
									class="btn btn-danger"><i class="fa-solid fa-gear"></i></a></li>
							</c:if>
							<c:if test="${sessionScope.username != null}">
								<div class="dropdown">
									<button class="btn btn-primary dropdown-toggle" type="button"
										id="userMenu" data-bs-toggle="dropdown" aria-expanded="false"><i class="fa-solid fa-user"></i></button>
									<ul class="dropdown-menu" aria-labelledby="userMenu">
										<li><a class="dropdown-item"
											href="${pageContext.request.contextPath}/user/profile">Hồ
												sơ của tôi</a></li>
										<li><a class="dropdown-item"
											href="${pageContext.request.contextPath}/user/order-history">Lịch
												sử đơn hàng</a></li>
										<li><a class="dropdown-item"
											href="${pageContext.request.contextPath}/user/change-password">Đổi
												mật khẩu</a></li>
										<li><hr class="dropdown-divider"></li>
										<li><a class="dropdown-item"
											href="${pageContext.request.contextPath}/logout">Đăng
												xuất</a></li>
									</ul>
								</div>
							</c:if>
							<li class="nav-item mx-2"><a
								href="${pageContext.request.contextPath}/cart"
								class="btn btn-secondary"> <i
									class="fa-solid fa-cart-shopping"></i> <span
									class="badge bg-danger">${cart.count}</span>
							</a></li>
						</ul>
					</div>
				</div>
			</div>
		</nav>
	</header>