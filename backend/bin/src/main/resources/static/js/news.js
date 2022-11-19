const url = "https://navernews.herokuapp.com/";
var newsList = [];

window.onload = () => {
    setTimeout
    fetch(url)
    .then(function(response) {
        return response.json();
    })
    .then((myJson)=>setTable(myJson));
}

const setTable = (myJson) =>{
    // 뉴스 데이터 가져와서 newsList에 저장
    const data = JSON.parse(JSON.stringify(myJson));
    newsList = data.items;
    console.log(newsList);
    // table row에 추가
    const tableBody = document.getElementById("table-body");
    for(let i = 0; i<newsList.length; i++){
        const trEl = document.createElement('tr');
        var strippedHtml = newsList[i].description.replace(/<[^>]+>/g, '');

        const tdNContents = 
        `
        <td><span class="cursor-pointer	link-dark" data-bs-toggle="popover" title="뉴스 내용" data-bs-content="${strippedHtml}">${newsList[i].title}</span>&nbsp;<a href="${newsList[i].link}">자세히..</a></td>
        <td>${newsList[i].pubDate.slice(0, 25)}<td>
        `;
        trEl.innerHTML=tdNContents;
        tableBody.appendChild(trEl);
    }
    var popoverTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="popover"]'))
    var popoverList = popoverTriggerList.map(function (popoverTriggerEl) {
        return new bootstrap.Popover(popoverTriggerEl)
      });

      console.log(popoverList);
}

