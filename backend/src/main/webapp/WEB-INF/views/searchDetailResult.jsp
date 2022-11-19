<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>현장하우스-검색결과</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css
    " />
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js
    "></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js">
	</script>
<link rel="stylesheet" href="/css/style.css" />
</head>
<body>
	<%@ include file="include/header.jsp"%>

	<!-- main -->
	<main class="d-flex justify-content-center align-items-center">
	<div class="bg-light p-5 rounded container-xl my-5">
		<div id="resultname">
			<h2>거래 상세 정보</h2>
		</div>
		<hr />
		<div class="container-fluid">
			<!-- <div class="row">
            <div class="col-md-1"></div>
          </div> -->
			<div class="row">
				<!-- <div class="col-md-1"></div> -->
				<div class="col-md-5 col-xxl-4 p-0 border">
					<h3 class="bg-warning p-2">[${aptName}] 거래 정보</h3>
					<div id="content" class="p-2">
						<!-- 거래정보  -->
						${msg}
						<c:forEach items="${details }" var="detail">
							<div id="deal-container" class="link-none border p-2 mb-1 rounded">
								<h6 class="text-muted">${detail.dealYear }/${detail.dealMonth }/${detail.dealDay }</h6>
								<h6>전용면적 : ${detail.area }</h6>
								<h6>층수 : ${detail.floor } </h6>
								<h6>거래액 : ${detail.dealAmount }</h6>
							</div>
						</c:forEach>
					</div>
				</div>
				<div class="col-md-7 col-xxl-8">
					<div>
						<canvas id="myChart" style="width:100%;max-width:700px"></canvas>
					</div>
				</div>
				<!-- <div class="col-md-1"></div> -->
			</div>
		</div>
	</div>

	</main>

	<%@ include file="include/footer.jsp"%>

	<script>
	var xValues = [];
	var yValues = [];
	var barColors = [];
	
	<c:forEach items="${details }" var="detail">
		xValues.push("${detail.dealYear }/${detail.dealMonth }/${detail.dealDay }");
		yValues.push("${detail.dealAmount}".replace(",", ""));
		barColors.push("orange");
	</c:forEach>

	
	var myChart = new Chart("myChart", {
		  type: "bar",
		  data: {
		    labels: xValues,
		    datasets: [{
		      backgroundColor: barColors,
		      data: yValues
		    }]
		  },
		  options: {
		    legend: {display: false},
		    title: {
		      display: true,
		      text: "[${aptName}] 거래 정보"
		    }
		  }
	});
    </script>
</body>
</html>