<%-- 
    Document   : showLoginForm
    Created on : 26.01.2021, 14:35:43
    Author     : Comp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h3 class="w-100 text-center  my-5">Введите логин и пароль</h3>
<div class="row text-center p-5 border" style="width: 25em; margin: 0 auto">
    <form action="login" method="POST">
          <div class="mb-3 row">
            <label for="login" class="col-sm-2 col-form-label">Логин</label>
            <div class="col-sm-10">
              <input type="text" class="form-control" name="login" id="login" value="${login}">
            </div>
          </div>
          <div class="mb-3 row">
            <label for="password" class="col-sm-2 col-form-label">Пароль</label>
            <div class="col-sm-10">
              <input type="password" class="form-control" name="password" id="password">
            </div>
          </div>
          <div class="col-auto">
            <button type="submit" class="btn btn-primary mb-3">Войти</button>
          </div>
       <p><a href="registrationForm">Регистрация</a></p>
    </form>
</div>



<!--        <h1>Введите логин и пароль</h1>
        <p>${info}</p>
        <form action="login" method="POST">
            Логин: <input type="text" name="login" value="${login}"><br>
            Пароль: <input type="password" name="password" value=""><br>
           <input type="submit" name="submit" value="Войти"><br>
        </form>
    -->
