
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link type="text/css" rel="stylesheet" href="style.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SPTVR19WebShop</title>
    </head>
    <body>
        <h1>Магазин Мягкой Мебели</h1>
        <p>${info}</p>
        <b>Основная программа:</b><br><hr>
        <a href="registrationForm">Регистрация нового пользователя</a><br><hr>
        <a href="showLoginForm">Войти</a><br><hr>
        <a href="addFurniture">Добавить товар</a><hr>
        <a href="listFurnitures">Список товара</a><hr>
        <a href="listBuyers">Список покупателей</a><hr>
        <a href="purchaseFurnitureForm">Купить товар</a><hr>
        <a href="logout">Выйти</a><br><hr>
        <br><br><br>
        <b>Дополнительно (для себя):</b><br><hr>
        <a href="http://localhost/phpmyadmin/db_structure.php?server=1&db=sptvr19webshop2">Просмотреть базу данных</a><hr>
        <a href="http://www.google.com/">Выход из программы</a><hr>

    </body>
</html>
