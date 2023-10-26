package racingcar;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
    }

    public static void printInputCarNameMessage() {
        final String STRING_INPUT_CAR_NAME = "경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)";
        System.out.println(STRING_INPUT_CAR_NAME);
    }

    public static String readCarNames() {
        final String carNames = Console.readLine();
        Console.close();
        return carNames;
    }

    public static List<String> getCarNameListFromCarNames(final String carNames) {
        final String DELIMITER_CAR_NAMES = ",";
        final String[] carNamesSplittingByDelimiter = carNames.split(DELIMITER_CAR_NAMES);
        final List<String> carNameList = Arrays.asList(carNamesSplittingByDelimiter);
        return Collections.unmodifiableList(carNameList);
    }

    public static void printInputTryCountMessage() {
        final String STRING_INPUT_TRY_COUNT = "시도할 회수는 몇회인가요?";
        System.out.println(STRING_INPUT_TRY_COUNT);
    }

    public static int readTryCount() {
        final String tryCount = Console.readLine();
        final int tryCountConvertedToInt = Integer.parseInt(tryCount);
        Console.close();
        return tryCountConvertedToInt;
    }

    public static void printNewLine() {
        final String STRING_NEW_LINE = "\n";
        System.out.println(STRING_NEW_LINE);
    }

    public static void printExecutionResult() {
        final String STRING_EXECUTION_RESULT = "실행 결과";
        System.out.println(STRING_EXECUTION_RESULT);
    }

    public static List<Car> getCarList(List<String> carNameList) {
        final List<Car> carList = carNameList.stream()
                .map(Car::new)
                .collect(Collectors.toList());
        return Collections.unmodifiableList(carList);
    }

    public static void moveForward(List<Car> carList) {
        carList.stream().forEach(Car::moveForward);
    }
}
