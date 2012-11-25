package org.salespointframework.web.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.salespointframework.web.WebLoginLogoutManager;

/**
 * 
 * @author Lars Kreisz
 * @author Uwe Schmidt
 * @author Paul Henke
 */
@SuppressWarnings("serial")
public class LoggedInTag extends BodyTagSupport
{
	private boolean test = true;

	/**
	 * The test condition that determines whether or not the body content should be processed.
	 * @param test a boolean condition
	 */
	public void setTest(boolean test)
	{
		this.test = test;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int doStartTag() throws JspException
	{
		/*
		UserManager<?> usermanager = Shop.INSTANCE.getUserManager();
		
		if(usermanager == null) {
			throw new NullPointerException("Shop.INSTANCE.getUserManager() returned null");
		}
		
		User user = null;
		
		if(usermanager instanceof TransientUserManager) {
			user = ((TransientUserManager)usermanager).getUserByToken(TransientUser.class, pageContext.getSession());
			//System.out.println("has cap transient");
		}
		
		if(usermanager instanceof PersistentUserManager) {
			user = ((PersistentUserManager)usermanager).getUserByToken(PersistentUser.class, pageContext.getSession());
		//	System.out.println("has cap persistent");
		}
		
		if(!(usermanager instanceof TransientUserManager || usermanager instanceof PersistentUserManager)) {
			user = ((UserManager<User>)usermanager).getUserByToken(User.class, pageContext.getSession());
		//	System.out.println("has cap unknown um");
		}
		
		*/
		
		boolean loggedIn = WebLoginLogoutManager.INSTANCE.loggedIn(pageContext.getSession());

		if(test) {
			return loggedIn ? EVAL_BODY_INCLUDE : SKIP_BODY;
		} else {
			return loggedIn ? SKIP_BODY : EVAL_BODY_INCLUDE;
		}
		
		/*
		if (test)
		{
			return user == null ? SKIP_BODY : EVAL_BODY_INCLUDE;
		} else
		{
			return user == null ? EVAL_BODY_INCLUDE : SKIP_BODY;
		}
		*/
	}

}
