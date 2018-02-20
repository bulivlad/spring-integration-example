package ro.bulimac.vlad.springintegrationexample.message;

import lombok.*;

/**
 * @author vladclaudiubulimac on 20/02/2018.
 */

@Builder
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Email{

    private Long id;
    private String to;
    private String from;
    private String cc;
    private String subject;
    private String body;

}
