<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <title>Home</title>
    <meta charset="utf-8">
</head>
<body>
<p> Picture: ${picture}</p>
<p> Count: ${count} </p>

<form action="sendCar" method="post">

    <label>
        <select name="selectedCar" id="selectedCar">
            <c:forEach items="${cars}" var="car">
                <option> ${car.name} </option>
            </c:forEach>
        </select>
    </label>

    <input type="submit" value="Add to cache">

</form>

<div id="carImages">
    <c:forEach items="${cache}" var="item">
        ${item.image}
    </c:forEach>
</div>

<script>

    var somefanc2 = function () {
        $.ajax({
            url: "/ajaxURL",
            type: "GET"
        })
            .done(function (data) {
                setContent(data);
            })
            .fail(function (xhr, status, error) {
                alert(xhr.responseText + '|\n' + status + '|\n' + error);
            })
    };

    var setContent = function (data) {
        document.getElementById("carImages").innerHTML = data;
    };

    setInterval(somefanc2, 100);

</script>

</body>
</html>