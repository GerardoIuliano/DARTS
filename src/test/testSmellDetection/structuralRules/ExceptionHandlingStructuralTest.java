package testSmellDetection.structuralRules;

import com.intellij.testFramework.fixtures.LightJavaCodeInsightFixtureTestCase;
import testSmellDetection.bean.PsiClassBean;
import testSmellDetection.structuralRules.ExceptionHandlingStructural;
import testSmellDetection.testSmellInfo.ExceptionHandlingInfo.MethodWithExceptionHandling;
import utility.ConverterUtilities;
import utility.TestSmellUtilities;

import java.util.ArrayList;

public class ExceptionHandlingStructuralTest extends LightJavaCodeInsightFixtureTestCase {

    @Override
    protected String getTestDataPath() {
        return "src/test/resources/testdata/";
    }

    public void testExceptionHandlingNotPresent() {
        myFixture.configureByFile("test/ExceptionTestNotPresent.java");
        ArrayList<PsiClassBean> psiClassBeans = ConverterUtilities.getClassesFromPackages(getProject());
        ArrayList<PsiClassBean> testClassBeans = TestSmellUtilities.getAllTestClasses(psiClassBeans);
        ArrayList<MethodWithExceptionHandling> smellList = ExceptionHandlingStructural.checkMethodsThatContainExceptions(testClassBeans.get(0));
        assertEquals(null, smellList);
    }

    public void testExceptionHandlingPresent() {
        myFixture.configureByFile("test/ExceptionTestPresent.java");
        ArrayList<PsiClassBean> psiClassBeans = ConverterUtilities.getClassesFromPackages(getProject());
        ArrayList<PsiClassBean> testClassBeans = TestSmellUtilities.getAllTestClasses(psiClassBeans);
        ArrayList<MethodWithExceptionHandling> smellList = ExceptionHandlingStructural.checkMethodsThatContainExceptions(testClassBeans.get(0));
        assertEquals(2, smellList.size());
    }
}
