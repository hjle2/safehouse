<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set value="${pageContext.request.contextPath }" var="root"></c:set>
<header>

	<script>
		// 에러메시지 출력
		var message = "${msg}";
		if (message !== "") {
			alert("${msg }");
		}
	</script>
	<nav class="navbar navbar-expand-sm bg-light navbar-fixed-top shadow">
		<div class="container-fluid d-flex">
			<a class="navbar-brand" href="${root}/"><strong>현정
					하우스</strong></a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link"
						href="${root}/news">뉴스</a></li>
				</ul>
			</div>
			<div class="d-flex justify-content-between">
				<div class="collapse navbar-collapse" id="navbarSupportedContent">
					<ul class="navbar-nav me-auto mb-2 mb-lg-0">
						<c:if test="${!empty userInfo}">
							<%@ include file="confirm.jsp"%>
						</c:if>
						<c:if test="${empty userInfo}">
							<li class="nav-item" id="login-list"><a class="nav-link"
								href="${root}/user/login">로그인</a></li>
							<li class="nav-item" id="register-list"><a class="nav-link"
								href="${root}/user/regist">회원가입</a></li>
						</c:if>
					</ul>

				</div>
			</div>
		</div>
	</nav>
</header>