<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
    <meta charset="utf-8">
</head>
<body>
<p> Picture: ${picture}</p>
<p> Count: ${count} </p>
<c:forEach items="${cars}" var="car">
<form action="/${car.name}">
    <input type="submit" value="Add ${car.name}">
</form>
</c:forEach>

<c:forEach items="${cache}" var="item">
    ${item.image}
</c:forEach>

</body>
</html>