package pro1.reports.report2;

import com.google.gson.Gson;
import pro1.DataSource;
import pro1.apiDataModel.ActionsList;
import pro1.reports.report2.reportDataModel.DepartmentStats;

public class DepartmentStatsReporting {
    public static DepartmentStats GetReport(DataSource dataSource, String rok, String katedra) {
        var actionsListJson = dataSource.getRozvrhByKatedra(rok, katedra);
        var actionsList = new Gson().fromJson(actionsListJson, ActionsList.class);
        return new DepartmentStats(
                maxActionStudentsCount(actionsList),
                emptyActionsCount(actionsList),
                maxTeacherScore(actionsList)
        );
    }

    private static long maxActionStudentsCount(ActionsList actionsList) {
        // TODO 2.1: maximální počet přihlášených studentů na rozvrhové akci
        return actionsList.items.stream()
                .mapToLong(a -> a.studentsCount)
                .max()
                .orElse(0);
    }

    private static long emptyActionsCount(ActionsList actionsList) {
        // TODO 2.2: počet rozvrhových akcí s 0 studenty
        return actionsList.items.stream()
                .filter(a -> a.studentsCount == 0)
                .count();
    }

    private static long maxTeacherScore(ActionsList actionsList) {
        // TODO 2.4: nejvyšší teacherScore mezi všemi učiteli
        return actionsList.items.stream()
                .mapToLong(a -> a.teacherId)
                .distinct()
                .map(id -> teacherScore(id, actionsList))
                .max()
                .orElse(0);
    }

    private static long teacherScore(long teacherId, ActionsList actionsList) {
        // TODO 2.3: součet obsazení všech akcí daného učitele
        return actionsList.items.stream()
                .filter(a -> a.teacherId == teacherId)
                .mapToLong(a -> a.studentsCount)
                .sum();
    }
}