<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<c:set var="title" value="Danh sách danh mục" />
<c:set var="menuId" value="3" />
<%@include file="../_header.jsp"%>

<div class="card mb-2">
	<div class="card-body row">
		<div class="col-3">
			<form action="${pageContext.request.contextPath}/admin/categories"
				method="GET">
				<div class="input-group">
					<input class="form-control" type="search" name="keyword"
						placeholder="Tên danh mục?" value="${keyword}">
					<button class="btn btn-secondary">
						<i class="fa-solid fa-magnifying-glass"></i>
					</button>
				</div>
			</form>
		</div>
		<div class="col-9 d-flex">
			<div class="ms-auto">
				<a href="${pageContext.request.contextPath}/admin/categories/create"
					class="btn btn-success"> <i class="fa-solid fa-plus"></i> Thêm
				</a> <a
					href="${pageContext.request.contextPath}/admin/categories/export"
					class="btn btn-secondary"><i class="fa-solid fa-file-excel"></i>
					Xuất excel</a>
			</div>
		</div>
	</div>
</div>
<div class="card">
	<div class="card-body">
		<div class="table-responsive">
			<table class="table  table-bordered table-hover">
				<thead>
					<tr>
						<th scope="col">ID</th>
						<th scope="col">Tên danh mục</th>
						<th scope="col" width="250px">Hành động</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="category" items="${page.content}">
						<tr>
							<th scope="row">${category.id}</th>
							<td>${category.name}</td>
							<td>
								<div class="btn-group" role="group" aria-label="Basic example">
									<a
										href="${pageContext.request.contextPath}/admin/categories/update/${category.id}"
										class="btn btn-primary"> <i
										class="fa-solid fa-pen-to-square"></i> Sửa
									</a> <a
										href="${pageContext.request.contextPath}/admin/categories/delete/${category.id}"
										onclick="return confirm('Bạn muốn xoá sản phẩm này?');"
										class="btn btn-danger"> <i class="fa-solid fa-trash-can"></i>
										Xoá
									</a>
								</div>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="row">
			<ul class="pagination justify-content-center">
				<li class="page-item"><a
					href="${pageContext.request.contextPath}/admin/categories?keyword=${keyword}&p=1"
					class="page-link"> <i class="fa-solid fa-backward-step"></i>
				</a></li>
				<li class="page-item"><a
					href="${pageContext.request.contextPath}/admin/categories?keyword=${keyword}&p=${page.number}"
					class="page-link"> <i class="fa-solid fa-backward"></i>
				</a></li>
				<li class="page-item disabled">
					<button class="page-link">${page.number + 1}</button>
				</li>
				<li class="page-item"><a
					href="${pageContext.request.contextPath}/admin/categories?keyword=${keyword}&p=${page.number + 2}"
					class="page-link"> <i class="fa-solid fa-forward"></i>
				</a></li>
				<li class="page-item"><a
					href="${pageContext.request.contextPath}/admin/categories?keyword=${keyword}&p=${page.totalPages}"
					class="page-link"> <i class="fa-solid fa-forward-step"></i>
				</a></li>
			</ul>
		</div>
	</div>
</div>
<%@include file="../_footer.jsp"%>