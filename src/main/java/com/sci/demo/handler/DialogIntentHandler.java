package com.sci.demo.handler;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import com.sci.demo.IntentNames;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class DialogIntentHandler implements CustomRequestHandler{

    private static final Logger log = LoggerFactory.getLogger(DialogIntentHandler.class);

    @Override
    public String getIntentName() {
        return IntentNames.DialogIntent.name();
    }

    @Override
    public String getResponseSpeech() {
        return "Ok, sure! Can you please tell me what type of service do you want?";
    }

    @Override
    public String getRePromptSpeech() {
        return "I am sorry, I did not understand! Can you please tell me what type of service do you want?";
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {
        return handlerInput.getResponseBuilder()
                .withReprompt(getRePromptSpeech())
                .withSpeech(getResponseSpeech())
                .withShouldEndSession(false)
                .build();
    }
}