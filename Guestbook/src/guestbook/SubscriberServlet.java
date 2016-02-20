package guestbook;
//http://1-dot-triple-env-120302.appspot.com/

import com.googlecode.objectify.ObjectifyService;
import static com.googlecode.objectify.ObjectifyService.ofy;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import java.io.IOException;

import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SubscriberServlet extends HttpServlet {
	static {
		ObjectifyService.register(Subscriber.class);
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String guestbookName = req.getParameter("guestbookName");
		
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		
		Subscriber checkSub = new Subscriber(user);
		
		List<Subscriber> subs = ObjectifyService.ofy().load().type(Subscriber.class).list();
		Subscriber sub = null;
		for(int i = 0; i < subs.size(); ++i) {
			if(checkSub.equals(subs.get(i))) {
				sub = subs.get(i);
				break;
			}
		}
		if(sub != null) {
			ofy().delete().entity(sub).now();
		}
		resp.sendRedirect("/ofyguestbook.jsp?guestbookName=" + guestbookName);
	}
	
	//add a user to the subs list
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String guestbookName = req.getParameter("guestbookName");
		
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		
		List<Subscriber> subs = ObjectifyService.ofy().load().type(Subscriber.class).list();
		Subscriber newSub = new Subscriber(user);
		
		boolean isSub = false;
		for(int i = 0; i < subs.size(); ++i) {
			if(newSub.equals(subs.get(i))) {
				isSub = true;
				break;
			}
		}
		if(!isSub){
			ofy().save().entity(newSub).now();
		}
		resp.sendRedirect("/ofyguestbook.jsp?guestbookName=" + guestbookName);
	}
}