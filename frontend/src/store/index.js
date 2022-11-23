import Vue from 'vue'
import Vuex from 'vuex'
import http from "@/api/http";

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    sidos: [{ value: {code: "", juso: ""}, text: "선택하세요" }],
    guguns: [{ value: {code: "", juso: ""}, text: "선택하세요" }],
    dongs: [{ value: {code: "", juso: ""}, text: "선택하세요" }],
    juso: "",
    houses: [],
    house: null,
    newss: [],
    polices: [],
  },
  getters: {
  },
  mutations: {
    /////////////////////////////// House start /////////////////////////////////////
    SET_SIDO_LIST(state, sidos) {
      sidos.forEach((sido) => {
        state.sidos.push({ value: {code: sido.sidoCode, juso: sido.sidoName}, text: sido.sidoName });
      });
    },
    SET_GUGUN_LIST(state, guguns) {
      guguns.forEach((gugun) => {
        state.guguns.push({ value: {code: gugun.gugunCode, juso: gugun.gugunName}, text: gugun.gugunName });
      });
    },
    SET_DONG_LIST(state, dongs) {
      dongs.forEach((dong) => {
        state.dongs.push({ value: {code: dong.dongCode, juso: dong.dongName}, text: dong.dongName });
      });
    },
    CLEAR_SIDO_LIST(state) {
      state.sidos = [{ value: null, text: "선택하세요" }];
    },
    CLEAR_APT_LIST(state) {
      state.houses = [];
      state.house = null;
    },
    CLEAR_GUGUN_LIST(state) {
      state.guguns = [{ value: null, text: "선택하세요" }];
    },
    CLEAR_DONG_LIST(state) {
      state.dongs = [{ value: null, text: "선택하세요" }];
    },
    SET_HOUSE_LIST(state, houses) {
      state.houses = houses;
    },
    SET_JUSO(state, juso) {
      state.juso = juso;
    },
    SET_DETAIL_HOUSE(state, house) {
      state.house = house;
    },
    SET_POLICE(state, polices) {
      state.polices = polices;
    },
    LOGIN() {
    },
    /////////////////////////////// House end /////////////////////////////////////
  },
  actions: {
    /////////////////////////////// House start /////////////////////////////////////
    getSido({ commit }) {
      http
        .get(`/house/sido`)
        .then(({ data }) => {
          commit("SET_SIDO_LIST", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getGugun({ commit }, sidoCode) {
      const params = { sido: sidoCode };
      http
        .get(`/house/gugun`, { params })
        .then(({ data }) => {
          commit("SET_GUGUN_LIST", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getDong({ commit }, gugunCode) {
      const params = { gugun: gugunCode };
      http
        .get(`/house/dong`, { params })
        .then(({ data }) => {
          commit("SET_DONG_LIST", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getHouseListSi({ commit }, code) {
      // vue cli enviroment variables 검색
      //.env.local file 생성.
      // 반드시 VUE_APP으로 시작해야 한다.
      // const SERVICE_KEY = process.env.VUE_APP_APT_DEAL_API_KEY;
      // // const SERVICE_KEY =
      // //   "######################## Service Key ########################";
      // const SERVICE_URL =
      //   "http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev";
      // const params = {
      //   LAWD_CD: gugunCode,
      //   DEAL_YMD: "202207",
      //   serviceKey: decodeURIComponent(SERVICE_KEY),
      // };
      const params = {code: code}
      http
        .get(`/house/list/sido`, { params })
        .then(({ data }) => {
          commit("SET_HOUSE_LIST", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getHouseListGun({ commit }, code) {
      const params = {code: code};
      http
        .get(`/house/list/gugun`, { params })
        .then(({ data }) => {
          commit("SET_HOUSE_LIST", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getHouseListDong({ commit }, code) {
      const params = {code: code};
      http
        .get(`/house/list/dong`, { params })
        .then(({ data }) => {
          commit("SET_HOUSE_LIST", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getPoliceStations({commit}) {
      http
        .get(`/house/list/police`)
        .then(({ data }) => {
          commit("SET_POLICE", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    detailHouse({ commit }, house) {
      // 나중에 house.일련번호를 이용하여 API 호출
      // console.log(commit, house);
      commit("SET_DETAIL_HOUSE", house);
    },
    /////////////////////////////// House end /////////////////////////////////////
    login({commit}, user) {
      http
      .post(`/user/login`, user)
      .then(() => {
        alert("로그인 성공!");
        sessionStorage.setItem("islogin", true);
        location.href='/';
      })
      .catch((error) => {
        console.log(error);
        commit("LOGIN");
      });
    },
    register({commit}, user) {
      http
      .post(`/user/regist`, user)
      .then(() => {
        alert('회원가입 성공!');
        location.href='/';
      })
      .catch((error) => {
        console.log(error);
        if (error.response.status === 500) {
          alert('이미 있는 아이디 입니다.')
        }
        commit("LOGIN");
      });
    },
    findpwd({commit}, user) {
      http
      .post(`/user/findpwd`, user)
      .then((data) => {
        alert('임시 비밀번호는 ' + data.data + ' 입니다.');
        location.href='/user/login';
      })
      .catch((error) => {
        console.log(error);
        commit("LOGIN");
      });
    },
  },
  modules: {
  },
  // plugins: [
  //   createPersistedState({
  //     // 브라우저 종료시 제거하기 위해 localStorage가 아닌 sessionStorage로 변경. (default: localStorage)
  //     storage: sessionStorage,
  //   }),
  // ],
})
