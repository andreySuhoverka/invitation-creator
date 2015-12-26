package com.sukhoverka.invitation;

import java.io.Console;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class InvitationCreator {

    private Console console;
    private Class InvitationAgentClass;
    private Object invitationAgent;

    public InvitationCreator() {
        console = System.console();
    }

    public void invite() throws Exception {
        console.format("Enter path to jar file\n");
        console.format("e.g: file:\\D:\\myprojects\\mentoring\\classloading\\invitation-agent\\target\\invitation-agent-1.0-SNAPSHOT.jar\n");
        console.format("path to jar: ");
        String pathToJar = console.readLine();
        InvitationAgentClass = getInvitationAgentClass(pathToJar);
        invitationAgent = InvitationAgentClass.newInstance();
        populateInvitationAgent("Type invitation Event: \n", "setEvent", String.class);
        populateInvitationAgent("Type template name: \n", "setTemplateName", String.class);
        populateInvitationAgent("Type email: \n", "setEmail", String.class);
        populateInvitationAgent("Type template name: \n", "sendInvitation");
    }

    private void populateInvitationAgent(String msg, String methodName, Class<?>... parameterTypes) throws Exception {
        Method method = InvitationAgentClass.getDeclaredMethod(methodName, parameterTypes);
        console.format(msg);
        if(parameterTypes.length != 0){
            String arg = console.readLine();
            method.invoke(invitationAgent, arg);
        } else {
            method.invoke(invitationAgent);
        }
    }

    private Class getInvitationAgentClass(String pathToJar) throws Exception {
        URL[] urls = new URL[]{new URL(pathToJar)};
        URLClassLoader child = new URLClassLoader(urls);
        return Class.forName("com.sukhoverka.invitation.agent.InvitationAgent", true, child);
    }
}
