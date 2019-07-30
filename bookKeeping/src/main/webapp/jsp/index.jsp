<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>

</head>
<body>
<h2>记账管理</h2>
<p>类型:<select id="tid">
    <option value="0">不限</option>
</select>&nbsp;&nbsp;
    <input type="button" id="sou" value="搜索">&nbsp;&nbsp;
<input type="button" id="ji" value="记账">
</p>
<table border="2">
    <thead>
    <tr>
        <th>标题</th>
        <th>记账时间</th>
        <th>类别</th>
        <th>金额</th>
        <th>说明</th>
    </tr>
    </thead>
    <tbody></tbody>
</table>
<script src="static/js/jquery-1.8.3.js"></script>
<script>
    var format = function(time, format) {
        var t = new Date(time);
        var tf = function(i) {
            return (i < 10 ? '0': '') + i
        };
        return format.replace(/yyyy|MM|dd|HH|mm|ss/g,
            function(a) {
                switch (a) {
                    case 'yyyy':
                        return tf(t.getFullYear());
                        break;
                    case 'MM':
                        return tf(t.getMonth() + 1);
                        break;
                    case 'mm':
                        return tf(t.getMinutes());
                        break;
                    case 'dd':
                        return tf(t.getDate());
                        break;
                    case 'HH':
                        return tf(t.getHours());
                        break;
                    case 'ss':
                        return tf(t.getSeconds());
                        break;
                }
            });
    }
    $(function () {
        $.ajax({
            url:"tgetAll",
            type:"post",
            success:function (data) {
                var html = "";
                for(var i=0;i<data.length;i++){
                    html+="<option value="+data[i].id+">"+data[i].name+"</option>"
                }
                $("#tid").append(html);
            }
        });
        $.ajax({
            url:"bgetAll",
            type:"post",
            success:function (data) {
                var html ="";
                for(var i=0;i<data.length;i++){
                    html+='<tr><td>'+data[i].title+'</td><td>'+format(data[i].bill_time,"yyyy-MM-dd")+'</td><td>'+data[i].type_name+'</td><td>'+data[i].type_price+'</td><td>'+data[i].explain+'</td></tr>';
                }
                $("tbody").html(html);
            }
        });
    })
    $("#ji").click(function () {
        location.href="ji";
    })
    $("#sou").click(function () {
        var type_id = $("#tid").val();
        if(type_id==0){
            type_id=null;
        }
       $.ajax({
            url:"bgetAll",
            data:{type_id:type_id},
            type:"post",
            success:function (data) {
                if(data==false){
                    $("tbody").html("未查到此类型账单！");
                    return false;
                }
                var html ="";
                for(var i=0;i<data.length;i++){
                    html+='<tr><td>'+data[i].title+'</td><td>'+format(data[i].bill_time,"yyyy-MM-dd")+'</td><td>'+data[i].type_name+'</td><td>'+data[i].type_price+'</td><td>'+data[i].explain+'</td></tr>';
                }
                $("tbody").html(html);
            }
        });
    })

</script>
</body>

</html>