<template>
  <div id="app" style="height:0%">
    <the-header-nav-bar></the-header-nav-bar>
    <main class="d-flex justify-content-center align-items-center">
      <router-view></router-view>
    </main>
    <!-- <div style="height:100%;">
      <router-view></router-view>
    </div> -->
    <the-footer></the-footer>
  </div>
</template>

<script>
import TheHeaderNavBar from "@/components/TheHeaderNavBar"
import TheFooter from "@/components/TheFooter"
export default {
  name: "App",
  components: {
    TheHeaderNavBar,
    TheFooter,
  },
};
</script>

<style>
html {
  height: 100%;
}
body {
  height: 100%;
}
main {
	min-height: 90vh;
	background-image: url("@/assets/img/bg-img.jpg");
	background-size: cover;
	background-repeat: no-repeat;
	background-position: center;
  height: 100%;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}
</style>
