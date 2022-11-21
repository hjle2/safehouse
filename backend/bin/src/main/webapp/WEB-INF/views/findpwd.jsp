<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>현장하우스-로그인</title>
    <link
      rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css
    "
    />
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js
    "></script>
    <link rel="stylesheet" href="/css/style.css" />
    <!-- 로그인 스타일  -->
    <link rel="stylesheet" href="/css/loginstyle.css">
  </head>
  <body>
	<%@ include file="include/header.jsp" %>

    <!-- main -->
    <main class="d-flex justify-content-center align-items-center">
      <!-- 로그인 부분  start-->
      <div id="main" class="container-xl p-5 my-5 text-white text-center rounded">
        <div class="row align-items-center">
          <h3 class="p-1">비밀번호 찾기</h3>
          <hr class="mb-5"/>
          <form id="login-form" action="${root }/user/findpwd" method="post">
          <input type="hidden" name="act" value="findpwd">
            <div class="input-group mb-1 input-group-lg">
              <span class="input-group-text w-25">아이디</span>
              <input type="text" name="id" id="id" class="form-control">
            </div>
            <div class="input-group mb-1 input-group-lg">
              <span class="input-group-text w-25">전화번호</span>
              <input type="tel" name="tel" id="tel" placeholder="010-XXXX-XXXX" class="form-control">
            </div>
            <button id="submit-btn" type="submit" class="btn btn-warning btn-lg w-25 mt-5">비밀번호 찾기</button>
          </form>
        </div>
      </div>
      <!-- 로그인 부분  end-->

    </main>

	<%@ include file="include/footer.jsp" %>

  </body>
</html>

