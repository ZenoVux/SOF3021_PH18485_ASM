<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="title" value="Nhập dữ liệu" />
<c:set var="menuId" value="6" />
<%@include file="_header.jsp"%>
<div class="mx-auto py-5" style="max-width: 600px;">
	<h3 class="text-center my-3">Nhập dữ liệu</h3>
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
	<form method="POST"
		action="${pageContext.request.contextPath}/admin/import"
		enctype="multipart/form-data">
		<div class="mb-3">
			<label class="form-label">File</label> <input type="file" name="file"
				class="form-control"
				accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel" />
		</div>
		<div class="d-grid gap-2 mb-3">
			<button class="btn btn-primary">Nhập dữ liệu</button>
		</div>
	</form>
</div>
<%@include file="_footer.jsp"%>