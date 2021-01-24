package com.sci.demo.handler;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import com.sci.demo.IntentNames;
import com.sci.demo.InputSlots;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class GetRecipientIntentHandler implements CustomRequestHandler{

    private static final Logger log = LoggerFactory.getLogger(GetRecipientIntentHandler.class);

    @Override
    public String getIntentName() {
        return IntentNames.GetRecipientIntent.name();
    }

    @Override
    public String getResponseSpeech() {
        return "Do you want to add any notes to this task?";
    }

    @Override
    public String getRePromptSpeech() {
        return "I am sorry, I did not understand! Can you please tell me, if you want to add any notes to this task?";
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {
        return handleDefault("Recipient", handlerInput, false,
                InputSlots.Recipient.name()).build();
    }
}