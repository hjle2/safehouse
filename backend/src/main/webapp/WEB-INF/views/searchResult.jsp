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

<link rel="stylesheet" href="/css/style.css" />
</head>
<body>
	<%@ include file="include/header.jsp"%>

	<!-- main -->
	<main class="d-flex justify-content-center align-items-center">
	<div class="bg-light p-5 rounded container-xl my-5">
		<div id="resultname">
			<h2>${juso}</h2>
		</div>
		<hr />
		<div class="container-fluid">
			<!-- <div class="row">
            <div class="col-md-1"></div>
          </div> -->
			<div class="row">
				<!-- <div class="col-md-1"></div> -->
				<div class="col-md-5 col-xxl-4 p-0 border">
					<h3 class="bg-warning p-2">거래정보</h3>
					<div id="content" class="p-2">
						<!-- 거래정보  -->
						${msg}
						<%-- <c:forEach items="${deals }" var="deal">
							<div id="deal-container" class="border p-2 mb-1 rounded" onclick="location.href='${root}/house/dealdetail?aptCode=${deal.aptCode}&aptName=${deal.aptName}'">
								<div class="d-flex justify-content-between">
									<div>
										<h5>아파트명 : ${deal.aptName }</h5>
									</div>
									<div> 
										<h5>
											<span class="badge bg-secondary">${deal.cnt }</span>
										</h5>
									</div>
								</div>
								<h6>평균 거래액 : ${deal.dealAmount }</h6>
							</div>
						</c:forEach> --%>
					</div>
				</div>
				<div class="col-md-7 col-xxl-8">
					<div>
						<div id="map" style="width: 100%; height: 800px;"></div>
					</div>
				</div>
				<!-- <div class="col-md-1"></div> -->
			</div>
		</div>
	</div>
	</main>

	<%@ include file="include/footer.jsp"%>

	<script type="text/javascript"
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=9468323e85c9e87b8333f98d478af0bc&libraries=services"></script>
	<script>
	
    
    var arr = new Array();
	  <c:forEach items="${deals}" var="deal">
	  	arr.push({
	  		title : "${deal.aptName}",
            latlng: new kakao.maps.LatLng(${deal.lat}, ${deal.lng})
	  	});
	  </c:forEach>
	  	
      var mapContainer = document.getElementById("map"), // 지도를 표시할 div
        mapOption = {
          center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
          level: 3, // 지도의 확대 레벨
        };

      // 지도를 생성합니다
      var map = new kakao.maps.Map(mapContainer, mapOption);

      // 주소-좌표 변환 객체를 생성합니다
      var geocoder = new kakao.maps.services.Geocoder();
      geocoder.addressSearch("${juso}", function(result, status) {

          // 정상적으로 검색이 완료됐으면 
          if (status === kakao.maps.services.Status.OK) {
              var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

              // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
              map.setCenter(coords);
          } 
      }); 

      
   	  // 마커를 표시할 위치와 title 객체 배열입니다 
      var positions = arr;
    
      for (var i = 0; i < positions.length; i ++) {
    	    
    	    // 마커를 생성합니다
    	    var marker = new kakao.maps.Marker({
    	        map: map, // 마커를 표시할 지도
    	        position: positions[i].latlng, // 마커를 표시할 위치
    	        title : positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
    	        clickable: true
    	    });
		    // 인포윈도우로 장소에 대한 설명을 표시합니다
		    var infowindow = new kakao.maps.InfoWindow({
		        position : positions[i].latlng, 
		        content: '<div style="width:200px;text-align:center;padding:6px 0;">'+positions[i].title+'</div>',
		    });
		    
		    // 마커에 클릭이벤트를 등록합니다
		    kakao.maps.event.addListener(marker, 'mouseover', makeOverListener(map, marker, infowindow) );
		    kakao.maps.event.addListener(marker, 'mouseout', makeOutListener(infowindow));
		    

   	  }
      
      // 인포윈도우를 표시하는 클로저를 만드는 함수입니다 
      function makeOverListener(map, marker, infowindow) {
          return function() {
              infowindow.open(map, marker);
          };
      }
      
      // 인포윈도우를 닫는 클로저를 만드는 함수입니다 
      function makeOutListener(infowindow) {
          return function() {
              infowindow.close();
          };
      }
      
    </script>
</body>
</html>