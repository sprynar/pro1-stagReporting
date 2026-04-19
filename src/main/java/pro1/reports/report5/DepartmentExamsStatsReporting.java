package pro1.reports.report5;

import com.google.gson.Gson;
import pro1.DataSource;
import pro1.apiDataModel.ExamsList;
import pro1.reports.report5.reportDataModel.DepartmentExamsStats;
import java.util.List;
import java.util.stream.Collectors;

public class DepartmentExamsStatsReporting {
    public static DepartmentExamsStats GetReport(DataSource dataSource, String katedra) {
        var json = dataSource.getTerminyZkousek2(katedra);
        var examsList = new Gson().fromJson(json, ExamsList.class);

        long realizedExamsCount = examsList.items.stream()
                .filter(e -> e.studentsCount != null && Integer.parseInt(e.studentsCount) > 0)
                .count();

        List<Long> teacherIds = examsList.items.stream()
                .filter(e -> e.teacherId != null)
                .map(e -> e.teacherId)
                .distinct()
                .sorted()
                .collect(Collectors.toList());

        return new DepartmentExamsStats(realizedExamsCount, teacherIds);
    }
}