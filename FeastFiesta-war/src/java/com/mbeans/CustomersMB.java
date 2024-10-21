package com.mbeans;

import com.entitybeans.Customers;
import com.sessionbeans.CustomersFacadeLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Huynh
 */
@Named(value = "customersMB")
@RequestScoped
public class CustomersMB {

    @EJB
    private CustomersFacadeLocal customersFacade;
    
    private String username;
    private String password;
    private Customers customer;
    private Customers loggedInUser;
    private String confirmpassword;
    private boolean loggedIn;

    public CustomersMB() {
        customer = new Customers();
    }

    // Show all customers
    public List<Customers> showAllCustomers() {
        return customersFacade.findAll();
    }

    // Check if username and password match
    public String checkLogin() {
        FacesContext context = FacesContext.getCurrentInstance();
        Customers cus = customersFacade.findCustomerbyUsername(username);
        if (cus != null) {
            if (cus.getPassword().equals(password)) {
                HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
                session.setMaxInactiveInterval(60 * 60 * 24); // 1-day session expiry
                session.setAttribute("username", cus.getUsername());
                session.setAttribute("customerID", cus.getCustomerID());
                
                // Admin check
                if (cus.getUsername().equals("admin")) {
                    session.setAttribute("role", "admin");
                    return "admin_home"; // Admin-specific page
                } else {
                    session.setAttribute("role", "customer");
                    return "home"; // Customer-specific page
                }
            } else {
                context.addMessage("loginForm:password", new FacesMessage("Password does not match"));
                return "login";
            }
        }
        context.addMessage("loginForm:username", new FacesMessage("Username is incorrect"));
        return "login";
    }

    // Logout user or admin
    public String logout() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "home";
    }

    // Register a new customer
    public String registryCustomers() {
        FacesContext context = FacesContext.getCurrentInstance();

        // Check if username already exists
        if (customersFacade.isUsernameExists(customer.getUsername())) {
            context.addMessage("formregistry:username", new FacesMessage("Username exists"));
            return "registry";
        }

        // Check if email is already used
        if (customersFacade.isEmailExists(customer.getEmail())) {
            context.addMessage("formregistry:email", new FacesMessage("Email already exists"));
            return "registry";
        }

        // Validate email format
        if (!customer.getEmail().matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
            context.addMessage("formregistry:email", new FacesMessage("Invalid email format"));
            return "registry";
        }

        // Check if password and confirm password match
        if (!customer.getPassword().equals(confirmpassword)) {
            context.addMessage("formregistry:password", new FacesMessage("Passwords do not match"));
            return "registry";
        }

        // Create new customer and log in
        customersFacade.create(customer);
        HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
        session.setAttribute("username", customer.getUsername());
        session.setAttribute("customerID", customer.getCustomerID());

        return "home"; // Redirect to home page
    }

    // Add a new customer (admin functionality)
    public String addCustomer() {
        FacesContext context = FacesContext.getCurrentInstance();

        // Check if username already exists
        if (customersFacade.isUsernameExists(customer.getUsername())) {
            context.addMessage("formRegister:username", new FacesMessage("Username exists"));
            return "customers_add";
        }

        // Check if email is already used
        if (customersFacade.isEmailExists(customer.getEmail())) {
            context.addMessage("formRegister:email", new FacesMessage("Email already exists"));
            return "customers_add";
        }

        // Validate email format
        if (!customer.getEmail().matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
            context.addMessage("formAddCustomer:email", new FacesMessage("Invalid email format"));
            return "customers_add";
        }

        // Create and add the customer
        customersFacade.create(customer);
        return "customers_view"; // Redirect to customer list
    }

    // Edit customer details
   public String editCustomers() {
        if (customersFacade.find(customer.getCustomerID()) != null) {
            customersFacade.edit(customer);
        } else {
            FacesContext.getCurrentInstance().addMessage("formEditCustomer:customerID", new FacesMessage("Customer ID don't exist"));
            return findCustomerforUpdate(customer);
        }
        return "customers_view";
    }


    // Delete customer (admin functionality)
    public String deleteCustomer(Customers cus) {
        customersFacade.remove(cus);
        return "customers_view";
    }

    // Reset form
    public String resetForm() {
        customer = null;
        return "customers_view";
    }

    // Find customer for editing
    public String findCustomerforUpdate(Customers cus) {
        customer = cus;
        return "customers_edit";
    }

    // Show profile
    public String showProfile(int id) {
        customer = customersFacade.find(id);
        return "profile";
    }

    // Getters and Setters
    public Customers getCustomer() {
        return customer;
    }

    public void setCustomer(Customers customer) {
        this.customer = customer;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public String getConfirmpassword() {
        return confirmpassword;
    }

    public void setConfirmpassword(String confirmpassword) {
        this.confirmpassword = confirmpassword;
    }

    public Customers getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(Customers loggedInUser) {
        this.loggedInUser = loggedInUser;
    }
}
