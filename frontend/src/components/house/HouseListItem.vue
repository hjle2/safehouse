<template>
  <div>
    {{msg}}
    <div id="content" class="p-2">
        <div id="deal-container" class="border p-2 mb-1 rounded" @click="selectHouse">
        <div class="d-flex justify-content-between">
        <div>
            <h5>아파트명 : {{house.아파트 }}</h5>
        </div>
        <div> 
            <h5>
                <!-- <span class="badge bg-secondary">{{deal.cnt }}</span> -->
            </h5>
        </div>
        </div>
        <h6>거래 금액 : {{house.거래금액 }}</h6>
        </div>
        </div>
    </div>
</template>

<script>
import { mapActions } from "vuex";
export default {
    name: "HouseListItem",
    data() {
        return {
            msg: "",
            isColor: false,
        };
    },
    props: {
        house: Object,
    },
    methods: {
        ...mapActions(["detailHouse"]),
        selectHouse() {
            this.detailHouse(this.house);
        },
        colorChange(flag) {
            this.isColor = flag;
        },
    },
}
</script>

<style scoped>
.apt {
  width: 50px;
}
.mouse-over-bgcolor {
  background-color: lightblue;
}
</style>
