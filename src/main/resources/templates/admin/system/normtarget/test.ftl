<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title></title>
    <meta name="description" content="">
    <meta name="keywords" content="">
    <link href="" rel="stylesheet">
    <link rel="stylesheet" href="https://static.mysiteforme.com/layui-treetable/layui/css/layui.css">
    <style>
        .layui-table .value_col{
            text-align: center;
        }
    </style>
</head>

<body style="margin:10px 10px 0;">
<fieldset class="layui-elem-field">
    <legend>指标</legend>
    <div class="layui-field-box">
    <div class="layui-inline">
        <a class="layui-btn layui-btn-normal" data-type="addNormtarget">添加指标类别</a>
    </div>
    </div>
</fieldset>
<div class="layui-form users_list">
    <div id="demo"></div>
</div>
</body>
<script src="https://static.mysiteforme.com/layui-treetable/layui/layui.js"></script>
<script type="text/javascript" src="${base}/static/js/jquery.min.js"></script>

<script type="text/javascript">
    layui.use(['tree', 'layer'], function() {
        var layer = layui.layer,
             $ = layui.jquery;

        var layout = [
            { name: '指标名称',
                treeNodes: true,
                headerClass: 'value_col',
                style: 'width: 5%',
            },
            // {
            //     name: '排序',
            //     headerClass: 'value_col',
            //     colClass: 'value_col',
            //     style: 'width: 5%;text-align: center;',
            //     render:function(row){
            //         return undefined === row.sortCode?"" : row.sortCode;
            //     }
            // },
            {
                name: '权重',
                headerClass: 'value_col',
                colClass: 'value_col',
                style: 'width: 5%;text-align: center;',
                render:function(row){
                    return undefined === row.entropy?"" : row.entropy;
                }
            },
            {
                name: '操作',
                headerClass: 'value_col',
                colClass: 'value_col',
                style: 'width: 30%;text-align: center;',
                render: function(row) {
                    return '<a class="layui-btn layui-btn-normal layui-btn-sm" onclick="addChildNormtarget(' + row.id + ')"><i class="layui-icon">&#xe654;</i> 添加评教指标</a>' +
                            '<a class="layui-btn layui-btn-normal layui-btn-sm" onclick="editChildNormtarget(' + row.id + ')"><i class="layui-icon">&#xe642;</i>修改评教指标</a>' +
                            '<a class="layui-btn layui-btn-danger layui-btn-sm" onclick="delNormtarget(' + row.id + ')"><i class="layui-icon">&#xe640;</i> 删除</a>';
                }
            }
        ];

        var setTree = function(data,layout){
            $("#demo").empty();
            layui.treeGird({
                elem: '#demo', //传入元素选择器
                nodes: data,
                layout: layout
            });
        };

        $(function(){
            $.post("${base}/admin/system/normtarget/treelist",function(res){
                if(res.success){
                    setTree(res.data,layout);
                }else{
                    layer.msg(res.message);
                }
            });
        });

        var active={
            addNormtarget : function(){
                var addIndex = layer.open({
                    title : "添加指标类别",
                    type : 2,
                    content : "${base}/admin/system/normtarget/add",
                    success : function(layero, addIndex){
                        setTimeout(function(){
                            layer.tips('点击此处返回角色列表', '.layui-layer-setwin .layui-layer-close', {
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
            }
        };

        $('.layui-inline .layui-btn').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

    });

    var addChildNormtarget = function(data){
        var addIndex = layer.open({
            title : "添加评教指标",
            type : 2,
            content : "${base}/admin/system/menu/add?parentId="+data,
            success : function(layero, addIndex){
                setTimeout(function(){
                    layer.tips('点击此处返回角色列表', '.layui-layer-setwin .layui-layer-close', {
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
    };

    var editChildNormtarget = function(data){
        var editIndex = layer.open({
            title : "编辑菜单",
            type : 2,
            content : "${base}/admin/system/menu/edit?id="+data,
            success : function(layero, index){
                setTimeout(function(){
                    layer.tips('点击此处返回会员列表', '.layui-layer-setwin .layui-layer-close', {
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
    };
    var delNormtarget =function(data){
        layer.confirm("你确定要删除该菜单么？这将会使得其下的所有子菜单都删除",{btn:['是的,我确定','我再想想']},
                function(){
                    $.post("${base}/admin/system/menu/delete",{"id":data},function (res){
                        if(res.success){
                            layer.msg("删除成功",{time: 1000},function(){
                                location.reload();
                            });
                        }else{
                            layer.msg(res.message);
                        }
                    });
                }
        )
    };
</script>

</html>