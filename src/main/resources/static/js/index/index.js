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
                // for(keyk in x){
                //     alert(keyk + " "+ x[keyk])
                // }

                var teamSize = x['teamSize'];
                alert(i+"  "+j+"  "+teamSize+"  aaav");
                for(var j=i+1; j<i+teamSize+1; j++){
                    var trHtml="<tr>  class='tdd' align='center' ";
                    if(j==i+1){

                        var tdHtml = "<td align='center' class='tdd' rowspan='"+teamSize+"'>"+(k)+"</td>";
                        tdHtml += "<td align='center' class='tdd' rowspan='"+teamSize+"'>"+x['项目名称']+"</td>";
                        trHtml += tdHtml;
                    }

                    var xx = {};
                    xx = d[j];
                 //   for(key in xx){
                 //       alert(key + " "+ xx[key])
                  //  }
                    alert(xx['开题']+"  aaa "+j);
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
                alert(i+"  "+j +" ij");
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