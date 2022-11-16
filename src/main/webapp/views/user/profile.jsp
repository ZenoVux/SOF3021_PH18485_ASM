<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<c:set var="title" value="Hồ sơ của tôi" />
<%@include file="../_header.jsp"%>

<div class="container py-5">
	<div class="mx-auto" style="max-width: 600px;">
		<div class="text-center">
			<img id="img-preview"
				src="${pageContext.request.contextPath}/images/${empty image ? 'no-image.jpg' : 'account/'.concat(image)}"
				width="150px" height="150px">
		</div>
		<h3 class="text-center my-3">${data.fullname}</h3>
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
			action="${pageContext.request.contextPath}/user/profile"
			modelAttribute="data" enctype="multipart/form-data">
			<div class="mb-3">
				<f:label path="fullname" cssClass="form-label">Họ và tên</f:label>
				<f:input type="text" name="fullname" class="form-control"
					path="fullname" />
				<f:errors path="fullname" element="div"
					cssClass="form-text text-danger" delimiter="<br>"></f:errors>
			</div>
			<div class="mb-3">
				<label class="form-label">Hình ảnh</label>
				<input type="file" id="file-input" name="image"
					accept="image/png, image/jpeg" class="form-control" />
			</div>
			<div class="mb-3">
				<f:label path="email" cssClass="form-label">Email</f:label>
				<f:input type="text" name="email" class="form-control" path="email" />
				<f:errors path="email" element="div"
					cssClass="form-text text-danger" delimiter="<br>"></f:errors>
			</div>
			<div class="d-grid gap-2">
					<button type="submit" class="btn btn-success">Cập nhật</button>
			</div>
		</f:form>
	</div>
</div>

<script type="text/javascript">
	const input = document.getElementById('file-input');
	const image = document.getElementById('img-preview');

	input.addEventListener('change', (e) => {
	    if (e.target.files.length) {
	        const src = URL.createObjectURL(e.target.files[0]);
	        image.src = src;
	    }
	});
</script>
<%@include file="../_footer.jsp"%>