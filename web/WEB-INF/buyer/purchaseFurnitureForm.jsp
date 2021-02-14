

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
        <p>
            <c:forEach var="book" items="${listBoughtFurnitures}">
                <!--список взятых пользователем книг-->
                  <div class="card" style="width: 18rem;">
                    <img src="..." class="card-img-top" alt="...">
                    <div class="card-body">
                      <h5 class="card-title">${furniture.name}</h5>
                      <p class="card-text">${furniture.color}</p>
                      <p class="card-text">${furniture.size}</p>
                      <p class="card-text">${furniture.quantity}</p>
                      <p class="card-text">${furniture.price}</p>
                      <a href="#" class="btn btn-primary">Просмотреть товар</a>
                    </div>
                  </div>
            </c:forEach>
        </p>
        </form>

 