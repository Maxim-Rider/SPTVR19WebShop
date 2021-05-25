import {authModule} from './AuthModule.js';
import {furnitureModule} from './FurnitureModule.js';
import {userModule } from './UserModule.js';

document.getElementById("index").onclick = function (){
    toogleMenuActive("index");
    document.getElementById('info').innerHTML='&nbsp;';
    furnitureModule.printListFurnitures();
    
};
document.getElementById("addFurniture").onclick = function (){
    toogleMenuActive("addFurniture");
    document.getElementById('info').innerHTML='&nbsp;';
    furnitureModule.printAddFurnitureForm();  
};
document.getElementById("listFurnitures").onclick = function (){
    toogleMenuActive("listFurnitures");
    document.getElementById('info').innerHTML='&nbsp;';
    furnitureModule.printListFurnitures();
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
    document.getElementById('info').innerHTML='&nbsp;';
    userModule.printAdminPanel();
};
document.getElementById("showLoginForm").onclick = function (){
    toogleMenuActive("showLoginForm");
    document.getElementById('info').innerHTML='&nbsp;';
    authModule.printShowLoginForm();
};
document.getElementById("logout").onclick = function (){
    toogleMenuActive("logout");
     document.getElementById('info').innerHTML='&nbsp';
    authModule.logout();
};
document.getElementById("basket").onclick = function (){

    document.getElementById('info').innerHTML='&nbsp';

};
document.getElementById("showProfile").onclick = function (){
    toogleMenuActive("showProfile");
    userModule.showProfile();
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
