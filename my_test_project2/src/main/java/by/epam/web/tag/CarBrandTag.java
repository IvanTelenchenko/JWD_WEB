package by.epam.web.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import by.epam.web.entity.TypeOfBrand;
import by.epam.web.entity.User;

public class CarBrandTag extends TagSupport {

	private static final Logger log = Logger.getLogger(CarBrandTag.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 3185326621064778240L;

	private TypeOfBrand brand;

	public void setBrand(TypeOfBrand brand) {
		this.brand = brand;
	}

	@Override
	public int doStartTag() throws JspException{
		
		JspWriter write = pageContext.getOut();
		
		try {
			String brandName = brand.toString().toLowerCase();
			brandName = Character.toUpperCase(brandName.charAt(0)) + brandName.substring(1);
			write.write(brandName);
		}catch (IOException e) {
			log.error(e);
		}
		return SKIP_BODY;
	}
}