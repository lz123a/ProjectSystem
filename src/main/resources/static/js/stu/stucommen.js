function subCon(x) {
    var constr = document.getElementById("stucon").text;
    $.ajax({
        type:"POST",
        url:"/subCon",
        data:{conStr:constr,val:x},
        dataType: "JSON",
        async: true,
        success:function (data) {

            //      alert(data.message);
            //      var url = encodeURI("choice?semester="+semester+"&course="+course);
            //      var u = encodeURI(url);
            if(data.success){
                alert(data.message);
                window.location.reload();
            }

        },
        error:function (data) {
            alert("error");
            //      alert(data.message)
        }
    })
}