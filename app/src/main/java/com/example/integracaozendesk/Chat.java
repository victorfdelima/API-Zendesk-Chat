package com.example.integracaozendesk;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.integracaozendesk.R;
import com.zendesk.logger.Logger;
import com.zendesk.util.StringUtils;
import zendesk.chat.ChatConfiguration;
import zendesk.chat.ChatEngine;
import zendesk.messaging.MessagingActivity;

public class Chat extends AppCompatActivity {



    private static final String CHAT_ACCOUNT_KEY = "Sua chave aqui"; //Chave de acesso da conta do Livechat
    private static boolean missingCredentials = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // Enable logging
        Logger.setLoggable(true);

        if (StringUtils.isEmpty(CHAT_ACCOUNT_KEY)) {
            missingCredentials = true;
        }

        zendesk.chat.Chat.INSTANCE.init(this, CHAT_ACCOUNT_KEY);

        if (Chat.isMissingCredentials()) {
            setContentView(R.layout.activity_main);
            return;
        }

        ChatConfiguration chatConfiguration = ChatConfiguration.builder().build();


        MessagingActivity.builder()
                .withEngines(ChatEngine.engine())
                .withBotLabelString("Nome do seu bot")
                .show(this, chatConfiguration);


    }
    static boolean isMissingCredentials() {
        return missingCredentials;
    }
}

//Criado por Victor de Lima