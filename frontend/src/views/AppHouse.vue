<template>
<div>
    <!-- <house-side-bar></house-side-bar> -->
		<full-kakao-map :juso="juso"></full-kakao-map>
</div>
</template>
<script>
// import HouseSideBar from "@/components/house/HouseSideBar.vue";
import FullKakaoMap from "@/components/house/FullKakaoMap";

export default {
  name: "AppHouse",
  components: {
    // HouseSideBar,
    FullKakaoMap,
  },
  data() {
    return {
      juso: "",
    }
  },
};
</script>
<style scoped>
.underline-orange {
  display: inline-block;
  background: linear-gradient(180deg, rgba(255, 255, 255, 0) 70%, rgba(231, 149, 27, 0.3) 30%);
}
</style>
