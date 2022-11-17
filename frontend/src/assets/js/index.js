// const xhr = new XMLHttpRequest();
// const method = "GET";
// let url = "https://grpc-proxy-server-mkvo6j4wsq-du.a.run.app/v1/regcodes?regcode_pattern=";

// var sidoList = []; // 광역.특별시/도 리스트
// var guList = []; // 시/군/구 리스트
// var dongList = []; // 동 리스트

// // 광역.특별시/도 리스트 option 추가
// window.onload = () => {
//   const new_url = url + "*00000000";
//   xhr.open(method, new_url);

//   xhr.onreadystatechange = () => {
//     if (xhr.readyState == xhr.DONE && xhr.status == 200) {
//       const sidoSelectEl = document.getElementById("sido-select");
//       sidoList = JSON.parse(xhr.responseText).regcodes;
//       console.log(sidoList);
//       for (let i = 0; i < sidoList.length; i++) {
//         const optionEl = document.createElement("option");
//         optionEl.setAttribute("value", sidoList[i].name);
//         const textNode = document.createTextNode(sidoList[i].name);
//         optionEl.appendChild(textNode);
//         sidoSelectEl.appendChild(optionEl);
//       }
//     }
//   };
//   xhr.send(); // 요청 전송
// };

// const sidoSelectEl = document.getElementById("sido-select"); // 시/도 select element
// const guSelectEl = document.getElementById("gu-select"); // 시군구 select element
// const dongSelectEl = document.getElementById("dong-select"); // 동 select element

// // 시/도 select되면
// sidoSelectEl.addEventListener("change", (e) => {
//   while (guSelectEl.hasChildNodes()) {
//     guSelectEl.removeChild(guSelectEl.firstChild);
//   }
//   const defaultEl = document.createElement('option');
//   defaultEl.setAttribute("selected", true);
//   defaultEl.setAttribute("disabled", true);
//   defaultEl.setAttribute("hidden", true);
//   defaultEl.setAttribute("value", "");
//   defaultEl.innerText = "시/군/구";
//   guSelectEl.appendChild(defaultEl);
//   for (let i = 0; i < sidoList.length; i++) {
//     if (sidoList[i].name == e.target.value) {
//       guList = [];
//       getGuList(sidoList[i].code.slice(0, 2) + "*000000");
//       break;
//     }
//   }
// });

// // 시/군/구 select되면
// guSelectEl.addEventListener("change", (e) => {
//   while (dongSelectEl.hasChildNodes()) {
//     dongSelectEl.removeChild(dongSelectEl.firstChild);
//   }
//   const defaultEl = document.createElement('option');
//   defaultEl.setAttribute("selected", true);
//   defaultEl.setAttribute("disabled", true);
//   defaultEl.setAttribute("hidden", true);
//   defaultEl.setAttribute("value", "");
//   defaultEl.innerText = "동";
//   dongSelectEl.appendChild(defaultEl);
//   for (let i = 0; i < dongList.length; i++) {}
//   for (let i = 0; i < guList.length; i++) {
//     if (guList[i].name == e.target.value) {
//       dongList = [];
//       getDongList(guList[i].code.slice(0, 4) + "*");
//       break;
//     }
//   }
// });

// // 동 select 되면
// dongSelectEl.addEventListener("change", (e) => {
//   var codeInput = document.getElementById("codeInput");
//   for (let i = 0; i < dongList.length; i++) {
//     if (dongList[i].name == e.target.value) {
//       codeInput.value = dongList[i].code;
//       break;
//     }
//   }
// });

// // 시/군/구 리스트 option 추가
// const getGuList = (code) => {
//   const new_url = url + code;
//   xhr.open(method, new_url);

//   xhr.onreadystatechange = () => {
//     if (xhr.readyState == xhr.DONE && xhr.status == 200) {
//       const guSelectEl = document.getElementById("gu-select");
//       guList = JSON.parse(xhr.responseText).regcodes;
//       console.log(guList);
//       for (let i = 0; i < guList.length; i++) {
//         const optionEl = document.createElement("option");
//         optionEl.setAttribute("value", guList[i].name);
//         const textNode = document.createTextNode(guList[i].name.split(" ")[1]);
//         if (!guList[i].name.split(" ")[1]) continue;

//         optionEl.appendChild(textNode);
//         guSelectEl.appendChild(optionEl);
//       }
//     }
//   };
//   xhr.send(); // 요청 전송
// };

// // 동 리스트 option 추가
// const getDongList = (code) => {
//   const new_url = url + code;
//   xhr.open(method, new_url);

//   xhr.onreadystatechange = () => {
//     if (xhr.readyState == xhr.DONE && xhr.status == 200) {
//       const dongSelectEl = document.getElementById("dong-select");
//       dongList = JSON.parse(xhr.responseText).regcodes;
//       console.log(dongList);
//       for (let i = 0; i < dongList.length; i++) {
//         const optionEl = document.createElement("option");
//         optionEl.setAttribute("value", dongList[i].name);
//         const textNode = document.createTextNode(dongList[i].name.split(" ")[2]);
//         if (!dongList[i].name.split(" ")[2]) continue;
//         optionEl.appendChild(textNode);
//         dongSelectEl.appendChild(optionEl);
//       }
//     }
//   };
//   xhr.send();
// };
