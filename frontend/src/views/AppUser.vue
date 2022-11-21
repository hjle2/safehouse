<template>
<div class="bg-light p-5 rounded container-xl">
    <h3 class="underline-steelblue"><b-icon icon="person-lines-fill"></b-icon> Member Service</h3>
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: "AppUser",
};
</script>

<style scoped>
.underline-steelblue {
  display: inline-block;
  background: linear-gradient(180deg, rgba(255, 255, 255, 0) 70%, rgba(72, 190, 233, 0.3) 30%);
}
</style>
