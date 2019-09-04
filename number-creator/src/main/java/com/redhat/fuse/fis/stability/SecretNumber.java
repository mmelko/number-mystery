package com.redhat.fuse.fis.stability;

import lombok.Getter;
import lombok.Setter;

public class SecretNumber {

    @Getter
    @Setter
    private int secretNumber;
    @Getter
    private long attempts;
    @Getter
    private int guessed;

    @Getter
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
