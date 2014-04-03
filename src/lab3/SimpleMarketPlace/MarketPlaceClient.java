package lab3.SimpleMarketPlace;

import javax.jms.*;
import javax.jms.Queue;

import java.util.*;

import javax.naming.*;

public class MarketPlaceClient {
	private Connection connection;
	private Session session;
	private Queue loginQueue;
	private Topic marketTopic;
	private MessageConsumer consumer;
	private Queue replyQueue;
	private Topic replyTopic;
	
	public boolean signUp(String firstName, String lastName, String emailID, String password, String userID) throws JMSException
	{
		String signUpInfo = firstName + ":" + lastName + ":" + emailID + ":" + password + ":" + userID;
		MessageProducer MP = session.createProducer(loginQueue);
		TextMessage TM = session.createTextMessage("signUp:"+ signUpInfo);
		
		replyQueue = session.createTemporaryQueue(); 
		consumer = session.createConsumer( replyQueue );
		
		TM.setJMSReplyTo(replyQueue);
		MP.send(TM);
		TextMessage Reply = (TextMessage)consumer.receive();
		
		String st = Reply.getText();
		
		if(st.equals("true")){
			return true;
		}
		else{
			return false;
		}
		
	}
	
	public String signIn(String emailID, String password) throws JMSException
	{
		String signInInfo = emailID + ":" + password;
		MessageProducer MP = session.createProducer(loginQueue);
		TextMessage TM = session.createTextMessage("signIn:"+ signInInfo);
		
		replyQueue = session.createTemporaryQueue(); 
		consumer = session.createConsumer( replyQueue );
		
		TM.setJMSReplyTo(replyQueue);
		MP.send(TM);
		TextMessage Reply = (TextMessage)consumer.receive();
		
		String ret = Reply.getText();
		
		return ret;
	}
	
	public boolean storeAdvertisement(String itemName, String itemDesc, float price, int quantity, String userID) throws JMSException
	{
		String adInfo = itemName + ":" + itemDesc + ":" + price +":" + quantity + ":"+userID;
		System.out.println(adInfo);
		MessageProducer MP = session.createProducer(loginQueue);
		TextMessage TM = session.createTextMessage("storeAdvertisement:"+ adInfo);
		
		replyQueue = session.createTemporaryQueue(); 
		consumer = session.createConsumer( replyQueue );
		
		TM.setJMSReplyTo(replyQueue);
		MP.send(TM);
		TextMessage Reply = (TextMessage)consumer.receive();
		
		if(Reply.getText().equals("true")){
			return true;
		}
		else{
			return false;
		}
		
	}
	
	public String displayAdvertisement(String userID) throws JMSException
	{
		String adInfo = userID;
		System.out.println(adInfo);
		MessageProducer MP = session.createProducer(marketTopic);
		TextMessage TM = session.createTextMessage("displayAdvertisement:"+ adInfo);
		
		replyTopic = session.createTemporaryTopic(); 
		consumer = session.createConsumer( replyTopic );
		
		TM.setJMSReplyTo(replyTopic);
		MP.send(TM);
		TextMessage Reply = (TextMessage)consumer.receive();
		
		return Reply.getText();
		
	}
	
	public boolean signOut(String userID) throws JMSException
	{
		String adInfo = userID;
		System.out.println(adInfo);
		MessageProducer MP = session.createProducer(loginQueue);
		TextMessage TM = session.createTextMessage("signOut:"+ adInfo);
		
		replyQueue = session.createTemporaryQueue(); 
		consumer = session.createConsumer( replyQueue );
		
		TM.setJMSReplyTo(replyQueue);
		MP.send(TM);
		TextMessage Reply = (TextMessage)consumer.receive();
		
		if(Reply.getText().equals("true")){
			return true;
		}
		else{
			return false;
		}
		
	}
	
	
	public MarketPlaceClient()
	{
		try
		{
		    Properties properties = new Properties();
		    properties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
		    properties.put(Context.URL_PKG_PREFIXES, "org.jnp.interfaces");
		    properties.put(Context.PROVIDER_URL, "localhost");
			
			InitialContext jndi = new InitialContext(properties);
			ConnectionFactory conFactory = (ConnectionFactory)jndi.lookup("XAConnectionFactory");
			connection = conFactory.createConnection();
			
			session = connection.createSession( false, Session.AUTO_ACKNOWLEDGE );
			loginQueue = (Queue)jndi.lookup("LoginQueue");
			marketTopic = (Topic)jndi.lookup("MarketTopic");
			
			connection.start();

		}
		catch(NamingException NE)
		{
			System.out.println("Naming Exception: "+NE);
		}
		catch(JMSException JMSE)
		{
			System.out.println("JMS Exception: "+JMSE);
		}
	}
	

}

