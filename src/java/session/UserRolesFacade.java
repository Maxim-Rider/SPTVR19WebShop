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

    public void setRoleToUser(Role r, User u) {
        if(!this.isRole(r.getRoleName(), u)){
            UserRoles ur = new UserRoles(u, r);
            this.create(ur);
        }
    }
    public void removeRoleFromUser(Role r, User u){
        if(this.isRole(r.getRoleName(), u)){
            em.createQuery("DELETE FROM UserRoles ur WHERE ur.user = :user AND ur.role = :role")
                    .setParameter("user", u)
                    .setParameter("role", r)
                    .executeUpdate();
        }
    }
}
