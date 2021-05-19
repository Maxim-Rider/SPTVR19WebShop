/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;


import entity.Buyer;
import entity.Furniture;
import entity.User;
import entity.Cover;
import entity.Text;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
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
import session.CoverFacade;
import session.TextFacade;
import tools.SheduleDiscount;


/**
 *
 * @author Comp
 */
@WebServlet(name = "ManagerServlet", urlPatterns = {
    "/addFurniture",
    "/createFurniture",
    "/editFurnitureForm",
    "/editFurniture",
    "/editBuyerForm",
    "/editBuyer",
    "/uploadForm",
    "/discountForm",
    "/setDiscount",
})
public class ManagerServlet extends HttpServlet {

    @EJB
    private HistoryFacade historyFacade;
    
    @EJB
    private UserFacade userFacade;

    @EJB
    private FurnitureFacade furnitureFacade;

    @EJB
    private BuyerFacade buyerFacade;
    
    @EJB private UserRolesFacade userRolesFacade;
    @EJB private CoverFacade coverFacade;
    @EJB private TextFacade textFacade;



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
        boolean isRole = userRolesFacade.isRole("MANAGER", user);
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
        String path = request.getServletPath();
        
        switch (path) {
            case "/addFurniture":
                request.setAttribute("activeAddFurniture", "true");
                List<Cover> listCovers = coverFacade.findAll();
                request.setAttribute("listCovers", listCovers);
                request.getRequestDispatcher(LoginServlet.pathToFile.getString("addFurniture")).forward(request, response);
                break;
            case "/createFurniture":
                String name = request.getParameter("name");
                String color = request.getParameter("color");
                String size = request.getParameter("size");
                String quantity = request.getParameter("quantity");
                String price = request.getParameter("price");
                String coverId = request.getParameter("coverId");
                String textId = request.getParameter("textId");
                if ("".equals(name) || name == null
                        || "".equals(color) || color == null
                        || "".equals(size) || size == null
                        || "".equals(quantity) || quantity == null
                        || "".equals(price) || price == null
                        || "".equals(textId) || textId == null
                        || "".equals(coverId) || coverId == null
                        || textId==null || "".equals(textId)){
                    request.setAttribute("name", name);
                    request.setAttribute("color", color);
                    request.setAttribute("size", size);
                    request.setAttribute("quantity", quantity);
                    request.setAttribute("price", price);
                    request.setAttribute("coverId",coverId);
                    request.setAttribute("textId",textId);
                    request.setAttribute("info", "Заполните все поля.");
                    request.getRequestDispatcher("/addFurniture").forward(request, response);
                    break;
                }
                else if (Integer.parseInt(price) < 1) {
                    request.setAttribute("info","Цена не может быть меньше 1$!");          
                    request.getRequestDispatcher("/addFurniture").forward(request, response);
                    break; 
                }
                Cover cover = coverFacade.find(Long.parseLong(coverId));
                Text text = textFacade.find(Long.parseLong(textId));
                Furniture furniture = new Furniture(
                        name, 
                        color, 
                        size,
                        quantity, 
                        price, 
                        cover,
                        text
                );
                furnitureFacade.create(furniture);
                request.getRequestDispatcher("/listFurnitures")
                        .forward(request, response);
                
                break;
            case "/editFurnitureForm":
                request.setAttribute("activeEditFurnitureForm", "true");
                String furnitureId = request.getParameter("furnitureId");
                furniture = furnitureFacade.find(Long.parseLong(furnitureId));
                request.setAttribute("furniture", furniture);
                request.getRequestDispatcher(LoginServlet.pathToFile.getString("editFurniture")).forward(request, response);
                break;
            case "/editFurniture":
                furnitureId = request.getParameter("furnitureId");
                furniture = furnitureFacade.find(Long.parseLong(furnitureId));
                name = request.getParameter("name");
                color = request.getParameter("color");
                size = request.getParameter("size");
                quantity = request.getParameter("quantity");
                price = request.getParameter("price");
                coverId = request.getParameter("coverId");
                if("".equals(name) || name == null
                        || "".equals(color) || color == null
                        || "".equals(size) || size == null
                        || "".equals(quantity) || quantity == null
                        || "".equals(price) || price == null
                        || "".equals(coverId) || coverId == null){
                    request.setAttribute("info","Заполните все поля формы");
                    request.setAttribute("name",name);
                    request.setAttribute("color",color);
                    request.setAttribute("size",size);
                    request.setAttribute("quantity",quantity);
                    request.setAttribute("price",price);
                    request.setAttribute("coverId",coverId);
                    request.setAttribute("furnitureId", furniture.getId());
                    request.getRequestDispatcher("/editFurnitureForm").forward(request, response);
                    break; 
                } else if (Integer.parseInt(price) < 1) {
                    request.setAttribute("info","Цена не может быть меньше 1$!");          
                    request.getRequestDispatcher("/editFurnitureForm").forward(request, response);
                    break;    
                }   
                furniture.setName(name);
                furniture.setColor(color);
                furniture.setSize(size);
                furniture.setQuantity(quantity);
                furniture.setPrice(Integer.parseInt(price));
                furnitureFacade.edit(furniture);
                request.setAttribute("furnitureId", furniture.getId());
                request.setAttribute("info","Товар успешно отредактирован: " + furniture.toString() );
                request.getRequestDispatcher(LoginServlet.pathToFile.getString("index")).forward(request, response);
                break;                
            case "/editBuyerForm":
                request.setAttribute("activeEditBuyerForm", "true");
                String buyerId = request.getParameter("buyerId");
                Buyer buyer = buyerFacade.find(Long.parseLong(buyerId));
                request.setAttribute("buyer", buyer);
                request.getRequestDispatcher(LoginServlet.pathToFile.getString("editBuyer")).forward(request, response);
                break;
            case "/editBuyer":
                buyerId = request.getParameter("buyerId");
                String firstname = request.getParameter("firstname");
                String lastname = request.getParameter("lastname");
                String phone = request.getParameter("phone");
                String wallet = request.getParameter("wallet");

                if("".equals(firstname) || firstname == null
                        || "".equals(lastname) || lastname == null
                        || "".equals(phone) || phone == null
                        || "".equals(wallet) || wallet == null){
                    request.setAttribute("info", "Поля не должны быть пустыми");
                    request.getRequestDispatcher("/editBuyerForm").forward(request, response);
                    break;
                }
                
                buyer = buyerFacade.find(Long.parseLong(buyerId));
                buyer.setFirstname(firstname);
                buyer.setLastname(lastname);
                buyer.setPhone(phone);
                buyer.setWallet(wallet);
                buyerFacade.edit(buyer);
                request.setAttribute("buyerId", buyer.getId());
                request.setAttribute("info", "Данные покупателя отредактированы");
                request.getRequestDispatcher("/editBuyerForm").forward(request, response);
                break;
            case "/uploadForm":
                    
                request.getRequestDispatcher(LoginServlet.pathToFile.getString("upload")).forward(request, response);
                break;
                
            case "/discountForm":
                request.setAttribute("activeDiscountForm", "true");
                List<Furniture> listFurnitures = furnitureFacade.findNotDiscountBook();
                request.setAttribute("listBooks", listFurnitures);
                request.getRequestDispatcher(LoginServlet
                                .pathToFile
                                .getString("discountForm")
                        )
                        .forward(request, response);
                break;
            case "/setDiscount":
                furnitureId = request.getParameter("furnitureId");
                String discount = request.getParameter("discount");
                String dateDiscount = request.getParameter("dateDiscount");//format yyyy-mm-dd
                String duration = request.getParameter("duration");
                String durationType = request.getParameter("durationType");
                if(furnitureId==null || "".equals(furnitureId)  
                       || discount==null || "".equals(discount) 
                       || dateDiscount==null || "".equals(dateDiscount) 
                       || duration==null || "".equals(duration) 
                       || durationType == null || "".equals(durationType)
                       ){
                    request.setAttribute("info", "Заполние все поля");
                    request.getRequestDispatcher("/discountForm")
                            .forward(request, response);
                }
                furniture = furnitureFacade.find(Long.parseLong(furnitureId));
                String year = dateDiscount.substring(0,4);
                String month = dateDiscount.substring(5,5+2);
                String day = dateDiscount.substring(8,8+2);
                Calendar cDateDiscount = new GregorianCalendar(Integer.parseInt(year), Integer.parseInt(month)-1, Integer.parseInt(day));
                SheduleDiscount sheduleDiscount = new SheduleDiscount();
                Furniture discountFurniture = sheduleDiscount.setDiscount(
                        furniture, 
                        Integer.parseInt(discount),
                        cDateDiscount.getTime(), 
                        Integer.parseInt(duration),
                        durationType
                );
                furnitureFacade.edit(discountFurniture);
                request.getRequestDispatcher("/listFurnitures")
                        .forward(request, response);
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