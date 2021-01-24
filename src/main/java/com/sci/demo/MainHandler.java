package com.sci.demo;

import com.amazon.ask.Skill;
import com.amazon.ask.SkillStreamHandler;
import com.amazon.ask.Skills;
import com.sci.demo.handler.*;

public class MainHandler extends SkillStreamHandler {

    private static Skill getSkill(String skillId) {
        return Skills.custom()
                .addRequestHandlers(
                        new LaunchRequestHandler(),
                        new DialogIntentHandler(),
                        new GetServiceIntentHandler(),
                        new GetLocationIntentHandler(),
                        new GetTimeIntentHandler(),
                        new GetRecipientIntentHandler(),
                        new GetNoteIntentHandler()
                )
                .withSkillId(skillId)
                .build();
    }

    public MainHandler() {
        // TODO Add your skill id below
        super(getSkill("amzn1.ask.skill.6978925d-bbe3-400b-b39f-25940600b431"));
    }
}