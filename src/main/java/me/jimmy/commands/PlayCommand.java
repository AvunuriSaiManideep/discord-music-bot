package me.jimmy.commands;

import me.jimmy.Config;
import me.jimmy.lavaplayer.PlayerManager;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class PlayCommand extends ListenerAdapter {

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        String prefix = Config.get("prefix");
        String play = event.getMessage().getContentRaw();
        String[] a = play.split(" ",2);
        if(event.getMessage().isFromGuild() && play.startsWith(prefix)) {
            final TextChannel channel = event.getChannel().asTextChannel();
            final Member self = event.getGuild().getSelfMember();
            final GuildVoiceState selfVoiceState = self.getVoiceState();

            if (!selfVoiceState.inAudioChannel() && !event.getMember().getUser().isBot()) {
                channel.sendMessage("I need to be in a voice channel").queue();
                return;
            }
            final Member member = event.getMember();
            final GuildVoiceState memberVoiceState = member.getVoiceState();
            if (!memberVoiceState.inAudioChannel() && !event.getMember().getUser().isBot()) {
                channel.sendMessage("You need to be in a voice channel").queue();
                return;
            }
            if (!memberVoiceState.getChannel().equals(selfVoiceState.getChannel()) && !event.getMember().getUser().isBot()) {
                channel.sendMessage("You need to be in the same voice channel").queue();
                return;
            }
            if(a.length == 1){
                if(!event.getMember().getUser().isBot() && a[0].equalsIgnoreCase(prefix + "play")) {
                    event.getChannel().sendMessage("Correct Syntax is `-play <youtube-link> or name`").queue();
                    return;
                }
            }
            String link = a[1];
            if(play.equalsIgnoreCase(prefix + "play " + link)){
                System.out.println("1");
                if(!isUrl(link)){
                    System.out.println(link);
                    link = "ytsearch:" + link;
                    System.out.println(link);
                }
                PlayerManager.getInstance().loadandPlay(channel, link);
            }
        }
    }

    private boolean isUrl(String url){
        try{
            new URL(url).toURI();
            System.out.println("2");
            return true;
        }
        catch(Exception e){
            System.out.println("3");
            return false;
        }
    }

}
