<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>主页</title>
</head>
<body>
<h1>
    <%=request.getSession().getAttribute("username")%>,欢迎您
</h1>
</body>
</html>
