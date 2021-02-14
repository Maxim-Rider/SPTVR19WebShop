<%-- 
    Document   : editFurnitureForm
    Created on : 16.12.2020, 11:33:31
    Author     : Comp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

        <h1>Редактирование товара</h1>

        <form action="editFurniture" method="POST">
            <input type="hidden" name="furnitureId" value="${furniture.id}">
            Название товара: <input type="text" name="name" value="${furniture.name}"><br>
            Цвет: <input type="text" name="color" value="${furniture.color}"><br>
            Размер: <input type="text" name="size" value="${furniture.size}"><br>
            Количество: <input type="text" name="quantity" value="${furniture.quantity}"><br>
            Цена: <input type="text" name="price" value="${furniture.price}"><br>
            <input type="submit" name="submit" value="Изменить">
        </form>
    