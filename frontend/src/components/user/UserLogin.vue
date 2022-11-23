<template>
  <div id="main" class="container-xl p-5 my-5 text-white text-center rounded">
        <div class="align-items-center">
          <h3 class="p-1">로그인</h3>
          <hr class="mb-5"/>
          <div>
            <div class="input-group mb-1 input-group-lg">
              <span class="input-group-text w-25">아이디</span>
              <input type="text" v-model="user.id" class="form-control">
            </div>
            <div class="input-group mb-1 input-group-lg">
              <span class="input-group-text w-25">비밀번호</span>
              <input type="password" v-model="user.pwd" class="form-control">
            </div>
            <button class="btn btn-warning btn-lg w-25 mt-5" @click="confirm">로그인</button>
          </div>
        </div>
		<div class="mt-3">
			<a class="text-light" href="/user/findpwd">비밀번호 찾기</a>&nbsp;&nbsp;&nbsp;
			<a class="text-light" href="/user/join">회원가입</a>	
		</div>
      </div>
</template>

<script>
import { mapActions } from "vuex";

export default {
  name: "UserLogin",
  data() {
    return {
      isLoginError: false,
      user: {
        id: null,
        pwd: null,
      },
    };
  },
  methods: {
    ...mapActions(["login"]),
    confirm() {
      console.log(this.user);
      this.login(this.user);
    },
  },
};
</script>

<style>
#main{
    background-color: cadetblue;
}
</style>
