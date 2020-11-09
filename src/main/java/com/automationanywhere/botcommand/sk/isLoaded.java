
package com.automationanywhere.botcommand.sk;




import static com.automationanywhere.commandsdk.model.AttributeType.TEXT;
import static com.automationanywhere.commandsdk.model.DataType.STRING;

import java.util.Map;

import org.openqa.selenium.chrome.ChromeDriver;

import com.automationanywhere.botcommand.data.impl.BooleanValue;
import com.automationanywhere.botcommand.exception.BotCommandException;
import com.automationanywhere.commandsdk.annotations.BotCommand;
import com.automationanywhere.commandsdk.annotations.CommandPkg;
import com.automationanywhere.commandsdk.annotations.Idx;
import com.automationanywhere.commandsdk.annotations.Pkg;
import com.automationanywhere.commandsdk.annotations.Sessions;
import com.automationanywhere.commandsdk.annotations.rules.NotEmpty;
import com.automationanywhere.commandsdk.model.DataType;
import com.automationanywhere.commandsdk.annotations.Execute;


/**
 * @author Stefan Karsten
 *
 */

@BotCommand
@CommandPkg(label = "Is Page Loaded", name = "isloaded",
        description = "Is Page Loaded",
        node_label = "Is Page Loaded", icon = "pkg.svg", comment = true ,  text_color ="#777777" , background_color = "#777777",
		return_type=DataType.BOOLEAN, return_label="Status", return_required=true)



public class isLoaded {
	
    @Sessions
    private Map<String, Object> sessions;
	
	@Execute
    public BooleanValue action(@Idx(index = "1", type = TEXT) @Pkg(label = "Session name",  default_value_type = STRING, default_value = "Default") @NotEmpty String sessionName
    				    ) throws Exception
     {
		Boolean isLoaded = false;
		try {
			BrowserConnection connection = (BrowserConnection) this.sessions.get(sessionName); 
			ChromeDriver driver = connection.getDriver();
			BrowserUtils utils =  new BrowserUtils();
			isLoaded = utils.isPageLoaded(driver);
		}
	    catch (Exception e) {
	   		throw new BotCommandException("PAGEISLOADED : "+e.getMessage());
	   	}
		return new BooleanValue(isLoaded);
	    

	}

    public void setSessions(Map<String, Object> sessions) {
        this.sessions = sessions;
    }
    
	
}