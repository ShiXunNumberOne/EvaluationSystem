<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>用户修改--${site.name}</title>
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
    <input class="layui-hide" name="id" value="${localuser.id}"/>
    <div class="layui-form-item">
        <label class="layui-form-label">登录名</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" name="loginName" lay-verify="required" placeholder="请输入登录名" value="${localuser.loginName}">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">昵称</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" name="nickName" placeholder="请输入昵称" value="${localuser.nickName}">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">邮箱</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" name="email" lay-verify="email" placeholder="请输入邮箱" value="${localuser.email}">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">手机</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" name="tel" lay-verify="phone" placeholder="请输入手机号" value="${localuser.tel}">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">性别</label>
        <div class="layui-input-block">
            <input type="radio" name="gender" value="0" title="男" <#if (localuser.gender  == 0)>checked=""</#if>>
            <input type="radio" name="gender" value="1" title="女"<#if (localuser.gender  == 1)>checked=""</#if>>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">用户角色</label>
        <div class="layui-input-block role-box">
            <div class="jq-role-inline">
                <fieldset class="layui-elem-field">
                    <legend>选择角色</legend>
                    <div class="layui-field-box">
                    <#if roleList??>
                        <#if (roleList?size > 0)>
                            <#list roleList as role>
                                <input type="checkbox" name="roles"  value="${role.id}" title="${role.name}" lay-filter="role" <#list roleIds as roleId><#if (roleId == role.id)>checked</#if></#list> />
                            </#list>
                        </#if>
                    </#if>
                    </div>
                </fieldset>
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">学院</label>
        <div class="layui-input-block">
            <select name="dept_id" id="dept" lay-filter="dept" lay-verify="required" lay-search>
                <option value="">${localuser.deptId}</option>
            </select>
        </div>
    </div>
        <div class="layui-form-item" id="changeClass" style="display: none">
            <label class="layui-form-label">班级</label>
            <div class="layui-input-block">
                <select name="clazz_id" id="clazz" lay-filter="clazz" lay-search>
                    <option value="">请选择班级</option>
                </select>
            </div>
        </div>

    <div class="layui-form-item">
        <label class="layui-form-label">是否启用</label>
        <div class="layui-input-block">
            <input type="checkbox" name="delFlag" lay-skin="switch" value="true"  lay-text="启用|停用" <#if (localuser.delFlag  == false)>checked=""</#if> >
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="addUser">我要修改</button>
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
                delFlage = ${localuser.delFlag?string};
        $.ajax({
            url: '${base}/admin/system/clazz/queryDept',
            dataType: 'json',
            type: 'get',
            success: function (data) {
                $.each(data.data, function (index,item) {
                    $('#dept').append(new Option(item.name,item.id));// 下拉菜单里添加元素
                });
                $("#dept option[value='${localuser.dept_id}']").prop("selected",true);
                layui.form.render("select");
            }
        })

        //角色下拉框change事件，判断是否为学生显示班级
        form.on('checkbox', function(data){
            console.log(data.value);
            if('${role.id}'==1 || '${role.id}'==2){
                $('#changeClass').css("display","none")
            }else{
                $('#changeClass').css("display","block")
            }
        });
        //选择院系，得到班级
        form.on('select(dept)', function(data) {
            console.log(data.value)
            $('#clazz').empty();
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
                    $("#clazz option[value='${localuser.clazz_id}']").prop("selected",true);
                    layui.form.render("select");
                }
            })
        });
        form.on('select(clazz)', function(data){
        });
        form.on("submit(addUser)",function(data){
            if(data.field.id == null){
                layer.msg("用户ID不存在");
                return false;
            }
            //给角色赋值
            delete data.field["roles"];
            var selectRole = [];
            $('input[name="roles"]:checked').each(function(){
                selectRole.push({"id":$(this).val()});
            });
            data.field.roleLists = selectRole;
            //判断用户是否启用
            if(undefined !== data.field.delFlag && null != data.field.delFlag){
                data.field.delFlag = false;
            }else{
                data.field.delFlag = true;
            }
            var loadIndex = layer.load(2, {
                shade: [0.3, '#333']
            });
            $.ajax({
                type:"POST",
                url:"${base}/admin/system/user/edit",
                dataType:"json",
                contentType:"application/json",
                data:JSON.stringify(data.field),
                success:function(res){
                    layer.close(loadIndex);
                    if(res.success){
                        parent.layer.msg("用户编辑成功！",{time:1500},function(){
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