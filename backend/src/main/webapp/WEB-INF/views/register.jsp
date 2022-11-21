<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>현장하우스-회원가입</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css
    " />
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js
    "></script>
<link rel="stylesheet" href="/css/style.css" />
<link rel="stylesheet" href="/css/loginstyle.css">
</head>
<body>
	<%@ include file="include/header.jsp"%>

	<!-- main -->
	<main class="d-flex justify-content-center align-items-center">
	<!-- 회원 가입 부분  start-->
	<div id="main"
		class="container-xl p-5 my-5 text-white text-center rounded">
		<h3 class="p-1">회원가입</h3>
		<hr class="mb-5" />
		<div class="">
			<form id="regist-form" action="${root }/user/regist" method="post">
				<input type="hidden" name="act" value="regist">
				<div class="input-group mb-1 input-group-lg">
					<span class="input-group-text w-25">아이디</span> <input type="text"
						name="id" id="id" class="form-control" placeholder="특수문자 불가"
						required>
				</div>
				<div class="input-group mb-1 input-group-lg">
					<span class="input-group-text w-25">비밀번호</span> <input
						type="password" name="pwd" id="pwd" class="form-control"
						placeholder="영문 숫자 포함 6자리 이상" required>
				</div>
				<div class="input-group mb-1 input-group-lg">
					<span class="input-group-text w-25">이름</span> <input type="text"
						name="name" id="name" class="form-control" placeholder="실명으로 작성"
						required>
				</div>
				<div class="input-group mb-1 input-group-lg">
					<span class="input-group-text w-25">주소</span> <input type="text"
						name="addr" id="address" class="form-control" placeholder="실거주지"
						required>
				</div>
				<div class="input-group mb-1 input-group-lg">
					<span class="input-group-text w-25">전화번호</span> <input type="text"
						name="tel" id="tel" class="form-control"
						placeholder="010-XXXX-XXXX 형식" required>
				</div>

				<button type="submit" id="submit-btn"
					class="btn btn-warning btn-lg w-25 mt-5">등록</button>
			</form>
		</div>

	</div>
	<!-- 회원 가입 부분  end--> </main>

	<%@ include file="include/footer.jsp"%>

</body>
</html>
