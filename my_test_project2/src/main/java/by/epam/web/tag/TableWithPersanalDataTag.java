package by.epam.web.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import by.epam.web.entity.User;

public class TableWithPersanalDataTag extends TagSupport {

	private static final Logger log = Logger.getLogger(TableWithPersanalDataTag.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 3185326621064778240L;

	private User user;

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int doStartTag() throws JspException{
		
		JspWriter write = pageContext.getOut();
		
		try {
			String table = " <table class=\"table\"> "
			+" <tr> "
				+" <td><p>${firstname}</p></td> "
				+ " <td> " + user.getFirstName() + "</td> "
			+ " </tr> "
			+ "<tr> "
			+ " <td><p>${lastname}</p></td> "
				+ " <td> " +user.getLastName() + " </td> "
			+ "</tr>"
			+ "<tr>"
				+ " <td><p>${phone}</p></td> "
				 + "<td>" + user.getPhoneNumber() + "</td> "
			+ "</tr> "
			+ "<tr> "
				+ " <td><p>${email}</p></td> "
				+ " <td>" + user.getEmail() + "</td> "
			+ "</tr> "
		+ "</table>";
			
			write.write(table);
		}catch (IOException e) {
			log.error(e);
		}
		return SKIP_BODY;
	}
}