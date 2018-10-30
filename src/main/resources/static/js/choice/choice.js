(function ($) {
    $.getUrlParam = function (name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return decodeURI(r[2]); return null;
    }
})(jQuery);

function saveStandard(standards,semester,course) {
    $.ajax({
        type:"POST",
        url:"/saveChangeStandard",
        data:{standards:standards,semester_name:semester,course_name:course},
        dataType: "JSON",
        async: true,
        success:function (data) {
        //   $(location).attr('href', 'index');
            $(location).attr('href', 'index');
            if(data.success == true){
      //          alert(data.message);
                $(location).attr('href', 'index');
            }else{
         //       alert(data.message)
            }
        }
    })
}

function saveChangeStandard() {
    var semester = decodeURI($.getUrlParam('semester'));
    var course = decodeURI($.getUrlParam('course'));
    var standards = "&&";
    var items = document.getElementsByName("item1");
    alert(items.length);
    for(var i=0; i<items.length; i++){
        var x = items[i].value;
 //       alert(x);
   //     var xx = x.substring(2,x.length);
        var y = document.getElementById("p"+x).value;
   //     alert(y);
        standards += y;
        standards += "&&";
      //  standards.push(y);
    }
    alert(standards);
    $.ajax({
        type:"POST",
        url:"/deleteStandard",
        data:{semester_name:semester,course_name:course},
        dataType: "JSON",
        async: true,
        success:function (data) {
            saveStandard(standards,semester,course);
            if(data.success==true){

            }else{
           //     alert(data.message);
            }
        }
    })
}

/*
$(document).ready(function () {
    var semester = decodeURI($.getUrlParam('semester'));
    var course = decodeURI($.getUrlParam('course'));
   // alert(decodeURI('2017%E5%B9%B4%E7%A7%8B%E5%AD%A3%E5%AD%A6%E6%9C%9F'));
    alert(semester+"  "+course);
    $.ajax({
        type:"POST",
        url:"/initStandard",
        data:{semester_name:semester,course_name:course},
        dataType: "JSON",
        contentType:"application/x-www-form-urlencoded",
        async: true,
        success:function (data) {
            alert(data.success);
            alert(data.standardNum);

            if(data.success){
                var standards = new Array();
                standards = data.message;
                for(var i = 0; i<standards.length; i++){
                    alert(standards[i]+"  aaaa");
                    initOneStandard(standards[i]);
                }
            }
        },
        error:function () {
            alert("error");
            //      alert(data.message)
        }
    })
})
*/
function myclick(e,itemName){
    var items = document.getElementsByName(itemName);
    for(var i = 0; i < items.length; i++)
    {
		sel("p"+(i+2));
        items[i].checked = e.checked;
    }
}
function sendValueToCenter(){
	var ary = new Array();
    var items = document.getElementsByName("item");
    for(var i = 0; i < items.length; i++)
    {
        ary[i] = document.getElementById("id"+items[i].value).parentNode.parentNode.rowIndex;//��������ѡ�е�����
		moveValueofCenter(items[i].value);//��ֵ
    }
	for(var i = ary.length; i >0; i--)
    {
        var leftTbody = document.getElementById("tab_sendValue1");
        //��߱���tbody
        //�ж�����ary���ֵ�ǲ���������
        if(!isNaN(ary[i-1]))
        {
            leftTbody.deleteRow(ary[i-1]-1);
            //�Ƴ�������ѡ��
        }
    }
    document.getElementById("check_all").checked = false;
	var ary1 = new Array();
    var items1 = document.getElementsByName("item1");
    for(var i = 0; i < items1.length; i++)
    {
            //�ȱ�����ѡ�е����� ���Ƴ�����ѡ��
        ary1[i] = document.getElementById("id"+items1[i].value).parentNode.parentNode.rowIndex;
            //��������ѡ�е�����
        moveValueofCenter(items1[i].value);//��ֵ
    }
	for(var i = ary1.length; i >0; i--)
    {
        var rightTbody = document.getElementById("tab_sendValue3");
        //�ұ߱���tbody
        //�ж�����ary���ֵ�ǲ���������
        if(!isNaN(ary1[i-1]))
        {
            rightTbody.deleteRow(ary1[i-1]-1);
            //�Ƴ�������ѡ��
        }
    }
    document.getElementById("check_all3").checked = false;
}
function moveValueofCenter(op){
	var wbid = document.getElementById("id"+op).value;
	var pvalue = document.getElementById("p"+op).value;
    var rightTbody = document.getElementById("tab_sendValue2");
//�ұ߱���tbody
    var tr = document.createElement("tr");
    var td1 = document.createElement("td");
    td1.innerHTML = "<input type='checkbox' class='check' id='id"+wbid+"' name='item2' value='"+wbid+"'><input type='button' class='butn1' id='p"+ wbid +"' onclick='sel(id)' value='"+pvalue+"'/>";
    tr.appendChild(td1);
    rightTbody.appendChild(tr);
}

