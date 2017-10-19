package me.ghui.example_android.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.support.customtabs.CustomTabsIntent;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.util.List;

import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;

import me.ghui.example_android.R;
import me.ghui.example_android.general.App;
import me.ghui.example_android.widget.Voast;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

/**
 * Created by ghui on 01/04/2017.
 */

public class Utils {
    public static int listSize(List list) {
        return list == null ? 0 : list.size();
    }


    public static CharSequence highlight(String text, boolean bold, int[]... hightIndexs) {
        SpannableStringBuilder builder = new SpannableStringBuilder(text);
        int color = App.get().getResources().getColor(R.color.colorAccent);
        for (int[] indexs : hightIndexs) {
            builder.setSpan(new ForegroundColorSpan(color), indexs[0], indexs[1], Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            if (bold)
                builder.setSpan(new StyleSpan(Typeface.BOLD), indexs[0], indexs[1], Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        }
        return builder;
    }

    public static void toggleKeyboard(boolean show, EditText inputEdit) {
        InputMethodManager imm = (InputMethodManager)
                App.get().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (show) {
            imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0);
            inputEdit.requestFocus();
        } else {
            imm.hideSoftInputFromWindow(inputEdit.getWindowToken(), 0);
        }
    }

    public static void openStorePage() {
        final String pkgName = App.get().getPackageName();
        openStorePage(pkgName);
    }

    public static void openStorePage(String pkgName) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + pkgName));
            intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
            App.get().startActivity(intent);
        } catch (android.content.ActivityNotFoundException anfe) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + pkgName));
            intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
            App.get().startActivity(intent);
        }
    }

    public static int getStatusBarHeight() {
        int statusBarHeight;
        int resourceId = App.get().getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = App.get().getResources().getDimensionPixelSize(resourceId);
        } else {
            statusBarHeight = ScaleUtils.dp(24);
        }
        return statusBarHeight;
    }

    public static void setPaddingForStatusBar(View view) {
        view.setPadding(view.getPaddingLeft(), view.getPaddingTop() + getStatusBarHeight(), view.getPaddingRight(), view.getPaddingBottom());
    }

    public static void copyToClipboard(Context context, String text) {
        android.content.ClipboardManager clipboard = (android.content.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        android.content.ClipData clip = android.content.ClipData.newPlainText("Reply Text", text);
        clipboard.setPrimaryClip(clip);
    }

    public static void transparentBars(Window window, int statusBarColor, int navBarColor) {
        window.requestFeature(Window.FEATURE_NO_TITLE);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(statusBarColor);
        window.setNavigationBarColor(navBarColor);
    }

    public static void transparentBars(Window window) {
        transparentBars(window, Color.TRANSPARENT, App.get().getResources().getColor(R.color.transparent_navbar_color));
    }

    public static void transparentStatus(Window window) {
        window.requestFeature(Window.FEATURE_NO_TITLE);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.TRANSPARENT);
    }

    public static void fullScreen(Window window) {
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }


    public static boolean isAppInstalled(String packageName) {
        PackageManager packageManager = App.get().getPackageManager();
        if (packageManager == null)
            return false;
        List<PackageInfo> packageInfoList = packageManager.getInstalledPackages(0);
        for (PackageInfo info : packageInfoList) {
            if (info.packageName.equals(packageName))
                return true;
        }
        return false;
    }

    public static void sendEmail(Context context, String mail, String subject) {
        sendEmail(context, mail, subject, null);
    }

    public static void sendEmail(Context context, String mail, String subject, String content) {
        if (PreConditions.isEmpty(mail)) return;
        String mailUri = mail;
        if (!mail.startsWith("mailto:")) {
            mailUri = "mailto:" + mail;
        }
        try {
            Intent request = new Intent(Intent.ACTION_VIEW);
            request.setData(Uri.parse(mailUri));
            request.putExtra(Intent.EXTRA_SUBJECT, subject);
            request.putExtra(Intent.EXTRA_TEXT, content);
            context.startActivity(request);
        } catch (ActivityNotFoundException e) {
            Voast.show("There are no email clients installed.");
        }
    }

    // 跳转至微博个人页
    public static void jumpToWeiboProfileInfo(Context context) {
        boolean weiboInstalled = Utils.isAppInstalled("com.sina.weibo");
        if (weiboInstalled) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.addCategory(Intent.CATEGORY_DEFAULT);
            intent.addCategory(Intent.CATEGORY_BROWSABLE);
            intent.setData(Uri.parse("sinaweibo://userinfo?uid=ghuiii"));
            context.startActivity(intent);
        } else {
            Utils.openInBrowser("http://weibo.com/ghuiii", context);
        }
    }


    public static void jumpToTwitterProfilePage(Context context) {
        if (Utils.isAppInstalled("com.twitter.android")) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.addCategory(Intent.CATEGORY_DEFAULT);
            intent.addCategory(Intent.CATEGORY_BROWSABLE);
            intent.setData(Uri.parse("twitter://user?screen_name=ghuizh"));
            context.startActivity(intent);
        } else {
            Utils.openInBrowser("https://mobile.twitter.com/ghuizh", context);
        }
    }


    public static void openInBrowser(String url, Context context) {
        CustomTabsIntent customTabsIntent = new CustomTabsIntent.Builder()
                .setToolbarColor(context.getResources().getColor(R.color.colorPrimary))
                .enableUrlBarHiding()
                .setShowTitle(true)
                .addDefaultShareMenuItem()
                .build();
        customTabsIntent.launchUrl(context, Uri.parse(url));
    }

    /**
     * get App versionCode
     *
     * @return
     */
    public static String getVersionCode() {
        Context context = App.get();
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageInfo;
        String versionCode = "";
        try {
            packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            versionCode = packageInfo.versionCode + "";
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    /**
     * get App versionName
     *
     * @return
     */
    public static String getVersionName() {
        Context context = App.get();
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageInfo;
        String versionName = "";
        try {
            packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            versionName = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }


    public static String[] cutString(String text, int cutPos) {
        if (PreConditions.isEmpty(text)) return null;
        String[] result = new String[2];
        result[0] = text.substring(0, cutPos);
        result[1] = text.substring(cutPos, text.length());
        return result;
    }

    public static int getIntFromString(String textContainInt) {
        try {
            return Integer.parseInt(textContainInt.replaceAll("[\\D]", ""));
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }


    public static void openApp(Context context, String packageName) {
        PackageManager manager = context.getPackageManager();
        Intent i = manager.getLaunchIntentForPackage(packageName);
        if (i == null) {
            try {
                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + packageName)));
            } catch (android.content.ActivityNotFoundException anfe) {
                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + packageName)));
            }
            return;
        }
        i.addCategory(Intent.CATEGORY_LAUNCHER);
        context.startActivity(i);
    }

    public boolean isRunning(Context ctx) {
        ActivityManager activityManager = (ActivityManager) ctx.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasks = activityManager.getRunningTasks(Integer.MAX_VALUE);

        for (ActivityManager.RunningTaskInfo task : tasks) {
            if (ctx.getPackageName().equalsIgnoreCase(task.baseActivity.getPackageName()))
                return true;
        }

        return false;
    }

    public static boolean isAppAvailable(String appName) {
        if (PreConditions.isEmpty(appName)) return true;
        PackageManager pm = App.get().getPackageManager();
        try {
            pm.getPackageInfo(appName, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    public static int getMaxTextureSize() {
        // Safe minimum default size
        final int IMAGE_MAX_BITMAP_DIMENSION = 2048;

        // Get EGL Display
        EGL10 egl = (EGL10) EGLContext.getEGL();
        EGLDisplay display = egl.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);

        // Initialise
        int[] version = new int[2];
        egl.eglInitialize(display, version);

        // Query total number of configurations
        int[] totalConfigurations = new int[1];
        egl.eglGetConfigs(display, null, 0, totalConfigurations);

        // Query actual list configurations
        EGLConfig[] configurationsList = new EGLConfig[totalConfigurations[0]];
        egl.eglGetConfigs(display, configurationsList, totalConfigurations[0], totalConfigurations);

        int[] textureSize = new int[1];
        int maximumTextureSize = 0;

        // Iterate through all the configurations to located the maximum texture size
        for (int i = 0; i < totalConfigurations[0]; i++) {
            // Only need to check for width since opengl textures are always squared
            egl.eglGetConfigAttrib(display, configurationsList[i], EGL10.EGL_MAX_PBUFFER_WIDTH, textureSize);

            // Keep track of the maximum texture size
            if (maximumTextureSize < textureSize[0])
                maximumTextureSize = textureSize[0];
        }

        // Release
        egl.eglTerminate(display);

        // Return largest texture size found, or default
//        return Math.max(maximumTextureSize, IMAGE_MAX_BITMAP_DIMENSION);
        return IMAGE_MAX_BITMAP_DIMENSION;
    }

    public static Drawable scaleImage(Drawable image, float scaleFactor) {

        if ((image == null) || !(image instanceof BitmapDrawable)) {
            return image;
        }

        Bitmap b = ((BitmapDrawable) image).getBitmap();

        int sizeX = Math.round(image.getIntrinsicWidth() * scaleFactor);
        int sizeY = Math.round(image.getIntrinsicHeight() * scaleFactor);

        Bitmap bitmapResized = Bitmap.createScaledBitmap(b, sizeX, sizeY, false);

        image = new BitmapDrawable(App.get().getResources(), bitmapResized);

        return image;

    }

}
