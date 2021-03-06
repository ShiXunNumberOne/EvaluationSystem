<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>授课修改--${site.name}</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
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
    <input class="layui-hide" name="id" value="${schedules.id}"/>
    <div class="layui-form-item">
        <label class="layui-form-label">班级-学院</label>
        <div class="layui-input-block">
            <select name="dept_id" id="dept"  lay-filter="dept" lay-verify="required" lay-search>
                <option value="">请选择学院</option>
            </select>
        </div>
    </div>
    <div class="layui-form-item" >
        <label class="layui-form-label">班级</label>
        <div class="layui-input-block">
            <select name="clazz_id" id="clazz"  lay-search>
                <option value="">请选择班级</option>
            </select>
        </div>
    </div>
    <div class="layui-form-item" >
        <label class="layui-form-label">教师</label>
        <div class="layui-input-block">
            <select name="teacher_id" id="teacher"  lay-search>
                <option value="">请选择教师</option>
            </select>
        </div>
    </div>
    <div class="layui-form-item" >
        <label class="layui-form-label">课程</label>
        <div class="layui-input-block">
            <select name="course_id" id="course"  lay-search>
                <option value="">请选择课程</option>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">上课地点</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" name="address" lay-verify="required" placeholder="上课地点" value="${schedules.address}">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">课时</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" name="hour" lay-verify="required" placeholder="课时" value="${schedules.hour}">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">学分</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" name="credit" lay-verify="required" placeholder="学分" value="${schedules.credit}">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">是否启用</label>
        <div class="layui-input-block">
            <input type="checkbox" name="status" lay-skin="switch" value="1"  lay-text="启用|停用" <#if (schedules.status  == 1)>checked=""</#if> >
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="addSchedules">我要修改</button>
            <button class="layui-btn"   class="layui-btn layui-btn-primary">我不改了</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="${base}/static/layui/layui.js"></script>
<script>
    var index = parent.layer.getFrameIndex(window.name); //当前窗口索引
    layui.use(['form','jquery','layer'],function(){
        var form = layui.form,
            $    = layui.jquery,
            layer = layui.layer,
            status = ${schedules.status};

        $.ajax({
            url: '${base}/admin/system/clazz/queryDept',
            dataType: 'json',
            type: 'get',
            success: function (data) {
                $.each(data.data, function (index,item) {
                    $('#dept').append(new Option(item.name,item.id));// 下拉菜单里添加元素
                });
                <#--$("#dept option[value='${}']").prop("selected",true);-->
                layui.form.render("select");
            }
        });
        $.ajax({
            url: '${base}/admin/system/course/queryCourse',
            dataType: 'json',
            type: 'get',
            success: function (data) {
                $.each(data.data, function (index,item) {
                    $('#course').append(new Option(item.name,item.id));// 下拉菜单里添加元素
                });
                $("#course option[value='${schedules.courseId}']").prop("selected",true);
                layui.form.render("select");
            }
        });
        form.on('select(course)', function(data){
        });
        //选择院系，得到班级
        form.on('select(dept)', function(data) {
            console.log(data.value)
            $('#clazz').empty()
            $('#teacher').empty()
            var dept_id = data.value
            $.ajax({
                url: '${base}/admin/system/clazz/queryClazz',
                dataType: 'json',
                type: 'get',
                data: {dept_id:dept_id},
                success: function (data) {
                    $.each(data.data, function (index, item) {

                        $('#clazz').append(new Option(item.name, item.id));// 下拉菜单里添加元素
                    });
                    $("#clazz option[value='${schedules.clazzId}']").prop("selected",true);
                    layui.form.render("select");
                }
            });
            $.ajax({
                url: '${base}/admin/system/user/queryUser',
                dataType: 'json',
                type: 'get',
                data: {dept_id:dept_id},
                success: function (data) {
                    $.each(data.data, function (index, item) {

                        $('#teacher').append(new Option(item.nickName, item.id));// 下拉菜单里添加元素
                    });
                    $("#teacher option[value='${schedules.teacherId}']").prop("selected",true);
                    layui.form.render("select");
                }
            });
        });
        form.on("submit(addSchedules)",function(data){
            if(data.field.id == null){
                layer.msg("授课ID不存在");
                return false;
            }
            console.log(data);
            //判断用户是否启用
            if(undefined !== data.field.status && null != data.field.status){
                data.field.status = 1;
            }else{
                data.field.status = 0;
            }
            var loadIndex = layer.load(2, {
                shade: [0.3, '#333']
            });
            $.ajax({
                type:"POST",
                url:"${base}/admin/system/teach/edit",
                dataType:"json",
                contentType:"application/json",
                data:JSON.stringify(data.field),
                success:function(res){
                    layer.close(loadIndex);
                    if(res.success){
                        parent.layer.msg("授课编辑成功！",{time:1500},function(){
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