<template>
<div class="text-center">
  <table class="mx-auto">
    <tbody>
      <tr>
        <td>
        <b-form-select v-model="sidoCode" :options="sidos" @change="gugunList"></b-form-select>
        </td>
        <td>
          <b-form-select v-model="gugunCode" :options="guguns" @change="dongList"></b-form-select>
        </td>
        <td>
          <b-form-select v-model="dongCode" :options="dongs"></b-form-select>
        </td>
      </tr>
    </tbody>
  </table>
    <input class="btn btn-md btn-warning w-25 mt-3" @click="searchApt" value="검색" />
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
    ...mapActions(["getSido", "getGugun", "getDong", "getHouseListSi", "getHouseListGun", "getHouseListDong"]),
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
      if (this.sidoCode == null)
        return;

      let juso = "";
      
      if (this.sidoCode != null) {
        juso += this.sidoCode.juso;
      }
      if (this.gugunCode != null) {
        juso += " " + this.gugunCode.juso;
      }
      if (this.dongCode != null) {
        juso += " " + this.dongCode.juso;
      }
      this.SET_JUSO(juso);
      
      if (this.gugunCode == null) {
        alert('주소를 선택해주세요.');
        // this.getHouseListSi(this.sidoCode.code);
      } 
      else if (this.dongCode == null) {
        this.getHouseListGun(this.gugunCode.code);
      } 
      else {
        this.getHouseListDong(this.dongCode.code);
      }
    },
  },
};
</script>

<style>
</style>
