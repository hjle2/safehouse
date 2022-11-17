<template>
	<!-- main -->
	<div class="d-flex justify-content-center align-items-center">
		<div class="bg-light p-5 rounded container-xl my-5">
			<div id="resultname">
				<h3>{{juso}}</h3>
				</div>
			<hr />
			<b-row>
				<b-container style="display:flex" v-if="houses && houses.length != 0" class="bv-example-row mt-3">
				
				<b-col cols="6" align="left">
					<h3 v-if="houses" class="bg-warning p-2">거래정보</h3>
					<house-list-item v-for="(house, index) in houses" :key="index" :house="house"></house-list-item>
				</b-col>

				<b-col cols="6" v-if="juso">
					<kakao-map id="map" :juso="juso"></kakao-map>
				</b-col>
				
				</b-container>
				<b-container v-else class="bv-example-row mt-3">
					<b-row>
						<b-col><b-alert show>주택 목록이 없습니다.</b-alert></b-col>
					</b-row>
				</b-container>
			</b-row>
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

</style>