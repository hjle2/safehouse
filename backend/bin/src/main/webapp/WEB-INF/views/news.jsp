<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>현장하우스-뉴스</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css
    " />
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js
    "></script>
<link rel="stylesheet" href="/css/style.css" />
</head>
<body>
	<%@ include file="include/header.jsp"%>

	<!-- main -->
	<main class="d-flex justify-content-center align-items-center">
		<div class="bg-light p-5 rounded container-xl my-5">
			<h2>부동산 NEWS</h2>
			<hr />
			<table class="table">
				<thead>
					<tr>
						<th>제목</th>
						<th>링크</th>
					</tr>
				</thead>
				<tbody id="table-body">

				</tbody>
			</table>
			<div class="px-5 mx-5 d-flex justify-content-between">
				<div>
					<a class="link-none">1</a>
				</div>
				<div>
					<a class="link-none">2</a>
				</div>
				<div>
					<a class="link-none">3</a>
				</div>
				<div>
					<a class="link-none">4</a>
				</div>
				<div>
					<a class="link-none">5</a>
				</div>
				<div>
					<a class="link-none">6</a>
				</div>
				<div>
					<a class="link-none">7</a>
				</div>
				<div>
					<a class="link-none">8</a>
				</div>
				<div>
					<a class="link-none">9</a>
				</div>
				<div>
					<a class="link-none">10</a>
				</div>
			</div>
		</div>
	</main>

	<%@ include file="include/footer.jsp"%>

</body>
<script>


  fetch("${root}/news/1")
    .then(response => response.json())
    .then(data => makeList(data));
  
  function makeList(newss) {
    	let table = ``;
    
    	newss.forEach(function(news) {
		      		table += `<tr>
		        	<td>\${news.title}</td>
		        	<td><a href=\${news.link}>바로가기</a></td>
		        	</tr>`;
		      	     document.querySelector("#table-body").innerHTML = table;
		          });
  }
  
  let links = document.querySelectorAll(".link-none");

  links.forEach(function (link) {
      link.addEventListener("click", function () {
    	  let page = link.textContent;
	      fetch("${root}/news/" + page)
		    .then(response => response.json())
		    .then(data => makeList(data));
      });
    });

  </script>

</html>