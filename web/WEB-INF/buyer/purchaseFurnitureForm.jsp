

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

    <h1>Купить товар</h1>

        <p>Выберите товар</p>
        <form method="post" action="purchaseFurniture">
        <select name="furnitureId">
          <option value="">Выберите товар:</option>
              <c:forEach var="furniture" items="${listFurnitures}" varStatus="status">
                  <option value="${furniture.id}">Название: ${furniture.name} | Цвет: ${furniture.color} | Размер: ${furniture.size} | Количество: ${furniture.quantity} | Цена: ${furniture.price}</option>
              </c:forEach>
        </select>
        
        <br><br>
        
        <input type="submit" name="submit" value="Купить товар">
        </form>

 