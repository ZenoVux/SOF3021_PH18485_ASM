<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<c:set var="title" value="Lịch sử đơn hàng" />
<%@include file="../_header.jsp"%>

<div class="container mh-100" style="padding: 100px 10px;">
	<h3 class="text-center my-3">Lịch sử đơn hàng</h3>
	<c:if test="${message != null}">
		<div class="alert alert-success alert-dismissible fade show"
			role="alert">
			${message}
			<button type="button" class="btn-close" data-bs-dismiss="alert"
				aria-label="Close"></button>
		</div>
	</c:if>
	<div class="mx-auto" style="max-width: 800px">
		<div class="card">
			<div class="card-body">
				<table class="table table-hover">
					<thead>
						<tr>
							<th scope="col">ID</th>
							<th scope="col">Số lượng</th>
							<th scope="col">Địa chỉ</th>
							<th scope="col">Ngày tạo</th>
							<th scope="col">Hành động</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="order" items="${page.content}">
							<tr>
								<th scope="row">${order.id}</th>
								<td>${order.orderDetails.size()}</td>
								<td>${order.address}</td>
								<td>${order.createdDate}</td>
								<td>
									<div class="btn-group" role="group">
										<a
											href="${pageContext.request.contextPath}/user/order-history/detail?id=${order.id}"
											class="btn btn-primary btn-sm">Xem chi tiết</a><a
											href="${pageContext.request.contextPath}/user/order-history/${order.id}/pdf"
											class="btn btn-success btn-sm">Xuất PDF</a>
									</div>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<c:if test="${page.content.size() <= 0}">
					<p class="text-center">Lịch sử đơn hàng trống</p>
				</c:if>
				<c:if test="${page.content.size() > 0}">
					<div class="row">
						<ul class="pagination justify-content-center">
							<li class="page-item ${page.first ? 'disabled' : ''}"><a
								href="${pageContext.request.contextPath}/user/order-history?p=1"
								class="page-link"> <i class="fa-solid fa-backward-step"></i>
							</a></li>
							<li class="page-item ${page.first ? 'disabled' : ''}"><a
								href="${pageContext.request.contextPath}/user/order-history?p=${page.number}"
								class="page-link"> <i class="fa-solid fa-backward"></i>
							</a></li>
							<li class="page-item disabled">
								<button class="page-link">${page.number + 1}</button>
							</li>
							<li class="page-item ${page.last ? 'disabled' : ''}"><a
								href="${pageContext.request.contextPath}/user/order-history?p=${page.number + 2}"
								class="page-link"> <i class="fa-solid fa-forward"></i>
							</a></li>
							<li class="page-item ${page.last ? 'disabled' : ''}"><a
								href="${pageContext.request.contextPath}/user/order-history?p=${page.totalPages}"
								class="page-link"> <i class="fa-solid fa-forward-step"></i>
							</a></li>
						</ul>
					</div>
				</c:if>
			</div>
		</div>

	</div>
</div>
<%@include file="../_footer.jsp"%>