function centerSendValueToLeft(){
	var ary = new Array();
    var items = document.getElementsByName("item2");
	
    for(var i = 0; i < items.length; i++)
    {
		ary[i] = document.getElementById("id"+items[i].value).parentNode.parentNode.rowIndex;//��������ѡ�е�����
		moveValueCenterToLeft(items[i].value);//��ֵ
    }
    for(var i = ary.length; i >0; i--)
    {
        var leftTbody = document.getElementById("tab_sendValue2");
        //��߱���tbody
        //�ж�����ary���ֵ�ǲ���������
        if(!isNaN(ary[i-1]))
        {
            leftTbody.deleteRow(ary[i-1]-1);
            //�Ƴ�������ѡ��
        }
    }
    document.getElementById("check_all").checked = false;
}
function moveValueCenterToLeft(op){
	var wbid = document.getElementById("id"+op).value;
	var pvalue = document.getElementById("p"+op).value;
    var leftTbody = document.getElementById("tab_sendValue1");
//��߱���tbody
    var tr = document.createElement("tr");
    var td1 = document.createElement("td");                                                        
    td1.innerHTML = "<input type='checkbox' class='check' id='id"+wbid+"' name='item' value='"+wbid+"'><input type='button' class='butn1' id='p"+ wbid +"'  onclick='sel(id)'      value='"+pvalue+"'/>";
    tr.appendChild(td1);
    leftTbody.appendChild(tr);
}
//�ƶ���߱���ֵ���ұ߱��
function sendValueToRight(){
    var ary = new Array();
    var items = document.getElementsByName("item");
    for(var i = 0; i < items.length; i++)
    {
        if(items[i].checked)
        {
            ary[i] = document.getElementById("id"+items[i].value).parentNode.parentNode.rowIndex;//��������ѡ�е�����
            moveValueOfLeft(items[i].value);//��ֵ
        }
    }
    for(var i = ary.length; i >0; i--)
    {
        var leftTbody = document.getElementById("tab_sendValue1");
        //��߱���tbody
        //�ж�����ary���ֵ�ǲ���������
        if(!isNaN(ary[i-1]))
        {
            leftTbody.deleteRow(ary[i-1]-1);
            //�Ƴ�������ѡ��
        }
    }
    document.getElementById("check_all").checked = false;
//ȫѡ��ѡ����Ϊfalse
}
//�ƶ���߱���ֵ���ұ߱��
function moveValueOfLeft(op){
    var wbid = document.getElementById("id"+op).value;
	var pvalue = document.getElementById("p"+op).value;
    var rightTbody = document.getElementById("tab_sendValue3");
//�ұ߱���tbody
    var tr = document.createElement("tr");
    var td1 = document.createElement("td");
    td1.innerHTML = "<input type='checkbox' class='check' id='id"+wbid+"' name='item1' value='"+wbid+"'><input type='button' class='butn1' id='p"+ wbid +"' onclick='sel(id)' value='"+pvalue+"'/>";
    tr.appendChild(td1);
    rightTbody.appendChild(tr);
}
//�ƶ��ұ߱���ֵ����߱��
function sendValueToLeft(){

    var ary1 = new Array();
    var items = document.getElementsByName("item1");
    for(var i = 0; i < items.length; i++)
    {
        if(items[i].checked)
        {
            //�ȱ�����ѡ�е����� ���Ƴ�����ѡ��
            ary1[i] = document.getElementById("id"+items[i].value).parentNode.parentNode.rowIndex;
            //��������ѡ�е�����
            moveValueOfRight(items[i].value);//��ֵ
        }
    }
    for(var i = ary1.length; i >0; i--)
    {
        var rightTbody = document.getElementById("tab_sendValue3");
        //�ұ߱���tbody
        //�ж�����ary���ֵ�ǲ���������
        if(!isNaN(ary1[i-1]))
        {
            rightTbody.deleteRow(ary1[i-1]-1);
            //�Ƴ�������ѡ��
        }
    }
    document.getElementById("check_all3").checked = false;
//ȫѡ��ѡ����Ϊfalse
}
//�ƶ��ұ߱���ֵ����߱��
function moveValueOfRight(op){
    var wbid = document.getElementById("id"+op).value;
	var pvalue = document.getElementById("p"+op).value;
    var leftTbody = document.getElementById("tab_sendValue1");
//��߱���tbody
    var tr = document.createElement("tr");
    var td1 = document.createElement("td");                                                        
    td1.innerHTML = "<input type='checkbox' class='check' id='id"+wbid+"' name='item' value='"+wbid+"'><input type='button' class='butn1' id='p"+ wbid +"'  onclick='sel(id)'      value='"+pvalue+"'/>";
    tr.appendChild(td1);
    leftTbody.appendChild(tr);
}
function add(){
	var items1 = document.getElementsByName("item2");
	var wbid = items1.length  + 2;
	var wname = document.getElementById("tt").value;
	var insidetbody = document.getElementById("tab_sendValue2");
	var tr1 = document.createElement("tr");
    var td1 = document.createElement("td");
	td1.innerHTML = "<input type='checkbox' class='check' id='id"+wbid+"' name='item2' value='"+wbid+"'><input type='button' class='butn1' id='p"+ wbid +"'  onclick='sel(id)'      value='"+wname+"'/>";
	tr1.appendChild(td1);
	insidetbody.appendChild(tr1);
}
function initOneStandard(standardNmae) {
  //  alert(standardNmae);
    var item = document.getElementsByName("item");
    var wbid = item.length + 2;
    var wname = standardNmae;
    var insidetbody = document.getElementById("tab_sendValue1");
    var tr1 = document.createElement("tr");
    var td1 = document.createElement("td");
    td1.innerHTML = "<input type='checkbox' class='check' id='id"+wbid+"' name='item' value='"+wbid+"'><input type='button' class='butn1' id='p"+ wbid +"'  onclick='sel(id)'      value='"+wname+"'/>";
    tr1.appendChild(td1);
    insidetbody.appendChild(tr1);
}
function del(){
	var ary1 = new Array();
    var items = document.getElementsByName("item2");
    for(var i = 0; i < items.length; i++)
    {
        if(items[i].checked)
        {
            //�ȱ�����ѡ�е����� ���Ƴ�����ѡ��
            ary1[i] = document.getElementById("id"+items[i].value).parentNode.parentNode.rowIndex;
            //��������ѡ�е�����
        }
    }
    for(var i = ary1.length; i >0; i--)
    {
        var rightTbody = document.getElementById("tab_sendValue2");
        //�ұ߱���tbody
        //�ж�����ary���ֵ�ǲ���������
        if(!isNaN(ary1[i-1]))
        {
            rightTbody.deleteRow(ary1[i-1]-1);
            //�Ƴ�������ѡ��
        }
    }
	var item = document.getElementsByName("item2");
	var temp = 2;
	for(var i=0; i<item.length; i++){
		var it = document.getElementById("id"+item[i].value);
		var it1 = document.getElementById("p"+item[i].value);
		it.id="id"+temp;
		it.value = temp;
		it1.id = "p"+temp;
		temp++;
	}
    document.getElementById("check_all3").checked = false;
}
function show(){
	sendValueToCenter();
	document.getElementById('light').style.display='block';
	document.getElementById('fade').style.display='block';
}
function hide(){
	centerSendValueToLeft();
	document.getElementById('light').style.display='none';
	document.getElementById('fade').style.display='none';
}
function sel(op){
	var value = op.toString();
//	alert(value+"  aaa");
	var v = value.substring(1,value.length);
	if (document.getElementById("id"+v).checked){
		document.getElementById("id"+v).checked = false;
		var butn = document.getElementById("p"+v);
		butn.className = "butn1";
	}else{
		document.getElementById("id"+v).checked = true;
		var butn = document.getElementById("p"+v);
		butn.className = "butn2";
	}
}


