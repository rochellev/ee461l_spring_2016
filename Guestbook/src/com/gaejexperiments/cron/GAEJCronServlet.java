package com.gaejexperiments.cron;

import java.util.Properties;
import java.util.List;
import java.util.Date;

import java.io.IOException;

import java.util.logging.Logger;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.googlecode.objectify.ObjectifyService;
import static com.googlecode.objectify.ObjectifyService.ofy;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import guestbook.Greeting;
import guestbook.Subscriber;

@SuppressWarnings("serial")
public class GAEJCronServlet extends HttpServlet {
	static long dayInMilli = 24 * 60 * 60 * 1000;
	
	private static final Logger _logger = Logger.getLogger(GAEJCronServlet.class.getName());
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		try {
			_logger.info("Cron Job has been executed");
			
	        Properties props = new Properties();
	        Session session = Session.getDefaultInstance(props, null);
	        
	        String msgBody = "New greetings from guestbook : \r";
	        
	        List<Greeting> greetings = ofy().load().type(Greeting.class).list();
	        for(Greeting greeting : greetings) {
	        	Date gDate = greeting.getDate();
	        	Date cutoff = new Date(gDate.getTime() - GAEJCronServlet.dayInMilli);
	        	if(gDate.after(cutoff)) {
	        		msgBody = msgBody + greeting.getUser() + "wrote: \r"
	        						  + greeting.getTitle() + "\r"
	        						  + greeting.getContent() + "\r"
	        						  + greeting.getDate() + "\r\r";
	        	}
	        }
	        
	        List<Subscriber> subs = ofy().load().type(Subscriber.class).list();
	        
	        for(Subscriber sub : subs)
		        try {
		            Message msg = new MimeMessage(session);
		            msg.setFrom(new InternetAddress("xxx@triple-env-120302.appspotmail.com"));
		            msg.addRecipient(Message.RecipientType.TO,
		                             new InternetAddress(sub.getUser().getEmail()));
		            msg.setSubject("it'll be our little secret sssshhhhh");
		            msg.setText(msgBody);
		            Transport.send(msg);
	
		        } catch (AddressException e) {
		            // ...
		        } catch (MessagingException e) {
		            // ...
	        }
		}
		catch (Exception ex) {
		//Log any exceptions in your Cron Job
		}
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
		doGet(req, resp);
	}
}
