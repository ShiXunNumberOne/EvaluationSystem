<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>在线评教系统---统计分析管理模块</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="${base}/static/layui/css/layui.css" media="all" />
    <link rel="stylesheet" href="${base}/static/css/user.css" media="all" />
    <script type="text/javascript" src="${base}/static/js/jquery.min.js"></script>
    <script type="text/javascript" src="${base}/static/js/echarts.js"></script>
    <script type="text/javascript" src="${base}/static/layui/layui.js"></script>
</head>
<style>
    @font-face {
        font-family: 'webfont';
        font-display: swap;
        src: url('//at.alicdn.com/t/webfont_bsba623q5hu.eot'); /* IE9*/
        src: url('//at.alicdn.com/t/webfont_bsba623q5hu.eot?#iefix') format('embedded-opentype'), /* IE6-IE8 */
        url('//at.alicdn.com/t/webfont_bsba623q5hu.woff2') format('woff2'),
        url('//at.alicdn.com/t/webfont_bsba623q5hu.woff') format('woff'), /* chrome、firefox */
        url('//at.alicdn.com/t/webfont_bsba623q5hu.ttf') format('truetype'), /* chrome、firefox、opera、Safari, Android, iOS 4.2+*/
        url('//at.alicdn.com/t/webfont_bsba623q5hu.svg#杨任东竹石体-Bold') format('svg'); /* iOS 4.1- */
    }
    .web-font{
        font-family:"webfont" !important;
        font-size:16px;font-style:normal;
        -webkit-font-smoothing: antialiased;
        -webkit-text-stroke-width: 0.2px;
        -moz-osx-font-smoothing: grayscale;
    }
    body{
        margin:0 auto;
        padding: 0 auto;
    }

    #top{
        width: 100%;
        height: 50px;
    }
    #contert{
        width: 100%;
        height: 450px;
    }
    #leftDiv{
        width: 600px;
        height:450px;
        float: left;
    }
    #rightDiv{
        width: 675px;
        height:450px;
        float: right;
    }
    #evaluation_Situation{
        width: 550px;
        height:25px;
        margin-left:25px;
    }
    #evaluation_top{
        width: 550px;
        height:25px;
    }
    .leftDivFristDiv{
        width: 200px;
        height: 190px;
        float: left;
    }
    .detailsFrist{
        width: 200px;
        height: 60px;
    }

    .details{
        width: 200px;
        height: 60px;
        margin-top: 5px;
    }
    .pieLeftOne{
        width: 250px;
        height:190px;
        float: right;;
    }
    .fontDiv{
        margin: 0 auto;
        margin-left: 40px;
    }
    .fontDiv{
        margin: 0 auto;
    }
    .fontsize{
        font-size: 15px;
        text-align: center;
    }
    .pieleft{
        width: 550px;
        height: 260px;
        float: left;
        margin-left: 15px;
    }
    #pieChart{
        width: 550px;
        height:260px;
        margin-left:25px;
    }

    .rightDivFrist{
        width: 675px;
        height:450px;
        margin-top: 10px;
    }
    .fountStyle{
        font-size: 25px;
        margin-top: 10px;
        text-align: center;
        color: #FFB800;
    }
</style>
<body>
<div id="top" >
    <div class="layui-form layui-inline" style="width: 200px">
        <select id="batchId" name="batchId"  lay-search>
            <option value="">选择批次</option>
        </select>
    </div>
    <div class="layui-form layui-inline" style="width: 200px">
        <button class="layui-btn" data-type="search" id="search" style="margin-left: 20px">查询</button>
    </div>


</div>
<div id="contert">
    <div id="leftDiv">
        <div id="evaluation_Situation">
            <div id="evaluation_top">
                <div style="width: 100px;height: 25px;margin-left: 20px">
                    <p class="web-font">评教详情</p>
                </div>
            </div>
            <div class="leftDivFristDiv">
                <div class="detailsFrist">
                    <div class="fontDiv">
                        <p  class="fontsize">学院数量:</p>
                        <p id="departmSize" class="fountStyle"></p>
                    </div>
                </div>
                <div class="details">
                    <div class="fontDiv">
                        <p class="fontsize" >老师人数:</p>
                        <p id="evaluationSize" class="fountStyle"></p>
                    </div>
                </div>

                <div class="details">
                    <div class="fontDiv">
                        <p  class="fontsize">参与评价人数:</p>
                        <p id="participateMen" class="fountStyle"></p>
                    </div>
                </div>
            </div>
            <div class="pieLeftOne">
                <div id="main" style="width: 250px;height:190px;"></div>
            </div>

        </div>

        <div id="pieChart">
            <div class="pieleft">
                <div id="barMain" style="width: 550px;height:260px;"></div>
            </div>
        </div>
    </div>


    <div id="rightDiv">
        <div class="rightDivFrist">
            <table id="demo" lay-filter="test"></table>
            <script type="text/html" id="barDemo">
                <a class="layui-btn layui-btn-xs" lay-event="detail" id="detail">详情</a>
            </script>
        </div>
    </div>
