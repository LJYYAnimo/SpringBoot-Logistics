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
                    <form class="layui-form" >
                        <div class="layui-container">
                            <div class="layui-row">
                                <div class="layui-col-md4">
                                    <label class="layui-form-label">购货单位</label>
                                    <div class="layui-input-block">
                                        <input type="text" name="customerName" lay-verify="required" class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-col-md4">
                                    <label class="layui-form-label">联系电话</label>
                                    <div class="layui-input-block">
                                        <input type="text" name="customerTel" class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-col-md4">
                                    <label class="layui-form-label">提/送货日期</label>
                                    <div class="layui-input-block">
                                        <input type="text" autocomplete="off" class="layui-input" id="getGoodsDate" readonly="readonly">
                                    </div>
                                </div>
                            </div>

                            <div class="layui-row" style="margin: 10px 0px">
                                <div class="layui-col-md4">
                                    <label class="layui-form-label">发票类型</label>
                                    <div class="layui-input-block">
                                        <input type="text" name="invoiceType" lay-verify="required" class="layui-input" value="待定">
                                    </div>
                                </div>
                                <div class="layui-col-md4">
                                    <label class="layui-form-label">发票编号</label>
                                    <div class="layui-input-block">
                                        <input type="text" name="invoiceNo" class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-col-md4">
                                    <label class="layui-form-label">提货人信息</label>
                                    <div class="layui-input-block">
                                        <input type="text" name="getGoodsPerson" class="layui-input">
                                    </div>
                                </div>

                            </div>

                            <div class="layui-row" style="margin: 10px 0px">
                                <div class="layui-col-md4">
                                    <label class="layui-form-label">是否结算</label>
                                    <div class="layui-input-block" style="width: 300px;">
                                        <input type="radio" name="payStatus" lay-filter="payStatus" value="2" title="未付款" checked="">
                                        <input type="radio" name="payStatus" lay-filter="payStatus" value="3" title="部分支付">
                                        <input type="radio" name="payStatus" lay-filter="payStatus" value="4" title="已结清">
                                    </div>
                                </div>
                                <div class="layui-col-md4">
                                    <label class="layui-form-label">已付货款</label>
                                    <div class="layui-input-block">
                                        <input type="text" name="paidAmount" class="layui-input paidAmount">
                                    </div>
                                </div>
                                <div class="layui-col-md4">
                                    <label class="layui-form-label">未付货款</label>
                                    <div class="layui-input-block">
                                        <input type="text" name="unpaidAmount" class="layui-input unpaidAmount">
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
                                <th style="text-align: center">
                                    <button type="button" onclick="append_tr()"><i class="layui-icon">&#xe654;</i>
                                    </button>
                                </th>
                                <th style="text-align: center">货品名称</th>
                                <th style="text-align: center">规格型号</th>
                                <th style="text-align: center">数量</th>
                                <th style="text-align: center">重量-吨</th>
                                <th style="text-align: center">计价方式</th>
                                <th style="text-align: center">单价</th>
                                <th style="text-align: center">金额</th>
                                <th style="text-align: center">备注</th>
                            </tr>
                            <tr class="contentTr">
                                <td style="text-align: center" class="delete_animo">
                                    <button type="button"><i class="layui-icon">&#xe640;</i></button>
                                </td>
                                <td><input type="text" name="goodsName" class="layui-input"></td>
                                <td><input type="text" name="goodsDetailInfo" class="layui-input"></td>
                                <td><input type="number" name="number" class="layui-input number"></td>
                                <td><input type="text" name="weight" class="layui-input weight"></td>
                                <td><input type="text" name="pricingMethod" class="layui-input"></td>
                                <td><input type="text" name="price" class="layui-input price"></td>
                                <td><input type="text" name="subtotalAmount" class="layui-input subtotalAmount"></td>
                                <td><input type="text" name="remark" class="layui-input"></td>
                            </tr>
                            <tr class="footTr">
                                <td colspan="3" style="text-align: center"><span>合计</span></td>
                                <td><input class="layui-input" style="border: none;" type="text" name="totalNumber" id="totalNumber" readonly="readonly"></td>
                                <td><input class="layui-input" style="border: none;" type="text" name="totalWeight" id="totalWeight" readonly="readonly"></td>
                                <td></td>
                                <td></td>
                                <td><input class="layui-input" style="border: none;" type="text" name="totalAmount" id="totalAmount" readonly="readonly"></td>
                                <td></td>
                            </tr>
                        </table>
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
                                <button class="layui-btn" lay-submit lay-filter="orderAddForm">新增</button>
                            <#--<button type="reset" class="layui-btn layui-btn-primary">重置</button>-->
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

        //执行一个laydate实例
        laydate.render({
            elem: '#getGoodsDate', //指定元素
            value: new Date()   // 默认当天
        });

        // 在合计上追加html
        window.append_tr = function () {
            $('#append_table .footTr').before('<tr class="contentTr">\n' +
                    '<td style="text-align: center" class="delete_animo"><button type="button""><i class="layui-icon">&#xe640;</i></button></td>' +
                    '<td><input type="text" name="goodsName" class="layui-input"></td>' +
                    '<td><input type="text" name="goodsDetailInfo" class="layui-input"></td>' +
                    '<td><input type="number" name="number" class="layui-input number"></td>' +
                    '<td><input type="text" name="weight" class="layui-input weight"></td>' +
                    '<td><input type="text" name="pricingMethod" class="layui-input"></td>' +
                    '<td><input type="text" name="price" class="layui-input price"></td>' +
                    '<td><input type="text" name="subtotalAmount" class="layui-input subtotalAmount"></td>' +
                    '<td><input type="text" name="remark" class="layui-input"></td>' +
                    '</tr>');
        };

        // 删除按钮监听事件
        $("#append_table").on("click", ".delete_animo", function () {
            this.parentElement.remove();
            totalNumberCalculate();
            totalWeightCalculate();
            totalAmountCalculate();
        });

        // 监听单选按钮选择框切换事件
        form.on('radio(payStatus)', function (data) {
            console.log(data.value);
            var totalAmount = $("#totalAmount").val();
            if(totalAmount != null && totalAmount != undefined && totalAmount.trim() != ""){
                if(data.value == 2){
                    $(".paidAmount").val("0");
                    $(".unpaidAmount").val(totalAmount);
                }else if(data.value == 4){
                    $(".unpaidAmount").val("0");
                    $(".paidAmount").val(totalAmount);
                }
            }
        });

        // 监听已付货款输入框失焦
        $("body").on("blur", ".paidAmount", function () {
            if($(this).val() != null && $(this).val() != undefined && $(this).val().trim() != ""&&
                $("#totalAmount").val() != null && $("#totalAmount").val() != undefined && $("#totalAmount").val().trim() != ""){
                var totalAmount = $("#totalAmount").val()
                var paidAmount = $(this).val();
                var unpaidAmount = Number(totalAmount) - Number(paidAmount);
                $(".unpaidAmount").val(unpaidAmount);
                var radioValue = 0;
                if(paidAmount == totalAmount){
                    radioValue = 4;
                }else if(paidAmount > 0){
                    radioValue = 3;
                }else if(paidAmount == 0){
                    radioValue = 2;
                }
                // 改变单选框值
                if(radioValue != 0){
                    var radio = document.getElementsByName("payStatus");
                    var radioLength = radio.length;
                    for (var i = 0; i < radioLength; i++) {
                        if(radioValue == radio[i].value){
                            $(radio[i]).next().click();
                        }
                    }
                }

            }
        });



        // 监听重量输入框失焦
        $("#append_table").on("blur", ".weight", function () {
            totalWeightCalculate();
        });

        // 监听数量输入框失焦
        $("#append_table").on("blur", ".number", function () {
            var _this = $(this);
            var number = _this.val();
            var price = _this.parent().parent().find("td .price").val();
            // 两个框的值都不为空 并且金额框为空 才触发计算填充
            if (number != undefined && number.trim() != "" && price != undefined && price.trim() != "") {
                if (_this.parent().parent().find("td .subtotalAmount").val() != undefined &&
                        _this.parent().parent().find("td .subtotalAmount").val().trim() != "" &&
                        _this.parent().parent().find("td .subtotalAmount").val() != null) {
                    return;
                }
                _this.parent().parent().find("td .subtotalAmount").val(number * price);
                totalAmountCalculate();
            }
            totalNumberCalculate();
        });

        // 监听单价输入框失焦
        $("#append_table").on("blur", ".price", function () {
            var _this = $(this);
            var price = _this.val();
            var number = _this.parent().parent().find("td .number").val();
            // 两个框的值都不为空 并且金额框为空 才触发计算填充
            if (number != undefined && number.trim() != "" && price != undefined && price.trim() != "") {
                if (_this.parent().parent().find("td .subtotalAmount").val() != undefined &&
                        _this.parent().parent().find("td .subtotalAmount").val().trim() != "" &&
                        _this.parent().parent().find("td .subtotalAmount").val() != null) {
                    return;
                }
                _this.parent().parent().find("td .subtotalAmount").val(number * price);
                totalAmountCalculate();
            }
        });

        // 监听小计金额变化事件
        $("#append_table").on("change", ".subtotalAmount", function () {
            totalAmountCalculate();
        });

        // 总计数量统计
        function totalNumberCalculate(){
            var numberList = [];
            numberList = $("#append_table").find(".number");
            console.log(numberList.length);
            var totalNumber = 0;
            for(var i = 0; i < numberList.length; i++){
                if($(numberList[i]).val() != null && $(numberList[i]).val() != undefined && $(numberList[i]).val().trim() != "") {
                    totalNumber += Number($(numberList[i]).val());
                }
            }
            $("#totalNumber").val(totalNumber);
        }

        // 总计重量统计
        function totalWeightCalculate(){
            var weightList = [];
            weightList = $("#append_table").find(".weight");
            console.log(weightList.length);
            var totalWeight = 0;
            for(var i = 0; i < weightList.length; i++){
                if($(weightList[i]).val() != null && $(weightList[i]).val() != undefined && $(weightList[i]).val().trim() != "") {
                    totalWeight+=Number($(weightList[i]).val());
                }
            }
            $("#totalWeight").val(totalWeight);
        }

        // 总计金额统计
        function totalAmountCalculate(){
            var amountList = [];
            amountList = $("#append_table").find(".subtotalAmount");
            console.log(amountList.length);
            var totalAmount = 0;
            for(var i = 0; i < amountList.length; i++){
                if($(amountList[i]).val() != null && $(amountList[i]).val() != undefined && $(amountList[i]).val().trim() != "") {
                    totalAmount+=Number($(amountList[i]).val());
                }
            }
            $("#totalAmount").val(totalAmount);
        }

        //监听提交
        form.on('submit(orderAddForm)', function (data) {
            var postData = {};
            var goodsList = [];
            var contentList = $("#append_table").find(".contentTr");
            for(var i = 0; i < contentList.length; i++){
                var detail = {};
                for(var index = 1; index <= 8; index++){
                    switch (index){
                        case 1:
                            detail.goodsName = $(contentList[i]).find("td").eq(index).find("input").val();
                            break;
                        case 2:
                            detail.goodsDetailInfo = $(contentList[i]).find("td").eq(index).find("input").val();
                            break;
                        case 3:
                            detail.number = $(contentList[i]).find("td").eq(index).find("input").val();
                            break;
                        case 4:
                            detail.weight = $(contentList[i]).find("td").eq(index).find("input").val();
                            break;
                        case 5:
                            detail.pricingMethod = $(contentList[i]).find("td").eq(index).find("input").val();
                            break;
                        case 6:
                            detail.price = $(contentList[i]).find("td").eq(index).find("input").val();
                            break;
                        case 7:
                            detail.subtotalAmount = $(contentList[i]).find("td").eq(index).find("input").val();
                            break;
                        case 8:
                            detail.remark = $(contentList[i]).find("td").eq(index).find("input").val();
                            break;
                    }
                }
                goodsList.push(detail);
            }
            console.log(goodsList);
            var orderEntity = data.field;
            delete orderEntity.goodsName;
            delete orderEntity.goodsDetailInfo;
            delete orderEntity.number;
            delete orderEntity.weight;
            delete orderEntity.pricingMethod;
            delete orderEntity.price;
            delete orderEntity.subtotalAmount;
            delete orderEntity.remark;
            orderEntity.getGoodsDate = $("#getGoodsDate").val();
            postData.orderEntity = orderEntity;
            postData.goodsList = goodsList;
            console.log(postData);

            axios({
                url:'/order/save',
                method: 'post',
                data:postData,
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
                layer.msg(error);
                return false;
            });
            return false;
        });

    });
</script>
</html>