<template>
<div class="map_wrap" style="height:100%">
<div id="rvWrapper"  class="view_map" style="height:100%;overflow:hidden;"></div>
<div id="mapWrapper" class="view_roadview" style="height:100%;overflow:hidden;"></div>
<div id="over_map" class="custom_typecontrol">
    <span class="btn" v-b-toggle.sidebar-right>MENU</span>

    <span id="roadviewControl" @click="setRoadviewRoad"></span>
</div>
    <b-sidebar id="sidebar-right" right shadow>
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
				<h4 style="margin:10px">{{juso}}</h4>
		</div>
     <hr/>
    <div v-if="houses && houses.length != 0">
            <h4 class="bg-warning p-2">거래정보</h4>
                <house-list-item v-for="(house, index) in houses" :key="index" :house="house"></house-list-item>
    </div>
    <div v-else class="text-center" style="margin:10px">
        <b-col><b-alert show>주택 목록이 없습니다.</b-alert></b-col>
    </div>
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
            isShow: false,
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
            const container = document.getElementById("mapWrapper");
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
            this.initRoadView();
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
    initRoadView() {
        let rvWrapper = document.getElementById('rvWrapper');
        let rv = new kakao.maps.Roadview(rvWrapper);
        let rvClient = new kakao.maps.RoadviewClient(); //좌표로부터 로드뷰 파노ID를 가져올 로드뷰 helper객체
        // 지도와 로드뷰 위에 마커로 표시할 특정 장소의 좌표입니다
        let center = this.map.getCenter();
        // 로드뷰의 위치를 특정 장소를 포함하는 파노라마 ID로 설정합니다
        // 로드뷰의 파노라마 ID는 Wizard를 사용하면 쉽게 얻을수 있습니다 

        // 마커 이미지를 생성합니다.
        var markImage = new kakao.maps.MarkerImage(
            'https://t1.daumcdn.net/localimg/localimages/07/2018/pc/roadview_minimap_wk_2018.png',
            new kakao.maps.Size(26, 46),
            {
                // 스프라이트 이미지를 사용합니다.
                // 스프라이트 이미지 전체의 크기를 지정하고
                spriteSize: new kakao.maps.Size(1666, 168),
                // 사용하고 싶은 영역의 좌상단 좌표를 입력합니다.
                // background-position으로 지정하는 값이며 부호는 반대입니다.
                spriteOrigin: new kakao.maps.Point(705, 114),
                offset: new kakao.maps.Point(13, 46)
            }
        );
        // 드래그가 가능한 마커를 생성합니다.
        var rvMarker = new kakao.maps.Marker({
            image : markImage,
            position: center,
            draggable: true,
            map: null,
        });

        let toggleRoadview = this.toggleRoadview;
        //마커에 dragend 이벤트를 할당합니다
        kakao.maps.event.addListener(rvMarker, 'dragend', function() {
            var position = rvMarker.getPosition(); //현재 마커가 놓인 자리의 좌표
            toggleRoadview(rvClient, rv, position); //로드뷰를 토글합니다
        });

        //지도에 클릭 이벤트를 할당합니다
        kakao.maps.event.addListener(this.map, 'click', function(mouseEvent){
            
            // 현재 클릭한 부분의 좌표를 리턴 
            var position = mouseEvent.latLng; 

            rvMarker.setPosition(position);
            toggleRoadview(rvClient, rv, position); //로드뷰를 토글합니다
        });
        let map = this.map;
        // 로드뷰내의 화살표나 점프를 하였을 경우 발생한다.
        // position값이 바뀔 때마다 map walker의 상태를 변경해 준다.
        kakao.maps.event.addListener(rv, 'position_changed', function(){
            // 이벤트가 발생할 때마다 로드뷰의 position값을 읽어, map walker에 반영 
            var position = rv.getPosition();
            rvMarker.setPosition(position);
            map.setCenter(position);

        });
        this.rvMarker = rvMarker;
        this.rv = rv;
        this.rvClient = rvClient;
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
        kakao.maps.event.addListener(marker, 'mouseover', this.mouseMarkerOverListener(this.map, marker, infowindow) );
        kakao.maps.event.addListener(marker, 'mouseout', this.mouseOutListener(infowindow));
        
        this.markers.push(marker);
      }
      this.clusterer.addMarkers(this.markers);
    },
    mvRoadView(rvClient, center) {
        let rv = this.rv;
        rvClient.getNearestPanoId(center, function (panoId) {
            rv.setPanoId(panoId, center); //panoId와 중심좌표를 통해 로드뷰 실행
        });
        
    },
    mouseOverListener(map, infowindow) {
          return function() {
              infowindow.open(map);
          };
    },
    mouseMarkerOverListener(map, marker, infowindow) {
          return function() {
              infowindow.open(map, marker);
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
    setRoadviewRoad() {
        var control = document.getElementById('roadviewControl');

        // 버튼이 눌린 상태가 아니면
        if (control.className.indexOf('active') === -1) {
            control.className = 'active';

            // 로드뷰 도로 오버레이가 보이게 합니다
            this.toggleOverlay(true);
        } else {
            document.getElementById("mapWrapper").className = "view_roadview";
            document.getElementById("rvWrapper").className = "view_map";
            control.className = '';

            // 로드뷰 도로 오버레이를 제거합니다
            this.toggleOverlay(false);
        }

        // if (this.isShow) {
        //     document.getElementById("mapWrapper").className = "view_map";
        //     document.getElementById("rvWrapper").className = "view_roadview";
        // }
        // else {
        //     document.getElementById("mapWrapper").className = "view_roadview";
        //     document.getElementById("rvWrapper").className = "view_map";
        // }
        // this.isShow = !this.isShow;
    },//로드뷰 toggle함수
    toggleRoadview(rvClient, rv, position){

        //전달받은 좌표(position)에 가까운 로드뷰의 panoId를 추출하여 로드뷰를 띄웁니다
        rvClient.getNearestPanoId(position, 50, function(panoId) {
            if (panoId !== null) {
                rv.setPanoId(panoId, position); //panoId를 통한 로드뷰 실행
                document.getElementById("mapWrapper").className = "view_map";
                document.getElementById("rvWrapper").className = "view_roadview";
            }
        });
    },
    toggleOverlay(active) {
        let map = this.map;
        let marker = this.rvMarker;
        
        if (active) {
            // overlayOn = true;

            // 지도 위에 로드뷰 도로 오버레이를 추가합니다
            map.addOverlayMapTypeId(kakao.maps.MapTypeId.ROADVIEW);

            // 지도 위에 마커를 표시합니다
            marker.setMap(map);
            // 마커의 위치를 지도 중심으로 설정합니다 
            marker.setPosition(map.getCenter());

            // 로드뷰의 위치를 지도 중심으로 설정합니다
            this.toggleRoadview(this.rvClient, this.rv, map.getCenter());
        } else {
            // overlayOn = false;

            // 지도 위의 로드뷰 도로 오버레이를 제거합니다
            map.removeOverlayMapTypeId(kakao.maps.MapTypeId.ROADVIEW);

            // 지도 위의 마커를 제거합니다
            marker.setMap(null);
        }
    }
    },
}
</script>

