package com.abramchik.taskOnePatterns.behavioral.chainOfResponsibility;

public class Main {
    public static void main(String[] args) {
        Notifier reportNotifier = new ReportNotifier(Priority.ROUTINE);
        Notifier emailNotifier = new EmailNotifier(Priority.IMPORTANT);
        Notifier smsNotifier = new SMSNotifier(Priority.ASAP);

        reportNotifier.setNextNotifier(emailNotifier);
        emailNotifier.setNextNotifier(smsNotifier);

        reportNotifier.notifyManager("Everything is OK ", Priority.ROUTINE);
        reportNotifier.notifyManager("Something wrong ", Priority.IMPORTANT);
        reportNotifier.notifyManager("Problems!!", Priority.ASAP);

    }
}
