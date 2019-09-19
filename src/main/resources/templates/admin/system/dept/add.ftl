<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>班级添加--${site.name}</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <!-- 页面描述 -->
    <meta name="description" content="${site.description}"/>
    <!-- 页面关键词 -->
    <meta name="keywords" content="${site.keywords}"/>
    <!-- 网页作者 -->
    <meta name="author" content="${site.author}"/>
    <link rel="stylesheet" href="${base}/static/layui/css/layui.css" media="all" />
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
    <div class="layui-form-item">
        <label class="layui-form-label">班级名称</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" name="name" lay-verify="required" placeholder="请输入班级">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">班级代码</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" name="code" lay-verify="required" placeholder="请输入班级代码">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">班级-学院</label>
        <div class="layui-input-block">
            <select name="dept_id" id="dept" lay-verify="required" lay-search>
                <option value="">请选择学院</option>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">是否启用</label>
        <div class="layui-input-block">
            <input type="checkbox" name="status" lay-skin="switch"  lay-filter="status"  lay-text="启用|停用" checked>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="addClazz">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="${base}/static/layui/layui.js"></script>
<script>
    layui.use(['form','jquery','layer'],function(){
        var form = layui.form,
            $    = layui.jquery,
            layer = layui.layer;
            status = 1;    //默认启用用户
        $.ajax({
            url: '${base}/admin/system/clazz/queryDept',
            dataType: 'json',
            type: 'get',
            success: function (data) {
                $.each(data.data, function (index,item) {
                    $('#dept').append(new Option(item.name,item.id));// 下拉菜单里添加元素
                });
                layui.form.render("select");
            }
        })
        form.on('select', function(data){
        });
        form.on("submit(addClazz)",function(data){
            var loadIndex = layer.load(2, {
                shade: [0.3, '#333']
            });
            var selectMatch= [];
            $('input[name="dept_id"]:checked').each(function(){
                selectMatch.push({"id":$(this).val()});
            });
            data.field.depts= selectMatch;
            //判断用户是否启用
            if(undefined !== data.field.status && null != data.field.status){
                data.field.status = 0;
            }else{
                data.field.status = 1;
            }
            $.ajax({
                type:"POST",
                url:"${base}/admin/system/clazz/add",
                dataType:"json",
                contentType:"application/json",
                data:JSON.stringify(data.field),
                success:function(res){
                    layer.close(loadIndex);
                    if(res.success){
                        parent.layer.msg("班级添加成功!",{time:1500},function(){
                            //刷新父页面
                            parent.location.reload();
                        });
                    }else{
                        layer.msg(res.message);
                    }
                }
            });
            return false;
        });
    });

</script>
</body>
</html>