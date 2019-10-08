<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>指标添加--layui后台管理模板</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="${base}/static/layui/css/layui.css" media="all" />
    <link rel="stylesheet" href="${base}/static/colorpicker/colpick.css" media="all" />
    <script type="text/javascript" src="${base}/static/js/jquery.min.js"></script>
    <style type="text/css">
        .layui-form-item .layui-inline{ width:33.333%; float:left; margin-right:0; }
        @media(max-width:1240px){
            .layui-form-item .layui-inline{ width:100%; float:none; }
        }
        .layui-form-item .role-box {
            position: relative;
        }
        .layui-form-item .role-box .jq-role-inline {
            height: 100%;
            overflow: auto;
        }

    </style>
    <script>
        $(document).ready(function(){
//<tr/>居中
            $("#tab tr").attr("align","center");
//增加<tr/>
            $("#but").click(function(){
                var _len = $("#tab tr").length;
                $("#tab").append("<tr id="+_len+" align='center'>"
                    +"<td>"+_len+"</td>"
                    +"<td><input type='text' name='oname' id='oname"+_len+"' /></td>"
                    +"<td style='display: none'><input type='number' name='normtargetId' id='normtargetId"+_len+"'VALUE='${normtargetId.id}' /></td>"
                    +"<td><input type='number' min='4' max='10' step='2' name='score' id='score"+_len+"' /></td>"
                    +"<td><a href=\'#\' onclick=\'deltr("+_len+")\'class=\"layui-btn layui-btn-danger layui-btn-xs\">删除</a></td>"
                    +"</tr>");
            })
        })
        //删除<tr/>
        var deltr =function(index)
        {
            var _len = $("#tab tr").length;
            if(_len<4){
                alert("提示!--选项最少有两个哦！")
            }else{
                $("tr[id='"+index+"']").remove();//删除当前行
            }
            for(var i=index+1,j=_len;i<j;i++)
            {
                var nextTxtVal = $("#desc"+i).val();
                $("tr[id=\'"+i+"\']")
                    .replaceWith("<tr id="+(i-1)+" align='center'>"
                        +"<td>"+(i-1)+"</td>"
                        +"<td><input type='text' name='oname"+_len+"' id='oname"+_len+"' /></td>"
                        +"<td><input type='text' name='score"+(i-1)+"' value='"+nextTxtVal+"' id='score"+(i-1)+"'/></td>"
                        +"<td><a href=\'#\' onclick=\'deltr("+(i-1)+")\' class=\"layui-btn layui-btn-danger layui-btn-xs\">删除</a></td>"
                        +"</tr>");
            }
        }
    </script>
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <#if parentNormtarget != null>
        <div class="layui-form-item">
            <label class="layui-form-label">指标类别</label>
            <div class="layui-input-block">
                <select name="pid" class="layui-input" disabled="">
                    <option value="${parentNormtarget.id}">${parentNormtarget.name}</option>
                </select>
            </div>
        </div>
    </#if>
    <div>
        <div class="layui-form-item">
            <label class="layui-form-label">指标</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" name="name" lay-verify="required" placeholder="请输入名称">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">排序码</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" name="sort" lay-verify="required" >
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">权重</label>
            <div class="layui-input-block">
                <input type="number" min="0" max="1.0" step="0.1" class="layui-input" name="entropy"  placeholder="请输入权重"style="width: 120px">
            </div>
        </div>
    </div>
    <#if parentNormtarget != null>
    <div class="layui-form-item">
        <label class="layui-form-label">指标选项</label> <input type="button"class="layui-btn add-btn" id="but" value="增加选项"/>
        <div class="layui-form-item">
            <table id="tab"class="layui-table" lay-filter="demo">
                <tr>
                    <td width="20%">选项序号</td>
                    <td>选项内容</td>
                    <td style="display: none"> 对应指标</td>
                    <td>选项分值</td>
                    <td>操作</td>
                </tr>
            </table>
        <#--<div class="layui-inline">-->
            <#--<div class="layui-input-block">-->
                <#--<input type="text" class="layui-input" name="name" lay-verify="required" placeholder="选项内容">-->
            <#--</div>-->
        <#--</div>-->
        <#--<div class="layui-inline">-->
            <#--<div class="layui-input-block">-->
                <#--<input type="number" min="4" max="10" step="2"  class="layui-input" name="score" lay-verify="required" placeholder="权重" style="width: 60px">-->
            <#--</div>-->
        <#--</div><button type="button" class="layui-btn add-btn">+</button><button type="button" class="layui-btn layui-btn-danger btn-del" lay-event="del">-</button>-->
        </div>
    </div>
    </#if>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="addUser">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>
<script src="https://code.jquery.com/jquery-2.2.4.min.js"
        integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44="
        crossorigin="anonymous"></script>
<script type="text/javascript" src="${base}/static/layui/layui.js"></script>
<script type="text/javascript" src="${base}/static/colorpicker/colpick.js"></script>
<script>
    layui.use(['form','jquery','layer'],function(){
        var form = layui.form,
            layer = layui.layer;
        $    = layui.jquery;

        var arr = [];
        form.on("submit(addUser)",function(data){
            //判断左侧是否显示
            if(undefined !== data.field.isShow && null != data.field.isShow){
                data.field.isShow = false;
            }else{
                data.field.isShow = true;
            }
            var loadIndex = layer.load(2, {
                shade: [0.3, '#333']
            });
            $("input[name='oname']").each(function(i){
                var score = $("input[name='score']").eq(i);
                var normtargetId = $("input[name='normtargetId']").eq(i);
                arr.push({"name": $(this).val(), "score": score.val(),"normtargetId":Number(normtargetId.val())+1});
            });
            <#--$.ajax({-->
                <#--type:"POST",-->
                <#--url:"${base}/admin/system/normitem/add",-->
                <#--dataType:"json",-->
                <#--contentType:"application/json",-->
                <#--data:JSON.stringify(arr),-->
                <#--success:function(res){-->
                    <#--layer.close(loadIndex);-->
                    <#--if(res.success){-->
                        <#--parent.layer.msg("选项添加成功!",{time:1500},function(){-->
                            <#--//刷新父页面-->
                            <#--parent.location.reload();-->
                        <#--});-->
                    <#--}else{-->
                        <#--layer.msg(res.message);-->
                    <#--}-->
                <#--}-->
            <#--});-->
            console.log(arr);
            $.post("${base}/admin/system/normtarget/add",data.field,function (res) {
                layer.close(loadIndex);
                if(res.success){
                    $.ajax({
                        type:"POST",
                        url:"${base}/admin/system/normitem/add",
                        dataType:"json",
                        contentType:"application/json",
                        data:JSON.stringify(arr),
                        success:function(res){
                            layer.close(loadIndex);
                            if(res.success){
                                parent.layer.msg("选项添加成功!",{time:1500},function(){
                                    //刷新父页面
                                    parent.location.reload();
                                });
                            }else{
                                layer.msg(res.message);
                            }
                        }
                    });
                    parent.layer.msg("类别添加成功!",{time:1500},function(){
                        //刷新父页面
                        parent.location.reload();
                    });
                }else{
                    layer.msg(res.message);
                }
            });
            return false;
        });
        // console.log(arr);
    });
</script>
</body>
</html>