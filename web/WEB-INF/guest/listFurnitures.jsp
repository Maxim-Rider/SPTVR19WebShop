

<%@page contentType="text/html" pageEncoding="UTF-8"%>

        <h1>Список товаров:</h1>

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
        