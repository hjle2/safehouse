<template>
  <b-container class="text-center">
    <b-row>
      <b-col>
        <b-alert show><h3>글목록</h3></b-alert>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col class="text-right">
        <b-button variant="outline-primary" @click="moveWrite()">글쓰기</b-button>
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-table striped hover :items="articles" :fields="fields" @row-clicked="viewArticle" >
          <template #cell(subject)="data">
            <router-link :to="{ name: 'boardview', params: { articleno: data.item.articleno } }">
              {{ data.item.subject }}
            </router-link>
          </template>
        </b-table>
      </b-col>
    </b-row>
    <nav aria-label="Page navigation" class="wrapper p-0">
      <ul class="pagination">
        <li class="page-item"><a class="page-link" href="#">&laquo;</a></li>
        <li class="page-item" v-for="i in 10" :key="i">
          <a class="page-link" @click="getArticles(i)">{{i}}</a>
        </li>
        <li class="page-item"><a class="page-link" href="#">&raquo;</a></li>
      </ul>
    </nav>
  </b-container>
</template>

<script>
import http from "@/api/http";

export default {
  name: "BoardList",
  data() {
    return {
      articles: [],
      fields: [
        { key: "no", label: "글번호", tdClass: "tdClass" },
        { key: "title", label: "제목", tdClass: "tdSubject" },
        { key: "id", label: "작성자", tdClass: "tdClass" },
        { key: "regtime", label: "작성일", tdClass: "tdClass" },
      ],
      pageNo: 1,
    };
  },
  created() {
    http.get(`/board/1`).then(({ data }) => {
      this.articles = data;
    });
  },
  methods: {
    moveWrite() {
      this.$router.push({ name: "boardwrite" });
    },
    viewArticle(article) {
      this.$router.push({
        name: "boardview",
        params: { no: article.no },
      });
    },
    getArticles(pageNo) {
        const URL = `/board/${pageNo}`;
        http
        .get(URL)
        .then(({data}) => {
          this.articles = data;
          this.pageNo = pageNo;
        })
        .catch((error) => {
          console.log(error);
        });
    }
  },
};
</script>

<style scope>
.tdClass {
  width: 50px;
  text-align: center;
}
.tdSubject {
  width: 300px;
  text-align: left;
}
</style>
