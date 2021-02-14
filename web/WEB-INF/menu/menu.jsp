<%-- 
    Document   : menu
    Created on : 02.02.2021, 10:34:42
    Author     : Comp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <div class="container-fluid">
    <a class="navbar-brand" href="index.jsp">Магазин Мягкой Мебели</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav">
        <li class="nav-item">
          <a class="nav-link" aria-current="page" href="listFurnitures">Список товара</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="purchaseFurnitureForm">Купить товар</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="addFurniture">Добавить товар</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="listBuyers">Список покупателей</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="adminPanel">Панель администратора</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="showLoginForm">Войти</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="logout">Выйти</a>
        </li>
      </ul>
    </div>
  </div>
</nav> 