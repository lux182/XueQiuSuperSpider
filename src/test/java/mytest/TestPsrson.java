package mytest;

import org.junit.Test;

/**
 * <p>注释</p>
 *
 * @author liuxuesi
 * @version $Id: mytest, v 0.1 2016/5/16 13:56 liuxuesi Exp $$
 */
public class TestPsrson {

    @Test
    public void testPersonFactory() {
        PersonFactory personFactory = Person::new;
        Person person = personFactory.create("Peter", "Parker");

        assert person.firstName=="Peter";
    }

}
