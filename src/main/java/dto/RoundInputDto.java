package dto;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RoundInputDto {
    private List<Integer> numbers;


    private RoundInputDto(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public static RoundInputDto from(final String[] userInputTokens) {
        validate(userInputTokens);
        List<Integer> numbers = extractNumbersFrom(userInputTokens);
        return new RoundInputDto(numbers);
    }

    private static List<Integer> extractNumbersFrom(final String[] userInputTokens) {
        return Arrays.stream(userInputTokens)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    private static void validate(final String[] userInputTokens) {
        if (hasNonDigitNumber(userInputTokens)) {
            throw new IllegalArgumentException("숫자 이외의 문자가 포함되어 있습니다");
        }
    }

    private static boolean hasNonDigitNumber(final String[] userInputTokens) {
        return Arrays.stream(userInputTokens)
                .anyMatch(userInputToken -> userInputToken.chars()
                        .anyMatch(token -> !Character.isDigit(token)));
    }

    public List<Integer> numbers() {
        return numbers;
    }
}