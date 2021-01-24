package com.sci.demo.handler;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import com.amazon.ask.response.ResponseBuilder;
import com.sci.demo.InputSlots;
import com.sci.demo.IntentNames;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Optional;

public class GetNoteIntentHandler implements CustomRequestHandler{

    private String responseSpeech;
    private static final Logger log = LoggerFactory.getLogger(GetNoteIntentHandler.class);

    @Override
    public String getIntentName() {
        return IntentNames.GetNoteIntent.name();
    }

    @Override
    public String getResponseSpeech() {
        return this.responseSpeech;
    }

    @Override
    public String getRePromptSpeech() {
        return "I am sorry, I am not sure if I understood what you just said. Lets try again! What type of service do you want?  ";
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {
        ResponseBuilder responseBuilder = handleDefault("Note",handlerInput,true,
                InputSlots.FreeText.name(), InputSlots.Phrase.name(),
                InputSlots.Adjective.name(), InputSlots.Quantity.name(),
                InputSlots.Unit.name(), InputSlots.Things.name());

        setResponseSpeech(handlerInput.getAttributesManager().getSessionAttributes());

        return responseBuilder.withSpeech(getResponseSpeech())
                .withSimpleCard("New task was created!",getResponseSpeech())
                .build();
    }

    private void setResponseSpeech(Map<String, Object> sessionAttributes) {
        this.responseSpeech = new StringBuilder("I have created a new task, I will read it for you once again! ")
            .append("Hello "+sessionAttributes.get("Recipient")+", ")
            .append("I need your help for "+sessionAttributes.get("Service")+" ")
            .append("at "+sessionAttributes.get("Location")+" ")
            .append("at "+sessionAttributes.get("Time")+". ")
            .append("P.S. : "+sessionAttributes.get("Note")+".")
            .toString();
    }
}