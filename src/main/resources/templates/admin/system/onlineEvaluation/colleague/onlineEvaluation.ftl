<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>问卷--layui后台管理模板</title>
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
<body>
<form class="layui-form" action="">


    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card" id="targetOptions">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button type="submit" class="layui-btn" id="Submission">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>

<script>
    function inputDataHandle(obj,user_id){
        console.log(obj)
        console.log(user_id)
        layui.use(['form', 'upload', 'layer'], function () {
            var form = layui.form;
            $.ajax({
                url:"/admin/system/questionnaire/selectColleagueOnlineEvaluation",
                type:"get",
                success:function (res) {
                    var a=res.data
                    var b
                    var i=1
                    for(var index in a) {
                        b=a[index].options
                        $('#targetOptions').append('<div class="layui-card-header" style="background-color: #F2F2F2;" >\n' +
                            '            <label class="layui-form-label" style="font-size: 18px;width: 30%;text-align: left">'+i+'.'+a[index].target_name+'</label>\n' +

                            '        </div>');
                        i++
                        for(var index1 in b) {
                            $('#targetOptions').append('<div class="layui-card-body">\n' +
                                '            <span>\n' +
                                '                <input lay-filter="options" type="radio"\n' +
                                '                       name="'+a[index].target_id+'" value="'+b[index1].options_id+'" title="'+b[index1].options_content+'" lay-skin="primary">\n' +
                                '            </span>\n' +
                                '        </div>')
                        };

                    };




                    layui.form.render();
                }
            })

            //全部渲染
            form.render();

            $("#Submission").click(function (e) {
                e.preventDefault()
                console.log("开始")
                var rater =user_id;//评分人
                var gradeds = obj.data.id;//被评分人
                var etask_id = obj.data.eid;//批次id
                var courses_id = obj.data.cid;//课程id

                console.log("评分人id:"+rater)
                console.log("被评分人id:"+gradeds)
                console.log("批次id:"+etask_id)
                console.log("课程id:"+courses_id)

                //答案
                var answers = $("#targetOptions input:radio:checked").map(function (index, elem) {
                    return $(elem).val();
                }).get().join(',');

                console.log("答案："+answers);
                //指标
                var target_name_id = $("#targetOptions input:radio:checked").map(function (index, elem) {
                    return $(elem).attr("name");
                }).get().join(',');
                console.log("target_name_id:"+target_name_id)
                var target_length = $("#targetOptions label").length //有多少选项

                //答案的长度
                var answers_length = $("#targetOptions input:radio:checked").map(function (index, elem) {
                    return $(elem).val();
                })


                //判断是否都有选择
                if(answers_length.length>=target_length){
                    $.ajax({
                        type:"get",
                        url:"/admin/system/onlineEvaluation/OnlineEvaluationFraction",
                        data:{
                            eavaluationId:rater,
                            earnedId:gradeds,  //被评人id
                            questionnaireId:etask_id,//批次ID
                            courses_id:courses_id,//课程id
                            answers:answers,      //答案id
                            optionsAll_id:answers,//答案id
                            target_name_id:target_name_id//指标id
                        },
                        success:function (res) {
                            if (res.data==1){
                                layer.msg('评教完毕',{icon:1})
                                var index = parent.layer.getFrameIndex(window.name);
                                parent.layer.close(index);

                            } else{
                                layer.msg('评教失败',{icon:2})
                            }
                        }
                    })
                }else {
                    layer.msg('有未选择题目',{icon:2})
                }



                console.log("结束")
            })
        })
    }
</script>