package testSmellDetection.structuralRules;

import com.intellij.testFramework.fixtures.LightJavaCodeInsightFixtureTestCase;
import testSmellDetection.bean.PsiClassBean;
import utility.ConverterUtilities;

import java.util.ArrayList;

public class ConverterUtilitiesTest extends LightJavaCodeInsightFixtureTestCase {

    @Override
    protected String getTestDataPath() {
        return "src/test/resources/testdata/";
    }

    public void testConverterUtilitiesNotNull() {
        myFixture.configureByFile("test/CondTestLogicNotPresentTest.java");
        ArrayList<PsiClassBean> psiClassBeans = ConverterUtilities.getClassesFromPackages(getProject());
        assertNotNull("PsiClassBean not null",psiClassBeans);
    }

    public void testConverterUtilitiesIstanceOfPsiClassBean() {
        myFixture.configureByFile("test/CondTestLogicNotPresentTest.java");
        ArrayList<PsiClassBean> psiClassBeans = ConverterUtilities.getClassesFromPackages(getProject());
        assertInstanceOf(psiClassBeans.get(0),PsiClassBean.class);
    }

    public void testConverterUtilitiesIstanceOfArrayList() {
        myFixture.configureByFile("test/CondTestLogicNotPresentTest.java");
        ArrayList<PsiClassBean> psiClassBeans = ConverterUtilities.getClassesFromPackages(getProject());
        assertInstanceOf(psiClassBeans,ArrayList.class);
    }
}
