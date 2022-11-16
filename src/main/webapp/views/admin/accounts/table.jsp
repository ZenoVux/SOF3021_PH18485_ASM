<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<c:set var="title" value="Danh sách người dùng" />
<c:set var="menuId" value="1" />
<%@include file="../_header.jsp"%>
<div class="card mb-2">
	<div class="card-body row">
		<div class="col-3">
			<form action="${pageContext.request.contextPath}/admin/accounts"
				method="GET">
				<div class="input-group">
					<input class="form-control" type="search" name="keyword"
						placeholder="Tên người dùng?" value="${keyword}">
					<button class="btn btn-secondary">
						<i class="fa-solid fa-magnifying-glass"></i>
					</button>
				</div>
			</form>
		</div>
		<div class="col-9 d-flex">
			<div class="ms-auto">
				<a href="${pageContext.request.contextPath}/admin/accounts/create"
					class="btn btn-success"> <i class="fa-solid fa-plus"></i> Thêm
				</a> <a href="${pageContext.request.contextPath}/admin/accounts/export"
					class="btn btn-secondary"><i class="fa-solid fa-file-excel"></i>
					Xuất excel</a>
			</div>
		</div>
	</div>
</div>
<div class="card">
	<div class="card-body">
		<div class="table-responsive">
			<table class="table  table-bordered table-hover">
				<thead>
					<tr>
						<th scope="col">ID</th>
						<th scope="col">Hình ảnh</th>
						<th scope="col">Họ và tên</th>
						<th scope="col">Tài khoản</th>
						<th scope="col">Email</th>
						<th scope="col">Vai trò</th>
						<th scope="col">Trạng thái</th>
						<th scope="col">Hành động</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="account" items="${page.content}">
						<tr>
							<th scope="row">${account.id}</th>
							<td><img
								src="${pageContext.request.contextPath}/images/${empty account.photo ? 'no-image.jpg' : 'account/'.concat(account.photo)}"
								alt="" width="100px" height="100px"></td>
							<td>${account.fullname}</td>
							<td>${account.username}</td>
							<td>${account.email}</td>
							<td>${account.role}</td>
							<td><c:if test="${account.activated == 1}">
									<span class="text-primary">Đã kích hoạt</span>
								</c:if> <c:if test="${account.activated == 0}">
									<span class="text-danger">Chưa kích hoạt</span>
								</c:if></td>
							<td>
								<div class="btn-group" role="group" aria-label="Basic example">
									<a
										href="${pageContext.request.contextPath}/admin/accounts/update/${account.id}"
										class="btn btn-primary"> <i
										class="fa-solid fa-pen-to-square"></i> Sửa
									</a> <a
										href="${pageContext.request.contextPath}/admin/accounts/delete?id=${account.id}"
										onclick="return confirm('Bạn muốn xoá người dùng này?');"
										class="btn btn-danger"> <i class="fa-solid fa-trash-can"></i>
										Xoá
									</a>
								</div>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="row">
			<ul class="pagination justify-content-center">
				<li class="page-item ${page.first ? 'disabled' : ''}"><a
					href="${pageContext.request.contextPath}/admin/accounts?keyword=${keyword}&p=1"
					class="page-link"> <i class="fa-solid fa-backward-step"></i>
				</a></li>
				<li class="page-item ${page.first ? 'disabled' : ''}"><a
					href="${pageContext.request.contextPath}/admin/accounts?keyword=${keyword}&p=${page.number}"
					class="page-link"> <i class="fa-solid fa-backward"></i>
				</a></li>
				<li class="page-item disabled">
					<button class="page-link">${page.number + 1}</button>
				</li>
				<li class="page-item ${page.last ? 'disabled' : ''}"><a
					href="${pageContext.request.contextPath}/admin/accounts?keyword=${keyword}&p=${page.number + 2}"
					class="page-link"> <i class="fa-solid fa-forward"></i>
				</a></li>
				<li class="page-item ${page.last ? 'disabled' : ''}"><a
					href="${pageContext.request.contextPath}/admin/accounts?keyword=${keyword}&p=${page.totalPages}"
					class="page-link"> <i class="fa-solid fa-forward-step"></i>
				</a></li>
			</ul>
		</div>
	</div>
</div>
<%@include file="../_footer.jsp"%>