package com.sukhoverka.invitation;

import java.io.Console;

public class App {
    public static void main(String[] args) throws Exception {
        Console console = System.console();
        if(console == null){
            throw new ExceptionInInitializerError("No console available, We need console to perform an action!");
        }
        console.format("**********Welcome to invitation creator**********\n");
        boolean tryAgain = true;
        InvitationCreator invitationCreator = new InvitationCreator();
        while (tryAgain){
            invitationCreator.invite();
            console.format("\nWould you like to do more invitations?\n");
            String userReply = console.readLine();
            if(!"yes".equals(userReply)){
                tryAgain = false;
            }
        }
    }
}
