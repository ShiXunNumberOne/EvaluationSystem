<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>批次列表--${site.name}</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel = "shortcut icon" href="${site.logo}">
    <link rel="stylesheet" href="${base}/static/layui/css/layui.css" media="all" />
    <link rel="stylesheet" href="${base}/static/css/user.css" media="all" />
</head>
<body class="childrenBody">

<fieldset class="layui-elem-field">
    <legend>批次检索</legend>
    <div class="layui-field-box">
    <form class="layui-form">
        <div class="layui-inline" style="width: 15%">
            <input type="text" value="" name="s_key" placeholder="可以输入批次名" class="layui-input search_input">
        </div>
        <div class="layui-inline">
            <a class="layui-btn" lay-submit="" lay-filter="searchForm">查询</a>
        </div>
        <div class="layui-inline">
            <a class="layui-btn layui-btn-normal" data-type="addEtask">添加批次</a>
        </div>
        <div class="layui-inline">
            <a class="layui-btn layui-btn-danger" data-type="deleteSome">批量删除</a>
        </div>
    </form>
    </div>
</fieldset>
<div class="layui-form users_list">
    <table class="layui-table" id="test" lay-filter="demo"></table>

    <script type="text/html" id="barDemo">
        <!-- 这里的 checked 的状态只是演示 -->
        <#--{{#  if(d.status == 1){ }}-->
        <#--<span class="layui-badge layui-bg-green">正在评教</span>-->
        <#--{{#  } else { }}-->
        <#--<span class="layui-badge layui-bg-gray">未开启</span>-->
        <#--{{#  } }}-->
        {{#  if(d.status == 0){ }}
        <a class="layui-btn layui-btn-xs" lay-event="open">开启</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="delete">删除</a>
        {{#  } else if(d.status == 1){ }}
        <a class="layui-btn layui-btn-xs layui-btn-warm" lay-event="suspend">暂停</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="end">结束</a>
        {{#  } else if(d.status == 2){ }}
        <a class="layui-btn layui-btn-xs" lay-event="open">开启</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="end">结束</a>
        {{#  } else if(d.status == 3){ }}
        <a class="layui-btn layui-btn-xs layui-btn-normal" lay-event="look">查看分数</a>
        {{#  } }}
    </script>
    <script type="text/html" id="etaskStatus">
        <#--<a class="layui-btn layui-btn-xs" lay-event="edit">开启</a>-->
        <#--<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>-->
        <#--<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>-->
        <#--<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">关闭</a>-->
        {{#  if(d.status == 1){ }}
        <button type="button" class="layui-btn layui-btn-sm" disabled="disabled">开启中</button>
        {{#  } else if(d.status == 0){ }}
        <button type="button" class="layui-btn layui-btn-sm layui-btn-primary" disabled="disabled">未开启</button>
        {{#  }else if(d.status == 2){ }}
        <button type="button" class="layui-btn layui-btn-sm layui-btn-warm" disabled="disabled">暂停中</button>
        {{#  }else if(d.status == 3){ }}
        <button type="button" class="layui-btn layui-btn-sm layui-btn-danger" disabled="disabled">已结束</button>
        {{#  } }}
    </script>
</div>
<div id="page"></div>
<script type="text/javascript" src="${base}/static/layui/layui.js"></script>
<script type="text/javascript" src="${base}/static/js/tools.js"></script>
<script>
    layui.use(['layer','form','table'], function() {
        var layer = layui.layer,
                $ = layui.jquery,
                form = layui.form,
                table = layui.table,
                t;                  //表格数据变量
//重构函数
        function batchRelode(){
            table.reload('demo', {
                page: {
                    curr: 1 //重新从第 1 页开始
                },
                url:"${base}/admin/system/etask/list"
            }, 't');
        }
        t = {
            elem: '#test',
            url:'${base}/admin/system/etask/list',
            method:'post',
            page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
                layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'], //自定义分页布局
                //,curr: 5 //设定初始在第 5 页
                groups: 2, //只显示 1 个连续页码
                first: "首页", //显示首页
                last: "尾页", //显示尾页
                limits:[3,10, 20, 30]
            },
            width: $(parent.window).width()-223,
            cols: [[
                {type:'checkbox'},
                {field:'name', title: '批次名称'},
                {field:'start_data', title: '开始时间',templet:'<div>{{ layui.laytpl.toDateString(d.startData) }}</div>',unresize: true},
                {field:'end_data', title: '结束时间',templet:'<div>{{ layui.laytpl.toDateString(d.endData) }}</div>',unresize: true},
                {field:'status', title: '评教状态',templet:'#etaskStatus'},
                {field:'nickName',  title: '创建者',templet:'<div>{{  d.user.nickName }}</div>'},
                {fixed: 'right',    width: '18%', align: 'center',toolbar: '#barDemo'}
            ]],
        };
        table.render(t);
        //监听工具条
        table.on('tool(demo)', function(obj){
            var data = obj.data;
            if(obj.event === 'edit'){
                var editIndex = layer.open({
                    title : "编辑批次",
                    type : 2,
                    content : "${base}/admin/system/etask/edit?id="+data.id,
                    success : function(layero, index){
                        setTimeout(function(){
                            layer.tips('点击此处返回批次列表', '.layui-layer-setwin .layui-layer-close', {
                                tips: 3
                            });
                        },500);
                    }
                });
                //改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
                $(window).resize(function(){
                    layer.full(editIndex);
                });
                layer.full(editIndex);
            }
            <#--if(obj.event === "del"){-->
                <#--layer.confirm("你确定要删除该批次么？",{btn:['是的,我确定','我再想想']},-->
                    <#--function(){-->
                        <#--$.post("${base}/admin/system/etask/delete",{"id":data.id},function (res){-->
                           <#--if(res.success){-->
                               <#--layer.msg("删除成功",{time: 1000},function(){-->
                                   <#--table.reload('test', t);-->
                               <#--});-->
                           <#--}else{-->
                               <#--layer.msg(res.message);-->
                           <#--}-->

                        <#--});-->
                    <#--}-->
                <#--)-->
            <#--}-->
            if(obj.event  === 'open'){ //开启按钮
                $.ajax({
                    url:'${base}/admin/system/etask/open',
                    type:'post',
                    data:{
                        id: data.id
                    },
                    success:function (res) {
                        layer.msg(res.result);
                        parent.location.reload();
                    }
                })
            } else if(obj.event  === 'suspend'){ //暂停
                $.ajax({
                    url:'${base}/admin/system/etask/suspend',
                    type:'post',
                    data:{
                        id: data.id
                    },
                    success:function (res) {
                        layer.msg(res.result);
                        parent.location.reload();
                    }
                })
            } else if(obj.event  === 'end'){ //结束
                $.ajax({
                    url:'${base}/admin/system/etask/end',
                    type:'post',
                    data:{
                        id: data.id
                    },
                    success:function (res) {

                        $.ajax({
                            url:'${base}/admin/system/statistical/cc',
                            type:'post',
                            data:{
                                batchId: data.id
                            },
                            success:function (res) {

                                layer.msg(res.result);
                                parent.location.reload();
                            }
                        });
                        layer.msg(res.result);
                        parent.location.reload();
                    }
                })
            } else if(obj.event  === 'delete'){ //删除
                $.ajax({
                    url:'${base}/admin/system/etask/delete',
                    type:'post',
                    data:{
                        id: data.id
                    },
                    success:function (res) {
                        layer.msg(res.result);
                        parent.location.reload();
                    }
                })
            }
        });

        //功能按钮
        var active={
            addEtask : function(){
                var addIndex = layer.open({
                    title : "添加批次",
                    type : 2,
                    content : "${base}/admin/system/etask/add",
                    success : function(layero, addIndex){
                        setTimeout(function(){
                            layer.tips('点击此处返回会员列表', '.layui-layer-setwin .layui-layer-close', {
                                tips: 3
                            });
                        },500);
                    }
                });
                //改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
                $(window).resize(function(){
                    layer.full(addIndex);
                });
                layer.full(addIndex);
            },
            deleteSome : function(){                        //批量删除
                var checkStatus = table.checkStatus('test'),
                     data = checkStatus.data;
                if(data.length > 0){
                    console.log(JSON.stringify(data));
                    for(var i=0;i<data.length;i++){
                        var d = data[i];
                        if(d.id === 1){
                            layer.msg("不能删除超级管理员");
                            return false;
                        }
                    }
                    layer.confirm("你确定要删除这些批次么？",{btn:['是的,我确定','我再想想']},
                        function(){
                            var deleteindex = layer.msg('删除中，请稍候',{icon: 16,time:false,shade:0.8});
                            $.ajax({
                                type:"POST",
                                url:"${base}/admin/system/etask/deleteSome",
                                dataType:"json",
                                contentType:"application/json",
                                data:JSON.stringify(data),
                                success:function(res){
                                    layer.close(deleteindex);
                                    if(res.success){
                                        layer.msg("删除成功",{time: 1000},function(){
                                            table.reload('test', t);
                                        });
                                    }else{
                                        layer.msg(res.message);
                                    }
                                }
                            });
                        }
                    )
                }else{
                    layer.msg("请选择需要删除的批次",{time:1000});
                }
            }
        };

        $('.layui-inline .layui-btn').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

        //搜索
        form.on("submit(searchForm)",function(data){
            t.where = data.field;
            table.reload('test', t);
            return false;
        });

    });
</script>
</body>
</html>