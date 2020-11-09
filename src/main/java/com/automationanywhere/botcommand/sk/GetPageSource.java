
package com.automationanywhere.botcommand.sk;




import static com.automationanywhere.commandsdk.model.AttributeType.TEXT;
import static com.automationanywhere.commandsdk.model.DataType.STRING;

import java.util.Map;

import org.openqa.selenium.chrome.ChromeDriver;

import com.automationanywhere.botcommand.data.impl.StringValue;
import com.automationanywhere.botcommand.exception.BotCommandException;
import com.automationanywhere.commandsdk.annotations.BotCommand;
import com.automationanywhere.commandsdk.annotations.CommandPkg;
import com.automationanywhere.commandsdk.annotations.Idx;
import com.automationanywhere.commandsdk.annotations.Pkg;
import com.automationanywhere.commandsdk.annotations.Sessions;
import com.automationanywhere.commandsdk.annotations.rules.NotEmpty;
import com.automationanywhere.commandsdk.annotations.Execute;


/**
 * @author Stefan Karsten
 *
 */

@BotCommand
@CommandPkg(label = "Get Page Source", name = "pagesource",
        description = "Get Page Source of a Page",
        node_label = "Get Page Source", icon = "pkg.svg", comment = true ,  text_color = "#777777" , background_color =  "#777777" ,
        return_type=STRING, return_label="HTML", return_required=true)



public class GetPageSource {
	
    @Sessions
    private Map<String, Object> sessions;
	
	@Execute
    public StringValue  action(@Idx(index = "1", type = TEXT) @Pkg(label = "Session name",  default_value_type = STRING, default_value = "Default") @NotEmpty String sessionName
    						   ) throws Exception
     {

		String html = "";
		try {
			BrowserConnection connection = (BrowserConnection) this.sessions.get(sessionName); 
			ChromeDriver driver = connection.getDriver();
			BrowserUtils utils =  new BrowserUtils();
			html = utils.getPageContent(driver);
    	}
    	catch (Exception e) {
    		throw new BotCommandException("GETPAGESORUCE : "+e.getMessage());
    	}
		return new StringValue(html);

	}

    public void setSessions(Map<String, Object> sessions) {
        this.sessions = sessions;
    }
    
	
}