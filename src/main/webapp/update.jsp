<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
    <head>

        <!-- 指定字符集 -->
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>修改学生</title>

        <link href="css/bootstrap.min.css" rel="stylesheet">
        <script src="js/jquery-2.1.0.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        
    </head>
    <body>
        <div class="container" style="width: 400px;">
        <h3 style="text-align: center;">修改学生信息</h3>
        <form action="${pageContext.request.contextPath}/UpdateUserServlet" method="post">
            <input type="hidden" name="id"  value="${user.id}">
          <div class="form-group">
            <label for="name">姓名：</label>
            <input type="text" class="form-control" id="name" name="name"  value="${user.name}" readonly="readonly" placeholder="请输入姓名" />
          </div>


            <div class="form-group">
            <label for="birthday">生日：</label>
            <input type="text" class="form-control" id="birthday"  name="birthday" value="${user.birthday}" placeholder="请输入生日" />
          </div>


          <div class="form-group">
            <label for="description">备注：</label>
            <input type="text" class="form-control"   id="description" name="description"  value="${user.description}" placeholder="请输入备注"/>
          </div>

          <div class="form-group">
            <label for="avgScore">平均分：</label>
            <input type="text" class="form-control" name="avgScore" value="${user.avgScore}" placeholder="请输入平均分"/>
          </div>

             <div class="form-group" style="text-align: center">
                 <input class="btn btn-default btn-sm" type="submit" value="提交" />
                 <input class="btn btn-default btn-sm" type="reset" value="重置" />
                 <a class="btn btn-default btn-sm" href="${pageContext.request.contextPath}/FindUserByPageServlet">返回</a>&nbsp;
             </div>
        </form>
        </div>
    </body>
</html>