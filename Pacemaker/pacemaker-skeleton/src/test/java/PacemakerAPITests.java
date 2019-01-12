import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controllers.PacemakerAPI;
import models.Activity;
import models.User;


public class PacemakerAPITests {

	  private PacemakerAPI pacemaker;

	  @BeforeEach
	  public void setup()
	  {
	    pacemaker = new PacemakerAPI();

	  }
	  
	  @AfterEach
	  public void tearDown()
	  {
	    pacemaker = null;
	  }
	  
	  @Test
	  public void testUsercommands() {
		  User usr= Fixtures.users.get(0);
		  User user = pacemaker.createUser(usr.firstname, usr.lastname, usr.email, usr.password);
		  assertEquals(usr, user);
		  assertEquals(usr, pacemaker.getUserByEmail(usr.email));
//assertEquals(user, pacemaker.getUser(usr.id));
		  usr= Fixtures.users.get(1);
		  pacemaker.createUser(usr.firstname, usr.lastname, usr.email, usr.password);
		  assertEquals(2, pacemaker.getUsers().size());
		  pacemaker.deleteUser(user.id);
		  pacemaker.deleteUsers();
		  assertEquals(0, pacemaker.getUsers().size());
		  
	  }
	  
	  @Test
	  public void testActivitycommands() {
		  User usr= Fixtures.users.get(0);
		  User user=pacemaker.createUser(usr.firstname, usr.lastname, usr.email, usr.password);
		  Activity activ= Fixtures.activities.get(0);
		  Activity actvity= pacemaker.createActivity(user.id, activ.type, activ.location, activ.distance);
		  assertEquals(activ, actvity);
		  assertEquals(actvity.type, pacemaker.getActivity(actvity.id).type);
		  Activity activ1= Fixtures.activities.get(1);
		  pacemaker.createActivity(user.id, activ1.type, activ1.location, activ1.distance);
		  
		  pacemaker.addLocation(actvity.id, 5.5, 6.5);
		  assertEquals(2, pacemaker.getActivities(user.id).size());
		  pacemaker.deleteActivities(user.id);
		  
	  }
	  
	  @Test
	  public void testfreindsmessagecommands() {
		  User usr= Fixtures.users.get(0);
		  User user=pacemaker.createUser(usr.firstname, usr.lastname, usr.email, usr.password);
		  User usr1= Fixtures.users.get(0);
		  User user1=pacemaker.createUser(usr1.firstname, usr1.lastname, usr1.email, usr1.password);
		  
		  
		  pacemaker.followFriend(user.id, user1.email);
		  assertEquals(1, pacemaker.getFriends(user.id).size());
		  
		  pacemaker.sendMessage(user1.email, "Hello");
		  assertEquals(1, pacemaker.getMessages(user1.id).size());
		  
		  pacemaker.unfollow(user.id, user1.email);
		  assertEquals(0, pacemaker.getFriends(user.id).size()-1);
		  System.out.println(pacemaker.getFriends(user.id).size());
		  
	  }
	  

}
