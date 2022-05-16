package com.automationanywhere.botcommand.sk;

import com.automationanywhere.bot.service.GlobalSessionContext;
import com.automationanywhere.botcommand.BotCommand;
import com.automationanywhere.botcommand.data.Value;
import com.automationanywhere.botcommand.exception.BotCommandException;
import com.automationanywhere.commandsdk.i18n.Messages;
import com.automationanywhere.commandsdk.i18n.MessagesFactory;
import java.lang.ClassCastException;
import java.lang.Deprecated;
import java.lang.Number;
import java.lang.Object;
import java.lang.String;
import java.lang.Throwable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class DoSelectCommand implements BotCommand {
  private static final Logger logger = LogManager.getLogger(DoSelectCommand.class);

  private static final Messages MESSAGES_GENERIC = MessagesFactory.getMessages("com.automationanywhere.commandsdk.generic.messages");

  @Deprecated
  public Optional<Value> execute(Map<String, Value> parameters, Map<String, Object> sessionMap) {
    return execute(null, parameters, sessionMap);
  }

  public Optional<Value> execute(GlobalSessionContext globalSessionContext,
      Map<String, Value> parameters, Map<String, Object> sessionMap) {
    logger.traceEntry(() -> parameters != null ? parameters.entrySet().stream().filter(en -> !Arrays.asList( new String[] {}).contains(en.getKey()) && en.getValue() != null).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)).toString() : null, ()-> sessionMap != null ?sessionMap.toString() : null);
    DoSelect command = new DoSelect();
    HashMap<String, Object> convertedParameters = new HashMap<String, Object>();
    if(parameters.containsKey("sessionName") && parameters.get("sessionName") != null && parameters.get("sessionName").get() != null) {
      convertedParameters.put("sessionName", parameters.get("sessionName").get());
      if(convertedParameters.get("sessionName") !=null && !(convertedParameters.get("sessionName") instanceof String)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","sessionName", "String", parameters.get("sessionName").get().getClass().getSimpleName()));
      }
    }
    if(convertedParameters.get("sessionName") == null) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.validation.notEmpty","sessionName"));
    }

    if(parameters.containsKey("jspath") && parameters.get("jspath") != null && parameters.get("jspath").get() != null) {
      convertedParameters.put("jspath", parameters.get("jspath").get());
      if(convertedParameters.get("jspath") !=null && !(convertedParameters.get("jspath") instanceof String)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","jspath", "String", parameters.get("jspath").get().getClass().getSimpleName()));
      }
    }
    if(convertedParameters.get("jspath") == null) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.validation.notEmpty","jspath"));
    }

    if(parameters.containsKey("newvalue") && parameters.get("newvalue") != null && parameters.get("newvalue").get() != null) {
      convertedParameters.put("newvalue", parameters.get("newvalue").get());
      if(convertedParameters.get("newvalue") !=null && !(convertedParameters.get("newvalue") instanceof String)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","newvalue", "String", parameters.get("newvalue").get().getClass().getSimpleName()));
      }
    }
    if(convertedParameters.get("newvalue") == null) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.validation.notEmpty","newvalue"));
    }

    if(parameters.containsKey("timeout") && parameters.get("timeout") != null && parameters.get("timeout").get() != null) {
      convertedParameters.put("timeout", parameters.get("timeout").get());
      if(convertedParameters.get("timeout") !=null && !(convertedParameters.get("timeout") instanceof Number)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","timeout", "Number", parameters.get("timeout").get().getClass().getSimpleName()));
      }
    }
    if(convertedParameters.get("timeout") == null) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.validation.notEmpty","timeout"));
    }

    if(parameters.containsKey("attribute") && parameters.get("attribute") != null && parameters.get("attribute").get() != null) {
      convertedParameters.put("attribute", parameters.get("attribute").get());
      if(convertedParameters.get("attribute") !=null && !(convertedParameters.get("attribute") instanceof String)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","attribute", "String", parameters.get("attribute").get().getClass().getSimpleName()));
      }
    }
    if(convertedParameters.get("attribute") == null) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.validation.notEmpty","attribute"));
    }

    command.setSessions(sessionMap);
    try {
      command.action((String)convertedParameters.get("sessionName"),(String)convertedParameters.get("jspath"),(String)convertedParameters.get("newvalue"),(Number)convertedParameters.get("timeout"),(String)convertedParameters.get("attribute"));Optional<Value> result = Optional.empty();
      return logger.traceExit(result);
    }
    catch (ClassCastException e) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.IllegalParameters","action"));
    }
    catch (BotCommandException e) {
      logger.fatal(e.getMessage(),e);
      throw e;
    }
    catch (Throwable e) {
      logger.fatal(e.getMessage(),e);
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.NotBotCommandException",e.getMessage()),e);
    }
  }

  public Map<String, Value> executeAndReturnMany(GlobalSessionContext globalSessionContext,
      Map<String, Value> parameters, Map<String, Object> sessionMap) {
    return null;
  }
}
