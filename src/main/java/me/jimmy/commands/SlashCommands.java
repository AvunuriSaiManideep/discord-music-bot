package me.jimmy.commands;

import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.AudioManager;

public class SlashCommands extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        switch (event.getName()) {
            case "hello" -> event.reply("M").queue();
            case "google" -> event.reply("Yes, You can google " + event.getMember().getUser().getAsMention()).queue();
            case "join" -> {
                event.reply("Joining...").queue();
                AudioManager manager = event.getGuild().getAudioManager();
                VoiceChannel vc = (VoiceChannel) event.getMember().getVoiceState().getChannel();
                manager.openAudioConnection(vc);
            }
            case "leave" ->{
                event.reply("Leaving...").queue();
                event.getGuild().getAudioManager().closeAudioConnection();
            }
            case "fuckoff" ->{
                event.reply("Fucking off...").queue();
                event.getGuild().getAudioManager().closeAudioConnection();
            }
            default -> {
            }
        }
    }
}
