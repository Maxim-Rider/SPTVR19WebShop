/*
 * Алгоритм создания Web приложения на Java
 * 
 * 1. Создать WebApplication в NetBeans
 * 2. Создать сущностные классы c аннотациями в пакете entity раздела Source Packages
 * 3. Создать базу данных и настроить persistence.xml.
 * 4. Создать сессионные Java Enterprice Beans для каждого сущностного класса 
 *    с помощью помощника NetBeans
 * 5. Создать странички jsp в разделе Web Pages (/web). 
 *    Обязательная папка WEB-INF служит для сокрытия ресурсов от прямого доступа
 * 6. Создать сервлет "MyServlet" в пакете servlets раздела Source Packages.
 * 7. Настроить параметр аннотации @WebServlet(urlPatterns={...})
 *    При запросе от клиента содержащего эти параметры будет выполняться метод
 *    ProcessRequest сервлета "MyServlet", который управляется веб контейнером
 * 8. Получить текущий запрос из запроса "path"
 * 9. Обработать запрос в switch и с помощью метода getRequestDispatcher()
 *    отдать страничку jsp с данными клиенту. 
 *    Например:
 *    request.getRequestDispatcher("/WEB-INF/addBookForm.jsp").forward(request, response);
 * 10. Для получения объектов классов "фасадов" использовать аннотацию @EJB 
 *    в поле класса "MyServlet"
 *
 */
