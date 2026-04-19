package pro1.reports.report2;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializer;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import pro1.ResourcesUtils;
import pro1.TestDataSource;

import java.io.IOException;

public class DepartmentStatsReportingTest
{
    @Test
    void test01() throws IOException, JSONException {
        var actual = DepartmentStatsReporting.GetReport(new TestDataSource(), "2025", "KIKM");
        var actualJson = new Gson().toJson(actual);
        System.out.println(actualJson);
        var expectedJson = ResourcesUtils.readResourceFile("expectedReports/departmentStats_2025_KIKM.json");
        JSONAssert.assertEquals(
                expectedJson,
                actualJson,
                JSONCompareMode.LENIENT);
    }
}