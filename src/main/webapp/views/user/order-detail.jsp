<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<c:set var="title" value="Chi tiết đơn hàng" />
<%@include file="../_header.jsp"%>

<div class="container mh-100" style="padding: 100px 10px;">
	<h3 class="text-center my-3">Chi tiết đơn hàng</h3>
	<div class="mx-auto" style="max-width: 800px">
		<div class="card">
			<div class="card-body">
				<table class="table table-hover">
					<thead>
						<tr>
							<th scope="col">#</th>
							<th scope="col">Tên sản phẩm</th>
							<th scope="col">Đơn giá</th>
							<th scope="col">Số lượng</th>
							<th scope="col">Thành tiền</th>
						</tr>
					</thead>
					<tbody>
						<c:set var="index" value="0" />
						<c:forEach var="orderDetail" items="${orderDetails}">
							<c:set var="index" value="${index = index + 1}" />
							<tr>
								<th scope="row">${index}</th>
								<td>${orderDetail.product.name}</td>
								<td><fmt:formatNumber type="number"
									pattern="###,###,### VNĐ" value="${orderDetail.price}" /></td>
								<td>${orderDetail.quantity}</td>
								<td><fmt:formatNumber type="number"
									pattern="###,###,### VNĐ" value="${orderDetail.price * orderDetail.quantity}" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
<%@include file="../_footer.jsp"%>