<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>批次管理修改--${site.name}</title>
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
    <input class="layui-hide" name="id" value="${etask.id}"/>
    <div class="layui-form-item">
        <label class="layui-form-label">批次名称</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" name="name" lay-verify="required" placeholder="批次名称" value="${etask.name}">
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">开始时间</label>
            <div class="layui-input-block">
                <input type="text" style="width: 58%"  id="test2" class="layui-input" name="startData" lay-verify="required" placeholder="开始时间" value="${etask.startData}">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">结束时间</label>
            <div class="layui-input-block">
                <input type="text" style="width:58%" id="test" class="layui-input" name="endData" lay-verify="required" placeholder="结束时间" value="${etask.endData}">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">是否启用</label>
        <div class="layui-input-block">
            <input type="checkbox" name="status" lay-skin="switch" value="1"  lay-text="启用|停用" <#if (etask.status  == 1)>checked=""</#if> >
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="addEtask">我要修改</button>
            <button class="layui-btn"   class="layui-btn layui-btn-primary">我不改了</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="${base}/static/layui/layui.js"></script>
<script>

    // var index = parent.layer.getFrameIndex(window.name); //当前窗口索引
            // console.log(a)
    layui.use(['form','jquery','layer','laydate'],function(){
        var form = layui.form,
                $    = layui.jquery,
                layer = layui.layer,
                laydate = layui.laydate,
                status = ${etask.status};
        // $("#test").val('2019/01/1')
        console.log($("#test").val())
//自定义日期格式
        laydate.render({
            elem: ['#test','#test2']
            ,format: 'yyyy-M-d' //可任意组合
        });

        form.on("submit(addEtask)",function(data){
            if(data.field.id == null){
                layer.msg("ID不存在");
                return false;
            }
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
                url:"${base}/admin/system/etask/edit",
                dataType:"json",
                contentType:"application/json",
                data:JSON.stringify(data.field),
                success:function(res){
                    layer.close(loadIndex);
                    if(res.success){
                        parent.layer.msg("批次编辑成功！",{time:1500},function(){
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
    //格式化时间
    Date.prototype.Format = function (fmt) {
        var o = {
            "M+": this.getMonth() + 1, //月份
            "d+": this.getDate(), //日
            "h+": this.getHours(), //小时
            "m+": this.getMinutes(), //分
            "s+": this.getSeconds(), //秒
            "q+": Math.floor((this.getMonth() + 3) / 3), //季度
            "S": this.getMilliseconds() //毫秒
        };
        if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
        for (var k in o)
            if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        return fmt;
    };
</script>
</body>
</html>