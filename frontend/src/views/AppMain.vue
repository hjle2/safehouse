<template>
<div class="bg-light p-5 rounded container-xl">
  <div class="mb-5 text-center">
    <h1>모든 실거래 현황은 <mark>해피 하우스</mark>에서!!</h1>
  </div>
  <div>
    <house-search-bar></house-search-bar>
    <house-list></house-list>
  </div>
</div>
</template>

<script>
import HouseSearchBar from '@/components/house/HouseSearchBar.vue'
import HouseList from '@/components/house/HouseList.vue'
export default {
    name: "AppMain",
    components: {
      HouseSearchBar,
      HouseList,
    },

}
</script>

<style>

</style>