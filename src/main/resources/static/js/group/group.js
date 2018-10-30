function change(value){
	var obj =document.getElementById("test_select");
	var index=obj.selectedIndex;
	var obj_value=obj.options[index].value;
	var obj_div1 = document.getElementById("test1");
	var obj_div2 = document.getElementById("test2");
	var obj_div3 = document.getElementById("test3");
		if(obj_value=='1'){
			obj_div1.style.display="block";
			obj_div2.style.display="none";
			obj_div3.style.display="none";
		}else if(obj_value=='2'){
			obj_div1.style.display="none";
			obj_div2.style.display="block";
			obj_div3.style.display="none";
		}else if(obj_value=='3'){
			obj_div1.style.display="none";
			obj_div2.style.display="none";
			obj_div3.style.display="block";
		}
}
function changeclass(value){
	var btc_div1 = document.getElementById("class1501311");
	var btc_div2 = document.getElementById("class1501312");
	var btc_div3 = document.getElementById("class1501313");
	if(value=="1501311"){
		btc_div1.style.display="block";
		btc_div2.style.display="none";
		btc_div3.style.display="none";
	}else if(value=="1501312"){
		btc_div1.style.display="none";
		btc_div2.style.display="block";
		btc_div3.style.display="none";
	}else if(value=="1501313"){
		btc_div1.style.display="none";
		btc_div2.style.display="none";
		btc_div3.style.display="block";
	}
}
function sendtoteam(){
    var ary = new Array();
    var items = document.getElementsByName("item");
//	alert(items.length);
	var wbid;
	var pvalue;
	var rightTbody;
	var tr = document.createElement("tr");
    for(var i = 0; i < items.length; i++)
    {
        if(items[i].checked)
        {
			
        //    ary[i] = document.getElementById("id"+items[i].value).parentNode.parentNode.rowIndex;//保存下所选行的索引
			//alert(i);
		//	alert(items[i].value);
		//	alert(document.getElementById("id"+items[i].value));
		//	alert(document.getElementById("id"+items[i].value).parentNode);
	//		alert(document.getElementById("id"+items[i].value).parentNode.parentNode);
	//		alert(document.getElementById("id"+items[i].value).parentNode.parentNode.rowIndex);
			ary[i] = document.getElementById("id"+items[i].value).parentNode.parentNode.rowIndex;
			var op = items[i].value;
			wbid = document.getElementById("id"+op).value;
			pvalue = document.getElementById("btn"+op).value;
			var td1 = document.createElement("td");
			td1.innerHTML = "<input type='checkbox' class='check' id='id"+wbid+"' name='item1' value='"+wbid+"'><input type='button' class='btn1' id='pn"+ wbid +"' onclick='sel(id)' value='"+pvalue+"'/>";
			tr.appendChild(td1);
       //     moveValueOfLeft(items[i].value);//移值
        }
    }
	rightTbody = document.getElementById("tab_sendValue3");
	rightTbody.appendChild(tr);
	alert(ary.length);                            
    for(var i = ary.length; i >0; i--)
    {
        var leftTbody = document.getElementById("tab_sendValue1");
        //左边表格的tbody
        //判断数组ary里的值是不是行索引
        if(!isNaN(ary[i-1]))
        {
		//	alert(ary[i-1]);
            leftTbody.deleteRow(ary[i-1]-1);
            //移除表格的所选行
        }
    }
 //   document.getElementById("check_all").checked = false;
//全选复选框置为false
}
//移动左边表格的值到右边表格
function moveValueOfLeft(op){
    var wbid = document.getElementById("id"+op).value;
	var pvalue = document.getElementById("btn"+op).value;
    var rightTbody = document.getElementById("tab_sendValue3");
//右边表格的tbody
    var tr = document.createElement("tr");
    var td1 = document.createElement("td");   
    td1.innerHTML = "<input type='checkbox' class='check' id='id"+wbid+"' name='item1' value='"+wbid+"'><input type='button' class='btn1' id='pn"+ wbid +"' onclick='sel(id)' value='"+pvalue+"'/>";
    tr.appendChild(td1);
    rightTbody.appendChild(tr);
}
//移动右边表格的值到左边表格
function sendValueToLeft(){

    var ary1 = new Array();
    var items = document.getElementsByName("item1");
    for(var i = 0; i < items.length; i++)
    {
        if(items[i].checked)
        {
            //先保存所选行的索引 在移除掉所选行
            ary1[i] = document.getElementById("id"+items[i].value).parentNode.parentNode.rowIndex;
            //保存下所选行的索引
            moveValueOfRight(items[i].value);//移值
        }
    }
    for(var i = ary1.length; i >0; i--)
    {
        var rightTbody = document.getElementById("tab_sendValue3");
        //右边表格的tbody
        //判断数组ary里的值是不是行索引
        if(!isNaN(ary1[i-1]))
        {
            rightTbody.deleteRow(ary1[i-1]-1);
            //移除表格的所选行
        }
    }
    document.getElementById("check_all3").checked = false;
//全选复选框置为false
}
//移动右边表格的值到左边表格
function moveValueOfRight(op){
    var wbid = document.getElementById("id"+op).value;
	var pvalue = document.getElementById("p"+op).value;
    var leftTbody = document.getElementById("tab_sendValue1");
//左边表格的tbody
    var tr = document.createElement("tr");
    var td1 = document.createElement("td");                                                        
    td1.innerHTML = "<input type='checkbox' class='check' id='id"+wbid+"' name='item' value='"+wbid+"'><input type='button' class='btn1' id='pn"+ wbid +"'  onclick='sel(id)' value='"+pvalue+"'/>";
    tr.appendChild(td1);
    leftTbody.appendChild(tr);
}
function sel(op){
	var value = op.toString();
//	alert(value);
	var vals = value.split("n");
	if (document.getElementById("id"+vals[1]).checked){
		document.getElementById("id"+vals[1]).checked = false;
		var butn = document.getElementById(value);
		butn.className = "btn1";
	}else{
		document.getElementById("id"+vals[1]).checked = true;
		var butn = document.getElementById(value);
		butn.className = "btn2";
	}
}