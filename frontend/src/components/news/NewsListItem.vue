<template>
<tr>
    <td class="left">{{news.title}}</td>
    <td><a :href="news.link">바로가기</a></td>
</tr>
</template>

<script>
export default {
    name: "NewsListItem",
    props: {
        page: Number,
        news: Object,
    }
}
</script>

<style>

</style>