

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!--        <h1>Список товаров:</h1>-->

        
        <h3 class="w-100 text-center  my-5">Товар магазина:</h3>
        <div class="w-100 d-flex justify-content-center">
            <c:forEach var="furniture" items="${listFurnitures}">
                <div class="card m-2" style="max-width: 12rem; max-height: 25rem; border:0">
                    <p class="card-text text-danger w-100 d-flex justify-content-center"><c:if test="${furniture.discount > 0}">Скидка ${furniture.discount}%!</c:if>&nbsp;</p>
                    <img src="insertFile/${furniture.cover.path}"  class="card-img-top" alt="..." style="max-width: 12rem; max-height: 15rem">
                        <div class="card-body">
                            <h5 class="card-title">${furniture.name}</h5>
                                Цвет: <p class="card-text">${furniture.color}</p>
                                Размер: <p class="card-text">${furniture.size} </p>
                                Количество: <p class="card-text">${furniture.quantity} </p>
                                Цена: <p class="card-text">${furniture.price} EUR </p>
                                <c:if test="${furniture.discount <= 0 || furniture.discountDate > today}">
                                    <p class="card-text m-0">${furniture.price/100} EUR</p>
                                </c:if>
                                <c:if test="${furniture.discount > 0 && furniture.discountDate < today}">
                                    <p class="card-text m-0 text-danger"><span class="text-decoration-line-through text-black-50">${furniture.price/100}</span> ${(furniture.price - furniture.price*furniture.discount/100)/100} EUR</p>
                                </c:if>
                                
                                <a href="readFurniture?furnitureId=${furniture.id}" class="w-50 text-nowrap">Просмотреть</a>
                                <a href="addToBasket?furnitureId=${furniture.id}" class=" w-50 text-nowrap">В корзину</a>
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
        