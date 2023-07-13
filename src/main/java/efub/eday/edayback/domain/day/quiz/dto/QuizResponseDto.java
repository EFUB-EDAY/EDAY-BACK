package efub.eday.edayback.domain.day.quiz.dto;

import efub.eday.edayback.domain.day.quiz.entity.Quiz;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QuizResponseDto {
    private Long dDay;
    private String topic;
    private String quizContent;
    private List<OptionsResponseDto> optionList;

    public QuizResponseDto(Long dDay, String topic, String quizContent, List<OptionsResponseDto> optionList){
        this.dDay=dDay;
        this.topic=topic;
        this.quizContent=quizContent;
        this.optionList=optionList;
    }

    public static QuizResponseDto from(Quiz quiz){
        List<OptionsResponseDto> optionList = quiz.getOptionsList()
                .stream()
                .map(OptionsResponseDto::from)
                .collect(Collectors.toList());
        return new QuizResponseDto(
                quiz.getDDay(),
                quiz.getDDay().getTopic(),
                quiz.getContent(),
                optionList
        );
    }

}
