package com.mercureit.DebtCollectorBFF.listeners;

import org.kie.api.event.rule.*;

import java.util.ArrayList;
import java.util.List;

public class CustomAgendaEventListener implements AgendaEventListener {

    private final List<String> matchedRules = new ArrayList<>();



    @Override
    public void matchCancelled(MatchCancelledEvent event) {
        System.out.println("Match Cancelled: " + event.getMatch().getRule().getName());
    }



    @Override
    public void agendaGroupPopped(AgendaGroupPoppedEvent event) {
        System.out.println("Agenda Group Popped: " + event.getAgendaGroup().getName());
    }

    @Override
    public void agendaGroupPushed(AgendaGroupPushedEvent event) {
        System.out.println("Agenda Group Pushed: " + event.getAgendaGroup().getName());
    }

    @Override
    public void beforeRuleFlowGroupActivated(RuleFlowGroupActivatedEvent event) {
        System.out.println("Before RuleFlow Group Activated: " + event.getRuleFlowGroup().getName());
    }

    @Override
    public void afterRuleFlowGroupActivated(RuleFlowGroupActivatedEvent event) {
        System.out.println("After RuleFlow Group Activated: " + event.getRuleFlowGroup().getName());
    }

    @Override
    public void beforeRuleFlowGroupDeactivated(RuleFlowGroupDeactivatedEvent event) {
        System.out.println("Before RuleFlow Group Deactivated: " + event.getRuleFlowGroup().getName());
    }

    @Override
    public void afterRuleFlowGroupDeactivated(RuleFlowGroupDeactivatedEvent event) {
        System.out.println("After RuleFlow Group Deactivated: " + event.getRuleFlowGroup().getName());
    }

    @Override
    public void matchCreated(MatchCreatedEvent event) {
        String ruleName = event.getMatch().getRule().getName();
        matchedRules.add(ruleName);
        System.out.println("Match Created: " + ruleName);
    }

    @Override
    public void beforeMatchFired(BeforeMatchFiredEvent event) {
        String ruleName = event.getMatch().getRule().getName();
        System.out.println("Before Match Fired: " + ruleName);
    }

    @Override
    public void afterMatchFired(AfterMatchFiredEvent event) {
        String ruleName = event.getMatch().getRule().getName();
        System.out.println("After Match Fired: " + ruleName);
    }

    public List<String> getMatchedRules() {
        return matchedRules;
    }
}
