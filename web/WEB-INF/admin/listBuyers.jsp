


<%@page contentType="text/html" pageEncoding="UTF-8"%>

        <h1>Список покупателей:</h1>

                <li>
                    Имя. Фамилия. Телефон. Кошелёк.
                </li>
        <ol>
            <c:forEach var="buyer" items="${listBuyers}" varStatus="status">
                <li>
                    <b>Имя:</b> ${buyer.firstname} | <b>Фамилия:</b> ${buyer.lastname} | <b>Телефон:</b> ${buyer.phone} | <b>Кошелёк:</b> ${buyer.wallet}$ | <a href="editBuyerForm?buyerId=${buyer.id}">Изменить</a>
                </li>
            </c:forEach>
        </ol>
   
