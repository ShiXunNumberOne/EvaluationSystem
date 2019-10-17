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
    <input name="id" type="hidden" value="${normtarget.id}">
    <div>
        <div class="layui-form-item">
            <label class="layui-form-label">指标</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" name="name" lay-verify="required" placeholder="请输入名称" value="${normtarget.name}">
            </div>
        </div>
        <div class="layui-form-item" >
            <label class="layui-form-label">角色</label>
            <div class="layui-input-block">
                <select name="r_id" id="role"  lay-search>
                    <option value=" "></option>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">排序码</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" name="sort" lay-verify="required" value="${normtarget.sort}" >
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">权重</label>
            <div class="layui-input-block">
                <input type="number" min="0" max="1.0" step="0.1" class="layui-input" name="entropy"  value="${normtarget.entropy}" placeholder="请输入权重"style="width: 120px">
            </div>
        </div>
    </div>
        <div class="layui-form-item">
            <legend class="layui-form-label">指标选项</legend>
            <div class="layui-form-item">
                <fieldset class="layui-elem-field">
                    <legend>选项内容</legend>
                    <div class="layui-form-item">
                        <#if normitems??>
                            <#if (normitems?size > 0)>
                                <#list normitems as nm>
                        <div class="layui-form-item">
                            <div class="layui-inline">
                            <label class="layui-form-label">选项</label>
                            <div class="layui-input-block">
                                <input type="text" class="layui-input" name="list"  value="${nm.name}" title="${nm.id}" lay-filter="role" width="80%"/>
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">分值</label>
                            <div class="layui-input-block">
                                <input type="text" class="layui-input" name="list"  value="${nm.score}" title="${nm.id}" lay-filter="role" width="20%"/>
                            </div>
                        </div>
                            </div>
                                </#list>
                            </#if>
                        </#if>
                    </div>
                </fieldset>
            </div>
        </div>
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


        $.ajax({
            url: '${base}/admin/system/role/queryRole',
            dataType: 'json',
            type: 'get',
            success: function (data) {

                $.each(data.data, function (index,item) {
                    console.log("角色姓名:"+item.name)
                    console.log("角色ID:"+item.id)
                    $('#role').append(new Option(item.name,item.id));// 下拉菜单里添加元素
                });
                $("#role option[value='${normtarget.rid}']").prop("selected",true);
                layui.form.render("select");
            }
        });
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
            $.post("${base}/admin/system/normtarget/edit",data.field,function (res) {
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