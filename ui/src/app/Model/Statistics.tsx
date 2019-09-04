class Statistics {
    private secretNumber: number;
    private attempts: number;
    private guessed: number;
    private lastOne: number;

    constructor(secretN: number, attempts: number, guessed: number, lastOne: number) {
        this.secretNumber = secretN;
        this.attempts = attempts;
        this.guessed = guessed;
        this.lastOne = lastOne;
    }

    public getSecretNumber() {
        return this.secretNumber;
    }
    public getAttempts() {
        return this.attempts;
    }

    public getGuessed() {
        return this.guessed;
    }
    public getLastOne() {
        return this.lastOne;
    }
}

export { Statistics };