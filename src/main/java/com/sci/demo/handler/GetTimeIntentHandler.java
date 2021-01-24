package com.sci.demo.handler;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import com.sci.demo.IntentNames;
import com.sci.demo.InputSlots;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class GetTimeIntentHandler implements CustomRequestHandler{

    private static final Logger log = LoggerFactory.getLogger(GetTimeIntentHandler.class);

    @Override
    public String getIntentName() {
        return IntentNames.GetTimeIntent.name();
    }

    @Override
    public String getResponseSpeech() {
        return "Whom shall I call for this service?";
    }

    @Override
    public String getRePromptSpeech() {
        return "I am sorry, I did not understand! Can you please tell me "+getResponseSpeech();
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {
        return handleDefault("Time", handlerInput, false,
                InputSlots.Day.name(), InputSlots.Date.name(), InputSlots.Time.name())
                .build();
    }
}