<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="title" value="Chi tiết đơn hàng" />
<c:set var="menuId" value="4" />
<%@include file="../_header.jsp"%>

<div class="container mh-100" style="padding: 100px 10px;">
	<h3 class="text-center my-3">Chi tiết đơn hàng</h3>
	<div class="mx-auto" style="max-width: 900px">
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
						<th scope="col" width="250px">Hành động</th>
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
		</div>
	</div>
</div>
<%@include file="../_footer.jsp"%>