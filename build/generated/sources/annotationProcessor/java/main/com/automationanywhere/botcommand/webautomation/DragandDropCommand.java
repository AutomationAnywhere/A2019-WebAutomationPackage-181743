package com.automationanywhere.botcommand.webautomation;

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

public final class DragandDropCommand implements BotCommand {
  private static final Logger logger = LogManager.getLogger(DragandDropCommand.class);

  private static final Messages MESSAGES_GENERIC = MessagesFactory.getMessages("com.automationanywhere.commandsdk.generic.messages");

  @Deprecated
  public Optional<Value> execute(Map<String, Value> parameters, Map<String, Object> sessionMap) {
    return execute(null, parameters, sessionMap);
  }

  public Optional<Value> execute(GlobalSessionContext globalSessionContext,
      Map<String, Value> parameters, Map<String, Object> sessionMap) {
    logger.traceEntry(() -> parameters != null ? parameters.entrySet().stream().filter(en -> !Arrays.asList( new String[] {}).contains(en.getKey()) && en.getValue() != null).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)).toString() : null, ()-> sessionMap != null ?sessionMap.toString() : null);
    DragandDrop command = new DragandDrop();
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

    if(parameters.containsKey("fromelement") && parameters.get("fromelement") != null && parameters.get("fromelement").get() != null) {
      convertedParameters.put("fromelement", parameters.get("fromelement").get());
      if(convertedParameters.get("fromelement") !=null && !(convertedParameters.get("fromelement") instanceof String)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","fromelement", "String", parameters.get("fromelement").get().getClass().getSimpleName()));
      }
    }
    if(convertedParameters.get("fromelement") == null) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.validation.notEmpty","fromelement"));
    }

    if(parameters.containsKey("toelement") && parameters.get("toelement") != null && parameters.get("toelement").get() != null) {
      convertedParameters.put("toelement", parameters.get("toelement").get());
      if(convertedParameters.get("toelement") !=null && !(convertedParameters.get("toelement") instanceof String)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","toelement", "String", parameters.get("toelement").get().getClass().getSimpleName()));
      }
    }
    if(convertedParameters.get("toelement") == null) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.validation.notEmpty","toelement"));
    }

    if(parameters.containsKey("type") && parameters.get("type") != null && parameters.get("type").get() != null) {
      convertedParameters.put("type", parameters.get("type").get());
      if(convertedParameters.get("type") !=null && !(convertedParameters.get("type") instanceof String)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","type", "String", parameters.get("type").get().getClass().getSimpleName()));
      }
    }
    if(convertedParameters.get("type") == null) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.validation.notEmpty","type"));
    }
    if(convertedParameters.get("type") != null) {
      switch((String)convertedParameters.get("type")) {
        case "XPATH" : {

        } break;
        case "ID" : {

        } break;
        case "TAG" : {

        } break;
        case "CSS" : {

        } break;
        default : throw new BotCommandException(MESSAGES_GENERIC.getString("generic.InvalidOption","type"));
      }
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
      command.action((String)convertedParameters.get("sessionName"),(String)convertedParameters.get("fromelement"),(String)convertedParameters.get("toelement"),(String)convertedParameters.get("type"),(Number)convertedParameters.get("timeout"),(String)convertedParameters.get("attribute"));Optional<Value> result = Optional.empty();
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
