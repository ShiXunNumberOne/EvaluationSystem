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
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <#if parentMenu != null>
    <div class="layui-form-item">
        <label class="layui-form-label">指标类别名称</label>
        <div class="layui-input-block">
            <select name="parentId" class="layui-input" disabled="">
                <option value="${parentNormtarget.id}">${parentNormtarget.name}</option>
            </select>
        </div>
    </div>
    </#if>
    <div>
        <div class="layui-form-item">
            <label class="layui-form-label">指标名称</label>
             <div class="layui-input-block">
                <input type="text" class="layui-input" name="name" lay-verify="required" placeholder="请输入指标名称">
            </div>
         </div>
         <div class="layui-form-item">
             <label class="layui-form-label">指标权重</label>
            <div class="layui-input-block">
                <input type="number" class="layui-input" name="href"  placeholder="请输入指标权重">
             </div>
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
    var iconShow,$;
    layui.use(['form','jquery','layer'],function(){
       var form = layui.form,
           layer = layui.layer;
        $    = layui.jquery;

        $('.color-box').colpick({
            colorScheme:'dark',
            layout:'rgbhex',
            color:'ff8800',
            onSubmit:function(hsb,hex,rgb,el) {
                $(el).css('background-color', '#'+hex);
                $(el).colpickHide();
                $("input[name='bgColor']").val("#"+hex);
            }
        }).css('background-color', '#ff8800');

        //选择图标
        $("#selectIcon").on("click",function () {
            iconShow =layer.open({
                type: 2,
                title: '选择图标',
                shadeClose: true,
                content: '${base}/static/page/icon.html'
            });
            layer.full(iconShow);
        });

        form.on("submit(addUser)",function(data){
            //判断左侧是否显示
            if(undefined !== data.field.isShow && null != data.field.isShow){
                data.field.isShow = true;
            }else{
                data.field.isShow = false;
            }
            var loadIndex = layer.load(2, {
                shade: [0.3, '#333']
            });
            $.post("${base}/admin/system/menu/add",data.field,function (res) {
                layer.close(loadIndex);
                if(res.success){
                    parent.layer.msg("菜单添加成功!",{time:1500},function(){
                        //刷新父页面
                        parent.location.reload();
                    });
                }else{
                    layer.msg(res.message);
                }
            });
            return false;
        });

    });
</script>
</body>
</html>