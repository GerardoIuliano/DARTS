package testSmellDetection.structuralRules;

import com.intellij.testFramework.fixtures.LightJavaCodeInsightFixtureTestCase;
import testSmellDetection.bean.PsiClassBean;
import utility.ConverterUtilities;
import utility.TestSmellUtilities;

import java.util.ArrayList;

public class TestSmellUtilitiesTest extends LightJavaCodeInsightFixtureTestCase {

    private ArrayList<PsiClassBean> psiClassBeans;

    @Override
    protected String getTestDataPath() {
        return "src/test/resources/testdata/";
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        myFixture.configureByFile("test/CondTestLogicNotPresentTest.java");
        psiClassBeans = ConverterUtilities.getClassesFromPackages(getProject());
    }

    public void testSmellUtilitiesNotNull() {
        ArrayList<PsiClassBean> testClassBeans = TestSmellUtilities.getAllTestClasses(psiClassBeans);
        assertNotNull(testClassBeans);
    }

    public void testSmellUtilitiesIstanceOfArrayList() {
        ArrayList<PsiClassBean> testClassBeans = TestSmellUtilities.getAllTestClasses(psiClassBeans);
        assertInstanceOf(testClassBeans,ArrayList.class);
    }

    public void testSmellUtilitiesIstanceOfPsiClassBean() {
        ArrayList<PsiClassBean> testClassBeans = TestSmellUtilities.getAllTestClasses(psiClassBeans);
        assertInstanceOf(testClassBeans.get(0),PsiClassBean.class);
    }
}
