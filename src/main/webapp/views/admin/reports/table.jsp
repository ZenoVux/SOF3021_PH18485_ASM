<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="title" value="Thống kê" />
<c:set var="menuId" value="5" />
<%@include file="../_header.jsp"%>
<div class="card mb-2">
	<div class="card-body row">
		<div class="col-12 d-flex">
			<div class="ms-auto">
				<a href="${pageContext.request.contextPath}/admin/reports/export"
					class="btn btn-secondary"><i class="fa-solid fa-file-excel"></i>
					Xuất excel</a>
			</div>
		</div>
	</div>
</div>
<div class="card">
	<div class="card-header">Thống kê doanh thu theo loại</div>
	<div class="card-body">
		<div class="table-responsive">
			<table class="table  table-bordered table-hover">
				<thead>
					<tr>
						<th scope="col">Loại hàng</th>
						<th scope="col">Doanh thu</th>
						<th scope="col">Số lượng</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="report" items="${reports}">
						<tr>
							<th scope="row">${report.loai.name}</th>
							<td><fmt:formatNumber type="number"
									pattern="###,###,### VNĐ" value="${report.doanhThu}" /></td>
							<td>${report.soLuong}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>
<%@include file="../_footer.jsp"%>