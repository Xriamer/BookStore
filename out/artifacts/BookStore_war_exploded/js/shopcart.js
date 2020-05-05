var allPrice = 0.0;
window.onload = function () {
    document.getElementById("result").innerHTML = "<font class='red'>总价格:" + allPrice;
}

function calBooks(bid) {
    var price = parseFloat(document.getElementById("price-" + bid).innerHTML);
    var count = parseInt(document.getElementById(bid).value);
    allPrice += (price * count);
   // alert("bid=" + bid + ",price=" + price + ",count=" + count);
    document.getElementById("cal-" + bid).innerHTML = "<font class='red'>" + (price * count);
    if (document.getElementById("result") != undefined) {
        document.getElementById("result").innerHTML = "<font class='red'>总价格:" + allPrice+"元";
    }

}

function addBut(bid) {
    var price = parseFloat(document.getElementById("price-" + bid).innerHTML);
    var count = parseInt(document.getElementById(bid).value);
    allPrice -= (price * count);
    var count = parseInt(document.getElementById(bid).value);
    count++;
    document.getElementById(bid).value = count;
    calBooks(bid);
}

function subBut(bid) {
    var price = parseFloat(document.getElementById("price-" + bid).innerHTML);
    var count = parseInt(document.getElementById(bid).value);
    allPrice -= (price * count);
    var count = parseInt(document.getElementById(bid).value);
    if (count >= 0) {
        count--;
        document.getElementById(bid).value = count;
    }
    calBooks(bid);
}

function calResult() {

}