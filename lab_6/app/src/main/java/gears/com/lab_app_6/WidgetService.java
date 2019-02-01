package gears.com.lab_app_6;

import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.widget.RemoteViewsService;

import java.util.List;

import gears.com.lab_app_6.sqlDB.CryptoUtil;
import gears.com.lab_app_6.sqlDB.CryptoValuesReaderContract;

/**
 * Created by cse on 2/20/2018.
 */

public class WidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        int appWidgetId = intent.getIntExtra(
                AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID);

        CryptoValuesReaderContract mdbHelper = new CryptoValuesReaderContract(this);
        final CryptoUtil util = new CryptoUtil(mdbHelper,CryptoUtil.WRITE_TO_DB);
        List<CryptoItem> k = util.getAllCryptos();
        return (new WidgetListProvider(this.getApplicationContext(), intent,k));
    }
}
