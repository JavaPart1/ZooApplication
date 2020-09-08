<html>
<head>
    <title>Spring MVC Hello World</title>
</head>

<body>
<h2>All Employees in System</h2>

<table border="1">
    <tr>
        <th>Visitor Id</th>
        <th>Visitor Name</th>
    </tr>
    <c:forEach items="${visitors}" var="visitor">
        <tr>
            <td>${visitor.id}</td>
            <td>${visitor.name}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
