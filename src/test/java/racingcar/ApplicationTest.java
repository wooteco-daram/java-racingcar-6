package racingcar;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.test.NsTest;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomNumberInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;

class ApplicationTest extends NsTest {
    private static final int MOVING_FORWARD = 4;
    private static final int STOP = 3;

    @Test
    void 전진_정지() {
        assertRandomNumberInRangeTest(
            () -> {
                run("pobi,woni", "1");
                assertThat(output()).contains("pobi : -", "woni : ", "최종 우승자 : pobi");
            },
            MOVING_FORWARD, STOP
        );
    }

    @Test
    void 이름에_대한_예외_처리() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("pobi,javaji", "1"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 경주할_자동차_이름을_입력하는_메시지_출력() {
        final String STRING_INPUT_CAR_NAME = "경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)";
        Application.printInputCarNameMessage();
        assertThat(output()).contains(STRING_INPUT_CAR_NAME);
    }

    @Test
    void 경주할_자동차_이름을_입력() {
        final String expected = "pobi,woni,jun";

        try (MockedStatic<Console> mockConsole = mockStatic(Console.class)) {
            mockConsole.when(() -> Console.readLine()).thenReturn(expected);
            final String readCarNames = Application.readCarNames();
            assertThat(readCarNames).contains(expected);
        }
    }

    @Test
    void 경주할_자동차_이름을_입력이후_Console_close_메서드_호출() {
        try (MockedStatic<Console> mockConsole = mockStatic(Console.class)) {
            mockConsole.when(() -> Console.readLine()).thenReturn("");
            Application.readCarNames();
            mockConsole.verify(() -> Console.close(), times(1));
        }
    }

    @Test
    void 자동차_이름을_쉼표로_구분하여_이름_배열을_만듦() {
        final List<String> expected = List.of("pobi", "woni", "jun");
        final List<String> actual = Application.getCarNameListFromCarNames("pobi,woni,jun");
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void getCarNameListFromCarNames_메서드에서_받은_리스트는_수정이_불가능() {
        final List<String> carNameList = Application.getCarNameListFromCarNames("pobi,woni,jun");
        assertThatThrownBy(() -> carNameList.set(0, "daram"))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void 시도할_회수를_물어보는_문구_출력() {
        final String STRING_INPUT_TRY_COUNT = "시도할 회수는 몇회인가요?";
        Application.printInputTryCountMessage();
        assertThat(output()).contains(STRING_INPUT_TRY_COUNT);
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
