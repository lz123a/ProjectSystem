var li = new Array();


function changeStandard1() {
    var x = document.getElementById("semester");
    var semester = x.options[x.selectedIndex].text;
    var y = document.getElementById("course");
    var course = y.options[y.selectedIndex].text;
    $.ajax({
        type:"POST",
        url:"/changeStandard",
        data:{semester_name:semester,course_name:course},
        dataType: "JSON",
        async: true,
        success:function (data) {

      //      alert(data.message);
      //      var url = encodeURI("choice?semester="+semester+"&course="+course);
      //      var u = encodeURI(url);
            var u = "choice?semester="+semester+"&course="+course;
            $(location).attr('href', u);
        },
        error:function (data) {
            alert("error");
            //      alert(data.message)
        }
    })
}

function changeGroup(){
    var x = document.getElementById("semester");
    var semester = x.options[x.selectedIndex].text;
    var y = document.getElementById("course");
    var course = y.options[y.selectedIndex].text;
    var z = document.getElementById("cla");
    var clas = z.options[z.selectedIndex].text;
    var u = "group?semester="+semester+"&course="+course+"&clas="+clas;
    $(location).attr('href', u);
}



$(function(){
	$("#semester").change(function (){
		GradeBind();
	})
})

$(function () {
	$("#grade").change(function () {
		CourseBind();
    })
})

$(function () {
    $("#course").change(function () {
        var z = document.getElementById("course");
        var course = z.options[z.selectedIndex].text;
        if(course=="点击添加新课程"){
            $('#mymodal').modal();
        }else{
            ClasBind();
        }

    })
})

function addCourse(){
    var x = document.getElementById("grade");
    var grade = x.options[x.selectedIndex].text;
    var y = document.getElementById("semester");
    var semester = y.options[y.selectedIndex].text;
    var course = $('#cou').val();
    $.ajax({
        type: 'post',
        url: "/addCourse",
        data:{semester:semester,grade:grade,courseName:course},
        dataType: "JSON",
        async:true,
        success:function (data) {
            if(data.success==true){
                alert("添加成功");
            }
        }
    })

}

$(function () {
    $("#cla").change(function () {
        standardBind();
    })
})

function standardBind() {
    var tb = document.getElementById("tb");
    var th = document.createElement("th");
    var tr1 = document.createElement("tr");
    var tr2 = document.createElement("tr");
    var y = document.getElementById("semester");
    var semester = y.options[y.selectedIndex].text;
    var z = document.getElementById("course");
    var course = z.options[z.selectedIndex].text;
    var zz = document.getElementById("cla");
    var clas = zz.options[zz.selectedIndex].text;
    $.ajax({
        type: 'POST',
        url: "/standardTableHead",
        data:{semester_name:semester,course_name:course},
        dataType: "JSON",
        async:true,
        success:function (data) {
            var addCount = data.addCount;
            var standardNameList = data.standardNameList;
            var standardCategoryList = data.standardCategoryList;
            var trHtml="id = 'th' name = 'th' class='tdd' ";
     //       trHtml += "<td width='4%' rowspan= '2' >组号</td>";
            for(var i =0; i<2; i++){
                trHtml += "<tr>";
                if(i == 0){
                    trHtml += "<td align='center' class='tdd' width='4%' rowspan= '2' >组号</td>";
                    trHtml += "<td align='center' class='tdd' width='8%' rowspan= '2' >项目名称</td>";
                    trHtml += "<td align='center' class='tdd'  rowspan= '2' >组员名单</td>";
                    li.push('组员名单');
                }

                for(var j=0; j<standardCategoryList.length; j++){

                    if(standardCategoryList[j] == 1&&i == 1){
                        var tdHtml = "<td align='center' class='tdd'>"+standardNameList[j]+"</td>";
                        trHtml = trHtml + tdHtml;
                        li.push(standardNameList[j]);
                        continue;
                    }
                    if(standardNameList[j] == "加分项"&&i == 0){
                        var tdHtml1 = "<td align='center' class='tdd' rowspan='2'>项目个人总分</td>";
                        trHtml = trHtml + tdHtml1;
                        li.push("项目个人总分");
                        var tdHtml = "<td align='center'  class='tdd' colspan='"+addCount+"'>"+standardNameList[j]+"</td>";
                        trHtml = trHtml + tdHtml;
                        li.push(standardNameList[j]);
                        continue;
                    }
                    if(standardCategoryList[j] == 0&&i == 0){
                        var tdHtml = "<td align='center' class='tdd' rowspan='2'>"+standardNameList[j]+"</td>";
                        if(standardNameList[j]=='开题'||standardNameList[j]=='中检'||standardNameList[j]=='末检'||standardNameList[j]=='周报'||standardNameList[j]=='报告'||standardNameList[j]=='版本控制工具'){
                            var x = standardNameList[j]+"个人得分";
                            li.push(standardNameList[j]);
                            li.push(x);
                            var tdHtml1 = "<td align='center' class='tdd' rowspan='2'>"+x+"</td>";
                            trHtml = trHtml + tdHtml + tdHtml1;
                          //  alert(trHtml);
                        }else{
                            li.push(standardNameList[j])
                            trHtml = trHtml + tdHtml;
                          //  alert(trHtml);
                        }

                        continue;
                    }

                }
                if(i==0){
                    var tdHtml1 = "<td align='center' class='tdd' rowspan='2'>个人总分</td>";
                    var tdHtml2 = "<td align='center' class='tdd' rowspan='2'>教务处成绩</td>";
                    trHtml = trHtml + tdHtml1 + tdHtml2;

                }
                trHtml = trHtml+"</tr>";
            }
            $("#th").html(trHtml);
            li.push("个人总分");
            li.push("教务处成绩");
            getScore();
        }
    })

}

