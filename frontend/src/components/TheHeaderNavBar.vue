<template>
<div>
  <b-navbar toggleable="lg" type="dark" variant="dark">
		<b-navbar-brand href="/">SAFE HOUSE
		</b-navbar-brand>
		<b-navbar-toggle target="nav-collapse"> </b-navbar-toggle>
		
    <b-collapse id="nav-collapse" is-nav>
      <b-navbar-nav>
        <b-nav-item href="/news">NEWS</b-nav-item>
        <b-nav-item href="/board">BOARD</b-nav-item>
      </b-navbar-nav>
      
      <div v-if="isLogin">
      <b-navbar-nav class="ml-auto">
        <b-nav-item @click="logout">LOGOUT</b-nav-item>
      </b-navbar-nav>
      </div>
      <div v-else>
      <b-navbar-nav class="ml-auto">
        <b-nav-item href="/user/join">SIGN UP</b-nav-item>
        <b-nav-item href="/user/login">LOGIN</b-nav-item>
      </b-navbar-nav>
      </div>
    </b-collapse>
	</b-navbar>
</div>
		
</template>

<script>
// import { mapState, mapMutations } from "vuex";
export default {
	name: "TheHeaderNavBar",
  data() {
    return {
      isLogin: false,
    }
  },
  methods: {
    logout() {
      this.isLogin = false;
      sessionStorage.removeItem('islogin');
      this.$router.push({name: "main"});
    },
  },
  computed: {
    // ...mapState(["user"]),
    // ...mapMutations(["LOGOUT"]),
  },
  created() {
    this.isLogin = sessionStorage.getItem('islogin');
  },
}
</script>

<style >

</style>