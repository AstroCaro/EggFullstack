package principal.domain.pet;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import principal.domain.user.User;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class Pet {
    
    private int id;
    private String nickname;
    private String race;
    private User user;
    
}
