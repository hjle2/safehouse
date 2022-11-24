<template>
<div>
<div class="bg-light p-5 rounded container-xl">
  <div class="mb-5 text-center">
    <h1>모든 실거래 현황은 <mark>SAFE HOUSE</mark>에서!!</h1>
  </div>
  <div>
    <house-search-bar></house-search-bar>
  </div>
</div>
<div class="m-3"></div>
<div class="rounded" style="display:flex;justify-content:space-between;">
  <b-col class="bg-light rounded">
    <div class="left"><h3>NEWS</h3></div>
    <div class="p-3 d-flex justify-content-between" v-for="idx in 5" :key="idx">
    <div class="item">
      <a class="left" :href=newss[idx].link>{{newss[idx].title}}</a>
    </div>
    </div>
  </b-col>
  <div>
  </div>
</div>

</div>
</template>

<script>
import http from "@/api/http";
import HouseSearchBar from '@/components/house/HouseSearchBar.vue'
export default {
    name: "AppMain",
    data() {
      return {
        newss: [],
      }
    },
    components: {
      HouseSearchBar,
    },
    created() {
      this.getNewss();
    },
    methods: {
      getNewss() {
        const NEWS_URL = `/news/1`;
        http
        .get(NEWS_URL)
        .then(({data}) => {
          this.newss = data;
        })
        .catch((error) => {
          console.log(error);
        });
      },
    },

}
</script>

<style>
/* searchResult */
.item * {
	transition: 0.4s;
	cursor: pointer;
  margin: 3px;
  color: black;
}

.item:hover *{
	transform: translate(0, -3px);
  border-radius: 5px;
	box-shadow: 0 2px 8px rgba(0, 0, 0, 0.3);
  color: skyblue;
}

</style>