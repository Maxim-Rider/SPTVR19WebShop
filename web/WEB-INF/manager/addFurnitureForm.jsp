
<%@page contentType="text/html" pageEncoding="UTF-8"%>

        <h3>Добавить товар</h3>
        <p>${info}</p>
        <a href="uploadForm">Загрузить обложку товара</a>
   
        <form action="createFurniture" method="POST">
            Название товара: <input type="text" name="name" value="${name}"><br>
            Цвет: <input type="text" name="color" value="${color}"><br>
            Размер: <input type="text" name="size" value="${size}"><br>
            Количество: <input type="text" name="quantity" value="${quantity}"><br>
            Цена: <input type="text" name="price" value="${price}"><br>
            Обложка: <input type="text" name="cover" value="${cover.description}">
            <input type="hidden" name="coverId" value="${cover.id}">
           <input type="submit" value="Отправить"><br>
        </form>
