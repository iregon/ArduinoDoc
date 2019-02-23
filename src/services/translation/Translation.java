package services.translation;

import java.util.Locale;
import java.util.ResourceBundle;

public class Translation {
    private static Translation instance;

    private ResourceBundle messages;

    public static Translation getInstance() {
        if(instance == null) instance = new Translation();
        return instance;
    }

    private Translation() {
        Locale location = new Locale(Locale.getDefault().getLanguage(), Locale.getDefault().getCountry());
        messages = ResourceBundle.getBundle("resources/translation/messagesBundle/MessagesBundle", location);
    }

    public String getString(String key) {
        return messages.getString(key);
    }
}
