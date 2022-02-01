<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 1/6/2022
  Time: 1:53 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
</head>

<body>

    <g:each in="${books}" var="book">


        ${book.title}
        -
        ${book.published}

        <br>

    </g:each>

</body>
</html>