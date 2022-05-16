
package com.automationanywhere.botcommand.webautomation;


import static com.automationanywhere.commandsdk.model.AttributeType.TEXT;
import static com.automationanywhere.commandsdk.model.DataType.STRING;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;

import com.automationanywhere.botcommand.data.Value;
import com.automationanywhere.botcommand.data.impl.ListValue;
import com.automationanywhere.botcommand.data.impl.StringValue;
import com.automationanywhere.botcommand.exception.BotCommandException;
import com.automationanywhere.botcommand.utils.BrowserConnection;
import com.automationanywhere.botcommand.utils.BrowserUtils;
import com.automationanywhere.commandsdk.annotations.BotCommand;
import com.automationanywhere.commandsdk.annotations.CommandPkg;
import com.automationanywhere.commandsdk.annotations.Idx;
import com.automationanywhere.commandsdk.annotations.Pkg;
import com.automationanywhere.commandsdk.annotations.Sessions;
import com.automationanywhere.commandsdk.annotations.rules.NotEmpty;
import com.automationanywhere.commandsdk.model.AttributeType;
import com.automationanywhere.commandsdk.model.DataType;
import com.automationanywhere.commandsdk.annotations.Execute;


/**
 * @author Stefan Karsten
 *
 */

@BotCommand
@CommandPkg(label = "Get Windows", name = "getwindows",
        description = "Get Windows",
        node_label = "of session {{sessionName}}", icon = "pkg.svg", group_label="Window", comment = true ,  text_color = "#2F4F4F" , background_color =  "#2F4F4F",
        return_type=DataType.LIST, return_sub_type=DataType.STRING , return_label="Window Handles", return_required=true)

public class GetWindows {
	
    @Sessions
    private Map<String, Object> sessions;
	
	@Execute
    public ListValue action(@Idx(index = "1", type = TEXT) @Pkg(label = "Session name",  default_value_type = STRING, default_value = "Default") @NotEmpty String sessionName
    				   ) throws Exception
     {
		
		try {
			BrowserConnection connection = (BrowserConnection) this.sessions.get(sessionName); 
			WebDriver driver = connection.getDriver();
			BrowserUtils utils =  new BrowserUtils();
			String library = connection.getLibrary();
			List<String> handles = utils.getWindowHandles(driver);
			List<Value> handleList = new ArrayList<Value>();
			for (Iterator iterator = handles.iterator(); iterator.hasNext();) {
				String value = (String) iterator.next();
				handleList.add(new StringValue(value));
			}
			ListValue returnList = new ListValue();
			returnList.set(handleList);
			return returnList;
		}
		catch (Exception e) {
			  		throw new BotCommandException("GETWINDOWS : "+e.getMessage());
		}
	}

    public void setSessions(Map<String, Object> sessions) {
        this.sessions = sessions;
    }
    
	
}