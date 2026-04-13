package pro1.reports.report1;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import pro1.DataSource;
import pro1.apiDataModel.Book;
import pro1.apiDataModel.BooksList;
import pro1.apiDataModel.TeacherCoursesList;
import pro1.reports.report1.reportDataModel.CourseBook;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class TeacherBooksReporting {

    public static List<CourseBook> GetReport(DataSource dataSource, String rok, int ucitIdno, String katedra) {
        var coursesJson = dataSource.getPredmetyByUcitel(rok, ucitIdno, katedra);

        // TODO 1.1: Převeď coursesJson na objekt typu apiDataModel.TeacherCoursesList
        var coursesList = new Gson().fromJson(coursesJson, TeacherCoursesList.class);
        var reportItems = new ArrayList<CourseBook>();

        // TODO 1.3: Pro každý předmět získej z dataSource ještě seznam knih

        coursesList.items.forEach(course -> {
            var booksJson = dataSource.getLiteraturaPredmetu(course.code, katedra);
            BooksList booksList = new Gson().fromJson(booksJson, BooksList.class);  // was List<Book>
            for (var book : booksList.items) {
                reportItems.add(new CourseBook(book.title, book.author, course.code));
            }
        });

        return reportItems;
    }
}