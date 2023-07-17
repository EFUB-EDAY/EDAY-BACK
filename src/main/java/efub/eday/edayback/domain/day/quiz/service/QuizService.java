package efub.eday.edayback.domain.day.quiz.service;

import efub.eday.edayback.domain.day.dday.entity.Dday;
import efub.eday.edayback.domain.day.quiz.entity.Quiz;
import efub.eday.edayback.domain.day.quiz.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional
@RequiredArgsConstructor
public class QuizService {
    private final QuizRepository quizRepository;

    //퀴즈 내용 조회
    @Transactional(readOnly = true)
    public Quiz findQuiz(Long d_day){
        Dday[] validDdays = Dday.values();
        boolean isValidDday = false;

        for (Dday dday : validDdays) {
            if (dday.getRemainingDays() == d_day) {
                isValidDday = true;
                break;
            }
        }

        if (!isValidDday) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "올바르지 않은 D-day 값입니다.");
        }

        return quizRepository.findBySubject_Dday(d_day)
                .orElseThrow(()-> new IllegalArgumentException("퀴즈를 찾을 수 없습니다."));
    }
}
