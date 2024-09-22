package Servlets;

import Conexion.BD;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Solicitud")
public class Solicitud extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
       
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        out.println("<html>");
        out.println("<head><title>Registrar Usuario</title>");
        out.println("<link rel='stylesheet' type='text/css' href='Style.css'>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Registrar Usuario</h1>");
        out.println("<form method='POST' action='Solicitud'>");
        out.println("ID: <input type='number' name='id'><br>");
        out.println("Nombre: <input type='text' name='Nombre'><br>");
        out.println("Edad: <input type='number' name='Edad'><br>");
        out.println("Email: <input type='email' name='email'><br>");
        out.println("Clave: <input type='password' name='Clave'><br>"); 
        
        out.println("<input type='submit' value='Registrar'>");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
       
        int id = Integer.parseInt(request.getParameter("id"));  
        String Nombre = request.getParameter("Nombre");
        int Edad = Integer.parseInt(request.getParameter("Edad"));  
        String email = request.getParameter("email");
        String Clave = request.getParameter("Clave");
        
        try (Connection conn = BD.getConnection())
        {
            String sql = "INSERT INTO Usuario (id, Nombre, Edad, email, Clave) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            
           
            stmt.setInt(1, id);        
            stmt.setString(2, Nombre);  
            stmt.setInt(3, Edad);       
            stmt.setString(4, email);   
            stmt.setString(5, Clave);   
            
            stmt.executeUpdate();  
            
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html>");
            out.println("<head><title>Registro Exitoso</title></head>");
            out.println("<body>");
            out.println("<h1>Usuario registrado exitosamente</h1>");
            out.println("<a href='Solicitud'>Registrar otro usuario</a>");
            out.println("</body>");
            out.println("</html>");
        } catch (SQLException e) {
            throw new ServletException("Error al registrar el usuario", e);
        }
    }
}
