package gears.com.lab_app_6;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.util.ArrayList;
import java.util.List;


public class WidgetListProvider implements RemoteViewsService.RemoteViewsFactory{

    private List<CryptoItem> listItemList = new ArrayList<>();
    private Context context = null;
    private int appWidgetId;

    public WidgetListProvider(Context context, Intent intent, List<CryptoItem> items) {
        this.context = context;
        appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID);

        listItemList = items;
    }

    @Override
    public int getCount() {
        return listItemList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /*
    *Similar to getView of Adapter where instead of View
    *we return RemoteViews
    *
    */
    @Override
    public RemoteViews getViewAt(int position) {
        final RemoteViews remoteView = new RemoteViews(
                context.getPackageName(), R.layout.list_item);
        CryptoItem item = listItemList.get(position);
        Log.d("TAG",item.getName());
        remoteView.setTextViewText(R.id.list_item_name, item.getName());
        remoteView.setTextViewText(R.id.list_item_symbol, item.getSymbol());
        remoteView.setTextViewText(R.id.list_item_price,String.valueOf(item.getPrice()));
//        remoteView.

        return remoteView;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}
