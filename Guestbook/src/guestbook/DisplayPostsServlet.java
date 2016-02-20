package guestbook;

import java.io.IOException;
import javax.servlet.http.*;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class DisplayPostsServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
		String guestbookName = req.getParameter("guestbookName");
		resp.sendRedirect("/all_posts.jsp?guestbookName=" + guestbookName);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
		String guestbookName = req.getParameter("guestbookName");
		resp.sendRedirect("/ofyguestbook.jsp?guestbookName=" + guestbookName);
	}
}