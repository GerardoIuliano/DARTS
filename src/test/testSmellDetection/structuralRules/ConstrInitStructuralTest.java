package testSmellDetection.structuralRules;

import com.intellij.testFramework.fixtures.LightJavaCodeInsightFixtureTestCase;
import testSmellDetection.bean.PsiClassBean;
import testSmellDetection.structuralRules.ConstructorInitStructural;
import testSmellDetection.testSmellInfo.constructorInitialization.MethodWithConstructorInitialization;
import utility.ConverterUtilities;
import utility.TestSmellUtilities;

import java.util.ArrayList;

public class ConstrInitStructuralTest extends LightJavaCodeInsightFixtureTestCase {

    @Override
    protected String getTestDataPath() {
        return "src/test/resources/testdata/";
    }

    public void testConstructorNotPresent() {
        myFixture.configureByFile("test/ConstructorInitializationNotPresentTest.java");
        ArrayList<PsiClassBean> psiClassBeans = ConverterUtilities.getClassesFromPackages(getProject());
        ArrayList<PsiClassBean> testClassBeans = TestSmellUtilities.getAllTestClasses(psiClassBeans);
        ArrayList<MethodWithConstructorInitialization> smellList = ConstructorInitStructural.checkMethodsThatCauseConstructorInitialization(testClassBeans.get(0));
        assertEquals(null, smellList);
    }

    public void testConstructorPresent() {
        myFixture.configureByFile("test/ConstructorInitializationPresentTest.java");
        ArrayList<PsiClassBean> psiClassBeans = ConverterUtilities.getClassesFromPackages(getProject());
        ArrayList<PsiClassBean> testClassBeans = TestSmellUtilities.getAllTestClasses(psiClassBeans);
        ArrayList<MethodWithConstructorInitialization> smellList = ConstructorInitStructural.checkMethodsThatCauseConstructorInitialization(testClassBeans.get(0));
        assertEquals(1, smellList.size());
    }
}
