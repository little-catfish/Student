<%@ page import="cn.service.StudentService" %>
<%@ page import="cn.service.Impl.StudentServiceImpl" %>
<%@ page import="cn.domain.Student" %>
<%@ page import="cn.domain.PageBean" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <!-- 使用Edge最新的浏览器的渲染方式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。
    width: 默认宽度与设备的宽度相同
    initial-scale: 初始的缩放比，为1:1 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>学生信息管理系统</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
    <style type="text/css">
        td, th {
            text-align: center;
        }
    </style>
    <script>
        function deleteUser(id) {
            if(confirm("你确定要删除吗?")){
                location.href="${pageContext.request.contextPath}/DelUserServlet?id="+id;
            }
        }
        window.onload = function () {
            document.getElementById("delSelected").onclick=function () {
                if(confirm("你确定要删除选中条目吗")){
                       var  flag = false;
                    var cbs = document.getElementsByName("uid");
                    for (var i = 0; i <cbs.length ; i++) {
                        if(cbs[i].checked){
                            flag= true;
                            break;
                        }
                    }
                    if (flag) {
                        document.getElementById("form").submit();
                    }
                }
            }
            document.getElementById("firstcb").onclick = function () {
                var cbs = document.getElementsByName("uid");
                for (var i = 0; i <cbs.length ; i++) {
                    cbs[i].checked = this.checked;
                }
            }

        }






    </script>
</head>
<body>
<div class="container">
    <h3 style="text-align: center">学生信息列表</h3>

    <div style="float: left;">



    </div>

    <div style="float: right;margin: 5px;">

        <a class="btn btn-primary" href="add.jsp">添加学生</a>
        <a class="btn btn-primary" href="javascript:void(0);" id="delSelected">删除选中</a>

    </div>
  <form  id="form" action="${pageContext.request.contextPath}/DelSelectedServlet" method="post">
    <table border="1" class="table table-bordered table-hover">
        <tr class="success">
            <th><input type="checkbox" id="firstcb"></th>
            <th>编号</th>
            <th>学生id</th>
            <th>姓名</th>
            <th>出生日期</th>
            <th>备注</th>
            <th>平均分</th>
            <th>修改项</th>
        </tr>

        <c:forEach items="${pb.list}" var="user" varStatus="s">
            <tr>
                <td><input type="checkbox" name="uid" value="${user.id}"></td>
                <td>${s.count}</td>
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.birthday}</td>
                <td>${user.description}</td>
                <td>${user.avgScore}</td>
                <td>
                    <a class="btn btn-default btn-sm" href="${pageContext.request.contextPath}/FindUserServlet?id=${user.id}">修改</a>&nbsp;
                    <a class="btn btn-default btn-sm" href="${pageContext.request.contextPath}/DelUserServlet?id=${user.id}">删除</a>
                </td>
            </tr>

        </c:forEach>


    </table>
  </form>
    <div>
        <nav aria-label="Page navigation">
            <ul class="pagination">
                <c:if test="${pb.currentPage==1}">
                    <li class="disabled">
                        <a href="#" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                </c:if>

                    <c:if test="${pb.currentPage!=1}">
                    <li>
                    <a href="${pageContext.request.contextPath}/FindUserByPageServlet?currentPage=${pb.currentPage-1}&rows=10" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
                    </c:if>

                <li>
                    <c:forEach begin="1" end="${pb.totalPage}" var="i">
                        <c:if test="${pb.currentPage==i}">
                        <li class="active"><a href="${pageContext.request.contextPath}/FindUserByPageServlet?currentPage=${i}&rows=10">${i}</a></li>
                         </c:if>
                <c:if test="${pb.currentPage!=i}">
                    <li><a href="${pageContext.request.contextPath}/FindUserByPageServlet?currentPage=${i}&rows=10">${i}</a></li>
                </c:if>

                </c:forEach>
                </li>
                <c:if test="${pb.currentPage!=pb.totalPage}">
                <li>
                    <a href="${pageContext.request.contextPath}/FindUserByPageServlet?currentPage=${pb.currentPage+1}&rows=10" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
            </c:if>

                <c:if test="${pb.currentPage==pb.totalPage}">
                    <li class="disabled">
                        <a href="#" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </c:if>


                <span style="font-size: 25px;margin-left: 5px;">
                    共${pb.totalCount}条记录，共${pb.totalPage}页
                </span>

            </ul>
        </nav>


    </div>


</div>


</body>
</html>
