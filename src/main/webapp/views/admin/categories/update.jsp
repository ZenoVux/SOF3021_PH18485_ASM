<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<c:set var="title" value="Cập nhật danh mục" />
<c:set var="menuId" value="3" />
<%@include file="../_header.jsp"%>
<div class="row">
	<div class="mx-auto" style="max-width: 600px;">
		<h3 class="text-center my-3">Cập nhật danh mục</h3>
		<c:if test="${message != null}">
			<div class="alert alert-success alert-dismissible fade show"
				role="alert">
				${message}
				<button type="button" class="btn-close" data-bs-dismiss="alert"
					aria-label="Close"></button>
			</div>
		</c:if>
		<f:form method="POST"
			action="${pageContext.request.contextPath}/admin/categories/update"
			modelAttribute="data" enctype="multipart/form-data">
			<f:input type="hidden" name="id" path="id" />
			<div class="mb-3">
				<label class="form-label">Tên danh mục</label>
				<f:input type="text" name="name" class="form-control" path="name" />
				<f:errors path="name" element="div" cssClass="form-text text-danger"
					delimiter="<br>"></f:errors>
			</div>
			<div class="d-grid gap-2">
				<button type="submit" class="btn btn-success">Cập nhật</button>
			</div>
		</f:form>
	</div>
</div>
<%@include file="../_footer.jsp"%>