<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <style>
        #reds{
            color: red;
        }
    </style>
</head>
<body>
        <h2>记账</h2>
        <p id="dan"></p>
        <p>标题：<input type="text" id="title"/></p>
        <p>日期：<input type="text" id="time"/>(yyyy-MM-dd)</p>
        <p>金额：<input type="text" id="price"/></p>
        <p>说明：<input type="text" id="explain"/></p>
        <p><input type="button" id="chong" value="重置"/><input type="button" id="bao" value="保存"/><input type="button" id="fan" value="返回"/></p>

     <script src="static/js/jquery-1.8.3.js"></script>
    <script>
        $("#bao").click(function () {
            var type_id = $("input[name='tname']:checked").val();
            var time = $("#time").val();
            var price = $("#price").val();
            var explain = $("#explain").val();
            var title = $("#title").val();
            $.ajax({
                url:"add",
                data:{type_id:type_id,time:time,price:price,explain:explain,title:title},
                type:"post",
                success:function (data) {
                    if(data==true){
                        location.href="index";
                    }else{
                        $("body").append("<p id='reds'>插入失败</p>");
                    }
                }
            })
        })
        $("#fan").click(function () {
            location.href="index";
        })
        $("#chong").click(function () {
            $("#title,#time,#price,#explain").val("");
            $("input[type='radio']").eq(0).prop("checked",true);
        })
        $(function () {
            $.ajax({
                url: "tgetAll",
                type: "post",
                success: function (data) {
                    var html = "爱好:";
                    for (var i = 0; i < data.length; i++) {
                        html += "<input type='radio' name='tname' value='"+data[i].id+"'/>"+data[i].name+"&nbsp;";
                    }
                    $("#dan").append(html);
                    $("input[type='radio']").eq(0).prop("checked",true);
                }
            })
        })
    </script>
</body>

</html>