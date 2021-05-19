import {authModule} from './AuthModule.js';
class UserModule{
    registration(){
        document.getElementById('context').innerHTML=
        `<h3 class="w-100 my-5 text-center">Регистрация пользователя</h3>
        <div class="w-100 d-flex justify-content-center m-2">
          <form action="createUser" method="POST" onsubmit="false">
              <div class="row">
                  <div class="col">
                    <input type="text" id="firstname" class="form-control my-2 g-2" placeholder="Имя" aria-label="Имя">
                  </div>
                  <div class="col">
                    <input type="text" id="lastname" class="form-control my-2 g-2" placeholder="Фамилия" aria-label="Фамилия">
                  </div>
                </div>
                <div class="row">
                  <div class="col">
                    <input type="text" id="phone" class="form-control my-2 g-2" placeholder="Телефон" aria-label="Телефон">
                  </div>
                  <div class="col">
                    <input type="text" id="wallet" class="form-control my-2 g-2" placeholder="Деньги" aria-label="Деньги">
                  </div>
                </div>
                <div class="row">
                  <div class="col">
                    <input type="text" id="login" class="form-control my-2 g-2" placeholder="Логин" aria-label="Логин">
                  </div>
                  <div class="col">
                    <input type="text" id="password" class="form-control my-2 g-2" placeholder="Пароль" aria-label="Пароль">
                  </div>
                </div>
                <div class="row">
                  <div class="col">
                    <input type="button" id="btnRegistration" class="form-control my-2 g-2 text-white bg-primary" value="Зарегистрировать">
                  </div>
                </div>
          
          </form>
      </div>`;

      document.getElementById('info').innerHTML='';
      document.getElementById('btnRegistration').addEventListener('click', userModule.createUser); 
    }

    async createUser(){
        document.getElementById('info').innerHTML='';
        const firstname = document.getElementById("firstname").value;
        const lastname = document.getElementById("lastname").value;
        const phone = document.getElementById("phone").value;
        const wallet = document.getElementById("wallet").value;
        const login = document.getElementById("login").value;
        const password = document.getElementById("password").value;
        const user = {
            "firstname": firstname,
            "lastname": lastname,
            "phone": phone,
            "wallet": wallet,
            "login": login,
            "password": password
        };

       const response = await fetch('createUserJson',{
            method: 'POST',
            headers: {
                'Content-Type': 'application/json;charset:utf8'
            },
            body: JSON.stringify(user)
        })
        if(response.ok){
          const result = await response.json();
          document.getElementById('info').innerHTML=result.info;
          console.log("Request status: "+result.requestStatus);
          authModule.printLoginForm();
        }else{
          console.log("Ошибка получения данных");
        }
        
    }
    async printListUsers(){
      let result = await userModule.loadListUsers();
      /*result = {
                  requestStatus: 'true',
                  info: 'Список пользователей',
                  listUsers: [
                    {
                      user: { id: 1, 
                              login: 'admin', 
                              buyer:{
                                id=1,
                                firstname: 'Max', 
                                ...}
                            },
                      role: 'ADMIN',
                    },
                    {
                      user: { id: 1, 
                              login: 'ivan', 
                              buyer:{
                                id=1,
                                firstname: 'Иван', 
                                ...}
                            },
                      role: 'BUYER',
                    }
                    
                  ]
      }
      */
      const count = result.listUsers.length;
      let context = document.getElementById('context');
      context.innerHTML='';
      context.insertAdjacentHTML('afterBegin', 
      `<h3 class="w-100 my-5 text-center">Список покупателей</h3>
        <p class="">Всего покупателей: ${count}<p>
        <table id="tableListBuyers" class="table table-striped">
            <thead>
            <th>№</th>
            <th>Id</th>
            <th>Логин</th>
            <th>Имя</th>
            <th>Фамилия</th>
            <th>Телефон</th>
            <th>Деньги (EUR)</th>
            <th>Роль</th>
            <th>Активность</th>
            <th></th>
            </thead>
            <tbody>
            </tbody>
        </table>`);
        let tbody = document.getElementById('tableListBuyers')
                            .getElementsByTagName('tbody')[0]; // элементов может быть несколько
        let i = 1;  
        for(let users of result.listUsers){
          let row = document.createElement('tr');
          let td = document.createElement('td');
          td.appendChild(document.createTextNode(i++));
          row.appendChild(td);
          let userId = null;
          for(let userField in users.user){
            let td = document.createElement('td');
            if(userField === 'id') userId = users.user[userField];
            if(typeof users.user[userField] === 'object'){
              for(let buyerField in users.user[userField]){
                if(buyerField === 'id') continue;
                td = document.createElement('td');
                if(buyerField === 'money'){
                  td.appendChild(document.createTextNode(users.user[userField][buyerField]/100));
                  row.appendChild(td);
                }else{
                  td.appendChild(document.createTextNode(users.user[userField][buyerField]));
                  row.appendChild(td);
                }
              }
            }else{
              td.appendChild(document.createTextNode(users.user[userField]));
              row.appendChild(td);
            }
          }
          td=document.createElement('td');
          td.appendChild(document.createTextNode(users.role));
          row.appendChild(td);
          td=document.createElement('td');
          td.appendChild(document.createTextNode('Yes'));
          row.appendChild(td);
          td=document.createElement('td');
          let span = document.createElement('span');
          span.classList.add('btn');
          span.classList.add('text-white');
          span.classList.add('bg-primary');
          span.classList.add('p-2');
          span.appendChild(document.createTextNode('Изменить'));
          span.onclick = function(){userModule.changeUser(userId)};
          td.appendChild(span);
          row.appendChild(td);
          tbody.appendChild(row);
        }
    }

    async loadListUsers(){
      let response = await fetch('listUsersJson',{
        method: 'GET',
        headers:{
          'Content-Type': 'aplication/json;charser=utf-8'
        }
      })
      if(response.ok){
        let result = await response.json();
        console.log('listUsers: '+result.listUsers.length);
        return result;
      }else{
        document.getElementById('info').innerHTML="Ошибка сервера";
        return null;
      }
    }
    changeUser(userId){
      alert('userId='+userId);
    }
}
const userModule = new UserModule();
export {userModule};