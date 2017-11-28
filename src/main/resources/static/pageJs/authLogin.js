/**
 * Created by qrc on 2017/2/15.
 */
$(function () {
    var bodyheight = $(document).height();
    var headerheight = $('.header').height();
    var footerhright = $('.footer').outerHeight(true);
    var loheight = bodyheight - headerheight - footerhright
    $('body').css('height', bodyheight);
    $('.loginBg').css('height', loheight);

    $(document).keydown(function (e) {

        if (e.keyCode == 13) {
            doLogin();
        }
    })

    $("#login").click(function () {
        doLogin();
    })
})


/**
 *应为用了iframe，当session超时的时候，而我们正好操作的是iframe的子页面的时候，子页面就会变成登录页。
 *造成页面嵌套混乱，所以加载本页面的时候验证是否为顶级页面
 */
window.onload = function () {
    var win = window.dialogArguments;
    $("#textfield").focus();
    if (win != null) {
        window.close();
        win.parent.location.href = self.location;
    } else {
        if (top.location != self.location) {
            top.location = self.location;
        }
    }

    $("#textfield").val("");
    $("#textfield2").val("");
}


function doLogin() {
    var userName = $("#textfield").val().trim();
    var userPassword = $("#textfield2").val().trim();
    if (check(userName, userPassword)) {

        var url = "/auth/login/loginCheck.html";
        $.ajax({
            async: false, //请勿改成异步，下面有些程序依赖此请数据
            type: "POST",
            data: {username: userName, password: userPassword},
            url: url,
            dataType: 'json',
            success: function (json) {

                if (json.error == '0000') {
                    //登录成功
                    window.location.href = "/auth/background/index.html";
                } else {
                    //登录失败
                    $("#checkSpanT").html(json.error);
                    $("#checkSpan").css("display", "block");
                }
            }
        });
    }
    return;
}

function check(userName, userPassword) {
    //校验
    if (userName == '') {
        return outText("用户名不能为空!");
    }
    if (userName.length < 5) {
        return outText("用户名不能小于5位!");
    }
    if (userName.length > 18) {
        return outText("用户名不能大于18位!");
    }

    var regex = /^[a-zA-Z]{1}[0-9a-zA-Z_]{1,}$/;
    if (!regex.test(userName)) {
        return outText("用户名格式错误！");
    }

    if (userPassword == '') {
        return outText("密码不能为空!");
    }
    if (userPassword.length < 5) {
        return outText("密码不能小于5位!");
    }
    if (userPassword.length > 18) {
        return outText("密码不能大于18位!");
    }
    return true;
}

function outText(errorMsg) {
    $("#checkSpanT").html(errorMsg);
    $("#checkSpan").css("display", "block");
    return false;
}
