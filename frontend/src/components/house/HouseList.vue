<template>
	<!-- main -->
	<div>
		<div id="resultname">
				<h3>{{juso}}</h3>
		</div>
		<hr />
			<div class="row">
				<b-container v-if="houses && houses.length != 0" style="display:flex">
					<div class="col-md-4 col-xxl-4 p-0 border" style="position:relative; height:600px; overflow-y:scroll;" >
						<h3 class="bg-warning p-2">거래정보</h3>
							<house-list-item v-for="(house, index) in houses" :key="index" :house="house"></house-list-item>
					</div>

					<div class="col-md-7 col-xxl-8">
						<kakao-map id="map" :juso="juso" :houses="houses"></kakao-map>
					</div>
				</b-container>
				<b-container v-else class="text-center">
					<b-col><b-alert show>주택 목록이 없습니다.</b-alert></b-col>
				</b-container>
			</div>
	</div>
</template>

<script>
import HouseListItem from "@/components/house/HouseListItem";
import KakaoMap from "@/components/house/KakaoMap"
import { mapState } from "vuex";

export default {
  name: "HouseList",
  components: {
    HouseListItem,
	KakaoMap,
  },
  data() {
	return {
	}
  },
  methods: {
	makeOutListener(infowindow) {
		return function() {
			infowindow.close();
		};
	},
	makeOverListener(map, marker, infowindow) {
		return function() {
			infowindow.open(map, marker);
		};
	}
  },
  computed: {
    ...mapState(["houses", "juso"]),
  },
}

</script>

<style>
kakao-map {
	width: 100%;
}
</style>