package testSmellDetection.textualRules;

import com.intellij.testFramework.fixtures.LightJavaCodeInsightFixtureTestCase;
import org.junit.jupiter.api.Test;

import testSmellDetection.bean.PsiClassBean;
import testSmellDetection.testSmellInfo.eagerTest.MethodWithEagerTest;
import utility.ConverterUtilities;
import utility.TestSmellUtilities;
import testSmellDetection.textualRules.EagerTestTextual;

import java.util.ArrayList;

public class EagerTestTextualTest extends LightJavaCodeInsightFixtureTestCase {
    @Override
    protected String getTestDataPath() {
        return "src/test/resources/testdata/";
    }

    public void testEagerTestNotPresent() {
        myFixture.configureByFile("main/EagerTestNotPresent.java");
        myFixture.configureByFile("test/EagerTestNotPresentTest.java");
        ArrayList<PsiClassBean> psiClassBeans = ConverterUtilities.getClassesFromPackages(getProject());
        ArrayList<PsiClassBean> testClassBeans = TestSmellUtilities.getAllTestClasses(psiClassBeans);
        ArrayList<MethodWithEagerTest> smellList = EagerTestTextual.checkMethodsThatCauseEagerTest(testClassBeans.get(0), testClassBeans.get(0).getProductionClass());
        assertEquals(null, smellList);
    }

    public void testEagerTestPresent() {
        myFixture.configureByFile("main/EagerTestPresent.java");
        myFixture.configureByFile("test/EagerTestPresentTest.java");
        ArrayList<PsiClassBean> psiClassBeans = ConverterUtilities.getClassesFromPackages(getProject());
        ArrayList<PsiClassBean> testClassBeans = TestSmellUtilities.getAllTestClasses(psiClassBeans);
        ArrayList<MethodWithEagerTest> smellList = EagerTestTextual.checkMethodsThatCauseEagerTest(testClassBeans.get(0), testClassBeans.get(0).getProductionClass());
        assertEquals(1, smellList.size());
    }
}