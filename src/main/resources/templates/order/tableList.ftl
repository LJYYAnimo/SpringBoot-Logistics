<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>成晟实业 - 订单</title>
    <link rel="stylesheet" href="/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="/css/app.css" media="all">
</head>
<body>
    <div class="layui-fluid">
        <div class="layui-row layui-col-space15">
            <div class="layui-col-md12">
                <div class="layui-card">
                    <div class="layui-card-header">* 2M，支持bmp/png/jpeg/jpg/gif格式 * CropImage（传图自动裁剪导致的隐藏图片）</div>
                    <div class="layui-card-body">
                        <table class="layui-hide" id="order-table" lay-filter="order-table"></table>
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
            elem: '#order-table'
            , url: '/order/tableList'
            , toolbar: 'default'
            , title: '订单系统'
            , cols:[[
                {type: 'checkbox', fixed: 'left'}
                ,{field: 'customerName', title: '客户', fixed: 'left',align:'center'}
                ,{field: 'customerTel', title: '手机号', fixed: 'left',align:'center'}
                ,{fixed: 'right', title: '操作',align:'center', toolbar: '#table-toolbar'}
            ]]
            , page: true
        });
    });
</script>
</html>