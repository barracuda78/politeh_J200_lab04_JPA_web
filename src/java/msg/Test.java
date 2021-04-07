
package msg;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import msg.DbMasterLocal;



@WebServlet(name = "Test", urlPatterns = {"/Test"})
public class Test extends HttpServlet {

    //@EJB
    //private DbMasterLocal dbMaster;
    
    @EJB
    private MessagesFacadeLocal messagesFacade;
    
    @EJB
    private NumbersFacadeLocal numbersFacade;

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        
        ArrayList<String> messages = messagesFacade.getMessageList();
        int sum = numbersFacade.getTotal();
        
        try (PrintWriter out = response.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/style01.css\"/>");
            out.println("<title>Servlet Test</title>");            
            out.println("</head>");
            out.println("<body>");
            
            out.println("<div class=\"box-1\">");
            String added;
            if(messages.size() == 0){
                added = "сообщений нет.";
            }
            else{
                added = "";
            }
            out.println("<h1><center>Список сообщений: <p1>" + added + "</p1></center></h1>");
            for(String s : messages){
                out.println("<ul>");
                out.println("<p1><li><center>" + s + "</center></li></p1>");
                out.println("</ul>");
            }
            out.println("<div>");
            out.println("<hr><h1><center>Сумма чисел в базе: <p1>" + sum + "</p1></center></h1>");
            
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
