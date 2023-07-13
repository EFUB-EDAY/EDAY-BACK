package efub.eday.edayback.domain.day.quiz.service;

import efub.eday.edayback.domain.day.quiz.entity.Quiz;
import efub.eday.edayback.domain.day.quiz.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class QuizService {
    private final QuizRepository quizRepository;

    //퀴즈 내용 조회
    @Transactional(readOnly = true)
    public Quiz findQuiz(Long d_day){
        return quizRepository.findByDDayId(d_day)
                .orElseThrow(()-> new IllegalArgumentException("퀴즈를 찾을 수 없습니다."));
    }
}