function getScore() {
    var y = document.getElementById("semester");
    var semester = y.options[y.selectedIndex].text;
    var z = document.getElementById("course");
    var course = z.options[z.selectedIndex].text;
    var zz = document.getElementById("cla");
    var clas = zz.options[zz.selectedIndex].text;
    $.ajax({
        type: 'POST',
        url: "/getScore",
        data:{semester:semester,course:course,clas:clas},
        dataType: "JSON",
        async:true,
        success:function (data) {

            alert(data.success);
            var d = data.ans;
            var k = 1;
            var tbodyhtml = "id = 'tbody' class='table-hover' name='tbody  ";
            for(var i=0; i<d.length; i++){
                var x = {};
                x = d[i];
                var teamSize = x['teamSize'];
           //     alert(i+"  "+j+"  "+teamSize+"  aaav");
                for(var j=i+1; j<i+teamSize+1; j++){
                    var trHtml="<tr>  class='tdd' align='center' ";
                    if(j==i+1){

                        var tdHtml = "<td align='center' class='tdd' rowspan='"+teamSize+"'>"+(k)+"</td>";
                        tdHtml += "<td align='center' class='tdd' rowspan='"+teamSize+"'>"+x['项目名称']+"</td>";
                        trHtml += tdHtml;
                    }
                    var xx = {};
                    xx = d[j];
            //        alert(xx['开题']+"  aaa "+j);
                    var tdHtml1 = "";
                    for(var kk=0; kk<li.length; kk++){
                        if(li[kk]=='加分项')
                            continue;
                        if(li[kk]=='开题'||li[kk]=='中检'||li[kk]=='报告'||li[kk]=='周报'||li[kk]=='末检'||li[kk]=='版本控制工具'){
                            if(j==i+1){
                                tdHtml1 += "<td align='center' class='tdd' rowspan='"+teamSize+"'>"+xx[li[kk]]+"</td>";
                            }
                            continue;
                        }
                        tdHtml1 += "<td align='center' class='tdd' >"+xx[li[kk]]+"</td>";
                    }
                    trHtml = trHtml + tdHtml1 + "</tr>";
                    tbodyhtml += trHtml;
                }
          //      alert(i+"  "+j +" ij");
                i = j-1;

               /* for(key in x){
                    alert(key + x[key]);
                }*/
    /*            for(var j=0; j<d[i].length; j++){
                    alert(d[i][j]);
                }*/
                k++;
            }
            $('#tbody').html(tbodyhtml);
        }
    })
}

