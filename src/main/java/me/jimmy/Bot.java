package me.jimmy;


import me.jimmy.commands.PlayCommand;
import me.jimmy.commands.SlashCommands;
import me.jimmy.events.Event;
import net.dv8tion.jda.api.*;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import javax.security.auth.login.LoginException;
import java.util.Arrays;

public class Bot extends ListenerAdapter {
    public static final GatewayIntent[] INTENTS = {GatewayIntent.DIRECT_MESSAGES,GatewayIntent.GUILD_MESSAGES,GatewayIntent.GUILD_MESSAGE_REACTIONS,GatewayIntent.GUILD_VOICE_STATES,GatewayIntent.GUILD_MEMBERS,GatewayIntent.GUILD_PRESENCES};
    public static void main(String[] args) throws LoginException {
        JDA jda = JDABuilder.createDefault(Config.get("token"), Arrays.asList(INTENTS))
                .enableCache(CacheFlag.VOICE_STATE)
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                .setActivity(Activity.watching("your mom"))
                .addEventListeners(new Event())
                .addEventListeners(new SlashCommands())
                .addEventListeners(new PlayCommand())
                .build();
        jda.updateCommands().addCommands(
                Commands.slash("hello","prints Hello"),
                Commands.slash("google","it does things"),
                Commands.slash("join","join a vc"),
                Commands.slash("leave","leaves vc"),
                Commands.slash("fuckoff","will fuck off")
        ).queue();

    }


}
