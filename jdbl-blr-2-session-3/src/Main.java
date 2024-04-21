import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
//        System.out.println("Hello world!");
//
//        String a = "abc";
//        System.out.println(a.hashCode());
//        String b= "abc";
//        System.out.println(b.hashCode());
//
//        if (a.equals(b)){
//            System.out.println(" a equals to b");
//        }

//        TakeOff<Flyable> takeOff = new TakeOff();
//        takeOff.fly(new Aeroplane());
//
//        takeOff.fly(new Helicopter());
//
//        PrintNumbers<Number> test = new PrintNumbers<>();
//        test.print(1);

//        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8);
//        // Odd numbers
//        List<Integer> oddNumbers = list.
//                stream().
//                filter( x -> x%2 !=  0 )
//                .collect(Collectors.toList());

//        for(Integer a : oddNumbersSquare ){
//            System.out.println(a);
//        }




//        List<Integer> oddNumbersSquare = list.
//                stream().
//                filter( x -> x%2 !=  0 )
//                .map( x -> {
//                    System.out.println(x);
//                    return x*x;
//                })
//                .collect(Collectors.toList());
//
//        Optional<Integer> sum = oddNumbersSquare.
//              stream().
//              map( x -> {
//                    System.out.println(x);
//                    return x;
//                })
//                .reduce( (x,y) -> x*y);
//        System.out.println(sum.get());


        // Find multiplication of all elements
//        List<Integer> list2 = Arrays.asList(1,2,3);
//        Optional<Integer> mul = list2.parallelStream().reduce((a,b)-> a*b);
//        System.out.println(mul.get());

//        Set<Integer> set  = new HashSet<>();
//
//        set.add(1);
//        set.add(3);
//        set.add(4);
//        set.add(2);
//        set.add(6);
//        set.add(7);
//        set.add(67);
//        set.add(11);
//
//        for ( Integer i : set){
//            System.out.println(i);
//        }

//        Set<Integer> set  = new LinkedHashSet<>();
//
//        set.add(1);
//        set.add(3);
//        set.add(4);
//        set.add(2);
//        set.add(6);
//        set.add(7);
//        set.add(67);
//        set.add(11);
//
//        for ( Integer i : set){
//            System.out.println(i);
//        }

//        Set<Integer> set  = new TreeSet<>();
//
//        set.add(1);
//        set.add(3);
//        set.add(4);
//        set.add(2);
//        set.add(6);
//        set.add(7);
//        set.add(67);
//        set.add(11);
//
//        for ( Integer i : set){
//            System.out.println(i);
//        }

//        Set<MovieReview> set  = new TreeSet<>();
//        set.add(new MovieReview(5,5));
//        set.add(new MovieReview(4,5));
//        set.add(new MovieReview(4,3));
//
//        for ( MovieReview i : set){
//           System.out.println(i.editorRating);
//        }


//        List<MovieReview> list  = new ArrayList<>();
//        list.add(new MovieReview(5,5));
//        list.add(new MovieReview(4,6));
//        list.add(new MovieReview(4,3));
//        Collections.sort(list);
//        for ( MovieReview i : list){
//            System.out.println(i.userRating + " "+ i.editorRating);
//        }


        List<MovieReviewWithComparator> list3  = new ArrayList<>();
        list3.add(new MovieReviewWithComparator(5,5));
        list3.add(new MovieReviewWithComparator(4,6));


        Collections.sort(list3, new MovieComparatorByEditorRating());
//        for ( MovieReviewWithComparator i : list3){
//            System.out.println(i.userRating + " "+ i.editorRating);
//        }

        Collections.sort(list3, new MovieComparatorByUserRating());
//        for ( MovieReviewWithComparator i : list3){
//            System.out.println(i.userRating + " "+ i.editorRating);
//        }
//
        PriorityQueue<MovieReviewWithComparator> priorityQueue = new
                PriorityQueue<>(new MovieComparatorByEditorRating());
        priorityQueue.add(new MovieReviewWithComparator(5,5));
        priorityQueue.add(new MovieReviewWithComparator(5,6));
        priorityQueue.add(new MovieReviewWithComparator(5,4));


        MovieReviewWithComparator movieReviewWithComparator =  priorityQueue.remove();

        System.out.println(movieReviewWithComparator.editorRating + " " +
                movieReviewWithComparator.userRating);



    }

    static void sort(List<MovieReviewWithComparator> list, String logic){
        if (logic.equals("editor")){
            Collections.sort(list, new MovieComparatorByEditorRating());
        }else if (logic.equals("user")){
            Collections.sort(list, new MovieComparatorByUserRating());
        }

    }
}