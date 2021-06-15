<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

    <div class='container-fluid'>
        <div class='row justify-content-xl-center'>
            <c:forEach var="furniture" items="${listFurnitures}">
                <div class="col">
                <form style="width: 30%" action="buyFurniture" method="POST">
                    <input type="text" name="furnitureId" value="${furniture.id}" hidden>                
                    <div class="card" style="width: 18rem;">
                    <img style="width: 95%;" src="insertFile/${furniture.cover.path}" class="card-img-top" alt="furniture">
                    <div class="card-body">
                      <h5 class="card-title">${furniture.name}</h5>
                      <p class="card-text">${furniture.cover.description}</p>
                      <p class="card-text">${furniture.price}$</p>
                      <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
                        Купить
                      </button>
                        <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                          <div class="modal-dialog">
                            <div class="modal-content">
                              <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel"> Подтверждение </h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                              </div>
                              <div class="modal-body">
                                Вы уверены, что хотите купить товар "${furniture.name}" за ${furniture.price}$?
                              </div>
                              <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">  Нет  </button>
                                <button type="submit" class="btn btn-primary">  Да </button>
                              </div>
                            </div>
                          </div>
                        </div>
                    </div>
                    </div>
                    </div>
                  </div>
                </form>     
                </div>
            </c:forEach>
        </div>
    </div>
