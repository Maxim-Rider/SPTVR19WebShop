

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h3 class="w-100 my-5 text-center">Список товара в корзине</h3>
    <div class="w-100 text-center">Желаете купить:</div>
    <form action="buyFurnitures" method="POST">
        <div class="row w-100 d-block">
            <table class="table table-striped table-hover w-50 mx-auto">
                <thead>
                        <th class="text-end h-5">Отметить</th>
                        <th class="text-center h-5">Товар</th>
                        <th class="text-center h-5">Удалить из корзины</th>

                </thead>
                <tbody>
            <c:forEach var="furniture" items="${listFurnituresInBasket}">
                <tr>
                    <td class="text-end m-2 align-middle">
                        <input class="form-check-input" type="checkbox" value="${furniture.id}" name="selectedFurnitures">
                    </td>
                    <td class="m-2">
                        <div class="card mx-auto" style="max-width: 12rem; max-height: 25rem" >
                          <img src="insertFile/${furniture.cover.path}" class="card-img-top d-block" alt="..." style="min-width: 12rem; min-height: 18rem">
                          <div class="card-body">
                            <div class="card-title">${furniture.name}</div>
                            <div class="card-text">${furniture.color}</div>
                            <div class="card-text">${furniture.size}</div>
                            <div class="card-text">${furniture.quantity}</div>
                            <div class="card-text">${furniture.price}</div>
                            <c:if test="${furniture.discount <= 0 || furniture.discountDate > today}">
                                    <p class="card-text m-0">${furniture.price/100} EUR</p>
                                </c:if>
                                <c:if test="${furniture.discount > 0 && furniture.discountDate < today}">
                                    <p class="card-text m-0 text-danger"><span class="text-decoration-line-through text-black-50">${furniture.price/100}</span> ${(furniture.price - furniture.price*furniture.discount/100)/100} EUR</p>
                                </c:if>
                          </div>
                        </div>
                    </td>
                    <td class="text-start m-2 align-middle">
                        <a href="removeFurnitureFromBasket?furnitureId=${furniture.id}" class="w-50 text-nowrap">Удалить из корзины</a>
                    </td>
                </tr>
            </c:forEach>
                <tr>
                    <td colspan="2">
                        <button type="submit" class="btn btn-primary mb-5 w-100">Купить отмеченные товары</button>
                    </td>
                </tr>
                </tbody>
        </table>

        </div>
    </form>





