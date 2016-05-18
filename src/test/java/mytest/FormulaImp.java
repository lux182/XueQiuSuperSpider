package mytest;

import java.util.Arrays;
import java.util.List;

/**
 * <p>注释</p>
 *
 * @author liuxuesi
 * @version $Id: mytest, v 0.1 2016/5/16 10:23 liuxuesi Exp $$
 */
public class FormulaImp implements Formula {
    @Override
    public double caculate(int a) {
        return Math.sqrt(a * 100);
    }

    public static void main(String[] args) {
//        Formula f = new FormulaImp();
//        double a = f.caculate(16);
//        double b = f.sqrt(4);
        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");

//        Collections.sort(names, new Comparator<String>() {
//            @Override
//            public int compare(String a, String b) {
//                return a.compareTo(b);
//            }
//        });
//        System.out.println(names);
//        Collections.sort(names, (String a, String b) -> {
//            return b.compareTo(a);
//        });
//        Collections.sort(names, (String a, String b) -> b.compareTo(a));
//        Collections.sort(names, (a, b) -> b.compareTo(a));
//        System.out.println(names);
//        Converter<String, Integer> converter = (from) -> Integer.valueOf(from);
//        Integer converted = converter.convert("123");
//        System.out.println(converted);
        Converter<String, Integer> converter = Integer::valueOf;
        Integer converted = converter.convert("123");
        System.out.println(converted); // 123
    }

}
