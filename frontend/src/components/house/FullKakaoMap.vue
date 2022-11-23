<template>
<div class="map_wrap" style="height:100%">
<div id="kakaomap" style="height:100%;overflow:hidden;"></div>
    <b-button v-b-toggle.sidebar-right id="menu_btn">메뉴 보이기</b-button>
    <b-sidebar id="sidebar-right" title="메뉴" right shadow>
        <div class="text-center">
  <table class="mx-auto">
    <tbody>
      <tr>
        <td>
        <b-form-select v-model="sidoCode" :options="sidos" @change="gugunList"></b-form-select>
        </td>
        <td>
          <b-form-select v-model="gugunCode" :options="guguns" @change="dongList"></b-form-select>
        </td>
      </tr>
    </tbody>
  </table>
    <input class="btn btn-md btn-warning w-25 mt-3" @click="searchApt" value="검색" />

	<div id="result">
		<div id="resultname">
				<h3>{{juso}}</h3>
		</div>
     <hr/>
    <b-container v-if="houses && houses.length != 0" style="overflow-y:scroll;">
            <h3 class="bg-warning p-2">거래정보</h3>
                <house-list-item v-for="(house, index) in houses" :key="index" :house="house"></house-list-item>
    </b-container>
    <b-container v-else class="text-center">
        <b-col><b-alert show>주택 목록이 없습니다.</b-alert></b-col>
    </b-container>
    </div>
    </div>
    </b-sidebar>
</div>
</template>

<script>
import HouseListItem from "@/components/house/HouseListItem";
import { mapState, mapActions, mapMutations } from "vuex";
export default {
    name: "FullKakaoMap",
    components: {
        HouseListItem,
    },
    data() {
        return {
            sidoCode: null,
            gugunCode: null,
            dongCode: null,
        };
    },
    created() {
        this.CLEAR_SIDO_LIST();
        this.CLEAR_GUGUN_LIST();
        this.CLEAR_DONG_LIST();
        this.CLEAR_APT_LIST();
        this.getSido();
        this.getPoliceStations();
    },
    computed: {
        ...mapState(["sidos", "guguns", "dongs", "houses", "polices", "juso"]),
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
            this.panTo();
            // 지우기
            this.clusterer.clear();
        },
        houses() {
            // 표시
            this.initMarker();
        }
    },
    methods: {
        ...mapActions(["getSido", "getGugun", "getDong", "getHouseListSi", "getHouseListGun", "getHouseListDong", "getPoliceStations"]),
        ...mapMutations(["CLEAR_SIDO_LIST", "CLEAR_GUGUN_LIST", "CLEAR_DONG_LIST", "CLEAR_APT_LIST", "SET_JUSO"]),

        dongList() {
        this.CLEAR_DONG_LIST();
        this.dongCode = null;
        if (this.gugunCode) this.getDong(this.gugunCode.code);
        },
        gugunList() {
        this.CLEAR_GUGUN_LIST();
        this.gugunCode = null;
        if (this.sidoCode) this.getGugun(this.sidoCode.code);
        },
        searchApt() {
        if (this.sidoCode == null)
            return;

        let juso = "";
        
        if (this.sidoCode != null) {
            juso += this.sidoCode.juso;
        }
        if (this.gugunCode != null) {
            juso += " " + this.gugunCode.juso;
        }
        if (this.dongCode != null) {
            juso += " " + this.dongCode.juso;
        }
        this.SET_JUSO(juso);
        
        if (this.gugunCode == null) {
            alert('주소를 선택해주세요.');
            // this.getHouseListSi(this.sidoCode.code);
        } 
        else if (this.dongCode == null) {
            this.getHouseListGun(this.gugunCode.code);
        } 
        else {
            this.getHouseListDong(this.dongCode.code);
        }

        },
        initMap() {
            const container = document.getElementById("kakaomap");
            const options = {
                center: new kakao.maps.LatLng(37.5013068, 127.0396597),
                level: 5,
            };
            this.map = new kakao.maps.Map(container, options);
            this.ps = new kakao.maps.services.Places(this.map); 

            this.panTo(this.map);
            
            this.initClusterer();
            this.initMarker();
            this.initPoliceStations();
        },
        async getLatLng(addr) {
            // 주소-좌표 변환 객체를 생성합니다
            let geocoder = new kakao.maps.services.Geocoder();

            return await new Promise((resolve) => {
                geocoder.addressSearch(addr, function (result, status) {
                // 정상적으로 검색이 완료됐으면
                if (status === kakao.maps.services.Status.OK) {
                    resolve(new kakao.maps.LatLng(result[0].y, result[0].x));
                }
                });
            });
        },
        async initPoliceStations() {
            for (let police of this.polices) {
                let fillcolor = '#CFE7FF';
                let strokecolor = '#75B8FA';

                let center;
                let text = police.policestation + ' 치안 등급 : [';

                if (police.lat === 0 && police.lng === 0) {
                    center = await this.getLatLng('서울특별시 중구 동호로 5길 15 약수지구대');
                } 
                else {
                    center = new kakao.maps.LatLng(police.lat, police.lng);
                }

                if (police.grade === 2) {
                    fillcolor = '#82e989';
                    strokecolor = '#2dc937';
                    text += '보통';
                }
                else if (police.grade === 3) {
                    fillcolor = '#ffbe47';
                    strokecolor = '#ee9a00';
                    text += '주의';
                }
                else if (police.grade === 4) {
                    fillcolor = '#ffa288';
                    strokecolor = '#ff5829';
                    text += '위험';
                }
                else {
                    text += '안전';
                }

                text += ']';

                let circle = new kakao.maps.Circle({
                    radius: 1500, // 미터 단위의 원의 반지름입니다 
                    strokeWeight: 1, // 선의 두께입니다 
                    strokeColor: strokecolor, // 선의 색깔입니다
                    strokeOpacity: 0.7, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
                    strokeStyle: 'solid', // 선의 스타일 입니다
                    fillColor: fillcolor, // 채우기 색깔입니다
                    fillOpacity: 0.4,  // 채우기 불투명도 입니다
                    center: center,
                });
                circle.setMap(this.map);

                
                // 인포윈도우로 장소에 대한 설명을 표시합니다
                let infowindow = new kakao.maps.InfoWindow({
                    position : center,
                    content: '<div style="width:200px;text-align:center;padding:6px 0;">'+text+'</div>',
                });
                
                // 마커에 클릭이벤트를 등록합니다
                kakao.maps.event.addListener(circle, 'mouseover', this.mouseOverListener(this.map, infowindow));
                kakao.maps.event.addListener(circle, 'mouseout', this.mouseOutListener(infowindow));
            }
        },
    initClusterer() {
      // 마커 클러스터러를 생성합니다 
      this.clusterer = new kakao.maps.MarkerClusterer({
          map: this.map, // 마커들을 클러스터로 관리하고 표시할 지도 객체 
          averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정 
          minLevel: 6 // 클러스터 할 최소 지도 레벨 
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
        let infowindow = new kakao.maps.InfoWindow({
          position : pt.latlng,
          content: '<div style="width:200px;text-align:center;padding:6px 0;">'+pt.title+'</div>',
        });
        
        // 마커에 클릭이벤트를 등록합니다
        kakao.maps.event.addListener(marker, 'mouseover', this.mouseOverListener(this.map, infowindow) );
        kakao.maps.event.addListener(marker, 'mouseout', this.mouseOutListener(infowindow));
        
        this.markers.push(marker);
      }
      this.clusterer.addMarkers(this.markers);
    },
    mouseOverListener(map, infowindow) {
          return function() {
              infowindow.open(map);
          };
    },
    mouseOutListener(infowindow) {
          return function() {
              infowindow.close();
          };
    },
    async panTo() {
        let latlng = await this.getLatLng(this.juso);
        this.map.panTo(latlng);
    },
    },
}
</script>

