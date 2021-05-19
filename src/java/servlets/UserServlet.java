/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * asadmin set configs.config.server-config.network-config.protocols.protocol.http-listener-1.http.max-form-post-size-bytes=-1
 */
package servlets;


import entity.Buyer;
import entity.Furniture;
import entity.History;

import entity.User;

import java.io.File;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Calendar;
import java.util.Date;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Stream;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.BuyerFacade;
import session.FurnitureFacade;
import session.HistoryFacade;
import session.UserFacade;
import session.UserRolesFacade;

/**
 *
 * @author Comp
 */
@WebServlet(name = "UserServlet", urlPatterns = {

    "/addToBasket",
    "/removeBookFromBasket",
    "/showBasket",
    "/buyBooks",
    "/purchasedBooks",
    "/editProfile",
    "/changeProfile",
    "/readBook",
    
})
public class UserServlet extends HttpServlet {
    @EJB
    private HistoryFacade historyFacade;
    
    @EJB
    private UserFacade userFacade;

    @EJB
    private FurnitureFacade furnitureFacade;

    @EJB
    private BuyerFacade buyerFacade;
    
    @EJB private UserRolesFacade userRolesFacade;
    
//    private List<Furniture> listFurnitures;
//    private List<Buyer> listBuyers;
//    private Buyer buyer;
//    private Furniture furniture;
//    private History history;




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
        HttpSession session = request.getSession(false);
        if(session == null){
            request.setAttribute("info", "У вас нет права для этого ресурса. Войдите в систему");
            request.getRequestDispatcher("/showLoginForm").forward(request, response);
            return;
        }
        User user = (User) session.getAttribute("user");
        if(user == null){
            request.setAttribute("info", "У вас нет права для этого ресурса. Войдите в систему");
            request.getRequestDispatcher("/showLoginForm").forward(request, response);
            return;
        }
        
        boolean isRole = userRolesFacade.isRole("BUYER", user);
        if(!isRole){
            request.setAttribute("info", "У вас нет права для этого ресурса. Войдите в систему с соответствующими правами");
            request.getRequestDispatcher("/showLoginForm").forward(request, response);
            return;
        }
        if(userRolesFacade.isRole("ADMIN",user)){
            request.setAttribute("role", "ADMIN");
        }else if(userRolesFacade.isRole("MANAGER",user)){
            request.setAttribute("role", "MANAGER");
        }else if(userRolesFacade.isRole("BUYER",user)){
            request.setAttribute("role", "BUYER");
        }
        
        request.setAttribute("role", userRolesFacade.getTopRoleForUser(user));
        List<Furniture> basketList = (List<Furniture>) session.getAttribute("basketList");
        if(basketList != null){
            request.setAttribute("basketListCount", basketList.size());
        }
        
        String path = request.getServletPath();

