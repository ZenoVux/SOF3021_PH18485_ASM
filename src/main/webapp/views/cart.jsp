<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<c:set var="title" value="Giỏ hàng" />
<%@include file="_header.jsp"%>

<div class="container mh-100" style="padding: 100px 10px;">
	<h3 class="text-center my-3">Giỏ hàng</h3>
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
							<th>#</th>
							<th>Tên</th>
							<th>Đơn giá</th>
							<th>Số lượng</th>
							<th>Thành tiền</th>
							<th>Hàng động</th>
						</tr>
					</thead>
					<tbody>
						<c:set var="index" value="0" />
						<c:forEach var="item" items="${cart.items}">
							<c:set var="index" value="${index = index + 1}" />
							<form
								action="${pageContext.request.contextPath}/cart/update/${item.id}"
								method="post">
								<input type="hidden" name="id" value="${item.id}">
								<tr>
									<td>${index}</td>
									<td>${item.name}</td>
									<td><fmt:formatNumber type="number"
											pattern="###,###,### VNĐ" value="${item.price}" /></td>
									<td><input type="number" name="quantity"
										value="${item.quantity}" onblur="this.form.submit()"
										style="width: 50px;"></td>
									<td><fmt:formatNumber type="number"
											pattern="###,###,### VNĐ"
											value="${item.price * item.quantity}" /></td>
									<td><a href="./cart/remove/${item.id}"
										onclick="return confirm('Bạn muốn xoá sản phẩm này khỏi giỏ hàng?');"
										class="btn btn-danger btn-sm">Xoá</a></td>
								</tr>
							</form>
						</c:forEach>
					</tbody>
				</table>
				<c:if test="${cart.count <= 0}">
					<p class="text-center">Giỏ hàng trống</p>
				</c:if>
				<c:if test="${cart.count > 0}">
					<p>
						Tổng tiền:
						<fmt:formatNumber type="number" pattern="###,###,### VNĐ"
							value="${cart.amount}" />
					</p>
					<div class="d-flex">
						<div class="me-auto">
							<a href="${pageContext.request.contextPath}/cart/checkout" class="btn btn-primary">Mua hàng</a>
						</div>
						<div class="ms-auto">
							<a href="${pageContext.request.contextPath}/cart/clear"
								onclick="return confirm('Bạn muốn xoá tất cả sản trong giỏ hàng?');"
								class="btn btn-secondary">Xoá tất cả</a>
						</div>
					</div>
				</c:if>
			</div>
		</div>
	</div>
</div>

<%@include file="_footer.jsp"%>