<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<c:set var="title" value="Thêm đơn hàng chi tiết" />
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<c:set var="menuId" value="7" />
<%@include file="../_header.jsp"%>
<div class="row">
	<div class="mx-auto" style="max-width: 600px;">
		<h3 class="text-center my-3">Thêm đơn hàng chi tiết</h3>
		<c:if test="${message != null}">
			<div class="alert alert-success alert-dismissible fade show"
				role="alert">
				${message}
				<button type="button" class="btn-close" data-bs-dismiss="alert"
					aria-label="Close"></button>
			</div>
		</c:if>
		<c:if test="${error != null}">
			<div class="alert alert-danger alert-dismissible fade show"
				role="alert">
				${error}
				<button type="button" class="btn-close" data-bs-dismiss="alert"
					aria-label="Close"></button>
			</div>
		</c:if>
		<f:form method="POST"
			action="${pageContext.request.contextPath}/admin/order-details/create"
			modelAttribute="data">
			<div class="mb-3">
				<label class="form-label">Đơn hàng</label>
				<f:select path="order" cssClass="form-select">
					<f:options items="${orders}" itemValue="id" itemLabel="address" />
				</f:select>
				<f:errors path="order" element="div"
					cssClass="form-text text-danger" delimiter="<br>"></f:errors>
			</div>
			<div class="mb-3">
				<label class="form-label">Sản phẩm</label>
				<f:select path="product" cssClass="form-select">
					<f:options items="${products}" itemValue="id" itemLabel="name" />
				</f:select>
				<f:errors path="product" element="div"
					cssClass="form-text text-danger" delimiter="<br>"></f:errors>
			</div>
			<div class="mb-3">
				<label class="form-label">Giá</label>
				<f:input type="text" name="price" class="form-control" path="price" />
				<f:errors path="price" element="div"
					cssClass="form-text text-danger" delimiter="<br>"></f:errors>
			</div>
			<div class="mb-3">
				<label class="form-label">Số lượng</label>
				<f:input type="number" name="quantity" class="form-control"
					path="quantity" />
				<f:errors path="quantity" element="div"
					cssClass="form-text text-danger" delimiter="<br>"></f:errors>
			</div>
			<div class="d-grid gap-2">
				<button type="submit" class="btn btn-primary">Thêm</button>
			</div>
		</f:form>
	</div>
</div>
<%@include file="../_footer.jsp"%>