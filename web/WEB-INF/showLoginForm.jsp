<%-- 
    Document   : showLoginForm
    Created on : 26.01.2021, 14:35:43
    Author     : Comp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Вход в систему</title>
    </head>
    <body>
        <h1>Введите логин и пароль</h1>
        <p>${info}</p>
        <form action="login" method="POST">
            Логин: <input type="text" name="login" value="${login}"><br>
            Пароль: <input type="password" name="password" value=""><br>
           <input type="submit" name="submit" value="Войти"><br>
        </form>
    </body>
</html>
