package com.npospolita.rbkchbot.api;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SendSticker;
import com.pengrad.telegrambot.response.SendResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class TelegramApi {

    private final TelegramBot bot;

    public void sendMessage(Update update, String text) {
        Message message = update.message();

        SendMessage request = new SendMessage(message.chat().id(), text)
                .parseMode(ParseMode.Markdown)
                .disableWebPagePreview(true)
                .disableNotification(true)
                .replyToMessageId(message.messageId());

        SendResponse response = bot.execute(request);

        if (!response.isOk()) {
            log.error("error: {}", response);
        }

    }

    public void sendSticker(Update update, String stickerFileId) {
        Message message = update.message();

        SendSticker request = new SendSticker(message.chat().id(), stickerFileId)
                .disableNotification(true)
                .replyToMessageId(message.messageId());

        SendResponse response = bot.execute(request);

        if (!response.isOk()) {
            log.error("error: {}", response);
        }
    }
}