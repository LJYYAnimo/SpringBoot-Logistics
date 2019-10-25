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
                        <div class="layui-container">
                            <div class="layui-row">
                                <div class="layui-col-md4">
                                    <label class="layui-form-label">订单金额</label>
                                    <div class="layui-input-block">
                                        <input type="text" disabled="disabled" name="totalAmount" class="layui-input" value="${result.orderEntity.totalAmount!}">
                                    </div>
                                </div>
                                <div class="layui-col-md4">
                                    <label class="layui-form-label">已付金额</label>
                                    <div class="layui-input-block">
                                        <input type="text" disabled="disabled" name="paidAmount" class="layui-input" value="${result.orderEntity.paidAmount!}">
                                    </div>
                                </div>
                                <div class="layui-col-md4">
                                    <label class="layui-form-label">未付金额</label>
                                    <div class="layui-input-block">
                                        <input type="text" disabled="disabled" name="unpaidAmount" class="layui-input" value="${result.orderEntity.unpaidAmount!}">
                                    </div>
                                </div>
                            </div>

                            <div class="layui-row" style="margin: 10px 0px">
                                <div class="layui-col-md5">
                                    <label class="layui-form-label">本次支付</label>
                                    <div class="layui-input-block">
                                        <input type="text" id="payObjAmount" lay-verify="required" class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-col-md7">
                                    <label class="layui-form-label">收款银行卡</label>
                                    <div class="layui-input-block">
                                        <input type="text" id="payObjBankNo" class="layui-input">
                                    </div>
                                </div>
                            </div>

                            <div class="layui-row" style="margin: 10px 0px">
                                <div class="layui-col-md9">
                                    <label class="layui-form-label">付款备注</label>
                                    <div class="layui-input-block">
                                        <input type="text" id="payObjRemark" class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-col-md3">
                                    <div class="layui-input-block" style="float: left;">
                                        <button class="layui-btn layui-btn-normal" type="button" id="commitPay">确认</button>
                                        <button class="layui-btn layui-btn-primary" type="button" id="clearPay">清空</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    <#-- 货物详情内容 -->
                        <table class="layui-table" id="append_table">
                        <#--设置列的宽度
                        <colgroup>
                            <col width="150">
                            <col width="150">
                            <col width="200">
                            <col>
                        </colgroup>-->
                            <tr class="headTr">
                                <th style="text-align: center" width="35px">操作</th>
                                <th style="text-align: center" width="35px">序号</th>
                                <th style="text-align: center" width="80px">付款金额</th>
                                <th style="text-align: center" width="150px">付款时间</th>
                                <th style="text-align: center" width="170px">收款银行卡号</th>
                                <th style="text-align: center" width="170px">付款备注</th>
                            </tr>
                            <#if result?exists && result.payList?has_content>
                                <#list result.payList as pay>
                                    <tr class="contentTr" style="text-align: center">
                                        <td style="text-align: center" class="delete_animo">
                                            <button type="button"><i class="layui-icon">&#xe640;</i></button>
                                        </td>
                                        <td>${pay?index+1}</td>
                                        <td>${pay.payAmount!}</td>
                                        <td>${pay.payTime!}</td>
                                        <td>${pay.bankNo!}</td>
                                        <td>${pay.payRemark!}</td>
                                        <td style="display: none;"><input type="hidden" name="goodsId" value="${pay.id!}" ></td>
                                    </tr>
                                </#list>
                            <#else>
                                <tr class="contentTr">
                                    <td style="text-align: center;background-color: #4E5465;color: #ffffff;" colspan="6" >
                                        -- 无支付记录 --
                                    </td>
                                </tr>
                            </#if>
                        </table>
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

        // 删除按钮监听事件
        $("#append_table").on("click", ".delete_animo", function () {
            var delId = $(this).parent().find("td").eq(6).find("input").val();
            if(delId != null && delId != undefined && delId.trim() != ""){
                console.log(delId);
            }
            this.parentElement.remove();
        });

        $("#commitPay").click(function() {
            var payObj = {};
            var num = /([1-9]+[0-9]*|0)(\\.[\\d]+)?/;
            if(!num.test($("#payObjAmount").val())){
                layer.alert('本次支付金额请输入数字!');
                return;
            }
            if($("#payObjAmount").val().trim() == "" || $("#payObjAmount").val() == undefined || $("#payObjAmount").val() == null){
                layer.alert('本次支付金额不能为空!');
                return;
            }
            if($("#payObjAmount").val() <= 0){
                layer.alert('本次支付金额的金额必须大于0!');
                return;
            }
            if($("#payObjAmount").val() > ${result.orderEntity.unpaidAmount !}){
                layer.alert('本次支付金额的金额大于未付金额!');
                return;
            }
            payObj.orderId = ${result.orderEntity.id};
            payObj.payAmount = $("#payObjAmount").val();
            payObj.bankNo = $("#payObjBankNo").val();
            payObj.payRemark = $("#payObjRemark").val();
            axios({
                url:'/order/savePay',
                method: 'post',
                data:payObj,
                headers:{
                    'Content-Type':'application/json; charset=UTF-8'
                }
            }).then(function (response) {
                if(response.data.type == 'SUCCESS'){
                    layer.msg("新增成功");
                    window.parent.location.reload();//刷新父页面
                    parent.layer.closeAll();
                    return false;
                }
                layer.msg(response.data.message);
                return false;
            })
            .catch(function (error) {
                console.log(error);
                layer.msg(error)
                return false;
            });
        })
        $("#clearPay").click(function() {
            $("#payObjAmount").val("");
            $("#payObjBankNo").val("");
            $("#payObjRemark").val("");
        })

    });
</script>
</html>