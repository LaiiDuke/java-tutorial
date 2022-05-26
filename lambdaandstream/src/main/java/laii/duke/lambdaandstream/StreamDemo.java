package laii.duke.lambdaandstream;

import lombok.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class StreamDemo {

    public static void main(String[] args) {
        List<Product> productList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            productList.add(new Product("Product" + i, Math.random() * 100 * i, (long) (Math.random() * 1000)));
        }
        List<Product> productList1 = new ArrayList<>(productList);
        List<Product> productListRm = new ArrayList<>();
        Date current = new Date();
        Long forTook = 0L;
        Long streamTook = 0L;
        for (Product pro :
                productList) {
//            System.out.println(Thread.currentThread().getName());

            pro.setPrice((double) Math.round(pro.getPrice() * 10));
            if (pro.getQuantity() < 200) {
                productListRm.add(pro);
            }
        }
        productList.removeAll(productListRm);
        forTook = (new Date().getTime() - current.getTime());

//        productList.forEach(System.out::println);

        current = new Date();

        productList1 = productList1.parallelStream().map(x -> {
            System.out.println(Thread.currentThread().getName());
            x.setPrice((double) Math.round(x.getPrice() * 10));
            return x;
        }).filter(x -> x.getQuantity() >= 200).collect(Collectors.toList());

//        List<String> stringList = productList1.stream().map(Product::getName).collect(Collectors.toList());
//        List<Product> list2 = productList1.stream().map(x -> {
//            x.setPrice(0D);
//            System.out.println(Thread.currentThread().getName());
//            return x;
//        }).collect(Collectors.toList());

        streamTook = (new Date().getTime() - current.getTime());
        System.out.println("foreach took: " + forTook);
        System.out.println(productList.size());
        System.out.println("stream took: " + streamTook);
        System.out.println(productList1.size());
//        productList1.forEach(System.out::println);

        BasicLambdaWithOneParam basicLambdaWithOneParam = (a -> "This is the " + a);

        productList = productList.stream().peek(x -> x.setName(basicLambdaWithOneParam.doSomething(x.getName()))).collect(Collectors.toList());
        productList.stream().parallel().forEach(System.out::println);



        List<String> data = Arrays.asList("Java", "C#", "C++", "PHP", "Javascript");

        data.stream() //
                .map(String::toUpperCase) // convert each element to upper case
                .forEach(System.out::println);
        data.forEach(System.out::println);
    }

}

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
class Product {
    private String name;
    private Double price;
    private Long quantity;
}


