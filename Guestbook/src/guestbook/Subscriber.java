package guestbook;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.Date;
import java.util.List;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;;

@Entity
public class Subscriber implements Comparable<Greeting> {
	@Id
	Long id;
	@Index
	User user;
	
	private Subscriber() {}
	
	public Subscriber(User user) {
		this.user = user;
	}
	
	public User getUser() {
		return user;
	}

	@Override
	public int compareTo(Greeting o) {
		if(o.user.equals(this.user)) {
			return 0;
		}
		else {
			return 1;
		}
	}
}
