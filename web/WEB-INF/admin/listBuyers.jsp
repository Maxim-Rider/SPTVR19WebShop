


<!--<%@page contentType="text/html" pageEncoding="UTF-8"%>

        <h1>Список покупателей:</h1>

                <li>
                    Имя. Фамилия. Телефон. Кошелёк.
                </li>
        <ol>
            <c:forEach var="buyer" items="${listBuyers}" varStatus="status">
                <li>
                    <b>Имя:</b> ${buyer.firstname} | <b>Фамилия:</b> ${buyer.lastname} | <b>Телефон:</b> ${buyer.phone} | <b>Кошелёк:</b> ${buyer.wallet}$ | <a href="editBuyerForm?buyerId=${buyer.id}">Изменить</a>
                </li>
            </c:forEach>
        </ol>-->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <h3 class="w-100 my-5 text-center">Список покупателей</h3>
        <p class="">Всего пользователей: ${usersCount}<p>
        <table id="tableListBuyers" class="table table-striped">
              <thead>
              <th>№</th>
              <th>Id</th>
              <th>Имя</th>
              <th>Фамилия</th>
              <th>Деньги</th>
              <th>Логин</th>
              <th>Роль1</th>
              <th>Роль2</th>
              <th>Роль3</th>
              <th>Активность</th>
              <th></th>
              </thead>
              <tbody>
                <c:forEach var="entry" items="${usersMapWithArrayRoles}" varStatus="status">
                    <tr>
                        <td>${status.index+1}</td>
                        <td>${entry.key.id}</td>
                        <td>${entry.key.buyer.firstname}</td>
                        <td>${entry.key.buyer.lastname}</td>
                        <td>${entry.key.buyer.wallet}</td>
                        <td>${entry.key.login}</td>
                        <td><c:if test="${entry.value[0] ne ''}">${entry.value[0]}</c:if></td>
                    </tr>
                </c:forEach>
                    
              </tbody>
          
        </table>
