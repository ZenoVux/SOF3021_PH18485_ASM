<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="title" value="Trang chủ" />
<%@include file="_header.jsp"%>
<div class="container py-5">
	<div class="row">
		<aside class="col-md-3">
			<div class="card my-2">
				<div class="card-header" data-bs-toggle="collapse"
					data-bs-target="#danhMuc">
					<span class="glyphicon glyphicon-th-list"></span> DANH MỤC
				</div>
				<ul class="list-group list-group-flush collapse show" id="danhMuc">
					<c:forEach var="category" items="${categories}">
						<a
							href="${pageContext.request.contextPath}/category/${category.id}"
							class="list-group-item d-flex justify-content-between align-items-start">${category.name}<span
							class="badge bg-primary rounded-pill">${category.products.size()}</span></a>
					</c:forEach>
				</ul>
			</div>
		</aside>
		<article class="col-md-9">
			<c:if test="${page.first && empty keyword}">
				<h3>Sản phẩm giảm giá</h3>
				<hr>
				<div class="row">
					<c:forEach var="product" items="${products}">
						<div class="col-md-4 my-2">
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
									<div
										class="product-discount text-center text-light fw-bold fs-7">
										Giảm
										<fmt:formatNumber type="percent" value="${product.discount}" />
									</div>
								</c:if>
							</div>
						</div>
					</c:forEach>
				</div>
			</c:if>
			<h3>Danh sách sản phẩm</h3>
			<hr>
			<div class="row">
				<c:forEach var="product" items="${page.content}">
					<div class="col-md-4 my-2">
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
								<div
									class="product-discount text-center text-light fw-bold fs-7">
									Giảm
									<fmt:formatNumber type="percent" value="${product.discount}" />
								</div>
							</c:if>
						</div>
					</div>
				</c:forEach>
			</div>
			<!-- Phân trang -->
			<div class="row">
				<c:if test="${not empty page.content}">
					<ul class="pagination justify-content-center">
						<li class="page-item ${page.first ? 'disabled' : ''}"><a
							href="${pageContext.request.contextPath}/home?${empty keyword ? '' : 'keyword='.concat(keyword).concat('&')}p=1"
							class="page-link"> <i class="fa-solid fa-backward-step"></i>
						</a></li>
						<li class="page-item ${page.first ? 'disabled' : ''}"><a
							href="${pageContext.request.contextPath}/home?${empty keyword ? '' : 'keyword='.concat(keyword).concat('&')}p=${page.number}"
							class="page-link"> <i class="fa-solid fa-backward"></i>
						</a></li>
						<li class="page-item disabled">
							<button class="page-link">${page.number + 1}</button>
						</li>
						<li class="page-item ${page.last ? 'disabled' : ''}"><a
							href="${pageContext.request.contextPath}/home?${empty keyword ? '' : 'keyword='.concat(keyword).concat('&')}p=${page.number + 2}"
							class="page-link"> <i class="fa-solid fa-forward"></i>
						</a></li>
						<li class="page-item ${page.last ? 'disabled' : ''}"><a
							href="${pageContext.request.contextPath}/home?${empty keyword ? '' : 'keyword='.concat(keyword).concat('&')}p=${page.totalPages}"
							class="page-link"> <i class="fa-solid fa-forward-step"></i>
						</a></li>
					</ul>
				</c:if>
				<c:if test="${empty page.content}">
					<div class="text-center">Không có sản phẩm</div>
				</c:if>
			</div>
		</article>
	</div>
</div>
<%@include file="_footer.jsp"%>