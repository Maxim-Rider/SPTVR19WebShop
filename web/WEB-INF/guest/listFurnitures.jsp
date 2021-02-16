

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!--        <h1>Список товаров:</h1>-->

        
        <h3 class="w-100 text-center  my-5">Товар магазина:</h3>
        <div class="w-100 d-flex justify-content-center">
            <c:forEach var="furniture" items="${listFurnitures}">
                <div class="card m-2" style="min-width: 12rem;">
                    <img src="insertFile/${furniture.cover.path}"  class="card-img-top" alt="..." style="max-width: 12rem; max-height: 15rem">
                        <div class="card-body">
                            <h5 class="card-title">${furniture.name}</h5>
                                Цвет: <p class="card-text">${furniture.color}</p>
                                Размер: <p class="card-text">${furniture.size} </p>
                                Количество: <p class="card-text">${furniture.quantity} </p>
                                Цена: <p class="card-text">${furniture.price}$ </p>
                                Описание: <p class="card-text">${furniture.text} </p>
                                <a href="purchaseFurniture?furnitureId=${furniture.id}" class="btn btn-primary">Купить</a>
                        </div>
                </div>
            </c:forEach>
        </div>
        
<!--        <ol>
            <form action="editFurnitureForm" method="POST">
            <select name="furnitureId" multiple="true">
                <option value="">Список товаров:</option>
            <c:forEach var="furniture" items="${listFurnitures}" varStatus="status">
                <li>
                    <option>
                    Товар: ${furniture.name} 
                    | Цвет: ${furniture.color} 
                    | Размер: ${furniture.size} 
                    | Количество: ${furniture.quantity} 
                    | Цена: ${furniture.price}$
                    </option>
                </li>
            </c:forEach>
            </select>
            <input type="submit" value="Изменить выбранный товар">
        </ol>-->
        