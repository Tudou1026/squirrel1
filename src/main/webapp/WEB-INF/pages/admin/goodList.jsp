<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>Squirrel后台管理系统</title>
    <script src="../js/jquery-3.1.1.min.js"></script>
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/jquery.bootgrid.min.css">
    <script src="../js/bootstrap.min.js"></script>
    <script src="../js/jquery.bootgrid.min.js"></script>
    <script src="../js/bootstrap-datetimepicker.min.js"></script>
    <link rel="stylesheet" href="../css/bootstrap-datetimepicker.min.css" type="text/css"></link>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <nav class="navbar navbar-inverse">
                <div class="container-fluid">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar">*****</span>
                        </button>
                        <a class="navbar-brand" href="#">Squirrel后台管理系统</a>
                    </div>
                    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                        <ul class="nav navbar-nav">
                            <li><a href="/user/logout">logout</a></li>
                        </ul>
                    </div>
                </div>
            </nav>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <h2>Squirrel商品信息</h2>
            <table id="grid-data" class="table table-condensed table-hover table-striped">
                <thead>
                <tr>
                    <th data-column-id="id"  data-identifier="true" data-type="numeric">商品编号</th>
                    <th data-column-id="catelogId">类别ID</th>
                    <th data-column-id="userId">用户ID</th>
                    <th data-column-id="name">商品名称</th>
                    <th data-column-id="price">商品价格</th>
                    <th data-column-id="percentNew">商品成色</th>
                    <th data-column-id="startTime">上架时间</th>
                    <th data-column-id="describle">商品描述</th>
                    <th data-column-id="commands" data-formatter="commands" data-sortable="false">Commands</th>
                </tr>
                </thead>
            </table>
        </div>
    </div>
</div>
<script>
    $(document).ready(function(){
        var grid = $("#grid-data").bootgrid({
            ajax:true,
            post: function ()
            {
                // return {
                //     id: "b0df282a-0d67-40e5-8558-c9e93b7acfhd"
                // };
            },
            url:"/admin/goods",
            formatters: {
                "commands": function(column, row)
                {
                    return "<button type=\"button\" class=\"btn btn-xs btn-default command-edit\" data-row-id=\"" + row.id + "\">审核<span class=\"fa fa-pencil\"></span></button> " +
                        "<button type=\"button\" class=\"btn btn-xs btn-default command-detail\" data-row-id=\"" + row.id + "\">详情<span class=\"fa fa-trash-o\"></span></button>";

                }
            }
        }).on("loaded.rs.jquery.bootgrid", function()
        {
            grid.find(".command-edit").on("click", function(e)
            {
                $(".stumodal").modal();
                $.post("/admin/getGoodsInfo",{goodsId:$(this).data("row-id")},function(str){
                    $("#goodsId2").val(str.id);
                    $("#goodsStatus2").val(str.checkStatus);
                });
            }).end().find(".command-detail").on("click", function(e)
            {
                $(".demodal").modal();
                $.post("/admin/getImage",{goodsId:$(this).data("row-id")},function(str){
                    // $("#imageUrl1").val(str.imgUrl);
                    // $("#imageUrl2").val('../upload/' + str.imgUrl);
                    // var img = document.getElementById('img');
                    // img.src = 'img/book.png';
                    $("#image").attr("src","../upload/" + str.imgUrl);
                   // $("#goodsStatus2").val(str.checkStatus);
                });
//                alert("You pressed delete on row: " + $(this).data("row-id"));
//                $.post("/stu/delStu",{stuId:$(this).data("row-id")},function(){
//                    alert("删除成功");
//                    $("#grid-data").bootgrid("reload");
//                });
            });
        });
    });

    $(document).ready(function(){
        $("#add").click(function(){
            $(".addmodal").modal();
        });
    });

</script>

<div class="modal fade demodal" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <%----%>
                <%--<div class="modal-body">--%>

                        <img id="image">

                    <%--<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>--%>
                    <%--<button type="submit" class="btn btn-primary">Save</button>--%>
                <%--</div>--%>
            <%--</form>--%>

            </div>
        </div>
    </div>
</div>


<div class="modal fade stumodal" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">商品审核</h4>
            </div>
            <form action="/goods/changeCheckStatus" method="post">
                <div class="modal-body">
                    <div class="form-group">
                        <label for="goodsId2">goodsId</label>
                        <input type="text" name="goodsId" class="form-control" id="goodsId2" readonly="true">
                    </div>

                    <div class="form-group">
                        <label for="goodsStatus2">goodsStatus(输入1表示审核通过)</label>
                        <input type="text" name="goodsStatus" class="form-control" id="goodsStatus2">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-primary">Save</button>
                </div>
            </form>
        </div>
    </div>
</div>

<div class="modal fade addmodal" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">添加用户</h4>
            </div>
            <form action="" method="post">
                <div class="modal-body">
                    <div class="form-group">
                        <label for="userName1">userName</label>
                        <input type="text" name="userName" class="form-control" id="userName1">
                    </div>
                    <div class="form-group">
                        <label for="userAge1">userAge</label>
                        <input type="text" name="userAge" class="form-control" id="userAge1">
                    </div>
                    <div class="form-group">
                        <label for="userMajor1">userMajor</label>
                        <input type="text" name="userMajor" class="form-control" id="userMajor1">
                    </div>
                    <div class="form-group">
                        <input type="hidden" name="Id" class="form-control" id="Id">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-primary">Add User</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
