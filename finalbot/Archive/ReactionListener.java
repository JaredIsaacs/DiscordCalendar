import org.javacord.api.event.message.reaction.ReactionAddEvent;
import org.javacord.api.listener.message.reaction.ReactionAddListener;

public class ReactionListener implements ReactionAddListener {
    @Override
    public void onReactionAdd(ReactionAddEvent event) { //NOT IN USE. FOR FUTURE REFERENCE
        System.out.println(event.getEmoji());
        if (event.getEmoji().equalsEmoji("\uD83D\uDC4D")){
            event.getMessage().get().getUserAuthor().get().sendMessage("test");
            event.getMessageAuthor().get().getMessage().getUserAuthor().get().sendMessage("test");
        }
    }
}