</div>
<script>
    layui.use(['form', 'upload', 'layer'], function () {
        $(function () {
            functionD()
        })

        $('#search').on('click', function(){
            batchId =   $("#batchId").val()
            functionD();
        });
    })

</script>
<script type="text/javascript">
    function dynamic_number(id,value) {
        var temp = id;
        temp.animate({count: value}, {
            duration: 2000,
            step: function () {
                temp.text(String(parseInt(this.count)));
            }
        });
    }
    function functionD() {
        $("#departmSize").text("")
        $("#evaluationSize").text("")
        $("#participateMen").text("")
        $("#GreatAvg").text("")
        var batchId =  $("#batchId").val()
        $.ajax({
            type:"post",
            url:"${base}/admin/system/statistical/evaluationTop",
            dataType:"json",
            data:{
                "batchId":batchId,
            },
            success:function (res) {
                dynamic_number($("#departmSize"),res[0].length);
                dynamic_number($("#evaluationSize"),res[1].length);
                dynamic_number($("#participateMen"),res[2].length);
                dynamic_number($("#GreatAvg"),res[3].length);
            }
        })

        /**
         * todo 柱状 图
         * @type {Array}
         */
        $.ajax({
            type: "post",
            async: true,
            url: "${base}/admin/system/statistical/querySocoreLimit5",
            data: {
                "batchId": batchId,
            },
            dataType: "json",
            success: function (result) {
                console.log(result)
                var app = {};
                var nameList = [];
                var xList =  [] ;
                nameList.push("总分");
                nameList.push("自评得分");
                nameList.push("教师评价总分");
                nameList.push("学生评价总分");
                var selfData = [];
                var teacherData = [];
                var studentData = [];
                var scoreData = [];
                for (var i = 0;i<result.length;i++){
                    xList.push(result[i].uname)
                    teacherData.push(result[i].student_score)
                    studentData.push(result[i].teacher_score)
                    selfData.push(result[i].self_score)
                    scoreData.push(result[i].score)
                }
                var TypeLineChart = echarts.init(document.getElementById('barMain'));
                var posList = [
                    'left', 'right', 'top', 'bottom',
                    'inside',
                    'insideTop', 'insideLeft', 'insideRight', 'insideBottom',
                    'insideTopLeft', 'insideTopRight', 'insideBottomLeft', 'insideBottomRight'
                ];

                app.configParameters = {
                    rotate: {
                        min: -90,
                        max: 90
                    },
                    align: {
                        options: {
                            left: 'left',
                            center: 'center',
                            right: 'right'
                        }
                    },
                    verticalAlign: {
                        options: {
                            top: 'top',
                            middle: 'middle',
                            bottom: 'bottom'
                        }
                    },
                    position: {
                        options: echarts.util.reduce(posList, function (map, pos) {
                            map[pos] = pos;
                            return map;
                        }, {})
                    },
                    distance: {
                        min: 0,
                        max: 100
                    }
                };

                app.config = {
                    rotate: 90,
                    align: 'left',
                    verticalAlign: 'middle',
                    position: 'insideBottom',
                    distance: 15,
                    onChange: function () {
                        var labelOption = {
                            normal: {
                                rotate: app.config.rotate,
                                align: app.config.align,
                                verticalAlign: app.config.verticalAlign,
                                position: app.config.position,
                                distance: app.config.distance
                            }
                        };
                        myChart.setOption({
                            series: [{
                                label: labelOption
                            }, {
                                label: labelOption
                            }, {
                                label: labelOption
                            }, {
                                label: labelOption
                            }]
                        });
                    }
                };


                var labelOption = {
                    normal: {
                        show: true,
                        position: app.config.position,
                        distance: app.config.distance,
                        align: app.config.align,
                        verticalAlign: app.config.verticalAlign,
                        rotate: app.config.rotate,
                        formatter: '{c}  {name|{a}}',
                        fontSize: 16,
                        rich: {
                            name: {
                                textBorderColor: '#fff'
                            }
                        }
                    }
                };

                option = {
                    color: ['#003366', '#006699', '#4cabce'],
                    tooltip: {
                        trigger: 'axis',
                        axisPointer: {
                            type: 'shadow'
                        }
                    },
                    legend: {
                        data: nameList
                    },
                    toolbox: {
                        show: true,
                        orient: 'vertical',
                        left: 'right',
                        top: 'center',
                        feature: {
                            mark: {show: true},
                            dataView: {show: true, readOnly: false},
                            magicType: {show: true, type: ['line', 'bar', 'stack', 'tiled']},
                            restore: {show: true},
                            saveAsImage: {show: true}
                        }
                    },
                    calculable: true,
                    xAxis: [
                        {
                            type: 'category',
                            axisTick: {show: false},
                            data: xList
                        }
                    ],
                    yAxis: [
                        {
                            type: 'value'
                        }
                    ],
                    series: [
                        {
                            name: '总分',
                            type: 'bar',
                            barGap: 0,
                            label: labelOption,
                            data: scoreData
                        },
                        {
                            name: '自评得分',
                            type: 'bar',
                            label: labelOption,
                            data: selfData
                        },
                        {
                            name: '教师评价总分',
                            type: 'bar',
                            label: labelOption,
                            data: teacherData
                        },
                        {
                            name: '学生评价总分',
                            type: 'bar',
                            label: labelOption,
                            data: studentData
                        }
                    ]
                };
                TypeLineChart.setOption(option);
            }
        })

        /**
         * todo pie 图
         * @type {Array}
         */
        var names=[];
        var nums=[];
        $.ajax({
            type : "post",
            async : true,
            url : "${base}/admin/system/statistical/queryPie",
            data:{
                "batchId":batchId,
            },
            dataType : "json",
            success : function(result) {
                var TypeSalesChart = echarts.init(document.getElementById('main'));
                var option = {
                    tooltip : {
                        trigger: 'item',
                        formatter: "{a} <br/>{b} : {c} ({d}%)"
                    },
                    legend: {
                        orient : 'vertical',
                        x : 'left',
                        itemWidth: 5,
                        itemHeight: 5,
                        data:(function(){
                            var res = [];
                            var len = result.length;
                            for(var i=0,size=len;i<size;i++) {
                                res.push({
                                    name: result[i].name,
                                });
                            }
                            return res;
                        })()

                    },
                    title : {
                        text: '教学质量 ',
                        x:'center'
                    },
                    series : [
                        {
                            name:'教学质量',
                            type:'pie',
                            radius : '55%',
                            center: ['50%', '60%'],
                            data:(function(){
                                var res = [];
                                var len = result.length;
                                for(var i=0,size=len;i<size;i++) {
                                    res.push({
                                        //通过把result进行遍历循环来获取数据并放入Echarts中
                                        name: result[i].name,
                                        value: result[i].size
                                    });
                                }
                                return res;
                            })()
                        }
                    ]
                };

                TypeSalesChart.setOption(option);
            }
        })

        layui.use('table', function() {
            var table = layui.table;
            //数据表表头
            table.render({
                elem: '#demo'
                // ,height: 400
                , url: '${base}/admin/system/statistical/querySocore'
                , page: true //开启分页
                ,cellMinWidth: 20 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
                ,skin: 'line ' //表格风格 line （行边框风格）row （列边框风格）nob （无边框风格）
                //,even: true    //隔行换色
                ,limits: [9,10,11]  //每页条数的选择项，默认：[10,20,30,40,50,60,70,80,90]。
                ,limit: 9 //每页默认显示的数量
                ,where:{
                    "batchId": batchId,
                }
                , cols: [
                    [ //表头
                        { title: '序号', toolbar: '#indexTpl',type:'numbers',width: '8%' , sort: true}
                        , {field: 'uname',  title: '姓名', width: '20%', align: 'center'}
                        , {field: 'self_score', title: '自评得分', width: '20%', align: 'center', sort: true}
                        , {field: 'teacher_score', title: '教师评价总分', width: '20%', align: 'center', sort: true}
                        , {field: 'student_score', title: '学生评价总分', width: '20%', align: 'center', sort: true}
                        , {field: 'score', title: '总分数', width: '20%', align: 'center', sort: true}
                        // , {title: '操作', width: '12%', align: 'center', toolbar: '#barDemo'}
                    ]
                ]
                ,done: function(res, curr, count){
                    $("[data-field='gradeds']").css('display','none'); //关键代码
                    $("[data-field='papers_id']").css('display','none'); //关键代码
                }
            })
            table.on('tool(test)', function(obj){
                var data = obj.data;
                var batchId =   $("#batchId").find("option:selected").text();

                console.log(obj)
                if(obj.event === 'detail'){
                    layer.open({
                        type: 2
                        ,skin: 'layui-layer-rim'
                        ,maxmin: true
                        ,area: ["1000px","500px"]
                        ,title: "评教详情"
                        ,content:"${base}/admin/system/statistical/godetails?gradeds="+obj.data.gradeds
                        ,success: function (layero,index) {
                            var body = layer.getChildFrame('body', index);
                            var iframeWin = window[layero.find('iframe')[0]['name']];
                            console.log(body.find("#batchId"))
                            if(papersId == "选择问卷" ){
                                body.find("#paperId").text("历年来所有问卷")
                            } else{
                                body.find("#paperId").text(papersId)
                            }
                            body.find("#teacherId").text(obj.data.user_name)
                            console.log(obj)
                            teacherID = obj.data.gradeds
                            papersIds = obj.data.papers_id
                            batchIds = $("#batchId").val()
                            iframeWin.selectDataCallBack(teacherID,papersIds,batchIds)
                        }
                    })
                }
            });
        })
    }
</script>

<script type="text/html" id="indexTpl">
    {{d.LAY_TABLE_INDEX+1}}
</script>
<!--动态生成角色下拉框-->
<script>
    layui.use(['form', 'upload', 'layer'], function () {
        $.ajax({
            url:"${base}/admin/system/statistical/queryBatchsList",
            dataType: 'json',
            type: 'get',
            success: function (data) {
                $.each(data, function (index,item) {
                    $('#batchId').append(new Option(item.name,item.id));
                });
                layui.form.render("select");
            }
        })
    });
</script>
</body>
</html>