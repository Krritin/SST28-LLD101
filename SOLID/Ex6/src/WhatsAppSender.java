public class WhatsAppSender extends NotificationSender {
    public WhatsAppSender(AuditLog audit) { super(audit); }

    @Override
    public void send(Notification n) {
        String phone = n.phone != null ? n.phone : "";
        if (!phone.startsWith("+")) {
            System.out.println("WA ERROR: phone must start with + and country code");
            audit.add("WA failed");
            return;
        }
        String body = n.body != null ? n.body : "";
        System.out.println("WA -> to=" + phone + " body=" + body);
        audit.add("wa sent");
    }
}
