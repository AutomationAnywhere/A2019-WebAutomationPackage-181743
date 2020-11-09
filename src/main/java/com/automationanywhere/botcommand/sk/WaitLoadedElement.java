
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
import com.automationanywhere.commandsdk.model.AttributeType;
import com.automationanywhere.commandsdk.model.DataType;
import com.automationanywhere.commandsdk.annotations.Execute;


/**
 * @author Stefan Karsten
 *
 */

@BotCommand
@CommandPkg(label = "Wait Element Loaded", name = "elementloaded",
        description = "Wait until Element is loaded",
        node_label = "Wait Element Loaded", icon = "pkg.svg", comment = true ,  text_color ="#777777" , background_color = "#777777",
		return_type=DataType.BOOLEAN, return_label="Status", return_required=false	)



public class WaitLoadedElement {
	
    @Sessions
    private Map<String, Object> sessions;
	
	@Execute
    public BooleanValue action(@Idx(index = "1", type = TEXT) @Pkg(label = "Session name",  default_value_type = STRING, default_value = "Default") @NotEmpty String sessionName,
    		 				   @Idx(index = "2", type = AttributeType.TEXTAREA)  @Pkg(label = "Base JS Script" , default_value_type = STRING ) @NotEmpty String jspath,
    						   @Idx(index = "3", type = AttributeType.NUMBER) @Pkg(label = "Timeout (Seconds)",  default_value_type = DataType.NUMBER, default_value = "10") @NotEmpty Number timeout,
     						   @Idx(index = "4", type = AttributeType.TEXT)  @Pkg(label = "Wait for Attribute Value" , default_value_type = STRING , default_value = "className" ) @NotEmpty String attribute
    				    ) throws Exception
     {
		Boolean isLoaded = false;
		try {
			BrowserConnection connection = (BrowserConnection) this.sessions.get(sessionName); 
			ChromeDriver driver = connection.getDriver();
			BrowserUtils utils =  new BrowserUtils();
			String library = connection.getLibrary();
			isLoaded = utils.waitUntilElementLoaded(driver, jspath,library, timeout.intValue(),attribute);
		}
	    catch (Exception e) {
	   		throw new BotCommandException("WAITELEMENT "+jspath+" : "+e.getMessage());
	   	}
		return new BooleanValue(isLoaded);
	    

	}

    public void setSessions(Map<String, Object> sessions) {
        this.sessions = sessions;
    }
    
	
}