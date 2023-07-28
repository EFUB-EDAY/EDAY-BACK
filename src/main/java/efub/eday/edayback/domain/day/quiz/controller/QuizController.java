package efub.eday.edayback.domain.day.quiz.controller;

		import io.swagger.v3.oas.annotations.Operation;
		import io.swagger.v3.oas.annotations.media.Content;
		import io.swagger.v3.oas.annotations.media.Schema;
		import io.swagger.v3.oas.annotations.responses.ApiResponse;
		import io.swagger.v3.oas.annotations.responses.ApiResponses;
		import io.swagger.v3.oas.annotations.tags.Tag;
		import org.springframework.http.HttpStatus;
		import org.springframework.http.ResponseEntity;
		import org.springframework.web.bind.annotation.GetMapping;
		import org.springframework.web.bind.annotation.PathVariable;
		import org.springframework.web.bind.annotation.PostMapping;
		import org.springframework.web.bind.annotation.RequestBody;
		import org.springframework.web.bind.annotation.RequestMapping;
		import org.springframework.web.bind.annotation.ResponseStatus;
		import org.springframework.web.bind.annotation.RestController;

		import efub.eday.edayback.domain.day.quiz.dto.QuizAnswerResponseDto;
		import efub.eday.edayback.domain.day.quiz.dto.QuizRequestDto;
		import efub.eday.edayback.domain.day.quiz.dto.QuizResponseDto;
		import efub.eday.edayback.domain.day.quiz.entity.Quiz;
		import efub.eday.edayback.domain.day.quiz.service.QuizService;
		import lombok.RequiredArgsConstructor;
		import lombok.extern.slf4j.Slf4j;

@Tag(name="퀴즈", description = "퀴즈 관련 api입니다.")
@Slf4j
@RestController
@RequestMapping("/quiz/{dDay}")
@RequiredArgsConstructor
public class QuizController {
	private final QuizService quizService;

	//퀴즈 내용 조회
	@Operation(summary = "퀴즈 내용 조회 메서드", description = "해당 디데이의 퀴즈를 조회하는 메서드입니다.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "퀴즈 내용 조회 성공",
					content = @Content(mediaType = "application/json",
							schema = @Schema(implementation = QuizResponseDto.class))),
			@ApiResponse(responseCode = "404", description = "퀴즈 내용 조회 실패",
					content = @Content(mediaType = "application/json",
							schema = @Schema(implementation = QuizResponseDto.class)))
	})
	@GetMapping
	@ResponseStatus(value = HttpStatus.OK)
	public QuizResponseDto findQuiz(@PathVariable int dDay) {
		Quiz quiz = quizService.findQuiz(dDay);
		return QuizResponseDto.from(quiz);
	}

	//퀴즈 정답 확인
	@Operation(summary = "퀴즈 정답 확인 메서드", description = "퀴즈 선지 중 하나를 보내면 정답 여부를 판별해 주는 메서드입니다.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "퀴즈 정답 확인 성공",
					content = @Content(mediaType = "application/json",
							schema = @Schema(implementation = QuizAnswerResponseDto.class))),
			@ApiResponse(responseCode = "404", description = "퀴즈 정답 확인 실패",
					content = @Content(mediaType = "application/json",
							schema = @Schema(implementation = QuizAnswerResponseDto.class)))
	})
	@PostMapping
	public ResponseEntity<QuizAnswerResponseDto> checkAnswer(
			@PathVariable int dDay,
			@RequestBody QuizRequestDto quizRequestDto
	) {
		boolean isCorrect;
		String quizDescription = null;
		try {
			isCorrect = quizService.checkAnswer(dDay, quizRequestDto.getOptionNumber());
			if (isCorrect) {
				quizDescription = quizService.getQuizDescription(dDay);
			}
		} catch (IllegalArgumentException e) {
			String errorMessage = e.getMessage();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new QuizAnswerResponseDto(false, errorMessage));
		}
		return ResponseEntity.ok(new QuizAnswerResponseDto(isCorrect, quizDescription));
	}
}
