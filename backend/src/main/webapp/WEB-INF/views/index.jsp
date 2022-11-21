<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>해피 하우스</title>
    <link
      rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css
    "
    />
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js
    "></script>
    <link rel="stylesheet" href="/css/style.css" />
  </head>
  <body>
	<%@ include file="include/header.jsp" %>
	
    <!-- main -->
    <main class="d-flex justify-content-center align-items-center">
      <div class="bg-light p-5 rounded container-xl">
        <div class="mb-5 text-center">
          <h1>모든 실거래 현황은 <mark>해피 하우스</mark>에서!!</h1>
        </div>
        <div class="text-center">
          <form action="house/deallist" method="post">
            <div>
              <table class="mx-auto">
                <tbody>
                  <tr>
                    <td>
                      <select class="form-select d-inline-block" id="sido-select">
                        <option value="" selected disabled hidden>시/도</option>
                      </select>
                    </td>
                    <td>
                      <select class="form-select d-inline-block" id="gu-select" required>
                        <option value="" selected disabled hidden>시/군/구</option>
                      </select>
                    </td>
                    <td>
                      <select class="form-select" name="juso" id="dong-select" required>
                        <option value="" selected disabled hidden>동</option>
                      </select>
                    </td>
                  </tr>
                </tbody>
              </table>
            </div>
            <input type="text" name="dongcode" id="codeInput" hidden/>
            <input class="btn btn-md btn-warning w-25 mt-3" type="submit" value="검색" />
          </form>
        </div>
      </div>
    </main>

	<%@ include file="include/footer.jsp" %>

  </body>
  <script src="/js/index.js"></script>
  </html>
