<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<c:set var="title" value="Thêm danh mục" />
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<c:set var="menuId" value="3" />
<%@include file="../_header.jsp"%>
<div class="row">
	<div class="mx-auto" style="max-width: 600px;">
		<h3 class="text-center my-3">Thêm danh mục</h3>
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
			action="${pageContext.request.contextPath}/admin/categories/create"
			modelAttribute="data" enctype="multipart/form-data">
			<div class="mb-3">
				<label class="form-label">Tên danh mục</label>
				<f:input type="text" name="name" class="form-control" path="name" />
				<f:errors path="name" element="div" cssClass="form-text text-danger"
					delimiter="<br>"></f:errors>
			</div>
			<div class="d-grid gap-2">
				<button type="submit" class="btn btn-primary">Thêm</button>
			</div>
		</f:form>
	</div>
</div>
<%@include file="../_footer.jsp"%>