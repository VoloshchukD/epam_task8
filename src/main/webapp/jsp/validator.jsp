<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Validator</title>
    <%@ include file="header.jsp" %>
</head>
<body>
<form action="disp" method="post">
    <input type="hidden" name="command" value="validator"/>
    Attach schema file:<br/>
    <input type="file" name="schema"/><br/>
    Attach target file:<br/>
    <input type="file" name="target"/><br/>
    <input type="submit" value="Validate"/><br/>
</form>
</body>
</html>
