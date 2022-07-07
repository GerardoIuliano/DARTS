package testSmellDetection;

import org.junit.Test;
import testConfiguration.TestConfig;
import testSmellDetection.bean.PsiClassBean;
import testSmellDetection.bean.PsiMethodBean;
import testSmellDetection.structuralRules.IgnoredTestStructural;
import testSmellDetection.structuralRules.MagicNumberStructural;
import testSmellDetection.testSmellInfo.IgnoredTest.MethodWithIgnoredTest;
import testSmellDetection.testSmellInfo.magicNamberTest.MethodWithMagicNumber;
import utility.ConverterUtilities;
import utility.TestSmellUtilities;

import java.util.ArrayList;

public class IgnoredTest extends TestConfig {

    @Test
    public void testTC_Sys_40(){
        super.setFileName("test/TestIT_Sys_0_instance.java");
        ArrayList<PsiClassBean> psiClassBeans = ConverterUtilities.getClassesFromPackages(getProject());
        ArrayList<PsiClassBean> testClassBeans = TestSmellUtilities.getAllTestClasses(psiClassBeans);
        ArrayList<MethodWithIgnoredTest> smellList = IgnoredTestStructural.checkMethodsThatCauseIgnoredTest(testClassBeans.get(0));
        assertEquals(null, smellList);
    }
    @Test
    public void testTC_Sys_41(){
        super.setFileName("test/TestIT_Sys_1_instance.java");
        ArrayList<PsiClassBean> psiClassBeans = ConverterUtilities.getClassesFromPackages(getProject());
        ArrayList<PsiClassBean> testClassBeans = TestSmellUtilities.getAllTestClasses(psiClassBeans);
        ArrayList<MethodWithIgnoredTest> smellList = IgnoredTestStructural.checkMethodsThatCauseIgnoredTest(testClassBeans.get(0));
        assertEquals(1, smellList.size());
    }
    @Test
    public void testTC_Sys_42(){
        super.setFileName("test/TestIT_Sys_2opiu_instance.java");
        ArrayList<PsiClassBean> psiClassBeans = ConverterUtilities.getClassesFromPackages(getProject());
        ArrayList<PsiClassBean> testClassBeans = TestSmellUtilities.getAllTestClasses(psiClassBeans);
        ArrayList<MethodWithIgnoredTest> smellList = IgnoredTestStructural.checkMethodsThatCauseIgnoredTest(testClassBeans.get(0));
        assertEquals(2, smellList.size());
    }

//    @Test
//    public void testIgnoredTestPresent() {
//        super.setFileName("test/IgnoredTestPresent.java");
//        ArrayList<PsiClassBean> psiClassBeans = ConverterUtilities.getClassesFromPackages(getProject());
//        ArrayList<PsiClassBean> testClassBeans = TestSmellUtilities.getAllTestClasses(psiClassBeans);
//        ArrayList<MethodWithIgnoredTest> smellList = IgnoredTestStructural.checkMethodsThatIgnoredTest(testClassBeans.get(0));
//        assertEquals(1, smellList.size());
//    }

//    @Test
//    public void testIgnoredTestNotPresent() {
//        super.setFileName("test/IgnoredTestNotPresent.java");
//        ArrayList<PsiClassBean> psiClassBeans = ConverterUtilities.getClassesFromPackages(getProject());
//        ArrayList<PsiClassBean> testClassBeans = TestSmellUtilities.getAllTestClasses(psiClassBeans);
//        ArrayList<MethodWithIgnoredTest> smellList = IgnoredTestStructural.checkMethodsThatIgnoredTest(testClassBeans.get(0));
//        assertEquals(null, smellList.size());
//    }
}

