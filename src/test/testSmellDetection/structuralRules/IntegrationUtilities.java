package testSmellDetection.structuralRules;

import com.intellij.testFramework.fixtures.LightJavaCodeInsightFixtureTestCase;
import testSmellDetection.bean.PsiClassBean;
import utility.ConverterUtilities;
import utility.TestSmellUtilities;

import java.util.ArrayList;

public class IntegrationUtilities extends LightJavaCodeInsightFixtureTestCase {

    @Override
    protected String getTestDataPath() {
        return "src/test/resources/testdata/";
    }

    public void testIntegrationUtilitiesNotNull() {
        myFixture.configureByFile("test/CondTestLogicPresentTest.java");
        ArrayList<PsiClassBean> psiClassBeans = ConverterUtilities.getClassesFromPackages(getProject());
        ArrayList<PsiClassBean> testClassBeans = TestSmellUtilities.getAllTestClasses(psiClassBeans);
        assertNotNull(testClassBeans);
    }

    public void testIntegrationUtilitiesIstanceOfArrayList() {
        myFixture.configureByFile("test/CondTestLogicPresentTest.java");
        ArrayList<PsiClassBean> psiClassBeans = ConverterUtilities.getClassesFromPackages(getProject());
        ArrayList<PsiClassBean> testClassBeans = TestSmellUtilities.getAllTestClasses(psiClassBeans);
        assertInstanceOf(testClassBeans,ArrayList.class);
    }

    public void testIntegrationUtilitiesIstanceOfPsiClassBean() {
        myFixture.configureByFile("test/CondTestLogicPresentTest.java");
        ArrayList<PsiClassBean> psiClassBeans = ConverterUtilities.getClassesFromPackages(getProject());
        ArrayList<PsiClassBean> testClassBeans = TestSmellUtilities.getAllTestClasses(psiClassBeans);
        assertInstanceOf(testClassBeans.get(0),PsiClassBean.class);
    }
}
