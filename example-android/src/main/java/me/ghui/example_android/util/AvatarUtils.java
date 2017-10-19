package me.ghui.example_android.util;

/**
 * Created by ghui on 23/06/2017.
 */

public class AvatarUtils {
    public static String adjustAvatar(String avatar) {
        if (PreConditions.isEmpty(avatar)) return null;
        //1.
        if (!avatar.startsWith("https") && !avatar.startsWith("http")) {
            avatar = "https:" + avatar;
        }

        //2.
        if (avatar.contains("_normal.png")) {
            avatar = avatar.replace("_normal.png", "_large.png");
        } else if (avatar.contains("_mini.png")) {
            avatar = avatar.replace("_mini.png", "_large.png");
        }

        if (avatar.contains("_xxlarge.png")) {
            avatar = avatar.replace("_xxlarge.png", "_large.png");
        }

        //3. del param
//        if (avatar.contains("?")) {
//            avatar = avatar.substring(0, avatar.indexOf("?"));
//        }
        return avatar;
    }
}
