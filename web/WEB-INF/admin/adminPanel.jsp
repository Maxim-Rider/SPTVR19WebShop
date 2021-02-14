<%-- 
    Document   : adminPanel
    Created on : 02.02.2021, 10:22:20
    Author     : Comp
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h3 class="w-100 m-2 text-center">Панель администратора</h3>

<p>
    <form action="setRoleToUser" method="POST">
        <div class="row">
            <div class="col m-2 text-end">
                Пользователь:
            </div>
            <div class="col">
                <select name="userId">
                    <option value="">Выберите пользователя</option>
                    <c:forEach var="entry" items="${usersMap}">
                        <option value="${entry.key.id}" <c:if test="${userId eq entry.key.id}"> selected </c:if>>
                            ${entry.key.buyer.firstname} 
                            ${entry.key.buyer.lastname}. 
                            Логин: ${entry.key.login}. Роль: 
                                <c:forEach var="role" items="${entry.value}"><span>${role.roleName} </span></c:forEach>
                        </option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="row">
            <div class="col m-2 text-end">
                Роли:
            </div>
            <div class="col">
                <select name="roleId">
                    <option value="">Выберите роль</option>
                    <c:forEach var="r" items="${listRoles}">
                        <option value="${r.id}" <c:if test="${roleId eq r.id}"> selected </c:if>>
                            ${r.roleName} 
                        </option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="row w-100">
            <div class="col text-end">
                <input class="form-check-input" name="changeRole" type="radio" value="0" checked>
            </div>
            <div class="col  text-start">
                Добавить роль
            </div>
        </div>
        <div class="row w-100">
            <div class="col text-end">
                <input class="form-check-input" name="changeRole" type="radio" value="1">
            </div>
            <div class="col  text-start">
                Удалить роль
            </div>
        </div>
        <div class="row">
            <div class="col m-2 text-center">
                <input type="submit" value="Изменить роль пользователю">
            </div>
        </div>
    </form>
</p> 
