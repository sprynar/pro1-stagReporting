package pro1.reports.report3;

import com.google.gson.Gson;
import pro1.DataSource;
import pro1.apiDataModel.ActionsList;
import pro1.reports.report3.reportDataModel.WeekdayStats;

public class DepartmentWeekdaysReporting {
    public static WeekdayStats[] GetReport(DataSource dataSource, String rok, String katedra, String[] days) {
        var actionsListJson = dataSource.getRozvrhByKatedra(rok, katedra);
        var actionsList = new Gson().fromJson(actionsListJson, ActionsList.class);

        var result = new WeekdayStats[days.length];
        for (int i = 0; i < days.length; i++) {
            String day = days[i];
            long count = actionsList.items.stream()
                    .filter(a -> day.equals(a.dayShort))
                    .count();
            result[i] = new WeekdayStats(day, count);
        }
        return result;
    }
}