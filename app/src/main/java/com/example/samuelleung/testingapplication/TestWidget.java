package com.example.samuelleung.testingapplication;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

/**
 * Implementation of App Widget functionality.
 */
public class TestWidget extends AppWidgetProvider {
    private static final String TAG = "TestWidget";
    private static String ACTION_CLICK_BUTTON = "ClickButton";

    private static String textViewValue = "START";


    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int i = 0; i < appWidgetIds.length; i++) {
            int widgetId = appWidgetIds[i];
            Log.d(TAG, "updateAppWidget: " + widgetId);

            CharSequence widgetText = textViewValue;
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.test_widget);
            views.setTextViewText(R.id.appwidget_text, widgetText);


            Intent intent = new Intent(context, TestWidget.class);
            intent.setAction(ACTION_CLICK_BUTTON);
//            intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
            views.setOnClickPendingIntent(R.id.appwidget_button, pendingIntent);


            // Instruct the widget manager to update the widget
            appWidgetManager.updateAppWidget(widgetId, views);
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        if (intent.getAction().equals(ACTION_CLICK_BUTTON)) {

            ComponentName watchWidget = new ComponentName(context, TestWidget.class);
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.test_widget);

            textViewValue = "LATEST: " + RandomHelper.getRandomNumber(0, 999);
            views.setTextViewText(R.id.appwidget_text, textViewValue);

            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            appWidgetManager.updateAppWidget(watchWidget, views);

        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
//        for (int appWidgetId : appWidgetIds) {
        updateAppWidget(context, appWidgetManager, appWidgetIds);
//        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

