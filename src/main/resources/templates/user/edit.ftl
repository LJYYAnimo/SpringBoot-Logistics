<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>成晟实业 - 用户</title>
    <link rel="stylesheet" href="/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="/css/app.css" media="all">
</head>
<body>
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-body">
                    <form class="layui-form">
                        <div class="layui-container">
                            <div class="layui-row">
                                <div class="layui-col-md4">
                                    <label class="layui-form-label">登录名</label>
                                    <div class="layui-input-block">
                                        <input type="text" placeholder="请输入登录账号" name="userName" lay-verify="required" class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-col-md4">
                                    <label class="layui-form-label">密码</label>
                                    <div class="layui-input-block">
                                        <input type="text" placeholder="请输入密码" lay-verify="required" name="password" class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-col-md4">
                                    <label class="layui-form-label">姓名</label>
                                    <div class="layui-input-block">
                                        <input type="text" placeholder="请输入姓名" lay-verify="required" name="nameCn" class="layui-input">
                                    </div>
                                </div>
                            </div>

                            <div class="layui-row" style="margin: 10px 0px">
                                <div class="layui-col-md4">
                                    <label class="layui-form-label">手机号</label>
                                    <div class="layui-input-block">
                                        <input type="text" name="mobile" lay-verify="required" class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-col-md4">
                                    <label class="layui-form-label">电话号</label>
                                    <div class="layui-input-block">
                                        <input type="text" name="tel" class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-col-md4">
                                    <label class="layui-form-label">等级</label>
                                    <div class="layui-input-block">
                                        <input type="radio" name="userLevel" lay-filter="userLevel" value="USER_LEVEL_STAFF" title="普通员工" checked="">
                                        <input type="radio" name="userLevel" lay-filter="userLevel" value="USER_LEVEL_ADMIN" title="管理员">
                                    </div>
                                </div>

                            </div>

                            <div class="layui-row" style="margin: 10px 0px">
                                <div class="layui-col-md12">
                                    <label class="layui-form-label">备注</label>
                                    <div class="layui-input-block">
                                        <textarea name="remark" placeholder="请输入备注" class="layui-textarea"></textarea>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="layui-container">
                            <div class="layui-row">
                                <div class="layui-col-md6">
                                </div>
                                <div class="layui-col-md6">
                                </div>
                            </div>
                        </div>
                        <div class="layui-form-item" style="float: right;margin-top: 10px;">
                            <div class="layui-input-block">
                                <button class="layui-btn" lay-submit lay-filter="userAddForm">新增</button>
                            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript" src="/js/jquery.min.js"></script>
<script type="text/javascript" src="/layui/layui.js"></script>
<script type="text/javascript" src="/js/qs.js"></script>
<script type="text/javascript" src="/js/axios.min.js"></script>
<script>
    layui.use(['laydate', 'form', 'jquery'], function () {
        var laydate = layui.laydate,
                form = layui.form,
                $ = layui.$;

        //监听提交
        form.on('submit(userAddForm)', function (data) {

            axios.post('/user/save', Qs.stringify(data.field))
                .then(function (response) {
                    if(response.data.type == 'SUCCESS'){
                        layer.msg('添加成功');
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