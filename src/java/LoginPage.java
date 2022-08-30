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

@WebServlet(urlPatterns = {"/LoginPage"})
public class LoginPage extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            String email = request.getParameter("email");
            String password = request.getParameter("pw");
            
           Class.forName("com.mysql.jdbc.Driver");
           
           Connection con=DriverManager.getConnection("jdbc:mysql://localhost/guvi","root","root123");
           
           PreparedStatement pst=con.prepareStatement("select * from signup_details");
           
           ResultSet rs=pst.executeQuery();
           int success = 0;
           String dbemail;
           String dbpass;
           while(rs.next())
           {
               dbemail = rs.getString("email");
               dbpass = rs.getString("password");
               
           
               if((dbemail.equals(email)) && (dbpass.equals(password)))
               {
                   success = 1;
               }
               else if((dbemail.equals(email)) && (!dbpass.equals(password))){
                   success = 2;
               }
               else if((dbpass.equals(password)) && (!dbemail.equals(email))){
                   success = 3;
               }
           }
           
            if(success==1){
                response.sendRedirect("profile.html");
                
            }
            else if(success==2)
            {
                out.println("You have entered incorrect password! ");
            }
            else if (success==3){
                out.println("Incorrect details! ");
            }
            else{
                out.println("Incorrect details! <br>");
                out.println("Ensure that you have created an account before login <br>");
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}