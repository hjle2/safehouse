<template>
<div class="bg-light p-5 rounded container-xl">
    <h3>QnA</h3>
    <hr>
    <router-view></router-view>
</div>
</template>

<script>
export default {
    name: "AppBoard",
}
</script>

<style scoped>
.underline-hotpink {
  display: inline-block;
  background: linear-gradient(180deg, rgba(255, 255, 255, 0) 70%, rgba(231, 27, 139, 0.3) 30%);
}
</style>
