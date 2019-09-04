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
                        <table class="layui-hide" id="user-table" lay-filter="user-table"></table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
<script type="text/html" id="table-toolbar">
    <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="cat">查看</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
<script type="text/javascript" src="/layui/layui.js"></script>
<script>
    layui.use(['table','form'], function(){

        var table = layui.table,
            form = layui.form;

        table.render({
            elem: '#user-table'
            , url: '/user/tableList'
            , toolbar: 'default'
            , title: '用户管理'
            , cols:[[
                {type: 'checkbox', fixed: 'left'}
                ,{field: 'nameCn', title: '姓名',align:'center'}
                ,{field: 'mobile', title: '手机号', align:'center'}
                ,{field: 'tel', title: '电话', align:'center'}
                ,{field: 'userLevel', title: '等级', align:'center',
                    templet: function(d){
                        if(d.userLevel == 'USER_LEVEL_STAFF'){
                            return '普通员工';
                        }else if(d.userLevel == 'USER_LEVEL_ADMIN'){
                            return '管理员';
                        }else{
                            return '未知状态';
                        }

                    }}
                ,{field: 'createTime', title: '创建时间', align:'center',
                    templet: function(d){
                        return layui.util.toDateString(d.createTime);
                    }}
                ,{field: 'remark', title: '备注', align:'center'}
                ,{fixed: 'right',width:150, title: '操作',align:'center', toolbar: '#table-toolbar'}
            ]]
            , page: true
        });


        //监听事件
        table.on('toolbar(user-table)', function(obj){
            switch(obj.event){
                case 'add':
                    layer.msg('添加');
                    layer.open({
                        type: 2,
                        title: '新增用户',
                        shadeClose: true,
                        shade: false,
                        maxmin: true, //开启最大化最小化按钮
                        area: ['60%', '80%'],
                        content: '/home/user/edit'
                    });
                    break;
                case 'delete':
                    layer.msg('删除');
                    break;
                case 'update':
                    layer.msg('编辑');
                    break;
            };
        });
    });
</script>
</html>