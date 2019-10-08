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
<form class="layui-form">


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
    function inputDataHandle(obj,user_id_session){
        console.log(obj)
        console.log(user_id_session)
        layui.use(['form', 'upload', 'layer'], function () {
            var form = layui.form;
            $.ajax({
                url:"/admin/system/questionnaire/selectOnlineEvaluation",
                type:"get",
                data:{papers_id:obj.data.papers_id},
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
                var rater = user_id_session;//评分人
                var gradeds = obj.data.user_id;//被评分人
                var papers_id = obj.data.papers_id;//批次id
                var courses_id = obj.data.courses_id;//课程id
                console.log(obj.data)
                console.log("评分人id:"+rater)
                console.log("被评分人id:"+gradeds)
                console.log("批次id:"+papers_id)
                console.log("课程id:"+courses_id)

                //答案
                var answers = $("#targetOptions input:radio:checked").map(function (index, elem) {
                    return $(elem).val();
                }).get().join(',');

                console.log("答案："+answers);



                console.log("结束")
            })
        })

    }
</script>