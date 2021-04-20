# DiscordCalendar
Discord bot that uses googles calendar api.

https://discord.com/api/oauth2/authorize?client_id=806739762001870901&permissions=19456&scope=bot <- adds bot to server. Be advised, bot doesn't always run. Only when I have it running on my pc.


Commands:
1. "!s eventName day time period"
  This command schedules an event named eventname at day and time. Period is AM or PM. Uses current month and year.
  
2. "!s eventName year month day time period"
  This command scheudles an event name eventname on the year and month provided at the day and time provided. Period is AM or PM.
  
3. "!events"
  recalls all created events on the current calendar.

4. "!join eventName email@email.com"
  Adds email to the guest list of the event name. This is useful if you want to get notification through google calendar itself.
  
5. "!remindme eventName"
  Reminds the user in Discord DMs 5 minutes before the event starts.
  
