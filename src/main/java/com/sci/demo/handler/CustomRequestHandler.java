package com.sci.demo.handler;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Slot;
import com.amazon.ask.response.ResponseBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;
import java.util.Map;

import static com.amazon.ask.request.Predicates.intentName;

public interface CustomRequestHandler extends RequestHandler {

    Logger log = LoggerFactory.getLogger(CustomRequestHandler.class);

    String getIntentName();

    String getResponseSpeech();

    String getRePromptSpeech();

    @Override
    default boolean canHandle(HandlerInput input) {
        return input.matches(intentName(getIntentName()));
    }

    default ResponseBuilder handleDefault(String outputSlot, HandlerInput handlerInput, boolean endSession, String... slotNames){

        Map<String, Slot> slotMap = ((IntentRequest)handlerInput.getRequestEnvelope().getRequest()).getIntent().getSlots();
        Map<String,Object> sessionAttributes = handlerInput.getAttributesManager().getSessionAttributes();
        Map<String,Object> localAttributes = new LinkedHashMap();

        if(slotNames.length>0){
            for(String slotName : slotNames){
                if(slotMap.containsKey(slotName) && slotMap.get(slotName)!=null){
                    localAttributes.put(slotName,slotMap.get(slotName).getValue());
                }
            }
            if(localAttributes.size()>0){
                sessionAttributes.put(outputSlot,getFinalValue(localAttributes));
                handlerInput.getAttributesManager().setSessionAttributes(sessionAttributes);
            }else{
                return handlerInput.getResponseBuilder()
                        .withReprompt(getRePromptSpeech())
                        .withSpeech(getRePromptSpeech())
                        .withShouldEndSession(endSession);
            }
        }
        return handlerInput.getResponseBuilder()
                .withReprompt(getRePromptSpeech())
                .withSpeech(getResponseSpeech())
                .withShouldEndSession(endSession);
    }

    default String getFinalValue(Map<String, Object> localAttributes){
        return localAttributes.values().stream().filter(value->value!=null).
                map(value->value.toString()+" ").reduce("",(s1, s2) -> s1+s2);
    }
}