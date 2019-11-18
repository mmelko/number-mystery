package org.mmelko.test.numbermystery.creator;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SecretNumber {

    private int secretNumber;
    private long attempts;
    private int guessed;
    private int lastOne;

    public SecretNumber() {
        this.resetAll();
    }

    public void resetAll() {
        this.attempts = 0;
        this.secretNumber = 0;
        this.guessed = 0;
        this.lastOne = 0;
    }

    public void guess() {
        this.attempts++;
    }

    public void addguessed() {
        this.guessed++;
    }

}
