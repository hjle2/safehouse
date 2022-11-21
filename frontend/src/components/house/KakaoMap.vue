<template>
    <div id="map">
    </div>
</template>

<script>
export default {
  name: "KakaoMap",
  props: {
    juso: String,
    houses: Array,
  },
  data() {
    return {
      coords: {},
      markers: [],
      infowindow: null,
    };
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=a5329ed71a6b0adbb1191e046f974b8a&libraries=services,clusterer";
      document.head.appendChild(script);
    }
  },
  watch: {
    juso() {
      this.panTo(this.map);
      this.coords = this.map.getCenter();
    },
    houses() {
      for (let marker of this.markers) {
        marker.setMap(null);
      }
      this.initMarker();
    }
  },
  methods: {
    initMap() {
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(33.450701, 126.570667),
        level: 5,
      };
      this.map = new kakao.maps.Map(container, options);
      this.panTo(this.map);
      
      this.initMarker();
      // this.initClusterer();
    },
    initClusterer() {
      this.clusterer = new kakao.maps.MarkerClusterer({
        map: this.map,
        markers: this.markers,
        gridSize: 35,
        averageCenter: true,
        minLevel: 6,
        disableClickZoom: true,
        styles: [{
            width : '53px', height : '52px',
            background: 'url(cluster.png) no-repeat',
            color: '#fff',
            textAlign: 'center',
            lineHeight: '54px'
        }]
      });
    },
    initMarker() {
      let arr = new Array();
      for (let house of this.houses) {
        arr.push({
          title: house.aptName,
          latlng: new kakao.maps.LatLng(house.lat, house.lng),
        });
      }
      this.markers = new Array();
      for (let pt of arr) {
        // 마커를 생성합니다
        var marker = new kakao.maps.Marker({
          map: this.map, // 마커를 표시할 지도
          position: pt.latlng, // 마커를 표시할 위치
          title : pt.title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
          clickable: true
        });
        // 인포윈도우로 장소에 대한 설명을 표시합니다
        var infowindow = new kakao.maps.InfoWindow({
          position : pt.latlng,
          content: '<div style="width:200px;text-align:center;padding:6px 0;">'+pt.title+'</div>',
        });
        
        // 마커에 클릭이벤트를 등록합니다
        kakao.maps.event.addListener(marker, 'mouseover', this.makeOverListener(this.map, marker, infowindow) );
        kakao.maps.event.addListener(marker, 'mouseout', this.makeOutListener(infowindow));

        this.markers.push(marker);
      }
    },
    makeOverListener(map, marker, infowindow) {
          return function() {
              infowindow.open(map, marker);
          };
    },
    makeOutListener(infowindow) {
          return function() {
              infowindow.close();
          };
    },
    panTo(map) {
      // 주소-좌표 변환 객체를 생성합니다
      var geocoder = new kakao.maps.services.Geocoder();
      // 주소로 좌표를 검색합니다
      var callback = function (result, status) {
        // 정상적으로 검색이 완료됐으면 
        if (status === kakao.maps.services.Status.OK) {
          map.panTo(new kakao.maps.LatLng(result[0].y, result[0].x));
        }
      };
      geocoder.addressSearch(this.juso, callback);
    },
    changeSize(size) {
      const container = document.getElementById("map");
      container.style.width = `${size}px`;
      container.style.height = `${size}px`;
      this.map.relayout();
    },
    displayMarker(markerPositions) {
      if (this.markers.length > 0) {
        this.markers.forEach((marker) => marker.setMap(null));
      }

      const positions = markerPositions.map(
        (position) => new kakao.maps.LatLng(...position)
      );

      if (positions.length > 0) {
        this.markers = positions.map(
          (position) =>
            new kakao.maps.Marker({
              map: this.map,
              position,
            })
        );

        const bounds = positions.reduce(
          (bounds, latlng) => bounds.extend(latlng),
          new kakao.maps.LatLngBounds()
        );

        this.map.setBounds(bounds);
      }
    },
    displayInfoWindow() {
      if (this.infowindow && this.infowindow.getMap()) {
        //이미 생성한 인포윈도우가 있기 때문에 지도 중심좌표를 인포윈도우 좌표로 이동시킨다.
        this.map.setCenter(this.infowindow.getPosition());
        return;
      }

      var iwContent = '<div style="padding:5px;">Hello World!</div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
        iwPosition = new kakao.maps.LatLng(33.450701, 126.570667), //인포윈도우 표시 위치입니다
        iwRemoveable = true; // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다

      this.infowindow = new kakao.maps.InfoWindow({
        map: this.map, // 인포윈도우가 표시될 지도
        position: iwPosition,
        content: iwContent,
        removable: iwRemoveable,
      });

      this.map.setCenter(iwPosition);
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
#map {
  width: 100%;
  height: 100%;
  min-height: 500px;
}

.button-group {
  margin: 10px 0px;
}

button {
  margin: 0 3px;
}
</style>
