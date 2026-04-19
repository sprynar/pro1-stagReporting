package pro1.reports.report4;

import com.google.gson.Gson;
import pro1.DataSource;
import pro1.apiDataModel.Thesis;
import pro1.apiDataModel.ThesisList;
import pro1.reports.report4.reportDataModel.YearDuration;

import java.time.temporal.ChronoUnit;

public class ThesisDurationReporting {
    public static YearDuration[] GetReport(DataSource dataSource, String katedra, String[] years) {
        var gson = new Gson();
        var result = new YearDuration[years.length];

        for (int i = 0; i < years.length; i++) {
            String year = years[i];
            var json = dataSource.getKvalifikacniPrace(year, katedra);
            ThesisList thesisList = gson.fromJson(json, ThesisList.class);

            long sum = 0;
            long count = 0;
            for (Thesis thesis : thesisList.items) {
                if (thesis.assignmentDate != null && thesis.submissionDate != null
                        && thesis.assignmentDate.isValid() && thesis.submissionDate.isValid()) {
                    long days = ChronoUnit.DAYS.between(
                            thesis.assignmentDate.toLocalDate(),
                            thesis.submissionDate.toLocalDate());
                    sum += days;
                    count++;
                }
            }

            long average = count > 0 ? Math.round((double) sum / count) : 0;
            result[i] = new YearDuration(year, average);
        }

        return result;
    }
}