package dfastringchecker;

/*
 * @author Saad Ahmed
 */
public class State {
    protected String stateName;
    protected boolean finalState;
    protected int stateNumber;
    protected State [] transitions;
    State(String StateName,State tonDigit,State tonOperator,State tonIdentifier,State tonDecimal, int sNumber){
        transitions = new State[4];
        stateNumber=sNumber;
        stateName=StateName;
        if (tonDigit!=null){
            transitions[0]=tonDigit;
        }
        else{
            transitions[0]=null;
        }
        if (tonOperator!=null){
            transitions[1]=tonOperator;
        }
        else{
            transitions[1]=null;
        }
        if (tonIdentifier!=null){
            transitions[2]=tonIdentifier;
        }
        else{
            transitions[2]=null;
        }
        if (tonDecimal!=null){
            transitions[3]=tonIdentifier;
        }
        else{
            transitions[3]=null;
        }
    }
    public State getTransitiononDigit(){        
        return transitions[0];
    }
    public State getTransitiononOperator(){        
        return transitions[1];
    }
    public State getTransitiononIdentifier(){        
        return transitions[2];
    }
    public State getTransitiononDecimal(){        
        return transitions[3];
    }
    public void setTransitiononDigit(State state){
        transitions[0]=state;
    }
    public void setTransitiononOperator(State state){
        transitions[1]=state;
    }
    public void setTransitiononIdentifier(State state){
        transitions[2]=state;
    }
    public void setTransitiononDecimal(State state){
        transitions[3]=state;
    }
    public String getStateName(){
        return this.stateName;
    }
    public int getStateNumber(){
        return this.stateNumber;
    }
}
