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
			
        //    ary[i] = document.getElementById("id"+items[i].value).parentNode.parentNode.rowIndex;//��������ѡ�е�����
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
       //     moveValueOfLeft(items[i].value);//��ֵ
        }
    }
	rightTbody = document.getElementById("tab_sendValue3");
	rightTbody.appendChild(tr);
	alert(ary.length);                            
    for(var i = ary.length; i >0; i--)
    {
        var leftTbody = document.getElementById("tab_sendValue1");
        //��߱���tbody
        //�ж�����ary���ֵ�ǲ���������
        if(!isNaN(ary[i-1]))
        {
		//	alert(ary[i-1]);
            leftTbody.deleteRow(ary[i-1]-1);
            //�Ƴ�������ѡ��
        }
    }
 //   document.getElementById("check_all").checked = false;
//ȫѡ��ѡ����Ϊfalse
}
//�ƶ���߱���ֵ���ұ߱��
function moveValueOfLeft(op){
    var wbid = document.getElementById("id"+op).value;
	var pvalue = document.getElementById("btn"+op).value;
    var rightTbody = document.getElementById("tab_sendValue3");
//�ұ߱���tbody
    var tr = document.createElement("tr");
    var td1 = document.createElement("td");   
    td1.innerHTML = "<input type='checkbox' class='check' id='id"+wbid+"' name='item1' value='"+wbid+"'><input type='button' class='btn1' id='pn"+ wbid +"' onclick='sel(id)' value='"+pvalue+"'/>";
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