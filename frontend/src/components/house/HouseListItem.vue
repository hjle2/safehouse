<template>
<div id="content" class="p-2">
    <div id="deal-container" class="border p-2 mb-1 rounded" @click="selectHouse">
    <div class="d-flex justify-content-between">
    <div>
        <h5 align="left">아파트명 : {{house.aptName }}</h5>
    </div>
    <div> 
        <h5>
            <span class="badge bg-secondary">{{house.cnt }}</span>
        </h5>
    </div>
    </div>
        <h6 align="left">평균 거래액 : {{house.dealAmount }}</h6>
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

/* searchResult */
#deal-container {
	box-shadow: 0 1px 3px rgba(0, 0, 0, 0.15);
	transition: 0.4s;
	cursor: pointer;
	box-shadow:
}

#deal-container:hover {
	transform: translate(0, -3px);
	box-shadow: 0 2px 8px rgba(0, 0, 0, 0.3);
}

.bg-secondary {
    color: white;
}
</style>
