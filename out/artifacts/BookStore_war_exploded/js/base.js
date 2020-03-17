// 保存所有的通用操作函数
function validateEmpty(eleName) {	// 验证指定元素内容是否为空
	var obj = document.getElementById(eleName) ;	// 取得对象
	var msg = document.getElementById(eleName + "Msg") ;	// 提示信息
	if (obj.value != "") {	// 进行内容的验证
		obj.className = "right" ;	// 更换使用的样式
		if (msg != null) {
			msg.innerHTML = "<font color='green'>内容输入正确！</font>" ;
		}
		return true ;	// 为以后的综合验证准备
	} else {	// 验证不通过
		obj.className = "wrong" ;	// 更换使用的样式
		if (msg != null) {
			msg.innerHTML = "<font color='red'>内容不允许为空！</font>" ;
		}
		return false ;	// 为以后的综合验证准备
	}
}
function validateRegex(eleName,regex) {
	var obj = document.getElementById(eleName) ;	// 取得对象
	var msg = document.getElementById(eleName + "Msg") ;	// 提示信息
	if (regex.test(obj.value)) {	// 进行内容的验证
		obj.className = "right" ;	// 更换使用的样式
		if (msg != null) {
			msg.innerHTML = "<font color='green'>内容输入正确！</font>" ;
		}
		return true ;	// 为以后的综合验证准备
	} else {	// 验证不通过
		obj.className = "wrong" ;	// 更换使用的样式
		if (msg != null) {
			msg.innerHTML = "<font color='red'>输入内容格式错误！</font>" ;
		}
		return false ;	// 为以后的综合验证准备
	}
}

function changeColor(obj,color) {	// 负责改变表格显示颜色
	obj.bgColor = color ;
}

function checkboxSelect(obj,eleName) {
	var item = document.all(eleName) ;
	if (item.length == undefined) {	// 表示只有一个元素，不是数组
		document.getElementById(eleName).checked = this.checked ;
	} else {
		for (var x = 0 ; x < item.length ; x ++) {
			item[x].checked = obj.checked ;
		}
	}
}
// url：表示要删除的操作路径
// paramName：表示要传递的参数名称
// eleName：表示要取得数据的ID名称
function deleteAll(url,paramName,eleName) {
	var data = "" ;	// 保存所有要删除的数据编号
	// 但是数据有可能是数组，也有可能只有一个
	var item = document.all(eleName) ;
	var count = 0 ;	// 保存要删除的数据个数
	// 要判断是否有要删除的数据
	if (item.length == undefined) {	// 表示只有一个元素，不是数组
		if (document.getElementById(eleName).checked == true) {	// 数据选中
			data += document.getElementById(eleName).value + "|" ;
			count ++ ;
		}
	} else {
		for (var x = 0 ; x < item.length ; x ++) {
			if (item[x].checked == true) {
				count ++ ;
				data += item[x].value + "|" ;
			}
		}
	}
	if (count > 0) {	// 有要删除的数据
		if (window.confirm("确定要删除这些数据吗？")) {
			// console.log(url + "?" + paramName + "=" + data) ;
			window.location = url + "&" + paramName + "=" + data ;
		}
	} else {
		alert("您还未选择任何要删除的数据！") ;
	}
}

function openPage(url) {
	window.open(url,"查看详细信息","width=600;height=500;scollable=yes") ;
}
function closePage() {
	window.close() ;
}

function changeCode(obj) {	// 要修改显示的验证码
	obj.src = "pages/image.jsp?tm=" + Math.random() ;
}
