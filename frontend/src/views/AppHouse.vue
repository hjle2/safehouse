<template>
<!-- <house-side-bar></house-side-bar> -->
<full-kakao-map id="map"></full-kakao-map>
</template>
<script>
import FullKakaoMap from "@/components/house/FullKakaoMap";

export default {
  name: "AppHouse",
  components: {
    FullKakaoMap,
  },
  data() {
    return {
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
