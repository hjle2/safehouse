<template>
  <b-tr>
    <b-td>{{ no }}</b-td>
    <b-th class="text-left">
      <router-link :to="{ name: 'boardview', params: { no: no } }">{{ title }}</router-link>
    </b-th>
    <b-td>{{ content }}</b-td>
    <b-td>{{ title }}</b-td>
    <b-td>{{ content }}</b-td>
    <b-td>{{ regtime | dateFormat }}</b-td>
  </b-tr>
</template>

<script>
import moment from "moment";

export default {
  name: "BoardListItem",
  props: {
    no: Number,
    id: String,
    title: String,
    content: Number,
    regtime: String,
  },
  filters: {
    dateFormat(regtime) {
      return moment(new Date(regtime)).format("YY.MM.DD");
    },
  },
};
</script>

<style></style>