<style>
.view_map  {
    z-index: 10;
    display: none;
}
.view_roadview  {
    z-index: 0;
}
#menuControl {
    background: url(https://t1.daumcdn.net/localimg/localimages/07/2018/pc/common/img_search.png) -230px -400px no-repeat;
    width:55px;height:42px;
}

#over_map {
    display: flex;
    position:absolute;top:5px;left:5px;z-index: 1;border-radius: 10px;
}
#over_map * {
    border: 0px; color: black; 
    margin: 2px;
}
#roadviewControl {width:42px;height:42px;cursor: pointer; background: url(https://t1.daumcdn.net/localimg/localimages/07/2018/pc/common/img_search.png) 0 -450px no-repeat;}
#roadviewControl.active {background-position:0 -350px;}
.map_wrap, .map_wrap * {margin:0;padding:0;font-family:'Malgun Gothic',dotum,'돋움',sans-serif;}
.map_wrap a, .map_wrap a:hover, .map_wrap a:active{color:#000;text-decoration: none;}
.map_wrap {position:relative; width:100%;height:100%;}
#menu_wrap {position:absolute;top:0;left:0;bottom:0;width:250px;margin:10px 0 30px 10px;padding:5px;overflow-y:auto;background:rgba(255, 255, 255, 0.7);z-index: 1;font-size:12px;border-radius: 10px;}
.bg_white {background:#fff;}
#menu_wrap hr {display: block; height: 1px;border: 0; border-top: 2px solid #5F5F5F;margin:3px 0;}
#menu_wrap .option{text-align: center;}
#menu_wrap .option p {margin:10px 0;}  
#menu_wrap .option button {margin-left:5px;}

.custom_typecontrol {position:absolute;top:10px;right:10px;overflow:hidden;width:130px;height:45px;margin:0;padding:0;z-index:1;font-size:12px;font-family:'Malgun Gothic', '맑은 고딕', sans-serif;}
.custom_typecontrol span {display:block;float:left;text-align:center;line-height:30px;cursor:pointer;}
.custom_typecontrol .btn {background:#fff;background:linear-gradient(#fff,  #e6e6e6);}       
.custom_typecontrol .btn:hover {background:#f5f5f5;background:linear-gradient(#f5f5f5,#e3e3e3);}
.custom_typecontrol .btn:active {background:#e6e6e6;background:linear-gradient(#e6e6e6, #fff);}    
.custom_typecontrol .selected_btn {color:#fff;background:#425470;background:linear-gradient(#425470, #5b6d8a);}
.custom_typecontrol .selected_btn:hover {color:#fff;}   
.custom_zoomcontrol {position:absolute;top:50px;right:10px;width:36px;height:80px;overflow:hidden;z-index:1;background-color:#f5f5f5;} 
.custom_zoomcontrol span {display:block;width:36px;height:40px;text-align:center;cursor:pointer;}     
.custom_zoomcontrol span img {width:15px;height:15px;padding:12px 0;border:none;}             
.custom_zoomcontrol span:first-child{border-bottom:1px solid #bfbfbf;}        
</style>