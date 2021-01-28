
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Список товаров</title>
    </head>
    <body>
        <h1>Список товаров:</h1>
        <a href="index.jsp"><Магазин></a><br>
        <ol>
            <form action="editFurnitureForm" method="POST">
            <select name="furnitureId" multiple="true">
                <option value="">Список товаров:</option>
            <c:forEach var="furniture" items="${listFurnitures}" varStatus="status">
                <li>
                    <option>
                    Товар: ${furniture.name} 
                    | Цвет: ${furniture.color} 
                    | Размер: ${furniture.size} 
                    | Количество: ${furniture.quantity} 
                    | Цена: ${furniture.price}$
                    </option>
                </li>
            </c:forEach>
            </select>
            <input type="submit" value="Изменить выбранный товар">
        </ol>
    </body>
</html>
