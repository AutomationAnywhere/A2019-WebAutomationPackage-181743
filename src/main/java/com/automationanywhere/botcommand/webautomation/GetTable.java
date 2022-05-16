
package com.automationanywhere.botcommand.webautomation;




import static com.automationanywhere.commandsdk.model.AttributeType.TEXT;
import static com.automationanywhere.commandsdk.model.DataType.STRING;
import static com.automationanywhere.commandsdk.model.DataType.TABLE;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;

import com.automationanywhere.botcommand.data.Value;
import com.automationanywhere.botcommand.data.impl.StringValue;
import com.automationanywhere.botcommand.data.impl.TableValue;
import com.automationanywhere.botcommand.data.model.Schema;
import com.automationanywhere.botcommand.data.model.table.Row;
import com.automationanywhere.botcommand.data.model.table.Table;
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
@CommandPkg(label = "Get Table Content", name = "gettablecontent",
        description = "Get Table Content",
        node_label = "of {{search}}", icon = "pkg.svg", comment = true , group_label="Get",text_color = "#2F4F4F" , background_color =  "#2F4F4F",
		return_type=TABLE, return_label="Value", return_required=true)


public class GetTable {
	
    @Sessions
    private Map<String, Object> sessions;
	
	@Execute
    public TableValue action(@Idx(index = "1", type = TEXT) @Pkg(label = "Session name",  default_value_type = STRING, default_value = "Default") @NotEmpty String sessionName,
    						  @Idx(index = "2", type = AttributeType.TEXTAREA)  @Pkg(label = "Search" , description = "Should match the type", default_value_type = STRING ) @NotEmpty String search,
    	    		    	  @Idx(index = "3", type = AttributeType.SELECT , options = {
    	      						    @Idx.Option(index = "3.1", pkg = @Pkg(label = "Search by Element XPath", value = BrowserUtils.XPATH)), 
    	      						    @Idx.Option(index = "3.2", pkg = @Pkg(label = "Search by Element Id", value = BrowserUtils.ID)), 
    	      						    @Idx.Option(index = "3.3", pkg = @Pkg(label = "Search by Tag name", value = BrowserUtils.TAG)), 
    	      						    @Idx.Option(index = "3.4", pkg = @Pkg(label = "Search by CSS Selector", value = BrowserUtils.CSS)),
    	      						    @Idx.Option(index = "3.5", pkg = @Pkg(label = "JavaScript", value = BrowserUtils.JS))}) 
    	       						@Pkg(label = "Search Type" , default_value = BrowserUtils.CSS , default_value_type= STRING ) @NotEmpty String type,
    						  @Idx(index = "4", type = AttributeType.NUMBER)  @Pkg(label = "Timeout (Seconds)" , description = "No wait if 0" ,default_value_type = DataType.NUMBER,  default_value ="0" ) @NotEmpty Number timeout,
    						  @Idx(index = "5", type = AttributeType.TEXT)  @Pkg(label = "Wait for Attribute Value" , default_value_type = STRING , default_value = "className" ) @NotEmpty String attribute
    				   ) throws Exception
     {
		List<Row> rows = null;
		TableValue result = new TableValue();
		///	try {
			BrowserConnection connection = (BrowserConnection) this.sessions.get(sessionName); 
			WebDriver driver = connection.getDriver();
			BrowserUtils utils =  new BrowserUtils();
			String library = connection.getLibrary();
			rows = utils.getTable(driver,search,type,library,timeout.intValue(),attribute);
			TableValue tableValue= new TableValue();
			Table table = new Table();
			if (utils.hasTableHeader) {
				List<Schema> schemas = new ArrayList<Schema>();
				Row headers = rows.get(0);
				for (Iterator iterator = headers.getValues().iterator(); iterator.hasNext();) {
					StringValue header = (StringValue) iterator.next();
					Schema schema =  new Schema();
					schema.setName(header.get()); 
					schemas.add(schema);
				}
				table.setSchema(schemas);
				rows.remove(0);
			}
		

			table.setRows(rows);
			tableValue.set(table);
			result = tableValue;
			//	}
    	//catch (Exception e) {
		//		throw new BotCommandException("GETTABLE "+search+" "+type+" : "+e.getMessage());
		//}
		return result;
	    

	}

    public void setSessions(Map<String, Object> sessions) {
        this.sessions = sessions;
    }
    
	
}