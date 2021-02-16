
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h3 class="w-100 text-center  my-5">Добавить товар</h3>
        <div class="" style="width: 50rem; margin: 0 auto">
            <a class="btn btn-primary my-2" href="uploadForm">Загрузить обложку товара</a>
            <form action="createFurniture" method="POST">
                  <div class="mb-3 row">
                    <label for="name" class="col-sm-3 col-form-label">Название товара:</label>
                    <div class="col-sm-9">
                      <input type="text" class="form-control" id="name" name="name" value="${name}">
                    </div>
                  </div>
                  <div class="mb-3 row">
                    <label for="color" class="col-sm-3 col-form-label">Цвет товара:</label>
                    <div class="col-sm-9">
                      <input type="text" class="form-control" id="color" name="color" value="${color}">
                    </div>
                  </div>
                  <div class="mb-3 row">
                    <label for="size" class="col-sm-3 col-form-label">Размер товара:</label>
                    <div class="col-sm-9">
                      <input type="text" class="form-control" id="color" name="size" value="${size}">
                    </div>
                  </div>
                  <div class="mb-3 row">
                    <label for="quantity" class="col-sm-3 col-form-label">Количество товара:</label>
                    <div class="col-sm-9">
                      <input type="text" class="form-control" id="quantity" name="quantity" value="${quantity}">
                    </div>
                  </div>
                  <div class="form-floating">
                    <textarea class="form-control" placeholder="text" name="text" id="text"></textarea>
                    <label for="text">Текст товара</label>
                  </div><br>
                  <div class="mb-3 row">
                    <label for="price" class="col-sm-3 col-form-label">Цена:</label>
                    <div class="col-sm-9">
                      <input type="text" class="form-control" id="price" name="price" value="${price}">
                    </div>
                  </div>
                  <div class="mb-3 row">
                    <label for="login" class="col-sm-3 col-form-label">Обложка: </label>
                    <div class="col-sm-9">
                        <select class="form-select" name="coverId">
                            <option value="">Выберите обложку</option>
                            <c:forEach var="cover" items="${listCovers}">
                                <option value="${cover.id}">${cover.description}</option>
                            </c:forEach>
                        </select>
                    </div>
                  </div>
                <div class="col-sm-12">
                  <button type="submit" class="btn btn-primary mb-3 w-100">Отправить</button>
                </div>
            </form>
        </div>



<!--        <h3>Добавить товар</h3>
        <p>${info}</p>
        <a href="uploadForm">Загрузить обложку товара</a>
   
        <form action="createFurniture" method="POST">
            Название товара: <input type="text" name="name" value="${name}"><br>
            Цвет: <input type="text" name="color" value="${color}"><br>
            Размер: <input type="text" name="size" value="${size}"><br>
            Количество: <input type="text" name="quantity" value="${quantity}"><br>
            Цена: <input type="text" name="price" value="${price}"><br>
            Обложка: <input type="text" name="cover" value="${cover.description}">
            <input type="hidden" name="coverId" value="${cover.id}">
           <input type="submit" value="Отправить"><br>
        </form>-->
