class Integration {
    private id: string;
    private name: string;
    private currentState: string;
    private targetState: string;

    constructor(id:string,name:string,currentState:string,targetState:string) {
        this.id = id;
        this.name = name;
        this.currentState = currentState;
        this.targetState = targetState;
    }

    public getId() {
        return this.id;
    }
    public getName() {
        return this.name;
    }

    public getCurrentState(){
        return this.currentState;
    }
    public getTargetState() {
        return this.targetState;
    }
}

export {Integration};