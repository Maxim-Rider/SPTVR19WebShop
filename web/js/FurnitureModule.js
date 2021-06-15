class FurnitureModule{
  printAddFurnitureForm(){
    document.getElementById('context').innerHTML=
     `<h3 class="w-100 text-center my-5 ">Добавить новый товар</h3>
      <form id="furnitureForm" method="POST" enctype="multipart/form-data">
        <div class="row w-50 my-2 mx-auto">
        <div class="col-4 text-end">
            Название товара 
        </div>
        <div class="col-8 text-start ">
          <input class="w-100" type="text" name="name" id="name">
        </div>
      </div>
      <div class="row w-50 my-2 mx-auto">
        <div class="col-4 text-end">
          Цвет товара
        </div>
        <div class="col-8 text-start">  
          <input class="col-8" type="text" name="color" id="color">
        </div>
      </div>
      <div class="row w-50 my-2 mx-auto">
        <div class="col-4 text-end">   
            Размер товара 
        </div>
        <div class="col-8 text-start">  
          <input class="col-4" type="text" name="size" id="size">
        </div>
      </div>
      <div class="row w-50 my-2 mx-auto">
        <div class="col-4 text-end">   
            Количество товара 
        </div>
        <div class="col-8 text-start">  
          <input class="col-8" type="text" name="quantity" id="quantity">
        </div>
      </div>
      <div class="row w-50 my-2 mx-auto">
        <div class="col-4 text-end">   
            Цена: 
        </div>
        <div class="col-8 text-start">  
          <input class="col-4" type="text" name="price" id="price">
        </div>
      </div>
      <div class="row w-50 my-2 mx-auto">
        <div class="col-4 text-end">
            Загрузите обложку 
        </div>
        <div class="col-8 text-start">     
            <input class="form-control col" type="file" name="file" id="file-cover">
        </div>
      </div>
      <div class="row w-50 my-2 mx-auto">
        <div class="col-4 text-end">
            Текст товара 
        </div>
        <div class="col-8 text-start">     
          <input class="form-control" type="file" name="file" id="file-text">
        </div>
      </div>
      <div class="row w-50 my-2 mx-auto">
        <div class="col-4 text-end">
             
        </div>
        <div class="col-8 text-start mt-3">     
          <input class="w-50 bg-primary text-white" type="submit" name="submit" value="Добавить">
        </div>
      </div>
    </form>`;
    document.getElementById('furnitureForm').onsubmit = function(e){
      e.preventDefault();
      furnitureModule.createFurniture();
    }
  }
  async createFurniture(){
    let response = await fetch('createFurnitureJson',{
      method: 'POST',
      body: new FormData(document.getElementById('furnitureForm'))
    })
    var result = await response.json();
        if((response).ok){
          console.log("Request status: " + result.requestStatus);
          document.getElementById('info').innerHTML = result.info;
          console.log("Request status: "+result.requestStatus);
          document.getElementById('context').innerHTML='';
          furnitureModule.printListfurnitures();
          furnitureModule.printBuyFurnitureForm();
    }else{
      document.getElementById('info').innerHTML='Ошибка сервера';
    }
  }
  async printListFurnitures(){
    const listFurnitures = await furnitureModule.getListFurnitures();
    let context = document.getElementById('context');
    context.innerHTML = '<h3 class="w-100 my-3 text-center">Список товара</h3>';
    let divForCarts = document.createElement('div');
    divForCarts.classList.add('w-100');
    divForCarts.classList.add('d-flex')
    divForCarts.classList.add('justify-content-center');
    for(let furniture of listFurnitures){
      let cart = document.createElement('div');
      cart.classList.add('card');
      cart.classList.add('m-2');
      cart.style.cssText=`max-width: 12rem; max-height: 25rem; border:0; min-height: 30rem; overflow-y:hidden;`;
      cart.innerHTML= '<p class="card-text text-danger w-100 d-flex justify-content-center">&nbsp;</p>';
      let img = document.createElement('img');
      img.classList.add('card-img-top');
      img.style.cssText=`max-width: 12rem; max-height: 15rem; min-height:12rem; border-style: solid; border-width: 0.5px; margin-top: -30px;`;
      img.setAttribute('src', `insertFile/${furniture.cover.path}`);
      cart.insertAdjacentElement('beforeEnd',img);
      cart.insertAdjacentHTML('beforeend',
                  ` <div class="card-body" style="border-style: solid; border-width: 0.5px">
                    <b>Название:</b>  <h7 class="card-title m-0">${furniture.name}</h7>
                    <br>
                    <b>Цвет:</b>  <h7 class="card-text m-0">${furniture.color}</h7>
                    <br>
                    <b>Размер:</b>  <h7 class="card-text m-0">${furniture.size}</h7>
                    <br>
                    <b>Кол-во:</b>  <h7 class="card-text m-0">${furniture.quantity}</h7>
                    <br>
                    <b>Цена:</b>  <h7 class="card-text m-0">${furniture.price} EUR</h7>
                      <p class="d-inline">
                        <a href="readFurniture?furnitureId=${furniture.id}" class="link text-nowrap"><button class="bg-info" style="color:white; margin-top: 5px; position: absolute; bottom: 10px; left:15px;">Просмотреть</button></a>           
                      <!--  <a href="addToBasket?furnitureId=${furniture.id}" class="link text-nowrap"><button class="bg-primary" style="color:white; margin-top: 3px; position: absolute; bottom: 43px; left:15px;">В корзину</button></a>-->
                      </p>
                    </div>`
                    );
      divForCarts.insertAdjacentElement('beforeend',cart);         
    }

    context.insertAdjacentElement('beforeend',divForCarts);

  }
  async getListFurnitures(){
    let response = await fetch('listFurnituresJson',{
      method: 'GET',
      headers: {
        'Content-Type': 'application/json;charset=utf8'
      }
    })
    if(response.ok){
      let result = await response.json();
      return result;
    }else{
      document.getElementById('info').innerHTML='Ошибка сервера';
      return null;
    }
  }
  
  async printBuyFurnitureForm() {
        const response = await fetch('printBuyFurnitureFormJson',{
            method: 'GET',
            headers: {
                'Content-Type': 'application/json;charset:utf8'
                }});
 
        var ids = [];
        var btn_ids = [];
        var counts = [];
        var result = await response.json();
            if (response.ok){
              console.log("Request status: "+result.requestStatus);
              var buy_quantity = result.buy_quantity;
            } else {
              console.log("Ошибка получения данных");
            }
        console.log(result)
        if (result.requestStatus == "false") {
            document.getElementById('info').innerHTML = result.info;
        } else {
            document.getElementById('context').innerHTML = `
                <div id="buys" class="row text-center">     
                    <h3 class="w-100 my-3 text-center" class="display-5">Список товара</h3> 
                </div>`;
            for (let furniture of result) {
                document.getElementById('buys').innerHTML += `
                <div class="col-md mx-auto">
                        <input id="furnitureId${furniture.id}" type="text" name="furnitureId${furniture.id}" value="${furniture.id}" hidden>                
                        <div class="card text-center mx-auto" style="width: 16rem;">
                            <img style="height: 100%;" src="insertFile/${furniture.cover.path}" class="card-img-top" alt="furniture">
                            <div class="card-body">
                                <h5 class="card-title">Товар: ${furniture.name}</h5>
                                <p class="card-text"><i>Цвет: ${furniture.color}</i></p>
                                <p class="card-text"><i>Размер: ${furniture.size}</i></p>
                                <p class="card-text">${furniture.price}$ (${furniture.quantity} на складе)</p>
                                <input id="c${furniture.id}" value="${furniture.quantity}" hidden>
                                <button id="btn${furniture.id}a" type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal${furniture.id}">
                                    Купить
                                </button>
                                <div class="modal fade" id="exampleModal${furniture.id}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title" id="exampleModalLabel"> Подтверждение </h5>
                                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                            </div>
                                            <div class="modal-body">
                                                Вы уверены, что хотите купить товар "${furniture.name}" за ${furniture.price} EUR/штука  ?
                                            </div>
                                            <div class="modal-footer">
                                                <input type="number" min="1" max="${furniture.quantity}" class="form-control" id="buy_quantity" name="buy_quantity">
                                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">  Нет  </button>
                                                <button id="btn${furniture.id}" type="submit" class="btn btn-primary">  Да </button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                </div>`;
                                
                ids.push(furniture.id.toString());
        }
        for (let j of ids) {
            console.log(j)
            document.getElementById('btn' + j).addEventListener('click', () => { furnitureModule.buyFurniture(j, 1) } );
        }
        console.log(ids);
    
    }
  
  } 
  
  async buyFurniture(id, quantity) {
    const furnitureId = id;
    const buy_quantity = document.getElementById('buy_quantity').value;
    const furniture_data = {
         "furnitureId": furnitureId,
         "buy_quantity": buy_quantity
       };
    console.log(furniture_data)
    const response = await fetch('buyFurnitureJson',{
    method: 'POST',
    body: JSON.stringify(furniture_data)
        });     
        
    var result = await response.json();
    
    if (response.ok){
      console.log("Request status: " + result.requestStatus);
        document.getElementById('info').innerHTML = result.info;
        console.log("Request status: "+result.requestStatus);
        document.getElementById('context').innerHTML='';
        furnitureModule.printBuyFurnitureForm();
    } else {
      console.log("Ошибка получения данных");
    }
  }
}
const furnitureModule = new FurnitureModule();
export {furnitureModule};