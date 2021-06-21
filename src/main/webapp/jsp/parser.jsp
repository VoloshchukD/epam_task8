<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Parser</title>
    <%@ include file="header.jsp" %>
</head>
<body>
<h3>Parse file</h3>
<form method="POST" action="disp" enctype="multipart/form-data">
    <input type="hidden" name="command" value="parser" id="command"/>
    File:
    <input type="file" name="target" id="target"/> <br/>
    Choose parser:
    <select size="1" name="parser" id="parser">
        <option value="DOM">DOM</option>
        <option value="SAX">SAX</option>
        <option value="STAX">STAX</option>
    </select> <br/>
    <input type="submit" value="Parse" name="parse" id="parse"/>
</form>

</body>
</html>
