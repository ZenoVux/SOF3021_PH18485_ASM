<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<c:set var="title" value="Đăng nhập" />
<%@include file="_header.jsp"%>

<div class="container py-5">
	<div class="row" style="max-width: 800px">
		<div class="col-5 my-2">
			<img class="img-thumbnail ms-auto"
				src="${pageContext.request.contextPath}/images/${empty product.image ? 'no-image.jpg' : 'product/'.concat(product.image)}" />
		</div>
		<div class="col-6 my-2">
			<h3>${product.name}</h3>
			<p class="fw-bold text-danger">
				<fmt:formatNumber type="number" pattern="###,###,### VNĐ"
					value="${product.price - (product.price * product.discount)}" />
			</p>
			<c:if test="${product.discount > 0}">
				<p class="text-decoration-line-through">
					<fmt:formatNumber type="number" pattern="###,###,### VNĐ"
						value="${product.price}" />
				</p>
			</c:if>
			<p>Mô tả: ${product.description}</p>
		</div>
	</div>
	<hr>
	<h3>Sản phẩm cùng danh mục</h3>
	<div class="row">
		<c:forEach var="product" items="${products}">
			<div class="col-md-3 my-2">
				<div class="card" style="position: relative;">
					<img
						src="${pageContext.request.contextPath}/images/${empty product.image ? 'no-image.jpg' : 'product/'.concat(product.image)}"
						class="card-img-top" width="300px" height="225px" alt="...">
					<div class="card-body">
						<p class="card-text">
						<h5>${product.name}</h5>
						<div class="row">
							<div class="col-6 fw-bold text-danger">
								<fmt:formatNumber type="number" pattern="###,###,### VNĐ"
									value="${product.price - (product.price * product.discount)}" />
							</div>
							<c:if test="${product.discount > 0}">
								<div class="col-6 text-decoration-line-through">
									<fmt:formatNumber type="number" pattern="###,###,### VNĐ"
										value="${product.price}" />
								</div>
							</c:if>
						</div>
						</p>
						<div class="row">
							<div class="col-md-9 mb-3">
								<a
									href="${pageContext.request.contextPath}/product/${product.id}"
									class="btn btn-primary" style="width: 100%">Xem chi tiết</a>
							</div>
							<div class="col-md-3">
								<a
									href="${pageContext.request.contextPath}/cart/add/${product.id}"
									class="btn btn-success" style="width: 100%"> <i
									class="fa-solid fa-cart-plus"></i>
								</a>
							</div>
						</div>
					</div>
					<c:if test="${product.discount > 0}">
						<div class="product-discount text-center text-light fw-bold fs-7">
							Giảm
							<fmt:formatNumber type="percent" value="${product.discount}" />
						</div>
					</c:if>
				</div>
			</div>
		</c:forEach>
	</div>
</div>

<%@include file="_footer.jsp"%>