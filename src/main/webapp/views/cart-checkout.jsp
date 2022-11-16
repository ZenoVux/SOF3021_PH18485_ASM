<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<c:set var="title" value="Giỏ hàng" />
<%@include file="_header.jsp"%><div class="container mh-100"
	style="padding: 100px 10px;">
	<h3 class="text-center my-3">Xác nhận đơn hàng</h3>
	<c:if test="${message != null}">
		<div class="alert alert-success alert-dismissible fade show"
			role="alert">
			${message}
			<button type="button" class="btn-close" data-bs-dismiss="alert"
				aria-label="Close"></button>
		</div>
	</c:if>
	<div class="row">
		<div class="card col-7 mx-2">
			<div class="card-body">
				<table class="table table-hover">
					<thead>
						<tr>
							<th scope="col">ID</th>
							<th scope="col">Tên sản phẩm</th>
							<th scope="col">Đơn giá</th>
							<th scope="col">Số lượng</th>
							<th scope="col">Thành tiền</th>
						</tr>
					</thead>
					<tbody>
						<c:set var="index" value="0" />
						<c:forEach var="item" items="${cart.items}">
							<c:set var="index" value="${index = index + 1}" />
							<tr>
								<th scope="row">${index}</th>
								<td>${item.name}</td>
								<td><fmt:formatNumber type="number"
										pattern="###,###,### VNĐ" value="${item.price}" /></td>
								<td>${item.quantity}</td>
								<td><fmt:formatNumber type="number"
										pattern="###,###,### VNĐ"
										value="${item.price * item.quantity}" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<div class="card col-4 mx-2">
			<div class="card-body">
				<f:form method="POST"
					action="${pageContext.request.contextPath}/cart/checkout"
					modelAttribute="data">
					<p>
						<b> Tổng tiền: <fmt:formatNumber type="number"
								pattern="###,###,### VNĐ" value="${cart.amount}" /></b>
					</p>
					<div class="mb-3">
						<f:label path="address" class="form-label">Địa chỉ</f:label>
						<f:textarea path="address" class="form-control" rows="3"></f:textarea>
						<f:errors path="address" element="div"
							cssClass="form-text text-danger" delimiter="<br>"></f:errors>
					</div>
					<div class="d-grid gap-2">
						<button class="btn btn-primary">Đặt hàng</button>
					</div>
				</f:form>
			</div>
		</div>
	</div>
</div>
<%@include file="_footer.jsp"%>