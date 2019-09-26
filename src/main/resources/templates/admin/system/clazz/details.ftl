<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>班级列表--${site.name}</title>
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
<body>
<div style="width: 100%;">
    <table class="layui-table" lay-size="lg">
        <colgroup>
            <col  style="background-color: #f8f8f8; " width="150">
            <col width="200">
            <col  style="background-color: #f8f8f8;" width="150">
            <col width="200">
        </colgroup>

        <tbody>
        <tr>
            <td >班级编号</td>
            <td id="class_id"></td>
            <td>班级名称</td>
            <td id="class_name">16级软件工程20班</td>

        </tr>
        <tr>
            <td>班级人数</td>
            <td id="class_count">25</td>
            <td>所属院系</td>
            <td id="departments_name">软件学院</td>
        </tr>
        </tbody>

    </table>
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 10px;">
        <legend>班级人员</legend>
    </fieldset>

    <!--<div class="layui-btn-group demoTable">-->
    <!--<button class="layui-btn" data-type="getCheckData">获取选中行数据</button>-->
    <!--<button class="layui-btn" data-type="getCheckLength">获取选中数目</button>-->
    <!--<button class="layui-btn" data-type="isAll">验证是否全选</button>-->
    <!--</div>-->
    <script type="text/html" id="userGender">
        <!-- 这里的 checked 的状态只是演示 -->
        {{#  if(d.gender == 0){ }}
        <span >男</span>
        {{#  } else { }}
        <span>女</span>
        {{#  } }}
    </script>
    <table  class="layui-table" lay-data="{ url:'${base}/admin/system/clazz/classList/', page:false , id:'idTest'}"lay-filter="demo" >
        <thead>
        <tr>
            <th lay-data="{type:'checkbox', fixed: 'left'}"></th>
            <th lay-data="{field:'login_name',  width:350,sort: true, fixed: true}">学号</th>
            <th lay-data="{field:'nick_name',width:350,fixed: true}">姓名</th>
            <th lay-data="{field:'gender',fixed: true,templet:'#userGender'}">性别</th>
            <th lay-data="{fixed: 'right', width:172, align:'center', toolbar: '#barDemo'}"></th>
        </tr>
        </thead>
    </table>

    <script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    </script>



</div>


</body>
<script type="text/javascript" src="/static/layui/layui.js"></script>
<script>
console.log(parent.a)


    layui.use(['form', 'layedit', 'laydate','table','jquery'], function() {
        var form = layui.form
            , layer = layui.layer
            , layedit = layui.layedit
            , laydate = layui.laydate
            , $ = layui.jquery
            , table = layui.table;

        console.log("子页面的班级名称"+parent.clazz_name)
        console.log("子页面的班级编号:"+parent.clazz_code)
        console.log("子页面的所属院系:"+parent.dept_name)

        $(function () {
            $("#class_id").text(parent.clazz_code)
            $("#class_name").text(parent.clazz_name)
            $("#departments_name").text(parent.dept_name)
            $.ajax({
                url:"${base}/admin/system/clazz/classList",
                dataType: 'json',
                type: 'get',
                data:{},
                success:function (e) {
                      console.log(e)
                    $("#class_count").text(e.count)
                }
            })
        })
        table.reload('idTest', {
            page: {
                curr: 1 //重新从第 1 页开始
            }
            ,url:'${base}/admin/system/clazz/classList',
        }, 'data');

        //console.log( $("#a").attr("lay-data"))
        table.on('checkbox(demo)', function(obj){
            //  console.log(obj)
        });

        <#--table.reload('idTest', {-->
            <#--page: {-->
                <#--curr: 1 //重新从第 1 页开始-->
            <#--},-->
            <#--url:'${base}/admin/system/clazz/classList'-->
        //     ,where: {
        //         clazz_Id:parent.classid
        //     }
        // }, 'data');
        <#--//监听工具条-->
        table.on('tool(demo)', function(obj){
            var data = obj.data;
            if(obj.event === 'detail'){
                console.log(data)
            } else if(obj.event === 'del'){
                layer.confirm('真的删除行么', function(index){
                    obj.del();
                    layer.close(index);
                });
            } else if(obj.event === 'edit'){
                console.log(data)

            }
        });

        var $ = layui.$, active = {
            getCheckData: function(){ //获取选中数据
                var checkStatus = table.checkStatus('idTest')
                    ,data = checkStatus.data;
                layer.alert(JSON.stringify(data));
            }
            ,getCheckLength: function(){ //获取选中数目
                var checkStatus = table.checkStatus('idTest')
                    ,data = checkStatus.data;
                layer.msg('选中了：'+ data.length + ' 个');
            }
            ,isAll: function(){ //验证是否全选
                var checkStatus = table.checkStatus('idTest');
                layer.msg(checkStatus.isAll ? '全选': '未全选')
            }
        };

        $('.demoTable .layui-btn').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

    });



</script>

</html>