        switch (path) {
            case "/addToBasket":
                String furnitureId = request.getParameter("furnitureId");
                if("".equals(furnitureId) || furnitureId==null){
                    request.setAttribute("info", "Что то пошло не так");
                    request.getRequestDispatcher("/listFurnitures").forward(request, response);
                    break;
                }
                Furniture furniture = furnitureFacade.find(Long.parseLong(furnitureId));
                basketList = (List<Furniture>) session.getAttribute("basketList");
                if(basketList == null) basketList = new ArrayList<>();
                basketList.add(furniture);
                session.setAttribute("basketList", basketList);
                request.setAttribute("basketListCount", basketList.size());
                request.getRequestDispatcher("/listFurnitures").forward(request, response);
                break;
            case "/removeFurnitureFromBasket":
                furnitureId = request.getParameter("furnitureId");
                if("".equals(furnitureId) || furnitureId==null){
                    request.setAttribute("info", "Что то пошло не так");
                    request.getRequestDispatcher("/showBasket").forward(request, response);
                    break;
                }
                furniture = furnitureFacade.find(Long.parseLong(furnitureId));
                basketList = (List<Furniture>) session.getAttribute("basketList");
                if(basketList.contains(furniture)){
                    basketList.remove(furniture);
                    session.setAttribute("basketList", basketList);
                }
                request.setAttribute("basketListCount", basketList.size());
                request.getRequestDispatcher("/showBasket").forward(request, response);
                break;
            case "/showBasket":
                List<Furniture> listFurnituresInBasket = (List<Furniture>) session.getAttribute("basketList");
                request.setAttribute("today", new Date());
                request.setAttribute("listFurnituresInBasket", listFurnituresInBasket);
                if(listFurnituresInBasket == null || listFurnituresInBasket.isEmpty()){
                    request.getRequestDispatcher("/listFurnitures").forward(request, response);
                    break;
                }
                request.getRequestDispatcher(LoginServlet.pathToFile.getString("showBasket")).forward(request, response);
                break;
            case "/buyFurnitures":
                user = userFacade.find(user.getId());
                //Получаем список книг в корзине из сессии
                listFurnituresInBasket = (List<Furniture>) session.getAttribute("basketList");
                //Получаем массив отмеченных для покупки книг в корзине или нажатия ссылки при прочтении отрывка
                String[] selectedFurnitures = request.getParameterValues("selectedFurnitures");
                if(selectedFurnitures == null){
                    request.setAttribute("info", "Чтобы купить выберите книгу.");
                    request.getRequestDispatcher("/listFurnitures").forward(request, response);
                    break;
                }
                int userWallet = user.getBuyer().getWallet();
                Calendar c = new GregorianCalendar();
                List<Furniture> buyFurnitures = new ArrayList<>();
                int totalPricePurchase = 0;
                //Считаем стоимость покупаемых товаров, которые отмечены в корзине
                for(String selectedFurnitureId : selectedFurnitures){
                    Furniture f = furnitureFacade.find(Long.parseLong(selectedFurnitureId));
                    long today = c.getTimeInMillis();
                    long bookDiscountDate = f.getDiscountDate().getTime();
                    if(f.getDiscountDate() != null && today > bookDiscountDate){
                        totalPricePurchase += f.getPrice() - f.getPrice()*f.getDiscount()/100;
                    }else{
                        totalPricePurchase += f.getPrice();
                    }
                    buyFurnitures.add(f);
                }
                if(userWallet < totalPricePurchase){
                    request.setAttribute("info", "Недостаточно денег для покупки");
                    request.getRequestDispatcher("/listBooks").forward(request, response);
                    break;
                }
                //Покупаем товар
                for(Furniture buyFurniture : buyFurnitures){
                    if(listFurnituresInBasket != null) listFurnituresInBasket.remove(buyFurniture); //если запрос пришел из корзины - удаляем из корзины купленную книгу
                    historyFacade.create(new History(buyFurniture,user.getBuyer(), new GregorianCalendar().getTime(),null));
                }
                //Списываем у покупателя деньги за купленные товары
                Buyer b = buyerFacade.find(user.getBuyer().getId());
                b.setWallet(b.getWallet()-totalPricePurchase);
                buyerFacade.edit(b);
                //Редактируем данные вошедшего покупателя в сессии
                User bUser = userFacade.find(user.getId());
                session.setAttribute("user", bUser);
                userFacade.edit(bUser);
                if(listFurnituresInBasket != null){
                    //если запрос из корзины
                    request.setAttribute("listFurnituresInBasket", listFurnituresInBasket);
                    request.setAttribute("basker", listFurnituresInBasket.size());
                }
                request.setAttribute("info", "Куплено товара: "+selectedFurnitures.length);
                request.getRequestDispatcher("/listBooks").forward(request, response);
                break;
            case "/purchasedFurnitures":
                request.setAttribute("activePurchasedFurnitures", "true");
                List<Furniture> purchasedFurnitures = historyFacade.findPurchasedFurniture(user.getBuyer());
                request.setAttribute("listFurnitures", purchasedFurnitures);
                request.getRequestDispatcher(LoginServlet.pathToFile.getString("purchasedFurnitures")).forward(request, response);
                break;
            case "/editProfile":
                user = (User) session.getAttribute("user");
                request.setAttribute("user", user);
                request.getRequestDispatcher(LoginServlet.pathToFile.getString("editProfile")).forward(request, response);
                break;
            case "/changeProfile":
                User pUser = userFacade.find(user.getId());
                Buyer pBuyer = buyerFacade.find(user.getBuyer().getId());
                String firstname = request.getParameter("firstname");
                if(pBuyer != null && !"".equals(firstname)) pBuyer.setFirstname(firstname);
                String lastname = request.getParameter("lastname");
                if(pBuyer != null && !"".equals(lastname)) pBuyer.setLastname(lastname);
                String phone = request.getParameter("phone");
                if(pBuyer != null && !"".equals(phone)) pBuyer.setPhone(phone);
                String wallet = request.getParameter("wallet");
                if(pBuyer != null && !"".equals(wallet)) pBuyer.setWallet(wallet);
//                String login = request.getParameter("login");
//                if(pUser != null && !"".equals(login)) pUser.setLogin(login);
                String password = request.getParameter("password");
                if(pUser != null && !"".equals(password)){
                    //здесь шифруем пароль и получаем соль
                    pUser.setPassword(password);
                    //user.setSalt(salt);
                    
                }
                buyerFacade.edit(pBuyer);
                pUser.setBuyer(pBuyer);
                userFacade.edit(pUser);
                session.setAttribute("user", null);//эта строка может быть избыточной
                session.setAttribute("user", pUser);
                session.setAttribute("info", "Профиль покупателя изменен");
                request.getRequestDispatcher("/editProfile").forward(request, response);
                break;
            case "/readFurniture":
                furnitureId = request.getParameter("furnitureId");
                if(furnitureId == null || "".equals(furnitureId)){
                    request.setAttribute("info","Выберите товар" );
                    request.getRequestDispatcher("/purchasedFurnitures").forward(request, response);
                }
                furniture = furnitureFacade.find(Long.parseLong(furnitureId));
                List<Furniture> buyFurnituresList = historyFacade.findPurchasedFurniture(user.getBuyer());
                try {
                    File file = new File(furniture.getText().getPath());
                    //FileBuyer fileBuyer = new FileBuyer(file);
                    //BufferedBuyer buyer = new BufferedBuyer(fileBuyer);
                    try (PrintWriter out = response.getWriter()) {
                        out.println("<!DOCTYPE html>");
                        out.println("<html>");
                        out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");
                        out.println("<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1\" crossorigin=\"anonymous\">");
                        out.println("<head>");
                        out.println("<title>"+furniture.getName()+"</title>");            
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<div class=\"container\">");
                        out.println("<p>");
                        if(buyFurnituresList.contains(furniture)){//если список купленных пользователем товаров СОДЕРЖИТ товар
                            try(Stream<String> stream = Files.lines(file.toPath(), StandardCharsets.UTF_8)){
                                stream.forEachOrdered(line -> out.print(line));
                            }
                        }else{//если список купленных пользователем товаров НЕ СОДЕРЖИТ книгу
                            try (Stream<String> lines = Files.lines (file.toPath(), StandardCharsets.UTF_8)){
                                int numLine = 0;
                                for (String line : (Iterable<String>) lines::iterator)
                                {
                                    out.print(line);
                                    numLine++;
                                    if(numLine > 200) break;
                                }
                            }
                            out.println("... ");
                            out.println("<br>");
                            out.println("<p class=\"w-100 d-flex justify-content-center\"><a href=\"buyBooks?selectedBooks="+furniture.getId()+"\">(Для продолжения просмотра купите товар).</a></p>");
                            out.println("</p>");
                        }
                        out.println("</p>");
                        out.println("</div>");
                        out.println("<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW\" crossorigin=\"anonymous\"></script>");
                        out.println("</body>");
                        out.println("</html>");
                    }
                    
                } catch (Exception e) {
                    request.setAttribute("info", "Невозможно прочесть файл");
                    request.getRequestDispatcher("/listFurnitures").forward(request, response);
                }
                break;
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