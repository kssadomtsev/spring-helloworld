import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(AppConfig.class);
        HelloWorld beanOne =
                (HelloWorld) applicationContext.getBean("helloworld");
        System.out.println(beanOne.getMessage());

        HelloWorld beanTwo =
                (HelloWorld) applicationContext.getBean("helloworld");

        Cat catOne = applicationContext.getBean("cat", Cat.class);
        Cat catTwo = applicationContext.getBean("cat", Cat.class);
        System.out.println(catOne == catTwo);
        System.out.println(beanOne == beanTwo);
    }
}