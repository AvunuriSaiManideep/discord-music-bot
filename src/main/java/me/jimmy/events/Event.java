package me.jimmy.events;

import me.duncte123.botcommons.BotCommons;
import me.jimmy.Config;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.GuildLeaveEvent;
import net.dv8tion.jda.api.events.guild.member.*;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceUpdateEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.*;
import org.jetbrains.annotations.*;

public class Event extends ListenerAdapter{
    @Override
    public void onGuildMemberJoin(@NotNull GuildMemberJoinEvent event) {
        if(!event.getGuild().getId().equals("1060918600925466686")){
            return;
        }
        event.getGuild().getTextChannelById("1060918602657701950").sendMessage("Welcome to the server" + event.getMember().getAsMention() + "!").queue();
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String prefix = Config.get("prefix");
        String raw = event.getMessage().getContentRaw();
        if(event.getMessage().isFromGuild()){
            String a = event.getMessage().getContentRaw();
            if(!event.getMember().getUser().isBot() && a.equalsIgnoreCase("hellokasmfkaf")){
                event.getChannel().sendMessage("Hello, How was your day " + event.getMember().getAsMention() + "?").queue();
            }
        }
        if(event.isFromGuild()){
            if(raw.equalsIgnoreCase(prefix + "shutdown") && event.getAuthor().getId().equals(Config.get("owner_id"))){
                event.getChannel().sendMessage("Successfully fucked off ").queue();
                event.getJDA().shutdown();
            }
        }

    }


    @Override
    public void onGuildVoiceUpdate(GuildVoiceUpdateEvent event) {
        if(event.getMember().getVoiceState().inAudioChannel()){
            event.getGuild().getTextChannelById("1060918602657701950").sendMessage("User Joined Channel: " + event.getMember().getAsMention()).queue();
        }
        if(!event.getMember().getVoiceState().inAudioChannel()){
            event.getGuild().getTextChannelById("1060918602657701950").sendMessage("User Left Channel: " + event.getMember().getAsMention()).queue();
        }
    }


}