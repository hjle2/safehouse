<template>
  <b-container class="bv-example-row mt-3 text-center">
    <h3 class="underline-orange"><b-icon icon="house-fill"></b-icon> House Service</h3>
    <b-row>
      <b-col>
        <!-- <b-img alt="Happy House" :src="require('@/assets/happyhouse.png')" /> -->
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <house-search-bar></house-search-bar>
      </b-col>
    </b-row>
    <b-row>
      <b-col cols="6" align="left">
        <house-list></house-list>
      </b-col>
    </b-row>
  </b-container>
</template>
<script>
import HouseSearchBar from "@/components/house/HouseSearchBar.vue";
import HouseList from "@/components/house/HouseList.vue";

export default {
  name: "AppHouse",
  components: {
    HouseSearchBar,
    HouseList,
  },
};
</script>
<style scoped>
.underline-orange {
  display: inline-block;
  background: linear-gradient(180deg, rgba(255, 255, 255, 0) 70%, rgba(231, 149, 27, 0.3) 30%);
}
</style>
