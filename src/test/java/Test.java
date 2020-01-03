import com.github.houbb.opencc4j.util.ZhConverterUtil;

/**
 * Created by songpanfei on 2019-11-22.
 */
public class Test {
    public static void  main(String[] args){
        String a = ZhConverterUtil.convertToSimple("中島竜生", false);
        System.out.println(a);
    }
}
