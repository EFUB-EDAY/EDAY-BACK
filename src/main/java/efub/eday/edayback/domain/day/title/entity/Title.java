package efub.eday.edayback.domain.day.title.entity;

import efub.eday.edayback.domain.day.dday.entity.Subject;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Title {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "title_id")
    private Integer id;

    @Column(name = "title_name", nullable = false, length = 127)
    private String name;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @Column(name = "image_with_not_text_url", nullable = false)
    private String thumbnailImageUrl;

    @OneToOne
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

}
