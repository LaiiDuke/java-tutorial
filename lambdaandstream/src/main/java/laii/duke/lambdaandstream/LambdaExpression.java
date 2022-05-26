package laii.duke.lambdaandstream;


@FunctionalInterface
interface BasicLambda {
    String doSomething();
}

@FunctionalInterface
interface BasicLambdaWithOneParam {
    String doSomething(String a);
}

public class LambdaExpression {
    public static void main(String[] args) {






        BasicLambda basicLambda = new BasicLambda() {
            @Override
            public String doSomething() {
                return "Basic lambda new";
            }
        };




        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("I have implemented Runnable!");
            }
        };
        runnable.run();


        BasicLambda s = () -> {
            return "Hello Lambda.";
        };

//        BasicLambda s = () -> "Hello Lambda.";

        System.out.println(s.doSomething());

        BasicLambdaWithOneParam basicLambdaWithOneParam = (String a) -> "Lambda With Argument value is: " + a;

        System.out.println(basicLambdaWithOneParam.doSomething("test"));


    }
}

class BasicLambdaImpl implements BasicLambda {
    @Override
    public String doSomething() {
        return "I have implemented Runnable!";
    }
}