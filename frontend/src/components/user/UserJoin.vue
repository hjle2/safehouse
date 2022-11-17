<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert variant="secondary" show><h3>회원가입</h3></b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col></b-col>
      <b-col cols="8">
        <b-card class="text-center mt-3" style="max-width: 40rem" align="left">
          <b-form class="text-left">
            <b-alert show variant="danger" v-if="isLoginError">아이디 또는 비밀번호를 확인하세요.</b-alert>
            <b-form-group label="아이디:" label-for="userid">
              <b-form-input
                id="userid"
                required
                v-model="user.id"
                placeholder="아이디 입력...."
              ></b-form-input>
            </b-form-group>
            <b-form-group label="비밀번호:" label-for="userpwd">
              <b-form-input
                type="password"
                id="userpwd"
                required
                v-model="user.pwd"
                placeholder="비밀번호 입력...."
              ></b-form-input>
            </b-form-group>
            <b-form-group label="이름:" label-for="username">
              <b-form-input
                id="username"
                required
                v-model="user.name"
                placeholder="이름 입력...."
              ></b-form-input>
            </b-form-group>
            <b-form-group label="주소:" label-for="useradd">
              <b-form-input
                id="useradd"
                required
                v-model="user.address"
                placeholder="실거주지"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="전화번호:" label-for="usertel">
              <b-form-input
                id="usertel"
                v-model="user.tel"
                placeholder="010-XXXX-XXXX 형식"
              ></b-form-input>
            </b-form-group>
          </b-form>
          <b-button type="button" variant="success" class="m-1" @click="join">회원가입</b-button>
        </b-card>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapActions } from "vuex";
export default {
  name: "UserJoin",
  data() {
    return {
      isLoginError: false,
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
</style>
