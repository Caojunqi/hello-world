package org.chapter3.exercise1.test1;

public interface Result {

    public class Success implements Result{
        @Override
        public void exc(ResultExecutable resultExecutable) {
            resultExecutable.successExc();
        }
    }

    public class Failure implements Result{
        private final String errorMessage;

        @Override
        public void exc(ResultExecutable resultExecutable) {
            resultExecutable.failureExc();
        }

        public Failure(String s){
            this.errorMessage = s;
        }

        public String getMessage(){
            return errorMessage;
        }
    }

    void exc(ResultExecutable resultExecutable);
}
