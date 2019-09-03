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
                ,{field: 'orderNo', title: '订单编号', fixed: 'left',align:'center'}
                ,{field: 'customerName', title: '客户', fixed: 'left',align:'center'}
                ,{field: 'customerTel', title: '手机号', fixed: 'left',align:'center'}
                ,{field: 'getGoodsDate', title: '提/送货日期', fixed: 'left',align:'center',
                    templet: function(d){
                        return layui.util.toDateString(d.getGoodsDate);
                    }
                 }
                ,{field: 'totalNumber', width: 80, title: '总数量', fixed: 'left',align:'center'}
                ,{field: 'totalWeight', width: 95, title: '总重量-吨', fixed: 'left',align:'center'}
                ,{field: 'totalAmount', width: 80, title: '总金额', fixed: 'left',align:'center'}
                ,{field: 'payStatus', title: '支付状态', fixed: 'left',align:'center',
                    templet: function(d){
                        if(d.payStatus == 'NOT_PAY'){
                            return '未付款';
                        }else if(d.payStatus == 'PAY_SOME'){
                            return '部分支付';
                        }else if(d.payStatus == 'PAY_ALL'){
                            return '已结清';
                        }else{
                            return '未知状态';
                        }

                    }
                }
                ,{field: 'paidAmount', width: 100, title: '已支付金额', fixed: 'left',align:'center'}
                ,{field: 'unpaidAmount',width: 100,  title: '未支付金额', fixed: 'left',align:'center'}
                ,{field: 'createTime', title: '创建时间', fixed: 'left',align:'center',
                    templet: function(d){
                        return layui.util.toDateString(d.createTime);
                    }
                }
                ,{field: 'remark', title: '订单备注', fixed: 'left',align:'center'}
                ,{fixed: 'right', title: '操作',align:'center', toolbar: '#table-toolbar'}
            ]]
            , page: true
        });

        //监听事件
        table.on('toolbar(order-table)', function(obj){
            var checkStatus = table.checkStatus(obj.config.id);
            switch(obj.event){
                case 'add':
                    layer.msg('添加');
                    layer.open({
                        type: 2,
                        title: '新建订单',
                        shadeClose: true,
                        shade: false,
                        maxmin: true, //开启最大化最小化按钮
                        area: ['1200px', '600px'],
                        content: '/home/order/edit'
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