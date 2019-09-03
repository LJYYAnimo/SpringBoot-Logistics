<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>成晟实业 - 订单页</title>
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
                        <div class="layui-form-item">
                            <label class="layui-form-label">购货单位</label>
                            <div class="layui-input-block">
                                <input type="text" name="customerName" lay-verify="required" autocomplete="off" placeholder="请输入购货单位（必填）" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">发票类型</label>
                            <div class="layui-input-block">
                                <input type="text" name="invoiceType" lay-verify="required"  placeholder="请输入发票类型（必填）" class="layui-input">
                            </div>
                        </div>

                        <div class="layui-form-item">
                            <label class="layui-form-label">提/送货日期</label>
                            <div class="layui-input-block">
                                <input type="text" class="layui-input" id="getGoodsDate">
                            </div>
                        </div>

                        <table class="layui-table">
                            <#--设置列的宽度
                            <colgroup>
                                <col width="150">
                                <col width="150">
                                <col width="200">
                                <col>
                            </colgroup>-->
                            <tr>
                                <th style="text-align: center">操作</th>
                                <th style="text-align: center">货品名称</th>
                                <th style="text-align: center">规格型号</th>
                                <th style="text-align: center">数量</th>
                                <th style="text-align: center">重量-吨</th>
                                <th style="text-align: center">计价方式</th>
                                <th style="text-align: center">单价</th>
                                <th style="text-align: center">金额</th>
                                <th style="text-align: center">备注</th>
                            </tr>
                            <tr>
                                <td><i class="layui-icon">&#xe654;</i>&nbsp;&nbsp;<i class="layui-icon">&#xe640;</i></td>
                                <td><input type="text" name="goodsName" class="layui-input"></td>
                                <td><input type="text" name="goodsDetailInfo" class="layui-input"></td>
                                <td><input type="text" name="number" class="layui-input"></td>
                                <td><input type="text" name="weight" class="layui-input"></td>
                                <td><input type="text" name="pricingMethod" class="layui-input"></td>
                                <td><input type="text" name="price" class="layui-input"></td>
                                <td><input type="text" name="subtotalAmount" class="layui-input"></td>
                                <td><input type="text" name="remark" class="layui-input"></td>
                            </tr>

                        </table>

                        <#--<div class="layui-form-item">
                            <div class="beg-pull-left beg-login-remember">
                                <button class="layui-btn" lay-submit  lay-filter="formDemo">登录</button>
                            </div>
                            <div class="beg-pull-right">
                                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                            </div>
                            <div class="beg-clear"></div>
                        </div>-->
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
    layui.use(['laydate','form'], function(){
        var laydate = layui.laydate;

        //执行一个laydate实例
        laydate.render({
            elem: '#getGoodsDate' //指定元素
        });


    });
</script>
</html>