<style>
#menu_btn {
position:absolute;top:0;left:0;margin:10px 0 30px 10px;background:rgba(255, 255, 255, 0.7);color: black; z-index: 1;border-radius: 10px;
}
.map_wrap, .map_wrap * {margin:0;padding:0;font-family:'Malgun Gothic',dotum,'돋움',sans-serif;font-size:12px;}
.map_wrap a, .map_wrap a:hover, .map_wrap a:active{color:#000;text-decoration: none;}
.map_wrap {position:relative; width:100%;height:100%;}
#menu_wrap {position:absolute;top:0;left:0;bottom:0;width:250px;margin:10px 0 30px 10px;padding:5px;overflow-y:auto;background:rgba(255, 255, 255, 0.7);z-index: 1;font-size:12px;border-radius: 10px;}
.bg_white {background:#fff;}
#menu_wrap hr {display: block; height: 1px;border: 0; border-top: 2px solid #5F5F5F;margin:3px 0;}
#menu_wrap .option{text-align: center;}
#menu_wrap .option p {margin:10px 0;}  
#menu_wrap .option button {margin-left:5px;}
#placesList li {list-style: none;}
#placesList .item {position:relative;border-bottom:1px solid #888;overflow: hidden;cursor: pointer;min-height: 65px;}
#placesList .item span {display: block;margin-top:4px;}
#placesList .item h5, #placesList .item .info {text-overflow: ellipsis;overflow: hidden;white-space: nowrap;}
#placesList .item .info{padding:10px 0 10px 55px;}
#placesList .info .gray {color:#8a8a8a;}
#placesList .info .jibun {padding-left:26px;background:url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_jibun.png) no-repeat;}
#placesList .info .tel {color:#009900;}
#placesList .item .markerbg {float:left;position:absolute;width:36px; height:37px;margin:10px 0 0 10px;background:url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png) no-repeat;}
#placesList .item .marker_1 {background-position: 0 -10px;}
#placesList .item .marker_2 {background-position: 0 -56px;}
#placesList .item .marker_3 {background-position: 0 -102px}
#placesList .item .marker_4 {background-position: 0 -148px;}
#placesList .item .marker_5 {background-position: 0 -194px;}
#placesList .item .marker_6 {background-position: 0 -240px;}
#placesList .item .marker_7 {background-position: 0 -286px;}
#placesList .item .marker_8 {background-position: 0 -332px;}
#placesList .item .marker_9 {background-position: 0 -378px;}
#placesList .item .marker_10 {background-position: 0 -423px;}
#placesList .item .marker_11 {background-position: 0 -470px;}
#placesList .item .marker_12 {background-position: 0 -516px;}
#placesList .item .marker_13 {background-position: 0 -562px;}
#placesList .item .marker_14 {background-position: 0 -608px;}
#placesList .item .marker_15 {background-position: 0 -654px;}
#pagination {margin:10px auto;text-align: center;}
#pagination a {display:inline-block;margin-right:10px;}
#pagination .on {font-weight: bold; cursor: default;color:#777;}
</style>