import {authModule} from './AuthModule.js';
import {furnitureModule} from './FurnitureModule.js';
import {userModule } from './UserModule.js';


document.getElementById("addFurniture").onclick = function (){
    toogleMenuActive("addFurniture");
    document.getElementById('info').innerHTML='&nbsp;';
    furnitureModule.printAddFurnitureForm();
    
};
document.getElementById("listFurnitures").onclick = function (){
    toogleMenuActive("listFurnitures");
    document.getElementById('info').innerHTML='&nbsp;';
    furnitureModule.printListBooks();
};
document.getElementById("purchasedFurnitures").onclick = function (){
    toogleMenuActive("purchasedFurnitures");
};
document.getElementById("discountForm").onclick = function (){
    toogleMenuActive("discountForm");
};
document.getElementById("listBuyers").onclick = function (){
    toogleMenuActive("listBuyers");
    document.getElementById('info').innerHTML='&nbsp;';
    userModule.printListUsers();
};
document.getElementById("adminPanel").onclick = function (){
    toogleMenuActive("adminPanel");
};
document.getElementById("showLoginForm").onclick = function (){
    toogleMenuActive("showLoginForm");
    document.getElementById('info').innerHTML='&nbsp;';
    authModule.printLoginForm();
};
document.getElementById("logout").onclick = function (){
    toogleMenuActive("logout");
    document.getElementById('info').innerHTML='';
    authModule.logout();
};

furnitureModule.printListFurnitures();
authModule.toogleMenu();

function toogleMenuActive(elementId){
   const activeElement = document.getElementById(elementId);
   const passiveElements = document.getElementsByClassName("nav-link");
    for (let i = 0; i < passiveElements.length; i++) {
        if(activeElement === passiveElements[i]){
            passiveElements[i].classList.add("active-menu");
        }else{
            if(passiveElements[i].classList.contains("active-menu")){
                passiveElements[i].classList.remove("active-menu");
            }
        }
    }
}