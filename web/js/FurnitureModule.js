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
    if((response).ok){
      const result = await response.json();
      document.getElementById('info').innerHTML = result.info;
      furnitureModule.printListfurnitures();
    }else{
      document.getElementById('info').innerHTML='Ошибка сервера';
    }
  }
  async printListFurnitures(){
    const listFurnitures = await furnitureModule.getListFurnitures();
    let context = document.getElementById('context');
    context.innerHTML = '<h3 class="w-100 my-5 text-center">Список товара</h3>';
    let divForCarts = document.createElement('div');
    divForCarts.classList.add('w-100');
    divForCarts.classList.add('d-flex')
    divForCarts.classList.add('justify-content-center');
    for(let furniture of listFurnitures){
      let cart = document.createElement('div');
      cart.classList.add('card');
      cart.classList.add('m-2');
      cart.style.cssText=`max-width: 12rem; max-height: 25rem; border:0`;
      cart.innerHTML= '<p class="card-text text-danger w-100 d-flex justify-content-center">&nbsp;</p>';
      let img = document.createElement('img');
      img.classList.add('card-img-top');
      img.style.cssText=`max-width: 12rem; max-height: 15rem`;
      img.setAttribute('src',`insertFile/${furniture.cover.path}`);
      cart.insertAdjacentElement('beforeEnd',img);
      cart.insertAdjacentHTML('beforeend',
                  ` <div class="card-body">
                      <h5 class="card-title m-0">${furniture.name}</h5>
                      <p class="card-text m-0">${furniture.color}</p>
                      <p class="card-text m-0">${furniture.size}</p>
                      <p class="card-text m-0">${furniture.quantity}</p>
                      <p class="card-text m-0">${furniture.price/100} EUR</p>
                      <p class="d-inline">
                        <a href="readFurniture?furnitureId=${furniture.id}" class="link text-nowrap">Просмотреть</a>
                        <a href="addToBasket?furnitureId=${furniture.id}" class="link text-nowrap">В корзину</a>
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
}
const furnitureModule = new FurnitureModule();
export {furnitureModule};