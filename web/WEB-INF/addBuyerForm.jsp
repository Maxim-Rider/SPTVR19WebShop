
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Добавить покупателя</title>
    </head>
    <body>
        <div>Добавить покупателя</div>
        <p>${info}</p>
        <br>
        <a href="index.jsp"><Магазин></a><br>
        <form action="createBuyer" method="POST">
            Имя: <input type="text" name="firstname" value="${firstname}"><br>
            Фамилия: <input type="text" name="lastname" value="${lastname}"><br>
            Телефон: <input type="text" name="phone" value="${phone}"><br>
            Кошелёк: <input type="text" name="wallet" value="${wallet}"><br>
            Логин: <input type="text" name="login" value="${login}"><br>
            Пароль: <input type="password" name="password" value=""><br>
            <input type="submit" name="submit" value="Зарегестрироваться">
        </form>
    </body>
</html>
