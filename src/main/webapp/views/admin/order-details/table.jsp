<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="title" value="Danh sách đơn hàng chi tiết" />
<c:set var="menuId" value="7" />
<%@include file="../_header.jsp"%>
<div class="card mb-2">
	<div class="card-body row">
		<div class="col-12 d-flex">
			<div class="ms-auto">
				<a
					href="${pageContext.request.contextPath}/admin/order-details/create"
					class="btn btn-success"> <i class="fa-solid fa-plus"></i> Thêm
				</a> <a
					href="${pageContext.request.contextPath}/admin/order-details/export"
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
						<th scope="col">Đơn hàng</th>
						<th scope="col">Sản phẩm</th>
						<th scope="col">Giá</th>
						<th scope="col">Số lượng</th>
						<th scope="col" width="350px">Hành động</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="orderDetail" items="${page.content}">
						<tr>
							<th scope="row">${orderDetail.id}</th>
							<td>${orderDetail.order.id}</td>
							<td>${orderDetail.product.name}</td>
							<td><fmt:formatNumber type="number"
									pattern="###,###,### VNĐ" value="${orderDetail.price}" /></td>
							<td>${orderDetail.quantity}</td>
							<td>
								<div class="btn-group" role="group">
									<a
										href="${pageContext.request.contextPath}/admin/order-details/update/${orderDetail.id}"
										class="btn btn-primary"> <i
										class="fa-solid fa-pen-to-square"></i> Sửa
									</a> </a> <a
										href="${pageContext.request.contextPath}/admin/order-details/delete/${orderDetail.id}"
										onclick="return confirm('Bạn muốn xoá đơn hàng chi tiết này?');"
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
					href="${pageContext.request.contextPath}/admin/order-details?p=1"
					class="page-link"> <i class="fa-solid fa-backward-step"></i>
				</a></li>
				<li class="page-item ${page.first ? 'disabled' : ''}"><a
					href="${pageContext.request.contextPath}/admin/order-details?p=${page.number}"
					class="page-link"> <i class="fa-solid fa-backward"></i>
				</a></li>
				<li class="page-item disabled">
					<button class="page-link">${page.number + 1}</button>
				</li>
				<li class="page-item ${page.last ? 'disabled' : ''}"><a
					href="${pageContext.request.contextPath}/admin/order-details?p=${page.number + 2}"
					class="page-link"> <i class="fa-solid fa-forward"></i>
				</a></li>
				<li class="page-item ${page.last ? 'disabled' : ''}"><a
					href="${pageContext.request.contextPath}/admin/order-details?p=${page.totalPages}"
					class="page-link"> <i class="fa-solid fa-forward-step"></i>
				</a></li>
			</ul>
		</div>
	</div>
</div>
<%@include file="../_footer.jsp"%>