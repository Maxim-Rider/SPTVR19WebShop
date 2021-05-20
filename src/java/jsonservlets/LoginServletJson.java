/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsonservlets;

import entity.Furniture;
import entity.Buyer;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jsoncovertors.JsonFurnitureBuilder;
import session.FurnitureFacade;
import session.BuyerFacade;
import session.UserFacade;
import session.UserRolesFacade;
import tools.EncryptPassword;
import javax.json.JsonReader;

/**
 *
 * @author Comp
 */
@WebServlet(name = "LoginServletJson", urlPatterns = {
    "/createUserJson",
    "/loginJson",
    "/logoutJson",
    "/listFurnituresJson",
    
})
public class LoginServletJson extends HttpServlet {
    @EJB UserFacade userFacade;
    @EJB FurnitureFacade furnitureFacade;
    @EJB BuyerFacade buyerFacade;
    @EJB UserRolesFacade userRolesFacade;
    
    @Inject EncryptPassword encryptPassword;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String json = null;
        JsonReader jsonReader = Json.createReader(request.getReader());
        JsonObjectBuilder job = Json.createObjectBuilder();
        String path = request.getServletPath();
        switch (path) {
            case "/createUserJson":
                JsonObject jsonObject = jsonReader.readObject();
                String firstname = jsonObject.getString("firstname","");
                String lastname = jsonObject.getString("lastname","");
                String phone = jsonObject.getString("phone","");
                String wallet = jsonObject.getString("wallet","");
                String login = jsonObject.getString("login","");
                String password = jsonObject.getString("password","");
                if(firstname == null || "".equals(firstname)
                      || lastname == null || "".equals(lastname) 
                      || phone == null || "".equals(phone) 
                      || wallet == null || "".equals(wallet) 
                      || login == null || "".equals(login) 
                      || password == null || "".equals(password) 
                        ){
                    json=job.add("requestStatus", "false")
                        .add("info", "Заполните все поля формы")
                        .build()
                        .toString();
                    break;
                }
                Buyer buyer = new Buyer(firstname, lastname, phone, wallet);
                buyerFacade.create(buyer);
                String salt = encryptPassword.createSalt();
                password = encryptPassword.createHash(password, salt);
                User user = new User(login, password, salt, buyer);
                try {
                    userFacade.create(user);
                } catch (Exception e) {
                    buyerFacade.remove(buyer);
                    json=job.add("requestStatus", "false")
                        .add("info", "Такой пользователь существует")
                        .build()
                        .toString();
                    break;
                }
                userRolesFacade.setRole("BUYER",user);
                json=job.add("requestStatus", "true")
                        .add("info", "Пользователь "+user.getLogin()+" успешно создан")
                        .build()
                        .toString();
                break;
            case "/loginJson": 
                jsonObject = jsonReader.readObject();
                login = jsonObject.getString("login","");
                password = jsonObject.getString("password","");
                if(login == null || "".equals(login)
                        || password == null || "".equals(password)){
                    json=job.add("requestStatus", "false")
                        .add("info", "Нет такого пользователя")
                        .build()
                        .toString();
                    break;
                }
                User regUser = userFacade.findByLogin(login);
                if(regUser == null){
                   json=job.add("requestStatus", "false")
                        .add("info", "Нет такого пользователя")
                        .build()
                        .toString();
                    break;
                }
                password = encryptPassword.createHash(password, regUser.getSalt());
                if(!password.equals(regUser.getPassword())){
                    json=job.add("requestStatus", "false")
                        .add("info", "Нет такого пользователя")
                        .build()
                        .toString();
                    break;
                }
                HttpSession session = request.getSession(true);
                session.setAttribute("user", regUser);
                json=job.add("requestStatus", "true")
                        .add("info", "Вы вошли как "+regUser.getLogin())
                        .add("token", session.getId())
                        .add("role", userRolesFacade.getTopRoleForUser(regUser))
                        .build()
                        .toString();
                
                break;
            case "/logoutJson":
                session = request.getSession(false);
                if(session != null){
                    session.invalidate();
                    json=job.add("requestStatus", "true")
                        .add("info", "Вы вышли")
                        .build()
                        .toString();
                }
                break;
            case "/listFurnituresJson":
                List<Furniture> listFurnitures = furnitureFacade.findAll();
                JsonArrayBuilder jab = Json.createArrayBuilder();
                listFurnitures.forEach((furniture) -> {
                    jab.add(new JsonFurnitureBuilder().createJsonFurniture(furniture));
                });
                json = jab.build().toString();
                break;
        }
        if(json == null && "".equals(json)){
            json=job.add("requestStatus", "false")
                        .add("info", "Ошибка обработки запроса")
                        .build()
                        .toString();
        }
        try (PrintWriter out = response.getWriter()) {
            out.println(json);
        }
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
