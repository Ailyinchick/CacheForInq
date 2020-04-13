<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <meta charset="utf-8">
</head>
<body>
<p> Picture: ${count} </p>
<form action="nature">
    <input type="submit" name="Submit" value="Add nature">
</form>

<form action="car">
    <input type="submit" name="Submit" value="Add car">
</form>

<form action="bart">
    <input type="submit" name="Submit" value="Add bart">
</form>

<c:forEach items="${cache}" var="item">
    <p>
            ${item}
    </p>
</c:forEach>

</body>
</html>