/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Role;
import java.util.ArrayList;
import java.util.List;
import entity.User;
import entity.UserRoles;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Comp
 */
@Stateless
public class UserRolesFacade extends AbstractFacade<UserRoles> {

    @PersistenceContext(unitName = "SPTVR19WebShopPU")
    private EntityManager em;

    @EJB private RoleFacade roleFacade;
    @EJB private UserRolesFacade userRolesFacade;

        
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserRolesFacade() {
        super(UserRoles.class);
    }

    public boolean isRole(String roleName, User user) {
        try {
            UserRoles userRoles = (UserRoles) em.createQuery("SELECT userRoles FROM UserRoles userRoles WHERE userRoles.role.roleName = :roleName AND userRoles.user = :user")
                    .setParameter("roleName", roleName)
                    .setParameter("user", user)
                    .getSingleResult();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public List<Role> getRolesForUser(User user) {
        try {
            return em.createQuery("SELECT ur.role FROM UserRoles ur WHERE ur.user = :user")
                    .setParameter("user", user)
                    .getResultList();
        } catch (Exception e){
            return new ArrayList<>();
        }
    }

    
//    public void removeRoleFromUser(Role r, User u){
//        if(this.isRole(r.getRoleName(), u)){
//            em.createQuery("DELETE FROM UserRoles ur WHERE ur.user = :user AND ur.role = :role")
//                    .setParameter("user", u)
//                    .setParameter("role", r)
//                    .executeUpdate();
//        }
//    }
    public String getTopRoleForUser(User user) {
        if(user == null) return "";
        List<UserRoles> listUserRoles = em.createQuery("SELECT userRoles FROM UserRoles userRoles WHERE userRoles.user = :user")
                .setParameter("user", user)
                .getResultList();
        for(int i=0;i<listUserRoles.size();i++){
            if("ADMIN".equals(listUserRoles.get(i).getRole().getRoleName())){
                return "ADMIN";
            }
        }
        for(int i=0;i<listUserRoles.size();i++){
            if("MANAGER".equals(listUserRoles.get(i).getRole().getRoleName())){
                return "MANAGER";
            }
        }
        for(int i=0;i<listUserRoles.size();i++){
            if("BUYER".equals(listUserRoles.get(i).getRole().getRoleName())){
                return "BUYER";
            }
        }
        return "";
    }
    public void setNewRole(UserRoles userRoles) {
        
        this.em.createQuery("DELETE FROM UserRoles userRoles WHERE userRoles.user = :user")
                .setParameter("user", userRoles.getUser())
                .executeUpdate();
        UserRoles ur;
        if("ADMIN".equals(userRoles.getRole().getRoleName())){
            ur = new UserRoles(userRoles.getUser(),roleFacade.findByName("ADMIN"));
            this.create(ur);
            ur = new UserRoles(userRoles.getUser(),roleFacade.findByName("MANAGER"));
            this.create(ur);
            ur = new UserRoles(userRoles.getUser(),roleFacade.findByName("BUYER"));
            this.create(ur);
        }
        if("MANAGER".equals(userRoles.getRole().getRoleName())){
            ur = new UserRoles(userRoles.getUser(),roleFacade.findByName("MANAGER"));
            this.create(ur);
            ur = new UserRoles(userRoles.getUser(),roleFacade.findByName("BUYER"));
            this.create(ur);
        }
        if("BUYER".equals(userRoles.getRole().getRoleName())){
            ur = new UserRoles(userRoles.getUser(),roleFacade.findByName("BUYER"));
            this.create(ur);
        }
    }

    public List<String> findRoles(User user) {
        List<String> listRoles = new ArrayList<>();
        listRoles.add("BUYER");
        listRoles.add("MANAGER"); 
        listRoles.add("ADMIN");
        return listRoles;
//         return em.createQuery("SELECT ur.role.roleName FROM UserRoles ur WHERE ur.user = :user")
//                .setParameter("user", user)
//                .getResultList();
         
    }
    
    public void setRole(String roleName, User user) {
        Role role = roleFacade.findByName(roleName);
        UserRoles ur = new UserRoles(user, role);
        userRolesFacade.create(ur);
    }

}
