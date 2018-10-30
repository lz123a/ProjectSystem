

$(document).ready(function() {
    $('#defaultForm').bootstrapValidator({
        //默认失去焦点时验证
        //live: 'disabled',
        //通用错误信息
        message: '该内容不合法',
        //图标-对号 X
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        //字段验证
        fields: {
            uid: {
                validators: {
                    notEmpty: {
                        message: '用户名不能为空'
                    },
                    stringLength: {
                        min: 6,
                        max: 11,
                        message: '长度在6-11之间'
                    },
                    regexp: {
                        regexp: /^[A-Za-z0-9]+$/,
                        message: '数字 字母 下划线组成'
                    },
                    different: {
                        field: 'password',
                        message: '不能与name=password的组件值相同'
                    }
                }
            },

            psword: {
                //		alert(1111);
                validators: {
                    notEmpty: {
                        message: '密码不能为空'
                    },
                    stringLength: {
                        min: 6,
                        max: 16,
                        message: '长度在6-16之间'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9_\.]+$/,
                        message: '数字 字母 下划线组成'
                    }
                }
            },
            yzm: {
                validators: {
                    notEmpty: {
                        message: '不能为空'
                    },
                    stringLength: {
                        min: 0,
                        max: 4,
                        message: '验证码错误'
                    },
                    regexp: {
                        regexp: /^[A-Za-z0-9]+$/,
                        message: '数字 字母 下划线组成'
                    }

                }
            }
        }
    });
});
$("#signup").click(function () {
    alert("qweqwe");
});



var code ; //在全局定义验证码

function createCode(){
    code = "";
    var codeLength = 4;//验证码的长度
    var checkCode = document.getElementById("code");
    var random = new Array(0,1,2,3,4,5,6,7,8,9,'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R',
        'S','T','U','V','W','X','Y','Z');//随机数
    for(var i = 0; i < codeLength; i++) {//循环操作
        var index = Math.floor(Math.random()*36);//取得随机数的索引（0~35）  
        code += random[index];//根据索引取得随机数加到code上  
    }
    checkCode.value = code;//把code值赋给验证码
}
//校验验证码
function valid(){
    var inputCode = document.getElementById("input").value.toUpperCase(); //取得输入的验证码并转化为大写
    if(inputCode.length <= 0) { //若输入的验证码长度为0
        $(location).attr('href', 'login');
        alert("请输入验证码！")

    }else if(inputCode != code ) { //若输入的验证码与产生的验证码不一致时
        createCode();//刷新验证码  
        document.getElementById("input").value = "";//清空文本框
        alert("验证码填写错误！")

    }else { //输入正确时

        var uid = $("input[name='uid']").val();
        var psword = $("input[name='psword']").val();
        var idenity = $("input[name='person']:checked").val();
        $.ajax({
            type:"POST",
            url:'/loginPost',
            data:{"uid":uid,"psword":psword,"idenity":idenity},
            async:true,
            success: function (data) {
                if(data.success==true){
                    if(idenity=="stu"){
                        $(location).attr('href', 'stu');
                    }else {
                        $(location).attr('href', 'index');
                    }
                }else{
                    $(location).attr('href', 'login');
                    alert(data.message);
                }
            },
            error: function (data) {
                $(location).attr('href', 'login'); alert("Error"); }
        },'json');
    }
}// JavaScript Document