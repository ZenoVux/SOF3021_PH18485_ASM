<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<c:set var="title" value="Quên mật khẩu" />
<%@include file="_header.jsp"%>

<div class="container py-5">
	<div class="mx-auto" style="max-width: 600px;">
		<h3 class="text-center my-3">Khôi phục mật khẩu</h3>
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
			action="${pageContext.request.contextPath}/reset-password"
			modelAttribute="data">
			<f:hidden path="token" name="token"/>
			<div class="mb-3">
				<label class="form-label">Mật khẩu</label>
				<f:input type="password" name="password" class="form-control"
					path="password" />
				<f:errors path="password" element="div"
					cssClass="form-text text-danger" delimiter="<br>"></f:errors>
			</div>
			<div class="mb-3">
				<label class="form-label">Xác nhân mật khẩu</label>
				<f:input type="password" name="rePassword" class="form-control"
					path="rePassword" />
				<f:errors path="rePassword" element="div"
					cssClass="form-text text-danger" delimiter="<br>"></f:errors>
			</div>
			<div class="d-grid gap-2 mb-3">
				<button class="btn btn-primary">Đổi mật khẩu</button>
			</div>
		</f:form>
	</div>
</div>

<%@include file="_footer.jsp"%>
</html>