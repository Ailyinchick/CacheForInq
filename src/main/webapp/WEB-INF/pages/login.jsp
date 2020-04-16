<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Home</title>
    <meta charset="utf-8">
</head>
<body>
<p> Picture: ${picture}</p>
<p> Count: ${count} </p>


<form action="sendCar" method="post">

    <label>
        <select name="selectedCar">
            <c:forEach items="${cars}" var="car">
                <option> ${car.name} </option>
            </c:forEach>
        </select>
    </label>

    <input type="submit" value="Add to cache">

</form>


<c:forEach items="${cache}" var="item">
    ${item.image}
</c:forEach>

</body>
</html>