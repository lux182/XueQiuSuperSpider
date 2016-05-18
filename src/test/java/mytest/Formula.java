package mytest;

/**
 * <p>注释</p>
 *
 * @author liuxuesi
 * @version $Id: mytest, v 0.1 2016/5/16 10:20 liuxuesi Exp $$
 */
public interface Formula {
    double caculate(int a);

    default double sqrt(int a) {
        return Math.sqrt(a);
    }

}
