<template>
<div>
  <b-row class="mt-4 mb-4 text-center">
    <!-- <b-col class="sm-3">
      <b-form-input
        v-model.trim="dongCode"
        placeholder="동코드 입력...(예 : 11110)"
        @keypress.enter="sendKeyword"
      ></b-form-input>
    </b-col> -->
    <b-col class="sm-3">
      <b-form-select v-model="sidoCode" :options="sidos" @change="gugunList"></b-form-select>
    </b-col>
    <b-col class="sm-3">
      <b-form-select v-model="gugunCode" :options="guguns" @change="dongList"></b-form-select>
    </b-col>
    <b-col class="sm-3">
      <b-form-select v-model="dongCode" :options="dongs"></b-form-select>
    </b-col>
    <b-button class="sm-3" variant="outline-primary" @click="searchApt">검색</b-button>
  </b-row>
  </div>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";

export default {
  name: "HouseSearchBar",
  data() {
    return {
      sidoCode: null,
      gugunCode: null,
      dongCode: null,
    };
  },
  computed: {
    ...mapState(["sidos", "guguns", "dongs", "houses"]),
  },
  created() {
    this.CLEAR_SIDO_LIST();
    this.CLEAR_GUGUN_LIST();
    this.CLEAR_DONG_LIST();
    this.CLEAR_APT_LIST();
    this.getSido();
  },
  methods: {
    ...mapActions(["getSido", "getGugun", "getDong", "getHouseList"]),
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
      let juso = "";
      if (this.sidoCode.juso) {
        juso += this.sidoCode.juso;
      }
      if (this.gugunCode.juso) {
        juso += " " + this.gugunCode.juso;
      }
      if (this.dongCode.juso) {
        juso += " " + this.dongCode.juso;
      }
      this.SET_JUSO(juso);
      this.getHouseList(this.dongCode.code);
    },
  },
};
</script>

<style></style>
