<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<c:set var="title" value="Đăng ký" />
<%@include file="_header.jsp"%>

<div class="container py-5">
	<div class="mx-auto" style="max-width: 600px;">
        <h3 class="text-center my-3">Đăng ký</h3>
		<c:if test="${message != null}">
			<div class="alert alert-success alert-dismissible fade show"
				role="alert">
				${message}
				<button type="button" class="btn-close" data-bs-dismiss="alert"
					aria-label="Close"></button>
			</div>
		</c:if>
		<f:form method="POST"
			action="${pageContext.request.contextPath}/register"
			modelAttribute="data"
			enctype="multipart/form-data">
			<div class="mb-3">
				<label class="form-label">Tên đăng nhập</label>
				<f:input type="text" name="username" class="form-control"
					path="username" />
				<f:errors path="username" element="div" cssClass="form-text text-danger"
					delimiter="<br>"></f:errors>
			</div>
			<div class="mb-3">
				<label class="form-label">Họ tên</label>
				<f:input type="text" name="fullname" class="form-control"
					path="fullname" />
				<f:errors path="fullname" element="div" cssClass="form-text text-danger"
					delimiter="<br>"></f:errors>
			</div>
			<div class="mb-3">
				<label class="form-label">Email</label>
				<f:input type="email" name="email" class="form-control" path="email" />
				<f:errors path="email" element="div" cssClass="form-text text-danger"
					delimiter="<br>"></f:errors>
			</div>
			<div class="mb-3">
				<label class="form-label">Mật khẩu</label>
				<f:input type="password" name="password" class="form-control"
					path="password" />
				<f:errors path="password" element="div" cssClass="form-text text-danger"
					delimiter="<br>"></f:errors>
			</div>
			<div class="mb-3">
				<label class="form-label">Xác nhận mật khẩu</label>
				<f:input type="rePassword" name="rePassword" class="form-control"
					path="rePassword" />
				<f:errors path="rePassword" element="div" cssClass="form-text text-danger"
					delimiter="<br>"></f:errors>
			</div>
			<div class="d-grid gap-2 mb-3">
				<button class="btn btn-primary">Đăng ký</button>
			</div>
		</f:form>
		<div class="text-divider">
			<span>Hoặc</span>
		</div>
		<p class="text-center my-2">
			Bạn đã có tài khoản? <a href="./login">Đăng nhập ngay</a>
		</p>
	</div>
</div>

<%@include file="_footer.jsp"%></html>