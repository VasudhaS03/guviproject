
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <title>Sign up form </title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel = "stylesheet" href="signupstyle.css">
    <script>
        function validate(){ 
            var password = document.signupform.pw;
            var cpassword = document.signupform.cpw;

            if(password.value != cpassword.value ){
                alert("Password mismatch !!");
                return false;
            }
            return true;
        }
    </script>    
    </head>
    <body>
        
        <div class = "container">
            <%@include file="message.jsp" %>
	<div class = "title"> SIGN UP </div>
            <br><hr>
            
            <form name="signupform" id="signupform" action = "SignUp" method="post" >
                <div class = "user-details">

                <div class = "input-box">
                <span class = "details"> Full Name </span>
                <input type = "text" name ="fullname" placeholder = "Enter your name" required>
                </div>

                <div class = "input-box">
                <span class = "details"> User Name </span>
                <input type = "text" name ="username" placeholder = "Enter your username" required>
                </div>

                <div class = "input-box">
                <span class = "details"> Email </span>
                <input type = "email" name ="email" placeholder = "Enter your email" required>
                </div>

                <div class = "input-box">
                <span class = "details"> Phone number </span>
                <input type = "text" name ="phno" placeholder = "Enter your number" required>
                </div>

                <div class = "input-box">
                <span class = "details"> Password </span>
                <input type = "password" name ="pw" placeholder = "Enter your password" required>
                </div>

                <div class = "input-box">
                <span class = "details"> Confirm Password </span>
                <input type = "password" name ="cpw" placeholder = "Confirm your password" required>
                </div>

                <div class = "gender-details">
                <span class = "gender-title"> Gender </span>
                <div class = "category">
                <label for = "Gender">
                <span class = "dot one"></span>
                <span class = "gender"> Male </span>
                <input type= "radio" name ="gender" value="male">
                   &nbsp; &nbsp;
                <span class = "gender"> Female </span>
                <input type= "radio" name ="gender" value="female">
                &nbsp; &nbsp;
                <span class = "gender"> Prefer not to say </span>
                <input type= "radio" name ="gender" value="prefer not to say">
                &nbsp; &nbsp;

                </label>
                </div>

                <div class = "button">
                    <input type = "submit" onclick= "validate()" value = "Sign Up" >
                </div>
                </div>
        </div>
     </form>
            <hr>
            <p style="text-align:center">or </p>
            <p> Do you have an existing account?  <a href ="login.html" style="color: cyan"> Sign In</a>
        </div>
            <hr>
            
      
    </body>
</html>
