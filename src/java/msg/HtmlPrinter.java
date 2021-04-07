package msg;

import java.io.PrintWriter;

public interface HtmlPrinter {

    default public void printHtmlHeader(PrintWriter out) {
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet JpaMessagesServlet</title>");
        out.println("</head>");
        out.println("<body>");
    }
    
    default public void printHtmlEnd(PrintWriter out) {
        out.println("</body>");
        out.println("</html>");
    }
}
