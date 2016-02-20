<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Collections" %>

<%@ page import="guestbook.Greeting" %>
<%@ page import="guestbook.Subscriber" %>

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
    if (user != null) {
      pageContext.setAttribute("user", user);
%>
<p>Hello, ${fn:escapeXml(user.nickname)}! (You can
<a href="<%= userService.createLogoutURL(request.getRequestURI()) %>">sign out</a>.)</p>
<%
    } else {
%>

<p>Hello!
<a href="<%= userService.createLoginURL(request.getRequestURI()) %>">Sign in</a>
to include your name with greetings you post.</p>
<%
    }
%>
 
<%
	ObjectifyService.register(Greeting.class);
	ObjectifyService.register(Subscriber.class);
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
        <p>Messages in Guestbook '${fn:escapeXml(guestbookName)}'.</p>
        <%
        for (int i = 0; i < java.lang.Math.min(5, greetings.size()); ++i) {
        	Greeting greeting = greetings.get(i);
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

    <form action="/ofysign" method="post">
      <div><textarea name="title" rows="1" cols="40"></textarea></div>
      <div><textarea name="content" rows="3" cols="60"></textarea></div>
      <div><input type="submit" value="Post Greeting" /></div>
      <input type="hidden" name="guestbookName" value="${fn:escapeXml(guestbookName)}"/>
    </form>
    
    <form action="/all_posts" method="get">
      <div><input type="submit" value="Show all greetings" /></div>
      <input type="hidden" name="guestbookName" value="${fn:escapeXml(guestbookName)}"/>
    </form>
    
    <form action="/subscriber" method="post">
        <div><input type="submit" value="Subscribe" /></div>
        <input type="hidden" name="guestbookName" value="${fn:escapeXml(guestbookName)}"/>
    </form>    
	<form action="/subscriber" method="get">
    	<div><input type="submit" value="Unsubscribe" /></div>
    	<input type="hidden" name="guestbookName" value="${fn:escapeXml(guestbookName)}"/>
    </form>
  </body>
</html>