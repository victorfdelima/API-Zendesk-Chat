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



    private static final String CHAT_ACCOUNT_KEY = "Sua chave aqui"; //Chave de acesso da conta do Livechat Zendesk
    private static boolean missingCredentials = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // Enable logging
        Logger.setLoggable(true);

        if (StringUtils.isEmpty(CHAT_ACCOUNT_KEY)) {
            missingCredentials = true;
        }
        //alterar o zendesk.chat.Chat por Chat conforme a que está comentada e refatorar a classe Chat para AJuda ou Help
        zendesk.chat.Chat.INSTANCE.init(this, CHAT_ACCOUNT_KEY);
        //Chat.INSTANCE.init(this, CHAT_ACCOUNT_KEY);

        if (Chat.isMissingCredentials()) {
            setContentView(R.layout.activity_main); //Alterar a activity_main que consta no R.Layout.activity_main para a sua tela de ajuda ou Help
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
