
<%@page contentType="text/html" pageEncoding="UTF-8"%>

        <h3>Зарегестрироваться</h3>

        <form action="registration" method="POST">
            Имя: <input type="text" name="firstname" value="${firstname}"><br>
            Фамилия: <input type="text" name="lastname" value="${lastname}"><br>
            Телефон: <input type="text" name="phone" value="${phone}"><br>
            Кошелёк: <input type="text" name="wallet" value="${wallet}"><br>
            Логин: <input type="text" name="login" value="${login}"><br>
            Пароль: <input type="password" name="password" value=""><br>
            <input type="submit" name="submit" value="Зарегестрироваться">
        </form>
    
