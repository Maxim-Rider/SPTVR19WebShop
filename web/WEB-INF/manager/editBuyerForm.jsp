<%-- 
    Document   : editBuyerForm
    Created on : 16.12.2020, 11:49:22
    Author     : Comp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

        <h1>Редактирование покупателя</h1>

        <form action="editBuyer" method="POST">
            <input type="hidden" name="buyerId" value="${buyer.id}">
            Имя: <input type="text" name="firstname" value="${buyer.firstname}"><br>
            Фамилия: <input type="text" name="lastname" value="${buyer.lastname}"><br>
            Телефон: <input type="text" name="phone" value="${buyer.phone}"><br>
            Кошелёк: <input type="text" name="wallet" value="${buyer.wallet}"><br>
            <input type="submit" name="submit" value="Изменить">
        </form>
    