function ClasBind() {
    $("#cla").html("");
    var y = document.getElementById("semester");
    var semester = y.options[y.selectedIndex].text;
    var z = document.getElementById("course");
    var course = z.options[z.selectedIndex].text;
    var str = "<option>===请选择===</option>";
    $.ajax({
        type: 'post',
        url: "/clasPost",
        data:{semester_name:semester,course_name:course},
        dataType: "JSON",
        async:true,
        success:function (data) {
            var clas = new Array();
            clas = data.message;
      //      alert(clas.length);
            for(var i=0; i<clas.length; i++){
                str +=" <option>   "+(clas[i])+" </option> ";
            }
            $("#cla").append(str);
        }
    })
}

function CourseBind() {
    $("#course").html("");
    var x = document.getElementById("grade");
    var grade = x.options[x.selectedIndex].text;
    var y = document.getElementById("semester");
    var semester = y.options[y.selectedIndex].text;
    var str = "<option>===请选择===</option>";
    $.ajax({
        type: 'post',
        url: "/coursePost",
        data:{semester_name:semester,grade_name:grade},
        dataType:"JSON",
        async:true,
        success:function (data) {
            var course = new Array();
            course = data.course;
            for(var i=0; i<course.length; i++){
                str +=" <option>   "+(course[i])+" </option> ";
            }
            str += " <option>   "+"点击添加新课程"+" </option> ";
            $("#course").append(str);
        }
    })
}



function TermBind() {
	$("#term").html("");
	var str = "<option>===请选择===</option>";
}

function GradeBind(){
	$("#grade").html("")
	var str = "<option>===请选择===</option>";
	var x = document.getElementById("semester");
	var t = x.options[x.selectedIndex].text;
	var y1 = t.substring(0,4);
	var y2 = t.substring(5,6);
	var year = parseInt(y1);
	if(y2=="春"){
		str +=" <option>   "+(year-3)+"级 </option> ";
		str += "<option>   "+(year-2)+"级 </option>";
		str += "<option>   "+(year-1)+"级 </option>";
	}else if (y2=="秋"){
		str += "<option>   "+(year-2)+"级 </option>";
		str += "<option>   "+(year-1)+"级 </option>";
		str += "<option>   "+(year-0)+"级 </option>";
	}
	$("#grade").append(str);

}


//下拉框初始数据
//var data = [{ "id": 1, "text": "曾令祥" }, { "id": 2, "text": "吴迪" }, { "id": 3, "text": "潘晔" }, { "id": 4, "text": "李恩书" }, { "id": 5, "text": "张雨桐" }, { "id": 6, "text": "阿大撒" }, { "id": 7, "text": "饿强强" }, { "id": 8, "text": "让人" }, { "id": 9, "text": "请问日期" }, { "id": 10, "text": "张全球" }, { "id": 11, "text": "阿方法" }];
var data;
//右侧栏数据集合，用于判重
var selectMember = [];

function getData1(){
    data = new Array();
    var zz = document.getElementById("cla");
    var clas = zz.options[zz.selectedIndex].text;
    $.ajax({
        type: 'post',
        url: "/getData",
        data:{clas:clas},
        dataType:"JSON",
        async:false,
        success:function (data1) {
        //    alert(data.message);
        //    $('#tb').html(data);
            var name = new Array();
            name = data1.message;
            for(var i=0; i<name.length; i++){
                data[i] = {};
                data[i]['id']=(i+1);
                data[i]['text']=name[i];
            }
            initData(data);

        },
        error: function (data1) {
            alert("Error"); }

    })
}

//初始化页面
/*$(document).ready(function () {
    //初始化数据
    initData(data);
})*/
/**
 * 初始化数据，给左侧下拉框绑定下拉选项
 */
function initData(data) {
    var $languageRemove = document.querySelector('#languageRemove');
    data.forEach(function (item, index) {
        var objOption = document.createElement("option");
        objOption.text = item.text;
        objOption.value = item.id;
        objOption.disabled = !!item.IsDisable;
        $languageRemove.appendChild(objOption);
    });
}
/**
 * 选中项添加到右边，左侧栏数据不删除，并且不能添加重复数据到右边
 */
