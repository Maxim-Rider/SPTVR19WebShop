


<!--        <h3>Зарегестрироваться</h3>

        <form action="registration" method="POST">
            Имя: <input type="text" name="firstname" value="${firstname}"><br>
            Фамилия: <input type="text" name="lastname" value="${lastname}"><br>
            Телефон: <input type="text" name="phone" value="${phone}"><br>
            Кошелёк: <input type="text" name="wallet" value="${wallet}"><br>
            Логин: <input type="text" name="login" value="${login}"><br>
            Пароль: <input type="password" name="password" value=""><br>
            <input type="submit" name="submit" value="Зарегестрироваться">
        </form>-->
    
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h3 class="w-100 text-center  my-5">Зарегестрироваться</h3>
<div class="row text-center p-5 border" style="width: 35em; margin: 0 auto">
    <form action="registration" method="POST">
          <div class="mb-3 row">
            <label for="firstname" class="col-sm-2 col-form-label">Имя:</label>
            <div class="col-sm-10">
              <input type="text" class="form-control" name="firstname" id="firstname" value="${firstname}">
            </div>
          </div>
            <div class="mb-3 row">
            <label for="lastname" class="col-sm-2 col-form-label">Фамилия:</label>
            <div class="col-sm-10">
              <input type="text" class="form-control" name="lastname" id="lastname" value="${lastname}">
            </div>
          </div>
            <div class="mb-3 row">
            <label for="phone" class="col-sm-2 col-form-label">Телефон:</label>
            <div class="col-sm-10">
              <input type="text" class="form-control" name="phone" id="phone" value="${phone}">
            </div>
          </div>
            <div class="mb-3 row">
            <label for="wallet" class="col-sm-2 col-form-label">Кошелёк:</label>
            <div class="col-sm-10">
              <input type="text" class="form-control" name="wallet" id="wallet" value="${wallet}">
            </div>
          </div>
            <div class="mb-3 row">
            <label for="login" class="col-sm-2 col-form-label">Логин</label>
            <div class="col-sm-10">
              <input type="login" class="form-control" name="login" id="login">
            </div>
          </div>
          <div class="mb-3 row">
            <label for="password" class="col-sm-2 col-form-label">Пароль</label>
            <div class="col-sm-10">
              <input type="password" class="form-control" name="password" id="password">
            </div>
          </div>
          <div class="col-auto">
            <button type="submit" class="btn btn-primary mb-3">Зарегестрироваться</button>
          </div>
       
    </form>
</div>