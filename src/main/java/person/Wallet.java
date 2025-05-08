package person;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Wallet {
    private double balance;
    private Person owner;
}
