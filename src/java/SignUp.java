import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/SignUp"})
public class SignUp extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            String fullname= request.getParameter("fullname");
            String username = request.getParameter("username");
            String email =request.getParameter("email");
            String phno =request.getParameter("phno");
            String pw =request.getParameter("pw");
            String cpw =request.getParameter("cpw");
            String gender =request.getParameter("gender");
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost/guvi","root","root123");
            
            PreparedStatement pst1 =con.prepareStatement("select * from signup_details");
            ResultSet rs1 = pst1.executeQuery();
            
            String dbemail;
            String dbuser;
            int success = 0;
            while(rs1.next())
           {
               dbemail = rs1.getString("email");
               dbuser = rs1.getString("username");
               
               if ((dbemail.equals(email)) && (dbuser.equals(username))){
                   success = 1;
               }
           }
            if (success == 0){
                if((cpw.equals(pw)))
                {
                    PreparedStatement pst=con.prepareStatement("insert into signup_details(fullname,username,email,phoneno,password,gender)values(?,?,?,?,?,?)");
                    pst.setString(1, fullname);
                    pst.setString(2, username);
                    pst.setString(3, email);
                    pst.setString(4, phno);
                    pst.setString(5, pw);
                    pst.setString(6, gender);
                    int rs=pst.executeUpdate();
            
                    if(rs>0)
                    {
                        HttpSession httpSession = request.getSession();
                        httpSession.setAttribute("message", "You have successfully registered! Login using your credentials");
                        response.sendRedirect("signup.jsp");
                    }
                }
                else
                {
                    response.sendRedirect("signup.jsp");
                } 
            }
            else{
                HttpSession httpSession = request.getSession();
                httpSession.setAttribute("message", "You have already registered!");
                response.sendRedirect("signup.jsp");
            }
           
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}

