import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

public class EmbedListener implements MessageCreateListener {
    @Override
    public void onMessageCreate(MessageCreateEvent event) { // NOT IN USE, FOR FUTURE REFERENCE
        if(event.getMessage().getUserAuthor().get().getName().equals("finalbot")){
            System.out.println("Bot Message: \"" + event.getMessage().getContent() + "\"");
            if(event.getMessageContent().equals("You've created a new event")){
                event.addReactionToMessage("\uD83D\uDC4D");
                System.out.println("Success");
            }
        }
    }
}
