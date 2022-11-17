<template>
<table class="table">
    <thead class="thead-light">
        <th>제목</th>
        <th width="15%">링크</th>
    </thead>
    <news-list-item v-for="(news, index) in newss" :key="index" :news="news"></news-list-item>
</table>
</template>

<script>
import NewsListItem from "@/components/news/NewsListItem"

export default {
    name: "NewsList",
    components: {
        NewsListItem,
    },
	props: {
		newss: Array
	},
}
</script>

<style>

</style>