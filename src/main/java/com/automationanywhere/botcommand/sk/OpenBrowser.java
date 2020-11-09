
package com.automationanywhere.botcommand.sk;




import static com.automationanywhere.commandsdk.model.AttributeType.TEXT;
import static com.automationanywhere.commandsdk.model.DataType.STRING;

import java.util.Map;

import org.openqa.selenium.chrome.ChromeDriver;

import com.automationanywhere.botcommand.exception.BotCommandException;
import com.automationanywhere.commandsdk.annotations.BotCommand;
import com.automationanywhere.commandsdk.annotations.CommandPkg;
import com.automationanywhere.commandsdk.annotations.Idx;
import com.automationanywhere.commandsdk.annotations.Pkg;
import com.automationanywhere.commandsdk.annotations.Sessions;
import com.automationanywhere.commandsdk.annotations.rules.NotEmpty;
import com.automationanywhere.commandsdk.model.AttributeType;
import com.automationanywhere.commandsdk.annotations.Execute;


/**
 * @author Stefan Karsten
 *
 */

@BotCommand
@CommandPkg(label = "Open Page", name = "openpage",
        description = "Open Page",
        node_label = "OpenPage", icon = "pkg.svg", comment = true ,  text_color ="#777777" , background_color =  "#777777")



public class OpenBrowser {
	
    @Sessions
    private Map<String, Object> sessions;
	
	@Execute
    public void action(@Idx(index = "1", type = TEXT) @Pkg(label = "Session name",  default_value_type = STRING, default_value = "Default") @NotEmpty String sessionName,
    				   @Idx(index = "2", type = AttributeType.TEXT)  @Pkg(label = "URL"  , default_value_type = STRING ) @NotEmpty String url
    				    ) throws Exception
     {
		try {
			BrowserConnection connection = (BrowserConnection) this.sessions.get(sessionName); 
			ChromeDriver driver = connection.getDriver();
			BrowserUtils utils =  new BrowserUtils();
			utils.openURL(driver, url);
		}
	    catch (Exception e) {
	   		throw new BotCommandException("OPENPAGE "+url+" : "+e.getMessage());
	   	}
	}

    public void setSessions(Map<String, Object> sessions) {
        this.sessions = sessions;
    }
    
	
}