import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;

public class Main {

    //https://developers.google.com/calendar/v3/reference/events/update
    public static void main(String[] args){
        DiscordApi api = new DiscordApiBuilder()
                // Adds custom listeners that looks for commands and adds events to a google calendar
                .addListener(new CalendarListener()).addListener(new CalendarRetrievalListener()).addListener(new AddAttendeeListener()).addListener(new RemindListener())
                .setToken("ODA2NzM5NzYyMDAxODcwOTAx.YBt08w.E2CrFMHktmfPR5eNOdbXIaq7Mc0")
                .login().join();
    }
}