function copyOption(obj, type) {
    var eleA = "";
    var eleB = "";
    if (type == "add") {//从左侧向右侧添加数据
        eleA = obj.parentNode.previousElementSibling.firstElementChild;
        eleB = obj.parentNode.nextElementSibling.firstElementChild;
    } else {//删除右侧数据
        eleA = obj.parentNode.nextElementSibling.firstElementChild;
        eleB = obj.parentNode.previousElementSibling.firstElementChild;
    }
    for (var i = 0; i < eleA.options.length; i++) {
        if (eleA.options[i].selected) {
            var eSelected = eleA.options[i];
            if (type == "add") {
                if (selectMember.indexOf(eSelected.text) < 0) {//判断左边选中项在右侧群成员列表中是否存在
                    eleB.options.add(new Option(eSelected.text, eSelected.value));//将选中项添加到右边
                    selectMember.push(eSelected.text); //将选中值追加到selectMember[]中
                } else {
                    alert("该项在右侧列表已经存在");
                }
            } else {
                eleA.remove(i);//移除选中项
                i = i - 1;//每移除一项，下拉选项的索引值会减1
                selectMember.splice($.inArray(eSelected.text, selectMember), 1); //将选中值从selectMember[]中移除
            }
        }
    }
}
/**
 * 选中项左右移动
 */
function moveOption(eleA, eleB) {
    for (var i = 0; i < eleA.options.length; i++) {
        if (eleA.options[i].selected) {
            var eSelected = eleA.options[i];
            eleB.options.add(new Option(eSelected.text, eSelected.value));//将选中项添加到右边
            eleA.remove(i);//移除选中项
            i = i - 1;//每移除一项，下拉选项的索引值会减1
        }
    }
}

function shoumodal2() {
    getData1();
    $('#mymoda2').modal();
}

function shoumodal3() {
 //   $('#teamscorelist').selectpicker('refresh');
    var y = document.getElementById("semester");
    var semester = y.options[y.selectedIndex].text;
    var z = document.getElementById("course");
    var course = z.options[z.selectedIndex].text;
    var zz = document.getElementById("cla");
    var clas = zz.options[zz.selectedIndex].text;$('#mymoda3').modal();
    $.ajax({
        type: 'post',
        url: "/teamPost",
        data:{semester:semester,course:course,clas:clas},
        dataType:"JSON",
        async:true,
        success:function (data) {
            var teamName = new Array();
            teamName = data.message;
            var str = "<option>请选择</option>";
            for(var i=0; i<teamName.length; i++){
                str += "<option>"+teamName[i]+"</option>";
            }
       //     alert(str);
            $('#teamlist').html("");
            $('#teamlist').append(str);

        }

    })
}


$(function(){
    $("#teamlist").change(function (){
        getTeamScore();
    })
})

function getTeamScore() {
    var x = document.getElementById("teamlist");
    var name = x.options[x.selectedIndex].text;

    $.ajax({
        type: 'post',
        url: "/teamScorePost",
        data:{name:name},
        dataType:"JSON",
        async:true,
        success:function (data) {
            var canGetScore = new Array();
            canGetScore = data.message;
            var str = "<option>请选择</option>";
            for(var i=0; i<canGetScore.length; i++){
                str += "<option>"+canGetScore[i]+"</option>";
            }
       //     alert(str);
            $('#teamscorelist').html("");
            $('#teamscorelist').append(str);
        }

    })
}

function changeTeamScore() {
    var x = document.getElementById("teamlist");
    var name = x.options[x.selectedIndex].text;
    var y = document.getElementById("teamscorelist");
    var item = y.options[y.selectedIndex].text;
    var score = $('#newscore').val();
    var z;
    if(item=="项目名称"){
        z = score;
        score = 1;
    }
    alert(name+" "+item+" "+score);
    $.ajax({
        type: 'post',
        url: "/changeScore",
        data:{name:name,item:item,score:score,newName:z},
        dataType:"JSON",
        async:false,
        success:function (data) {
            alert(data.message);
            $('#tb').html(data);
        },
        error: function (data) {
             alert("Error"); }

    })
}

