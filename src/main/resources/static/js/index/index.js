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
        ClasBind();
    })
})

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
     //       alert(addCount);
     //       alert(standardNameList.length);
     //       alert(standardCategoryList.length);
            var trHtml="id = 'th' name = 'th' ";
     //       trHtml += "<td width='4%' rowspan= '2' >组号</td>";
            for(var i =0; i<2; i++){
                trHtml += "<tr>";
                if(i == 0){
                    trHtml += "<td width='4%' rowspan= '2' >组号</td>";
                    trHtml += "<td width='8%' rowspan= '2' >项目名称</td>";
                }
           //     alert(trHtml);
                for(var j=0; j<standardCategoryList.length; j++){
                 //   alert(standardNameList[j]);
                    if(standardCategoryList[j] == 1&&i == 1){
                        var tdHtml = "<td>"+standardNameList[j]+"</td>";
                        trHtml = trHtml + tdHtml;
                        continue;
                    }
                    if(standardNameList[j] == "加分项"&&i == 0){
                        var tdHtml = "<td colspan='"+addCount+"'>"+standardNameList[j]+"</td>";
                        trHtml = trHtml + tdHtml;
                        continue;
                    }
                    if(standardCategoryList[j] == 0&&i == 0){
                        var tdHtml = "<td rowspan='2'>"+standardNameList[j]+"</td>";
                        trHtml = trHtml + tdHtml;
                        continue;
                    }
                }
                trHtml = trHtml+"</tr>";
            }
        //    alert(trHtml);
          //  tb.appendChild(th);
            $("#th").html(trHtml);

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