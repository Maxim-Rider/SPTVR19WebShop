

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!--    <h1>Купить товар</h1>
        <p>Выберите товар</p>
        <form method="post" action="purchaseFurniture">
        <select name="furnitureId">
          <option value="">Выберите товар:</option>
              <c:forEach var="furniture" items="${listFurnitures}" varStatus="status">
                  <option value="${furniture.id}">Название: ${furniture.name} | Цвет: ${furniture.color} | Размер: ${furniture.size} | Количество: ${furniture.quantity} | Цена: ${furniture.price}</option>
              </c:forEach>
        </select>
     <input class="m-2" type="submit" name="submit" value="Купить товар">-->
     

     
<!--    <h3 class="w-100 text-center  my-5">Выбрать товар</h3>
    <div class="w-100 d-flex justify-content-center"    
        <form method="post" action="purchaseFurniture">
        <select class="form-control" name="furnitureId">
          <option value="">Выберите товар:</option>
              <c:forEach var="furniture" items="${listFurnitures}" varStatus="status">
                  <option value="${furniture.id}">Название: ${furniture.name} | Цвет: ${furniture.color} | Размер: ${furniture.size} | Количество: ${furniture.quantity} | Цена: ${furniture.price}</option>
              </c:forEach>
        </select>
        <input class="m-2" type="submit" value="Купить товар">
        </form>
    </div>  -->




<h3 class="w-100 my-5 text-center">Список купленных товаров</h3>

<div class="w-100 d-flex justify-content-center m-2">
  <c:forEach var="furniture" items="${listFurnitures}">
    <div class="card m-2 border" style="max-width: 12rem; max-height: 25rem">
        <img src="insertFile/${furniture.cover.path}" class="card-img-top" style="max-width: 12rem; max-height: 15rem" alt="...">
        <div class="card-body">
          <h5 class="card-title">${furniture.name}</h5>
          <div class="card-text">${furniture.color}</div>
          <div class="card-text">${furniture.size}</div>
          <div class="card-text">${furniture.quantity}</div>
          <div class="card-text">${furniture.price}</div>
          <p class="d-inline">
            <a href="readFurniture?furnitureId=${furniture.id}" class="link text-nowrap">Просмотреть</a>
          </p>
        </div>
    </div>
  </c:forEach>
</div>