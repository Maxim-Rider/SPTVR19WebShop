/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;


import entity.Buyer;
import entity.Furniture;
import entity.History;

import entity.User;
import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.List;
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

/**
 *
 * @author Comp
 */
@WebServlet(name = "UserServlet", urlPatterns = {

    "/purchaseFurnitureForm",
    "/purchaseFurniture",
    
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
    private List<Furniture> listFurnitures;
    private List<Buyer> listBuyers;
    private Furniture furniture;
    private Buyer buyer;
    private History history;

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
            request.getRequestDispatcher("/WEB-INF/showLoginForm.jsp").forward(request, response);
            return;
        }
        User user = (User) session.getAttribute("user");
        if(user == null){
            request.setAttribute("info", "У вас нет права для этого ресурса. Войдите в систему");
            request.getRequestDispatcher("/WEB-INF/showLoginForm.jsp").forward(request, response);
            return;
        }
        String path = request.getServletPath();

        switch (path) {
            case "/purchaseFurnitureForm":
                listFurnitures = furnitureFacade.findAll();
                request.setAttribute("listFurnitures", listFurnitures);
                listBuyers = buyerFacade.findAll();
                request.setAttribute("listBuyers", listBuyers);
                request.getRequestDispatcher("/WEB-INF/purchaseFurnitureForm.jsp").forward(request, response);
                break;
            case "/purchaseFurniture":
                String furnitureId = request.getParameter("furnitureId");
                furniture = furnitureFacade.find(Long.parseLong(furnitureId));
                String buyerId = request.getParameter("buyerId");
                buyer = buyerFacade.find(Long.parseLong(buyerId));

                if (!(furniture.getQuantity()-1>=0)) {
                    request.setAttribute("info", "Нет товара");
                    request.getRequestDispatcher("/index.jsp").forward(request, response);
                    break;
                } 
                if (!(buyer.getWallet() >= furniture.getPrice())) {
                    request.setAttribute("info", "Недостаточно денег для покупки");
                    request.getRequestDispatcher("/index.jsp").forward(request, response);
                    break;
                }
                buyer.setWallet(buyer.getWallet() - furniture.getPrice());
                   
                buyerFacade.edit(buyer);
                furniture.setQuantity(furniture.getQuantity() - 1);
                furnitureFacade.edit(furniture);
                history = new History(furniture, buyer, new GregorianCalendar().getTime());
                historyFacade.create(history);

                request.setAttribute("info", "Товар '" + furniture.getName() + "' успешно куплен покупателем " + buyer.getFirstname() + " " + buyer.getLastname() + "!");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
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