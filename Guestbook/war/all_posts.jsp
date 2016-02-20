<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Collections" %>

<%@ page import="guestbook.Greeting" %>

<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>

<%@ page import="com.googlecode.objectify.Objectify" %>
<%@ page import="com.googlecode.objectify.ObjectifyService" %>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
  <head>
	<link type="text/css" rel="stylesheet" href="/stylesheets/main.css" />
  </head>
  <body>
  		<h1>
  			<img src="/images/email.png" alt="email" height="128" width="128">
  		</h1>
<%
    String guestbookName = request.getParameter("guestbookName");
    if (guestbookName == null) {
        guestbookName = "default";
    }
    pageContext.setAttribute("guestbookName", guestbookName);
    UserService userService = UserServiceFactory.getUserService();
    User user = userService.getCurrentUser();

    ObjectifyService.register(Greeting.class);	
	List<Greeting> greetings = ObjectifyService.ofy().load().type(Greeting.class).list();   
	Collections.sort(greetings);
    // Run an ancestor query to ensure we see the most up-to-date
    // view of the Greetings belonging to the selected Guestbook.
        if (greetings.isEmpty()) {
        %>
        <p>Guestbook '${fn:escapeXml(guestbookName)}' has no messages.</p>
        <%
    } else {
        %>
        <p>All messages in Guestbook '${fn:escapeXml(guestbookName)}'.</p>
        <%
        for (Greeting greeting : greetings) {
        	pageContext.setAttribute("greeting_user",
                    				  greeting.getUser());
        	pageContext.setAttribute("greeting_title", greeting.getTitle());
            pageContext.setAttribute("greeting_content", greeting.getContent());
            pageContext.setAttribute("greeting_date", greeting.getDate());
            %>
            <p><b>${fn:escapeXml(greeting_user.nickname)}</b> wrote:</p>
        <blockquote id="post_title"><b>${fn:escapeXml(greeting_title)}</b></blockquote>
        <blockquote>${fn:escapeXml(greeting_content)}</blockquote>
        <p> on ${fn:escapeXml(greeting_date)}</p>
        <%
     	}
      }
%>
	<form action="/all_posts" method="post">
    	<div><input type="submit" value="Home" /></div>
    	<input type="hidden" name="guestbookName" value="${fn:escapeXml(guestbookName)}"/>
	</form>
</body>

