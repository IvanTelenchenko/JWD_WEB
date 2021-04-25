package by.epam.web.controller.command;

import java.util.HashMap;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.epam.web.controller.command.impl.AcceptOrder;
import by.epam.web.controller.command.impl.AddNewCar;
import by.epam.web.controller.command.impl.Authorization;
import by.epam.web.controller.command.impl.CancelOrder;
import by.epam.web.controller.command.impl.ChangeLocale;
import by.epam.web.controller.command.impl.ChangePD;
import by.epam.web.controller.command.impl.ChangePassword;
import by.epam.web.controller.command.impl.CompleteOrder;
import by.epam.web.controller.command.impl.CreateOrder;
import by.epam.web.controller.command.impl.DeleteCar;
import by.epam.web.controller.command.impl.EditCar;
import by.epam.web.controller.command.impl.GoToAccountPage;
import by.epam.web.controller.command.impl.GoToAddNewCarPage;
import by.epam.web.controller.command.impl.GoToCarPage;
import by.epam.web.controller.command.impl.GoToCarsBasePage;
import by.epam.web.controller.command.impl.GoToChangePDPage;
import by.epam.web.controller.command.impl.GoToChangePasswordPage;
import by.epam.web.controller.command.impl.GoToEditCarPage;
import by.epam.web.controller.command.impl.GoToError404;
import by.epam.web.controller.command.impl.GoToError500;
import by.epam.web.controller.command.impl.GoToMainPage;
import by.epam.web.controller.command.impl.GoToOrderHistoryPage;
import by.epam.web.controller.command.impl.GoToPersonalDataPage;
import by.epam.web.controller.command.impl.GoToRegistrationPage;
import by.epam.web.controller.command.impl.GoToSignInPage;
import by.epam.web.controller.command.impl.GoToSuccessChange;
import by.epam.web.controller.command.impl.GoToVerificationPage;
import by.epam.web.controller.command.impl.Logout;
import by.epam.web.controller.command.impl.MainPageWithDateUser;
import by.epam.web.controller.command.impl.MainPageWithFilter;
import by.epam.web.controller.command.impl.Registration;

/**
 * This class represents factory of commands. Based on the factory method pattern
 * */

public class FactoryCommand {
	
	private static final Logger log = Logger.getLogger(FactoryCommand.class);
	
	private Map<CommandName,Command> commands = new HashMap<>();
	
	public FactoryCommand() {
		commands.put(CommandName.AUTHORIZATION, new Authorization());
		commands.put(CommandName.GOTOSIGNIN, new GoToSignInPage());
		commands.put(CommandName.MAINPAGE, new GoToMainPage());
		commands.put(CommandName.GOTOREGISTRATION, new GoToRegistrationPage());
		commands.put(CommandName.REGISTRATION, new Registration());
		commands.put(CommandName.LOGOUT, new Logout());
		commands.put(CommandName.LOCAL, new ChangeLocale());
		commands.put(CommandName.GOTOACCOUNT, new GoToAccountPage());
		commands.put(CommandName.GOTOPERSONALDATA, new GoToPersonalDataPage());
		commands.put(CommandName.GOTOORDERHISTORY, new GoToOrderHistoryPage());
		commands.put(CommandName.GOTOCAR, new GoToCarPage());
		commands.put(CommandName.MAINPAGEWITHFILTER, new MainPageWithFilter());
		commands.put(CommandName.GOTOCHANGEPD, new GoToChangePDPage());
		commands.put(CommandName.GOTOCHANGEPASSWORD, new GoToChangePasswordPage());
		commands.put(CommandName.CHANGEPASSWORD, new ChangePassword());
		commands.put(CommandName.SUCCESSCHANGE, new GoToSuccessChange());
		commands.put(CommandName.CHANGEPD, new ChangePD());
		commands.put(CommandName.MAINPAGEWITHDATEUSER, new MainPageWithDateUser());
		commands.put(CommandName.GOTOVERIFICATIONPAGE, new GoToVerificationPage());
		commands.put(CommandName.CREATEORDER, new CreateOrder());
		commands.put(CommandName.CANCELORDER, new CancelOrder());
		commands.put(CommandName.GOTOCARSBASE, new GoToCarsBasePage());
		commands.put(CommandName.DELETECAR, new DeleteCar());
		commands.put(CommandName.GOTOADDNEWCAR, new GoToAddNewCarPage());
		commands.put(CommandName.ADDNEWCAR, new AddNewCar());
		commands.put(CommandName.ACCEPTORDER, new AcceptOrder());
		commands.put(CommandName.EDITCAR, new EditCar());
		commands.put(CommandName.GOTOEDITCAR, new GoToEditCarPage());
		commands.put(CommandName.COMPLETEORDER, new CompleteOrder());
		commands.put(CommandName.ERROR404, new GoToError404());
		commands.put(CommandName.ERROR500, new GoToError500());
	}
	
	/**
	 * This method returns {@link Command}
	 * @param name Command name
	 * @return returns {@link Command} which match with command name
	 * 
	 */

	public Command getCommand(String name) {

		CommandName command = CommandName.valueOf(name.toUpperCase());
		
		log.info("The command " + name +" has been gotten");
		
		return commands.get(command);		
	}

}