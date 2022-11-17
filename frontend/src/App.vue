<template>
  <div id="app">
    <main>
    <the-header-nav-bar></the-header-nav-bar>
    <router-view></router-view>
    </main>
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
main {
	min-height: 90vh;
	background-image: url("@/assets/img/bg-img.jpg");
	background-size: cover;
	background-repeat: no-repeat;
	background-position: center;
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
