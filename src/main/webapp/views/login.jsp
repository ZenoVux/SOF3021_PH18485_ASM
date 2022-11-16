<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<c:set var="title" value="Đăng nhập" />
<%@include file="_header.jsp"%>

<div class="container py-5">
	<div class="mx-auto" style="max-width: 600px;">
		<h3 class="text-center my-3">Đăng nhập</h3>
		<c:if test="${message != null}">
			<div class="alert alert-danger alert-dismissible fade show"
				role="alert">
				${message}
				<button type="button" class="btn-close" data-bs-dismiss="alert"
					aria-label="Close"></button>
			</div>
		</c:if>
		<f:form method="POST"
			action="${pageContext.request.contextPath}/login"
			modelAttribute="data">
			<div class="mb-3">
				<label class="form-label">Tên đăng nhập</label>
				<f:input type="text" name="username" class="form-control"
					path="username" />
				<f:errors path="username" element="div"
					cssClass="form-text text-danger" delimiter="<br>"></f:errors>
			</div>
			<div class="mb-3">
				<label class="form-label">Mật khẩu</label>
				<f:input type="password" name="password" class="form-control"
					path="password" />
				<f:errors path="password" element="div"
					cssClass="form-text text-danger" delimiter="<br>"></f:errors>
				<a href="${pageContext.request.contextPath}/forgot-password">Quên
					mật khẩu?</a>
			</div>
			<div class="d-grid gap-2 mb-3">
				<button class="btn btn-primary">Đăng nhập</button>
			</div>
		</f:form>
		<div class="text-divider">
			<span>Hoặc</span>
		</div>
		<p class="text-center my-2">
			Bạn chưa có tài khoản? <a
				href="${pageContext.request.contextPath}/register">Đăng ký ngay</a>
		</p>
	</div>
</div>

<%@include file="_footer.jsp"%>
</html>