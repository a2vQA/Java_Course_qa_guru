package guru.qa.pages.components;

import static com.codeborne.selenide.Selenide.$;

public class CalendarComponent {

    public void setDate(String yyyy, String month, String dd){
        $(".react-datepicker__month-select").selectOption(month);
        $(".react-datepicker__year-select").selectOption(yyyy);
        $(".react-datepicker__day--0" + dd).click();
    }
}
