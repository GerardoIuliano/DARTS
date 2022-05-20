package testSmellDetection.structuralRules;

import com.intellij.psi.PsiClass;
import com.intellij.testFramework.fixtures.LightJavaCodeInsightFixtureTestCase;
import org.junit.jupiter.api.Test;
import testSmellDetection.bean.PsiClassBean;
import utility.ConverterUtilities;
import utility.TestSmellUtilities;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Logger;

public class TestSmellUtilitiesTest extends LightJavaCodeInsightFixtureTestCase {

    private ArrayList<PsiClassBean> psiClassBeans;
    private ArrayList<PsiClassBean> testClassBeans = new ArrayList<PsiClassBean>();

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

    @Test
    public void testSmellUtilitiesNotNull() {
        JButton jb = new JButton();
        jb.addActionListener(actionEvent -> {
            testClassBeans = TestSmellUtilities.getAllTestClasses(psiClassBeans);
        });
        assertNotNull(testClassBeans);
    }

    @Test
    public void testSmellUtilitiesIstanceOfArrayList() {
        JButton jb = new JButton();
        jb.addActionListener(actionEvent -> {
            testClassBeans = TestSmellUtilities.getAllTestClasses(psiClassBeans);
        });
        assertInstanceOf(testClassBeans,ArrayList.class);
    }

    @Test
    public void testSmellUtilitiesIstanceOfPsiClassBean() {
        JButton jb = new JButton();
        jb.addActionListener(actionEvent -> {
            testClassBeans = TestSmellUtilities.getAllTestClasses(psiClassBeans);
            assertInstanceOf(testClassBeans.get(0), PsiClassBean.class);
        });
    }
}
