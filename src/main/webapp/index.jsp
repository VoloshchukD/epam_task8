<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Main</title>
    <%@ include file="jsp/header.jsp" %>
</head>
<body>
<div class="card" style="width: 18rem;">
    <div class="card-body">
        <h5 class="card-title">Card title</h5>
        <button type="button" class="btn btn-primary">Primary</button>
        <button type="button" class="btn btn-primary">Primary</button>
        <%--        <input type="hidden" name="command" value="registration">--%>
    </div>
</div>

<h3>File Upload:</h3>
Select a file to upload: <br/>
<form method="POST" action="upload" enctype="multipart/form-data" >
    File:
    <input type="file" name="file" id="file" /> <br/>
    Destination:
    <input type="text" value="/tmp" name="destination"/>
    </br>
    <input type="submit" value="Upload" name="upload" id="upload" />
</form>


</body>
</html>