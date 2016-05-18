package mytest;

/**
 * <p>注释</p>
 *
 * @author liuxuesi
 * @version $Id: mytest, v 0.1 2016/5/16 13:54 liuxuesi Exp $$
 */
public interface PersonFactory<P extends Person> {
    P  create(String firstname,String lastname);
}
