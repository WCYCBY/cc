<!DOCTYPE html>
<html lang="en">
<head>
    <title></title>
    <meta charset="utf-8" />

    <meta name="description" content="overview & stats" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <!-- basic styles -->
    <link href="/css/bootstrap.min.css" rel="stylesheet" />
    <link href="/css/bootstrap-responsive.min.css" rel="stylesheet" />
    <link rel="stylesheet" href="/css/font-awesome.min.css" />


    <!-- page specific plugin styles -->
    <!-- 下拉框-->
    <link rel="stylesheet" href="/css/chosen.css" />

    <!-- ace styles -->
    <link rel="stylesheet" href="/css/ace.min.css" />
    <link rel="stylesheet" href="/css/ace-responsive.min.css" />
    <link rel="stylesheet" href="/css/ace-skins.min.css" />


    <script type="text/javascript" src="/js/jquery-1.11.1.js"></script>

    <link rel="stylesheet" href="/css/datepicker.css" /><!-- 日期框 -->
    <!--引入弹窗组件start-->
    <script type="text/javascript" src="/plugins/attention/zDialog/zDrag.js"></script>
    <script type="text/javascript" src="/plugins/attention/zDialog/zDialog.js"></script>
    <!--引入弹窗组件end-->
    <script type="text/javascript" src="/js/jquery.tips.js"></script>

    <script src="/js/bootstrap.min.js"></script>
    <script src="/js/bootbox.min.js"></script>

    <script type="text/javascript">

        //保存
        function save(){
            if($("#password").val()==""){

                $("#password").tips({
                    side:3,
                    msg:'输入密码',
                    bg:'#AE81FF',
                    time:2
                });

                $("#password").focus();
                return false;
            }

            if( $("#password").val().length < 6){

                $("#password").tips({
                    side:3,
                    msg:'密码长度至少为6位',
                    bg:'#AE81FF',
                    time:2
                });

                $("#password").focus();
                return false;
            }

            if($("#password").val()!=$("#chkpwd").val()){

                $("#chkpwd").tips({
                    side:3,
                    msg:'两次密码不相同',
                    bg:'#AE81FF',
                    time:3
                });

                $("#chkpwd").focus();
                return false;
            }

            var url = "/auth/user/updatePassword";
                $("#zhongxin").hide();
                $("#zhongxin2").show();
                //ajax提交
            $.post(
                url,
                    {
                        "userId":$("#user_id").val(),
                        "passwd":$("#password").val()
                    },
                    function (data) {
                        if (data.flag) {
                            bootbox.alert("修改密码成功！", function () {
                                    window.location.href = "/auth/login/logout";
                            });
                        } else {
                            bootbox.alert("修改密码失败！",function () {
                                top.Dialog.close();
                            });
                        }
                    }
            )
        }
    </script>
</head>
<body>
<form  name="userForm" id="userForm" method="post" >
    <input type="hidden" name="userId" id="user_id" value="${loginUser}"/>
    <div id="zhongxin">
        <table >
            <tr>
            </tr>
            <tr>
                <td><input type="password" name="PASSWORD" id="password"  maxlength="32" placeholder="输入长度大于6位的密码" title="密码"/></td>
            </tr>
            <tr>
                <td><input type="password" name="chkpwd" id="chkpwd"  maxlength="32" placeholder="确认密码" title="确认密码" /></td>
            </tr>

            <tr>
                <td style="text-align: center;">
                    <a class="btn btn-mini btn-primary" onclick="save();">保存</a>
                    <a class="btn btn-mini btn-danger" onclick="top.Dialog.close();">取消</a>
                </td>
            </tr>
        </table>
    </div>

    <div id="zhongxin2" class="center" style="display:none"><br/><br/><br/><br/><img style="width: 100px;height: 30px" src="/img/jiazai.gif" /><br/><h4 class="lighter block green"></h4></div>

</form>
</body>
</html>