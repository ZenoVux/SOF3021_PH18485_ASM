<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<c:set var="title" value="Danh sách đơn hàng" />
<c:set var="menuId" value="4" />
<%@include file="../_header.jsp"%>
<div class="card mb-2">
	<div class="card-body row">
		<div class="col-3">
			<form action="${pageContext.request.contextPath}/admin/orders"
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
				<a href="${pageContext.request.contextPath}/admin/orders/create"
					class="btn btn-success"> <i class="fa-solid fa-plus"></i> Thêm
				</a> <a href="${pageContext.request.contextPath}/admin/orders/export"
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
						<th scope="col">Khách hàng</th>
						<th scope="col">Địa chỉ</th>
						<th scope="col">Ngày tạo</th>
						<th scope="col" width="350px">Hành động</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="order" items="${page.content}">
						<tr>
							<th scope="row">${order.id}</th>
							<td>${order.account.fullname}</td>
							<td>${order.address}</td>
							<td>${order.createdDate}</td>
							<td>
								<div class="btn-group" role="group">
									<a
										href="${pageContext.request.contextPath}/admin/orders/detail?id=${order.id}"
										class="btn btn-success"> <i class="fa-solid fa-eye"></i>
										Chi tiết
									</a><a
										href="${pageContext.request.contextPath}/admin/orders/update/${order.id}"
										class="btn btn-primary"> <i
										class="fa-solid fa-pen-to-square"></i> Sửa
									</a> </a> <a
										href="${pageContext.request.contextPath}/admin/orders/delete/${account.id}"
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
					href="${pageContext.request.contextPath}/admin/orders?keyword=${keyword}&p=1"
					class="page-link"> <i class="fa-solid fa-backward-step"></i>
				</a></li>
				<li class="page-item ${page.first ? 'disabled' : ''}"><a
					href="${pageContext.request.contextPath}/admin/orders?keyword=${keyword}&p=${page.number}"
					class="page-link"> <i class="fa-solid fa-backward"></i>
				</a></li>
				<li class="page-item disabled">
					<button class="page-link">${page.number + 1}</button>
				</li>
				<li class="page-item ${page.last ? 'disabled' : ''}"><a
					href="${pageContext.request.contextPath}/admin/orders?keyword=${keyword}&p=${page.number + 2}"
					class="page-link"> <i class="fa-solid fa-forward"></i>
				</a></li>
				<li class="page-item ${page.last ? 'disabled' : ''}"><a
					href="${pageContext.request.contextPath}/admin/orders?keyword=${keyword}&p=${page.totalPages}"
					class="page-link"> <i class="fa-solid fa-forward-step"></i>
				</a></li>
			</ul>
		</div>
	</div>
</div>
<%@include file="../_footer.jsp"%>