//package servlets;
//
//import entity.Furniture;
//import entity.History;
//import entity.Buyer;
//import java.io.IOException;
//import java.util.GregorianCalendar;
//import java.util.List;
//import javax.ejb.EJB;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import session.FurnitureFacade;
//import session.HistoryFacade;
//import session.BuyerFacade;
///**
// * @author Juri
// */
//@WebServlet(name = "MyServlet", urlPatterns = {
//    "/addFurniture",
//    "/createFurniture",
//    "/listFurnitures",
//    "/addBuyer",
//    "/createBuyer",
//    "/listBuyers",
//    "/purchaseFurnitureForm",
//    "/purchaseFurniture",
//    "/editFurnitureForm",
//    "/editFurniture",
//    "/editBuyerForm",
//    "/editBuyer",
//    
//    
//})
//public class MyServlet extends HttpServlet {
//    
//@EJB
//private FurnitureFacade furnitureFacade;
//
//@EJB
//private BuyerFacade buyerFacade;
//
//@EJB
//private HistoryFacade historyFacade;
//    /**
//     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
//     * methods.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        request.setCharacterEncoding("UTF-8");
//        
//        History history;
//        Buyer buyer;
//        Furniture furniture;
//        
//        String path = request.getServletPath();
//        switch (path){
//            case "/addFurniture":
//                request.getRequestDispatcher("/WEB-INF/addFurnitureForm.jsp").forward(request, response);
//                break;
//            case "/createFurniture":
//                String name = request.getParameter("name");
//                String color = request.getParameter("color");
//                String size = request.getParameter("size");
//                String quantity = request.getParameter("quantity");
//                String price = request.getParameter("price");
//               
//                if ("".equals(name) || name == null
//                        || "".equals(color) || color == null
//                        || "".equals(size) || size == null
//                        || "".equals(quantity) || quantity == null
//                        || "".equals(price) || price == null){
//                    request.setAttribute("name", name);
//                    request.setAttribute("color", color);
//                    request.setAttribute("size", size);
//                    request.setAttribute("quantity", quantity);
//                    request.setAttribute("price", price);
//                    request.setAttribute("info", "Заполните все поля.");
//                    request.getRequestDispatcher("/WEB-INF/addFurnitureForm.jsp").forward(request, response); 
//                    break;
//                }
//                else if (Integer.parseInt(price) < 1) {
//                    request.setAttribute("info","Цена не может быть меньше 1$!");          
//                    request.getRequestDispatcher("/WEB-INF/addFurnitureForm.jsp").forward(request, response);
//                    break; 
//                }    
//                furniture = new Furniture(name, color, size, Integer.parseInt(quantity), Integer.parseInt(price));
//                furnitureFacade.create(furniture);
//                request.setAttribute("info", "Товар\"" +furniture.getName()+ "\" был добавлен");
//                request.getRequestDispatcher("index.jsp").forward(request, response);
//                break;
//            case "/listFurnitures":
//                List<Furniture> listFurnitures = furnitureFacade.findAll();
//                request.setAttribute("listFurnitures", listFurnitures);
//                request.getRequestDispatcher("/WEB-INF/listFurnitures.jsp").forward(request, response);
//                break; 
//            case "/addBuyer":
//                request.getRequestDispatcher("/WEB-INF/addBuyerForm.jsp").forward(request, response);
//                break;
//            case "/createBuyer":
//                String firstname = request.getParameter("firstname");
//                String lastname = request.getParameter("lastname");
//                String phone = request.getParameter("phone");
//                String wallet = request.getParameter("wallet");
//                
//                if ("".equals(firstname) || firstname == null
//                        || "".equals(lastname) || lastname == null
//                        || "".equals(phone) || phone == null
//                        || "".equals(wallet) || wallet == null){
//                    request.setAttribute("firstname", firstname);
//                    request.setAttribute("lastname", lastname);
//                    request.setAttribute("phone", phone);
//                    request.setAttribute("wallet", wallet);
//                    
//                    request.setAttribute("info", "Заполните все поля..");
//                    request.getRequestDispatcher("/WEB-INF/addBuyerForm.jsp").forward(request, response); 
//                    break;
//                }
//                buyer = new Buyer(firstname, lastname, phone, wallet);
//                buyerFacade.create(buyer);
//                request.setAttribute("info", "Покупатель\"" +buyer.getFirstname()+" " +buyer.getLastname()+ "\" был добавлен");
//                request.getRequestDispatcher("index.jsp").forward(request, response);
//                break;
//            case "/listBuyers":
//                List<Buyer> listBuyers = buyerFacade.findAll();
//                request.setAttribute("listBuyers", listBuyers);
//                request.getRequestDispatcher("/WEB-INF/listBuyers.jsp").forward(request, response);
//                break;
//            case "/purchaseFurnitureForm":
//                listFurnitures = furnitureFacade.findAll();
//                request.setAttribute("listFurnitures", listFurnitures);
//                listBuyers = buyerFacade.findAll();
//                request.setAttribute("listBuyers", listBuyers);
//                request.getRequestDispatcher("/WEB-INF/purchaseFurnitureForm.jsp").forward(request, response);
//                break;
//            case "/purchaseFurniture":
//                String furnitureId = request.getParameter("furnitureId");
//                furniture = furnitureFacade.find(Long.parseLong(furnitureId));
//                String buyerId = request.getParameter("buyerId");
//                buyer = buyerFacade.find(Long.parseLong(buyerId));
//
//                if (!(furniture.getQuantity()-1>=0)) {
//                    request.setAttribute("info", "Нет товара");
//                    request.getRequestDispatcher("/index.jsp").forward(request, response);
//                    break;
//                } 
//                if (!(buyer.getWallet() >= furniture.getPrice())) {
//                    request.setAttribute("info", "Недостаточно денег для покупки");
//                    request.getRequestDispatcher("/index.jsp").forward(request, response);
//                    break;
//                }
//                buyer.setWallet(buyer.getWallet() - furniture.getPrice());
//                   
//                buyerFacade.edit(buyer);
//                furniture.setQuantity(furniture.getQuantity() - 1);
//                furnitureFacade.edit(furniture);
//                history = new History(furniture, buyer, new GregorianCalendar().getTime());
//                historyFacade.create(history);
//
//                request.setAttribute("info", "Товар '" + furniture.getName() + "' успешно куплен покупателем " + buyer.getFirstname() + " " + buyer.getLastname() + "!");
//                request.getRequestDispatcher("/index.jsp").forward(request, response);
//                break;
//            case "/editFurnitureForm":                  
//                furnitureId = request.getParameter("furnitureId");
//                furniture = furnitureFacade.find(Long.parseLong(furnitureId));
//                request.setAttribute("furniture", furniture);
//                request.getRequestDispatcher("/WEB-INF/editFurnitureForm.jsp").forward(request, response);
//                break;               
//            case "/editFurniture":
//                furnitureId = request.getParameter("furnitureId");
//                furniture = furnitureFacade.find(Long.parseLong(furnitureId));
//                request.setAttribute("furniture", furniture);
//                name = request.getParameter("name");
//                color = request.getParameter("color");
//                size = request.getParameter("size");
//                quantity = request.getParameter("quantity");
//                price = request.getParameter("price");
//                if("".equals(name) || name == null
//                        || "".equals(color) || color == null
//                        || "".equals(size) || size == null
//                        || "".equals(quantity) || quantity == null
//                        || "".equals(price) || price == null){
//                    request.setAttribute("info","Заполните все поля формы");
//                    request.setAttribute("name",name);
//                    request.setAttribute("color",color);
//                    request.setAttribute("size",size);
//                    request.setAttribute("quantity",quantity);
//                    request.setAttribute("price",price);
//                    request.setAttribute("furniture", furniture.getId()); 
//                    request.getRequestDispatcher("/WEB-INF/editFurnitureForm.jsp").forward(request, response);
//                    break; 
//                } else if (Integer.parseInt(price) < 1) {
//                    request.setAttribute("info","Цена не может быть меньше 1$!");          
//                    request.getRequestDispatcher("/WEB-INF/editFurnitureForm.jsp").forward(request, response);
//                    break;    
//                }   
//                furniture.setName(name);
//                furniture.setColor(color);
//                furniture.setSize(size);
//                furniture.setQuantity(quantity);
//                furniture.setPrice(Integer.parseInt(price));
//                furnitureFacade.edit(furniture);
//                request.setAttribute("furnitureId", furniture.getId());
//                request.setAttribute("info","Товар успешно отредактирован: " + furniture.toString() );
//                request.getRequestDispatcher("/index.jsp").forward(request, response);
//                break;              
//            case "/editBuyerForm":
//                buyerId = request.getParameter("buyerId");
//                buyer = buyerFacade.find(Long.parseLong(buyerId));
//                request.setAttribute("buyer", buyer);
//                request.getRequestDispatcher("/WEB-INF/editBuyerForm.jsp").forward(request, response);
//                break;
//            case "/editBuyer":
//                buyerId = request.getParameter("buyerId");
//                firstname = request.getParameter("firstname");
//                lastname = request.getParameter("lastname");
//                phone = request.getParameter("phone");
//                wallet = request.getParameter("wallet");
//                
//                if("".equals(firstname) || firstname == null
//                        || "".equals(lastname) || lastname == null
//                        || "".equals(phone) || phone == null
//                        || "".equals(wallet) || wallet == null){
//                    request.setAttribute("info", "Поля не должны быть пустыми");
//                    request.getRequestDispatcher("/editBuyerForm").forward(request, response);
//                    break;
//                }
//                buyer = buyerFacade.find(Long.parseLong(buyerId));
//                buyer.setFirstname(firstname);
//                buyer.setLastname(lastname);
//                buyer.setPhone(phone);
//                buyer.setWallet(wallet);
//                buyerFacade.edit(buyer);
//                request.setAttribute("buyerId", buyerId);
//                request.setAttribute("info", "Данные покупателя отредактированы");
//                request.getRequestDispatcher("/editBuyerForm").forward(request, response);
//                break;
//   
//        }               
//    }
//
//    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
//    /**
//     * Handles the HTTP <code>GET</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//    }
//
//    /**
//     * Handles the HTTP <code>POST</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//    }
//
//    /**
//     * Returns a short description of the servlet.
//     *
//     * @return a String containing servlet description
//     */
//    @Override
//    public String getServletInfo() {
//        return "Short description";
//    }// </editor-fold>
//
//}