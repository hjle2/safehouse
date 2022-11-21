<template>
<div class="bg-light p-5 rounded container-xl">
    <h3>부동산 NEWS</h3>
    <hr>
    <b-col>
      <news-list class="mx-auto" :newss="newss"></news-list>
    </b-col>
    <nav aria-label="Page navigation" class="wrapper">
      <ul class="pagination">
        <li class="page-item"><a class="page-link" href="#">&laquo;</a></li>
        <li class="page-item" v-for="i in 10" :key="i">
          <a class="page-link" @click="getNewss(i)">{{i}}</a>
        </li>
        <li class="page-item"><a class="page-link" href="#">&raquo;</a></li>
      </ul>
    </nav>
</div>
</template>

<script>
import http from "@/api/http";
import NewsList from '@/components/news/NewsList'
export default {
  components: { NewsList },
  name: "AppNews",
  data() {
    return {
      newss: [],
    }
  },
    methods: {
      getNewss(pageNum) {
        const NEWS_URL = `/news/${pageNum}`;
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
    created() {
      this.getNewss(1);
    },
}
</script>

<style>
.wrapper {
    text-align: center;
}
.wrapper ul {
    display: inline-block;
    margin: 0;
    padding: 0;
    /* For IE, the outcast */
    zoom:1;
    *display: inline;
}
.wrapper li {
    float: left;
}
</style>