
<%@page contentType="text/html" pageEncoding="UTF-8"%>

        <h3>Добавить товар</h3>
        
   
        <form action="createFurniture" method="POST">
            Название товара: <input type="text" name="name" value="${name}"><br>
            Цвет: <input type="text" name="color" value="${color}"><br>
            Размер: <input type="text" name="size" value="${size}"><br>
            Количество: <input type="text" name="quantity" value="${quantity}"><br>
            Цена: <input type="text" name="price" value="${price}"><br>
            <input type="submit" name="submit" value="Добавить товар">
        </form>
