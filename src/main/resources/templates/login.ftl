<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>成晟实业 - 登录</title>
    <link rel="stylesheet" href="/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="/css/login.css" media="all"/>
</head>
<body class="beg-login-bg" id="login">
<div class="beg-login-box">
    <header>
        <h1>后台登录</h1>
    </header>
    <div class="beg-login-main">
        <form class="layui-form">
            <div class="layui-form-item">
                <label class="beg-login-icon">
                    <i class="layui-icon">&#xe612;</i>
                </label>
                <input type="text" name="userName" lay-verify="required" autocomplete="off" placeholder="请输入用户名（必填）" class="layui-input">
            </div>

            <div class="layui-form-item">
                <label class="beg-login-icon">
                    <i class="layui-icon">&#xe642;</i>
                </label>
                <input type="password" name="password" lay-verify="required"  placeholder="请输入密码（必填）" autocomplete="off" class="layui-input">
            </div>

            <div class="layui-form-item">
                <div class="beg-pull-left beg-login-remember">
                    <button class="layui-btn" lay-submit  lay-filter="formDemo">登录</button>
                </div>
                <div class="beg-pull-right">
                    <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                </div>
                <div class="beg-clear"></div>
            </div>
        </form>
    </div>
    <footer>
        <p>技术支持单位：XXXXXXXX有限公司</p>
    </footer>
</div>
</body>
<script type="text/javascript" src="/js/jquery.min.js"></script>
<script type="text/javascript" src="/layui/layui.js"></script>
<script type="text/javascript" src="/js/qs.js"></script>
<script type="text/javascript" src="/js/axios.min.js"></script>

<script type="text/javascript">
    layui.use('form', function(){
        var form = layui.form;
        form.on('submit(formDemo)', function(data){
            axios.post('/login', Qs.stringify(data.field))
            .then(function (response) {
                if(response.data.type == 'SUCCESS'){
                    layer.msg('登陆成功');
                    return window.location.href="/home";
                }
                return layer.msg(response.data.message);
            })
            .catch(function (error) {
                console.log(error);
            });
            return false;
        });
    });
</script>
</html>