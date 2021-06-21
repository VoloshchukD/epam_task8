<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Validator</title>
    <%@ include file="header.jsp" %>
</head>
<body>
<h3>Validate file</h3>
<form method="POST" action="disp" enctype="multipart/form-data">
    <input type="hidden" name="command" value="validator" id="command"/>
    Target File:
    <input type="file" name="target" id="target"/> <br/>
    Schema File:
    <input type="file" name="schema" id="schema"/> <br/>
    <input type="submit" value="Validate" name="validate" id="validate"/>
</form>
</body>
</html>
