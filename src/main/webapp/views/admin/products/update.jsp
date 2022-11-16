<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<c:set var="title" value="Cập nhật sản phẩm" />
<c:set var="menuId" value="2" />
<%@include file="../_header.jsp"%>
<div class="mx-auto" style="max-width: 600px;">
	<h3 class="text-center my-3">Cập nhật sản phẩm</h3>
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
	<div class="text-center">
		<img id="img-preview"
			src="${pageContext.request.contextPath}/images/${empty image ? 'no-image.jpg' : 'product/'.concat(image)}"
			width="150px" height="150px">
	</div>
	<f:form method="POST"
		action="${pageContext.request.contextPath}/admin/products/update"
		modelAttribute="data" enctype="multipart/form-data">
		<f:input type="hidden" name="id" path="id" />
		<div class="mb-3">
			<label class="form-label">Tên</label>
			<f:input type="text" name="name" class="form-control" path="name" />
			<f:errors path="name" element="div" cssClass="form-text text-danger"
				delimiter="<br>"></f:errors>
		</div>
		<div class="mb-3">
			<label class="form-label">Hình ảnh</label> <input type="file"
				id="file-input" name="image" accept="image/png, image/jpeg"
				class="form-control" />
		</div>
		<div class="mb-3">
			<label class="form-label">Giá</label>
			<f:input type="text" name="price" class="form-control" path="price" />
			<f:errors path="price" element="div" cssClass="form-text text-danger"
				delimiter="<br>"></f:errors>
		</div>
		<div class="mb-3">
			<label class="form-label">Giảm giá</label>
			<f:input type="text" name="discount" class="form-control"
				path="discount" />
			<f:errors path="discount" element="div"
				cssClass="form-text text-danger" delimiter="<br>"></f:errors>
		</div>
		<div class="mb-3">
			<label class="form-label">Số lượng</label>
			<f:input type="number" name="quantity" class="form-control"
				path="quantity" />
			<f:errors path="quantity" element="div"
				cssClass="form-text text-danger" delimiter="<br>"></f:errors>
		</div>
		<div class="mb-3">
			<label class="form-label">Danh mục</label>
			<f:select path="category" cssClass="form-select">
				<f:options items="${categories}" itemValue="id" itemLabel="name" />
			</f:select>
			<f:errors path="category" element="div"
				cssClass="form-text text-danger" delimiter="<br>"></f:errors>
		</div>
		<div class="mb-3">
			<label class="form-label">Trạng thái</label> <br>
			<div class="form-check form-check-inline">
				<f:radiobutton class="form-check-input" name="available" id="nam"
					value="1" path="available" />
				<label class="form-check-label" for="nam">Đang bán</label>
			</div>
			<div class="form-check form-check-inline">
				<f:radiobutton class="form-check-input" name="available" id="nu"
					value="0" path="available" />
				<label class="form-check-label" for="nu">Ngừng bán</label>
			</div>
			<br>
			<f:errors path="available" element="div"
				cssClass="form-text text-danger" delimiter="<br>"></f:errors>
		</div>
		<div class="mb-3">
			<f:label path="description" class="form-label">Mô
				tả</f:label>
			<f:textarea class="form-control" name="description" rows="3"
				path="description"></f:textarea>
		</div>

		<div class="d-grid gap-2">
			<button type="submit" class="btn btn-success">Cập nhật</button>
		</div>
	</f:form>
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