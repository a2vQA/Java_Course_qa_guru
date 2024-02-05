package guru.qa.tests;

import com.codeborne.selenide.DragAndDropOptions;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.actions;
import static com.codeborne.selenide.Selenide.open;

public class DragAndDropTests extends BaseTest {

    @Test
    void dragAndDropTest() {
        open("https://the-internet.herokuapp.com/drag_and_drop");
        actions()
                .moveToElement($("#column-a"))
                .clickAndHold()
                .moveToElement($("#column-b"))
                .release()
                .perform();
        
        $("#column-a")
                .$(byTagAndText("header","B"))
                .shouldBe(visible);
        $("#column-b")
                .$(byTagAndText("header","A"))
                .shouldBe(visible);
    }

    @Test
    void dragAndDropBySelenideDragAndDropFunctionTest() {
        open("https://the-internet.herokuapp.com/drag_and_drop");
        $("#column-a")
                .dragAndDrop(DragAndDropOptions.to("#column-b"));

        $("#column-a")
                .$(byTagAndText("header","B"))
                .shouldBe(visible);
        $("#column-b")
                .$(byTagAndText("header","A"))
                .shouldBe(visible);
    }
}
