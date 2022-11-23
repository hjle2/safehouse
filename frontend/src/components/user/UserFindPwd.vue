<template>
<!-- 로그인 부분  start-->
<div id="main" class="container-xl p-5 my-5 text-white text-center rounded">
  <div class="align-items-center">
    <h3 class="p-1">비밀번호 찾기</h3>
    <hr class="mb-5"/>
      <div class="input-group mb-1 input-group-lg">
        <span class="input-group-text w-25">아이디</span>
        <input type="text" v-model="id" class="form-control">
      </div>
      <div class="input-group mb-1 input-group-lg">
        <span class="input-group-text w-25">전화번호</span>
        <input type="tel" v-model="tel" placeholder="010-XXXX-XXXX" class="form-control">
      </div>
      <button @click="findPassword" class="btn btn-warning btn-lg w-25 mt-5">비밀번호 찾기</button>
  </div>
</div>
</template>

<script>
import {mapActions} from "vuex";
export default {
  name: "UserFindPwd",
  data() {
    return {
      id: "",
      tel: "",
    }
  },
  methods: {
    ...mapActions(["findpwd"]),
    findPassword() {
      this.findpwd(this.id, this.tel);
    }
  },
}
</script>

<style>
#main{
    background-color: cadetblue;
}
</style>