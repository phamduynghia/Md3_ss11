<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<button><a href="<%=request.getContextPath()%>/bai1">Bài 1</a></button>
<button><a href="<%=request.getContextPath()%>/bai2">Bài 2</a></button>
<button><a href="<%=request.getContextPath()%>/bai3">Bài 3</a></button>
<button><a href="<%=request.getContextPath()%>/bai4">Bài 4</a></button>
</body>
</html>