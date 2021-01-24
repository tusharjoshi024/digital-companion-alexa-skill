package com.sci.demo.handler;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.LaunchRequest;
import com.amazon.ask.model.Response;
import com.sci.demo.IntentNames;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.requestType;

public class LaunchRequestHandler implements CustomRequestHandler{

    private static final Logger log = LoggerFactory.getLogger(LaunchRequestHandler.class);

    @Override
    public boolean canHandle(HandlerInput handlerInput){
        return handlerInput.matches(requestType(LaunchRequest.class));
    }

    @Override
    public String getIntentName() {
        return IntentNames.LaunchRequest.name();
    }

    @Override
    public String getResponseSpeech() {
        return "Welcome to Digital Companion, to start, say 'Create new task'";
    }

    @Override
    public String getRePromptSpeech() {
        return "I am sorry, I did not understand! to start, say 'Create new task'";
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