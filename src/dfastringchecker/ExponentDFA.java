package dfastringchecker;
import java.util.ArrayList;
import java.util.Scanner;

/*
* @author Saad Ahmed
 */
public class ExponentDFA {
    protected State [] states;
    protected int numberofStates;
    protected int initialState;
    protected int finalState;
    ExponentDFA(){
        initialState=0;
        finalState=6;
        states= new State[7];
        String Name=null;
        for (int i=0;i<7;i++){
            Name="Q"+(i+1);
            states[i]= new State(Name ,null, null, null, null, i);
        }
        states[0].setTransitiononDigit(states[1]);
        states[1].setTransitiononDigit(states[1]);
        states[1].setTransitiononDecimal(states[2]);
        states[2].setTransitiononDigit(states[3]);
        states[3].setTransitiononDigit(states[3]);
        states[3].setTransitiononIdentifier(states[4]);
        states[4].setTransitiononOperator(states[5]);
        states[5].setTransitiononDigit(states[6]);
        states[6].setTransitiononDigit(states[6]);
    }
    public String CheckAcceptance(String input){
        State state=states[0];
        int index=0;
        int in=0;
        String Trace=state.stateName+"  --->  ";
        char curr=input.charAt(index);
        ArrayList <String> Names= new ArrayList();
        while(curr!=';' && state!=null){
            if ( curr=='0' || curr=='1' || curr=='2' || curr=='3' 
                    || curr=='4' || curr=='5' || curr=='6' || curr=='7' || curr=='8' || curr=='9'){
                if (state.getTransitiononDigit()!=null){
                    state=state.getTransitiononDigit();
                    Names.add(in,state.getStateName());
                }
                in++;
            }
            else if (curr=='.'){
                if(state.getTransitiononDecimal()!=null){
                        state=state.getTransitiononDecimal();
                        Names.add(in,state.getStateName());
                    }
                    in++;
                }
            else if (curr=='e' || curr=='E'){
                if(state.getTransitiononIdentifier()!=null){
                    state=state.getTransitiononIdentifier();
                    Names.add(in,state.getStateName());
                    in++;                    
                }
            }
            else if (curr=='+' || curr=='-'){
                if(state.getTransitiononOperator()!=null){
                    state=state.getTransitiononOperator();
                    Names.add(in,state.getStateName());
                    in++;
                }
            }
            index++;
            curr=input.charAt(index);
        }
        if (state.getStateNumber()==this.finalState){
            System.out.print("String is part of the Regular Expression's Language dd*.dd*e(+|-)dd*\n");
        }
        else{
            System.out.print("String is not part of the Regular Expression's Language dd*.dd*e(+|-)dd*\n");
        }
        index=0;
        while(index<Names.size()){
            Trace+=Names.get(index)+"  --->  ";
            index++;
        }
        System.out.println();
        return Trace;
    }
    public void Main(){
        int Choice,Choice1;
        String inp;
        String Trace;
        System.out.print("\nDFA Simulation for Regular Expression of Type\n\tdd*.dd*e(+|-)dd*\n\n\n1.Show Transition Table\n2.Check Acceptance of String\n3.Exit");
        System.out.print("\nPlease Enter Your Choice: ");
        Scanner scanner = new Scanner(System.in);
        Choice=scanner.nextInt();
        switch(Choice){
            case 1:
                showTransitionTable();
                break;
            case 2:
                System.out.print("Please Enter String (Ends with a Terminator): ");
                inp=scanner.next();
                Trace=CheckAcceptance(inp);
                System.out.print("1.Show String Path\n");
                Choice1=scanner.nextInt();
                switch(Choice1){
                    case 1:
                        System.out.print(Trace+" End");
                        break;
                }
                Main();
            case 3:
                System.exit(0);
            default:
                Main();
        }
    }
    public void showTransitionTable(){
        System.out.print("\nTransition Table\n");
        System.out.print("State\t\t0-9\t\t+|-\t\te\t\t.\n");
        String sN=null,sTD=null,sTO=null,sTI=null,sTd=null;
        boolean f1=false,f2=false,f3=false,f4=false;
        for (int i=0;i<7;i++){
            sN="";
            sTD="";
            sTO="";
            sTI="";
            sTd="";
            sN=states[i].getStateName();
            if(states[i].getTransitiononDigit()!=null){
                f1=true;
                sTD=states[i].getTransitiononDigit().getStateName();                
            }
            if (states[i].getTransitiononOperator()!=null){
                f2=true;
                sTO=states[i].getTransitiononOperator().getStateName();            
            }
            if (states[i].getTransitiononIdentifier()!=null){
                f3=true;
                sTI=states[i].getTransitiononIdentifier().getStateName();                
            }
            if (states[i].getTransitiononDecimal()!=null){
                f4=true;
                sTd=states[i].getTransitiononDecimal().getStateName();
            }
            System.out.print(sN);
            if (f1==true){
                System.out.print("\t\t"+sTD);
            }
            else{
                System.out.print("\t\t");
            }
            if (f2==true){
                System.out.print("\t\t"+sTO);
            }
            else{
                System.out.print("\t\t");
            }
            if (f3==true){
                System.out.print("\t\t"+sTI);
            }
            else{
                System.out.print("\t\t");
            }            
            if (f4==true){
                System.out.print("\t\t"+sTd);
            }
            else{
                System.out.print("\t\t");
            }            
            System.out.println();
        }
        System.out.println();
        System.out.println();
        Main();
    }
}