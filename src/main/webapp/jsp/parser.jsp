<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Parser</title>
    <%@ include file="header.jsp" %>
</head>
<body>


<form method="POST" action="disp" enctype="multipart/form-data" >
    <input type = "hidden" name = "command" value = "parser" id="command"/>
    File:
    <input type="file" name="target" id="target" /> <br/>
    <input type="submit" value="Parse" name="parse" id="parse" />
</form>

</body>
</html>
