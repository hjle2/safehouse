<template>
<!-- 회원 가입 부분  start-->
<div id="main"
  class="container-xl p-5 my-5 text-white text-center rounded">
  <h3 class="p-1">회원가입</h3>
  <hr class="mb-5" />
  <div class="">
    
      <input type="hidden" name="act" value="regist">
      <div class="input-group mb-1 input-group-lg">
        <span class="input-group-text w-25">아이디</span> 
        <input type="text" v-model="user.id"
        class="form-control" placeholder="특수문자 불가"
          required>
      </div>
      <div class="input-group mb-1 input-group-lg">
        <span class="input-group-text w-25">비밀번호</span> 
        <input type="password" v-model="user.pwd"
        class="form-control"
          placeholder="영문 숫자 포함 6자리 이상" required>
      </div>
      <div class="input-group mb-1 input-group-lg">
        <span class="input-group-text w-25">이름</span> 
        <input type="text" v-model="user.name"
          class="form-control" placeholder="실명으로 작성"
          required>
      </div>
      <div class="input-group mb-1 input-group-lg">
        <span class="input-group-text w-25">주소</span> 
        <input type="text" v-model="user.address"
          class="form-control" placeholder="실거주지"
          required>
      </div>
      <div class="input-group mb-1 input-group-lg">
        <span class="input-group-text w-25">전화번호</span> 
        <input type="text" v-model="user.tel"
          class="form-control"
          placeholder="010-XXXX-XXXX 형식" required>
      </div>

      <button @click="join"
        class="btn btn-warning btn-lg w-25 mt-5">등록</button>
  </div>
</div>
<!-- 회원 가입 부분  end--> 
</template>

<script>
import { mapActions } from "vuex";
export default {
  name: "UserJoin",
  data() {
    return {
      user: {
        id: "",
        pwd: "",
        name: "",
        address: "",
        tel: "",
      }
    }
  },
  methods: {
    ...mapActions(["register"]),
    join() {
      this.register(this.user);
    }
  },
};
</script>

<style>
#main{
    background-color: cadetblue;
}
</style>
