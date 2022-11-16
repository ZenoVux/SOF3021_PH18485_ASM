<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<c:set var="title"
	value="${data.id == null ? 'Thêm' : 'Cập nhật'} đơn hàng" />
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<c:set var="menuId" value="4" />
<%@include file="../_header.jsp"%>
<div class="row">
	<div class="mx-auto" style="max-width: 600px;">
		<h3 class="text-center my-3">${data.id == null ? 'Thêm' : 'Cập nhật'}
			đơn hàng</h3>
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
			action="${pageContext.request.contextPath}/admin/orders/create"
			modelAttribute="data">
			<div class="mb-3">
				<label class="form-label">Khách hàng</label>
				<f:select path="account" cssClass="form-select">
					<f:options items="${accounts}" itemValue="id" itemLabel="username" />
				</f:select>
				<f:errors path="account" element="div"
					cssClass="form-text text-danger" delimiter="<br>"></f:errors>
			</div>
			<div class="mb-3">
				<f:label path="address" class="form-label">Địa chỉ</f:label>
				<f:textarea path="address" class="form-control" rows="3"></f:textarea>
				<f:errors path="address" element="div"
					cssClass="form-text text-danger" delimiter="<br>"></f:errors>
			</div>
			<div class="d-grid gap-2">
				<c:if test="${data.id == null}">
					<button type="submit" class="btn btn-primary">Thêm</button>
				</c:if>
				<c:if test="${data.id != null}">
					<button type="submit" class="btn btn-success">Cập nhật</button>
				</c:if>
			</div>
		</f:form>
	</div>
</div>
<%@include file="../_footer.jsp"%>