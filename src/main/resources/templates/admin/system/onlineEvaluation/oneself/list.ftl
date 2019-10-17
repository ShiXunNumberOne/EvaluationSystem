<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>自评--layui后台管理模板</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="${base}/static/layui/css/layui.css" media="all" />
    <link rel="stylesheet" href="${base}/static/css/user.css" media="all" />
    <script type="text/javascript" src="${base}/static/js/jquery.min.js"></script>
    <script type="text/javascript" src="${base}/static/layui/layui.js"></script>
</head>
<body class="childrenBody">
<!--搜索框-->
<div class="demoTable">
    批次:
    <div class="layui-form layui-inline" style="width: 200px">
        <select id="batch_name" name="batch_name"  lay-search>
            <option value=""></option>
        </select>
    </div>
</div>
<!--动态生成批次下拉框-->
<script>
    layui.use(['form', 'upload', 'layer','table'], function () {
        $.ajax({
            url: '/admin/system/onlineEvaluation/selectBatchName',
            dataType: 'json',
            type: 'get',
            success: function (data) {
                $.each(data.data, function (index,item) {
                    $('#batch_name').append(new Option(item.name,item.id));// 下拉菜单里添加元素
                });
                layui.form.render("select");
            }
        })
        var table = layui.table;
        var form = layui.form
        form.on('select',function (data) {
            console.log(data.value)
            if(data.value!=""){
                table.reload("idTest",{
                    url:"/admin/system/onlineEvaluation/selectBatchIdOneselfEvaluation"
                    ,where: {
                        batch_id:data.value
                    }
                })
            }else {
                table.reload("idTest",{
                    url:"/admin/system/onlineEvaluation/selectOneselfEvaluation"
                })
            }

        })
    });

</script>

<!--生成表格-->
<script type="text/html" id="xuHao">
    {{d.LAY_INDEX}}
</script>
<table class="layui-table" lay-data="{width:1100,url:'/admin/system/onlineEvaluation/selectOneselfEvaluation'}" lay-filter="demo">
    <thead>
    <tr>
        <th lay-data="{field: '序号',templet: '#xuHao',sort: true,width:80}">序号</th>
        <th lay-data="{field:'ename',width:160}">批次</th>
        <th lay-data="{field:'dname',width:90}">学院</th>
        <th lay-data="{field:'ccode',sort: true,width:160}">课程代码</th>
        <th lay-data="{field:'cname',sort: true,width:160}">课程名称</th>
        <th lay-data="{field:'nick_name',width:160}">老师</th>
        <th lay-data="{field:'rname',width:160}">用户角色</th>
        <th lay-data="{title:'操作', toolbar: '#barDemo'}"></th>
    </tr>
    </thead>
</table>

<!--表格的操作-->
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="edit">开始评教</a>
</script>


<script>
    layui.use('table', function(){
        var table = layui.table;
        var form = layui.form
        //监听工具条
        table.on('tool(demo)', function(obj){
            var data = obj.data;
            console.log(data.cid)
            if(obj.event === 'edit'){//开始评教
                $.ajax({
                    type: "get",
                    url: "/admin/system/onlineEvaluation/selectIfStartEvaluation",
                    data: {
                        batch_Id: data.eid
                    },
                    success: function (res) {
                        if(res.data == 1){
                            $.ajax({
                                type:"get",
                                url:"/admin/system/onlineEvaluation/selectIfEvaluation",
                                data:{
                                    gradeds:data.id,
                                    course_id:data.cid,
                                    etask_id:data.eid,
                                },
                                success:function (res) {
                                    if(res.data==1){
                                        layer.msg('已经完成评教',{icon:2})
                                    }else{
                                        layer.open({
                                            type:2
                                            ,skin:'layui-layer-rim'//加上边框
                                            // ,maxmin: true //开启最大化最小化按钮
                                            ,area: ["100%","100%"]
                                            ,title: "欢迎进入,请全部填写完在提交"
                                            ,content:"/admin/system/questionnaire/oneself/GoOnlineEvaluation"
                                            ,success: function (layero,index) {
                                                var body = layer.getChildFrame('body', index);
                                                var iframe = window['layui-layer-iframe' + index];
                                                // 向子页面的全局函数
                                                iframe.inputDataHandle(obj);
                                            }

                                        })
                                    }
                                }
                            })
                        }else{
                            layer.msg('评教已关闭',{icon:2})
                        }
                    }
                })



            }else if(obj.event === 'detail'){
                $.ajax({
                    type:"get",
                    url:"/ColleagueEvaluation/selectIfEvaluation",
                    data:{
                        gradeds:data.user_id,
                        papers_id:data.papers_id,
                        courses_id:data.courses_id
                    },
                    success:function (res) {
                        if(res.data==1){
                            layer.open({
                                type:2
                                ,skin:'layui-layer-rim'//加上边框
                                // ,maxmin: true //开启最大化最小化按钮
                                ,area: ["100%","100%"]
                                ,title: "欢迎进入，请全部填写完在提交"
                                ,content:"/SelectOnlineEvaluationController/GoSelectOnlineEvaluation"
                                ,success: function (layero,index) {
                                    var body = layer.getChildFrame('body', index);
                                    var iframe = window['layui-layer-iframe' + index];
                                    // 向子页面的全局函数
                                    iframe.inputDataHandle(obj);
                                }
                            })
                        }else{
                            layer.msg('还未评教',{icon:2})
                        }
                    }
                })
            }
        });


        $('.demoTable .layui-btn').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

    });
</script>
</body>
</html>