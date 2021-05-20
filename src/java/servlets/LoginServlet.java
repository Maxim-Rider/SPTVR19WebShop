package servlets;

import entity.Buyer;
import entity.Furniture;
import entity.History;
import entity.User;
import entity.Role;
import entity.UserRoles;
import java.util.Date;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.BuyerFacade;
import session.FurnitureFacade;
import session.HistoryFacade;
import session.RoleFacade;
import session.UserFacade;
import session.UserRolesFacade;
import tools.EncryptPassword;

/**
 *
 * @author Comp
 */
@WebServlet(name = "LoginServlet", loadOnStartup = 1, urlPatterns = {
    "/showLoginForm",
    "/login",
    "/logout",
    "/registrationForm",
    "/registration",
    "/listFurnitures",
})
public class LoginServlet extends HttpServlet {

    @EJB 
    private UserFacade userFacade;
    
    @EJB
    private FurnitureFacade furnitureFacade;

    @EJB
    private BuyerFacade buyerFacade;
    
    @EJB
    private HistoryFacade historyFacade;
    
    private Buyer buyer;
    
    @EJB private RoleFacade roleFacade;
    @EJB private UserRolesFacade userRolesFacade;
    
    @Inject private EncryptPassword encryptPassword;

    
    public static final ResourceBundle pathToFile = ResourceBundle.getBundle("property.pathToFile");
    
    @Override
    public void init() throws ServletException {
        if(userFacade.count() > 0) return;
        String salt = encryptPassword.createSalt();
        String password = encryptPassword.createHash("12345", salt);
        //Создаем суппер администратора
        Buyer buyer = new Buyer("Max", "Kolesnikov", "58007334", "99999");
        buyerFacade.create(buyer);
        User user = new User("admin", password, salt, buyer);
        userFacade.create(user);

        //Создаем и назначаем роли пользователю
        Role role = new Role("ADMIN");
        roleFacade.create(role);
        UserRoles userRoles = new UserRoles(user, role);
        userRolesFacade.create(userRoles);

        role = new Role("MANAGER");
        roleFacade.create(role);
        userRoles = new UserRoles(user, role);
        userRolesFacade.create(userRoles);

        role = new Role("BUYER");
        roleFacade.create(role);
        userRoles = new UserRoles(user, role);
        userRolesFacade.create(userRoles);

    }



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
        List<Furniture> purchasedFurnitures = null;
        HttpSession session = request.getSession(false);
        User user = null;
        if(session != null){
            user=(User)session.getAttribute("user");
            if(user != null){
                if(userRolesFacade.isRole("ADMIN",user)){
                    request.setAttribute("role", "ADMIN");
                }else if(userRolesFacade.isRole("MANAGER",user)){
                    request.setAttribute("role", "MANAGER");
                }else if(userRolesFacade.isRole("BUYER",user)){
                    request.setAttribute("role", "BUYER");
                }
            }
        }
        String path = request.getServletPath();

        switch (path) {
            case "/index":
                List<Furniture> listFurnitures = furnitureFacade.findAll();
                request.setAttribute("listFurnitures", listFurnitures);
                request.getRequestDispatcher(LoginServlet.pathToFile.getString("index")).forward(request, response);
                break;
            case "/showLoginForm":
                request.setAttribute("activeEnter", "true");
                request.getRequestDispatcher(LoginServlet.pathToFile.getString("login")).forward(request, response);
                break;
            case "/login":
                String login = request.getParameter("login");
                String password = request.getParameter("password");
                if("".equals(login) || login == null
                       || "".equals(password) || password == null){
                    request.setAttribute("info","Заполните все поля");
                    request.getRequestDispatcher("/showLoginForm").forward(request, response);
                    break;
                }
                user = userFacade.findByLogin(login);
                if(user == null){
                    request.setAttribute("info","Нет такого пользователя");
                    request.getRequestDispatcher("/showLoginForm").forward(request, response);
                    break;
                }
                String salt = user.getSalt();
                String encryptPwd = encryptPassword.createHash(password, salt);
                if(!encryptPwd.equals(user.getPassword())){
                    request.setAttribute("info","Нет такого пользователя");
                    request.getRequestDispatcher("/showLoginForm").forward(request, response);
                    break;
                }
                session = request.getSession(true);
                session.setAttribute("user", user);
                request.setAttribute("info","Вы вошли как "+ user.getLogin());
                request.setAttribute("role", userRolesFacade.getTopRoleForUser(user));
                request.getRequestDispatcher("/index").forward(request, response);
                break;
            case "/logout":
                session = request.getSession(false);
                if(session != null){
                    session.invalidate();
                }
                request.setAttribute("role", null);
                request.setAttribute("info", "Вы вышли! :)");
                request.getRequestDispatcher(LoginServlet.pathToFile.getString("index")).forward(request, response);
                break;
                
            
            case "/registrationForm":
                request.setAttribute("activeRegistrationForm", "true");
                request.getRequestDispatcher(LoginServlet.pathToFile.getString("registration")).forward(request, response);
                break;
            case "/registration":
                String firstname = request.getParameter("firstname");
                String lastname = request.getParameter("lastname");
                String phone = request.getParameter("phone");
                String wallet = request.getParameter("wallet");
                login = request.getParameter("login");
                password = request.getParameter("password");
                
                if ("".equals(firstname) || firstname == null
                        || "".equals(lastname) || lastname == null
                        || "".equals(phone) || phone == null
                        || "".equals(wallet) || wallet == null
                        || "".equals(login) || login == null
                        || "".equals(password) || password == null){
                    request.setAttribute("firstname", firstname);
                    request.setAttribute("lastname", lastname);
                    request.setAttribute("phone", phone);
                    request.setAttribute("wallet", wallet);
                    request.setAttribute("info", "Заполните все поля..");
                    request.getRequestDispatcher("/WEB-INF/addBuyerForm").forward(request, response); 
                    break;
                }
                buyer = new Buyer(firstname, lastname, phone, wallet);
                buyerFacade.create(buyer);
                salt = encryptPassword.createSalt();
                encryptPwd = encryptPassword.createHash(password, salt);
                user = new User(login, encryptPwd, salt, buyer);
                userFacade.create(user);
                Role roleBuyer = roleFacade.findByName("BUYER");
                UserRoles userRoles = new UserRoles(user, roleBuyer);
                userRolesFacade.create(userRoles);
                request.setAttribute("info", "Покупатель\"" +buyer.getFirstname()+" " +buyer.getLastname()+ "\" был зарегестрирован");
                request.getRequestDispatcher(LoginServlet.pathToFile.getString("index")).forward(request, response);
                break;
            case "/listFurnitures":
                request.setAttribute("activeListFurniture", "true");

                request.setAttribute("today", new Date());
                listFurnitures = null;
                try {
                    listFurnitures = furnitureFacade.findAll();
                    if(purchasedFurnitures != null){
                        listFurnitures.removeAll(purchasedFurnitures);
                    }
                } catch (Exception e) {
                    listFurnitures = new ArrayList<>();
                }
                
                request.setAttribute("listFurnitures", listFurnitures);
                request.getRequestDispatcher(LoginServlet.pathToFile.getString("listFurnitures")).forward(request, response);
                break;    
//            case "/descriptionForm":
//                List<History> listBoughtFurnitures = historyFacade.findAll();
//                request.setAttribute("listBoughtFurnitures", listBoughtFurnitures);
//                request.getRequestDispatcher("").forward(request, response);
//            case "/description":
                
                
                
            
            
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